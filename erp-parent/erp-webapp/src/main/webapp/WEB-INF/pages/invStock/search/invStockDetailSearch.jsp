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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="searchDiv" class="ibox ">
	<div class="ibox-title collapse-link" style="cursor: pointer;"
		title="展开/收起查询条件">

		<h5>查询条件</h5>
		<div class="ibox-tools"></div>

	</div>
	
	<form id="searchForm" action="web/invStock/getInvStockDetailList?warehouseCode=${param.warehouseCode}" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<select class="select2 form-control" name="materialCode" id="materialCode">
                    	<option value="" selected="selected">请选择物料...</option>
                        <c:forEach items="${requestScope.materialMap}" var="material">
                    		<option value="${material.key}">${material.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
			<%-- 
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="retainFlag">保留标识</label> 
					<select name="retainFlag" id="retainFlag" class="form-control">
						<option value="" selected="">请选择</option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</div>
			</div>
			--%>
		</div>	
		<%-- 
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="billType">关联单据类型</label> 
					<select name="billType" id="billType" class="form-control">
						<option value="" selected="">请选择</option>
						<c:forEach items="${requestScope.stockBillTypeMap}" var="stockBillType">
                    		<option value="${stockBillType.key}">${stockBillType.value}</option>
                    	</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="billHeadCode">关联单据编码</label>
					<input type="text" id="billHeadCode" name="billHeadCode" value="${param.billHeadCode}" class="form-control">
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
		--%>
	</div>
	
	<%-- 隐藏字段 --%>
	<input type="hidden" name="page" value="1">
	</form>
</div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<script>
$(document).ready(function(){
	//设置materialCode默认值
	if("${param.materialCode}"!=""){
		$("#materialCode").val("${param.materialCode}");
	}
	
	//初始化选择框
	$('.select2').select2({width: "100%"});
	
	/*
	//设置retainFlag默认值
	if("${param.retainFlag}"!=""){
		$("#retainFlag").val("${param.retainFlag}");
	}
	
	//设置billType默认值
	if("${param.billType}"!=""){
		$("#billType").val("${param.billType}");
	}
	*/
	
	$("#materialCode").change(function(){
		if($("#materialCode").val()!=""){
			$("#searchForm").submit();
		}
	});
});
</script>
