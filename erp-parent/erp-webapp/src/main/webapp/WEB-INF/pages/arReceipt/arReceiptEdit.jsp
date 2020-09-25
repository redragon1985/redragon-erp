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
					<h5>收款头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.receiptHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-wrench btn-redragon-tools" style="color: black; font-size: 14px;" title="工具栏"></i>
                        </a>
						<ul class="dropdown-menu dropdown-user">
                        	<c:choose>
                        		<c:when test="${requestScope.receiptHead.approveStatus=='APPROVE' }">
                        			<li><a href="javascript:void(0)" title="查看会计分录" onclick="getVoucherEntryModal('RECEIPT','${param.receiptHeadCode}')"><i class="fa fa-list-alt text-success"></i>&nbsp;&nbsp;<strong>查看会计分录</strong></a></li>
                        			<li><a href="javascript:void(0)" title="重新生成分录" onclick="autoCreateVoucherEntry('${param.receiptHeadCode}')"><i class="fa fa-calculator text-warning"></i>&nbsp;&nbsp;<strong>重新生成分录</strong></a></li>
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
					<form id="form" action="web/arReceiptHead/editArReceiptHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款编码</strong></label>
							<div class="col-sm-4">
								<input id="receiptHeadCode" name="receiptHeadCode" type="text" class="form-control" value="${requestScope.receiptHead.receiptHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款类型</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="receiptType" id="receiptType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.receiptTypeMap}" var="receiptType">
		                        		<option value="${receiptType.key}">${receiptType.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>摘要</strong></label>
							<div class="col-sm-10">
								<textarea id="memo" name="memo" rows="3" class="form-control">${requestScope.receiptHead.memo}</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户</strong></label>
							<div class="col-sm-10">
								<select class="select2 form-control" name="customerCode" id="customerCode">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.customerMap}" var="customer">
		                        		<option value="${customer.key}">${customer.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                    	<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款金额（含税）</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="amount" name="amount" type="text" class="form-control" value="${requestScope.receiptHead.amount}" >
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>币种</strong></label>
	
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="currencyCode" id="currencyCode">
		                        	<c:forEach items="${requestScope.currencyMap}" var="currency">
		                        		<option value="${currency.key}">${currency.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款日期</strong></label>
							<div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="receiptDate" name="receiptDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.receiptHead.receiptDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.receiptStatusMap[requestScope.receiptHead.status]}" readonly="readonly">
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
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/arReceiptHead/getArReceiptHeadList'">返回</button>&nbsp;
								<c:if test="${param.receiptHeadCode==null||param.receiptHeadCode==''||requestScope.receiptHead.approveStatus=='UNSUBMIT'||requestScope.receiptHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.receiptHeadCode!=null&&param.receiptHeadCode!=''}">
									<c:if test="${requestScope.receiptHead.approveStatus=='UNSUBMIT'||requestScope.receiptHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="submitInvoiceApprove()">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.receiptHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/arReceiptHead/updateApproveStatus?code=${requestScope.receiptHead.receiptHeadCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/arReceiptHead/updateApproveStatus?code=${requestScope.receiptHead.receiptHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.receiptHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/arReceiptHead/updateApproveStatus?code=${requestScope.receiptHead.receiptHeadCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="status" name="status" value="${requestScope.receiptHead.status}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.receiptHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.receiptHead.departmentCode}">
						<input type="hidden" name="receiptHeadId" value="${requestScope.receiptHead.receiptHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.receiptHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.receiptHead.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>付款核销行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.receiptHead.receiptHeadCode}')">核销行</a></li>
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

	//付款历史金额
	var hAmountHis = 0;
	
	$(document).ready(function() {
	
		//获取付款历史金额，用于提交验证
		if($.isNumeric($("#amount").val())){
			hAmountHis = parseFloat($("#amount").val());
		}
	
		//初始化receiptType
		if("${requestScope.receiptHead.receiptType}"!=""){
			$("#receiptType").val("${requestScope.receiptHead.receiptType}");
		}
		//初始化customerCode
		if("${requestScope.receiptHead.customerCode}"!=""){
			$("#customerCode").val("${requestScope.receiptHead.customerCode}");
		}
		//初始化currencyCode
		if("${requestScope.receiptHead.currencyCode}"!=""){
			$("#currencyCode").val("${requestScope.receiptHead.currencyCode}");
		}
		//初始化receiptHeadCode只读
		if("${requestScope.receiptHead.receiptHeadCode}"!=""){
			$("#receiptHeadCode").prop("readonly", true);
		}
		
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		//设置日期插件
		$('#receiptDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
		
		
		
		//表单
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				receiptHeadCode : {
					required : true,
				},
				receiptType : {
					required : true,
				},
				customerCode : {
					required : true,
				},
				currencyCode : {
					required : true,
				},
				receiptDate : {
					required : true,
				},
				amount : {
					required : true,
					number : true,
					min: 0,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
		
		//初始化tab
		getLineTab("${requestScope.receiptHead.receiptHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/arReceiptLine/getArReceiptLineList",
			data: {"receiptHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.receiptHeadCode}"!="null"&&"${param.receiptHeadCode}"!=""&&"${requestScope.receiptHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.receiptHead.approveStatus}"!="REJECT")||
					   "${param.receiptHeadCode}"=="null"||"${param.receiptHeadCode}"==""){
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
	
	//付款头提交
	function submitInvoiceApprove(){
		var lAmount = parseFloat($("#lineAmountSum").text());
		var hAmount = parseFloat($("#amount").val());
		
		if(hAmountHis==hAmount){
			if(lAmount==hAmount){
				window.location.href='web/arReceiptHead/updateApproveStatus?code=${requestScope.receiptHead.receiptHeadCode}&approveStatus=SUBMIT';
			}else{
				redragonJS.alert("收款金额("+hAmount+"元)与核销行合计金额("+lAmount+"元)不相等，金额不匹配无法提交收款");
			}
		}else{
			redragonJS.alert("请先保存收款头");
		}
		
	}
	
	//重新创建凭证分录
	function autoCreateVoucherEntry(headCode){
		$.ajax({
			type: "post",
			url: "web/arReceiptHead/autoCreateVoucherEntry",
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
</script>