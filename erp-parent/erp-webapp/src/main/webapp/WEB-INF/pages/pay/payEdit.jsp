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
					<h5>付款单头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.payHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div id="ibox-content" class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/payHead/editPayHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款单编码</strong></label>
							<div class="col-sm-4">
								<input id="payHeadCode" name="payHeadCode" type="text" class="form-control" value="${requestScope.payHead.payHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款来源</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="paySourceType" id="paySourceType">
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
	                        	<input id="paySourceHeadCode" name="paySourceHeadCode" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadCode}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong id="paySourceHeadNameText">来源头名称</strong></label>
	                        <div class="col-sm-4">
		                        <input id="paySourceHeadName" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadName}" readonly="readonly">
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong>摘要</strong></label>
							<div class="col-sm-10">
								<textarea id="memo" name="memo" rows="3" class="form-control">${requestScope.payHead.memo}</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款方</strong></label>
							<div class="col-sm-4">
								<select class="select2 form-control" name="payer" id="payer">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.vendorOwnMap}" var="vendorOwn">
		                        		<option value="${vendorOwn.key}">${vendorOwn.value}</option>
		                        	</c:forEach>
		                        </select>
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>收款方</strong></label>
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
	                    	<label class="col-sm-2 col-form-label"><strong>本次付款金额</strong></label>
	                        <div class="col-sm-4 input-group">
		                        <input id="amount" type="text" class="form-control" value="${requestScope.payHead.amount}" readonly="readonly">
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
	                    
							<label class="col-sm-2 col-form-label"><strong>历史付款金额</strong></label>
							<div class="col-sm-4 input-group">
		                        <input id="paySourceHeadHISAmount" type="text" class="form-control" value="${requestScope.payHead.paySourceHeadHISAmount}" readonly="readonly">
	                        	<span class="input-group-addon">(元)</span>
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款状态</strong></label>
	                        <div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.paidStatusMap[requestScope.payHead.paidStatus]}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.payStatusMap[requestScope.payHead.status]}" readonly="readonly">
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
							<label class="col-sm-2 col-form-label"><strong>收款银行</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankName" name="bankName" type="text" class="form-control" value="${requestScope.payHead.bankName}">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><strong>收款分行</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="subBankCode" name="subBankCode" type="text" class="form-control" value="${requestScope.payHead.subBankCode}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
							<label class="col-sm-2 col-form-label"><strong>收款银行账户</strong></label>
	                        <div class="col-sm-4">
		                        <input id="bankAccount" name="bankAccount" type="text" class="form-control" value="${requestScope.payHead.bankAccount}" readonly="readonly">
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>付款时间</strong></label>
							<div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="payDate" name="payDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.payHead.payDate}" pattern="yyyy-MM-dd"/>" autocomplete="off">
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
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/payHead/getPayHeadList'">返回</button>&nbsp;
								<c:if test="${param.payHeadCode==null||param.payHeadCode==''||requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.payHeadCode!=null&&param.payHeadCode!=''}">
									<c:if test="${requestScope.payHead.approveStatus=='UNSUBMIT'||requestScope.payHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/payHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/payHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/payHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.payHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/payHead/updateApproveStatus?code=${requestScope.payHead.payHeadCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
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
								<h5>付款单行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.payHead.payHeadCode}')">付款行</a></li>
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
	$(document).ready(function() {
	
		//设置收起的title效果
		$(".collapse-link").on("click", function(){
			if($(this).find("h5").html().indexOf("</i>")==-1){
				$(this).find("h5").append("<i class=\"fa fa-chrome fa-spin\"></i>");
			}else{
				$(this).find("h5").find("i").remove();
			}
			
		});
	
		//初始化paySourceType
		if("${requestScope.payHead.paySourceType}"!=""){
			$("#paySourceType").val("${requestScope.payHead.paySourceType}");
		}
		//初始化paySourceHeadCode
		if("${requestScope.payHead.paySourceHeadCode}"!=""){
			$("#paySourceHeadCode").val("${requestScope.payHead.paySourceHeadCode}");
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
		
		//初始化选择头单据字段
		setPaySourceHeadCodeText();
		
		//来源类型切换
		$("#paySourceType").change(function(){
			if($(this).val()=="PO"){
				$("#paySourceHeadCodeText").text("采购订单编码");
				$("#paySourceHeadNameText").text("采购订单名称");
				$("#paySourceHeadAmountText").text("采购订单金额");
			}else if($(this).val()=="INPUT"){
				$("#paySourceHeadCodeText").text("入库单编码");
				$("#paySourceHeadNameText").text("入库单名称");
				$("#paySourceHeadAmountText").text("采购订单金额");
			}
			//设置选择头单据字段
			setPaySourceHeadCodeText();
		});
		
		//选择采购订单
		$("#paySourceHeadCode").focus(function(){
			if(!$("#paySourceHeadCode").prop("readonly")){
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
				payHeadCode : {
					required : true,
				},
				paySourceType : {
					required : true,
				},
				paySourceHeadCode : {
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
				payDate : {
					required : true,
				},
				prepayFlag : {
					required : true,
				},
				payMode : {
					required : true,
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
			url: "web/payLine/getPayLineList",
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
	
	//设置来源头编码字段的效果
	function setPaySourceHeadCodeText(){
		if($("#paySourceType").val()==""||$("#paySourceType").val()=="INPUT"){
			$("#paySourceHeadCode").prop("readonly", true);
		}else{
			$("#paySourceHeadCode").prop("readonly", false);
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
			redragonJS.alert("请先选择收款方");
		}else{
			$('#selectPODiv').modal('hide');
			redragonJS.loading("ibox-content");
			$.ajax({
				type: "post",
				url: "web/payHead/getSelectBankModal",
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
	function getSelectPOModal(){
		$('#selectPODiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/payHead/getSelectPOModal",
			data: {"status": "NEW", "poHeadCode": $("#poHeadCode").val(), "poName": $("#poName").val(), 
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
</script>