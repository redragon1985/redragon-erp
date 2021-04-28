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
package com.erp.common.voucher.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.BasicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.framework.annotation.Cache;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.model.Pages;
import com.framework.util.DaoUtil;
import com.erp.common.voucher.dao.FinVoucherLineDao;
import com.erp.common.voucher.dao.model.FinVoucherHead;
import com.erp.common.voucher.dao.model.FinVoucherLine;
import com.erp.common.voucher.dao.model.FinVoucherLineCO;

@Repository("finVoucherLineDaoCommon")
public class FinVoucherLineDaoImpl implements FinVoucherLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(FinVoucherLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherLine> getDataObjects() {
        return this.basicDao.getDataAllObject(FinVoucherLine.class);
    }

    @Override
    public FinVoucherLine getDataObject(int id) {
        return (FinVoucherLine)this.basicDao.getDataObject(FinVoucherLine.class, id);
    }
    
    @Override
    public FinVoucherLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteFinVoucherLineByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_line where voucher_head_code = :voucherheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
