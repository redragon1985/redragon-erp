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
					<label class="control-label" for="soHeadCode">订单编码</label> 
					<input type="text" id="soHeadCode" name="soHeadCode" value="${param.soHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="control-label" for="soName">订单名称</label> 
					<input type="text" id="soName" name="soName" value="${param.soName}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="control-label" for="soType">订单类型</label> 
					<select class="form-control" name="soType" id="soType">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.soTypeMap}" var="soType">
                    		<option value="${soType.key}">${soType.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="customerCode">客户</label> 
                    <select class="chosen-select form-control" name="customerCode" id="customerCode">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.customerMap}" var="customer">
                    		<option value="${customer.key}">${customer.value}</option>
                    	</c:forEach>
                    </select>					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="projectCode">项目</label> 
					<select id="projectCode" name="projectCode" class="chosen-select form-control">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.projectMap}" var="project">
                    		<option value="${project.key}">${project.value}</option>
                    	</c:forEach>
                    </select>
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
	//设置状态默认值
	if("${param.status}"!=""){
		$("#status").val("${param.status}");
	}
	//设置soType默认值
	if("${param.soType}"!=""){
		$("#soType").val("${param.soType}");
	}
	//设置customerCode默认值
	if("${param.customerCode}"!=""){
		$("#customerCode").val("${param.customerCode}");
	}
	//设置projectCode默认值
	if("${param.projectCode}"!=""){
		$("#projectCode").val("${param.projectCode}");
	}
	
	//初始化chosen-select
	$('.chosen-select').chosen({width: "100%"});
	
	//查询按钮
	$("#searchConfirmButton").click(function(){
		getSelectSOModal();
	});
});
</script>
