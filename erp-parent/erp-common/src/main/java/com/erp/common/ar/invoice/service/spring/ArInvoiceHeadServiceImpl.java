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
 * @description ArInvoiceHeadServiceImpl.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.ar.invoice.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.common.ar.invoice.dao.ArInvoiceHeadDao;
import com.erp.common.ar.invoice.service.ArInvoiceHeadService;

/**
 * @description
 * @date 2020年10月8日
 * @author dongbin
 * 
 */
@Service("arInvoiceHeadServiceCommon")
@Transactional(rollbackFor=Exception.class)
public class ArInvoiceHeadServiceImpl implements ArInvoiceHeadService {
    
    //注入Dao
    @Autowired
    @Qualifier("arInvoiceHeadDaoCommon")
    private ArInvoiceHeadDao arInvoiceHeadDao;
    
    @Override
    public boolean isExistArInvoiceRelateSO(String headCode) {
        return this.arInvoiceHeadDao.isExistArInvoiceRelateSO(headCode);
    }

}
