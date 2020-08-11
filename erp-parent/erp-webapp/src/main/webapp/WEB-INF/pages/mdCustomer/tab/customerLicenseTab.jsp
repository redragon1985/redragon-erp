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
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div id="ltab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">
		<form id="lform" action="web/mdCustomerLicense/editMdCustomerLicense" method="post">
		<fieldset>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>营业执照号:</label>
				<div class="col-sm-10">
					<input id="licenseNumber" name="licenseNumber" type="text" class="form-control" value="${requestScope.mdCustomerLicense.licenseNumber}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>法人代表:</label>
				<div class="col-sm-10">
					<input id="legalPerson" name="legalPerson" type="text" class="form-control" value="${requestScope.mdCustomerLicense.legalPerson}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>公司类型:</label>
				<div class="col-sm-10">
					<select class="form-control" name="companyType" id="companyType">
                        <option value="yxzr" selected="selected">有限责任公司</option>
                        <option value="gfyx">股份有限责任公司</option>
                        <option value="grdz">个人独资企业</option>
                        <option value="hh">合伙企业</option>
                    </select>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">经营范围:</label>
				<div class="col-sm-10">
					<textarea id="businessScope" name="businessScope" rows="" cols="" class="form-control">${requestScope.mdCustomerLicense.businessScope}</textarea>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>成立日期:</label>
				<div class="col-sm-10">
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="startDate" name="startDate" type="text" class="form-control" value="${requestScope.mdCustomerLicense.startDate}" autocomplete="off">
					</div>
				</div>
				
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>状态:</label>
				<div class="col-sm-10">
					<select class="form-control" name="status" id="status">
                        <option value="Y" selected="selected">有效</option>
                        <option value="N">无效</option>
                    </select>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-12 col-sm-offset-2 text-right">
					<button class="ladda-button ladda-button-demo btn btn-info btn-lg"
						data-style="expand-right">
						&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
					</button>
				</div>
			</div>
		</fieldset>
		
		<input type="hidden" name="customerCode" value="${param.customerCode}">
		<input type="hidden" name="licenseId" value="${requestScope.mdCustomerLicense.licenseId}">
		<input type="hidden" name="createdDate" value="${requestScope.mdCustomerLicense.createdDate}">
		<input type="hidden" name="createdBy" value="${requestScope.mdCustomerLicense.createdBy}">
		
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	//初始化status
	if("${requestScope.mdCustomerLicense.status}"!=""){
		$("#status").val("${requestScope.mdCustomerLicense.status}");
	}
	//初始化companyType
	if("${requestScope.mdCustomerLicense.companyType}"!=""){
		$("#companyType").val("${requestScope.mdCustomerLicense.companyType}");
	}

	//设置日期插件
	$('#startDate').datepicker({
		todayBtn : "linked",
		keyboardNavigation : true,
		forceParse : false,
		calendarWeeks : false,
		autoclose : true,
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
	});
	
	var l = $('.ladda-button-demo').ladda();

	l.click(function() {
		$("#lform").valid();
		//l.ladda('stop');
	});

	$("#lform").validate({
		rules : {
			licenseNumber : {
				required : true,
			},
			legalPerson : {
				required : true,
			},
			/*
			businessScope : {
				required : true,
			},
			*/
			startDate : {
				required : true,
			},
		},
		submitHandler: function(form) {
			l.ladda('start');
	        form.submit();
	    }
	});
});
</script>
