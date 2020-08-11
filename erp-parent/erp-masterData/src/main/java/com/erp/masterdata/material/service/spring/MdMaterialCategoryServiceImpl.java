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