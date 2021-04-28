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
package com.erp.inv.input.service.spring;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.framework.shiro.ShiroUtil;
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
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.input.dao.InvInputHeadDao;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputHeadCO;
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.input.service.InvInputHeadService;
import com.erp.inv.input.service.InvInputLineService;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.service.InvStockService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.service.PoLineService;
import redragon.basic.tools.SnowFlake;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvInputHeadServiceImpl implements InvInputHeadService {

    //注入Dao
    @Autowired
    private InvInputHeadDao invInputHeadDao;
    @Autowired
    private InvInputLineService invInputLineService;
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
    private PoLineService poLineService;
    
    @Override
    public void insertDataObject(InvInputHead obj) {
        this.invInputHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvInputHead obj) {
        this.invInputHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvInputHead obj) {
        this.invInputHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvInputHead obj) {
        this.invInputHeadDao.deleteDataObject(obj);
        this.invInputLineService.deleteInvInputLineByInputHeadCode(obj.getInputHeadCode());
    }

    @Override
    public List<InvInputHead> getDataObjects() {
        return this.invInputHeadDao.getDataObjects();
    }

    @Override
    public InvInputHead getDataObject(int id) {
        return this.invInputHeadDao.getDataObject(id);
    }

    @Override
    public InvInputHead getDataObject(String code) {
        return this.invInputHeadDao.getDataObject(code);
    }

    @Override
    public List<InvInputHead> getDataObjects(InvInputHeadCO paramObj) {
        return this.invInputHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvInputHead> getDataObjects(Pages pages) {
        return this.invInputHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvInputHead> getDataObjects(Pages pages, InvInputHeadCO paramObj) {
        return this.invInputHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvInputHeadCO paramObj) {
        return this.invInputHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvInputHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvInputHeadCO paramObj) {
        return this.invInputHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.invInputHeadDao.updateApproveStatus(code, approveStatus);
        
        //入库审批通过后修改入库成功后的关联数据
        if(approveStatus.equals("APPROVE")) {
            //===========================修改库存明细
            //获取头
            InvInputHead head = this.invInputHeadDao.getDataObject(code);
            //获取行
            Pages pages = new Pages();
            pages.setPage(1);
            pages.setMax(1000);
            InvInputLineCO invInputLineCO = new InvInputLineCO();
            invInputLineCO.setInputHeadCode(code);
            List<InvInputLine> lineList = this.invInputLineService.getInvInputLineListByInputHeadCode(pages, invInputLineCO);
            
            //循环插入库存
            for(InvInputLine line: lineList) {
                InvStock stock = new InvStock();
                stock.setBillHeadCode(head.getInputHeadCode());
                stock.setBillLineCode(line.getInputLineCode());
                stock.setBillType("INPUT");
                stock.setMaterialCode(line.getMaterialCode());
                stock.setMemo("入库单自动创建");
                stock.setStockCode(SnowFlake.generateId().toString());
                stock.setStockNumber(line.getInputQuantity());
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
            List<InvInputLine> inputLineList = this.invInputLineService.getInvInputLineListByInputHeadCode(code);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            //循环获取加和入库数量和物料单价
            for(InvInputLine invInputLine: inputLineList) {
                BigDecimal quantity = new BigDecimal(invInputLine.getInputQuantity());
                //获取采购订单行
                PoLine poLine = this.poLineService.getDataObject(invInputLine.getInputSourceLineCode());
                BigDecimal price = new BigDecimal(poLine.getPrice());
                //计算行金额
                BigDecimal lineAmount = quantity.multiply(price);
                //计算合计金额
                voucherAmount = voucherAmount.add(lineAmount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(code, new Double[]{voucherAmount.doubleValue()}, "INPUT");
        }else if(approveStatus.equals("UNSUBMIT")) {
            //===========================//删除入库库存
            this.invStockService.deleteInvStockByBillHeadCode("INPUT", code);
            
            //===========================删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("INPUT", code);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
        }
    }
    
}