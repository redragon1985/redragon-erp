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
package com.erp.inv.stock.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.util.List;

import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.dao.model.InvStockCO;

public interface InvStockService extends DaoCRUDIF<InvStock, InvStockCO> {
    
    //仓库中某些物料是否存在库存（排除初始库存）
    public abstract boolean isExistRelateDataForInvStock(String warehouseCode, String materialCode);
    
    //仓库中某些物料是否存在初始库存
    public abstract boolean isExistInitDataForInvStock(String warehouseCode, String materialCode);
    
    //删除库存明细（根据出入库头编码）
    public abstract void deleteInvStockByBillHeadCode(String billType, String billHeadCode);
    
    //删除库存明细（根据出入库行编码）
    public abstract void deleteInvStockByBillLineCode(String billType, String billHeadCode, String billLineCode);
    
    //获取物料库存数量
    public abstract Double getStockNumberByMaterialCode(String materialCode);
    
    //获取初始化库存数据
    public abstract List<InvStock> getInitInvStockList(Pages pages, InvStockCO paramObj);
    
}
