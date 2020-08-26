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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<div class="modal" id="selectSODiv" tabindex="-1" role="dialog" aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">销售订单选择</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">

				<jsp:include page="../search/soSearch.jsp"></jsp:include>
					
				<div class="table-responsive">
					
					<div class="col-sm-12 text-right" style="padding-right: 0px;">
						<button id="searchConfirmButton" type="button" class="btn btn-success btn-sm" style="margin-bottom: 5px; display: none;">查询</button>
						<button id="searchButton" class="btn btn-default btn-sm" type="button" style="margin-bottom: 5px;"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
					</div>
					
					<table class="table table-striped table-hover table-bordered border-top">
						<thead>
							<tr>
								<th></th>
								<th>订单编码</th>
								<th>订单名称</th>
								<th>订单类型</th>
								<th>客户</th>
								<th>签订日期</th>
								<th>采购员</th>
								<th>采购部门</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.soHeadList}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td class="code">${data.soHeadCode}</td>
									<td class="name">${data.soName}</td>
									<td>${requestScope.soTypeMap[data.soType]}</td>
									<td class="companyName">${requestScope.customerMap[data.customerCode]}</td>
									<td class="companyCode" style="display: none;">${data.customerCode}</td>
									<td><fmt:formatDate value="${data.signDate}" pattern="yyyy-MM-dd" /></td>
									<td>${data.staffName}</td>
									<td>${data.departmentName}</td>
									<td><span class="label">${requestScope.soStatusMap[data.status]}</span></td>
									<td class="companyContact" style="display: none;">${data.customerContactDesc}</td>
								</tr>
							</c:forEach>

						</tbody>
						<tfoot>
							<%-- 导入页码 --%>
							<jsp:include page="../../common/pages.jsp"></jsp:include>
						</tfoot>
					</table>
				</div>

				<div class="form-group row m-b-none">
					<div class="col-sm-12 col-sm-offset-2 text-right">
						<button class="btn btn-white btn-lg" type="button" data-dismiss="modal">返回</button>
						&nbsp;
						<button id="selectButton" class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">
							&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
						</button>
					</div>
				</div>

			</div>

		</div>

	</div>

</div>

<script>
	$(document).ready(function() {
		//初始化checkbox
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
		
		
		//checkbox选中效果
		$("tr").click(function(){
			if($(this).find(".i-checks").prop("checked")){
				$(this).find(".i-checks").iCheck('uncheck');
			}else{
				$(".i-checks").iCheck('uncheck');
				$(this).find(".i-checks").iCheck('check');
			}
			
		});

		//查询条件
		$("#searchButton").click(function() {
			if ($("#searchDiv").css("display") == "none") {
				$("#searchDiv").show();
				$("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;关闭查询');
				$("#searchButton").addClass("btn-outline btn-warning");
				$("#searchButton").blur();
				$("#searchConfirmButton").show();
			} else {
				$("#searchDiv").hide();
				$("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;展开查询');
				$("#searchButton").removeClass("btn-outline btn-warning");
				$("#searchButton").blur();
				$("#searchConfirmButton").hiden();
			}
		});
		
		//确认按钮
		$("#selectButton").click(function(){
			var selectFlag = "N"
			var code =  "";
			var name = "";
			var companyCode = "";
			var companyName = "";
			var companyContact = "";
		
			$('.i-checks').each(function(){
				if($(this).prop("checked")){
					selectFlag = "Y";
					code = $(this).parents("tr").find("td.code").text();
					name = $(this).parents("tr").find("td.name").text();
					companyCode = $(this).parents("tr").find("td.companyCode").text();
					companyName = $(this).parents("tr").find("td.companyName").text();
					companyContact = $(this).parents("tr").find("td.companyContact").text();
					return false;
				}
			});
			
			if(selectFlag=="N"){
				redragonJS.alert("必须选择一个销售订单");
			}else{
				$("#customerName").val(companyName);
				$("#customerContactDesc").val(companyContact);
				$("#outputSourceHeadCode").val(code);
				$("#outputSourceHeadName").val(name);
				$('#selectSODiv').modal('hide');
			}
		});
	});
</script>
