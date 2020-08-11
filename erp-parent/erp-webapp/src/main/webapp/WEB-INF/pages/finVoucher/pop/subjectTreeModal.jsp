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

<div class="modal" id="subjectTreeDiv" tabindex="-1" role="dialog" aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">科目选择</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">
				
				<div id="subjectTreeDiv" class="col-sm-12">
					<c:import url="/web/mdFinanceSubject/getMdFinanceSubjectTreeReadOnly" charEncoding="utf-8"></c:import>
				</div>
				
				<div class="form-group row m-b-none">
					<div class="col-sm-12 col-sm-offset-2 text-right">
						<button class="btn btn-white btn-lg" type="button" data-dismiss="modal">返回</button>
						&nbsp;
						<button id="selectButton" class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">
							&nbsp;&nbsp;确定&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
						</button>
					</div>
				</div>

			</div>

		</div>

	</div>

</div>

<script>
	$(document).ready(function() {
		//确认按钮
		$("#selectButton").click(function(){
			var nodeId = getTreeSelectNodeId();
			var nodeType = getTreeSelectNodeType();
			if(nodeId==""){
				redragonJS.alert("必须选择一个科目");
			}else if(nodeType=="root"){
				redragonJS.alert("不能选择跟节点");
			}else{
				$('#subjectTreeDiv').modal('hide');
				$.ajax({
					type: "post",
					url: "web/mdFinanceSubject/getMdFinanceSubject",
					data: {"subjectId": nodeId},
					async: false,
					dataType: "json",
					cache: false,
					success: function(data){
						if(data!=""){
							var subjectCode = data.subjectCode;
							var subjectName = data.subjectName;
							var segmentCode = data.segmentCode;
							var segmentDesc = data.segmentDesc;
							$(selectSubjectInput).val(segmentDesc);
							$(selectSubjectInput).parent().find("input[name='subjectCode']").val(subjectCode);
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
