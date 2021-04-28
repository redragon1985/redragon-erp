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
package com.erp.order.poa.controller;

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
import com.erp.order.poa.dao.model.PoAgreementHead;
import com.erp.order.poa.dao.model.PoAgreementHeadCO;
import com.erp.order.poa.service.PoAgreementHeadService;
import redragon.util.string.JsonUtil;

@RestController
@RequestMapping("/api/poAgreementHead")
public class PoAgreementHeadApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PoAgreementHeadApiController.class);
    
    //服务层注入
    @Autowired
    private PoAgreementHeadService poAgreementHeadService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-10-19 17:01:51
     * @author 
     * @param pages
     * @param poAgreementHeadCO
     * @return String
     *
     */
    @RequestMapping("getPoAgreementHeadList")
    public String getPoAgreementHeadList(Pages pages, PoAgreementHeadCO poAgreementHeadCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<PoAgreementHead> list = this.poAgreementHeadService.getDataObjects(pages, poAgreementHeadCO);
        
        return JsonTextUtil.getDataJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @return String
     *
     */
    @RequestMapping("getPoAgreementHead")
    public String getPoAgreementHead(PoAgreementHead poAgreementHead) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editPoAgreementHead")
    public String editPoAgreementHead(@Valid PoAgreementHead poAgreementHead, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonTextUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.poAgreementHeadService.insertDataObject(poAgreementHead);
        
        return JsonTextUtil.getErrorJson(0);
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @return String
     *
     */
    @RequestMapping("deletePoAgreementHead")
    public String deletePoAgreementHead(PoAgreementHead poAgreementHead) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.poAgreementHeadService.deleteDataObject(poAgreementHead);
        
        return JsonTextUtil.getErrorJson(0);
    }
    
}