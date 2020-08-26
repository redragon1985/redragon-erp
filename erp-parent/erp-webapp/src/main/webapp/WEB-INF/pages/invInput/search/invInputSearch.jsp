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
	
	<form action="web/invInputHead/getInvInputHeadList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="payHeadCode">入库单编码</label> 
					<input type="text" id="inputHeadCode" name="inputHeadCode" value="${param.inputHeadCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="inputSourceType">来源</label> 
					<select class="form-control" name="inputSourceType" id="inputSourceType">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.inputSourceTypeMap}" var="inputSourceType">
                    		<option value="${inputSourceType.key}">${inputSourceType.value}</option>
                    	</c:forEach>
                    </select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="inputSourceHeadCode">来源编码</label> 
					<input type="text" id="inputSourceHeadCode" name="inputSourceHeadCode" value="${param.inputSourceHeadCode}" class="form-control">
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="inputType">入库类型</label> 
                    <select class="select2 form-control" name="inputType" id="inputType">
                    	<option value="" selected="selected">请选择...</option>
                    	<c:forEach items="${requestScope.inputTypeMap}" var="inputType">
                    		<option value="${inputType.key}">${inputType.value}</option>
                    	</c:forEach>
                    </select>					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="inputDate">入库时间</label> 
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="inputDate" name="inputDate" type="text" class="form-control" value="${param.inputDate}" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> 
					<select class="form-control" name="status" id="status">
                    	<option value="" selected="selected">请选择...</option>
                        <c:forEach items="${requestScope.inputStatusMap}" var="inputStatus">
                    		<option value="${inputStatus.key}">${inputStatus.value}</option>
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
	if("${param.inputSourceType}"!=""){
		$("#inputSourceType").val("${param.inputSourceType}");
	}
	//设置inputType默认值
	if("${param.inputType}"!=""){
		$("#inputType").val("${param.inputType}");
	}
	
	//初始化select2
	$('.select2').select2({width: "100%"});
	
	//设置日期插件
	$('#inputDate').datepicker({
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
