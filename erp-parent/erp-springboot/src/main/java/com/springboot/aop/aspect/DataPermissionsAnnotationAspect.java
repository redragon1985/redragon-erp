/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * This file is part of redragon-erp/赤龙ERP.

 * redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.springboot.aop.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.framework.shiro.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.framework.annotation.Permissions;

@Component
public class DataPermissionsAnnotationAspect {

    @Autowired
    private HrCommonService hrCommonService;

    public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {

        // 获取目标类的aop拦截的方法
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        // 获取方法参数
        Object[] args = jp.getArgs();
        Class[] classes = null;
        if (args != null && args.length > 0) {
            classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                if (methodSignature.getMethod().getParameterTypes()[i].getName().equals("java.lang.Object")) {
                    classes[i] = args[i].getClass();
                } else {
                    classes[i] = methodSignature.getMethod().getParameterTypes()[i];
                }
            }
        }
        Method method = jp.getTarget().getClass().getMethod(methodSignature.getMethod().getName(), classes);

        // 判断方法是否存在Permissions注解
        if (method.isAnnotationPresent(com.framework.annotation.Permissions.class)) {
            //获取用户和组织机构
            String username = ShiroUtil.getUsername();
            String orgCode = ShiroUtil.getOrgCode();

            System.out.println("===username:" + username);
            System.out.println("===orgCode:" + orgCode);

            // 获取Permissions注解信息
            com.framework.annotation.Permissions permissionAnnotation = method.getAnnotation(com.framework.annotation.Permissions.class);

            if (permissionAnnotation.value() != null) {

                System.out.println("===permissionAnnotation.value():" + permissionAnnotation.value());

                // 获取方法中的所有参数
                Class<?>[] class3 = method.getParameterTypes();
                for (int i = 0; i < class3.length - 1; i++) {

                    //找到用SqlParam注解的参数
                    boolean paramAnnotationFlag = false;
                    if (method.getParameterAnnotations()[i] != null && method.getParameterAnnotations()[i].length > 0) {
                        for (int x = 0; x < method.getParameterAnnotations()[i].length; x++) {
                            if (method.getParameterAnnotations()[i][x] != null) {
                                String paramAnnotationName = method.getParameterAnnotations()[i][x].annotationType().getName();
                                if (StringUtils.isNotEmpty(paramAnnotationName) && "com.framework.annotation.SqlParam".equals(paramAnnotationName)) {
                                    paramAnnotationFlag = true;
                                    break;
                                }
                            }
                        }
                    }

                    // 判断参数上是否包含SqlParam注解
                    if (paramAnnotationFlag) {
                        // 获取当前登录职员信息
                        HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(username);
                        
                        // 如果取不到当前登录人信息则查询不到任何数据
                        if (staffInfo != null) {
                            System.out.println("===staffInfo:" + staffInfo.getStaffName());
                            System.out.println("===staffInfo:" + staffInfo.getDepartmentSegmentDesc());
                            
                            // 查询当前登录人的数据权限
                            String dataAuthType = "";
                            int dataAuthLevel = -1;

                            if (SecurityUtils.getSubject().isPermitted("public_data_auth")) {
                                dataAuthType = "public";
                            } else if (SecurityUtils.getSubject().isPermitted("org_level2_data_auth")) {
                                dataAuthType = "org";
                                dataAuthLevel = 2;
                            } else if (SecurityUtils.getSubject().isPermitted("org_level1_data_auth")) {
                                dataAuthType = "org";
                                dataAuthLevel = 1;
                            } else if (SecurityUtils.getSubject().isPermitted("org_level0_data_auth")) {
                                dataAuthType = "org";
                                dataAuthLevel = 0;
                            } else if (SecurityUtils.getSubject().isPermitted("private_data_auth")) {
                                dataAuthType = "private";
                            }

                            System.out.println("===数据权限:" + dataAuthType + ",级别:" + dataAuthLevel);

                            // 查询当前登录人有权限的组织
                            Set<String> orgStructureSet = new HashSet<String>();

                            // 判断当前人是否有数据权限
                            if (com.framework.annotation.Permissions.PermissionType.DATA_AUTH.equals(permissionAnnotation.value()) && StringUtils.isNotBlank(dataAuthType)) {

                                if (dataAuthType.equals("public")) {
                                    // 当前登录人是公共数据权限
                                    System.out.println("===SecurityUtils:public");
                                    args[i] = "";
                                } else if (dataAuthType.equals("org") && dataAuthLevel != -1) {
                                    // 当前登录人是特定组织数据权限

                                    // 如果查询组织获取所有父辈节点
                                    List<String> orgStructureListTemp = new ArrayList<String>();
                                    if (staffInfo.getDeaprtmentSegmentCode() != null) {
                                        String[] parentOrgs = staffInfo.getDeaprtmentSegmentCode().split("_");
                                        for (int a = parentOrgs.length - 1; a >= 0; a--) {
                                            orgStructureListTemp.add(parentOrgs[a]);
                                        }
                                    }
                                    
                                    //根据节点数，循环添加有权限的组织节点
                                    for(int a=0;a<orgStructureListTemp.size();a++) {
                                        if(a<=dataAuthLevel) {
                                            orgStructureSet.add(orgStructureListTemp.get(a));
                                        }
                                    }

                                    // 根据组织数据权限的级别获取有权限的最高父组织
                                    String parentOrgStructure = "";
                                    if (dataAuthLevel < orgStructureListTemp.size()) {
                                        parentOrgStructure = orgStructureListTemp.get(dataAuthLevel);
                                    } else {
                                        parentOrgStructure = orgStructureListTemp.get(orgStructureListTemp.size() - 1);
                                    }

                                    System.out.println("===用户有权限的最高父组织:" + parentOrgStructure);

                                    // 查询有权限父组织下所有的下属组织
                                    if (StringUtils.isNotBlank(parentOrgStructure)) {
                                        List<HrDepartment> list = this.hrCommonService.getHrDepartmentChildList(parentOrgStructure);
                                        for (HrDepartment hrDepartment : list) {
                                            orgStructureSet.add(hrDepartment.getDepartmentCode());
                                        }
                                    }

                                    //拼接查询条件
                                    StringBuilder argsql = new StringBuilder();
                                    for (String value : orgStructureSet) {
                                        argsql.append(" " + Permissions.PermissionSQLDepartmentCodeAlias + "department_code ='" + value + "' or ");
                                    }

                                    args[i] = " and (" + argsql;
                                    args[i] = args[i] + " " + Permissions.PermissionSQLCreatedByAlias + "created_by ='" + username + "') ";

                                    
                                    System.out.println("===orgStructureSet:"+orgStructureSet.toString());
                                    System.out.println("===SecurityUtils:org");
                                } else {
                                    // 当前登录人是个人数据权限
                                    System.out.println("===SecurityUtils:private");
                                    args[i] = " and " + Permissions.PermissionSQLCreatedByAlias + "created_by ='" + username + "' ";
                                }
                            } else {
                                // 登陆人无数据权限，使用注解参数设置权限
                                if (com.framework.annotation.Permissions.PermissionType.OWN.equals(permissionAnnotation.value())) {
                                    // 权限为个人
                                    args[i] = " and " + Permissions.PermissionSQLCreatedByAlias + "created_by ='" + username + "' ";
                                } else if (com.framework.annotation.Permissions.PermissionType.COMPANY.equals(permissionAnnotation.value())) {
                                    // 当前功能未开放
                                } else if (com.framework.annotation.Permissions.PermissionType.OTHERCOMPANY.equals(permissionAnnotation.value())) {
                                    // 当前功能未开放
                                } else if (com.framework.annotation.Permissions.PermissionType.ALL.equals(permissionAnnotation.value())) {
                                    // 权限为公共
                                    args[i] = "";
                                } else {
                                    // 权限为个人
                                    args[i] = " and " + Permissions.PermissionSQLCreatedByAlias + "created_by ='" + username + "' ";
                                }
                            }

                        } else {
                            System.out.println("===staffInfo:null");
                            args[i] = " and 1=2 ";
                        }
                    }
                }
            }
        }
        return jp.proceed(args);
    }

}
