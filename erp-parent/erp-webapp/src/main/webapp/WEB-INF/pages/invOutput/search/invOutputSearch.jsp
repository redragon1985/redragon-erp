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
	
	<form action="web/invOutputHead/getInvOutputHeadList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="outputHeadCode">出库单编码</label> 
					<input type="text" id="outputHeadCode" name="outputHeadCode" value="${param.outputHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="outputSourceType">来源</label> 
					<select class="form-control" name="outputSourceType" id="outputSourceType">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.outputSourceTypeMap}" var="outputSourceType">
                    		<option value="${outputSourceType.key}">${outputSourceType.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="outputSourceHeadCode">来源编码</label> 
					<input type="text" id="outputSourceHeadCode" name="outputSourceHeadCode" value="${param.outputSourceHeadCode}" class="form-control">
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="outputType">出库类型</label> 
                    <select class="select2 form-control" name="outputType" id="outputType">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.outputTypeMap}" var="outputType">
                    		<option value="${outputType.key}">${outputType.value}</option>
                    	</c:forEach>
                    </select>					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="outputDate">出库时间</label> 
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="outputDate" name="outputDate" type="text" class="form-control" value="${param.outputDate}" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> 
					<select class="form-control" name="status" id="status">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.outputStatusMap}" var="outputStatus">
                    		<option value="${outputStatus.key}">${outputStatus.value}</option>
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
	//设置inputSourceType默认值
	if("${param.outputSourceType}"!=""){
		$("#outputSourceType").val("${param.outputSourceType}");
	}
	//设置inputType默认值
	if("${param.outputType}"!=""){
		$("#outputType").val("${param.outputType}");
	}
	
	//初始化select2
	$('.select2').select2({width: "100%"});
	
	//设置日期插件
	$('#outputDate').datepicker({
		todayBtn : "linked",
		keyboardNavigation : true,
		forceParse : false,
		calendarWeeks : false,
		autoclose : true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
	});
});
</script>
