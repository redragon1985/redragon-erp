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
	<jsp:include page="search/hrStaffSearch.jsp"></jsp:include>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
		        <div class="ibox-title">
		            <h4>职员列表</h4>
		            <div class="ibox-tools">
		                <button id="addButton" class="btn btn-success btn-sm" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增职员</span></button>
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
									<th>职员工号</th>
									<th>职员名称</th>
									<th>性别</th>
									<th>入职时间</th>
									<th>职员状态</th>
									<th>手机</th>
									<th>邮箱</th>
									<th>关联用户名</th>
									<th>创建时间</th>
									<th>创建人</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							
							    <c:forEach items="${requestScope.hrStaffList}" var="data" varStatus="status">
								<tr>
									<%-- 
									<td><input type="checkbox" class="i-checks"
										name="input[]"></td>
									--%>	
									<td>${status.count}</td>
									<td>${data.staffCode}</td>
									<td>${data.staffName}</td>
									<td>
										<c:choose>
										   <c:when test="${data.staffSex=='MALE'}">
										       男
										   </c:when>
										   <c:when test="${data.staffSex=='FEMALE'}">
										       女
										   </c:when>
										</c:choose>
									</td>
									<td><fmt:formatDate value="${data.staffEntryDate}" pattern="yyyy-MM-dd"/></td>
									<td>
										<c:choose>
										   <c:when test="${data.staffStatus=='WORK'}">
										       <span class="label label-primary">在职</span>
										   </c:when>
										   <c:when test="${data.staffStatus=='LEAVE'}">
										       <span class="label label-danger">离职</span>
										   </c:when>
										</c:choose>
									</td>
									<td>${data.staffMobile}</td>
									<td>${data.staffEmail}</td>
									<td>${data.username}</td>
									<td><fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${data.createdBy}</td>
									<td>
										<div class="btn-group">
											<button class="btn-white btn btn-xs" onclick="editData(${data.staffId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
											<button class="btn-white btn btn-xs" onclick="deleteData(${data.staffId},'${data.staffCode}')"><i class="fa fa-trash"></i>&nbsp;删除</button>
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
		    window.location.href="web/hrStaff/getHrStaff";
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
		window.location.href="web/hrStaff/getHrStaff?staffId="+id;
	}
	
	function deleteData(id, code) {
		redragonJS.confirm("确认删除数据？", function(){
			window.location.href="web/hrStaff/deleteHrStaff?staffId="+id+"&staffCode="+code;
		});
	}
</script>       