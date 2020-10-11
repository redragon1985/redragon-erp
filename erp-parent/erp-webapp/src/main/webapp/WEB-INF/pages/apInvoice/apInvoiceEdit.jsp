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
				<div class="ibox-title btn-success btn-outline panel-success collapse-link" title="展开/收起">
					<h5>采购发票头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.payHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-wrench btn-redragon-tools" style="color: black; font-size: 14px;" title="工具栏"></i>
                        </a>
						<ul class="dropdown-menu dropdown-user">
                        	<c:choose>
                        		<c:when test="${requestScope.payHead.approveStatus=='APPROVE' }">
                        			<li><a href="javascript:void(0)" title="查看会计分录" onclick="getVoucherEntryModal('AP_INVOICE','${param.invoiceHeadCode}')"><i class="fa fa-list-alt text-success"></i>&nbsp;&nbsp;<strong>查看会计分录</strong></a></li>
                        			<li><a href="javascript:void(0)" title="重新生成分录" onclick="autoCreateVoucherEntry('${param.invoiceHeadCode}')"><i class="fa fa-calculator text-warning"></i>&nbsp;&nbsp;<strong>重新生成分录</strong></a></li>
                        		</c:when>
                        		<c:otherwise>
                        			<li><a href="javascript:void(0)" title="查看会计分录" onclick="redragonJS.alert('请在审批通过后操作');"><i class="fa fa-list-alt text-success"></i>&nbsp;&nbsp;<strong style="color: silver;">查看会计分录</strong></a></li>
                        			<li><a href="javascript:void(0)" title="重新生成分录" onclick="redragonJS.alert('请在审批通过后操作');"><i class="fa fa-calculator text-warning"></i>&nbsp;&nbsp;<strong style="color: silver;">重新生成分录</strong></a></li>
                        		</c:otherwise>
                        	</c:choose>
                        </ul>
                        &nbsp;
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div id="ibox-content" class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/apInvoiceHead/editApInvoiceHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票编码</strong></label>
							<div class="col-sm-4">
								<input id="invoiceHeadCode" name="invoiceHeadCode" type="text" class="form-control" value="${requestScope.payHead.invoiceHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票来源</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="invoiceSourceType" id="invoiceSourceType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.paySourceTypeMap}" var="paySourceType">
		                        		<option value="${paySourceType.key}">${paySourceType.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong id="paySourceHeadCodeText">来源头编码</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="invoiceSourceHeadCode" name="invoiceSourceHeadCode" type="text" class="form-control" value="${requestScope.payHead.invoiceSourceHeadCode}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong id="paySourceHeadNameText">来源头名称</strong></label>
	                        <div class="col-sm-4">
		                        <input id="paySourceHeadName" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadName}" readonly="readonly">
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>摘要</strong></label>
							<div class="col-sm-4">
								<textarea id="memo" name="memo" rows="3" class="form-control">${requestScope.payHead.memo}</textarea>
							</div>
							
							<label class="col-sm-2 col-form-label"><strong>发票参考号</strong></label>
							<div class="col-sm-4">
								<input id="referenceNumber" name="referenceNumber" type="text" class="form-control" value="${requestScope.payHead.referenceNumber}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>购买方/客户</strong></label>
							<div class="col-sm-4">
								<select class="select2 form-control" name="payer" id="payer">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.vendorOwnMap}" var="vendorOwn">
		                        		<option value="${vendorOwn.key}">${vendorOwn.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>销售方/供应商</strong></label>
	                        <div class="col-sm-4">
	                        	<%-- 
		                        <select class="select2 form-control" name="receiver" id="receiver">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.vendorMap}" var="vendor">
		                        		<option value="${vendor.key}">${vendor.value}</option>
		                        	</c:forEach>
		                        </select>
		                        --%>
		                        <input id="receiverName" name="receiverName" type="text" class="form-control" value="${requestScope.payHead.receiverName}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                    	<label class="col-sm-2 col-form-label"><strong>发票金额（不含税）</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="amount" name="amount" type="text" class="form-control" value="${requestScope.payHead.amount}" >
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>币种</strong></label>
	
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="currencyCode" id="currencyCode">
		                        	<c:forEach items="${requestScope.currencyTypeMap}" var="currencyType">
		                        		<option value="${currencyType.key}">${currencyType.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><strong id="paySourceHeadAmountText">来源单据金额</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="paySourceHeadAmount" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadAmount}" readonly="readonly">
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>历史开票金额</strong></label>
							<div class="col-sm-4 input-group">
		                        <input id="paySourceHeadHISAmount" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadHISAmount}" readonly="readonly">
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>预付款标识</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="prepayFlag" id="prepayFlag">
		                        	<option value="" selected="selected">请选择...</option>
		                            <option value="Y">是</option>
		                            <option value="N">否</option>
		                        </select>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.payStatusMap[requestScope.payHead.status]}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款状态</strong></label>
	                        <div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.paidStatusMap[requestScope.payHead.paidStatus]}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款方式</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="payMode" id="payMode">
		                        	<option value="" selected="selected">请选择...</option>
		                            <c:forEach items="${requestScope.payModeMap}" var="payMode">
		                        		<option value="${payMode.key}">${payMode.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>供应商收款银行</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankName" name="bankName" type="text" class="form-control" value="${requestScope.payHead.bankName}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong>供应商收款分行</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="subBankCode" name="subBankCode" type="text" class="form-control" value="${requestScope.payHead.subBankCode}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>供应商收款银行账户</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankAccount" name="bankAccount" type="text" class="form-control" value="${requestScope.payHead.bankAccount}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票日期</strong></label>
							<div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="invoiceDate" name="invoiceDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.payHead.invoiceDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单人</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.payHead.staffName}" readonly="readonly">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单部门</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.payHead.departmentName}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/apInvoiceHead/getApInvoiceHeadList'">返回</button>&nbsp;
								<c:if test="${param.invoiceHeadCode==null||param.invoiceHeadCode==''||requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.invoiceHeadCode!=null&&param.invoiceHeadCode!=''}">
									<c:if test="${requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="submitInvoiceApprove()">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg btn-redragon-approve" type="button" onclick="approveData()">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg btn-redragon-approve" type="button" onclick="window.location.href='web/apInvoiceHead/updateApproveStatus?code=${requestScope.payHead.invoiceHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="alterData()">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="bankCode" name="bankCode" value="${requestScope.payHead.bankCode}">
						<input type="hidden" id="receiver" name="receiver" value="${requestScope.payHead.receiver}">
						<input type="hidden" id="status" name="status" value="${requestScope.payHead.status}">
						<input type="hidden" id="paidStatus" name="paidStatus" value="${requestScope.payHead.paidStatus}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.payHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.payHead.departmentCode}">
						<input type="hidden" name="invoiceHeadId" value="${requestScope.payHead.invoiceHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.payHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.payHead.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>采购发票行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.payHead.invoiceHeadCode}')">发票行</a></li>
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
<div id="payModal"></div>
<!-- 会计分录 -->
<div id="voucherEntryModal"></div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<!-- editPage -->
<script src="js/editPage.js"></script>

