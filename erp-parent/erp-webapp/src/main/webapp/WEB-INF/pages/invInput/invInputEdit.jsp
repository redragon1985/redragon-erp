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
    <c:if test="${hint!=null&&hint!=''}">
   		<jsp:include page="../common/alert/alert.jsp">
   			<jsp:param value="${hint}" name="alertType"/>
   			<jsp:param value="${alertMessage}" name="alertMessage"/>
   		</jsp:include>
    </c:if>

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title btn-success btn-outline panel-success collapse-link" style="cursor: pointer;" title="展开/收起">
					<h5>入库单头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.invInputHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div id="ibox-content" class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/invInputHead/editInvInputHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>入库单编码</strong></label>
							<div class="col-sm-4">
								<input id="inputHeadCode" name="inputHeadCode" type="text" class="form-control" value="${requestScope.invInputHead.inputHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>入库类型</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="inputType" id="inputType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.inputTypeMap}" var="inputType">
		                        		<option value="${inputType.key}">${inputType.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>仓库</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="warehouseCode" id="warehouseCode">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.warehouseMap}" var="warehouse">
		                        		<option value="${warehouse.key}" address="${warehouse.value.warehouseAddress}">${warehouse.value.warehouseName}</option>
		                        	</c:forEach>
		                        </select>
							</div>
							
							<label class="col-sm-2 col-form-label"><strong>仓库地址</strong></label>
							<div class="col-sm-4">
								<input id="warehouseAddress" type="text" class="form-control" value="${requestScope.invInputHead.warehouseAddress}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>入库来源</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="inputSourceType" id="inputSourceType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.inputSourceTypeMap}" var="inputSourceType">
		                        		<option value="${inputSourceType.key}">${inputSourceType.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong id="inputSourceHeadCodeText">来源头编码</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="inputSourceHeadCode" name="inputSourceHeadCode" type="text" class="form-control" value="${requestScope.invInputHead.inputSourceHeadCode}">
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong id="inputSourceHeadNameText">来源头名称</strong></label>
	                        <div class="col-sm-4">
		                        <input id="inputSourceHeadName" type="text" class="form-control" value="${requestScope.invInputHead.inputSourceHeadName}" readonly="readonly">
	                        </div>
	                        
							<label class="col-sm-2 col-form-label"><strong>备注</strong></label>
							<div class="col-sm-4">
								<input id="memo" name="memo" type="text" class="form-control" value="${requestScope.invInputHead.memo}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商</strong></label>
							<div class="col-sm-4">
								<input id="vendorName" type="text" class="form-control" value="${requestScope.invInputHead.vendorName}" readonly="readonly">
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商联系人</strong></label>
	                        <div class="col-sm-4">
		                        <input id="vendorContactDesc" type="text" class="form-control" value="${requestScope.invInputHead.vendorContactDesc}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>入库日期</strong></label>
	                        <div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="inputDate" name="inputDate" type="text" class="form-control" value="${requestScope.invInputHead.inputDate}" autocomplete="off">
								</div>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.inputStatusMap[requestScope.invInputHead.status]}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单人</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.invInputHead.staffName}" readonly="readonly">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单部门</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.invInputHead.departmentName}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/invInputHead/getInvInputHeadList'">返回</button>&nbsp;
								<c:if test="${param.inputHeadCode==null||param.inputHeadCode==''||requestScope.invInputHead.approveStatus=='UNSUBMIT'||requestScope.invInputHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.inputHeadCode!=null&&param.inputHeadCode!=''}">
									<c:if test="${requestScope.invInputHead.approveStatus=='UNSUBMIT'||requestScope.invInputHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/invInputHead/updateApproveStatus?code=${requestScope.invInputHead.inputHeadCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.invInputHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/invInputHead/updateApproveStatus?code=${requestScope.invInputHead.inputHeadCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/invInputHead/updateApproveStatus?code=${requestScope.invInputHead.inputHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.invInputHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/invInputHead/updateApproveStatus?code=${requestScope.invInputHead.inputHeadCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="status" name="status" value="${requestScope.invInputHead.status}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.invInputHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.invInputHead.departmentCode}">
						<input type="hidden" name="inputHeadId" value="${requestScope.invInputHead.inputHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.invInputHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.invInputHead.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>入库单行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.invInputHead.inputHeadCode}')">入库行</a></li>
									</ul>
									<div id="tabDiv" class="tab-content">
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
				<!-- tab 结束 -->
				
			</div>
		</div>
	</div>
</div>

<!-- pay模式对话框 -->
<div id="headModal"></div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<script src="js/editPage.js"></script>
<script>
	$(document).ready(function() {
		//初始化inputType
		if("${requestScope.invInputHead.inputType}"!=""){
			$("#inputType").val("${requestScope.invInputHead.inputType}");
		}
		//初始化inputSourceType
		if("${requestScope.invInputHead.inputSourceType}"!=""){
			$("#inputSourceType").val("${requestScope.invInputHead.inputSourceType}");
		}
		//初始化warehouseCode
		if("${requestScope.invInputHead.warehouseCode}"!=""){
			$("#warehouseCode").val("${requestScope.invInputHead.warehouseCode}");
		}
		//初始化inputHeadCode只读
		if("${requestScope.invInputHead.inputHeadCode}"!=""){
			$("#inputHeadCode").prop("readonly", true);
		}
		
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		//设置日期插件
		$('#inputDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
		
		//仓库选择效果
		$("#warehouseCode").change(function(){
			$("#warehouseAddress").val($("#warehouseCode").find("option:selected").attr("address"));
		});
		
		//初始化来源类型效果
		setSourceHeadCodeText();
		
		//来源类型切换
		$("#inputSourceType").change(function(){
			if($(this).val()=="PO"){
				$("#inputSourceHeadCodeText").text("采购订单编码");
				$("#inputSourceHeadNameText").text("采购订单名称");
			}
			
			setSourceHeadCodeText();
		});
		
		//选择采购订单
		$("#inputSourceHeadCode").focus(function(){
			if(!$("#inputSourceHeadCode").prop("readonly")){
				getSelectPOModal();
			}
		});
		
		
		
		//表单
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				inputHeadCode : {
					required : true,
				},
				inputType : {
					required : true,
				},
				warehouseCode : {
					required : true,
				},
				inputSourceType : {
					required : true,
				},
				inputSourceHeadCode : {
					required : true,
				},
				inputDate : {
					required : true,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
		
		//初始化tab
		getLineTab("${requestScope.invInputHead.inputHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/invInputLine/getInvInputLineList",
			data: {"inputHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.inputHeadCode}"!="null"&&"${param.inputHeadCode}"!=""&&"${requestScope.invInputHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.invInputHead.approveStatus}"!="REJECT")||
					   "${param.inputHeadCode}"=="null"||"${param.inputHeadCode}"==""){
						$("#tabDiv .btn").hide();
					}
					initControlAuth();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//设置来源头编码字段的效果
	function setSourceHeadCodeText(){
		if($("#inputSourceType").val()==""){
			$("#inputSourceHeadCode").prop("readonly", true);
		}else{
			$("#inputSourceHeadCode").prop("readonly", false);
		}
	}
	
	//返回采购订单选择框
	function getSelectPOModal(){
		$('#selectPODiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/invInputHead/getSelectPOModal",
			data: {"status": "NEW", "poHeadCode": $("#poHeadCode").val(), "poName": $("#poName").val(), 
				   "poType": $("#poType").val(), "vendorCode": $("#vendorCode").val(), "projectCode": $("#projectCode").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content");
				if(data!=""){
					$("#headModal").html(data);
					$('#selectPODiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script>