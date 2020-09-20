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
package com.erp.finance.ap.pay.controller;

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
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ap.pay.dao.model.ApPayHeadCO;
import com.erp.finance.ap.pay.service.ApPayHeadService;

@RestController
@RequestMapping("/api/apPayHead")
public class ApPayHeadApiController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApPayHeadApiController.class);
    
    //服务层注入
    @Autowired
    private ApPayHeadService apPayHeadService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-09-15 14:43:59
     * @author 
     * @param pages
     * @param apPayHeadCO
     * @return String
     *
     */
    @RequestMapping("getApPayHeadList")
    public String getApPayHeadList(Pages pages, ApPayHeadCO apPayHeadCO) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<ApPayHead> list = this.apPayHeadService.getDataObjects(pages, apPayHeadCO);
        
        return JsonResultUtil.getQueryJson(JsonUtil.listToJson(list, "yyyy-MM-dd HH:mm:ss"), JsonUtil.objectToJson(pages), null);
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-09-15 14:43:59
     * @author 
     * @param apPayHead
     * @return String
     *
     */
    @RequestMapping("getApPayHead")
    public String getApPayHead(ApPayHead apPayHead) {
        //TODO: 查询要编辑的数据
        
        return null;
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-09-15 14:43:59
     * @author 
     * @param apPayHead
     * @param bindingResult
     * @return String
     *
     */
    @RequestMapping("editApPayHead")
    public String editApPayHead(@Valid ApPayHead apPayHead, BindingResult bindingResult) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult);
        if(errorMap.size()>0) {
            return JsonResultUtil.getErrorJson(11, "", JsonUtil.mapToJson(errorMap));
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.apPayHeadService.insertDataObject(apPayHead);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-09-15 14:43:59
     * @author 
     * @param apPayHead
     * @return String
     *
     */
    @RequestMapping("deleteApPayHead")
    public String deleteApPayHead(ApPayHead apPayHead) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.apPayHeadService.deleteDataObject(apPayHead);
        
        return JsonResultUtil.getErrorJson(0);
    }
    
}