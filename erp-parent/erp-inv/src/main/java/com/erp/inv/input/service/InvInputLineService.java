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
package com.erp.inv.input.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.util.List;

import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;

public interface InvInputLineService extends DaoCRUDIF<InvInputLine, InvInputLineCO> {
    
    //获取行列表（根据头code）
    public abstract List<InvInputLine> getInvInputLineListByInputHeadCode(Pages pages, InvInputLineCO paramObj);
    
    //删除行（根据头code）
    public abstract void deleteInvInputLineByInputHeadCode(String inputHeadCode);
    
}
