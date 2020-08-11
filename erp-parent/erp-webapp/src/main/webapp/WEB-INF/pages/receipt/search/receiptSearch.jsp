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
	
	<form action="web/receiptHead/getReceiptHeadList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="receiptHeadCode">收款单编码</label> 
					<input type="text" id="receiptHeadCode" name="receiptHeadCode" value="${param.receiptHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="receiptSourceType">来源</label> 
					<select class="form-control" name="receiptSourceType" id="receiptSourceType">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.receiptSourceTypeMap}" var="receiptSourceType">
                    		<option value="${receiptSourceType.key}">${receiptSourceType.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="receiptSourceHeadCode">来源编码</label> 
					<input type="text" id="receiptSourceHeadCode" name="receiptSourceHeadCode" value="${param.receiptSourceHeadCode}" class="form-control">
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="payer">付款方</label> 
                    <select class="select2 form-control" name="payer" id="payer">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.customerMap}" var="customer">
                    		<option value="${customer.key}">${customer.value}</option>
                    	</c:forEach>
                    </select>					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="preReceiptFlag">预收款标识</label> 
					<select class="form-control" name="preReceiptFlag" id="preReceiptFlag">
                    	<option value="" selected="selected">请选择...</option>
                        <option value="Y">是</option>
                        <option value="N">否</option>
                    </select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> 
					<select class="form-control" name="status" id="status">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.receiptStatusMap}" var="receiptStatus">
                    		<option value="${receiptStatus.key}">${receiptStatus.value}</option>
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
	//设置receiptSourceType默认值
	if("${param.receiptSourceType}"!=""){
		$("#receiptSourceType").val("${param.receiptSourceType}");
	}
	//设置payer默认值
	if("${param.payer}"!=""){
		$("#payer").val("${param.payer}");
	}
	//设置prepayFlag默认值
	if("${param.preReceiptFlag}"!=""){
		$("#preReceiptFlag").val("${param.preReceiptFlag}");
	}
	
	//初始化select2
	$('.select2').select2({width: "100%"});
});
</script>
