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
package com.erp.finance.voucher.service.spring;

import java.util.List;
import java.util.Map;

import com.framework.controller.JsonTextUtil;
import com.framework.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.annotation.Log;
import com.framework.dao.model.Pages;

import redragon.basic.tools.SnowFlake;
import redragon.basic.tools.TimeToolKit;

import com.erp.finance.voucher.dao.FinVoucherModelHeadDao;
import com.erp.finance.voucher.dao.FinVoucherModelLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHeadCO;
import com.erp.finance.voucher.dao.model.FinVoucherModelLine;
import com.erp.finance.voucher.service.FinVoucherBillRService;
import com.erp.finance.voucher.service.FinVoucherHeadService;
import com.erp.finance.voucher.service.FinVoucherLineService;
import com.erp.finance.voucher.service.FinVoucherModelHeadService;
import com.erp.finance.voucher.util.FinVoucherUtil;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FinVoucherModelHeadServiceImpl implements FinVoucherModelHeadService {

    //注入Dao
    @Autowired
    private FinVoucherModelHeadDao finVoucherModelHeadDao;
    @Autowired
    private FinVoucherModelLineDao finVoucherModelLineDao;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    private FinVoucherLineService finVoucherLineService;
    @Autowired
    private FinVoucherBillRService finVoucherBillRService;
    
    @Override
    public void insertDataObject(FinVoucherModelHead obj) {
        this.finVoucherModelHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelHead obj) {
        this.finVoucherModelHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelHead obj) {
        this.finVoucherModelHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelHead obj) {
        this.finVoucherModelHeadDao.deleteDataObject(obj);
        this.finVoucherModelLineDao.deleteFinVoucherModelLineByVoucherHeadCode(obj.getVoucherHeadCode());
    }

    @Override
    public List<FinVoucherModelHead> getDataObjects() {
        return this.finVoucherModelHeadDao.getDataObjects();
    }

    @Override
    public FinVoucherModelHead getDataObject(int id) {
        return this.finVoucherModelHeadDao.getDataObject(id);
    }

    @Override
    public FinVoucherModelHead getDataObject(String code) {
        return this.finVoucherModelHeadDao.getDataObject(code);
    }

    @Override
    public List<FinVoucherModelHead> getDataObjects(FinVoucherModelHeadCO paramObj) {
        return this.finVoucherModelHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<FinVoucherModelHead> getDataObjects(Pages pages) {
        return this.finVoucherModelHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<FinVoucherModelHead> getDataObjects(Pages pages, FinVoucherModelHeadCO paramObj) {
        return this.finVoucherModelHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherModelHeadCO paramObj) {
        return this.finVoucherModelHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<FinVoucherModelHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, FinVoucherModelHeadCO paramObj) {
        return this.finVoucherModelHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void insertOrUpdateFinVoucherModel(FinVoucherModelHead finVoucherModelHead, List<FinVoucherModelLine> finVoucherModelLineList) {
        //编辑头
        this.finVoucherModelHeadDao.insertOrUpdateDataObject(finVoucherModelHead);
        //循环编辑行
        for(FinVoucherModelLine finVoucherModelLine: finVoucherModelLineList) {
            this.finVoucherModelLineDao.insertOrUpdateDataObject(finVoucherModelLine);
        }
    }
    
    @Override
    public List<FinVoucherModelHead> getFinVoucherModelHeadListForCustom() {
        return this.finVoucherModelHeadDao.getFinVoucherModelHeadListForCustom();
    }
    
    @Override
    public List<String> getFinVoucherModelHeadForBusinessType() {
        return this.finVoucherModelHeadDao.getFinVoucherModelHeadForBusinessType();
    }
    
    @Override
    public FinVoucherModelHead getFinVoucherModelHeadByBusinessType(String businessType) {
        return this.finVoucherModelHeadDao.getFinVoucherModelHeadByBusinessType(businessType);
    }
    
    @Override
    public String autoCreateVoucher(String billHeadCode, Double amount, String businessType) {
        //获取凭证模板头
        FinVoucherModelHead finVoucherModelHead = this.finVoucherModelHeadDao.getFinVoucherModelHeadByBusinessType(businessType);
        
        //获取凭证模板行
        if(finVoucherModelHead!=null) {
           List<FinVoucherModelLine> finVoucherModelLineList = this.finVoucherModelLineDao.getFinVoucherModelLineListByVoucherHeadCode(finVoucherModelHead.getVoucherHeadCode());
           
           if(finVoucherModelLineList.size()>0) {
               //设置凭证头
               FinVoucherHead finVoucherHead = new FinVoucherHead();
               finVoucherHead.setBillNum(0);
               TimeToolKit time = new TimeToolKit();
               finVoucherHead.setVoucherDate(time.getJavaDate());
               String code = SnowFlake.generateId().toString();
               finVoucherHead.setVoucherHeadCode(code);
               finVoucherHead.setVoucherType(finVoucherModelHead.getVoucherType());
               //获取凭证编号
               finVoucherHead.setVoucherNumber(FinVoucherUtil.incrVoucherNumberCache(finVoucherModelHead.getVoucherType()).toString());
               //获取当前用户职员信息
               HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
               finVoucherHead.setStaffCode(staffInfo.getStaffCode());
               finVoucherHead.setDepartmentCode(staffInfo.getDepartmentCode());
               //插入凭证头
               this.finVoucherHeadService.insertDataObject(finVoucherHead);
               
               //循环设置凭证行
               for(FinVoucherModelLine finVoucherModelLine: finVoucherModelLineList) {
                   FinVoucherLine finVoucherLine = new FinVoucherLine();
                   //金额设置
                   if(finVoucherModelLine.getDrAmount().equals("AMOUNT")) {
                       finVoucherLine.setDrAmount(amount);
                       finVoucherLine.setCrAmount(0D);
                   }else if(finVoucherModelLine.getCrAmount().equals("AMOUNT")) {
                       finVoucherLine.setCrAmount(amount);
                       finVoucherLine.setDrAmount(0D);
                   }
                   
                   finVoucherLine.setMemo(finVoucherModelLine.getMemo());
                   finVoucherLine.setSubjectCode(finVoucherModelLine.getSubjectCode());
                   finVoucherLine.setVoucherHeadCode(finVoucherHead.getVoucherHeadCode());
                   finVoucherLine.setVoucherLineCode(SnowFlake.generateId().toString());
                   //插入凭证行
                   this.finVoucherLineService.insertDataObject(finVoucherLine);
               }
               
               //记录凭证和单据的关联
               FinVoucherBillR finVoucherBillR = new FinVoucherBillR();
               finVoucherBillR.setBillType(businessType);
               finVoucherBillR.setVoucherHeadCode(finVoucherHead.getVoucherHeadCode());
               finVoucherBillR.setBillHeadCode(billHeadCode);
               //插入关联
               this.finVoucherBillRService.insertDataObject(finVoucherBillR);
               
               //返回json数据
               return "{\"errCode\":0, \"errMsg\": \"\", \"voucherHeadId\": "+finVoucherHead.getVoucherHeadId()+", \"voucherHeadCode\": \""+finVoucherHead.getVoucherHeadCode()+"\"}";
           }
        }else {
            return JsonTextUtil.getErrorJson(-1, "请先创建自动凭证模板");
        }
        
        return JsonTextUtil.getErrorJson(-1, "自动生成凭证执行错误");
    }
    
}