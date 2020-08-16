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

import java.util.ArrayList;
import java.util.HashMap;
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
import com.erp.masterdata.material.dao.model.MdMaterialCategory;
import com.erp.masterdata.material.dao.model.MdMaterialCategoryCO;
import com.erp.masterdata.material.service.MdMaterialCategoryService;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.subject.dao.model.MdFinanceSubjectCO;

@Controller
@RequestMapping("/web/mdMaterialCategory")
public class MdMaterialCategoryWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdMaterialCategoryWebController.class);
    
    //服务层注入
    @Autowired
    private MdMaterialCategoryService mdMaterialCategoryService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdMaterialCategoryList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-02 14:54:16
     * @author 
     * @param pages
     * @param mdMaterialCategoryCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterialCategoryList")
    public String getMdMaterialCategoryList(Pages pages, MdMaterialCategoryCO mdMaterialCategoryCO, Model model) {
        return "basic.jsp?content=mdMaterial/mdMaterialCategoryList";
    }
    
    
    
    /**
     * 
     * @description 获取物料类型树
     * @date 2020年7月5日
     * @author dongbin
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterialCategoryTree")
    public String getMdMaterialCategoryTree(Model model) {
        
        model.addAttribute("treeType", "write");
        model.addAttribute("title", "物料与事项类型");
        model.addAttribute("editNodeModalUrl", "web/mdMaterialCategory/getMdMaterialCategoryEditModal");
        model.addAttribute("getTreeDataUrl", "web/mdMaterialCategory/getMdMaterialCategoryTreeData");
        model.addAttribute("deleteNodeUrl", "web/mdMaterialCategory/deleteMdMaterialCategory?categoryId=");
        
        return "common/tree/tree";
    }
    
    @RequestMapping("getMdMaterialCategoryTreeReadOnly")
    public String getMdMaterialCategoryTreeReadOnly(Model model) {
        
        model.addAttribute("treeType", "read");
        model.addAttribute("title", "物料与事项类型");
        model.addAttribute("editNodeModalUrl", "web/mdMaterialCategory/getMdMaterialCategoryEditModal");
        model.addAttribute("getTreeDataUrl", "web/mdMaterialCategory/getMdMaterialCategoryTreeData");
        model.addAttribute("deleteNodeUrl", "web/mdMaterialCategory/deleteMdMaterialCategory?categoryId=");
        
        return "common/tree/tree";
    }
    
    
    
    /**
     * 
     * @description 获取树结构数据
     * @date 2020年8月2日
     * @author dongbin
     * @param nodeId
     * @return
     * @return String
     *
     */
    @RequestMapping(value="getMdMaterialCategoryTreeData", produces = "application/json")
    @ResponseBody
    public String getMdMaterialCategoryTreeData(String nodeId) {
        //获取物料类型列表
        List<MdMaterialCategory> list = this.mdMaterialCategoryService.getDataObjects(new MdMaterialCategoryCO());
        //根节点
        TreeNode rootNode = new TreeNode();
        //节点Map
        Map<String, TreeNode> treeNodeMap = new TreeMap<String, TreeNode>();
        
        //重组组织树
        for(int a=0;a<list.size();a++) {
            MdMaterialCategory obj = list.get(a);
            
            if(a==0) {
                //设置根节点
                rootNode.setId(obj.getCategoryId().toString());
                rootNode.setCode(obj.getCategoryCode());
                rootNode.setText(obj.getCategoryName()+(obj.getStatus().equals("N")?"(停用)":""));
                rootNode.setType("root");
                Map<String, Boolean> stateMap = new HashMap<String, Boolean>();
                stateMap.put("opened", true);
                rootNode.setState(stateMap);
                rootNode.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                //设置节点Map
                treeNodeMap.put(obj.getCategoryCode(), rootNode);
            }else {
                //设置其他节点
                if(treeNodeMap.containsKey(obj.getCategoryCode())) {
                    TreeNode node = treeNodeMap.get(obj.getCategoryCode());
                    node.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(obj.getCategoryId().toString());
                    node.setCode(obj.getCategoryCode());
                    node.setText(obj.getCategoryName()+(obj.getStatus().equals("N")?"(停用)":""));
                    node.setType("node");
                    node.setChildren(this.getAllChildrenNode(obj, list, treeNodeMap));
                    //设置节点Map
                    treeNodeMap.put(obj.getCategoryCode(), node);
                }
            }
        }
        
        return JsonUtil.objectToJson(rootNode);
    }
    
  //返回所有直接子节点
    private List<TreeNode> getAllChildrenNode(MdMaterialCategory currentObj, List<MdMaterialCategory> list, Map<String, TreeNode> treeNodeMap) {
        List<TreeNode> childrenNodeList = new ArrayList<TreeNode>();
        //获取父节点段值数量
        int segmentNum = currentObj.getSegmentCode().split("_").length;
        
        for(MdMaterialCategory obj: list) {
            //获取节点段值数量
            int segmentNumTemp = obj.getSegmentCode().split("_").length;
            //判断父节点的直接子节点
            if(segmentNumTemp==segmentNum+1&&obj.getSegmentCode().contains(currentObj.getSegmentCode())) {
                if(treeNodeMap.containsKey(obj.getCategoryCode())) {
                    TreeNode node = treeNodeMap.get(obj.getCategoryCode());
                    childrenNodeList.add(node);
                }else {
                    TreeNode node = new TreeNode();
                    node.setId(obj.getCategoryId().toString());
                    node.setCode(obj.getCategoryCode());
                    node.setText(obj.getCategoryName()+(obj.getStatus().equals("N")?"(停用)":""));
                    node.setType("node");
                    childrenNodeList.add(node);
                    //设置节点Map
                    treeNodeMap.put(obj.getCategoryCode(), node);
                }
            }
        }
        
        return childrenNodeList;
    }
    
    
    
    /**
     * 
     * @description 获取物料类型编辑模态对话框
     * @date 2020年7月5日
     * @author dongbin
     * @param nodeCode
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterialCategoryEditModal")
    public String getMdMaterialCategoryEditModal(String nodeId, Model model) {
        
        //修改时获取当前节点
        if(StringUtils.isNotBlank(nodeId)) {
            MdMaterialCategory parentNode = this.mdMaterialCategoryService.getDataObject(Integer.parseInt(nodeId));
            model.addAttribute("nodeCodeValue", parentNode.getCategoryCode());
            model.addAttribute("nodeNameValue", parentNode.getCategoryName());
            model.addAttribute("nodeStatusValue", parentNode.getStatus());
            model.addAttribute("nodeCreatedDateValue", parentNode.getCreatedDate());
            model.addAttribute("nodeCreatedByValue", parentNode.getCreatedBy());
        }
       
        
        model.addAttribute("nodeId", "categoryId");
        model.addAttribute("nodeCode", "categoryCode");
        model.addAttribute("nodeName", "categoryName");
        model.addAttribute("editNodeUrl", "web/mdMaterialCategory/editMdMaterialCategory");
        
        return "common/tree/editNodeModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-02 14:54:16
     * @author 
     * @param mdMaterialCategory
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdMaterialCategory")
    @ResponseBody
    public String getMdMaterialCategory(MdMaterialCategory mdMaterialCategory, Model model) {
        String categoryCode = "";
        String categoryName = "";
        String segmentCode = "";
        String segmentDesc = "";
        
        //查询要编辑的数据
        if(mdMaterialCategory!=null&&mdMaterialCategory.getCategoryId()!=null) {
            mdMaterialCategory = this.mdMaterialCategoryService.getDataObject(mdMaterialCategory.getCategoryId());
            categoryCode = mdMaterialCategory.getCategoryCode();
            categoryName = mdMaterialCategory.getCategoryName();
            segmentCode = mdMaterialCategory.getSegmentCode();
            segmentDesc = mdMaterialCategory.getSegmentDesc();
        }
        
        return "{\"categoryCode\":\""+categoryCode+"\",\"categoryName\":\""+categoryName+"\",\"segmentCode\":\""+segmentCode+"\",\"segmentDesc\":\""+segmentDesc+"\"}";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-02 14:54:16
     * @author 
     * @param mdMaterialCategory
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdMaterialCategory")
    @ResponseBody
    public String editMdMaterialCategory(@Valid @RequestBody MdMaterialCategory obj, @RequestParam String parentId, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            //参数校验
            /*
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getHrDepartmentEditModal";
            }*/
            
            //判断是新增还是修改
            String editStatus = null;
            if(obj.getCategoryId()!=null) {
                editStatus = "update";
            }else {
                editStatus = "insert";
            }
            
            //对当前编辑的对象初始化默认的字段
            //根节点
            if(StringUtils.isBlank(parentId)) {
                editStatus = "addRoot";
                obj.setSegmentCode(obj.getCategoryCode());
                obj.setSegmentDesc(obj.getCategoryName());
            }else {
                //获取父节点
                MdMaterialCategory parentNode = this.mdMaterialCategoryService.getDataObject(Integer.parseInt(parentId));
                obj.setParentCategoryCode(parentNode.getCategoryCode());
                obj.setSegmentCode(parentNode.getSegmentCode()+"_"+obj.getCategoryCode());
                obj.setSegmentDesc(parentNode.getSegmentDesc()+"_"+obj.getCategoryName());
            }
            
            //保存编辑的数据
            this.mdMaterialCategoryService.insertOrUpdateDataObject(obj);
            
            //停用设置
            if(obj.getStatus().equals("N")) {
                obj.setCategoryName(obj.getCategoryName()+"(停用)");
            }
            
            return "{\"result\":\"success\",\"editStatus\":\""+editStatus+"\",\"nodeId\":\""+obj.getCategoryId()+"\",\"nodeText\":\""+obj.getCategoryName()+"\"}";
        }catch(Exception e) {
            
        }
        
        return "{\"result\":\"error\"}";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-02 14:54:16
     * @author 
     * @param mdMaterialCategory
     * @return String
     *
     */
    @RequestMapping("deleteMdMaterialCategory")
    @ResponseBody
    public String deleteMdMaterialCategory(MdMaterialCategory obj, RedirectAttributes attr) {
        //删除数据前验证数据
        if(obj!=null&&obj.getCategoryId()!=null) {
            int childNum = this.mdMaterialCategoryService.getChildMaterialCategoryNum(obj.getCategoryId());
            if(childNum==0) {
                //删除数据
                this.mdMaterialCategoryService.deleteDataObject(obj);
                
                return "{\"result\":\"success\"}";
            }else {
                return "{\"result\":\"hint\",\"hintMessage\":\"当前节点存在子节点，不能删除\"}";
            }
            
        }
        
        return "{\"result\":\"error\"}";
    }
}
