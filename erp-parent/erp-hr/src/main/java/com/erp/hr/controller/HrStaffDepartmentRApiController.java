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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.erp.hr.dao.model.HrStaffDepartmentR;
import com.erp.hr.dao.model.HrStaffDepartmentRCO;
import com.erp.hr.service.HrStaffDepartmentRService;

@RestController
@RequestMapping("/api/hrStaffDepartmentR")
public class HrStaffDepartmentRApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(HrStaffDepartmentRApiController.class);
    
    //服务层注入
    @Autowired
    private HrStaffDepartmentRService hrStaffDepartmentRService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-08 20:53:39
     * @author 
     * @param pages
     * @param hrStaffDepartmentRCO
     * @return String
     *
     */
    @RequestMapping("getHrStaffDepartmentRList")
    public String getHrStaffDepartmentRList(Pages pages, HrStaffDepartmentRCO hrStaffDepartmentRCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<HrStaffDepartmentR> list = this.hrStaffDepartmentRService.getDataObjects(pages, hrStaffDepartmentRCO);
        
        return JsonResultUtil.getQueryJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @return String
     *
     */
    @RequestMapping("getHrStaffDepartmentR")
    public String getHrStaffDepartmentR(HrStaffDepartmentR hrStaffDepartmentR) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editHrStaffDepartmentR")
    public String editHrStaffDepartmentR(@Valid HrStaffDepartmentR hrStaffDepartmentR, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonResultUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.hrStaffDepartmentRService.insertDataObject(hrStaffDepartmentR);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-08 20:53:39
     * @author 
     * @param hrStaffDepartmentR
     * @return String
     *
     */
    @RequestMapping("deleteHrStaffDepartmentR")
    public String deleteHrStaffDepartmentR(HrStaffDepartmentR hrStaffDepartmentR) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.hrStaffDepartmentRService.deleteDataObject(hrStaffDepartmentR);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
}