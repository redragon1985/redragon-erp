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
					<h5>出库单头信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.invOutputHead.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-wrench btn-redragon-tools" style="color: black; font-size: 14px;" title="工具栏"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                        	<c:choose>
                        		<c:when test="${requestScope.invOutputHead.approveStatus=='APPROVE' }">
                        			<li><a href="javascript:void(0)" title="查看会计分录" onclick="getVoucherEntryModal('OUTPUT','${param.outputHeadCode}')"><i class="fa fa-list-alt text-success"></i>&nbsp;&nbsp;<strong>查看会计分录</strong></a></li>
                        			<li><a href="javascript:void(0)" title="重新生成分录" onclick="autoCreateVoucherEntry('${param.outputHeadCode}')"><i class="fa fa-calculator text-warning"></i>&nbsp;&nbsp;<strong>重新生成分录</strong></a></li>
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
					<form id="form" action="web/invOutputHead/editInvOutputHead" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>出库单编码</strong></label>
							<div class="col-sm-4">
								<input id="outputHeadCode" name="outputHeadCode" type="text" class="form-control" value="${requestScope.invOutputHead.outputHeadCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>出库类型</strong></label>
							<div class="col-sm-4">
								<select class="form-control" name="outputType" id="outputType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.outputTypeMap}" var="outputType">
		                        		<option value="${outputType.key}">${outputType.value}</option>
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
								<input id="warehouseAddress" type="text" class="form-control" value="${requestScope.invOutputHead.warehouseAddress}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>出库来源</strong></label>
	                        <div class="col-sm-4">
	                        	<select class="form-control" name="outputSourceType" id="outputSourceType">
		                        	<option value="" selected="selected">请选择...</option>
		                        	<c:forEach items="${requestScope.outputSourceTypeMap}" var="outputSourceType">
		                        		<option value="${outputSourceType.key}">${outputSourceType.value}</option>
		                        	</c:forEach>
		                        </select>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong id="outputSourceHeadCodeText">来源头编码</strong></label>
	                        <div class="col-sm-4">
	                        	<input id="outputSourceHeadCode" name="outputSourceHeadCode" type="text" class="form-control" value="${requestScope.invOutputHead.outputSourceHeadCode}">
	                        </div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong id="outputSourceHeadNameText">来源头名称</strong></label>
	                        <div class="col-sm-4">
		                        <input id="outputSourceHeadName" type="text" class="form-control" value="${requestScope.invOutputHead.outputSourceHeadName}" readonly="readonly">
	                        </div>
	                        
							<label class="col-sm-2 col-form-label"><strong>备注</strong></label>
							<div class="col-sm-4">
								<input id="memo" name="memo" type="text" class="form-control" value="${requestScope.invOutputHead.memo}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户</strong></label>
							<div class="col-sm-4">
								<input id="customerName" type="text" class="form-control" value="${requestScope.invOutputHead.customerName}" readonly="readonly">
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户联系人</strong></label>
	                        <div class="col-sm-4">
		                        <input id="customerContactDesc" type="text" class="form-control" value="${requestScope.invOutputHead.customerContactDesc}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>出库日期</strong></label>
	                        <div class="col-sm-4">
	                        	<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="outputDate" name="outputDate" type="text" class="form-control" value="${requestScope.invOutputHead.outputDate}" autocomplete="off">
								</div>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
	                        	<input type="text" class="form-control" value="${requestScope.outputStatusMap[requestScope.invOutputHead.status]}" readonly="readonly">
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单人</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.invOutputHead.staffName}" readonly="readonly">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>制单部门</strong></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.invOutputHead.departmentName}" readonly="readonly">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/invOutputHead/getInvOutputHeadList'">返回</button>&nbsp;
								<c:if test="${param.outputHeadCode==null||param.outputHeadCode==''||requestScope.invOutputHead.approveStatus=='UNSUBMIT'||requestScope.invOutputHead.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.outputHeadCode!=null&&param.outputHeadCode!=''}">
									<c:if test="${requestScope.invOutputHead.approveStatus=='UNSUBMIT'||requestScope.invOutputHead.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/invOutputHead/updateApproveStatus?code=${requestScope.invOutputHead.outputHeadCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.invOutputHead.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/invOutputHead/updateApproveStatus?code=${requestScope.invOutputHead.outputHeadCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/invOutputHead/updateApproveStatus?code=${requestScope.invOutputHead.outputHeadCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.invOutputHead.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/invOutputHead/updateApproveStatus?code=${requestScope.invOutputHead.outputHeadCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						
						<input type="hidden" id="status" name="status" value="${requestScope.invOutputHead.status}">
						<input type="hidden" id="staffCode" name="staffCode" value="${requestScope.invOutputHead.staffCode}">
						<input type="hidden" id="departmentCode" name="departmentCode" value="${requestScope.invOutputHead.departmentCode}">
						<input type="hidden" name="outputHeadId" value="${requestScope.invOutputHead.outputHeadId}">
						<input type="hidden" name="createdDate" value="${requestScope.invOutputHead.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.invOutputHead.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>出库单行信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div id="ibox-content1" class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#lineTab" onclick="getLineTab('${requestScope.invOutputHead.outputHeadCode}')">出库行</a></li>
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
<!-- 会计分录 -->
<div id="voucherEntryModal"></div>

