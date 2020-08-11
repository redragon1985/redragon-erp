/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.erp.permission.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.framework.util.ShiroUtil;

import redragon.util.code.MD5Util;

import com.erp.permission.dao.model.SysRole;
import com.erp.permission.dao.model.SysUser;
import com.erp.permission.dao.model.SysUserCO;
import com.erp.permission.dao.model.SysUserRO;
import com.erp.permission.dao.model.SysUserRoleR;
import com.erp.permission.service.SysRoleService;
import com.erp.permission.service.SysUserRoleRService;
import com.erp.permission.service.SysUserService;

@Controller
@RequestMapping("/web/sysUser")
public class SysUserWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SysUserWebController.class);
    
    //服务层注入
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleRService sysUserRoleRService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSysUserList";
    }
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-06-27 20:46:27
     * @author 
     * @param pages
     * @param sysUserCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysUserList")
    public String getSysUserList(Pages pages, SysUserCO sysUserCO, Model model) {
        
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SysUser> sysUserList = this.sysUserService.getDataObjects(pages, sysUserCO);
        
        //页面属性设置
        model.addAttribute("sysUserList", sysUserList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=sysUser/sysUserList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-06-27 20:46:27
     * @author 
     * @param sysUser
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysUser")
    public String getSysUser(SysUser sysUser, Model model) {
        //查询要编辑的数据
        if(sysUser!=null&&sysUser.getUserId()!=null) {
            sysUser = this.sysUserService.getDataObject(sysUser.getUserId());
        }
        
        //页面属性设置
        model.addAttribute("sysUser", sysUser);
        
        return "basic.jsp?content=sysUser/sysUserEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-06-27 20:46:27
     * @author 
     * @param sysUser
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSysUser")
    public String editSysUser(@Valid SysUser sysUser, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSysUser";
        }
        
        //对当前编辑的对象初始化默认的字段
        //密码加密
        if(StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(MD5Util.encodeSHA(sysUser.getPassword()));
        }else {
            sysUser.setPassword(null);
        }
        
        //保存编辑的数据
        this.sysUserService.insertOrUpdateDataObject(sysUser);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getSysUserList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-06-27 20:46:27
     * @author 
     * @param sysUser
     * @return String
     *
     */
    @RequestMapping("deleteSysUser")
    public String deleteSysUser(SysUser sysUser, RedirectAttributes attr) {
        //删除数据前验证数据
        if(sysUser!=null&&sysUser.getUserId()!=null) {
            //删除数据前验证数据
            if(this.sysUserService.isExistRelateDataForSysUser(sysUser.getUsername())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "存在用户的关联数据，无法删除用户");
            }else {
                //删除数据
                this.sysUserService.deleteDataObject(sysUser);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
            
        }
        
        return "redirect:getSysUserList";
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
    @RequestMapping("beforeRelateSysUserRole")
    public String beforeRelateSysUserRole(String username, Model model) {
        
        //获取要关联的用户
        List<SysUserRO> sysUserROList = this.sysUserService.getSysUserROListForRelate();
        //获取要关联的角色
        List<SysRole> sysRoleList = this.sysRoleService.getSysRoleListByStatus("Y");
        if(StringUtils.isNotBlank(username)) {
            //获取用户已关联的角色
            List<SysRole> sysRoleRelateList = this.sysRoleService.getRelateSysRoleListByUsername(username);
            model.addAttribute("sysRoleRelateList", sysRoleRelateList);
            
            //根据已关联的角色得到未关联的角色
            if(sysRoleList.size()>0&&sysRoleRelateList.size()>0) {
                Iterator<SysRole> sysRoleListIterator = sysRoleList.iterator();
                while(sysRoleListIterator.hasNext()) {
                    SysRole sysRoleTemp = sysRoleListIterator.next();
                    for(SysRole sysRoleR:sysRoleRelateList) {
                        if(sysRoleTemp.getRoleCode().equals(sysRoleR.getRoleCode())) {
                            sysRoleListIterator.remove();
                            break;
                        }
                    }
                }
            }
        }
        
        //页面属性设置
        model.addAttribute("sysUserROList", sysUserROList);
        model.addAttribute("sysRoleList", sysRoleList);
        
        return "basic.jsp?content=sysUser/userRoleRelate";
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
    @RequestMapping("getRoleListRelateAjax")
    public String getRoleListRelateAjax(String username, Model model) {
        
        if(StringUtils.isNotBlank(username)) {
            //获取要关联的角色
            List<SysRole> sysRoleList = this.sysRoleService.getSysRoleListByStatus("Y");
            //获取用户已关联的角色
            List<SysRole> sysRoleRelateList = this.sysRoleService.getRelateSysRoleListByUsername(username);
            
            //根据已关联的角色得到未关联的角色
            if(sysRoleList.size()>0&&sysRoleRelateList.size()>0) {
                Iterator<SysRole> sysRoleListIterator = sysRoleList.iterator();
                while(sysRoleListIterator.hasNext()) {
                    SysRole sysRoleTemp = sysRoleListIterator.next();
                    for(SysRole sysRoleR:sysRoleRelateList) {
                        if(sysRoleTemp.getRoleCode().equals(sysRoleR.getRoleCode())) {
                            sysRoleListIterator.remove();
                            break;
                        }
                    }
                }
            }            
            
            //页面属性设置
            model.addAttribute("sysRoleList", sysRoleList);
            model.addAttribute("sysRoleRelateList", sysRoleRelateList);
        }
        
        return "sysUser/ajax/roleListRelateAjax";
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
    @RequestMapping("relateSysUserRole")
    public String relateSysUserRole(String username, String[] roleCode, Model model, RedirectAttributes attr) {
        
        if(StringUtils.isNotBlank(username)) {
            //删除之前关联的数据
            this.sysUserRoleRService.deleteSysUserRoleRByUsername(username);
            //循环插入用户和角色的关联数据
            if(roleCode!=null) {
                for(String roleCodeTemp: roleCode) {
                    SysUserRoleR sysUserRoleR = new SysUserRoleR();
                    sysUserRoleR.setUsername(username);
                    sysUserRoleR.setRoleCode(roleCodeTemp);
                    this.sysUserRoleRService.insertDataObject(sysUserRoleR);
                }
            }
        }
        
        //redirect传递数据
        attr.addAttribute("username", username);
        
        return "redirect:beforeRelateSysUserRole";
    }
}
