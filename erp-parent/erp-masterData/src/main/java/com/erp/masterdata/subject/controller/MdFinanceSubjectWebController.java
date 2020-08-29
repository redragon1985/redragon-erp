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
package com.erp.masterdata.subject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.dao.model.TreeNode;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.subject.dao.model.MdFinanceSubjectCO;
import com.erp.masterdata.subject.service.MdFinanceSubjectService;

@Controller
@RequestMapping("/web/mdFinanceSubject")
public class MdFinanceSubjectWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdFinanceSubjectWebController.class);
    
    //服务层注入
    @Autowired
    private MdFinanceSubjectService mdFinanceSubjectService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdFinanceSubjectList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-27 16:22:54
     * @author 
     * @param pages
     * @param mdFinanceSubjectCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdFinanceSubjectList")
    public String getMdFinanceSubjectList(Pages pages, MdFinanceSubjectCO mdFinanceSubjectCO, Model model) {
        return "basic.jsp?content=mdFinanceSubject/subjectList";
    }
    
    
    /**
     * 
     * @description 获取科目树
     * @date 2020年7月5日
     * @author dongbin
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdFinanceSubjectTree")
    public String getMdFinanceSubjectTree(Model model) {
        
        model.addAttribute("treeType", "write");
        model.addAttribute("title", "科目结构");
        model.addAttribute("editNodeModalUrl", "web/mdFinanceSubject/getMdFinanceSubjectEditModal");
        model.addAttribute("getTreeDataUrl", "web/mdFinanceSubject/getMdFinanceSubjectTreeData");
        model.addAttribute("deleteNodeUrl", "web/mdFinanceSubject/deleteMdFinanceSubject?subjectId=");
        
        return "common/tree/tree";
    }
    
    @RequestMapping("getMdFinanceSubjectTreeReadOnly")
    public String getMdFinanceSubjectTreeReadOnly(Model model) {
        
        model.addAttribute("treeType", "read");
        model.addAttribute("title", "科目结构");
        model.addAttribute("editNodeModalUrl", "web/mdFinanceSubject/getMdFinanceSubjectEditModal");
        model.addAttribute("getTreeDataUrl", "web/mdFinanceSubject/getMdFinanceSubjectTreeData");
        model.addAttribute("deleteNodeUrl", "web/mdFinanceSubject/deleteMdFinanceSubject?subjectId=");
        
        return "common/tree/tree";
    }
    
    
    
    @RequestMapping(value="getMdFinanceSubjectTreeData", produces = "application/json")
    @ResponseBody
    public String getMdFinanceSubjectTreeData(String nodeId) {
        //获取科目列表
        List<MdFinanceSubject> list = this.mdFinanceSubjectService.getDataObjects(new MdFinanceSubjectCO());
        //根节点
        TreeNode rootNode = new TreeNode();
        //节点Map
        Map<String, TreeNode> treeNodeMap = new TreeMap<String, TreeNode>();
        
        //重组组织树
        for(int a=0;a<list.size();a++) {
            MdFinanceSubject obj = list.get(a);
            
            if(a==0) {
                //设置根节点
                rootNode.setId(obj.getSubjectId().toString());
                rootNode.setCode(obj.getSubjectCode());
                rootNode.setText(obj.getSubjectName()+(obj.getStatus().equals("N")?"(停用)":""));
                rootNode.setType("root");
                Map<String, Boolean> stateMap = new HashMap<String, Boolean>();
                stateMap.put("opened", true);
                rootNode.setState(stateMap);
                rootNode.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                //设置节点Map
                treeNodeMap.put(obj.getSubjectCode(), rootNode);
            }else {
                //设置其他节点
                if(treeNodeMap.containsKey(obj.getSubjectCode())) {
                    TreeNode node = treeNodeMap.get(obj.getSubjectCode());
                    node.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(obj.getSubjectId().toString());
                    node.setCode(obj.getSubjectCode());
                    node.setText(obj.getSubjectCode()+" "+obj.getSubjectName()+(obj.getStatus().equals("N")?"(停用)":""));
                    node.setType("node");
                    node.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                    //设置节点Map
                    treeNodeMap.put(obj.getSubjectCode(), node);
                }
            }
        }
        
        return JsonUtil.objectToJson(rootNode);
    }
    
    //返回所有直接子节点
    private List<TreeNode> getAllChildrenNode(MdFinanceSubject currentObj, List<MdFinanceSubject> list, Map<String, TreeNode> treeNodeMap) {
        List<TreeNode> childrenNodeList = new ArrayList<TreeNode>();
        //获取父节点段值数量
        int segmentNum = currentObj.getSegmentCode().split("_").length;
        
        for(MdFinanceSubject obj: list) {
            //获取节点段值数量
            int segmentNumTemp = obj.getSegmentCode().split("_").length;
            //判断父节点的直接子节点
            if(segmentNumTemp==segmentNum+1&&obj.getSegmentCode().contains(currentObj.getSegmentCode())) {
                if(treeNodeMap.containsKey(obj.getSubjectCode())) {
                    TreeNode node = treeNodeMap.get(obj.getSubjectCode());
                    childrenNodeList.add(node);
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(obj.getSubjectId().toString());
                    node.setCode(obj.getSubjectCode());
                    node.setText(obj.getSubjectCode()+" "+obj.getSubjectName()+(obj.getStatus().equals("N")?"(停用)":""));
                    node.setType("node");
                    childrenNodeList.add(node);
                    //设置节点Map
                    treeNodeMap.put(obj.getSubjectCode(), node);
                }
            }
        }
        
        return childrenNodeList;
    }
    
    
    
    /**
     * 
     * @description 获取组织编辑模态对话框
     * @date 2020年7月5日
     * @author dongbin
     * @param nodeCode
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdFinanceSubjectEditModal")
    public String getMdFinanceSubjectEditModal(String nodeId, Model model) {
        
        //修改时获取当前节点
        if(StringUtils.isNotBlank(nodeId)) {
            MdFinanceSubject parentNode = this.mdFinanceSubjectService.getDataObject(Integer.parseInt(nodeId));
            model.addAttribute("nodeCodeValue", parentNode.getSubjectCode());
            model.addAttribute("nodeNameValue", parentNode.getSubjectName());
            model.addAttribute("nodeStatusValue", parentNode.getStatus());
            model.addAttribute("nodeCreatedDateValue", parentNode.getCreatedDate());
            model.addAttribute("nodeCreatedByValue", parentNode.getCreatedBy());
        }
       
        
        model.addAttribute("nodeId", "subjectId");
        model.addAttribute("nodeCode", "subjectCode");
        model.addAttribute("nodeName", "subjectName");
        model.addAttribute("editNodeUrl", "web/mdFinanceSubject/editMdFinanceSubject");
        
        return "common/tree/editNodeModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-27 16:22:54
     * @author 
     * @param mdFinanceSubject
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdFinanceSubject")
    @ResponseBody
    public String getMdFinanceSubject(MdFinanceSubject mdFinanceSubject, Model model) {
        String subjectCode = "";
        String subjectName = "";
        String segmentCode = "";
        String segmentDesc = "";
        
        //查询要编辑的数据
        if(mdFinanceSubject!=null&&mdFinanceSubject.getSubjectId()!=null) {
            mdFinanceSubject = this.mdFinanceSubjectService.getDataObject(mdFinanceSubject.getSubjectId());
            subjectCode = mdFinanceSubject.getSubjectCode();
            subjectName = mdFinanceSubject.getSubjectName();
            segmentCode = mdFinanceSubject.getSegmentCode();
            segmentDesc = mdFinanceSubject.getSegmentDesc();
        }
        
        return "{\"subjectCode\":\""+subjectCode+"\",\"subjectName\":\""+subjectName+"\",\"segmentCode\":\""+segmentCode+"\",\"segmentDesc\":\""+segmentDesc+"\"}";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param MdFinanceSubject
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdFinanceSubject")
    @ResponseBody
    public String editMdFinanceSubject(@Valid @RequestBody MdFinanceSubject obj,@RequestParam String parentId, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        
        try {
            //参数校验
            /*
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getHrDepartmentEditModal";
            }*/
            
            //判断是新增还是修改
            String editStatus = null;
            if(obj.getSubjectId()!=null) {
                editStatus = "update";
            }else {
                editStatus = "insert";
            }
            
            //对当前编辑的对象初始化默认的字段
            //根节点
            if(StringUtils.isBlank(parentId)) {
                editStatus = "addRoot";
                obj.setSegmentCode(obj.getSubjectCode());
                obj.setSegmentDesc(obj.getSubjectName());
            }else {
                //获取父节点
                MdFinanceSubject parentNode = this.mdFinanceSubjectService.getDataObject(Integer.parseInt(parentId));
                obj.setParentSubjectCode(parentNode.getSubjectCode());
                obj.setSegmentCode(parentNode.getSegmentCode()+"_"+obj.getSubjectCode());
                obj.setSegmentDesc(parentNode.getSegmentDesc()+"_"+obj.getSubjectName());
            }
            
            //保存编辑的数据
            this.mdFinanceSubjectService.insertOrUpdateDataObject(obj);
            
            //停用设置
            if(obj.getStatus().equals("N")) {
                obj.setSubjectName(obj.getSubjectName()+"(停用)");
            }
            
            return "{\"result\":\"success\",\"editStatus\":\""+editStatus+"\",\"nodeId\":\""+obj.getSubjectId()+"\",\"nodeText\":\""+obj.getSubjectName()+"\"}";
        }catch(Exception e) {
            
        }
        
        return "{\"result\":\"error\"}";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-27 16:22:54
     * @author 
     * @param mdFinanceSubject
     * @return String
     *
     */
    @RequestMapping("deleteMdFinanceSubject")
    @ResponseBody
    public String deleteMdFinanceSubject(MdFinanceSubject obj, RedirectAttributes attr) {
        //删除数据前验证数据
        if(obj!=null&&obj.getSubjectId()!=null) {
            int childNum = this.mdFinanceSubjectService.getChildSubjectNum(obj.getSubjectId());
            if(childNum==0) {
                //删除数据
                this.mdFinanceSubjectService.deleteDataObject(obj);
                
                return "{\"result\":\"success\"}";
            }else {
                return "{\"result\":\"hint\",\"hintMessage\":\"当前节点存在子节点，不能删除\"}";
            }
            
        }
        
        return "{\"result\":\"error\"}";
    }
}
