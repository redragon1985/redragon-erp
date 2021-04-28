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
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrPositionCO;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffCO;
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
        HrStaffCO hrStaffCO = new HrStaffCO();
        hrStaffCO.setStaffStatus("WORK");
        List<HrStaff> hrStaffList = this.hrStaffService.getDataObjects(hrStaffCO);
        
        //获取岗位列表
        HrPositionCO hrPositionCO = new HrPositionCO();
        hrPositionCO.setStatus("Y");
        List<HrPosition> hrPositionList = this.hrPositionService.getDataObjects(hrPositionCO);
        
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
