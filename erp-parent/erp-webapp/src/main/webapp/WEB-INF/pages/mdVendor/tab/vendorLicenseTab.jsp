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
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div id="ltab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">
		<form id="lform" action="web/mdVendorLicense/editMdVendorLicense" method="post">
		<fieldset>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>营业执照号:</label>
				<div class="col-sm-10">
					<input id="licenseNumber" name="licenseNumber" type="text" class="form-control" value="${requestScope.mdVendorLicense.licenseNumber}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>法人代表:</label>
				<div class="col-sm-10">
					<input id="legalPerson" name="legalPerson" type="text" class="form-control" value="${requestScope.mdVendorLicense.legalPerson}">
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
					<textarea id="businessScope" name="businessScope" rows="" cols="" class="form-control">${requestScope.mdVendorLicense.businessScope}</textarea>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>成立日期:</label>
				<div class="col-sm-10">
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<input id="startDate" name="startDate" type="text" class="form-control" value="${requestScope.mdVendorLicense.startDate}" autocomplete="off">
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
		
		<input type="hidden" name="vendorCode" value="${param.vendorCode}">
		<input type="hidden" name="licenseId" value="${requestScope.mdVendorLicense.licenseId}">
		<input type="hidden" name="createdDate" value="${requestScope.mdVendorLicense.createdDate}">
		<input type="hidden" name="createdBy" value="${requestScope.mdVendorLicense.createdBy}">
		
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	//初始化status
	if("${requestScope.mdVendorLicense.status}"!=""){
		$("#status").val("${requestScope.mdVendorLicense.status}");
	}
	//初始化companyType
	if("${requestScope.mdVendorLicense.companyType}"!=""){
		$("#companyType").val("${requestScope.mdVendorLicense.companyType}");
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
