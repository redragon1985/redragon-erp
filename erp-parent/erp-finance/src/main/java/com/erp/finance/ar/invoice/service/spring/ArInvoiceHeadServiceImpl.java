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
package com.erp.finance.ar.invoice.service.spring;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.common.voucher.service.FinVoucherBillRService;
import com.erp.common.voucher.service.FinVoucherHeadService;
import com.erp.common.voucher.service.FinVoucherModelHeadService;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLine;
import com.erp.finance.ar.invoice.dao.ArInvoiceHeadDao;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHead;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHeadCO;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceLine;
import com.erp.finance.ar.invoice.service.ArInvoiceHeadService;
import com.erp.finance.ar.invoice.service.ArInvoiceLineService;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.dao.model.Pages;

@Service
@Transactional(rollbackFor=Exception.class)
public class ArInvoiceHeadServiceImpl implements ArInvoiceHeadService {

    //注入Dao
    @Autowired
    private ArInvoiceHeadDao receiptHeadDao;
    @Autowired
    private ArInvoiceLineService receiptLineService;
    @Autowired
    @Qualifier("finVoucherModelHeadServiceCommon")
    private FinVoucherModelHeadService finVoucherModelHeadService;
    @Autowired
    @Qualifier("finVoucherHeadServiceCommon")
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    @Qualifier("finVoucherBillRServiceCommon")
    private FinVoucherBillRService finVoucherBillRService;
    
    @Override
    public void insertDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.deleteDataObject(obj);
        this.receiptLineService.deleteLineByHeadCode(obj.getInvoiceHeadCode());
    }

    @Override
    public List<ArInvoiceHead> getDataObjects() {
        return this.receiptHeadDao.getDataObjects();
    }

    @Override
    public ArInvoiceHead getDataObject(int id) {
        return this.receiptHeadDao.getDataObject(id);
    }

    @Override
    public ArInvoiceHead getDataObject(String code) {
        return this.receiptHeadDao.getDataObject(code);
    }

    @Override
    public List<ArInvoiceHead> getDataObjects(ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages) {
        return this.receiptHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ArInvoiceHead> getArInvoiceHeadListForNotCreateVoucher(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getArInvoiceHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.receiptHeadDao.updateApproveStatus(code, approveStatus);
        
        //发票审批通过后修改关联数据
        if(approveStatus.equals("APPROVE")) {
            //===========================自动生成凭证和分录
            //计算分录的金额
            //获取入库行
            List<ArInvoiceLine> lineList = this.receiptLineService.getArInvoiceLineListByHeadCode(code);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            BigDecimal amountSum = new BigDecimal(0D);
            BigDecimal taxAmountSum = new BigDecimal(0D);
            //循环获取加和
            for(ArInvoiceLine line: lineList) {
                BigDecimal amount = new BigDecimal(line.getAmount());
                BigDecimal taxAmount = new BigDecimal(line.getTaxAmount());
                //计算合计金额
                amountSum = amountSum.add(amount);
                taxAmountSum = taxAmountSum.add(taxAmount);
                voucherAmount = voucherAmount.add(amount).add(taxAmount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(code, new Double[]{voucherAmount.doubleValue(),amountSum.doubleValue(),taxAmountSum.doubleValue()}, "AR_INVOICE");
        }else if(approveStatus.equals("UNSUBMIT")) {
            //===========================删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("AR_INVOICE", code);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
        }
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getArInvoiceHeadNum(String startDate, String endDate) {
        return this.receiptHeadDao.getArInvoiceHeadNum(startDate, endDate);
    }
    
}