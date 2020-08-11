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
package com.erp.dataset.controller;

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
import com.erp.dataset.dao.model.SysDatasetType;
import com.erp.dataset.dao.model.SysDatasetTypeCO;
import com.erp.dataset.service.SysDatasetTypeService;

@Controller
@RequestMapping("/web/sysDatasetType")
public class SysDatasetTypeWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SysDatasetTypeWebController.class);
    
    //服务层注入
    @Autowired
    private SysDatasetTypeService sysDatasetTypeService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSysDatasetTypeList";
    }
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-02 23:06:48
     * @author 
     * @param pages
     * @param sysDatasetTypeCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysDatasetTypeList")
    public String getSysDatasetTypeList(Pages pages, SysDatasetTypeCO sysDatasetTypeCO, Model model) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SysDatasetType> sysDatasetTypeList = this.sysDatasetTypeService.getDataObjects(pages, sysDatasetTypeCO);
        
        //页面属性设置
        model.addAttribute("sysDatasetTypeList", sysDatasetTypeList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=datasetType/datasetTypeList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-02 23:06:48
     * @author 
     * @param sysDatasetType
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysDatasetType")
    public String getSysDatasetType(SysDatasetType sysDatasetType, Model model) {
        //查询要编辑的数据
        if(sysDatasetType!=null&&sysDatasetType.getDatasetTypeId()!=null) {
            sysDatasetType = this.sysDatasetTypeService.getDataObject(sysDatasetType.getDatasetTypeId());
        }
        
        //页面属性设置
        model.addAttribute("sysDatasetType", sysDatasetType);
        
        return "basic.jsp?content=datasetType/datasetTypeEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-02 23:06:48
     * @author 
     * @param sysDatasetType
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSysDatasetType")
    public String editSysDatasetType(@Valid SysDatasetType sysDatasetType, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSysDatasetType";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.sysDatasetTypeService.insertOrUpdateDataObject(sysDatasetType);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getSysDatasetTypeList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-02 23:06:48
     * @author 
     * @param sysDatasetType
     * @return String
     *
     */
    @RequestMapping("deleteSysDatasetType")
    public String deleteSysDatasetType(SysDatasetType sysDatasetType, RedirectAttributes attr) {
        //删除数据前验证数据
        if(sysDatasetType!=null&&sysDatasetType.getDatasetTypeId()!=null) {
            //删除数据前验证数据
            if(this.sysDatasetTypeService.isExistRelateDataForSysDatasetType(sysDatasetType.getDatasetTypeCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "当前值集类型存在关联数据，无法删除");
            }else {
                //删除数据
                this.sysDatasetTypeService.deleteDataObject(sysDatasetType);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }

        }
        
        return "redirect:getSysDatasetTypeList";
    }
}
