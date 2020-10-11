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
 * @description InvOutputHeadDaoImpl.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.inv.output.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.common.inv.output.dao.InvOutputHeadDao;
import com.framework.dao.DaoSupport;

/**
 * @description
 * @date 2020年10月8日
 * @author dongbin
 * 
 */
@Repository("invOutputHeadDaoCommon")
public class InvOutputHeadDaoImpl implements InvOutputHeadDao {
    
    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public boolean isExistInvOutputHeadRelateSO(String headCode) {
        String sql = "select count(*) from inv_output_head h where h.output_source_head_code = :headCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headCode", headCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            int count = this.daoSupport.convertSQLCount(list.get(0));
            if(count>0) {
                return true;
            }
        }
        
        return false;
    }

}
