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

import com.framework.controller.JsonTextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.service.HrDepartmentService;
import redragon.util.string.JsonUtil;

@RestController
@RequestMapping("/api/hrDepartment")
public class HrDepartmentApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrDepartmentApiController.class);
    
    //服务层注入
    @Autowired
    private HrDepartmentService hrDepartmentService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-05 12:28:05
     * @author 
     * @param pages
     * @param hrDepartmentCO
     * @return String
     *
     */
    @RequestMapping("getHrDepartmentList")
    public String getHrDepartmentList(Pages pages, HrDepartmentCO hrDepartmentCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<HrDepartment> list = this.hrDepartmentService.getDataObjects(pages, hrDepartmentCO);
        
        return JsonTextUtil.getDataJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param hrDepartment
     * @return String
     *
     */
    @RequestMapping("getHrDepartment")
    public String getHrDepartment(HrDepartment hrDepartment) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-05 12:28:05
     * @author 
     * @param hrDepartment
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editHrDepartment")
    public String editHrDepartment(@Valid HrDepartment hrDepartment, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonTextUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.hrDepartmentService.insertDataObject(hrDepartment);
        
        return JsonTextUtil.getErrorJson(0);
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
    public String deleteHrDepartment(HrDepartment hrDepartment) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.hrDepartmentService.deleteDataObject(hrDepartment);
        
        return JsonTextUtil.getErrorJson(0);
    }
    
}