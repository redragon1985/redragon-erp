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
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%-- 导入面包屑 --%>
<jsp:include page="../common/nav.jsp"></jsp:include>

<div class="wrapper wrapper-content animated fadeInRight">

	<%-- 导入提示信息框 --%>
	<c:if test="${requestScope.hints!=null&&requestScope.hints!=''}">
		<jsp:include page="../common/alert/alert.jsp">
			<jsp:param value="hint" name="alertType"/>
			<jsp:param value="${fn:replace(requestScope.hints,';', '<br/>')}" name="alertMessage"/>
		</jsp:include>
	</c:if>

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h4>职员编辑</h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/hrStaff/editHrStaff" method="post">
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>关联用户名</label>
							<div class="col-sm-10">
								<select id="username" name="username" class="chosen-select"  tabindex="1">
					                <option value="">请选择...</option>
					                <c:forEach items="${requestScope.sysUserList}" var="data">
					                	<option value="${data.username}">${data.username}</option>
					                </c:forEach>
				                </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>职员工号</label>
							<div class="col-sm-10">
								<input id="staffCode" name="staffCode" type="text" class="form-control" value="${requestScope.hrStaff.staffCode}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>职员名称</label>
							<div class="col-sm-10">
								<input id="staffName" name="staffName" type="text" class="form-control" value="${requestScope.hrStaff.staffName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>性别</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="staffSex" id="staffSex">
	                            <option value="MALE" selected="selected">男</option>
	                            <option value="FEMALE">女</option>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">入职日期</label>
							<div class="col-sm-10">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="staffEntryDate" name="staffEntryDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.hrStaff.staffEntryDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>职员状态</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="staffStatus" id="staffStatus">
	                            <option value="WORK" selected="selected">在职</option>
	                            <option value="LEAVE">离职</option>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group  row">
							<label class="col-sm-2 col-form-label">手机</label>
							<div class="col-sm-10">
								<input id="staffMobile" name="staffMobile" type="text" class="form-control" value="${requestScope.hrStaff.staffMobile}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">邮箱</label>
							<div class="col-sm-10">
								<input id="staffEmail" name="staffEmail" type="text" class="form-control" value="${requestScope.hrStaff.staffEmail}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/hrStaff/getHrStaffList'">返回</button>&nbsp;
								<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i></button>
							</div>
						</div>
						
						<input type="hidden" name="staffId" value="${requestScope.hrStaff.staffId}">
						<input type="hidden" name="createdDate" value="${requestScope.hrStaff.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.hrStaff.createdBy}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Chosen -->
<script src="js/plugins/chosen/chosen.jquery.js"></script>
<link href="css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">

<script>
	$(document).ready(function() {
	
		//初始化roleCode
		if("${requestScope.hrStaff.username}"!=""){
			$("#username").val("${requestScope.hrStaff.username}");
		}
	
		//设置高级select插件
		$('.chosen-select').chosen({width: "100%"});
	
		//初始化status
		if("${requestScope.hrStaff.staffStatus}"!=""){
			$("#staffStatus").val("${requestScope.hrStaff.staffStatus}");
		}
		//初始化sex
		if("${requestScope.hrStaff.staffSex}"!=""){
			$("#staffSex").val("${requestScope.hrStaff.staffSex}");
		}
		//初始化code只读
		if("${requestScope.hrStaff.staffCode}"!=""){
			$("#staffCode").prop("readonly", true);
		}
		
		//设置日期插件
		$('#staffEntryDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
	
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").valid();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				staffCode : {
					required : true,
				},
				staffName : {
					required : true,
				},
				username : {
					required : true,
				}
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
	});
</script>