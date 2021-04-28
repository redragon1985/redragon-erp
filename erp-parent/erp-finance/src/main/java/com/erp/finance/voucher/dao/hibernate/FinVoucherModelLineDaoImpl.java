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
package com.erp.finance.voucher.dao.hibernate;

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
import com.erp.finance.voucher.dao.FinVoucherModelLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelLineCO;

@Repository
public class FinVoucherModelLineDaoImpl implements FinVoucherModelLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(FinVoucherModelLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherModelLine> getDataObjects() {
        return this.basicDao.getDataAllObject(FinVoucherModelLine.class);
    }

    @Override
    public FinVoucherModelLine getDataObject(int id) {
        return (FinVoucherModelLine)this.basicDao.getDataObject(FinVoucherModelLine.class, id);
    }
    
    @Override
    public FinVoucherModelLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherModelLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getFinVoucherModelLineListByVoucherHeadCode(String voucherHeadCode) {
        String sql = "select l.* from fin_voucher_model_line l where voucher_head_code = :voucherheadcode order by l.voucher_line_id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", FinVoucherModelLine.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public void deleteFinVoucherModelLineByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_model_line where voucher_head_code = :voucherheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