<!-- select2 -->
<script src="js/plugins/select2/select2.full.min.js"></script>

<!-- editPage -->
<script src="js/editPage.js"></script>

<script>
	$(document).ready(function() {
		//初始化outputType
		if("${requestScope.invOutputHead.outputType}"!=""){
			$("#outputType").val("${requestScope.invOutputHead.outputType}");
		}
		//初始化outputSourceType
		if("${requestScope.invOutputHead.outputSourceType}"!=""){
			$("#outputSourceType").val("${requestScope.invOutputHead.outputSourceType}");
		}
		//初始化warehouseCode
		if("${requestScope.invOutputHead.warehouseCode}"!=""){
			$("#warehouseCode").val("${requestScope.invOutputHead.warehouseCode}");
		}
		//初始化outputHeadCode只读
		if("${requestScope.invOutputHead.outputHeadCode}"!=""){
			$("#outputHeadCode").prop("readonly", true);
		}
		
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		//设置日期插件
		$('#outputDate').datepicker({
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
		$("#outputSourceType").change(function(){
			if($(this).val()=="SO"){
				$("#outputSourceHeadCodeText").text("销售订单编码");
				$("#outputSourceHeadNameText").text("销售订单名称");
			}
			
			setSourceHeadCodeText();
		});
		
		//选择销售订单
		$("#outputSourceHeadCode").focus(function(){
			if(!$("#outputSourceHeadCode").prop("readonly")){
				getSelectSOModal();
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
				outputHeadCode : {
					required : true,
				},
				outputType : {
					required : true,
				},
				warehouseCode : {
					required : true,
				},
				outputSourceType : {
					required : true,
				},
				outputSourceHeadCode : {
					required : true,
				},
				outputDate : {
					required : true,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
		
		//初始化tab
		getLineTab("${requestScope.invOutputHead.outputHeadCode}");
	});
	
	//获取行tab
	function getLineTab(code){
		$.ajax({
			type: "post",
			url: "web/invOutputLine/getInvOutputLineList",
			data: {"outputHeadCode": code},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#lineTab").addClass("active");
					//隐藏保存按钮
					if(("${param.outputHeadCode}"!="null"&&"${param.outputHeadCode}"!=""&&"${requestScope.invOutputHead.approveStatus}"!="UNSUBMIT"&&"${requestScope.invOutputHead.approveStatus}"!="REJECT")||
					   "${param.outputHeadCode}"=="null"||"${param.outputHeadCode}"==""){
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
		if($("#outputSourceType").val()==""){
			$("#outputSourceHeadCode").prop("readonly", true);
		}else{
			$("#outputSourceHeadCode").prop("readonly", false);
		}
	}
	
	//返回销售订单选择框
	function getSelectSOModal(){
		$('#selectSODiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/invOutputHead/getSelectSOModal",
			data: {"status": "NEW", "soHeadCode": $("#soHeadCode").val(), "soName": $("#soName").val(), 
				   "soType": $("#soType").val(), "customerCode": $("#customerCode").val(), "projectCode": $("#projectCode").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content");
				if(data!=""){
					$("#headModal").html(data);
					$('#selectSODiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//重新创建凭证分录
	function autoCreateVoucherEntry(headCode){
		$.ajax({
			type: "post",
			url: "web/invOutputHead/autoCreateVoucherEntry",
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