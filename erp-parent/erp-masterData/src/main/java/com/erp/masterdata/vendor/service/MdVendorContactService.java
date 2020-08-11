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
package com.erp.masterdata.vendor.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.util.List;

import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;

public interface MdVendorContactService extends DaoCRUDIF<MdVendorContact, MdVendorContactCO> {
    
    //查询供应商的联系人数量
    public abstract int getContactCountByVendorCode(String vendorCode);
    
    //查询供应商的联系人列表(分页)
    public abstract List<MdVendorContact> getContactListByVendorCode(Pages pages, MdVendorContactCO paramObj);
    
}
