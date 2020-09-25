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
package com.erp.inv.output.service.spring;

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
import com.framework.util.ShiroUtil;

import redragon.frame.hibernate.SnowFlake;

import com.erp.common.voucher.service.FinVoucherBillRService;
import com.erp.common.voucher.service.FinVoucherHeadService;
import com.erp.common.voucher.service.FinVoucherModelHeadService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.output.dao.InvOutputHeadDao;
import com.erp.inv.output.dao.model.InvOutputHead;
import com.erp.inv.output.dao.model.InvOutputHeadCO;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;
import com.erp.inv.output.service.InvOutputHeadService;
import com.erp.inv.output.service.InvOutputLineService;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.service.InvStockService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.service.PoLineService;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.service.SoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvOutputHeadServiceImpl implements InvOutputHeadService {

    //注入Dao
    @Autowired
    private InvOutputHeadDao invOutputHeadDao;
    @Autowired
    private InvOutputLineService invOutputLineService;
    @Autowired
    private InvStockService invStockService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    @Qualifier("finVoucherModelHeadServiceCommon")
    private FinVoucherModelHeadService finVoucherModelHeadService;
    @Autowired
    @Qualifier("finVoucherHeadServiceCommon")
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    @Qualifier("finVoucherBillRServiceCommon")
    private FinVoucherBillRService finVoucherBillRService;
    @Autowired
    private SoLineService soLineService;
    
    @Override
    public void insertDataObject(InvOutputHead obj) {
        this.invOutputHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvOutputHead obj) {
        this.invOutputHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvOutputHead obj) {
        this.invOutputHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvOutputHead obj) {
        this.invOutputHeadDao.deleteDataObject(obj);
        this.invOutputLineService.deleteInvOutputLineByOutputHeadCode(obj.getOutputHeadCode());
    }

    @Override
    public List<InvOutputHead> getDataObjects() {
        return this.invOutputHeadDao.getDataObjects();
    }

    @Override
    public InvOutputHead getDataObject(int id) {
        return this.invOutputHeadDao.getDataObject(id);
    }

    @Override
    public InvOutputHead getDataObject(String code) {
        return this.invOutputHeadDao.getDataObject(code);
    }

    @Override
    public List<InvOutputHead> getDataObjects(InvOutputHeadCO paramObj) {
        return this.invOutputHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvOutputHead> getDataObjects(Pages pages) {
        return this.invOutputHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvOutputHead> getDataObjects(Pages pages, InvOutputHeadCO paramObj) {
        return this.invOutputHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvOutputHeadCO paramObj) {
        return this.invOutputHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvOutputHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvOutputHeadCO paramObj) {
        return this.invOutputHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.invOutputHeadDao.updateApproveStatus(code, approveStatus);
        //出库审批通过后修改出库成功后的关联数据
        if(approveStatus.equals("APPROVE")) {
            //===========================修改库存明细
            //获取头
            InvOutputHead head = this.invOutputHeadDao.getDataObject(code);
            //获取行
            Pages pages = new Pages();
            pages.setPage(1);
            pages.setMax(1000);
            InvOutputLineCO invOutputLineCO = new InvOutputLineCO();
            invOutputLineCO.setOutputHeadCode(code);
            List<InvOutputLine> lineList = this.invOutputLineService.getInvOutputLineListByOutputHeadCode(pages, invOutputLineCO);
            
            //先删除之前的保留
            this.invStockService.deleteInvStockByBillHeadCode("OUTPUT", code);
            
            //循环插入库存
            for(InvOutputLine line: lineList) {
                InvStock stock = new InvStock();
                stock.setBillHeadCode(head.getOutputHeadCode());
                stock.setBillLineCode(line.getOutputLineCode());
                stock.setBillType("OUTPUT");
                stock.setMaterialCode(line.getMaterialCode());
                stock.setMemo("出库单自动创建");
                stock.setStockCode(SnowFlake.generateId().toString());
                stock.setStockNumber(-line.getOutputQuantity());
                stock.setWarehouseCode(head.getWarehouseCode());
                //获取当前用户职员信息
                HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
                stock.setStaffCode(staffInfo.getStaffCode());
                stock.setDepartmentCode(staffInfo.getDepartmentCode());
                
                this.invStockService.insertDataObject(stock);
            }
            
            //===========================自动生成凭证和分录
            //计算分录的金额
            //获取入库行
            List<InvOutputLine> outputLineList = this.invOutputLineService.getInvOutputLineListByOutputHeadCode(code);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            //循环获取加和入库数量和物料单价
            for(InvOutputLine invOutputLine: outputLineList) {
                BigDecimal quantity = new BigDecimal(invOutputLine.getOutputQuantity());
                //获取采购订单行
                SoLine soLine = this.soLineService.getDataObject(invOutputLine.getOutputSourceLineCode());
                BigDecimal price = new BigDecimal(soLine.getPrice());
                //计算行金额
                BigDecimal lineAmount = quantity.multiply(price);
                //计算合计金额
                voucherAmount = voucherAmount.add(lineAmount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(code, new Double[]{voucherAmount.doubleValue()}, "OUTPUT");
        }else if(approveStatus.equals("UNSUBMIT")) {
            //===========================//删除入库库存
            this.invStockService.deleteInvStockByBillHeadCode("OUTPUT", code);
            
            //===========================删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("OUTPUT", code);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
        }
    }
    
}