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
package com.erp.finance.ap.invoice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;

@RestController
@RequestMapping("/api/apInvoiceHead")
public class ApInvoiceHeadApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApInvoiceHeadApiController.class);
    
    //服务层注入
    @Autowired
    private ApInvoiceHeadService apInvoiceHeadService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-19 14:16:19
     * @author 
     * @param pages
     * @param payHeadCO
     * @return String
     *
     */
    @RequestMapping("getApInvoiceHeadList")
    public String getApInvoiceHeadList(Pages pages, ApInvoiceHeadCO payHeadCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<ApInvoiceHead> list = this.apInvoiceHeadService.getDataObjects(pages, payHeadCO);
        
        return JsonResultUtil.getQueryJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @return String
     *
     */
    @RequestMapping("getApInvoiceHead")
    public String getApInvoiceHead(ApInvoiceHead payHead) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editApInvoiceHead")
    public String editApInvoiceHead(@Valid ApInvoiceHead payHead, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonResultUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.apInvoiceHeadService.insertDataObject(payHead);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @return String
     *
     */
    @RequestMapping("deleteApInvoiceHead")
    public String deleteApInvoiceHead(ApInvoiceHead payHead) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.apInvoiceHeadService.deleteDataObject(payHead);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
}