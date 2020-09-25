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
package com.erp.inv.input.service.spring;

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
import com.erp.inv.input.dao.InvInputLineDao;
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.input.service.InvInputLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvInputLineServiceImpl implements InvInputLineService {

    //注入Dao
    @Autowired
    private InvInputLineDao invInputLineDao;
    
    @Override
    public void insertDataObject(InvInputLine obj) {
        this.invInputLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvInputLine obj) {
        this.invInputLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvInputLine obj) {
        this.invInputLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvInputLine obj) {
        this.invInputLineDao.deleteDataObject(obj);
    }

    @Override
    public List<InvInputLine> getDataObjects() {
        return this.invInputLineDao.getDataObjects();
    }

    @Override
    public InvInputLine getDataObject(int id) {
        return this.invInputLineDao.getDataObject(id);
    }

    @Override
    public InvInputLine getDataObject(String code) {
        return this.invInputLineDao.getDataObject(code);
    }

    @Override
    public List<InvInputLine> getDataObjects(InvInputLineCO paramObj) {
        return this.invInputLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvInputLine> getDataObjects(Pages pages) {
        return this.invInputLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvInputLine> getDataObjects(Pages pages, InvInputLineCO paramObj) {
        return this.invInputLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvInputLineCO paramObj) {
        return this.invInputLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvInputLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvInputLineCO paramObj) {
        return this.invInputLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<InvInputLine> getInvInputLineListByInputHeadCode(Pages pages, InvInputLineCO paramObj) {
        return this.invInputLineDao.getInvInputLineListByInputHeadCode(pages, paramObj);
    }
    
    @Override
    public void deleteInvInputLineByInputHeadCode(String inputHeadCode) {
        this.invInputLineDao.deleteInvInputLineByInputHeadCode(inputHeadCode);
    }
    
    @Override
    public Double getInputQuantityByPoLineCode(String poLineCode) {
        return this.invInputLineDao.getInputQuantityByPoLineCode(poLineCode);
    }
    
    @Override
    public List<InvInputLine> getInvInputLineListByInputHeadCode(String inputHeadCode) {
        return this.invInputLineDao.getInvInputLineListByInputHeadCode(inputHeadCode);
    }
    
}