<script>

	//发票历史金额
	var hAmountHis = 0;
	
	$(document).ready(function() {
	
		//获取发票历史金额，用于提交验证
		if($.isNumeric($("#amount").val())){
			hAmountHis = parseFloat($("#amount").val());
		}
	
		//初始化paySourceType
		if("${requestScope.payHead.invoiceSourceType}"!=""){
			$("#invoiceSourceType").val("${requestScope.payHead.invoiceSourceType}");
		}
		//初始化invoiceSourceHeadCode
		if("${requestScope.payHead.invoiceSourceHeadCode}"!=""){
			$("#invoiceSourceHeadCode").val("${requestScope.payHead.invoiceSourceHeadCode}");
		}
		//初始化payer
		if("${requestScope.payHead.payer}"!=""){
			$("#payer").val("${requestScope.payHead.payer}");
		}
		//初始化receiver
		if("${requestScope.payHead.receiver}"!=""){
			$("#receiver").val("${requestScope.payHead.receiver}");
		}
		//初始化currencyCode
		if("${requestScope.payHead.currencyCode}"!=""){
			$("#currencyCode").val("${requestScope.payHead.currencyCode}");
		}
		//初始化prepayFlag
		if("${requestScope.payHead.prepayFlag}"!=""){
			$("#prepayFlag").val("${requestScope.payHead.prepayFlag}");
		}
		//初始化payMode
		if("${requestScope.payHead.payMode}"!=""){
			$("#payMode").val("${requestScope.payHead.payMode}");
		}
		//初始化payHeadCode只读
		if("${requestScope.payHead.invoiceHeadCode}"!=""){
			$("#invoiceHeadCode").prop("readonly", true);
		}
		
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		//设置日期插件
		$('#invoiceDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
		
		//初始化选择头单据字段
		setPaySourceHeadCodeText();
		
		//设置来源头类型选择的效果
		setPaySourceTypeStyle();
		
		//来源类型切换
		$("#invoiceSourceType").change(function(){
			//设置来源头类型选择的效果
			setPaySourceTypeStyle();
			
			//设置选择头单据字段
			setPaySourceHeadCodeText();
		});
		
		//选择采购订单
		$("#invoiceSourceHeadCode").focus(function(){
			if(!$("#invoiceSourceHeadCode").prop("readonly")){
				getSelectPOModal();
			}
		});
		
		//选择银行
		$("#bankName").focus(function(){
			getSelectBankModal();
		});
		
		//初始化付款方式的效果
		initPayMode();
		
		//付款方式切换效果
		$("#payMode").change(function(){
			initPayMode();
		});
		
		
		
		//表单
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				invoiceHeadCode : {
					required : true,
				},
				invoiceSourceType : {
					required : true,
				},
				invoiceSourceHeadCode : {
					required : true,
				},
				payer : {
					required : true,
				},
				receiverName : {
					required : true,
				},
				currencyCode : {
					required : true,
				},
				invoiceDate : {
					required : true,
				},
				prepayFlag : {
					required : true,
				},
				payMode : {
					required : true,
				},
				amount : {
					required : true,
					number : true,
					min: 0,
				},
				/*bbc 只有转账必填
				bankName : {
					required : true,
				},
				bankAccount : {
					required : true,
				},*/
			},
			submitHandler: function(form) {
				var submitFlag = "Y";
				
				if($("#payMode").val()=="transfer"){
					if($("#bankName").val()==""||$("#bankAccount").val()==""){
						redragonJS.alert("银行与账户不能为空");
						submitFlag = "N";
					}
				}
			
				//表单提交
				if(submitFlag=="Y"){
					l.ladda('start');
		        	form.submit();
				}
		    }
		});
		
		//初始化tab
		getLineTab("${requestScope.payHead.invoiceHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/apInvoiceLine/getApInvoiceLineList",
			data: {"invoiceHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.invoiceHeadCode}"!="null"&&"${param.invoiceHeadCode}"!=""&&"${requestScope.payHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.payHead.approveStatus}"!="REJECT")||
					   "${param.invoiceHeadCode}"=="null"||"${param.invoiceHeadCode}"==""){
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
	function setPaySourceHeadCodeText(){
		if($("#invoiceSourceType").val()==""||$("#invoiceSourceType").val()=="INPUT"){
			$("#invoiceSourceHeadCode").prop("readonly", true);
		}else{
			$("#invoiceSourceHeadCode").prop("readonly", false);
		}
	}
	
	//设置来源头类型选择的效果
	function setPaySourceTypeStyle(){
		if($("#invoiceSourceType").val()=="PO"){
			$("#paySourceHeadCodeText").text("采购订单编码");
			$("#paySourceHeadNameText").text("采购订单名称");
			$("#paySourceHeadAmountText").text("采购订单金额");
		}else if($("#invoiceSourceType").val()=="INPUT"){
			$("#paySourceHeadCodeText").text("入库单编码");
			$("#paySourceHeadNameText").text("入库单名称");
			$("#paySourceHeadAmountText").text("采购订单金额");
		}
	}
	
	//付款方式切换效果
	function initPayMode(){
		if($("#payMode").val()==""||$("#payMode").val()=="cash"||$("#payMode").val()=="check"){
			$("#bankName").prop("readonly", true);
			$("#subBankCode").prop("readonly", true);
			$("#bankAccount").prop("readonly", true);
		}else{
			$("#bankName").prop("readonly", false);
			$("#subBankCode").prop("readonly", false);
			$("#bankAccount").prop("readonly", false);
		}
	}
	
	//返回银行选择框
	function getSelectBankModal(){
		$("#bankName").blur();
		if($("#receiver").val()==""){
			redragonJS.alert("请先选择供应商");
		}else{
			$('#selectPODiv').modal('hide');
			redragonJS.loading("ibox-content");
			$.ajax({
				type: "post",
				url: "web/apInvoiceHead/getSelectBankModal",
				data: {"vendorCode": $("#receiver").val()},
				async: false,
				dataType: "html",
				cache: false,
				success: function(data){
					redragonJS.removeLoading("ibox-content");
					if(data!=""){
						$("#payModal").html(data);
						$('#selectPODiv').modal('show');
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					redragonJS.alert(textStatus);
				}
			});
		}
	}
	
	//返回采购订单选择框
	function getSelectPOModal(page){
		$('#selectPODiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/apInvoiceHead/getSelectPOModal",
			data: {"status": "NEW", "poHeadCode": $("#poHeadCode").val(), "poName": $("#poName").val(), "page": page,
				   "poType": $("#poType").val(), "vendorCode": $("#vendorCode").val(), "projectCode": $("#projectCode").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content");
				if(data!=""){
					$("#payModal").html(data);
					$('#selectPODiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//发票头提交
	function submitInvoiceApprove(){
		var submitFlag = "Y";
		
		if($("#lineTab tbody tr").length<=1){
			submitFlag = "N";
			redragonJS.alert("至少新增一行后，才能提交数据");
		}
	
		if(submitFlag=="Y"){
			var lAmount = parseFloat($("#lineAmountSum").text());
			var hAmount = parseFloat($("#amount").val());
			
			if(hAmountHis==hAmount){
				if(lAmount==hAmount){
					window.location.href='web/apInvoiceHead/updateApproveStatus?code=${requestScope.payHead.invoiceHeadCode}&approveStatus=SUBMIT';
				}else{
					redragonJS.alert("发票金额("+hAmount+"元)与发票行合计金额("+lAmount+"元)不相等，金额不匹配无法提交发票");
				}
			}else{
				redragonJS.alert("请先保存发票头");
			}
		}
	}
	
	//重新创建凭证分录
	function autoCreateVoucherEntry(headCode){
		$.ajax({
			type: "post",
			url: "web/apInvoiceHead/autoCreateVoucherEntry",
			data: {"headCode": headCode},
			async: false,
			dataType: "json",
			cache: false,
			success: function(data){
				if(data.errCode==0){
					redragonJS.alert("重新生成分录成功");
				}else{
					redragonJS.alert(data.errMsg);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//审批通过
	function approveData(){
		redragonJS.confirm("确认审批通过？", function(){
			window.location.href='web/apInvoiceHead/updateApproveStatus?code=${requestScope.payHead.invoiceHeadCode}&approveStatus=APPROVE';
		});
	}
	
	//数据变更
	function alterData(){
		redragonJS.confirm("确认变更数据？数据变更后将产生变更历史信息！", function(){
			window.location.href='web/apInvoiceHead/updateApproveStatus?code=${requestScope.payHead.invoiceHeadCode}&approveStatus=UNSUBMIT';
		});
	}
</script>