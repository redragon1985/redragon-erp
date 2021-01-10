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

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

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
import com.erp.permission.dao.model.SysAuthCO;
import com.erp.permission.service.SysAuthService;

@Controller
@RequestMapping("/web/sysAuth")
public class SysAuthWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SysAuthWebController.class);
    
    //服务层注入
    @Autowired
    private SysAuthService sysAuthService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSysAuthList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-06-30 22:10:11
     * @author 
     * @param pages
     * @param sysAuthCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysAuthList")
    public String getSysAuthList(Pages pages, SysAuthCO sysAuthCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SysAuth> sysAuthList = this.sysAuthService.getDataObjects(pages, sysAuthCO);
        
        //页面属性设置
        model.addAttribute("sysAuthList", sysAuthList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=sysAuth/sysAuthList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-06-30 22:10:11
     * @author 
     * @param sysAuth
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysAuth")
    public String getSysAuth(SysAuth sysAuth, Model model) {
        //查询要编辑的数据
        if(sysAuth!=null&&sysAuth.getAuthId()!=null) {
            sysAuth = this.sysAuthService.getDataObject(sysAuth.getAuthId());
        }
        
        //页面属性设置
        model.addAttribute("sysAuth", sysAuth);
        
        return "basic.jsp?content=sysAuth/sysAuthEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-06-30 22:10:11
     * @author 
     * @param sysAuth
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSysAuth")
    public String editSysAuth(@Valid SysAuth sysAuth, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSysAuth";
        }
        
        try {
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.sysAuthService.insertOrUpdateDataObject(sysAuth);
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }catch(Exception e){
            if(e.getCause().getClass()==ConstraintViolationException.class) {
                //提示信息
                model.addAttribute("hint", "hint");
                model.addAttribute("alertMessage", "编码已存在请重新输入");
                return "forward:getSysAuth";
            }else {
                throw e;
            }
        }
        
        return "redirect:getSysAuthList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-06-30 22:10:11
     * @author 
     * @param sysAuth
     * @return String
     *
     */
    @RequestMapping("deleteSysAuth")
    public String deleteSysAuth(SysAuth sysAuth, RedirectAttributes attr) {
        //删除数据前验证数据
        if(sysAuth!=null&&sysAuth.getAuthId()!=null) {
            //判断是否存在关联数据
            if(this.sysAuthService.isExistRelateDataForSysAuth(sysAuth.getAuthCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "存在权限的关联数据，无法删除权限");
            }else {
                //删除数据
                this.sysAuthService.deleteDataObject(sysAuth);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }

        }
        
        return "redirect:getSysAuthList";
    }
}
