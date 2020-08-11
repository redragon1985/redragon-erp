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
package com.erp.finance.voucher.controller;

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
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherBillRCO;
import com.erp.finance.voucher.service.FinVoucherBillRService;

@RestController
@RequestMapping("/api/finVoucherBillR")
public class FinVoucherBillRApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(FinVoucherBillRApiController.class);
    
    //服务层注入
    @Autowired
    private FinVoucherBillRService finVoucherBillRService;

    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-31 12:50:25
     * @author 
     * @param pages
     * @param finVoucherBillRCO
     * @return String
     *
     */
    @RequestMapping("getFinVoucherBillRList")
    public String getFinVoucherBillRList(Pages pages, FinVoucherBillRCO finVoucherBillRCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<FinVoucherBillR> list = this.finVoucherBillRService.getDataObjects(pages, finVoucherBillRCO);
        
        return JsonResultUtil.getQueryJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @return String
     *
     */
    @RequestMapping("getFinVoucherBillR")
    public String getFinVoucherBillR(FinVoucherBillR finVoucherBillR) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editFinVoucherBillR")
    public String editFinVoucherBillR(@Valid FinVoucherBillR finVoucherBillR, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonResultUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.finVoucherBillRService.insertDataObject(finVoucherBillR);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @return String
     *
     */
    @RequestMapping("deleteFinVoucherBillR")
    public String deleteFinVoucherBillR(FinVoucherBillR finVoucherBillR) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.finVoucherBillRService.deleteDataObject(finVoucherBillR);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
}