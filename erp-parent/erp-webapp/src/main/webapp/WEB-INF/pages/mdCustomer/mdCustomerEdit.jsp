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
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
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
					<h5>客户基本信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.mdCustomer.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/mdCustomer/editMdCustomer" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户编码</strong></label>
							<div class="col-sm-4">
								<input id="customerCode" name="customerCode" type="text" class="form-control" value="${requestScope.mdCustomer.customerCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户名称</strong></label>
							<div class="col-sm-4">
								<input id="customerName" name="customerName" type="text" class="form-control" value="${requestScope.mdCustomer.customerName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>客户区分</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="customerType" id="customerType">
	                            <option value="company" selected="selected">公司</option>
	                            <option value="person">个人</option>
	                        </select>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>客户电话</strong></label>
							<div class="col-sm-4">
								<input id="customerTelephone" name="customerTelephone" type="text" class="form-control" value="${requestScope.mdCustomer.customerTelephone}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong>客户地址</strong></label>
							<div class="col-sm-10">
								<input id="customerAddress" name="customerAddress" type="text" class="form-control" value="${requestScope.mdCustomer.customerAddress}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>所在国家</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="customerCountry" id="customerCountry">
	                        	<c:forEach items="${requestScope.countryMap}" var="country">
	                        		<option value="${country.key}">${country.value}</option>
	                        	</c:forEach>
	                        </select>
	                        </div>

	                        <label class="col-sm-2 col-form-label"><strong>所在城市</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="select2 form-control" name="customerCity" id="customerCity">
	                        	<option value="" selected="selected">请选择...</option>
	                        	<c:forEach items="${requestScope.cityMap}" var="city">
	                        		<option value="${city.key}">${city.value}</option>
	                        	</c:forEach>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><strong>客户分类</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="customerCategory" id="customerCategory">
	                        	<option value="" selected="selected">请选择...</option>
	                            <option value="common">普通客户</option>
	                            <option value="vip">VIP客户</option>
	                        </select>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>客户标签</strong></label>
							<div class="col-sm-4">
								<input id="customerLabel" name="customerLabel" type="text" class="form-control" value="${requestScope.mdCustomer.customerLabel}">
								<span class="help-block m-b-none" style="color: silver;">多个标签用空格隔开</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
		                        <select class="form-control" name="status" id="customerStatus">
		                            <option value="Y" selected="selected">有效</option>
		                            <option value="N">无效</option>
		                        </select>
	                        </div>
	                        
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>本公司标识</strong></label>
	                        <div class="col-sm-4">
		                        <select class="form-control" name="ownFlag" id="ownFlag">
		                            <option value="Y">是</option>
		                            <option value="N" selected="selected">否</option>
		                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/mdCustomer/getMdCustomerList'">返回</button>&nbsp;
								<c:if test="${param.customerCode==null||param.customerCode==''||requestScope.mdCustomer.approveStatus=='UNSUBMIT'||requestScope.mdCustomer.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.customerCode!=null&&param.customerCode!=''}">
									<c:if test="${requestScope.mdCustomer.approveStatus=='UNSUBMIT'||requestScope.mdCustomer.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/mdCustomer/updateApproveStatus?code=${requestScope.mdCustomer.customerCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdCustomer.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/mdCustomer/updateApproveStatus?code=${requestScope.mdCustomer.customerCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/mdCustomer/updateApproveStatus?code=${requestScope.mdCustomer.customerCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdCustomer.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/mdCustomer/updateApproveStatus?code=${requestScope.mdCustomer.customerCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						<input type="hidden" name="customerId" value="${requestScope.mdCustomer.customerId}">
						<input type="hidden" name="createdDate" value="${requestScope.mdCustomer.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.mdCustomer.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>客户其他信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#ltab" onclick="getLicenseTab('${requestScope.mdCustomer.customerCode}')">营业执照</a></li>
										<li><a class="nav-link" data-toggle="tab" href="#ctab" onclick="getContactTab('${requestScope.mdCustomer.customerCode}')">联系人</a></li>
										<li><a class="nav-link" data-toggle="tab" href="#btab" onclick="getBankTab('${requestScope.mdCustomer.customerCode}')">银行信息</a></li>
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
	
		//初始化ownFlag
		if("${requestScope.mdCustomer.ownFlag}"!=""){
			$("#ownFlag").val("${requestScope.mdCustomer.ownFlag}");
		}
		//初始化status
		if("${requestScope.mdCustomer.status}"!=""){
			$("#customerStatus").val("${requestScope.mdCustomer.status}");
		}
		//初始化customerType
		if("${requestScope.mdCustomer.customerType}"!=""){
			$("#customerType").val("${requestScope.mdCustomer.customerType}");
		}
		//初始化customerCountry
		if("${requestScope.mdCustomer.customerCountry}"!=""){
			$("#customerCountry").val("${requestScope.mdCustomer.customerCountry}");
		}
		//初始化customerCity
		if("${requestScope.mdCustomer.customerCity}"!=""){
			$("#customerCity").val("${requestScope.mdCustomer.customerCity}");
		}
		//初始化customerCategory
		if("${requestScope.mdCustomer.customerCategory}"!=""){
			$("#customerCategory").val("${requestScope.mdCustomer.customerCategory}");
		}
		
		//初始化customerCode只读
		if("${requestScope.mdCustomer.customerCode}"!=""){
			$("#customerCode").prop("readonly", true);
		}
	
		//初始化select2
		$('.select2').select2({width: "100%"});
		
		
		//表单提交
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				customerCode : {
					required : true,
				},
				customerName : {
					required : true,
				},
				customerCountry : {
					required : true,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
		
		//初始化tab
		getLicenseTab("${requestScope.mdCustomer.customerCode}");
	});
	
	//获取营业执照tab
	function getLicenseTab(customerCode){
		$.ajax({
			type: "post",
			url: "web/mdCustomerLicense/getMdCustomerLicense",
			data: {"customerCode": customerCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#ltab").addClass("active");
					//隐藏保存按钮
					if(("${param.customerCode}"!="null"&&"${param.customerCode}"!=""&&"${requestScope.mdCustomer.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdCustomer.approveStatus}"!="REJECT")||"${param.customerCode}"=="null"||"${param.customerCode}"==""){
						$("#tabDiv .ladda-button").hide();
					}
					initControlAuth();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//获取联系人tab
	function getContactTab(customerCode){
		$.ajax({
			type: "post",
			url: "web/mdCustomerContact/getMdCustomerContactList",
			data: {"customerCode": customerCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#ctab").addClass("active");
					//隐藏保存按钮
					if(("${param.customerCode}"!="null"&&"${param.customerCode}"!=""&&"${requestScope.mdCustomer.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdCustomer.approveStatus}"!="REJECT")||"${param.customerCode}"=="null"||"${param.customerCode}"==""){
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
	
	//获取银行tab
	function getBankTab(customerCode){
		$.ajax({
			type: "post",
			url: "web/mdCustomerBank/getMdCustomerBankList",
			data: {"customerCode": customerCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#btab").addClass("active");
					//隐藏保存按钮
					if(("${param.customerCode}"!="null"&&"${param.customerCode}"!=""&&"${requestScope.mdCustomer.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdCustomer.approveStatus}"!="REJECT")||"${param.customerCode}"=="null"||"${param.customerCode}"==""){
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
</script>