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
package com.erp.permission.service;

import com.framework.api.DaoCRUDIF;

import java.util.List;

import com.erp.permission.dao.model.SysRole;
import com.erp.permission.dao.model.SysRoleCO;

public interface SysRoleService extends DaoCRUDIF<SysRole, SysRoleCO> {
    
    //获取角色列表（根据数据状态）
    public abstract List<SysRole> getSysRoleListByStatus(String status);
    
    //获取用户关联的角色列表
    public abstract List<SysRole> getRelateSysRoleListByUsername(String username);
    
    //角色是否存在关联数据
    public abstract boolean isExistRelateDataForSysRole(String roleCode);
    
}
