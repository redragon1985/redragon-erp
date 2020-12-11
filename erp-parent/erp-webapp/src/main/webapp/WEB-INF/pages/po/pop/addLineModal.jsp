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
				<h4 class="modal-title">采购订单行编辑</h4>
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
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>物料</label>
						<div class="col-sm-9">
							<select class="select2Model form-control" name="materialCode" id="materialCode">
	                        	<option value="" selected="selected">请选择...</option>
	                        	<c:forEach items="${requestScope.materialMap}" var="material">
	                        		<option value="${material.key}">${material.value}（${material.key}）</option>
	                        	</c:forEach>
	                        </select>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
				
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>单价</label>
						<div class="col-sm-9">
							<input id="price" name="price" type="text" class="form-control" value="${requestScope.poLine.price}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>数量</label>
						<div class="col-sm-9">
							<input id="quantity" name="quantity" type="text" class="form-control" value="${requestScope.poLine.quantity}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>单位</label>
						<div class="col-sm-9">
							<select class="select2Model form-control" name="unit" id="unit">
	                        	<option value="" selected="selected">请选择...</option>
	                        	<c:forEach items="${requestScope.materialUnitMap}" var="materialUnit">
	                        		<option value="${materialUnit.key}">${materialUnit.value}</option>
	                        	</c:forEach>
	                        </select>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>行金额</label>
						<div class="col-sm-9">
							<input id="lineAmount" name="amount" type="text" class="form-control" value="${requestScope.poLine.amount}" readonly="readonly">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">行摘要</label>
						<div class="col-sm-9">
							<input id="memo" name="memo" type="text" class="form-control" value="${requestScope.poLine.memo}">
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
					
					<input type="hidden" id="poHeadCode" name="poHeadCode" value="${param.poHeadCode}"> 
					<input type="hidden" id="poLineId" name="poLineId" value="${requestScope.poLine.poLineId}"> 
					<input type="hidden" id="poLineCode" name="poLineCode" value="${requestScope.poLine.poLineCode}"> 
					<input type="hidden" id="createdDate" name="createdDate" value="${requestScope.poLine.createdDate}"> 
					<input type="hidden" id="createdBy" name="createdBy" value="${requestScope.poLine.createdBy}">
				</form>
			</div>

		</div>

	</div>

</div>

<!-- Chosen -->
<script src="js/plugins/chosen/chosen.jquery.js"></script>

<script>
	$(document).ready(function() {
		//初始化materialCode
		if("${requestScope.poLine.materialCode}"!=""){
			$("#materialCode").val("${requestScope.poLine.materialCode}");
		}
		//初始化unit
		if("${requestScope.poLine.unit}"!=""){
			$("#unit").val("${requestScope.poLine.unit}");
		}
		
		//初始化chosen
		//$('.select2Model').select2({width: "100%"});
		$('.select2Model').chosen({width: "100%"});
		
		//自动计算行金额
		setAmount();
		
		//设置失去焦点自动计算行金额
		$("#quantity").blur(function(){
			setAmount();
		});
		
		$("#price").blur(function(){
			setAmount();
		});
		
		//自动设置协议验证逻辑
		setPoaCheck();
		
		//有协议的订单行编辑时，根据协议中的物料做验证
		$("#materialCode").change(function(){
			setPoaCheck();
		});
		
		
		
		//表单提交
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#lineForm").valid();
			//l.ladda('stop');
		});

		$("#lineForm").validate({
			rules : {
				materialCode : {
					required : true,
				},
				price : {
					required : true,
					gtZero : true,
				},
				quantity : {
					required : true,
					gtZero : true,
				},
				unit : {
					required : true,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
				editLine();
		    }
		});
		
	});
	
	//异步编辑联系人
	function editLine(){
		redragonJS.loading("tabDiv");
	
		$.ajax({
			type: "post",
			url: "web/poLine/editPoLine",
			data: JSON.stringify({"materialCode": $("#materialCode").val(), "price": $("#price").val(), "quantity": $("#quantity").val(), 
								"unit": $("#unit").val(), "amount": $("#lineAmount").val(), "createdDate": $("#createdDate").val(), "createdBy": $("#createdBy").val(), 
								"memo": $("#memo").val(), "poHeadCode": $("#poHeadCode").val(), "poLineId": $("#poLineId").val(), "poLineCode": $("#poLineCode").val()}),
			async: false,
			dataType: "html",
			contentType: "application/json",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("tabDiv");
				$('#addLineDiv').modal('hide');
				$('.ladda-button-demo').ladda('stop');
				
				var json = JSON.parse(data);
				if(json.result=="success"){
					getLineTab($("#poHeadCode").val());
				}else{
					redragonJS.alert("编辑行信息错误");
				}
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//计算行金额
	function setAmount(){
		var quantity = $("#quantity").val();
		var price = $("#price").val();
		
		if($.isNumeric(quantity)&&$.isNumeric(price)){
			var amount = parseFloat(price)*parseFloat(quantity);
			$("#lineAmount").val(Math.round(amount*100)/100);
		}
	}
	
	//设置协议验证逻辑
	function setPoaCheck(){
		if($("#materialCode").val()!=""){
			var lines = poaLineMap.get($("#materialCode").val());
			var line = lines.split(":");
			//设置单价、数量和单位
			if($("#price").val()==""){
				$("#price").val(line[0]);
			}
			if($("#unit").val()==""){
				$("#unit").val(line[2]);
			}
			
			if(line[1]!=""){
				$("#quantityPOA").text(line[1]);
				$("#quantityPOADesc").text("协议数量：");
			}
			if(line[3]!=""){
				$("#agreementFinishQuantity").text(line[3]);
				$("#agreementFinishQuantityDesc").text("协议已发放数量：");
			}
			
			$("#quantity").focus();
		}
	}
</script>
