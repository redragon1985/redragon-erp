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
package com.springboot.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 * @date 2020年7月6日
 * @author dongbin
 * 
 */
public class TreeNode implements Serializable {
    
    //节点id
    private String id;
    //节点编码
    private String code;
    //节点名称
    private String text;
    //节点类型
    private String type;
    //节点展开或收起状态
    private Map state;
    //子节点
    private List<TreeNode> children = new ArrayList<TreeNode>();
    
    
    /*
     * setter,getter方法
     */
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<TreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    public Map getState() {
        return state;
    }
    public void setState(Map state) {
        this.state = state;
    }
    
}
