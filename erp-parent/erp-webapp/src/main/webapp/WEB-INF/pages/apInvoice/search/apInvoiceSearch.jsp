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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="searchDiv" class="ibox " style="display: none;">
	<div class="ibox-title collapse-link" style="cursor: pointer;"
		title="展开/收起查询条件">

		<h5>查询条件</h5>
		<div class="ibox-tools"></div>

	</div>
	
	<form action="web/apInvoiceHead/getApInvoiceHeadList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="invoiceHeadCode">发票编码</label> 
					<input type="text" id="invoiceHeadCode" name="invoiceHeadCode" value="${param.invoiceHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="invoiceSourceType">来源</label> 
					<select class="form-control" name="invoiceSourceType" id="invoiceSourceType">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.paySourceTypeMap}" var="paySourceType">
                    		<option value="${paySourceType.key}">${paySourceType.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="invoiceSourceHeadCode">采购订单编码</label> 
					<input type="text" id="invoiceSourceHeadCode" name="invoiceSourceHeadCode" value="${param.invoiceSourceHeadCode}" class="form-control">
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="receiver">供应商</label> 
                    <select class="select2 form-control" name="receiver" id="receiver">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.vendorMap}" var="vendor">
                    		<option value="${vendor.key}">${vendor.value}</option>
                    	</c:forEach>
                    </select>					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> 
					<select class="form-control" name="status" id="status">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.payStatusMap}" var="payStatus">
                    		<option value="${payStatus.key}">${payStatus.value}</option>
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
	//设置paySourceType默认值
	if("${param.invoiceSourceType}"!=""){
		$("#invoiceSourceType").val("${param.invoiceSourceType}");
	}
	//设置receiver默认值
	if("${param.receiver}"!=""){
		$("#receiver").val("${param.receiver}");
	}
	
	//初始化select2
	$('.select2').select2({width: "100%"});
});
</script>
