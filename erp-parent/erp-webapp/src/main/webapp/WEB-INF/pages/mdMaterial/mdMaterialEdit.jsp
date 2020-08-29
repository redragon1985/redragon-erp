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
					<h4>物料编辑&nbsp;<span style="color: black;">（<i class="fa fa-tag"></i>${requestScope.approveStatusMap[requestScope.mdMaterial.approveStatus]}）</span></h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/mdMaterial/editMdMaterial" method="post">
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>物料/事项</label>
							<div class="col-sm-10">
								<select name="materialType" id="materialType" class="form-control">
									<option value="" selected="">请选择</option>
									<c:forEach items="${requestScope.materialTypeMap}" var="materialType">
			                       		<option value="${materialType.key}">${materialType.value}</option>
			                       	</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
					
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><span id="materialCodeLabel">物料</span>编码</label>
							<div class="col-sm-10">
								<input id="materialCode" name="materialCode" type="text" class="form-control" value="${requestScope.mdMaterial.materialCode}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><span id="materialNameLabel">物料</span>名称</label>
							<div class="col-sm-10">
								<input id="materialName" name="materialName" type="text" class="form-control" value="${requestScope.mdMaterial.materialName}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span><span id="categoryCodeLabel">物料</span>类别</label>
							<div class="col-sm-10">
								<input id="categoryDesc" type="text" class="form-control" value="${requestScope.mdMaterial.categoryDesc}" readonly="readonly">
								<input id="categoryCode" name="categoryCode" type="hidden" class="form-control" value="${requestScope.mdMaterial.categoryCode}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">物料单位</label>
							<div class="col-sm-10">
								<select name="materialUnit" id="materialUnit" class="form-control">
									<option value="" selected="">请选择</option>
									<c:forEach items="${requestScope.materialUnitMap}" var="materialUnit">
			                       		<option value="${materialUnit.key}">${materialUnit.value}</option>
			                       	</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">效期（天数）</label>
							<div class="col-sm-10">
								<input id="validDay" name="validDay" type="text" class="form-control" value="${requestScope.mdMaterial.validDay}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">规格</label>
							<div class="col-sm-10">
								<input id="standard" name="standard" type="text" class="form-control" value="${requestScope.mdMaterial.standard}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">规格单位</label>
							<div class="col-sm-10">
								<select name="standardUnit" id="standardUnit" class="form-control">
									<option value="" selected="">请选择</option>
									<c:forEach items="${requestScope.materialUnitMap}" var="materialUnit">
			                       		<option value="${materialUnit.key}">${materialUnit.value}</option>
			                       	</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">包装规格</label>
							<div class="col-sm-10">
								<input id="packStandard" name="packStandard" type="text" class="form-control" value="${requestScope.mdMaterial.packStandard}">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group row">
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>状态</label>
	
	                        <div class="col-sm-10">
	                        <select class="form-control m-b" name="status" id="status">
	                            <option value="Y" selected="selected">有效</option>
	                            <option value="N">无效</option>
	                        </select>
	                        </div>
	                    </div>
	                    <div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-12 col-sm-offset-2 text-right">
								<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/mdMaterial/getMdMaterialList'">返回</button>&nbsp;
								<c:if test="${param.materialId==null||param.materialId==''||requestScope.mdMaterial.approveStatus=='UNSUBMIT'||requestScope.mdMaterial.approveStatus=='REJECT' }">
									<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
								</c:if>
								
								<c:if test="${param.materialId!=null&&param.materialId!=''}">
									<c:if test="${requestScope.mdMaterial.approveStatus=='UNSUBMIT'||requestScope.mdMaterial.approveStatus=='REJECT' }">
										<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/mdMaterial/updateApproveStatus?id=${param.materialId}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdMaterial.approveStatus=='SUBMIT' }">
										<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/mdMaterial/updateApproveStatus?id=${param.materialId}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
										<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/mdMaterial/updateApproveStatus?id=${param.materialId}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
									</c:if>
									<c:if test="${requestScope.mdMaterial.approveStatus=='APPROVE' }">
										<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/mdMaterial/updateApproveStatus?id=${param.materialId}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
									</c:if>
								</c:if>
							</div>
						</div>
						
						<input type="hidden" name="materialId" value="${requestScope.mdMaterial.materialId}">
						<input type="hidden" name="createdDate" value="${requestScope.mdMaterial.createdDate}">
						<input type="hidden" name="createdBy" value="${requestScope.mdMaterial.createdBy}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 导入物料类型选择框 -->
<jsp:include page="pop/materialCategoryTreeModal.jsp"></jsp:include>


<script>
	$(document).ready(function() {
		//初始化status
		if("${requestScope.mdMaterial.status}"!=""){
			$("#status").val("${requestScope.mdMaterial.status}");
		}
		//初始化materialType
		if("${requestScope.mdMaterial.materialType}"!=""){
			$("#materialType").val("${requestScope.mdMaterial.materialType}");
		}
		//初始化materialUnit
		if("${requestScope.mdMaterial.materialUnit}"!=""){
			$("#materialUnit").val("${requestScope.mdMaterial.materialUnit}");
		}
		//初始化standardUnit
		if("${requestScope.mdMaterial.standardUnit}"!=""){
			$("#standardUnit").val("${requestScope.mdMaterial.standardUnit}");
		}
		//初始化code只读
		if("${requestScope.mdMaterial.materialCode}"!=""){
			$("#materialCode").prop("readonly", true);
		}
		
		//物料类型选择框
		$("#categoryDesc").focus(function(){
			$('#treeDiv').modal('show');
		});
		
		//页面加载默认执行
		setMaterialType();
		
		//切换物料或类型效果
		$("#materialType").change(function(){
			setMaterialType();
		});
	
		function setMaterialType(){
			if($("#materialType").val()=="MATERIAL"){
				$("#materialCodeLabel").text("物料");
				$("#materialNameLabel").text("物料");
				$("#categoryCodeLabel").text("物料");
				
				$("#materialUnit").prop("disabled", false);
				$("#validDay").prop("readonly", false);
				$("#standard").prop("readonly", false);
				$("#standardUnit").prop("disabled", false);
				$("#packStandard").prop("readonly", false);
			}else if($("#materialType").val()=="MATTER"){
				$("#materialCodeLabel").text("事项");
				$("#materialNameLabel").text("事项");
				$("#categoryCodeLabel").text("事项");
				
				$("#materialUnit").prop("disabled", true);
				$("#validDay").prop("readonly", true);
				$("#standard").prop("readonly", true);
				$("#standardUnit").prop("disabled", true);
				$("#packStandard").prop("readonly", true);
			}
		}
	
	
		//表单处理
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").valid();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				materialType : {
					required : true,
				},
				materialCode : {
					required : true,
				},
				materialName : {
					required : true,
				},
				categoryCode : {
					required : true,
				},
				/*
				materialUnit : {
					required : true,
				},
				standard : {
					required : true,
				},
				standardUnit : {
					required : true,
				},
				*/
			},
			submitHandler: function(form) {
				var submitFlag = "Y";
				
				//字段验证
				if($("#materialType").val()=="MATERIAL"){
					if($("#materialUnit").val()==""){
						redragonJS.alert("物料单位不能为空");
						submitFlag = "N";
					}else if($("#standard").val()==""){
						redragonJS.alert("规格不能为空");
						submitFlag = "N";
					}else if($("#standardUnit").val()==""){
						redragonJS.alert("规格单位不能为空");
						submitFlag = "N";
					}
				}
				
				if(submitFlag=="Y"){
					l.ladda('start');
		        	form.submit();
				}
				
		    }
		});
	});
</script>