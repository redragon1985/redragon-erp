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
package com.erp.masterdata.project.controller;

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
import com.erp.masterdata.project.dao.model.MdProject;
import com.erp.masterdata.project.dao.model.MdProjectCO;
import com.erp.masterdata.project.service.MdProjectService;

@Controller
@RequestMapping("/web/mdProject")
public class MdProjectWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdProjectWebController.class);
    
    //服务层注入
    @Autowired
    private MdProjectService mdProjectService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdProjectList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-15 11:53:13
     * @author 
     * @param pages
     * @param mdProjectCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdProjectList")
    public String getMdProjectList(Pages pages, MdProjectCO mdProjectCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<MdProject> mdProjectList = this.mdProjectService.getDataObjects(pages, mdProjectCO);
        
        //项目类型
        Map projectTypeMap = this.datasetCommonService.getProjectType();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdProjectList", mdProjectList);
        model.addAttribute("pages", pages);
        model.addAttribute("projectTypeMap", projectTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdProject/mdProjectList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-15 11:53:13
     * @author 
     * @param mdProject
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdProject")
    public String getMdProject(MdProject mdProject, Model model) {
        //查询要编辑的数据
        if(mdProject!=null&&mdProject.getProjectId()!=null) {
            mdProject = this.mdProjectService.getDataObject(mdProject.getProjectId());
        }
        
        //项目类型
        Map projectTypeMap = this.datasetCommonService.getProjectType();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdProject", mdProject);
        model.addAttribute("projectTypeMap", projectTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdProject/mdProjectEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-15 11:53:13
     * @author 
     * @param mdProject
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdProject")
    public String editMdProject(@Valid MdProject mdProject, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getMdProject";
        }
        
        //对当前编辑的对象初始化默认的字段
        if(mdProject.getEndDate()!=null) {
            mdProject.setStatus("N");
        }
        
        //保存编辑的数据
        this.mdProjectService.insertOrUpdateDataObject(mdProject);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getMdProjectList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-15 11:53:13
     * @author 
     * @param mdProject
     * @return String
     *
     */
    @RequestMapping("deleteMdProject")
    public String deleteMdProject(MdProject mdProject, RedirectAttributes attr) {
        //删除数据前验证数据
        if(mdProject!=null&&mdProject.getProjectId()!=null) {
            //删除数据
            this.mdProjectService.deleteDataObject(mdProject);
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }
        
        return "redirect:getMdProjectList";
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
            this.mdProjectService.updateApproveStatus(id, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("projectId", id);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("projectId", id);
        }
        
        return "redirect:getMdProject";
    }
}
