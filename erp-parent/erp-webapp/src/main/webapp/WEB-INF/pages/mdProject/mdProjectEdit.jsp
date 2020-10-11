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
    <c:if test="${hint!=null&&hint!=''}">
   		<jsp:include page="../common/alert/alert.jsp">
   			<jsp:param value="${hint}" name="alertType"/>
   			<jsp:param value="${alertMessage}" name="alertMessage"/>
   		</jsp:include>
    </c:if>

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h4>项目编辑&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.mdProject.approveStatus]}）</span></h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/mdProject/editMdProject" method="post">
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>项目编码</label>
							<div class="col-sm-10">
								<input id="projectCode" name="projectCode" type="text" class="form-control" value="${requestScope.mdProject.projectCode}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>项目名称</label>
							<div class="col-sm-10">
								<input id="projectName" name="projectName" type="text" class="form-control" value="${requestScope.mdProject.projectName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>项目类别</label>
							<div class="col-sm-10">
								<select name="projectType" id="projectType" class="form-control">
									<option value="" selected="">请选择</option>
									<c:forEach items="${requestScope.projectTypeMap}" var="projectType">
			                       		<option value="${projectType.key}">${projectType.value}</option>
			                       	</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">项目描述</label>
							<div class="col-sm-10">
								<textarea id="projectDesc" name="projectDesc" rows="" cols="" class="form-control">${requestScope.mdProject.projectDesc}</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>项目开始日期</label>
							<div class="col-sm-10">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="startDate" name="startDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.mdProject.startDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">项目结束日期</label>
							<div class="col-sm-10">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="endDate" name="endDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.mdProject.endDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
								<span class="help-block m-b-none" style="font-size: 12px; color: silver;">填写结束日期后项目将自动结束</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>状态</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="status" id="status">
	                            <option value="Y" selected="selected">进行中</option>
	                            <option value="N">已结束</option>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/mdProject/getMdProjectList'">返回</button>&nbsp;
								<c:if test="${param.projectId==null||param.projectId==''||requestScope.mdProject.approveStatus=='UNSUBMIT'||requestScope.mdProject.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.projectId!=null&&param.projectId!=''}">
									<c:if test="${requestScope.mdProject.approveStatus=='UNSUBMIT'||requestScope.mdProject.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/mdProject/updateApproveStatus?id=${param.projectId}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdProject.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg btn-redragon-approve" type="button" onclick="approveData()">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg btn-redragon-approve" type="button" onclick="window.location.href='web/mdProject/updateApproveStatus?id=${param.projectId}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdProject.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="alterData()">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						<input type="hidden" name="projectId" value="${requestScope.mdProject.projectId}">
						<input type="hidden" name="createdDate" value="${requestScope.mdProject.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.mdProject.createdBy}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		//初始化status
		if("${requestScope.mdProject.status}"!=""){
			$("#status").val("${requestScope.mdProject.status}");
		}
		//初始化projectType
		if("${requestScope.mdProject.projectType}"!=""){
			$("#projectType").val("${requestScope.mdProject.projectType}");
		}
		//初始化code只读
		if("${requestScope.mdProject.projectCode}"!=""){
			$("#projectCode").prop("readonly", true);
		}
		
		//设置日期插件
		$('#startDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
		
		$('#endDate').datepicker({
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
				projectCode : {
					required : true,
				},
				projectName : {
					required : true,
				},
				projectType : {
					required : true,
				},
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
	
	//审批通过
	function approveData(){
		redragonJS.confirm("确认审批通过？", function(){
			window.location.href='web/mdProject/updateApproveStatus?id=${param.projectId}&approveStatus=APPROVE';
		});
	}
	
	//数据变更
	function alterData(){
		redragonJS.confirm("确认变更数据？数据变更可能会影响到已有的业务！", function(){
			window.location.href='web/mdProject/updateApproveStatus?id=${param.projectId}&approveStatus=UNSUBMIT';
		});
	}
</script>