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

<div id="btab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">

		<div class="col-lg-12 text-right" style="padding-right: 0px;">
			<button id="addButton" class="btn btn-info btn-sm" type="button" ><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增银行</span></button>
			<%-- 
			<button id="searchButton" class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
			--%>
		</div><br/>

		<div class="table-responsive">
			<table class="table table-stripped table-hover table-bordered border-top">

				<thead>
					<tr>
						<th width="5%">序号</th>
						<th>银行编码</th>
						<th>分行编码</th>
						<th>银行账户</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th width="5%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.mdVendorBankList}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${requestScope.bankMap[data.bankCode]}</td>
						<td>${data.subBankCode}</td>
						<td>${data.bankAccount}</td>
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
								<button class="btn-white btn btn-xs" onclick="editData(${data.bankId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
								<button class="btn-white btn btn-xs" onclick="deleteData(${data.bankId})"><i class="fa fa-trash"></i>&nbsp;删除</button>
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

<div id="addBankModal"></div>

<script>
	$(document).ready(function() {
		$("#addButton").click(function(){
		    getAddBankModal();
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
	
	function editData(id){
		getAddBankModal(id);
	}
	
	function deleteData(id) {
		redragonJS.confirm("确认删除数据？", function(){
			deleteBank(id);
		});
	}
	
	function getAddBankModal(id){
		$.ajax({
			type: "post",
			url: "web/mdVendorBank/getMdVendorBank",
			data: {"bankId": id, "vendorCode": "${param.vendorCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#addBankModal").html(data);
					$('#addBankDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	function deleteBank(id){
		$.ajax({
			type: "post",
			url: "web/mdVendorBank/deleteMdVendorBank",
			data: JSON.stringify({"bankId": id, "vendorCode": "${param.vendorCode}"}),
			contentType: "application/json",
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				var json = JSON.parse(data);
				if(json.result=="success"){
					redragonJS.close();
					getBankTab("${param.vendorCode}");
				}else{
					redragonJS.alert("删除银行错误");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script> 