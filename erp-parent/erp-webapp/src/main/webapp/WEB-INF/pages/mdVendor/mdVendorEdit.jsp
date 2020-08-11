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
					<h5>供应商基本信息&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.mdVendor.approveStatus]}）</span></h5>
					<div class="ibox-tools">
						<i class="fa fa-chevron-up"></i> 
					</div>
				</div>

				<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<form id="form" action="web/mdVendor/editMdVendor" method="post">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商编码</strong></label>
							<div class="col-sm-4">
								<input id="vendorCode" name="vendorCode" type="text" class="form-control" value="${requestScope.mdVendor.vendorCode}">
							</div>
							
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商名称</strong></label>
							<div class="col-sm-4">
								<input id="vendorName" name="vendorName" type="text" class="form-control" value="${requestScope.mdVendor.vendorName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>供应商区分</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="vendorType" id="vendorType">
	                            <option value="company" selected="selected">公司</option>
	                            <option value="person">个人</option>
	                        </select>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>供应商电话</strong></label>
							<div class="col-sm-4">
								<input id="vendorTelephone" name="vendorTelephone" type="text" class="form-control" value="${requestScope.mdVendor.vendorTelephone}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><strong>供应商地址</strong></label>
							<div class="col-sm-10">
								<input id="vendorAddress" name="vendorAddress" type="text" class="form-control" value="${requestScope.mdVendor.vendorAddress}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>所在国家</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="vendorCountry" id="vendorCountry">
	                        	<c:forEach items="${requestScope.countryMap}" var="country">
	                        		<option value="${country.key}">${country.value}</option>
	                        	</c:forEach>
	                        </select>
	                        </div>

	                        <label class="col-sm-2 col-form-label"><strong>所在城市</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="select2 form-control" name="vendorCity" id="vendorCity">
	                        	<option value="" selected="selected">请选择...</option>
	                        	<c:forEach items="${requestScope.cityMap}" var="city">
	                        		<option value="${city.key}">${city.value}</option>
	                        	</c:forEach>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><strong>供应商分类</strong></label>
	
	                        <div class="col-sm-4">
	                        <select class="form-control" name="vendorCategory" id="vendorCategory">
	                        	<option value="" selected="selected">请选择...</option>
	                            <option value="common">普通客户</option>
	                            <option value="vip">VIP客户</option>
	                        </select>
	                        </div>
	                    
							<label class="col-sm-2 col-form-label"><strong>供应商标签</strong></label>
							<div class="col-sm-4">
								<input id="vendorLabel" name="vendorLabel" type="text" class="form-control" value="${requestScope.mdVendor.vendorLabel}">
								<span class="help-block m-b-none" style="color: silver;">多个标签用空格隔开</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
	                    
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span><strong>状态</strong></label>
	                        <div class="col-sm-4">
		                        <select class="form-control" name="status" id="vendorStatus">
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
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/mdVendor/getMdVendorList'">返回</button>&nbsp;
								<c:if test="${param.vendorCode==null||param.vendorCode==''||requestScope.mdVendor.approveStatus=='UNSUBMIT'||requestScope.mdVendor.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.vendorCode!=null&&param.vendorCode!=''}">
									<c:if test="${requestScope.mdVendor.approveStatus=='UNSUBMIT'||requestScope.mdVendor.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/mdVendor/updateApproveStatus?code=${requestScope.mdVendor.vendorCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdVendor.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/mdVendor/updateApproveStatus?code=${requestScope.mdVendor.vendorCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/mdVendor/updateApproveStatus?code=${requestScope.mdVendor.vendorCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdVendor.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/mdVendor/updateApproveStatus?code=${requestScope.mdVendor.vendorCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						<input type="hidden" name="vendorId" value="${requestScope.mdVendor.vendorId}">
						<input type="hidden" name="createdDate" value="${requestScope.mdVendor.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.mdVendor.createdBy}">
					</form>
				</div>

				<!-- tab 开始 -->
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox ">
							<div class="ibox-title btn-info btn-outline panel-info">
								<h5>供应商其他信息</h5>
								<div class="ibox-tools">
								</div>
							</div>

							<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
								<div class="tabs-container">
									<ul class="nav nav-tabs">
										<li><a class="nav-link active" data-toggle="tab" href="#ltab" onclick="getLicenseTab('${requestScope.mdVendor.vendorCode}')">营业执照</a></li>
										<li><a class="nav-link" data-toggle="tab" href="#ctab" onclick="getContactTab('${requestScope.mdVendor.vendorCode}')">联系人</a></li>
										<li><a class="nav-link" data-toggle="tab" href="#btab" onclick="getBankTab('${requestScope.mdVendor.vendorCode}')">银行信息</a></li>
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
		if("${requestScope.mdVendor.ownFlag}"!=""){
			$("#ownFlag").val("${requestScope.mdVendor.ownFlag}");
		}
		//初始化status
		if("${requestScope.mdVendor.status}"!=""){
			$("#vendorStatus").val("${requestScope.mdVendor.status}");
		}
		//初始化vendorType
		if("${requestScope.mdVendor.vendorType}"!=""){
			$("#vendorType").val("${requestScope.mdVendor.vendorType}");
		}
		//初始化vendorCountry
		if("${requestScope.mdVendor.vendorCountry}"!=""){
			$("#vendorCountry").val("${requestScope.mdVendor.vendorCountry}");
		}
		//初始化vendorCity
		if("${requestScope.mdVendor.vendorCity}"!=""){
			$("#vendorCity").val("${requestScope.mdVendor.vendorCity}");
		}
		//初始化vendorCategory
		if("${requestScope.mdVendor.vendorCategory}"!=""){
			$("#vendorCategory").val("${requestScope.mdVendor.vendorCategory}");
		}
		
		//初始化vendorCode只读
		if("${requestScope.mdVendor.vendorCode}"!=""){
			$("#vendorCode").prop("readonly", true);
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
				vendorCode : {
					required : true,
				},
				vendorName : {
					required : true,
				},
				vendorCountry : {
					required : true,
				},
			},
			submitHandler: function(form) {
				l.ladda('start');
		        form.submit();
		    }
		});
		
		//初始化tab
		getLicenseTab("${requestScope.mdVendor.vendorCode}");
	});
	
	//获取营业执照tab
	function getLicenseTab(vendorCode){
		$.ajax({
			type: "post",
			url: "web/mdVendorLicense/getMdVendorLicense",
			data: {"vendorCode": vendorCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#ltab").addClass("active");
					//隐藏保存按钮
					if("${param.vendorCode}"!="null"&&"${param.vendorCode}"!=""&&"${requestScope.mdVendor.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdVendor.approveStatus}"!="REJECT"){
						$("#tabDiv .ladda-button").hide();
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//获取联系人tab
	function getContactTab(vendorCode){
		$.ajax({
			type: "post",
			url: "web/mdVendorContact/getMdVendorContactList",
			data: {"vendorCode": vendorCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#ctab").addClass("active");
					//隐藏保存按钮
					if("${param.vendorCode}"!="null"&&"${param.vendorCode}"!=""&&"${requestScope.mdVendor.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdVendor.approveStatus}"!="REJECT"){
						$("#tabDiv .btn").hide();
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//获取银行tab
	function getBankTab(vendorCode){
		$.ajax({
			type: "post",
			url: "web/mdVendorBank/getMdVendorBankList",
			data: {"vendorCode": vendorCode},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#tabDiv").html(data);
					$("#btab").addClass("active");
					//隐藏保存按钮
					if("${param.vendorCode}"!="null"&&"${param.vendorCode}"!=""&&"${requestScope.mdVendor.approveStatus}"!="UNSUBMIT"&&"${requestScope.mdVendor.approveStatus}"!="REJECT"){
						$("#tabDiv .btn").hide();
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script>