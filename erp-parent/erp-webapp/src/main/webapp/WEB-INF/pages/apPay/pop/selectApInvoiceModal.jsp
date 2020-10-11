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

<div class="modal" id="selectBillLineDiv" tabindex="-1" role="dialog" aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">选择要核销的发票行</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">
			
				<jsp:include page="../search/invoiceSearch.jsp"></jsp:include>

				<div class="table-responsive">
				
					<div class="col-sm-12 text-right" style="padding-right: 0px;">
						<button id="searchConfirmButton" type="button" class="btn btn-success btn-sm" style="margin-bottom: 5px; display: none;">查询</button>
						<button id="searchButton" class="btn btn-default btn-sm" type="button" style="margin-bottom: 5px;"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
					</div>
					
					<table class="table table-striped table-hover table-bordered border-top">
						<thead>
							<tr>
								<th></th>
								<th>发票编码</th>
								<th>采购订单编码</th>
								<th>供应商</th>
								<th>发票参数号</th>
								<th>发票日期</th>
								<th>发票金额</th>
								<th>税额</th>
								<th>预付款</th>
								<th>摘要</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.invoiceHeadList}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td class="invoiceHeadCode">${data.invoiceHeadCode}</td>
									<td class="invoiceSourceHeadCode">${data.invoiceSourceHeadCode}</td>
									<td class="receiverName">${data.receiverName}</td>
									<td class="referenceNumber">${data.referenceNumber}</td>
									<td class="invoiceDate"><fmt:formatDate value="${data.invoiceDate}" pattern="yyyy-MM-dd"/></td>
									<td class="siAmount">${data.amount}</td>
									<td class="siTaxAmount">${data.taxAmount}</td>
									<td>${data.prepayFlag}</td>
									<td>${data.memo}</td>
								</tr>
							</c:forEach>

						</tbody>
						<tfoot>
							<%-- 导入页码 --%>
							<jsp:include page="../../common/popPages.jsp"></jsp:include>
						</tfoot>
					</table>
				</div>

				<div class="form-group row m-b-none">
					<div class="col-sm-12 col-sm-offset-2 text-right">
						<button class="btn btn-white btn-lg" type="button" data-dismiss="modal">返回</button>
						&nbsp;
						<button id="selectButton" class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">
							&nbsp;&nbsp;继续&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
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
			
			var invoiceHeadCode =  "";
			var invoiceSourceHeadCode = "";
			var receiverName = "";
			var referenceNumber = "";
			var invoiceDate = "";
			var siAmount = "";
			var siTaxAmount = "";
			
			$('.i-checks').each(function(){
				if($(this).prop("checked")){
					selectFlag = "Y";
					
					invoiceHeadCode = $(this).parents("tr").find("td.invoiceHeadCode").text();
					invoiceSourceHeadCode = $(this).parents("tr").find("td.invoiceSourceHeadCode").text();
					receiverName = $(this).parents("tr").find("td.receiverName").text();
					referenceNumber = $(this).parents("tr").find("td.referenceNumber").text();
					invoiceDate = $(this).parents("tr").find("td.invoiceDate").text();
					siAmount = $(this).parents("tr").find("td.siAmount").text();
					siTaxAmount = $(this).parents("tr").find("td.siTaxAmount").text();
					return false;
				}
			});
			
			if(selectFlag=="N"){
				redragonJS.alert("必须选择一个单据行");
			}else{
				$('#selectBillLineDiv').modal('hide');
				getLineModal(null, invoiceHeadCode, invoiceSourceHeadCode, receiverName, referenceNumber, invoiceDate, siAmount, siTaxAmount);
			}
		});
	});
	
//跳转页面
function gotoPage(page){
	var pageNumber = ${requestScope.pages.pageNumber};
	var currentPage = ${requestScope.pages.page};
	//首页和尾页无需跳转
	if((currentPage==1&&page==1)||(currentPage==pageNumber&&page==pageNumber)){
		
	}else{
		getSelectApInvoiceModal(page);
	}
}
</script>
