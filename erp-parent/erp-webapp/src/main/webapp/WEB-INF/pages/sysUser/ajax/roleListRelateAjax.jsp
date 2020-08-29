<%--

    Copyright 2020-2021 redragon.dongbin
 
    This file is part of redragon-erp/赤龙ERP.

    redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
	
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="col-sm-12">
	<span class="text-info"><label class="col-form-label"><strong>角色选择</strong></label></span>
		<select id="roleCode" name="roleCode" class="form-control dual_select" multiple>
			<c:forEach items="${sysRoleList}" var="data">
				<option value="${data.roleCode}">${data.roleName}</option>
			</c:forEach>
			<c:forEach items="${sysRoleRelateList}" var="data">
				<option selected value="${data.roleCode}">${data.roleName}</option>
			</c:forEach>
		</select>
</div>
<div class="hr-line-dashed"></div>	

<script>
$(document).ready(function(){
	$('.dual_select').bootstrapDualListbox({
        selectorMinimalHeight: 160,
        nonSelectedListLabel: '未选择的角色',
	    selectedListLabel: '已选择的角色',
	    filterPlaceHolder: '输入查询条件...',
	    moveSelectedLabel: "添加",
	    moveAllLabel: '添加所有',
	    removeSelectedLabel: "移除",
	    removeAllLabel: '移除所有',
	    filterTextClear: "显示所有",
	    infoText: '共{0}个角色',
	    infoTextFiltered: '<span class="label label-warning">搜索到</span>&nbsp;{0}个角色 ,共{1}个角色',
	    infoTextEmpty: '列表为空',
    });
});
</script>