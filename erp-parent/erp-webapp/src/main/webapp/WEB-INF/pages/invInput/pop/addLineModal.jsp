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

<div class="modal" id="addLineDiv" tabindex="-1" role="dialog" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">入库行编辑</h4>
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
			
				<form id="lineForm">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">物料编码</label>
						<div class="col-sm-9">
							<input id="materialCode" type="text" class="form-control" value="${requestScope.invInputLine.materialCode}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">物料</label>
						<div class="col-sm-9">
							<input id="materialName" type="text" class="form-control" value="${requestScope.invInputLine.materialName}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">规格型号</label>
						<div class="col-sm-9">
							<input id="materialStandard" type="text" class="form-control" value="${requestScope.invInputLine.materialStandard}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
				
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">单价</label>
						<div class="col-sm-9 input-group">
							<input id="price" type="text" class="form-control" value="${requestScope.invInputLine.price}" readonly="readonly">
							<span class="input-group-addon">(元)</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-3 col-form-label">数量</label>
						<div class="col-sm-9 input-group">
							<input id="quantity" type="text" class="form-control" value="${requestScope.invInputLine.quantity}" readonly="readonly">
							<span class="input-group-addon">(${requestScope.invInputLine.unit})</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">行金额</label>
						<div class="col-sm-9 input-group">
							<input id="lineAmount" type="text" class="form-control" value="${requestScope.invInputLine.poLineAmount}" readonly="readonly">
							<span class="input-group-addon">(元)</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>入库数量</label>
						<div class="col-sm-9 input-group">
							<input id="inputQuantity" name="inputQuantity" type="text" class="form-control" value="${requestScope.invInputLine.inputQuantity}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">入库摘要</label>
						<div class="col-sm-9">
							<input id="inputLineMemo" name="memo" type="text" class="form-control" value="${requestScope.invInputLine.memo}">
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
					
					<input type="hidden" id="inputHeadCode" name="inputHeadCode" value="${param.inputHeadCode}"> 
					<input type="hidden" id="inputLineId" name="inputLineId" value="${requestScope.invInputLine.inputLineId}"> 
					<input type="hidden" id="inputLineCode" name="inputLineCode" value="${requestScope.invInputLine.inputLineCode}"> 
					<input type="hidden" id="inputSourceLineCode" name="inputSourceLineCode" value="${requestScope.invInputLine.inputSourceLineCode}"> 
					<input type="hidden" id="createdDate" name="createdDate" value="${requestScope.invInputLine.createdDate}"> 
					<input type="hidden" id="createdBy" name="createdBy" value="${requestScope.invInputLine.createdBy}">
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
				inputQuantity : {
					required : true,
					compareNumber: "#quantity"
				},
			},
			messages : {
				inputQuantity : {
					compareNumber: "入库数量不能大于行数量"
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
				editLine();
		    }
		});
		
	});
	
	//异步编辑付款行
	function editLine(){
		redragonJS.loading("ibox-content1");
	
		$.ajax({
			type: "post",
			url: "web/invInputLine/editInvInputLine",
			data: {"inputQuantity": $("#inputQuantity").val(), "memo": $("#inputLineMemo").val(), "inputHeadCode": $("#inputHeadCode").val(), "inputLineId": $("#inputLineId").val(),
				   "inputLineCode": $("#inputLineCode").val(), "inputSourceLineCode": $("#inputSourceLineCode").val(), "materialCode": $("#materialCode").val(), 
				   "createdDate": $("#createdDate").val(), "createdBy": $("#createdBy").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content1");
				$('#addLineDiv').modal('hide');
				$('.ladda-button-demo').ladda('stop');
				
				var json = JSON.parse(data);
				if(json.result=="success"){
					getLineTab($("#inputHeadCode").val());
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
