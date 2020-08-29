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
package com.erp.masterdata.common.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.masterdata.common.param.MasterDataParam;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.customer.dao.MdCustomerContactDao;
import com.erp.masterdata.customer.dao.MdCustomerDao;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;
import com.erp.masterdata.material.dao.MdMaterialDao;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.project.dao.MdProjectDao;
import com.erp.masterdata.project.dao.model.MdProject;
import com.erp.masterdata.subject.dao.MdFinanceSubjectDao;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.vendor.dao.MdVendorContactDao;
import com.erp.masterdata.vendor.dao.MdVendorDao;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.masterdata.vendor.service.MdVendorContactService;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;

/**
 * @description
 * @date 2020年7月16日
 * @author dongbin
 * 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MasterDataCommonServiceImpl implements MasterDataCommonService {
    
    //注入Dao
    @Autowired
    private MdCustomerDao mdCustomerDao;
    @Autowired
    private MdVendorDao mdVendorDao;
    @Autowired
    private MdMaterialDao mdMaterialDao;
    @Autowired
    private MdProjectDao mdProjectDao;
    @Autowired
    private MdFinanceSubjectDao mdFinanceSubjectDao;
    @Autowired
    private MdCustomerContactDao mdCustomerContactDao;
    @Autowired
    private MdVendorContactDao mdVendorContactDao;
    
    
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.CUSTOMER_CACHE_KEY)
    public Map<String, String> getCustomerMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdCustomer> list = this.mdCustomerDao.getDataObjects();
        for(MdCustomer mdCustomer: list) {
            map.put(mdCustomer.getCustomerCode(), mdCustomer.getCustomerName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.CUSTOMER_OWN_CACHE_KEY)
    public Map<String, String> getOwnCustomerMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdCustomer> list = this.mdCustomerDao.getMdCustomerListForOwn();
        for(MdCustomer mdCustomer: list) {
            map.put(mdCustomer.getCustomerCode(), mdCustomer.getCustomerName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.MATERIAL_CACHE_KEY)
    public Map<String, String> getMaterialMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdMaterial> list = this.mdMaterialDao.getDataObjects();
        for(MdMaterial mdMaterial: list) {
            map.put(mdMaterial.getMaterialCode(), mdMaterial.getMaterialName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.PROJECT_CACHE_KEY)
    public Map<String, String> getProjectMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdProject> list = this.mdProjectDao.getDataObjects();
        for(MdProject mdProject: list) {
            map.put(mdProject.getProjectCode(), mdProject.getProjectName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.VENDOR_CACHE_KEY)
    public Map<String, String> getVendorMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdVendor> list = this.mdVendorDao.getDataObjects();
        for(MdVendor mdVendor: list) {
            map.put(mdVendor.getVendorCode(), mdVendor.getVendorName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.VENDOR_OWN_CACHE_KEY)
    public Map<String, String> getOwnVendorMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdVendor> list = this.mdVendorDao.getMdVendorListForOwn();
        for(MdVendor mdVendor: list) {
            map.put(mdVendor.getVendorCode(), mdVendor.getVendorName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.SUBJECT_CACHE_KEY)
    public Map<String, String> getSubjectMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        List<MdFinanceSubject> list = this.mdFinanceSubjectDao.getDataObjects();
        for(MdFinanceSubject mdFinanceSubject: list) {
            map.put(mdFinanceSubject.getSubjectCode(), mdFinanceSubject.getSegmentDesc());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public MdMaterial getMdMaterialInfoCache(String materialCode) {
        return this.mdMaterialDao.getDataObject(materialCode);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public MdCustomer getMdCustomerInfoCache(String customerCode) {
        //获取客户信息
        MdCustomer mdCustomer = this.mdCustomerDao.getDataObject(customerCode);
        //获取客户联系人
        MdCustomerContactCO mdCustomerContactCO = new MdCustomerContactCO();
        mdCustomerContactCO.setCustomerCode(customerCode);
        List<MdCustomerContact> mdCustomerContactList = this.mdCustomerContactDao.getDataObjects(mdCustomerContactCO);
        //设置联系人
        mdCustomer.setMdCustomerContactList(mdCustomerContactList);
        
        return mdCustomer;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public MdVendor getMdVendorInfoCache(String vendorCode) {
        //获取供应商信息
        MdVendor mdVendor = this.mdVendorDao.getDataObject(vendorCode);
        //获取供应商联系人
        MdVendorContactCO mdVendorContactCO = new MdVendorContactCO();
        mdVendorContactCO.setVendorCode(vendorCode);
        List<MdVendorContact> mdVendorContactList = this.mdVendorContactDao.getDataObjects(mdVendorContactCO);
        //设置联系人
        mdVendor.setMdVendorContactList(mdVendorContactList);
        
        return mdVendor;
    }

}
