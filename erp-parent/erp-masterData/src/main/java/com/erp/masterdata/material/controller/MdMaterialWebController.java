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
package com.erp.masterdata.material.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import com.springboot.dao.data.GlobalDataBox;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.material.dao.data.DataBox;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.dao.model.MdMaterialCO;
import com.erp.masterdata.material.dao.model.MdMaterialCategory;
import com.erp.masterdata.material.service.MdMaterialCategoryService;
import com.erp.masterdata.material.service.MdMaterialService;

@Controller
@RequestMapping("/web/mdMaterial")
public class MdMaterialWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdMaterialWebController.class);
    
    //服务层注入
    @Autowired
    private MdMaterialService mdMaterialService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private MdMaterialCategoryService mdMaterialCategoryService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdMaterialList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-14 22:27:04
     * @author 
     * @param pages
     * @param mdMaterialCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterialList")
    public String getMdMaterialList(Pages pages, MdMaterialCO mdMaterialCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<MdMaterial> mdMaterialList = this.mdMaterialService.getDataObjects(pages, mdMaterialCO);
        for(MdMaterial mdMaterial: mdMaterialList) {
          //获取物料类型
            MdMaterialCategory mdMaterialCategory = this.mdMaterialCategoryService.getDataObject(mdMaterial.getCategoryCode());
            if(mdMaterialCategory!=null) {
                mdMaterial.setCategoryDesc(mdMaterialCategory.getSegmentDesc());
            }
        }
        
        //获取物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        //获取物料或事项选择
        Map materialTypeMap = DataBox.getMaterialTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdMaterialList", mdMaterialList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialUnitMap", materialUnitMap);
        model.addAttribute("materialTypeMap", materialTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdMaterial/mdMaterialList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-14 22:27:04
     * @author 
     * @param mdMaterial
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterial")
    public String getMdMaterial(MdMaterial mdMaterial, Model model) {
        //查询要编辑的数据
        if(mdMaterial!=null&&mdMaterial.getMaterialId()!=null) {
            mdMaterial = this.mdMaterialService.getDataObject(mdMaterial.getMaterialId());
            //获取物料类型
            MdMaterialCategory mdMaterialCategory = this.mdMaterialCategoryService.getDataObject(mdMaterial.getCategoryCode());
            if(mdMaterialCategory!=null) {
                mdMaterial.setCategoryDesc(mdMaterialCategory.getSegmentDesc());
            }
        }
        
        //获取物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        //获取物料或事项选择
        Map materialTypeMap = DataBox.getMaterialTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdMaterial", mdMaterial);
        model.addAttribute("materialUnitMap", materialUnitMap);
        model.addAttribute("materialTypeMap", materialTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdMaterial/mdMaterialEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-14 22:27:04
     * @author 
     * @param mdMaterial
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdMaterial")
    public String editMdMaterial(@Valid MdMaterial mdMaterial, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getMdMaterial";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.mdMaterialService.insertOrUpdateDataObject(mdMaterial);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getMdMaterialList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-14 22:27:04
     * @author 
     * @param mdMaterial
     * @return String
     *
     */
    @RequestMapping("deleteMdMaterial")
    public String deleteMdMaterial(MdMaterial mdMaterial, RedirectAttributes attr) {
        //删除数据前验证数据
        if(mdMaterial!=null&&mdMaterial.getMaterialId()!=null) {
            //删除数据
            this.mdMaterialService.deleteDataObject(mdMaterial);
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }
        
        return "redirect:getMdMaterialList";
    }
    
    
    
    /**
     * 
     * @description 更新审批状态
     * @date 2020年8月4日
     * @author dongbin
     * @param code
     * @param approveStatus
     * @param attr
     * @return
     * @return String
     *
     */
    @RequestMapping("updateApproveStatus")
    public String updateApproveStatus(Integer id, String approveStatus, RedirectAttributes attr) {
        
        if(id!=null&&StringUtils.isNotBlank(approveStatus)) {
            //更新审核状态
            this.mdMaterialService.updateApproveStatus(id, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("materialId", id);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("materialId", id);
        }
        
        return "redirect:getMdMaterial";
    }
}
