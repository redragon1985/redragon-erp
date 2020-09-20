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
					<h5>付款头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.payHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div id="ibox-content" class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/apPayHead/editApPayHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款编码</strong></label>
							<div class="col-sm-4">
								<input id="payHeadCode" name="payHeadCode" type="text" class="form-control" value="${requestScope.payHead.payHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款类型</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="payType" id="payType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.payTypeMap}" var="payType">
		                        		<option value="${payType.key}">${payType.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>摘要</strong></label>
							<div class="col-sm-10">
								<textarea id="memo" name="memo" rows="3" class="form-control">${requestScope.payHead.memo}</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商</strong></label>
							<div class="col-sm-10">
								<select class="select2 form-control" name="vendorCode" id="vendorCode">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.vendorMap}" var="vendor">
		                        		<option value="${vendor.key}">${vendor.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                    	<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款金额（含税）</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="amount" name="amount" type="text" class="form-control" value="${requestScope.payHead.amount}" >
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
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款日期</strong></label>
							<div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="payDate" name="payDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.payHead.payDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
								</div>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.payStatusMap[requestScope.payHead.status]}" readonly="readonly">
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
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/apPayHead/getApPayHeadList'">返回</button>&nbsp;
								<c:if test="${param.payHeadCode==null||param.payHeadCode==''||requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.payHeadCode!=null&&param.payHeadCode!=''}">
									<c:if test="${requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="submitInvoiceApprove()">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/apPayHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/apPayHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/apPayHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="status" name="status" value="${requestScope.payHead.status}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.payHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.payHead.departmentCode}">
						<input type="hidden" name="payHeadId" value="${requestScope.payHead.payHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.payHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.payHead.createdBy}">
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
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.payHead.payHeadCode}')">核销行</a></li>
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

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<script>

	//付款历史金额
	var hAmountHis = 0;
	
	$(document).ready(function() {
	
		//获取付款历史金额，用于提交验证
		if($.isNumeric($("#amount").val())){
			hAmountHis = parseFloat($("#amount").val());
		}
	
		//设置收起的title效果
		$(".collapse-link").on("click", function(){
			if($(this).find("h5").find("i.fa-chrome").length==0){
				$(this).find("h5").append("<i class=\"fa fa-chrome fa-spin\"></i>");
			}else{
				$(this).find("h5").find("i.fa-chrome").remove();
			}
			
		});
	
		//初始化payType
		if("${requestScope.payHead.payType}"!=""){
			$("#payType").val("${requestScope.payHead.payType}");
		}
		//初始化vendorCode
		if("${requestScope.payHead.vendorCode}"!=""){
			$("#vendorCode").val("${requestScope.payHead.vendorCode}");
		}
		//初始化currencyCode
		if("${requestScope.payHead.currencyCode}"!=""){
			$("#currencyCode").val("${requestScope.payHead.currencyCode}");
		}
		//初始化payHeadCode只读
		if("${requestScope.payHead.payHeadCode}"!=""){
			$("#payHeadCode").prop("readonly", true);
		}
		
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		//设置日期插件
		$('#payDate').datepicker({
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
				payHeadCode : {
					required : true,
				},
				payType : {
					required : true,
				},
				vendorCode : {
					required : true,
				},
				currencyCode : {
					required : true,
				},
				payDate : {
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
		getLineTab("${requestScope.payHead.payHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/apPayLine/getApPayLineList",
			data: {"payHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.payHeadCode}"!="null"&&"${param.payHeadCode}"!=""&&"${requestScope.payHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.payHead.approveStatus}"!="REJECT")||
					   "${param.payHeadCode}"=="null"||"${param.payHeadCode}"==""){
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
				window.location.href='web/apPayHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=SUBMIT';
			}else{
				redragonJS.alert("付款金额("+hAmount+"元)与核销行合计金额("+lAmount+"元)不相等，金额不匹配无法提交付款");
			}
		}else{
			redragonJS.alert("请先保存付款头");
		}
		
	}
</script>