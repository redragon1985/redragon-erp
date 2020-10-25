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
					<h5>销售发票头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.receiptHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-wrench btn-redragon-tools" style="color: black; font-size: 14px;" title="工具栏"></i>
                        </a>
						<ul class="dropdown-menu dropdown-user">
                        	<c:choose>
                        		<c:when test="${requestScope.receiptHead.approveStatus=='APPROVE' }">
                        			<li><a href="javascript:void(0)" title="查看会计分录" onclick="getVoucherEntryModal('AR_INVOICE','${param.invoiceHeadCode}')"><i class="fa fa-list-alt text-success"></i>&nbsp;&nbsp;<strong>查看会计分录</strong></a></li>
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
					<form id="form" action="web/arInvoiceHead/editArInvoiceHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票编码</strong></label>
							<div class="col-sm-4">
								<input id="invoiceHeadCode" name="invoiceHeadCode" type="text" class="form-control" value="${requestScope.receiptHead.invoiceHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票来源</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="invoiceSourceType" id="invoiceSourceType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.receiptSourceTypeMap}" var="receiptSourceType">
		                        		<option value="${receiptSourceType.key}">${receiptSourceType.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong id="receiptSourceHeadCodeText">来源头编码</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="invoiceSourceHeadCode" name="invoiceSourceHeadCode" type="text" class="form-control" value="${requestScope.receiptHead.invoiceSourceHeadCode}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong id="receiptSourceHeadNameText">来源头名称</strong></label>
	                        <div class="col-sm-4">
		                        <input id="receiptSourceHeadName" type="text" class="form-control" value="${requestScope.receiptHead.receiptSourceHeadName}" readonly="readonly">
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong>摘要</strong></label>
							<div class="col-sm-4">
								<textarea id="memo" name="memo" rows="3" class="form-control">${requestScope.receiptHead.memo}</textarea>
							</div>
							
							<label class="col-sm-2 col-form-label"><strong>发票参考号</strong></label>
							<div class="col-sm-4">
								<input id="referenceNumber" name="referenceNumber" type="text" class="form-control" value="${requestScope.receiptHead.referenceNumber}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>购买方/客户</strong></label>
							<div class="col-sm-4">
		                        <input id="payerName" name="payerName" type="text" class="form-control" value="${requestScope.receiptHead.payerName}" readonly="readonly">
								<span class="help-block m-b-none" style="color: silver;">联系人信息：${requestScope.receiptHead.customerContact}</span>
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>销售方/供应商</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="select2 form-control" name="receiver" id="receiver">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.customerOwnMap}" var="customerOwn">
		                        		<option value="${customerOwn.key}">${customerOwn.value}</option>
		                        	</c:forEach>
		                        </select>
		                   	</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>客户地址</strong></label>
							<div class="col-sm-4">
		                        <input id="customerAddress" type="text" class="form-control" value="${requestScope.receiptHead.customerAddress}" readonly="readonly">
	                        </div>
							
	                        <label class="col-sm-2 col-form-label"><strong>客户纳税人识别号</strong></label>
	                        <div class="col-sm-4">
		                        <input id="customerLicenseNumber" type="text" class="form-control" value="${requestScope.receiptHead.customerLicenseNumber}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                    	<label class="col-sm-2 col-form-label"><strong>发票金额（不含税）</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="amount" name="amount" type="text" class="form-control" value="${requestScope.receiptHead.amount}" >
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
	                        <label class="col-sm-2 col-form-label"><strong id="receiptSourceHeadAmountText">来源单据金额</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="receiptSourceHeadAmount" type="text" class="form-control" value="${requestScope.receiptHead.receiptSourceHeadAmount}" readonly="readonly">
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>历史开票金额</strong></label>
							<div class="col-sm-4 input-group">
		                        <input id="receiptSourceHeadHISAmount" type="text" class="form-control" value="${requestScope.receiptHead.receiptSourceHeadHISAmount}" readonly="readonly">
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>预收款标识</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="preReceiptFlag" id="preReceiptFlag">
		                        	<option value="" selected="selected">请选择...</option>
		                            <option value="Y">是</option>
		                            <option value="N">否</option>
		                        </select>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.receiptStatusMap[requestScope.receiptHead.status]}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款状态</strong></label>
	                        <div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.receivedStatusMap[requestScope.receiptHead.receivedStatus]}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款方式</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="receiptMode" id="receiptMode">
		                        	<option value="" selected="selected">请选择...</option>
		                            <c:forEach items="${requestScope.receiptModeMap}" var="receiptMode">
		                        		<option value="${receiptMode.key}">${receiptMode.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>供应商收款银行</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankName" name="bankName" type="text" class="form-control" value="${requestScope.receiptHead.bankName}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong>供应商收款分行</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="subBankCode" name="subBankCode" type="text" class="form-control" value="${requestScope.receiptHead.subBankCode}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>供应商收款银行账户</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankAccount" name="bankAccount" type="text" class="form-control" value="${requestScope.receiptHead.bankAccount}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>发票日期</strong></label>
							<div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="invoiceDate" name="invoiceDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.receiptHead.invoiceDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单人</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.receiptHead.staffName}" readonly="readonly">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单部门</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.receiptHead.departmentName}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/arInvoiceHead/getArInvoiceHeadList'">返回</button>&nbsp;
								<c:if test="${param.invoiceHeadCode==null||param.invoiceHeadCode==''||requestScope.receiptHead.approveStatus=='UNSUBMIT'||requestScope.receiptHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.invoiceHeadCode!=null&&param.invoiceHeadCode!=''}">
									<c:if test="${requestScope.receiptHead.approveStatus=='UNSUBMIT'||requestScope.receiptHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="submitInvoiceApprove()">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.receiptHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg btn-redragon-approve" type="button" onclick="approveData()">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg btn-redragon-approve" type="button" onclick="window.location.href='web/arInvoiceHead/updateApproveStatus?code=${requestScope.receiptHead.invoiceHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.receiptHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="alterData()">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="bankCode" name="bankCode" value="${requestScope.receiptHead.bankCode}">
						<input type="hidden" id="payer" name="payer" value="${requestScope.receiptHead.payer}">
						<input type="hidden" id="status" name="status" value="${requestScope.receiptHead.status}">
						<input type="hidden" id="receivedStatus" name="receivedStatus" value="${requestScope.receiptHead.receivedStatus}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.receiptHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.receiptHead.departmentCode}">
						<input type="hidden" name="invoiceHeadId" value="${requestScope.receiptHead.invoiceHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.receiptHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.receiptHead.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>销售发票行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.receiptHead.invoiceHeadCode}')">发票行</a></li>
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

