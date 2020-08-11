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

<div class="modal" id="selectBillDiv" tabindex="-1" role="dialog" aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">单据选择</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">

				<jsp:include page="../search/receiptBillSearch.jsp"></jsp:include>
					
				<div class="table-responsive">
					
					<div class="col-sm-12 text-right" style="padding-right: 0px;">
						<button id="searchBillConfirmButton" type="button" class="btn btn-success btn-sm" style="margin-bottom: 5px; display: none;">查询</button>
						<button id="searchBillButton" class="btn btn-default btn-sm" type="button" style="margin-bottom: 5px;"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
					</div>
					
					<table class="table table-striped table-hover table-bordered border-top">
						<thead>
							<tr>
								<th></th>
								<th>收款单编码</th>
								<th>来源</th>
								<th>来源编码</th>
								<th>付款方</th>
								<th>收款日期</th>
								<th>收款金额</th>
								<th>制单人</th>
								<th>状态</th>
								<th>出纳状态</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.receiptHeadList}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td class="billHeadCode">${data.receiptHeadCode}</td>
									<td>${requestScope.receiptSourceTypeMap[data.receiptSourceType]}</td>
									<td>${data.receiptSourceHeadCode}</td>
									<td>${requestScope.customerMap[data.payer]}</td>
									<td><fmt:formatDate value="${data.receiptDate}" pattern="yyyy-MM-dd" /></td>
									<td class="amount">${data.amount}</td>
									<td>${data.staffName}-${data.departmentName}</td>
									<td><span class="label">${requestScope.receiptStatusMap[data.status]}</span></td>
									<td><span class="label">${requestScope.receivedStatusMap[data.receivedStatus]}</span></td>
									
									<td class="businessType" style="display: none;">RECEIPT</td>
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
		$("#searchBillButton").click(function() {
			if ($("#searchBillDiv").css("display") == "none") {
				$("#searchBillDiv").show();
				$("#searchBillButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;关闭查询');
				$("#searchBillButton").addClass("btn-outline btn-warning");
				$("#searchBillButton").blur();
				$("#searchBillConfirmButton").show();
			} else {
				$("#searchBillDiv").hide();
				$("#searchBillButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;展开查询');
				$("#searchBillButton").removeClass("btn-outline btn-warning");
				$("#searchBillButton").blur();
				$("#searchBillConfirmButton").hiden();
			}
		});
		
		//确认按钮
		$("#selectButton").click(function(){
			var selectFlag = "N"
			var billHeadCode =  "";
			var amount = "";
			var businessType = "";
		
			$('.i-checks').each(function(){
				if($(this).prop("checked")){
					selectFlag = "Y";
					billHeadCode = $(this).parents("tr").find("td.billHeadCode").text();
					amount = $(this).parents("tr").find("td.amount").text();
					businessType = $(this).parents("tr").find("td.businessType").text();
					return false;
				}
			});
			
			if(selectFlag=="N"){
				redragonJS.alert("必须选择一个收款单");
			}else{
				$('#selectBillDiv').modal('hide');

				$.ajax({
					type: "post",
					url: "web/finVoucherModelHead/autoCreateVoucher",
					data: {"billHeadCode": billHeadCode, "amount": amount, "businessType": businessType},
					async: false,
					dataType: "json",
					cache: false,
					success: function(data){
						if(data.errCode==0){
							window.location.href = "web/finVoucherHead/getFinVoucherHead?voucherHeadId="+data.voucherHeadId+"&voucherHeadCode="+data.voucherHeadCode;
						}else{
							redragonJS.alert(data.errMsg);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						redragonJS.alert(textStatus);
					}
				});
			}
		});
	});
</script>
