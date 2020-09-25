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
package com.erp.finance.ap.invoice.service.spring;

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
import com.erp.finance.ap.invoice.dao.ApInvoiceHeadDao;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLine;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.order.po.dao.model.PoLine;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.dao.model.Pages;
import com.framework.util.ShiroUtil;

import redragon.frame.hibernate.SnowFlake;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApInvoiceHeadServiceImpl implements ApInvoiceHeadService {

    //注入Dao
    @Autowired
    private ApInvoiceHeadDao payHeadDao;
    @Autowired
    private ApInvoiceLineService payLineService;
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
    public void insertDataObject(ApInvoiceHead obj) {
        this.payHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApInvoiceHead obj) {
        this.payHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApInvoiceHead obj) {
        this.payHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApInvoiceHead obj) {
        this.payHeadDao.deleteDataObject(obj);
        this.payLineService.deleteLineByHeadCode(obj.getInvoiceHeadCode());
    }

    @Override
    public List<ApInvoiceHead> getDataObjects() {
        return this.payHeadDao.getDataObjects();
    }

    @Override
    public ApInvoiceHead getDataObject(int id) {
        return this.payHeadDao.getDataObject(id);
    }

    @Override
    public ApInvoiceHead getDataObject(String code) {
        return this.payHeadDao.getDataObject(code);
    }

    @Override
    public List<ApInvoiceHead> getDataObjects(ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApInvoiceHead> getDataObjects(Pages pages) {
        return this.payHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApInvoiceHead> getDataObjects(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceHead> getApInvoiceHeadListForNotCreateVoucher(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getApInvoiceHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.payHeadDao.updateApproveStatus(code, approveStatus);
        
        //发票审批通过后修改关联数据
        if(approveStatus.equals("APPROVE")) {
            //===========================自动生成凭证和分录
            //计算分录的金额
            //获取入库行
            List<ApInvoiceLine> lineList = this.payLineService.getApInvoiceLineListByHeadCode(code);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            BigDecimal amountSum = new BigDecimal(0D);
            BigDecimal taxAmountSum = new BigDecimal(0D);
            //循环获取加和
            for(ApInvoiceLine line: lineList) {
                BigDecimal amount = new BigDecimal(line.getAmount());
                BigDecimal taxAmount = new BigDecimal(line.getTaxAmount());
                //计算合计金额
                amountSum = amountSum.add(amount);
                taxAmountSum = taxAmountSum.add(taxAmount);
                voucherAmount = voucherAmount.add(amount).add(taxAmount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(code, new Double[]{amountSum.doubleValue(),taxAmountSum.doubleValue(),voucherAmount.doubleValue()}, "AP_INVOICE");
        }else if(approveStatus.equals("UNSUBMIT")) {
            //===========================删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("AP_INVOICE", code);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
        }
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getApInvoiceHeadNum(String startDate, String endDate) {
        return this.payHeadDao.getApInvoiceHeadNum(startDate, endDate);
    }
    
}