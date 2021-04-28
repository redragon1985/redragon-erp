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

import com.framework.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.annotation.Log;
import com.framework.dao.model.Pages;

import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.output.dao.InvOutputLineDao;
import com.erp.inv.output.dao.model.InvOutputHead;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;
import com.erp.inv.output.service.InvOutputHeadService;
import com.erp.inv.output.service.InvOutputLineService;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.service.InvStockService;
import redragon.basic.tools.SnowFlake;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvOutputLineServiceImpl implements InvOutputLineService {

    //注入Dao
    @Autowired
    private InvOutputLineDao invOutputLineDao;
    @Autowired
    private InvStockService invStockService;
    @Autowired
    private InvOutputHeadService invOutputHeadService;
    @Autowired
    private HrCommonService hrCommonService;
    
    @Override
    public void insertDataObject(InvOutputLine obj) {
        this.invOutputLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvOutputLine obj) {
        this.invOutputLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvOutputLine obj) {
        this.invOutputLineDao.insertOrUpdateDataObject(obj);
        //插入库存（保留）
        //获取头
        InvOutputHead head = this.invOutputHeadService.getDataObject(obj.getOutputHeadCode());
        
        InvStock stock = new InvStock();
        stock.setBillHeadCode(obj.getOutputHeadCode());
        stock.setBillLineCode(obj.getOutputLineCode());
        stock.setBillType("OUTPUT");
        stock.setRetainFlag("Y");
        stock.setMaterialCode(obj.getMaterialCode());
        stock.setMemo("出库单自动创建");
        stock.setStockCode(SnowFlake.generateId().toString());
        stock.setStockNumber(-obj.getOutputQuantity());
        stock.setWarehouseCode(head.getWarehouseCode());
        //获取当前用户职员信息
        HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
        stock.setStaffCode(staffInfo.getStaffCode());
        stock.setDepartmentCode(staffInfo.getDepartmentCode());
        
        this.invStockService.insertDataObject(stock);
        
    }

    @Override
    public void deleteDataObject(InvOutputLine obj) {
        //删除库存
        InvOutputLine temp = this.invOutputLineDao.getDataObject(obj.getOutputLineId());
        this.invStockService.deleteInvStockByBillLineCode("OUTPUT", temp.getOutputHeadCode(), temp.getOutputLineCode());
        
        this.invOutputLineDao.deleteDataObject(obj);
    }

    @Override
    public List<InvOutputLine> getDataObjects() {
        return this.invOutputLineDao.getDataObjects();
    }

    @Override
    public InvOutputLine getDataObject(int id) {
        return this.invOutputLineDao.getDataObject(id);
    }

    @Override
    public InvOutputLine getDataObject(String code) {
        return this.invOutputLineDao.getDataObject(code);
    }

    @Override
    public List<InvOutputLine> getDataObjects(InvOutputLineCO paramObj) {
        return this.invOutputLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvOutputLine> getDataObjects(Pages pages) {
        return this.invOutputLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvOutputLine> getDataObjects(Pages pages, InvOutputLineCO paramObj) {
        return this.invOutputLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvOutputLineCO paramObj) {
        return this.invOutputLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvOutputLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvOutputLineCO paramObj) {
        return this.invOutputLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<InvOutputLine> getInvOutputLineListByOutputHeadCode(Pages pages, InvOutputLineCO paramObj) {
        return this.invOutputLineDao.getInvOutputLineListByOutputHeadCode(pages, paramObj);
    }
    
    @Override
    public void deleteInvOutputLineByOutputHeadCode(String outputHeadCode) {
        this.invOutputLineDao.deleteInvOutputLineByOutputHeadCode(outputHeadCode);
    }
    
    @Override
    public Double getOutputQuantityBySoLineCode(String soLineCode) {
        return this.invOutputLineDao.getOutputQuantityBySoLineCode(soLineCode);
    }
    
    @Override
    public List<InvOutputLine> getInvOutputLineListByOutputHeadCode(String outputHeadCode) {
        return this.invOutputLineDao.getInvOutputLineListByOutputHeadCode(outputHeadCode);
    }
    
    @Override
    public Double getOutputedQuantityBySoLine(String soLineCode, Integer outputLineId) {
        return this.invOutputLineDao.getOutputedQuantityBySoLine(soLineCode, outputLineId);
    }
    
}