<!-- receipt模式对话框 -->
<div id="receiptModal"></div>
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
		
		//初始化invoiceSourceType
		if("${requestScope.receiptHead.invoiceSourceType}"!=""){
			$("#invoiceSourceType").val("${requestScope.receiptHead.invoiceSourceType}");
		}
		//初始化invoiceSourceHeadCode
		if("${requestScope.receiptHead.invoiceSourceHeadCode}"!=""){
			$("#invoiceSourceHeadCode").val("${requestScope.receiptHead.invoiceSourceHeadCode}");
		}
		//初始化payer
		if("${requestScope.receiptHead.payer}"!=""){
			$("#payer").val("${requestScope.receiptHead.payer}");
		}
		//初始化receiver
		if("${requestScope.receiptHead.receiver}"!=""){
			$("#receiver").val("${requestScope.receiptHead.receiver}");
		}
		//初始化currencyCode
		if("${requestScope.receiptHead.currencyCode}"!=""){
			$("#currencyCode").val("${requestScope.receiptHead.currencyCode}");
		}
		//初始化preReceiptFlag
		if("${requestScope.receiptHead.preReceiptFlag}"!=""){
			$("#preReceiptFlag").val("${requestScope.receiptHead.preReceiptFlag}");
		}
		//初始化receiptMode
		if("${requestScope.receiptHead.receiptMode}"!=""){
			$("#receiptMode").val("${requestScope.receiptHead.receiptMode}");
		}
		//初始化invoiceHeadCode只读
		if("${requestScope.receiptHead.invoiceHeadCode}"!=""){
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
		setReceiptSourceHeadCodeText();
		
		//设置来源头类型选择的效果
	 	setReceiptSourceTypeStyle();
		
		//来源类型切换
		$("#invoiceSourceType").change(function(){
			//设置来源头类型选择的效果
	 		setReceiptSourceTypeStyle();
	 		
			//设置选择头单据字段
			setReceiptSourceHeadCodeText();
		});
		
		//选择采购订单
		$("#invoiceSourceHeadCode").focus(function(){
			if(!$("#invoiceSourceHeadCode").prop("readonly")){
				getSelectSOModal();
			}
		});
		
		//选择银行
		$("#bankName").focus(function(){
			if(!$("#bankName").prop("readonly")){
				getSelectBankModal();
			}
		});
		
		//初始化收款方式的效果
		initReceiptMode();
		
		//付款方式切换效果
		$("#receiptMode").change(function(){
			initReceiptMode();
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
				payerName : {
					required : true,
				},
				receiver : {
					required : true,
				},
				currencyCode : {
					required : true,
				},
				invoiceDate : {
					required : true,
				},
				preReceiptFlag : {
					required : true,
				},
				receiptMode : {
					required : true,
				},
				amount : {
					required : true,
					number : true,
					min: 0,
				},
				/* bbc 只有转账必填
				bankName : {
					required : true,
				},
				bankAccount : {
					required : true,
				},*/
			},
			submitHandler: function(form) {
				var submitFlag = "Y";
				
				if($("#receiptMode").val()=="transfer"){
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
		getLineTab("${requestScope.receiptHead.invoiceHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/arInvoiceLine/getArInvoiceLineList",
			data: {"invoiceHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.invoiceHeadCode}"!="null"&&"${param.invoiceHeadCode}"!=""&&"${requestScope.receiptHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.receiptHead.approveStatus}"!="REJECT")||
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
	function setReceiptSourceHeadCodeText(){
		if($("#invoiceSourceType").val()==""||$("#invoiceSourceType").val()=="OUTPUT"){
			$("#invoiceSourceHeadCode").prop("readonly", true);
		}else{
			$("#invoiceSourceHeadCode").prop("readonly", false);
		}
	}
	
	//设置来源头类型选择的效果
	function setReceiptSourceTypeStyle(){
		if($("#invoiceSourceType").val()=="SO"){
			$("#receiptSourceHeadCodeText").text("销售订单编码");
			$("#receiptSourceHeadNameText").text("销售订单名称");
			$("#receiptSourceHeadAmountText").text("销售订单金额");
		}else if($("#invoiceSourceType").val()=="OUTPUT"){
			$("#receiptSourceHeadCodeText").text("出库单编码");
			$("#receiptSourceHeadNameText").text("出库单名称");
			$("#receiptSourceHeadAmountText").text("销售订单金额");
		}
	}
	
	//付款方式切换效果
	function initReceiptMode(){
		if($("#receiptMode").val()==""||$("#receiptMode").val()=="cash"||$("#receiptMode").val()=="check"){
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
			$('#selectSODiv').modal('hide');
			redragonJS.loading("ibox-content");
			$.ajax({
				type: "post",
				url: "web/arInvoiceHead/getSelectBankModal",
				data: {"vendorCode": $("#receiver").val()},
				async: false,
				dataType: "html",
				cache: false,
				success: function(data){
					redragonJS.removeLoading("ibox-content");
					if(data!=""){
						$("#receiptModal").html(data);
						$('#selectSODiv').modal('show');
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					redragonJS.alert(textStatus);
				}
			});
		}
	}
	
	//返回采购订单选择框
	function getSelectSOModal(page){
		$('#selectSODiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/arInvoiceHead/getSelectSOModal",
			data: {"status": "NEW", "soHeadCode": $("#soHeadCode").val(), "soName": $("#soName").val(), "page": page,
				   "soType": $("#soType").val(), "customerCode": $("#customerCode").val(), "projectCode": $("#projectCode").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content");
				if(data!=""){
					$("#receiptModal").html(data);
					$('#selectSODiv').modal('show');
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
					window.location.href='web/arInvoiceHead/updateApproveStatus?code=${requestScope.receiptHead.invoiceHeadCode}&approveStatus=SUBMIT'
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
			url: "web/arInvoiceHead/autoCreateVoucherEntry",
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
			window.location.href='web/arInvoiceHead/updateApproveStatus?code=${requestScope.receiptHead.invoiceHeadCode}&approveStatus=APPROVE';
		});
	}
	
	//数据变更
	function alterData(){
		redragonJS.confirm("确认变更数据？数据变更后将产生变更历史信息！", function(){
			window.location.href='web/arInvoiceHead/updateApproveStatus?code=${requestScope.receiptHead.invoiceHeadCode}&approveStatus=UNSUBMIT';
		});
	}
</script>