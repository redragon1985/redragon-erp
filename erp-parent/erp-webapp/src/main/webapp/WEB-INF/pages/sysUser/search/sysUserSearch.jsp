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

<div id="searchDiv" class="ibox " style="display: none;">
	<div class="ibox-title collapse-link" style="cursor: pointer;"
		title="展开/收起查询条件">

		<h5>查询条件</h5>
		<div class="ibox-tools"></div>

	</div>
	
	<form action="web/sysUser/getSysUserList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="product_name">用户名</label> 
					<input type="text" id="username" name="username" value="${param.username}" class="form-control">
				</div>
			</div>
			<div class="col-sm-6">
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
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="date_added">创建开始日期</label>
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="createdDateStart" name="createdDateStart" type="text" class="form-control" value="${param.createdDateStart}" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label class="control-label" for="date_modified">创建结束日期</label>
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="createdDateEnd" name="createdDateEnd" type="text" class="form-control" value="${param.createdDateEnd}" autocomplete="off">
					</div>
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

<script>
$(document).ready(function(){
	//设置日期插件
	$('#createdDateStart').datepicker({
		todayBtn : "linked",
		keyboardNavigation : true,
		forceParse : false,
		calendarWeeks : false,
		autoclose : true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
	});
	//设置日期插件	
	$('#createdDateEnd').datepicker({
		todayBtn : "linked",
		keyboardNavigation : true,
		forceParse : false,
		calendarWeeks : false,
		autoclose : true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
	});

	//设置状态默认值
	var status = "${param.status}";
	if(status!=""){
		$("#status").val(status);
	}
	
});
</script>
