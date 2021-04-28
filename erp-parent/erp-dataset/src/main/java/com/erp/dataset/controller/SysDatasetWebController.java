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
import com.erp.dataset.dao.model.SysDataset;
import com.erp.dataset.dao.model.SysDatasetCO;
import com.erp.dataset.service.SysDatasetService;

@Controller
@RequestMapping("/web/sysDataset")
public class SysDatasetWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SysDatasetWebController.class);
    
    //服务层注入
    @Autowired
    private SysDatasetService sysDatasetService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSysDatasetList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-02 23:06:11
     * @author 
     * @param pages
     * @param sysDatasetCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysDatasetList")
    public String getSysDatasetList(Pages pages, SysDatasetCO sysDatasetCO, Model model) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SysDataset> sysDatasetList = this.sysDatasetService.getDataObjects(pages, sysDatasetCO);
        
        //页面属性设置
        model.addAttribute("sysDatasetList", sysDatasetList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=dataset/datasetList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-02 23:06:11
     * @author 
     * @param sysDataset
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSysDataset")
    public String getSysDataset(SysDataset sysDataset, Model model) {
        //查询要编辑的数据
        if(sysDataset!=null&&sysDataset.getDatasetId()!=null) {
            sysDataset = this.sysDatasetService.getDataObject(sysDataset.getDatasetId());
        }
        
        //页面属性设置
        model.addAttribute("sysDataset", sysDataset);
        
        return "dataset/pop/addDatasetModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-02 23:06:11
     * @author 
     * @param sysDataset
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSysDataset")
    public String editSysDataset(@Valid SysDataset sysDataset, BindingResult bindingResult, String datasetTypeName, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", errorMap.get("alertMessage"));
            //添加重定向参数
            attr.addAttribute("datasetTypeCode", sysDataset.getDatasetTypeCode());
            attr.addAttribute("datasetTypeName", datasetTypeName);
            return "redirect:getSysDatasetList";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.sysDatasetService.insertOrUpdateDataObject(sysDataset);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        //添加重定向参数
        attr.addAttribute("datasetTypeCode", sysDataset.getDatasetTypeCode());
        attr.addAttribute("datasetTypeName", datasetTypeName);
        
        return "redirect:getSysDatasetList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-02 23:06:11
     * @author 
     * @param sysDataset
     * @return String
     *
     */
    @RequestMapping("deleteSysDataset")
    public String deleteSysDataset(SysDataset sysDataset, String datasetTypeName, RedirectAttributes attr) {
        //删除数据前验证数据
        if(sysDataset!=null&&sysDataset.getDatasetId()!=null) {
            //删除数据
            this.sysDatasetService.deleteDataObject(sysDataset);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            //设置重定向参数
            attr.addAttribute("datasetTypeName", datasetTypeName);
            attr.addAttribute("datasetTypeCode", sysDataset.getDatasetTypeCode());
        }
        
        return "redirect:getSysDatasetList";
    }
}
