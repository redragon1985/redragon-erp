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
package com.erp.finance.ap.pay.service.spring;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.annotation.Log;
import com.framework.dao.model.Pages;
import com.erp.common.voucher.service.FinVoucherBillRService;
import com.erp.common.voucher.service.FinVoucherHeadService;
import com.erp.common.voucher.service.FinVoucherModelHeadService;
import com.erp.finance.ap.pay.dao.ApPayHeadDao;
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ap.pay.dao.model.ApPayHeadCO;
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ap.pay.service.ApPayHeadService;
import com.erp.finance.ap.pay.service.ApPayLineService;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceLine;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApPayHeadServiceImpl implements ApPayHeadService {

    //注入Dao
    @Autowired
    private ApPayHeadDao apPayHeadDao;
    @Autowired
    private ApPayLineService apPayLineService;
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
    public void insertDataObject(ApPayHead obj) {
        this.apPayHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApPayHead obj) {
        this.apPayHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApPayHead obj) {
        this.apPayHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApPayHead obj) {
        this.apPayHeadDao.deleteDataObject(obj);
        this.apPayLineService.deleteLineByHeadCode(obj.getPayHeadCode());
    }

    @Override
    public List<ApPayHead> getDataObjects() {
        return this.apPayHeadDao.getDataObjects();
    }

    @Override
    public ApPayHead getDataObject(int id) {
        return this.apPayHeadDao.getDataObject(id);
    }

    @Override
    public ApPayHead getDataObject(String code) {
        return this.apPayHeadDao.getDataObject(code);
    }

    @Override
    public List<ApPayHead> getDataObjects(ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApPayHead> getDataObjects(Pages pages) {
        return this.apPayHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApPayHead> getDataObjects(Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApPayHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.apPayHeadDao.updateApproveStatus(code, approveStatus);
        
        //发票审批通过后修改关联数据
        if(approveStatus.equals("APPROVE")) {
            //===========================自动生成凭证和分录
            //计算分录的金额
            //获取入库行
            List<ApPayLine> lineList = this.apPayLineService.getApPayLineListByHeadCode(code);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            //循环获取加和
            for(ApPayLine line: lineList) {
                BigDecimal amount = new BigDecimal(line.getInvoicePayAmount());
                //计算合计金额
                voucherAmount = voucherAmount.add(amount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(code, new Double[]{voucherAmount.doubleValue()}, "PAY");
        }else if(approveStatus.equals("UNSUBMIT")) {
            //===========================删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("PAY", code);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
        }
    }
    
}