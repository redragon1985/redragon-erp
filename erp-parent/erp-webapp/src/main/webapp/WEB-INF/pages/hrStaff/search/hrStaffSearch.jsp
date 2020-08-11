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
	
	<form action="web/hrStaff/getHrStaffList" method="get">
	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="staffCode">职员工号</label> 
					<input type="text" id="staffCode" name="staffCode" value="${param.staffCode}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="staffName">职员名称</label> 
					<input type="text" id="staffName" name="staffName" value="${param.staffName}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="staffStatus">职员状态</label> 
					<select name="staffStatus" id="staffStatus" class="form-control">
						<option value="" selected="">请选择</option>
						<option value="WORK">在职</option>
						<option value="LEAVE">离职</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="username">关联用户名</label> 
					<input type="text" id="username" name="username" value="${param.username}" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="staffSex">性别</label> 
					<select name="staffSex" id="staffSex" class="form-control">
						<option value="" selected="">请选择</option>
						<option value="MALE">男</option>
						<option value="FEMALE">女</option>
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

<script>
$(document).ready(function(){
	//设置状态默认值
	var status = "${param.staffStatus}";
	if(status!=""){
		$("#staffStatus").val(status);
	}
	
	//设置性别默认值
	var sex = "${param.staffSex}";
	if(sex!=""){
		$("#staffSex").val(sex);
	}
});
</script>
