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
	
	<%-- 导入查询框 --%>
	<jsp:include page="search/voucherModelSearch.jsp"></jsp:include>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
		        <div class="ibox-title">
		            <h4>凭证模板列表</h4>
		            <div class="ibox-tools">
		                <button id="addButton" class="btn btn-success btn-sm" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增凭证模板</span></button>
		                <button id="searchButton" class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
		            </div>
		        </div>
				<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
					<div class="table-responsive">
						<table class="table table-striped table-hover table-bordered border-top">
							<thead>
								<tr>
									<%-- 
									<th></th>
									--%>
									<th width="5%">序号</th>
									<th>模板名称</th>
									<th>业务类型</th>
									<th>凭证字</th>
									<th>制单人</th>
									<th width="5%">状态</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							
							    <c:forEach items="${requestScope.finVoucherHeadList}" var="data" varStatus="status">
								<tr>
									<%-- 
									<td><input type="checkbox" class="i-checks"
										name="input[]"></td>
									--%>	
									<td>${status.count}</td>
									<td>${data.modelName}</td>
									<td>${requestScope.voucherBusinessTypeMap[data.businessType]}</td>
									<td>${requestScope.voucherTypeMap[data.voucherType]}</td>
									<td>${data.staffName}</td>
									<td>
									<c:choose>
									   <c:when test="${data.status=='Y'}">
									       <span class="label label-primary">有效</span>
									   </c:when>
									   <c:otherwise>
									       <span class="label label-danger">作废</span>
									   </c:otherwise>
									</c:choose>
									</td>
									<td>
										<div class="btn-group">
											<button class="btn-white btn btn-xs" onclick="editData(${data.voucherHeadId},'${data.voucherHeadCode}')"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
											<button class="btn-white btn btn-xs" onclick="deleteData(${data.voucherHeadId},'${data.voucherHeadCode}')"><i class="fa fa-trash"></i>&nbsp;删除</button>
										</div>
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
							<tfoot>
							    <%-- 导入页码 --%>
								<jsp:include page="../common/pages.jsp"></jsp:include>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>
		
	</div>
</div>

<script>
	$(document).ready(function() {
		/*
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
		*/
		$("#addButton").click(function(){
		    window.location.href="web/finVoucherModelHead/getFinVoucherModelHead";
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
	
	function editData(id, code){
		window.location.href="web/finVoucherModelHead/getFinVoucherModelHead?voucherHeadId="+id+"&voucherHeadCode="+code;
	}
	
	function deleteData(id, code) {
		redragonJS.confirm("确认删除数据？", function(){
			window.location.href="web/finVoucherModelHead/deleteFinVoucherModelHead?voucherHeadId="+id+"&voucherHeadCode="+code;
		});
	}
</script>       