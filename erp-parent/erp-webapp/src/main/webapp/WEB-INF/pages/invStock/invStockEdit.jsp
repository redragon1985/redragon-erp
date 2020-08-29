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
					<h4>物料库存初始化</h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/invStock/editInvStock" method="post">
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>物料选择</label>
							<div class="col-sm-10">
								<select class="select2 form-control" name="materialCode" id="materialCode">
			                    	<option value="" selected="selected">请选择...</option>
			                        <c:forEach items="${requestScope.materialMap}" var="material">
			                    		<option value="${material.key}">${material.value}</option>
			                    	</c:forEach>
			                    </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label">物料规格</label>
	                        <div class="col-sm-10">
								<input id="materialStandard" type="text" class="form-control" readonly="readonly">
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label">物料单位</label>
	                        <div class="col-sm-10">
								<input id="materialUnit" type="text" class="form-control" readonly="readonly">
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>初始数量</label>
							<div class="col-sm-10">
								<input id="stockNumber" name="stockNumber" type="text" class="form-control" value="${requestScope.invStock.stockNumber}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label">备注</label>
	                        <div class="col-sm-10">
								<textarea id="memo" name="memo" class="form-control">${requestScope.invStock.memo}</textarea>
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/invStock/getInvStockList?warehouseCode=${param.warehouseCode}'">返回</button>&nbsp;
								<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i></button>
							</div>
						</div>
						
						<input type="hidden" name="warehouseCode" value="${param.warehouseCode}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<script>
	$(document).ready(function() {
		//初始化选择框
		$('.select2').select2({width: "100%"});
		
		//初始化status
		if("${requestScope.invStock.materialCode}"!=""){
			$("#materialCode").val("${requestScope.invStock.materialCode}");
		}
	
		//获取物料信息
		$("#materialCode").change(function(){
			$.ajax({
				type: "post",
				url: "web/invStock/getMaterialInfoAjax",
				data: {"materialCode": $(this).val()},
				async: false,
				dataType: "json",
				cache: false,
				success: function(data){
					if(data!=""){
						$("#materialStandard").val(data.materialStandard);
						$("#materialUnit").val(data.materialUnit);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					redragonJS.alert(errorThrown);
				}
			});
		});
	
	
		
		//表单提交
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").valid();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				materialCode : {
					required : true,
				},
				stockNumber : {
					required : true,
					number : true
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
	});
</script>