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

<div class="modal" id="addLineDiv" tabindex="-1" role="dialog" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">收款行编辑</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px;">
				<%-- 导入提示信息框 --%>
				<c:if test="${requestScope.hints!=null&&requestScope.hints!=''}">
					<jsp:include page="../../common/alert/alert.jsp">
						<jsp:param value="hint" name="alertType"/>
						<jsp:param value="${fn:replace(requestScope.hints,';', '<br/>')}" name="alertMessage"/>
					</jsp:include>
				</c:if>
			
				<form id="lineForm">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">物料编码</label>
						<div class="col-sm-9">
							<input id="materialCode" type="text" class="form-control" value="${requestScope.receiptLine.materialCode}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">物料</label>
						<div class="col-sm-9">
							<input id="materialName" type="text" class="form-control" value="${requestScope.receiptLine.materialName}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
				
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">单价</label>
						<div class="col-sm-9 input-group">
							<input id="price" type="text" class="form-control" value="${requestScope.receiptLine.price}" readonly="readonly">
							<span class="input-group-addon">(元)</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-3 col-form-label">数量</label>
						<div class="col-sm-9 input-group">
							<input id="quantity" type="text" class="form-control" value="${requestScope.receiptLine.quantity}" readonly="readonly">
							<span class="input-group-addon">(${requestScope.receiptLine.unit})</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">行金额</label>
						<div class="col-sm-9 input-group">
							<input id="lineAmount" type="text" class="form-control" value="${requestScope.receiptLine.soLineAmount}" readonly="readonly">
							<span class="input-group-addon">(元)</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>收款金额</label>
						<div class="col-sm-9 input-group">
							<input id="receiptLineAmount" name="amount" type="text" class="form-control" value="${requestScope.receiptLine.amount}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">收款摘要</label>
						<div class="col-sm-9">
							<input id="receiptLineMemo" name="memo" type="text" class="form-control" value="${requestScope.receiptLine.memo}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row m-b-none">
						<div class="col-sm-12 col-sm-offset-2 text-right">
							<button class="btn btn-white btn-lg" type="button"
								data-dismiss="modal">返回</button>
							&nbsp;
							<button
								class="ladda-button ladda-button-demo btn btn-primary btn-lg"
								data-style="expand-right">
								&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
							</button>
						</div>
					</div>
					
					<input type="hidden" id="receiptHeadCode" name="receiptHeadCode" value="${param.receiptHeadCode}"> 
					<input type="hidden" id="receiptLineId" name="receiptLineId" value="${requestScope.receiptLine.receiptLineId}"> 
					<input type="hidden" id="receiptLineCode" name="receiptLineCode" value="${requestScope.receiptLine.receiptLineCode}"> 
					<input type="hidden" id="receiptSourceLineCode" name="receiptSourceLineCode" value="${requestScope.receiptLine.receiptSourceLineCode}"> 
					<input type="hidden" id="createdDate" name="createdDate" value="${requestScope.receiptLine.createdDate}"> 
					<input type="hidden" id="createdBy" name="createdBy" value="${requestScope.receiptLine.createdBy}">
				</form>
			</div>

		</div>

	</div>

</div>


<script>
	$(document).ready(function() {
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#lineForm").valid();
			//l.ladda('stop');
		});

		$("#lineForm").validate({
			rules : {
				amount : {
					required : true,
					compareNumber: "#lineAmount"
				},
			},
			messages : {
				amount : {
					compareNumber: "收款金额不能大于行金额"
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
				editLine();
		    }
		});
		
	});
	
	//异步编辑收款行
	function editLine(){
		redragonJS.loading("ibox-content1");
	
		$.ajax({
			type: "post",
			url: "web/receiptLine/editReceiptLine",
			data: {"amount": $("#receiptLineAmount").val(), "memo": $("#receiptLineMemo").val(), "receiptHeadCode": $("#receiptHeadCode").val(), "receiptLineId": $("#receiptLineId").val(),
				   "receiptLineCode": $("#receiptLineCode").val(), "receiptSourceLineCode": $("#receiptSourceLineCode").val(), "createdDate": $("#createdDate").val(),
				   "createdBy": $("#createdBy").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content1");
				$('#addLineDiv').modal('hide');
				$('.ladda-button-demo').ladda('stop');
				
				var json = JSON.parse(data);
				if(json.result=="success"){
					getLineTab($("#receiptHeadCode").val());
				}else{
					redragonJS.alert("编辑行信息错误");
				}
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
</script>
