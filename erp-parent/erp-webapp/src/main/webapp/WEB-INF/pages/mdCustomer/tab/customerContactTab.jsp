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

<div id="ctab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">

		<div class="col-lg-12 text-right" style="padding-right: 0px;">
			<button id="addButton" class="btn btn-info btn-sm" type="button" ><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增联系人</span></button>
			<%-- 
			<button id="searchButton" class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
			--%>
		</div><br/>

		<div class="table-responsive">
			<table class="table table-stripped table-hover table-bordered border-top">

				<thead>
					<tr>
						<th width="5%">序号</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>联系人岗位</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th width="5%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.mdCustomerContactList}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${data.contactName}</td>
						<td>${data.contactTelephone}</td>
						<td>${data.contactPosition}</td>
						<td><fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${data.createdBy}</td>
						<td>
						<c:choose>
						   <c:when test="${data.status=='Y'}">
						       <span class="label label-primary">有效</span>
						   </c:when>
						   <c:otherwise>
						       <span class="label label-danger">无效</span>
						   </c:otherwise>
						</c:choose>
						</td>
						<td>
							<div class="btn-group">
								<button class="btn-white btn btn-xs" onclick="editSysUser(${data.contactId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
								<button class="btn-white btn btn-xs" onclick="deleteSysUser(${data.contactId})"><i class="fa fa-trash"></i>&nbsp;删除</button>
							</div>
						</td>
					</tr>
					</c:forEach>
					
				</tbody>
				<tfoot>
					<%-- 导入页码 --%>
					<jsp:include page="../../common/pages.jsp"></jsp:include>
				</tfoot>
			</table>

		</div>

	</div>
</div>

<div id="addContactModal"></div>

<script>
	$(document).ready(function() {
		$("#addButton").click(function(){
		    getAddContactModal();
		});
		
		$("#searchButton").click(function(){
		    if($("#searchDiv").css("display")=="none"){
		        $("#searchDiv").show();
		        $("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;关闭查询');
		        $("#searchButton").addClass("btn-outline btn-warning");
		        $("#searchButton").blur();
		    }else{
		        $("#searchDiv").hide();
		        $("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;展开查询');
		        $("#searchButton").removeClass("btn-outline btn-warning");
		        $("#searchButton").blur();
		    }
		});
	});
	
	function editSysUser(id){
		getAddContactModal(id);
	}
	
	function deleteSysUser(id) {
		redragonJS.confirm("确认删除数据？", function(){
			deleteContact(id);
		});
	}
	
	function getAddContactModal(id){
		$.ajax({
			type: "post",
			url: "web/mdCustomerContact/getMdCustomerContact",
			data: {"contactId": id, "customerCode": "${param.customerCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#addContactModal").html(data);
					$('#addContactDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	function deleteContact(id){
		$.ajax({
			type: "post",
			url: "web/mdCustomerContact/deleteMdCustomerContact",
			data: JSON.stringify({"contactId": id, "customerCode": "${param.customerCode}"}),
			contentType: "application/json",
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				var json = JSON.parse(data);
				if(json.result=="success"){
					redragonJS.close();
					getContactTab("${param.customerCode}");
				}else{
					redragonJS.alert("删除联系人错误");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script> 