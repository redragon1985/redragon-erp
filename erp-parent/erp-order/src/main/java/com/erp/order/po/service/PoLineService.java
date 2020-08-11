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
package com.erp.order.po.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.math.BigDecimal;
import java.util.List;

import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;

public interface PoLineService extends DaoCRUDIF<PoLine, PoLineCO> {
    
    //获取行列表（根据头code）
    public abstract List<PoLine> getPoLineListByPoHeadCode(Pages pages, PoLineCO paramObj);
    
    //删除行（根据头code）
    public abstract void deletetPoLineByPoHeadCode(String poHeadCode);
    
    //获取订单行金额汇总
    public abstract BigDecimal getPoAmount(String poHeadCode);
    
}
