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
package com.erp.inv.check.service.spring;

import java.math.BigDecimal;
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
import com.erp.inv.check.dao.InvStockCheckHeadDao;
import com.erp.inv.check.dao.model.InvStockCheckHead;
import com.erp.inv.check.dao.model.InvStockCheckHeadCO;
import com.erp.inv.check.dao.model.InvStockCheckLine;
import com.erp.inv.check.dao.model.InvStockData;
import com.erp.inv.check.service.InvStockCheckHeadService;
import com.erp.inv.check.service.InvStockCheckLineService;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.service.InvStockService;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvStockCheckHeadServiceImpl implements InvStockCheckHeadService {

    //注入Dao
    @Autowired
    private InvStockCheckHeadDao invStockCheckHeadDao;
    @Autowired
    private InvStockCheckLineService invStockCheckLineService;
    @Autowired
    private InvStockService invStockService;
    @Autowired
    private HrCommonService hrCommonService;
    
    @Override
    public void insertDataObject(InvStockCheckHead obj) {
        this.invStockCheckHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvStockCheckHead obj) {
        this.invStockCheckHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStockCheckHead obj) {
        this.invStockCheckHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvStockCheckHead obj) {
        this.invStockCheckHeadDao.deleteDataObject(obj);
        this.invStockCheckLineService.deleteInvStockCheckLineByHeadCode(obj.getCheckHeadCode());
    }

    @Override
    public List<InvStockCheckHead> getDataObjects() {
        return this.invStockCheckHeadDao.getDataObjects();
    }

    @Override
    public InvStockCheckHead getDataObject(int id) {
        return this.invStockCheckHeadDao.getDataObject(id);
    }

    @Override
    public InvStockCheckHead getDataObject(String code) {
        return this.invStockCheckHeadDao.getDataObject(code);
    }

    @Override
    public List<InvStockCheckHead> getDataObjects(InvStockCheckHeadCO paramObj) {
        return this.invStockCheckHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvStockCheckHead> getDataObjects(Pages pages) {
        return this.invStockCheckHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvStockCheckHead> getDataObjects(Pages pages, InvStockCheckHeadCO paramObj) {
        return this.invStockCheckHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCheckHeadCO paramObj) {
        return this.invStockCheckHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvStockCheckHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvStockCheckHeadCO paramObj) {
        return this.invStockCheckHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.invStockCheckHeadDao.updateApproveStatus(code, approveStatus);
        //修改库存明细
        if(approveStatus.equals("APPROVE")) {
            //获取头
            InvStockCheckHead head = this.invStockCheckHeadDao.getDataObject(code);
            //获取行
            List<InvStockCheckLine> lineList = this.invStockCheckLineService.getInvStockCheckLineListByHeadCode(code);
            
            //循环插入库存
            for(InvStockCheckLine line: lineList) {
                InvStock stock = new InvStock();
                stock.setBillHeadCode(head.getCheckHeadCode());
                stock.setBillLineCode(line.getCheckLineCode());
                stock.setBillType("CHECK");
                stock.setMaterialCode(line.getMaterialCode());
                stock.setStockCode(SnowFlake.generateId().toString());
                //计算库存变化
                BigDecimal after = new BigDecimal(line.getCheckAfterQuantity());
                BigDecimal before = new BigDecimal(line.getCheckBeforeQuantity());
                Double quantity = after.subtract(before).doubleValue();
                if(quantity==0) {
                    continue;
                }else if(quantity>0) {
                    stock.setMemo("库存盘点[盘盈]自动创建");
                }else if(quantity<0) {
                    stock.setMemo("库存盘点[盘亏]自动创建");
                }
                stock.setStockNumber(quantity);
                stock.setWarehouseCode(head.getWarehouseCode());
                //获取当前用户职员信息
                HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
                stock.setStaffCode(staffInfo.getStaffCode());
                stock.setDepartmentCode(staffInfo.getDepartmentCode());
                
                this.invStockService.insertDataObject(stock);
            }
        }else if(approveStatus.equals("UNSUBMIT")) {
            //删除入库库存
            this.invStockService.deleteInvStockByBillHeadCode("CHECK", code);
        }
    }
    
    @Override
    public List<InvStockData> getInvStockDataForCheck(String warehouseCode) {
        return this.invStockCheckHeadDao.getInvStockDataForCheck(warehouseCode);
    }
    
    @Override
    public void insertOrUpdateStockCheck(InvStockCheckHead invStockCheckHead, List<InvStockCheckLine> invStockCheckLineList) {
        //保存头
        this.invStockCheckHeadDao.insertOrUpdateDataObject(invStockCheckHead);
        //循环保存行
        for(InvStockCheckLine invStockCheckLine: invStockCheckLineList) {
            this.invStockCheckLineService.insertOrUpdateDataObject(invStockCheckLine);
        }
    }
    
}