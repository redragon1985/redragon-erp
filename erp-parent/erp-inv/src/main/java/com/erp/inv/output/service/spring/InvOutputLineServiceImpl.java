/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import com.erp.inv.output.dao.InvOutputLineDao;
import com.erp.inv.output.dao.model.InvOutputHead;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;
import com.erp.inv.output.service.InvOutputHeadService;
import com.erp.inv.output.service.InvOutputLineService;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.service.InvStockService;

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
    
}