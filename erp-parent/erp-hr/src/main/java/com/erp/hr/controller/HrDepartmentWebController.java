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
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.service.HrDepartmentService;
import redragon.util.string.JsonUtil;

@Controller
@RequestMapping("/web/hrDepartment")
public class HrDepartmentWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrDepartmentWebController.class);
    
    //服务层注入
    @Autowired
    private HrDepartmentService hrDepartmentService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getHrDepartmentList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-05 12:28:05
     * @author 
     * @param pages
     * @param hrDepartmentCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrDepartmentList")
    public String getHrDepartmentList(Pages pages, HrDepartmentCO hrDepartmentCO, Model model) {
        return "basic.jsp?content=hrDepartment/hrDepartmentList";
    }
    
    /**
     * 
     * @description 获取组织树
     * @date 2020年7月5日
     * @author dongbin
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrDepartmentTree")
    public String getHrDepartmentTree(Model model) {
        
        model.addAttribute("treeType", "write");
        model.addAttribute("title", "组织架构");
        model.addAttribute("editNodeModalUrl", "web/hrDepartment/getHrDepartmentEditModal");
        model.addAttribute("getTreeDataUrl", "web/hrDepartment/getHrDepartmentTreeData");
        model.addAttribute("deleteNodeUrl", "web/hrDepartment/deleteHrDepartment?departmentId=");
        
        return "common/tree/tree";
    }
    
    @RequestMapping("getHrDepartmentTreeReadOnly")
    public String getHrDepartmentTreeReadOnly(Model model) {
        
        model.addAttribute("treeType", "read");
        model.addAttribute("title", "组织架构");
        model.addAttribute("editNodeModalUrl", "web/hrDepartment/getHrDepartmentEditModal");
        model.addAttribute("getTreeDataUrl", "web/hrDepartment/getHrDepartmentTreeData");
        model.addAttribute("deleteNodeUrl", "web/hrDepartment/deleteHrDepartment?departmentId=");
        
        return "common/tree/tree";
    }
    
    @RequestMapping(value="getHrDepartmentTreeData", produces = "application/json")
    @ResponseBody
    public String getHrDepartmentTreeData(String nodeId) {
        //获取组织列表
        List<HrDepartment> hrDepartmentList = this.hrDepartmentService.getDataObjects(new HrDepartmentCO());
        //根节点
        TreeNode rootNode = new TreeNode();
        //节点Map
        Map<String, TreeNode> treeNodeMap = new TreeMap<String, TreeNode>();
        
        //重组组织树
        for(int a=0;a<hrDepartmentList.size();a++) {
            HrDepartment hrDepartment = hrDepartmentList.get(a);
            
            if(a==0) {
                //设置根节点
                rootNode.setId(hrDepartment.getDepartmentId().toString());
                rootNode.setCode(hrDepartment.getDepartmentCode());
                rootNode.setText(hrDepartment.getDepartmentName()+(hrDepartment.getStatus().equals("N")?"(停用)":""));
                rootNode.setType("root");
                Map<String, Boolean> stateMap = new HashMap<String, Boolean>();
                stateMap.put("opened", true);
                rootNode.setState(stateMap);
                rootNode.setChildren(this.getAllChildrenNode(hrDepartment, hrDepartmentList, treeNodeMap));
                //设置节点Map
                treeNodeMap.put(hrDepartment.getDepartmentCode(), rootNode);
            }else {
                //设置其他节点
                if(treeNodeMap.containsKey(hrDepartment.getDepartmentCode())) {
                    TreeNode node = treeNodeMap.get(hrDepartment.getDepartmentCode());
                    node.setChildren(this.getAllChildrenNode(hrDepartment, hrDepartmentList, treeNodeMap));
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(hrDepartment.getDepartmentId().toString());
                    node.setCode(hrDepartment.getDepartmentCode());
                    node.setText(hrDepartment.getDepartmentName()+(hrDepartment.getStatus().equals("N")?"(停用)":""));
                    node.setType("node");
                    node.setChildren(this.getAllChildrenNode(hrDepartment, hrDepartmentList, treeNodeMap));
                    //设置节点Map
                    treeNodeMap.put(hrDepartment.getDepartmentCode(), node);
                }
            }
        }
        
        return JsonUtil.objectToJson(rootNode);
    }
    
    //返回所有直接子节点
    private List<TreeNode> getAllChildrenNode(HrDepartment currentHrDepartment, List<HrDepartment> hrDepartmentList, Map<String, TreeNode> treeNodeMap) {
        List<TreeNode> childrenNodeList = new ArrayList<TreeNode>();
        //获取父节点段值数量
        int segmentNum = currentHrDepartment.getSegmentCode().split("_").length;
        
        for(HrDepartment hrDepartment: hrDepartmentList) {
            //获取节点段值数量
            int segmentNumTemp = hrDepartment.getSegmentCode().split("_").length;
            //判断父节点的直接子节点
            if(segmentNumTemp==segmentNum+1&&hrDepartment.getSegmentCode().contains(currentHrDepartment.getSegmentCode())) {
                if(treeNodeMap.containsKey(hrDepartment.getDepartmentCode())) {
                    TreeNode node = treeNodeMap.get(hrDepartment.getDepartmentCode());
                    childrenNodeList.add(node);
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(hrDepartment.getDepartmentId().toString());
                    node.setCode(hrDepartment.getDepartmentCode());
                    node.setText(hrDepartment.getDepartmentName()+(hrDepartment.getStatus().equals("N")?"(停用)":""));
                    node.setType("department");
                    childrenNodeList.add(node);
                    //设置节点Map
                    treeNodeMap.put(hrDepartment.getDepartmentCode(), node);
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
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrDepartmentEditModal")
    public String getHrDepartmentEditModal(String nodeId, Model model) {
        
        //修改时获取当前节点
        if(StringUtils.isNotBlank(nodeId)) {
            HrDepartment hrParentDepartment = this.hrDepartmentService.getDataObject(Integer.parseInt(nodeId));
            model.addAttribute("nodeCodeValue", hrParentDepartment.getDepartmentCode());
            model.addAttribute("nodeNameValue", hrParentDepartment.getDepartmentName());
            model.addAttribute("nodeStatusValue", hrParentDepartment.getStatus());
            model.addAttribute("nodeCreatedDateValue", hrParentDepartment.getCreatedDate());
            model.addAttribute("nodeCreatedByValue", hrParentDepartment.getCreatedBy());
        }
       
        
        model.addAttribute("nodeId", "departmentId");
        model.addAttribute("nodeCode", "departmentCode");
        model.addAttribute("nodeName", "departmentName");
        model.addAttribute("editNodeUrl", "web/hrDepartment/editHrDepartment");
        
        return "common/tree/editNodeModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param hrDepartment
     * @param model
     * @return String
     *
     */
    @RequestMapping("getHrDepartment")
    public String getHrDepartment(HrDepartment hrDepartment, Model model) {
        //TODO: 查询要编辑的数据
        
        //页面属性设置
        model.addAttribute("hrDepartment", hrDepartment);
        
        return "hrDepartment/hrDepartmentEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param hrDepartment
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editHrDepartment")
    @ResponseBody
    public String editHrDepartment(@Valid @RequestBody HrDepartment hrDepartment,@RequestParam String parentId, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        
        try {
            //参数校验
            /*
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getHrDepartmentEditModal";
            }*/
            
            //判断是新增还是修改
            String editStatus = null;
            if(hrDepartment.getDepartmentId()!=null) {
                editStatus = "update";
            }else {
                editStatus = "insert";
            }
            
            //对当前编辑的对象初始化默认的字段
            //根节点
            if(StringUtils.isBlank(parentId)) {
                editStatus = "addRoot";
                hrDepartment.setSegmentCode(hrDepartment.getDepartmentCode());
                hrDepartment.setSegmentDesc(hrDepartment.getDepartmentName());
            }else {
                //获取父节点
                HrDepartment hrParentDepartment = this.hrDepartmentService.getDataObject(Integer.parseInt(parentId));
                hrDepartment.setParentDepartmentCode(hrParentDepartment.getDepartmentCode());
                hrDepartment.setSegmentCode(hrParentDepartment.getSegmentCode()+"_"+hrDepartment.getDepartmentCode());
                hrDepartment.setSegmentDesc(hrParentDepartment.getSegmentDesc()+"_"+hrDepartment.getDepartmentName());
            }
            
            //保存编辑的数据
            this.hrDepartmentService.insertOrUpdateDataObject(hrDepartment);
            
            //停用设置
            if(hrDepartment.getStatus().equals("N")) {
                hrDepartment.setDepartmentName(hrDepartment.getDepartmentName()+"(停用)");
            }
            
            return "{\"result\":\"success\",\"editStatus\":\""+editStatus+"\",\"nodeId\":\""+hrDepartment.getDepartmentId()+"\",\"nodeText\":\""+hrDepartment.getDepartmentName()+"\"}";
        }catch(Exception e) {
            
        }
        
        return "{\"result\":\"error\"}";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param hrDepartment
     * @return String
     *
     */
    @RequestMapping("deleteHrDepartment")
    @ResponseBody
    public String deleteHrDepartment(HrDepartment hrDepartment, RedirectAttributes attr) {
        //删除数据前验证数据
        if(hrDepartment!=null&&hrDepartment.getDepartmentId()!=null) {
            int childNum = this.hrDepartmentService.getHrChildDepartmentNum(hrDepartment.getDepartmentId());
            if(childNum==0) {
                //删除数据
                this.hrDepartmentService.deleteDataObject(hrDepartment);
                
                return "{\"result\":\"success\"}";
            }else {
                return "{\"result\":\"hint\",\"hintMessage\":\"当前节点存在子节点，不能删除\"}";
            }
            
        }
        
        return "{\"result\":\"error\"}";
    }
}
