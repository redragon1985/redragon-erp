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
package com.erp.permission.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.erp.permission.dao.model.SysAuth;
import com.erp.permission.dao.model.SysRole;
import com.erp.permission.dao.model.SysRoleAuthR;
import com.erp.permission.dao.model.SysRoleCO;
import com.erp.permission.dao.model.SysUserRO;
import com.erp.permission.dao.model.SysUserRoleR;
import com.erp.permission.service.SysAuthService;
import com.erp.permission.service.SysRoleAuthRService;
import com.erp.permission.service.SysRoleService;

@Controller
@RequestMapping("/web/sysRole")
public class SysRoleWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SysRoleWebController.class);
    
    //服务层注入
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysAuthService sysAuthService;
    @Autowired
    private SysRoleAuthRService sysRoleAuthRService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSysRoleList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-06-30 18:19:21
     * @author 
     * @param pages
     * @param sysRoleCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysRoleList")
    public String getSysRoleList(Pages pages, SysRoleCO sysRoleCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SysRole> sysRoleList = this.sysRoleService.getDataObjects(pages, sysRoleCO);
        
        //页面属性设置
        model.addAttribute("sysRoleList", sysRoleList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=sysRole/sysRoleList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-06-30 18:19:21
     * @author 
     * @param sysRole
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysRole")
    public String getSysRole(SysRole sysRole, Model model) {
        //查询要编辑的数据
        if(sysRole!=null&&sysRole.getRoleId()!=null) {
            sysRole = this.sysRoleService.getDataObject(sysRole.getRoleId());
        }
        
        //页面属性设置
        model.addAttribute("sysRole", sysRole);
        
        return "basic.jsp?content=sysRole/sysRoleEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-06-30 18:19:21
     * @author 
     * @param sysRole
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSysRole")
    public String editSysRole(@Valid SysRole sysRole, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSysRole";
        }
        
        try {
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.sysRoleService.insertOrUpdateDataObject(sysRole);
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }catch(Exception e){
            if(e.getCause().getClass()==ConstraintViolationException.class) {
                //提示信息
                model.addAttribute("hint", "hint");
                model.addAttribute("alertMessage", "编码已存在请重新输入");
                return "forward:getSysRole";
            }else {
                throw e;
            }
        }
        
        return "redirect:getSysRoleList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-06-30 18:19:21
     * @author 
     * @param sysRole
     * @return String
     *
     */
    @RequestMapping("deleteSysRole")
    public String deleteSysRole(SysRole sysRole, RedirectAttributes attr) {
        //删除数据前验证数据
        if(sysRole!=null&&sysRole.getRoleId()!=null) {
            //判断是否存在关联数据
            if(this.sysRoleService.isExistRelateDataForSysRole(sysRole.getRoleCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "存在角色的关联数据，无法删除角色");
            }else {
                //删除数据
                this.sysRoleService.deleteDataObject(sysRole);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
            
        }
        
        return "redirect:getSysRoleList";
    }
    
    
    
    /**
     * 
     * @description 在跳转用户和角色关联页面前准备数据
     * @date 2020-06-27 20:46:27
     * @author 
     * @param username
     * @param model
     * @return String
     *
     */
    @RequestMapping("beforeRelateSysRoleAuth")
    public String beforeRelateSysRoleAuth(String roleCode, Model model) {
        
        //获取要关联的角色
        List<SysRole> sysRoleList = this.sysRoleService.getSysRoleListByStatus(null);
        //获取要关联的权限
        List<SysAuth> sysAuthList = this.sysAuthService.getSysAuthListByStatus(null);
        
        if(StringUtils.isNotBlank(roleCode)) {
            //获取用户已关联的权限
            List<SysAuth> sysAuthRelateList = this.sysAuthService.getRelateSysAuthListByRoleCode(roleCode);
            model.addAttribute("sysAuthRelateList", sysAuthRelateList);
            
            //根据已关联的权限得到未关联的权限
            if(sysAuthList.size()>0&&sysAuthRelateList.size()>0) {
                Iterator<SysAuth> sysAuthListIterator = sysAuthList.iterator();
                while(sysAuthListIterator.hasNext()) {
                    SysAuth sysAuthTemp = sysAuthListIterator.next();
                    for(SysAuth sysAuthR:sysAuthRelateList) {
                        if(sysAuthTemp.getAuthCode().equals(sysAuthR.getAuthCode())) {
                            sysAuthListIterator.remove();
                            break;
                        }
                    }
                }
            }
        }
        
        //页面属性设置
        model.addAttribute("sysRoleList", sysRoleList);
        model.addAttribute("sysAuthList", sysAuthList);
        
        return "basic.jsp?content=sysRole/roleAuthRelate";
    }
    
    /**
     * 
     * @description 获取角色列表关联的页面
     * @date 2020年7月1日
     * @author dongbin
     * @param username
     * @param model
     * @return String
     *
     */
    @RequestMapping("getAuthListRelateAjax")
    public String getAuthListRelateAjax(String roleCode, Model model) {
        
        if(StringUtils.isNotBlank(roleCode)) {
            //获取要关联的权限
            List<SysAuth> sysAuthList = this.sysAuthService.getSysAuthListByStatus(null);
            //获取用户已关联的权限
            List<SysAuth> sysAuthRelateList = this.sysAuthService.getRelateSysAuthListByRoleCode(roleCode);
            
            //根据已关联的权限得到未关联的权限
            if(sysAuthList.size()>0&&sysAuthRelateList.size()>0) {
                Iterator<SysAuth> sysAuthListIterator = sysAuthList.iterator();
                while(sysAuthListIterator.hasNext()) {
                    SysAuth sysAuthTemp = sysAuthListIterator.next();
                    for(SysAuth sysAuthR:sysAuthRelateList) {
                        if(sysAuthTemp.getAuthCode().equals(sysAuthR.getAuthCode())) {
                            sysAuthListIterator.remove();
                            break;
                        }
                    }
                }
            }
            
            //页面属性设置
            model.addAttribute("sysAuthList", sysAuthList);
            model.addAttribute("sysAuthRelateList", sysAuthRelateList);
        }
        
        return "sysRole/ajax/authListRelateAjax";
    }
    
    /**
     * 
     * @description 关联用户和角色
     * @date 2020-06-27 20:46:27
     * @author 
     * @param username
     * @param roleCode
     * @param model
     * @param attr
     * @return String
     *
     */
    @RequestMapping("relateSysRoleAuth")
    public String relateSysRoleAuth(String roleCode, String[] authCode, Model model, RedirectAttributes attr) {
        
        if(StringUtils.isNotBlank(roleCode)) {
            //删除之前关联的数据
            this.sysRoleAuthRService.deleteSysRoleAuthRByRoleCode(roleCode);
            //循环插入角色和权限的关联数据
            if(authCode!=null) {
                for(String authCodeTemp: authCode) {
                    SysRoleAuthR sysRoleAuthR = new SysRoleAuthR();
                    sysRoleAuthR.setAuthCode(authCodeTemp);
                    sysRoleAuthR.setRoleCode(roleCode);
                    this.sysRoleAuthRService.insertDataObject(sysRoleAuthR);
                }
            }
        }
        
        //redirect传递数据
        attr.addAttribute("roleCode", roleCode);
        
        return "redirect:beforeRelateSysRoleAuth";
    }
}
