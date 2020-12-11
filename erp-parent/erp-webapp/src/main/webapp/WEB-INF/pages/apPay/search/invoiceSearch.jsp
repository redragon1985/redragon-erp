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

<div id="searchDiv" class="ibox " style="display: none; margin-bottom: 0px;">
	<%-- 
	<div class="ibox-title collapse-link" style="cursor: pointer;" title="展开/收起查询条件">

		<h5>查询条件</h5>
		<div class="ibox-tools"></div>

	</div>
	--%>
	
	<div class="ibox-content m-b-sm border-bottom" style="padding-bottom: 0px; padding-top: 5px;">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="control-label" for="invoiceHeadCodeLS">发票编码</label> 
					<input type="text" id="invoiceHeadCodeLS" value="${param.invoiceHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="control-label" for="invoiceSourceHeadCodeLS">订单编码</label> 
					<input type="text" id="invoiceSourceHeadCodeLS" value="${param.invoiceSourceHeadCode}" class="form-control">
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="amountLS">发票金额</label> 
                    <input type="text" id="amountLS" value="${param.amount}" class="form-control">				
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="invoiceDateLS">发票日期</label> 
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="invoiceDateLS" type="text" class="form-control" value="${param.invoiceDate}" autocomplete="off">
					</div>
				</div>
			</div>
		</div>
		<%-- 
		<div class="row">
			<div class="col-sm-12 text-left" style="padding-bottom: 0px;">
				<div class="form-group">
					<button type="submit" class="btn btn-w-m btn-default">查询</button>
				</div>
			</div>
		</div>
		--%>
	</div>
	
	<%-- 隐藏字段 --%>
	<input type="hidden" name="page" value="1">
</div>

<!-- Chosen -->
<script src="js/plugins/chosen/chosen.jquery.js"></script>

<script>
$(document).ready(function(){

	//设置日期插件
	$('#invoiceDateLS').datepicker({
		todayBtn : "linked",
		keyboardNavigation : true,
		forceParse : true,
		calendarWeeks : false,
		autoclose : true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
	});

	//初始化chosen-select
	$('.chosen-select').chosen({width: "100%"});
	
	//查询按钮
	$("#searchConfirmButton").click(function(){
		getSelectApInvoiceModal();
	});
});
</script>
