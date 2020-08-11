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
package com.erp.permission.dao;

import com.framework.api.DaoCRUDIF;

import java.util.List;

import com.erp.permission.dao.model.SysUserRoleR;
import com.erp.permission.dao.model.SysUserRoleRCO;

public interface SysUserRoleRDao extends DaoCRUDIF<SysUserRoleR, SysUserRoleRCO>{
    
    //删除用户角色关联数据（根据用户名）
    public abstract void deleteSysUserRoleRByUsername(String username);
    
}