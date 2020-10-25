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
 * @description ProcedureDaoImpl.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.proc.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.common.proc.dao.ProcedureDao;
import com.framework.dao.DaoSupport;
import com.framework.dao.model.ProcedureParam;

/**
 * @description
 * @date 2020年10月13日
 * @author dongbin
 * 
 */
@Repository("procedureDaoCommon")
public class ProcedureDaoImpl implements ProcedureDao {
    
    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;

    @Override
    public int saveHistoryData(String tableName, String primaryKey, String primarykeyValue) {
        //初始化参数
        List<ProcedureParam> procedureParams = this.initProcedureParam(tableName, primaryKey, primarykeyValue);
        
        try {
            String sql="{call save_history_data_proc(?,?,?,?)}";
            procedureParams = this.daoSupport.executeProcedures(sql, procedureParams);
            
            for (int i = 0; i < procedureParams.size(); i++) {
                ProcedureParam procedureParam=procedureParams.get(i);
                if (procedureParam.getParamInoutType().equals("out")) {
                    return Integer.parseInt(String.valueOf(procedureParam.getParamValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * 
     * @description 初始化存储过程参数
     * @date 2020年10月13日
     * @author dongbin
     * @param tableName
     * @param primaryKey
     * @param primarykeyValue
     * @return
     * @return List<ProcedureParam>
     *
     */
    private List<ProcedureParam> initProcedureParam(String tableName, String primaryKey, String primarykeyValue) {
        List<ProcedureParam> list = new ArrayList<ProcedureParam>();
        
        ProcedureParam procetn=new ProcedureParam();
        procetn.setParamType(String.class);
        procetn.setParamValue(tableName);
        procetn.setParamInoutType(ProcedureParam.IN);
        list.add(procetn);
        
        ProcedureParam procetk=new ProcedureParam();
        procetk.setParamType(String.class);
        procetk.setParamValue(primaryKey);
        procetk.setParamInoutType(ProcedureParam.IN);
        list.add(procetk);
        
        ProcedureParam procetv=new ProcedureParam();
        procetv.setParamType(String.class);
        procetv.setParamValue(primarykeyValue);
        procetv.setParamInoutType(ProcedureParam.IN);
        list.add(procetv);
        
        ProcedureParam proceov=new ProcedureParam();
        proceov.setParamType(String.class);
        proceov.setParamValue("");
        proceov.setParamInoutType(ProcedureParam.OUT);
        list.add(proceov);
        
        return list;
    }

}
