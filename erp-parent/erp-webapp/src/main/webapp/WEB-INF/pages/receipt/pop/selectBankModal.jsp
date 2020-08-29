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
				<h4 class="modal-title">银行和账号选择</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">

				<div class="table-responsive">
					
					<table class="table table-striped table-hover table-bordered border-top">
						<thead>
							<tr>
								<th width="5%"></th>
								<th>银行编码</th>
								<th>分行</th>
								<th>银行账户</th>
								<th>创建时间</th>
								<th>创建人</th>
								<th width="5%">状态</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.mdCustomerBankList}" var="data" varStatus="status">
							<tr>
								<td><input type="checkbox" class="i-checks" name="input[]"></td>
								<td class="bankName">${requestScope.bankMap[data.bankCode]}</td>
								<td class="bankCode" style="display: none;">${data.bankCode}</td>
								<td class="subBankCode">${data.subBankCode}</td>
								<td class="bankAccount">${data.bankAccount}</td>
								<td><fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${data.createdBy}</td>
								<td>
								<c:choose>
								   <c:when test="${data.status=='Y'}">
								       <span class="label">有效</span>
								   </c:when>
								   <c:otherwise>
								       <span class="label">无效</span>
								   </c:otherwise>
								</c:choose>
								</td>
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

		
		//确认按钮
		$("#selectButton").click(function(){
			var selectFlag = "N"
			var bankCode =  "";
			var bankName = "";
			var subBankCode = "";
			var bankAccount = "";
		
			$('.i-checks').each(function(){
				if($(this).prop("checked")){
					selectFlag = "Y";
					bankCode = $(this).parents("tr").find("td.bankCode").text();
					bankName = $(this).parents("tr").find("td.bankName").text();
					subBankCode = $(this).parents("tr").find("td.subBankCode").text();
					bankAccount = $(this).parents("tr").find("td.bankAccount").text();
					return false;
				}
			});
			
			if(selectFlag=="N"){
				redragonJS.alert("必须选择一个银行账号");
			}else{
				$("#bankCode").val(bankCode);
				$("#bankName").val(bankName);
				$("#subBankCode").val(subBankCode);
				$("#bankAccount").val(bankAccount);
				$('#selectSODiv').modal('hide');
			}
		});
	});
</script>
