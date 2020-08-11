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
package com.erp.hr.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
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
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffCO;
import com.erp.hr.service.HrStaffService;
import com.erp.permission.dao.model.SysUser;
import com.erp.permission.service.SysUserService;

@Controller
@RequestMapping("/web/hrStaff")
public class HrStaffWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrStaffWebController.class);
    
    //服务层注入
    @Autowired
    private HrStaffService hrStaffService;
    @Autowired
    private SysUserService sysUserService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getHrStaffList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-04 19:35:02
     * @author 
     * @param pages
     * @param hrStaffCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrStaffList")
    public String getHrStaffList(Pages pages, HrStaffCO hrStaffCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<HrStaff> hrStaffList = this.hrStaffService.getDataObjects(pages, hrStaffCO);
        
        //页面属性设置
        model.addAttribute("hrStaffList", hrStaffList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=hrStaff/hrStaffList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-04 19:35:02
     * @author 
     * @param hrStaff
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrStaff")
    public String getHrStaff(HrStaff hrStaff, Model model) {
        //查询要编辑的数据
        if(hrStaff!=null&&hrStaff.getStaffId()!=null) {
            hrStaff = this.hrStaffService.getDataObject(hrStaff.getStaffId());
        }
        
        //获取username列表
        List<SysUser> sysUserList = this.sysUserService.getDataObjects();
        
        //页面属性设置
        model.addAttribute("hrStaff", hrStaff);
        model.addAttribute("sysUserList", sysUserList);
        
        return "basic.jsp?content=hrStaff/hrStaffEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-04 19:35:02
     * @author 
     * @param hrStaff
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editHrStaff")
    public String editHrStaff(@Valid HrStaff hrStaff, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getHrStaff";
        }
        
        //对当前编辑的对象初始化默认的字段
        hrStaff.setStaffNumber(hrStaff.getStaffCode());
        
        //保存编辑的数据
        this.hrStaffService.insertOrUpdateDataObject(hrStaff);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getHrStaffList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-04 19:35:02
     * @author 
     * @param hrStaff
     * @return String
     *
     */
    @RequestMapping("deleteHrStaff")
    public String deleteHrStaff(HrStaff hrStaff, RedirectAttributes attr) {
        //删除数据前验证数据
        if(hrStaff!=null&&hrStaff.getStaffId()!=null) {
            //删除数据前验证数据
            if(this.hrStaffService.isExistRelateDataForHrStaff(hrStaff.getStaffCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "存在职员的关联数据，无法删除职员");
            }else {
                //删除数据
                this.hrStaffService.deleteDataObject(hrStaff);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
            
        }
        
        return "redirect:getHrStaffList";
    }
}
