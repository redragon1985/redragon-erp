<%--

	Copyright 2020-2021 redragon.dongbin

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="col-sm-12">
	<span class="text-info"><label class="col-form-label"><strong>权限选择</strong></label></span>
		<select id="authCode" name="authCode" class="form-control dual_select" multiple>
			<c:forEach items="${sysAuthList}" var="data">
				<option value="${data.authCode}">${data.authName}</option>
			</c:forEach>
			<c:forEach items="${sysAuthRelateList}" var="data">
				<option selected value="${data.authCode}">${data.authName}</option>
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