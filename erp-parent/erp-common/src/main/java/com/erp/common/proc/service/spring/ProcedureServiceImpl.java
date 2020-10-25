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
/**
 * @description ProcedureServiceImpl.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.proc.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.common.ap.pay.dao.ApPayHeadDao;
import com.erp.common.proc.dao.ProcedureDao;
import com.erp.common.proc.service.ProcedureService;

/**
 * @description
 * @date 2020年10月13日
 * @author dongbin
 * 
 */
@Service("procedureServiceCommon")
//@Transactional(rollbackFor=Exception.class)
public class ProcedureServiceImpl implements ProcedureService {
    
    //注入Dao
    @Autowired
    @Qualifier("procedureDaoCommon")
    private ProcedureDao procedureDao;

    @Override
    public int saveHistoryData(String tableName, String primaryKey, String primarykeyValue) {
        return this.procedureDao.saveHistoryData(tableName, primaryKey, primarykeyValue);
    }

}
