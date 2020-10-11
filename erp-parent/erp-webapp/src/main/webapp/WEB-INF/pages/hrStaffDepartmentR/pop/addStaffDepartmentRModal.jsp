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

<div class="modal" id="addDiv" tabindex="-1" role="dialog" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">职员与部门关联</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px;">
				<%-- 导入提示信息框 --%>
			    <c:if test="${hint!=null&&hint!=''}">
			   		<jsp:include page="../../common/alert/alert.jsp">
			   			<jsp:param value="${hint}" name="alertType"/>
			   			<jsp:param value="${alertMessage}" name="alertMessage"/>
			   		</jsp:include>
			    </c:if>
			
				<form id="form" action="web/hrStaffDepartmentR/editHrStaffDepartmentR" method="post">
					<div class="form-group row">
						<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>职员</label>
						<div class="col-sm-10">
							<select id="staffCode" name="staffCode" class="form-control m-xs chosen-select"  tabindex="1">
				                <option value="">请选择...</option>
				                <c:forEach items="${hrStaffList}" var="data">
				                	<option value="${data.staffCode}">${data.staffName}</option>
				                </c:forEach>
			                </select>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>岗位</label>
						<div class="col-sm-10">
							<select id="positionCode" name="positionCode" class="form-control m-b chosen-select"  tabindex="1">
				                <option value="">请选择...</option>
				                <c:forEach items="${hrPositionList}" var="data">
				                	<option value="${data.positionCode}">${data.positionName}</option>
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

					<div class="form-group row m-b-none">
						<div class="col-sm-12 col-sm-offset-2 text-right">
							<button class="btn btn-white btn-lg" type="button" data-dismiss="modal">返回</button>
							&nbsp;
							<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">
								&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
							</button>
						</div>
					</div>
					
					<input type="hidden" name="departmentId" value="${param.departmentId}">
					<input type="hidden" name="departmentCode" value="${requestScope.hrDepartment.departmentCode}">
					<input type="hidden" name="sdId" value="${requestScope.hrStaffDepartmentR.sdId}"> 
					<input type="hidden" name="createdDate" value="${requestScope.hrStaffDepartmentR.createdDate}"> 
					<input type="hidden" name="createdBy" value="${requestScope.hrStaffDepartmentR.createdBy}">
				</form>
			</div>

			<%-- 
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">确定</button>
			</div>
			--%>
		</div>

	</div>

</div>

<!-- Chosen -->
<script src="js/plugins/chosen/chosen.jquery.js"></script>
<link href="css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">

<script>
	$(document).ready(function() {
		//初始化status
		if("${requestScope.hrStaffDepartmentR.status}"!=""){
			$("#status").val("${requestScope.hrStaffDepartmentR.status}");
		}
		//初始化staffCode
		if("${requestScope.hrStaffDepartmentR.staffCode}"!=""){
			$("#staffCode").val("${requestScope.hrStaffDepartmentR.staffCode}");
		}
		//初始化positionCode
		if("${requestScope.hrStaffDepartmentR.positionCode}"!=""){
			$("#positionCode").val("${requestScope.hrStaffDepartmentR.positionCode}");
		}
		//初始化username只读
		if("${requestScope.hrStaffDepartmentR.staffCode}"!=""){
			$("#staffCode").prop("disabled", true);
		}
		
		//chosen初始化
		$('.chosen-select').chosen({width: "100%"});
		
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				staffCode : {
					required : true,
				},
				positionCode : {
					required : true,
				}
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		        //$('#addDiv').modal('hide');
		    }
		});
	});
</script>
