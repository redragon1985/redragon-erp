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
import com.erp.masterdata.customer.dao.MdCustomerLicenseDao;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerCO;
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;
import com.erp.masterdata.customer.dao.model.MdCustomerLicense;
import com.erp.masterdata.customer.dao.model.MdCustomerLicenseCO;
import com.erp.masterdata.material.dao.MdMaterialDao;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.dao.model.MdMaterialCO;
import com.erp.masterdata.project.dao.MdProjectDao;
import com.erp.masterdata.project.dao.model.MdProject;
import com.erp.masterdata.project.dao.model.MdProjectCO;
import com.erp.masterdata.subject.dao.MdFinanceSubjectDao;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.vendor.dao.MdVendorContactDao;
import com.erp.masterdata.vendor.dao.MdVendorDao;
import com.erp.masterdata.vendor.dao.MdVendorLicenseDao;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorCO;
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.masterdata.vendor.dao.model.MdVendorLicense;
import com.erp.masterdata.vendor.dao.model.MdVendorLicenseCO;
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
    @Autowired
    private MdCustomerLicenseDao mdCustomerLicenseDao;
    @Autowired
    private MdVendorLicenseDao mdVendorLicenseDao;
    
    
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.CUSTOMER_CACHE_KEY)
    public Map<String, String> getCustomerMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        MdCustomerCO mdCustomerCO = new MdCustomerCO();
        mdCustomerCO.setStatus("Y");
        mdCustomerCO.setApproveStatus("APPROVE");
        List<MdCustomer> list = this.mdCustomerDao.getDataObjects(mdCustomerCO);
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
        
        MdMaterialCO mdMaterialCO = new MdMaterialCO();
        mdMaterialCO.setStatus("Y");
        mdMaterialCO.setApproveStatus("APPROVE");
        List<MdMaterial> list = this.mdMaterialDao.getDataObjects(mdMaterialCO);
        for(MdMaterial mdMaterial: list) {
            map.put(mdMaterial.getMaterialCode(), mdMaterial.getMaterialName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.MATERIAL_MATERIAL_CACHE_KEY)
    public Map<String, String> getMaterialForMaterialMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        MdMaterialCO mdMaterialCO = new MdMaterialCO();
        mdMaterialCO.setMaterialType("MATERIAL");
        mdMaterialCO.setStatus("Y");
        mdMaterialCO.setApproveStatus("APPROVE");
        
        List<MdMaterial> list = this.mdMaterialDao.getDataObjects(mdMaterialCO);
        for(MdMaterial mdMaterial: list) {
            map.put(mdMaterial.getMaterialCode(), mdMaterial.getMaterialName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.MATERIAL_MATTER_CACHE_KEY)
    public Map<String, String> getMaterialForMatterMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        MdMaterialCO mdMaterialCO = new MdMaterialCO();
        mdMaterialCO.setMaterialType("MATTER");
        mdMaterialCO.setStatus("Y");
        mdMaterialCO.setApproveStatus("APPROVE");
        
        List<MdMaterial> list = this.mdMaterialDao.getDataObjects(mdMaterialCO);
        for(MdMaterial mdMaterial: list) {
            map.put(mdMaterial.getMaterialCode(), mdMaterial.getMaterialName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.PROJECT_CACHE_KEY)
    public Map<String, String> getProjectMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        MdProjectCO mdProjectCO = new MdProjectCO();
        mdProjectCO.setStatus("Y");
        mdProjectCO.setApproveStatus("APPROVE");
        List<MdProject> list = this.mdProjectDao.getDataObjects(mdProjectCO);
        for(MdProject mdProject: list) {
            map.put(mdProject.getProjectCode(), mdProject.getProjectName());
        }
        
        return map;
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=MasterDataParam.VENDOR_CACHE_KEY)
    public Map<String, String> getVendorMap() {
        Map<String, String> map = new HashMap<String, String>();
        
        MdVendorCO mdVendorCO = new MdVendorCO();
        mdVendorCO.setStatus("Y");
        mdVendorCO.setApproveStatus("APPROVE");
        List<MdVendor> list = this.mdVendorDao.getDataObjects(mdVendorCO);
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
        
        //获取客户营业执照
        MdCustomerLicenseCO mdCustomerLicenseCO = new MdCustomerLicenseCO();
        mdCustomerLicenseCO.setCustomerCode(customerCode);
        List<MdCustomerLicense> mdCustomerLicenseList = this.mdCustomerLicenseDao.getDataObjects(mdCustomerLicenseCO);
        //设置营业执照
        if(mdCustomerLicenseList!=null&&mdCustomerLicenseList.size()>0) {
            mdCustomer.setMdCustomerLicense(mdCustomerLicenseList.get(0));
        }
        
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
        
        //获取供应商营业执照
        MdVendorLicenseCO mdVendorLicenseCO = new MdVendorLicenseCO();
        mdVendorLicenseCO.setVendorCode(vendorCode);
        List<MdVendorLicense> mdVendorLicenseList = this.mdVendorLicenseDao.getDataObjects(mdVendorLicenseCO);
        //设置营业执照
        if(mdVendorLicenseList!=null&&mdVendorLicenseList.size()>0) {
            mdVendor.setMdVendorLicense(mdVendorLicenseList.get(0));
        }
        
        return mdVendor;
    }

}
