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
					<h4>岗位编辑</h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/hrPosition/editHrPosition" method="post">
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>岗位编码</label>
							<div class="col-sm-10">
								<input id="positionCode" name="positionCode" type="text" class="form-control" value="${requestScope.hrPosition.positionCode}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>岗位名称</label>
							<div class="col-sm-10">
								<input id="positionName" name="positionName" type="text" class="form-control" value="${requestScope.hrPosition.positionName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>岗位类型</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="positionType" id="positionType">
	                            <option value="" selected="selected">请选择...</option>
	                            <c:forEach items="${requestScope.positionTypeMap}" var="positionType">
	                            	<option value="${positionType.key}">${positionType.value}</option>
	                            </c:forEach>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>状态</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="status" id="status">
	                            <option value="Y" selected="selected">有效</option>
	                            <option value="N">无效</option>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/hrPosition/getHrPositionList'">返回</button>&nbsp;
								<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i></button>
							</div>
						</div>
						
						<input type="hidden" name="positionId" value="${requestScope.hrPosition.positionId}">
						<input type="hidden" name="createdDate" value="${requestScope.hrPosition.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.hrPosition.createdBy}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		//初始化status
		if("${requestScope.hrPosition.status}"!=""){
			$("#status").val("${requestScope.hrPosition.status}");
		}
		//初始化positionType
		if("${requestScope.hrPosition.positionType}"!=""){
			$("#positionType").val("${requestScope.hrPosition.positionType}");
		}
		//初始化code只读
		if("${requestScope.hrPosition.positionCode}"!=""){
			$("#positionCode").prop("readonly", true);
		}
	
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").valid();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				positionCode : {
					required : true,
				},
				positionName : {
					required : true,
				},
				positionType : {
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