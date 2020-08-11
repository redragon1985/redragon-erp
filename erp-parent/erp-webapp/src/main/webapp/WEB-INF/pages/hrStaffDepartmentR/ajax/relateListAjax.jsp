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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<div class="ibox ">

		<div class="ibox-title">
			<h4>关联职员和岗位</h4>
			<div class="ibox-tools">
				<button id="addRelateButton" class="btn btn-success btn-sm" type="button">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增关联</span>
				</button>

			</div>
		</div>

		<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
			<div class="table-responsive">
				<table
					class="table table-striped table-hover table-bordered border-top">
					<thead>
						<tr>
							<th width="10%">序号</th>
							<th>职员</th>
							<th>岗位</th>
							<th>组织</th>
							<th width="5%">状态</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.relateList}" var="data" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${data[1].staffName}</td>
								<td>${data[2].positionName}</td>
								<td>${data[3].departmentName}</td>
								<td>
									<c:choose>
									   <c:when test="${data[0].status=='Y'}">
									       <span class="label label-primary">有效</span>
									   </c:when>
									   <c:otherwise>
									       <span class="label label-danger">无效</span>
									   </c:otherwise>
									</c:choose>
								</td>
								<td>
									<div class="btn-group">
										<button class="btn-white btn btn-xs" onclick="editData(${data[0].sdId})">
											<i class="fa fa-edit btn-outline btn-info"></i>&nbsp;编辑
										</button>
										&nbsp;
										<button class="btn-white btn btn-xs" onclick="deleteData(${data[0].sdId})">
											<i class="fa fa-trash btn-outline btn-warning"></i>&nbsp;删除
										</button>
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
	
	<script>
	$(document).ready(function() {
		$("#addRelateButton").on("click",function(){
			var nodeId = getTreeSelectNodeId();
			if(nodeId!=""){
				getAddRelateModal(nodeId);
			}else{
				redragonJS.alert("请先选择一个组织节点");
			}
		});
	});
	</script>
