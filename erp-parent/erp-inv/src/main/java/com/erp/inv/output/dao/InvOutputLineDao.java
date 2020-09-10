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
package com.erp.inv.output.dao;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.util.List;

import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;

public interface InvOutputLineDao extends DaoCRUDIF<InvOutputLine, InvOutputLineCO>{
    
    //获取行列表（根据头code）
    public abstract List<InvOutputLine> getInvOutputLineListByOutputHeadCode(Pages pages, InvOutputLineCO paramObj);
    
    //删除行（根据头code）
    public abstract void deleteInvOutputLineByOutputHeadCode(String outputHeadCode);
    
    //获取物料的出库数量（根据销售订单行）
    public abstract Double getOutputQuantityBySoLineCode(String soLineCode);
    
}