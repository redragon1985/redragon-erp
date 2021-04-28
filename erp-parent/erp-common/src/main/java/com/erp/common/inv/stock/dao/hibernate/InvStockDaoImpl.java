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
 * @description InvStockDaoImpl.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.inv.stock.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.BasicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.common.inv.stock.dao.InvStockDao;


/**
 * @description
 * @date 2020年11月19日
 * @author dongbin
 * 
 */
@Repository("invStockDaoCommon")
public class InvStockDaoImpl implements InvStockDao {
    
    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;

    @Override
    public Double getMaterialStockNumber(String materialCode) {
        String sql = "select sum(s.stock_number) from inv_stock s where s.material_code = :materialCode and s.status = 'Y'";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("materialCode", materialCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list!=null&&list.size()>0) {
            try {
                return Double.valueOf(String.valueOf(list.get(0)));
            }catch(Exception e) {
            }
        }
        
        return 0D;
    }

}
