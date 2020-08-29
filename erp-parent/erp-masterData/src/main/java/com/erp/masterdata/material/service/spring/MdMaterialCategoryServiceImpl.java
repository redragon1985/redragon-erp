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
package com.erp.masterdata.material.service.spring;

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
import com.erp.masterdata.material.dao.MdMaterialCategoryDao;
import com.erp.masterdata.material.dao.model.MdMaterialCategory;
import com.erp.masterdata.material.dao.model.MdMaterialCategoryCO;
import com.erp.masterdata.material.service.MdMaterialCategoryService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdMaterialCategoryServiceImpl implements MdMaterialCategoryService {

    //注入Dao
    @Autowired
    private MdMaterialCategoryDao mdMaterialCategoryDao;
    
    @Override
    public void insertDataObject(MdMaterialCategory obj) {
        this.mdMaterialCategoryDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(MdMaterialCategory obj) {
        this.mdMaterialCategoryDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdMaterialCategory obj) {
        this.mdMaterialCategoryDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdMaterialCategory obj) {
        this.mdMaterialCategoryDao.deleteDataObject(obj);
    }

    @Override
    public List<MdMaterialCategory> getDataObjects() {
        return this.mdMaterialCategoryDao.getDataObjects();
    }

    @Override
    public MdMaterialCategory getDataObject(int id) {
        return this.mdMaterialCategoryDao.getDataObject(id);
    }

    @Override
    public MdMaterialCategory getDataObject(String code) {
        return this.mdMaterialCategoryDao.getDataObject(code);
    }

    @Override
    public List<MdMaterialCategory> getDataObjects(MdMaterialCategoryCO paramObj) {
        return this.mdMaterialCategoryDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdMaterialCategory> getDataObjects(Pages pages) {
        return this.mdMaterialCategoryDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdMaterialCategory> getDataObjects(Pages pages, MdMaterialCategoryCO paramObj) {
        return this.mdMaterialCategoryDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdMaterialCategoryCO paramObj) {
        return this.mdMaterialCategoryDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdMaterialCategory> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdMaterialCategoryCO paramObj) {
        return this.mdMaterialCategoryDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getChildMaterialCategoryNum(Integer categoryId) {
        return this.mdMaterialCategoryDao.getChildMaterialCategoryNum(categoryId);
    }
    
}