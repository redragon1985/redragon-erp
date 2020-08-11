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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div class="modal" id="addDiv" tabindex="-1" role="dialog" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">节点编辑</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px;">
				<%-- 导入提示信息框 --%>
				<c:if test="${requestScope.hints!=null&&requestScope.hints!=''}">
					<jsp:include page="../../common/alert/alert.jsp">
						<jsp:param value="hint" name="alertType"/>
						<jsp:param value="${fn:replace(requestScope.hints,';', '<br/>')}" name="alertMessage"/>
					</jsp:include>
				</c:if>
			
				<form id="form">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>节点编码</label>
						<div class="col-sm-9">
							<input id="${requestScope.nodeCode}" name="${requestScope.nodeCode}" type="text"
								class="form-control" value="${requestScope.nodeCodeValue}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>节点名称</label>
						<div class="col-sm-9">
							<input id="${requestScope.nodeName}" name="${requestScope.nodeName}" type="text" 
								class="form-control" value="${requestScope.nodeNameValue}">
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row">
						<label class="col-sm-3 col-form-label"><span class="text-danger">*</span>状态</label>

						<div class="col-sm-9">
							<select class="form-control m-b" name="status" id="status">
								<option value="Y" selected="selected">有效</option>
								<option value="N">无效</option>
							</select>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group row m-b-none">
						<div class="col-sm-12 col-sm-offset-2 text-right">
							<button class="btn btn-white btn-lg" type="button"
								data-dismiss="modal">返回</button>
							&nbsp;
							<button
								class="ladda-button ladda-button-demo btn btn-primary btn-lg"
								data-style="expand-right">
								&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
							</button>
						</div>
					</div>
					
					<input type="hidden" id="parentId" name="parentId" value="${param.parentId}"> 
					<input type="hidden" id="${requestScope.nodeId}" name="${requestScope.nodeId}" value="${param.nodeId}"> 
					<input type="hidden" id="createdDate" name="createdDate" value="${requestScope.nodeCreatedDateValue}"> 
					<input type="hidden" id="createdBy" name="createdBy" value="${requestScope.nodeCreatedByValue}">
				</form>
			</div>

			<%-- 
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">确定</button>
			</div>
			--%>
		</div>

	</div>

</div>

<script>
	$(document).ready(function() {
		//初始化status
		if("${requestScope.nodeStatusValue}"!=""){
			$("#status").val("${requestScope.nodeStatusValue}");
		}
		//初始化code只读
		if("${requestScope.nodeCodeValue}"!=""){
			$("#${requestScope.nodeCode}").prop("readonly", true);
		}
		
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#form").valid();
			//l.ladda('stop');
		});

		$("#form").validate({
			rules : {
				${requestScope.nodeCode} : {
					required : true,
				},
				${requestScope.nodeName} : {
					required : true,
				}
			},
			submitHandler: function(form) {
				l.ladda('start');
				//异步提交
				$.ajax({
					type: "post",
					url: "${requestScope.editNodeUrl}?parentId="+$("#parentId").val(),
					data: JSON.stringify({"${requestScope.nodeCode}": $("#${requestScope.nodeCode}").val(), "${requestScope.nodeName}": $("#${requestScope.nodeName}").val(), "status": $("#status").val(), 
							 	 "${requestScope.nodeId}": $("#${requestScope.nodeId}").val(), "createdDate": $("#createdDate").val(), "createdBy": $("#createdBy").val()}),
					async: false,
					dataType: "html",
					contentType: 'application/json',
					cache: false,
					success: function(data){
						//格式化json
						var json = $.parseJSON(data);
						
						if(json.result=="success"){
							if(json.editStatus=="insert"){
								//调用jstree新增节点效果
	        					addNodeForJsTree(json.nodeId, json.nodeText);
							}else if(json.editStatus=="update"){
								//调用jstree编辑节点效果
	        					editNodeForJsTree(json.nodeText);
							}else if(json.editStatus=="addRoot"){
								window.location.reload();
							}
							
	        				$('#addDiv').modal('hide');
						}else{
							redragonJS.alert("新增节点错误");
							$('#addDiv').modal('hide');
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						redragonJS.alert(textStatus);
					}
				});
		    }
		});
		
	});
	
</script>
