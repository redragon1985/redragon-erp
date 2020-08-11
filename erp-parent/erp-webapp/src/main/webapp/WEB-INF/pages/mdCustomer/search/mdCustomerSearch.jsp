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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="searchDiv" class="ibox " style="display: none;">
	<div class="ibox-title collapse-link" style="cursor: pointer;"
		title="展开/收起查询条件">

		<h5>查询条件</h5>
		<div class="ibox-tools"></div>

	</div>
	
	<form action="web/mdCustomer/getMdCustomerList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="product_name">客户编码</label> 
					<input type="text" id="customerCode" name="customerCode" value="${param.customerCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="product_name">客户名称</label> 
					<input type="text" id="customerName" name="customerName" value="${param.customerName}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> 
					<select name="status" id="status" class="form-control">
						<option value="" selected="">请选择</option>
						<option value="Y">有效</option>
						<option value="N">无效</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="customerType">客户类型</label> 
					<select name="customerType" id="customerType" class="form-control">
						<option value="" selected="">请选择</option>
						<option value="company">公司</option>
						<option value="person">个人</option>
					</select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="customerCity">所在城市</label> 
					<select name="customerCity" id="customerCity" class="select2 form-control">
						<option value="" selected="">请选择</option>
						<c:forEach items="${requestScope.cityMap}" var="city">
                       		<option value="${city.key}">${city.value}</option>
                       	</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-w-m btn-primary">查询</button>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 隐藏字段 --%>
	<input type="hidden" name="page" value="1">
	</form>
</div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<script>
$(document).ready(function(){
	//设置状态默认值
	if("${param.status}"!=""){
		$("#status").val("${param.status}");
	}
	//设置所在城市默认值
	if("${param.customerCity}"!=""){
		$("#customerCity").val("${param.customerCity}");
	}
	//设置客户类型默认值
	if("${param.customerType}"!=""){
		$("#customerType").val("${param.customerType}");
	}
	
	//初始化select2
	$('.select2').select2({width: "100%"});
});
</script>
