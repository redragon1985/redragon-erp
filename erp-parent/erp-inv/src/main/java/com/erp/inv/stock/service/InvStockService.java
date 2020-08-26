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
