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
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffDepartmentR;
import com.erp.hr.dao.model.HrStaffDepartmentRCO;
import com.erp.hr.service.HrDepartmentService;
import com.erp.hr.service.HrPositionService;
import com.erp.hr.service.HrStaffDepartmentRService;
import com.erp.hr.service.HrStaffService;

@Controller
@RequestMapping("/web/hrStaffDepartmentR")
public class HrStaffDepartmentRWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrStaffDepartmentRWebController.class);
    
    //服务层注入
    @Autowired
    private HrStaffDepartmentRService hrStaffDepartmentRService;
    @Autowired
    private HrStaffService hrStaffService;
    @Autowired
    private HrPositionService hrPositionService;
    @Autowired
    private HrDepartmentService hrDepartmentService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:beforeRelateStaffDepartment";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-08 20:53:39
     * @author 
     * @param pages
     * @param hrStaffDepartmentRCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("beforeRelateStaffDepartment")
    public String beforeRelateStaffDepartment(Pages pages, HrStaffDepartmentRCO hrStaffDepartmentRCO, Model model) {
        return "basic.jsp?content=hrStaffDepartmentR/staffDepartmentRelate";
    }
    
    
    
    /**
     * 
     * @description 获取关联列表
     * @date 2020年7月10日
     * @author dongbin
     * @param pages
     * @param hrStaffDepartmentRCO
     * @param model
     * @return
     * @return String
     *
     */
    @RequestMapping("getRelateListAjax")
    public String getRelateListAjax(Pages pages, HrStaffDepartmentRCO hrStaffDepartmentRCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List relateList = this.hrStaffDepartmentRService.getHrStaffDepartmentRList(pages, hrStaffDepartmentRCO);
        
        //页面属性设置
        model.addAttribute("relateList", relateList);
        model.addAttribute("pages", pages);
        
        return "hrStaffDepartmentR/ajax/relateListAjax";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrStaffDepartmentR")
    public String getHrStaffDepartmentR(HrStaffDepartmentR hrStaffDepartmentR, int departmentId, Model model) {
        //查询要编辑的数据
        if(hrStaffDepartmentR!=null&&hrStaffDepartmentR.getSdId()!=null) {
            hrStaffDepartmentR = this.hrStaffDepartmentRService.getDataObject(hrStaffDepartmentR.getSdId());
        }

        //获取组织节点信息
        HrDepartment hrDepartment = this.hrDepartmentService.getDataObject(departmentId);
        
        //获取职员列表
        List<HrStaff> hrStaffList = this.hrStaffService.getDataObjects();
        
        //获取岗位列表
        List<HrPosition> hrPositionList = this.hrPositionService.getDataObjects();
        
        //页面属性设置
        model.addAttribute("hrStaffDepartmentR", hrStaffDepartmentR);
        model.addAttribute("hrStaffList", hrStaffList);
        model.addAttribute("hrPositionList", hrPositionList);
        model.addAttribute("hrDepartment", hrDepartment);
        
        return "hrStaffDepartmentR/pop/addStaffDepartmentRModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editHrStaffDepartmentR")
    public String editHrStaffDepartmentR(@Valid HrStaffDepartmentR hrStaffDepartmentR, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getHrStaffDepartmentR";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.hrStaffDepartmentRService.insertOrUpdateDataObject(hrStaffDepartmentR);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:beforeRelateStaffDepartment";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @return String
     *
     */
    @RequestMapping("deleteHrStaffDepartmentR")
    public String deleteHrStaffDepartmentR(HrStaffDepartmentR hrStaffDepartmentR, RedirectAttributes attr) {
        //删除数据前验证数据
        if(hrStaffDepartmentR!=null&&hrStaffDepartmentR.getSdId()!=null) {
            //删除数据
            this.hrStaffDepartmentRService.deleteDataObject(hrStaffDepartmentR);
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }
        
        return "redirect:beforeRelateStaffDepartment";
    }
}
