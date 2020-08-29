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
import com.erp.inv.check.dao.InvStockCheckLineDao;
import com.erp.inv.check.dao.model.InvStockCheckLine;
import com.erp.inv.check.dao.model.InvStockCheckLineCO;
import com.erp.inv.check.service.InvStockCheckLineService;
import com.erp.inv.input.dao.model.InvInputLine;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvStockCheckLineServiceImpl implements InvStockCheckLineService {

    //注入Dao
    @Autowired
    private InvStockCheckLineDao invStockCheckLineDao;
    
    @Override
    public void insertDataObject(InvStockCheckLine obj) {
        this.invStockCheckLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvStockCheckLine obj) {
        this.invStockCheckLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStockCheckLine obj) {
        this.invStockCheckLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvStockCheckLine obj) {
        this.invStockCheckLineDao.deleteDataObject(obj);
    }

    @Override
    public List<InvStockCheckLine> getDataObjects() {
        return this.invStockCheckLineDao.getDataObjects();
    }

    @Override
    public InvStockCheckLine getDataObject(int id) {
        return this.invStockCheckLineDao.getDataObject(id);
    }

    @Override
    public InvStockCheckLine getDataObject(String code) {
        return this.invStockCheckLineDao.getDataObject(code);
    }

    @Override
    public List<InvStockCheckLine> getDataObjects(InvStockCheckLineCO paramObj) {
        return this.invStockCheckLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvStockCheckLine> getDataObjects(Pages pages) {
        return this.invStockCheckLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvStockCheckLine> getDataObjects(Pages pages, InvStockCheckLineCO paramObj) {
        return this.invStockCheckLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCheckLineCO paramObj) {
        return this.invStockCheckLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvStockCheckLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvStockCheckLineCO paramObj) {
        return this.invStockCheckLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<InvStockCheckLine> getInvStockCheckLineListByHeadCode(String headCode) {
        return this.invStockCheckLineDao.getInvStockCheckLineListByHeadCode(headCode);
    }
    
    @Override
    public void deleteInvStockCheckLineByHeadCode(String headCode) {
        this.invStockCheckLineDao.deleteInvStockCheckLineByHeadCode(headCode);
    }
    
}