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
import com.erp.hr.dao.data.DataBox;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrPositionCO;
import com.erp.hr.service.HrPositionService;

@Controller
@RequestMapping("/web/hrPosition")
public class HrPositionWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrPositionWebController.class);
    
    //服务层注入
    @Autowired
    private HrPositionService hrPositionService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getHrPositionList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-04 18:25:32
     * @author 
     * @param pages
     * @param hrPositionCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrPositionList")
    public String getHrPositionList(Pages pages, HrPositionCO hrPositionCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<HrPosition> hrPositionList = this.hrPositionService.getDataObjects(pages, hrPositionCO);
        
        //获取职位类型
        Map<String, String> positionTypeMap = DataBox.getPositionTypeMap();
        
        //页面属性设置
        model.addAttribute("hrPositionList", hrPositionList);
        model.addAttribute("pages", pages);
        model.addAttribute("positionTypeMap", positionTypeMap);
        
        return "basic.jsp?content=hrPosition/hrPositionList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-04 18:25:32
     * @author 
     * @param hrPosition
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrPosition")
    public String getHrPosition(HrPosition hrPosition, Model model) {
        //查询要编辑的数据
        if(hrPosition!=null&&hrPosition.getPositionId()!=null) {
            hrPosition = this.hrPositionService.getDataObject(hrPosition.getPositionId());
        }
        
        //获取职位类型
        Map<String, String> positionTypeMap = DataBox.getPositionTypeMap();
        
        //页面属性设置
        model.addAttribute("hrPosition", hrPosition);
        model.addAttribute("positionTypeMap", positionTypeMap);
        
        return "basic.jsp?content=hrPosition/hrPositionEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-04 18:25:32
     * @author 
     * @param hrPosition
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editHrPosition")
    public String editHrPosition(@Valid HrPosition hrPosition, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getHrPosition";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.hrPositionService.insertOrUpdateDataObject(hrPosition);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getHrPositionList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-04 18:25:32
     * @author 
     * @param hrPosition
     * @return String
     *
     */
    @RequestMapping("deleteHrPosition")
    public String deleteHrPosition(HrPosition hrPosition, RedirectAttributes attr) {
        //删除数据前验证数据
        if(hrPosition!=null&&hrPosition.getPositionId()!=null) {
            //删除数据前验证数据
            if(this.hrPositionService.isExistRelateDataForHrPosition(hrPosition.getPositionCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "存在岗位的关联数据，无法删除岗位");
            }else {
                //删除数据
                this.hrPositionService.deleteDataObject(hrPosition);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }

        }
        
        return "redirect:getHrPositionList";
    }
}
