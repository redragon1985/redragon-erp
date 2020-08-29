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

import java.util.List;
import java.util.Map;
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
        //修改库存明细
        if(approveStatus.equals("APPROVE")) {
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
        }else if(approveStatus.equals("UNSUBMIT")) {
            //删除入库库存
            this.invStockService.deleteInvStockByBillHeadCode("OUTPUT", code);
        }
    }
    
}