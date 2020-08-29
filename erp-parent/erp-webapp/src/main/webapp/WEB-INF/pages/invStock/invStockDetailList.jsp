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
    <c:if test="${hint!=null&&hint!=''}">
   		<jsp:include page="../common/alert/alert.jsp">
   			<jsp:param value="${hint}" name="alertType"/>
   			<jsp:param value="${alertMessage}" name="alertMessage"/>
   		</jsp:include>
    </c:if>
	
	<%-- 导入查询框 --%>
	<jsp:include page="search/invStockDetailSearch.jsp"></jsp:include>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
		        <div class="ibox-title">
		            <h2>仓库${param.warehouseCode}库存明细</h2>
		            <div class="ibox-tools">
		            	<button class="btn btn-lg btn-notcontrol" type="button" onclick="window.location.href='web/invWarehouse/getInvWarehouseList'">返回</button>&nbsp;
		            </div>
		        </div>
				<div class="ibox-content border-bottom" style="padding-bottom: 0px;">
					
					
					<c:if test="${requestScope.invStockList==null||fn:length(requestScope.invStockList)==0}">
					<div class="row" style="margin-bottom: 30px;  margin-top: 30px; color: silver; font-size: 20px;">
						<div class="col-sm-12">
							<center><i class="fa fa-exclamation-triangle">&nbsp;</i>未选择物料或当前物料无库存</center>
						</div>
					</div>		
					</c:if>
					
					<c:if test="${requestScope.invStockList!=null&&fn:length(requestScope.invStockList)>0}">
					<div class="row" style="margin-bottom: 5px;">
						<div class="col-sm-6">
							<strong>物料编码：</strong><span>${requestScope.mdMaterial.materialCode}</span><br>
							<strong>物料名称：</strong><span>${requestScope.mdMaterial.materialName}</span><br>
							<strong>规格型号：</strong><span>${requestScope.mdMaterial.standard}</span><br>
							<strong>物料单位：</strong><span>${materialUnitMap[requestScope.mdMaterial.materialUnit]}</span><br>
						</div>

						<div class="col-sm-6 text-right">
							<h4 class="text-navy"><strong>库存数量：</strong><span>${requestScope.stockNumber}</span><br></h4>
						</div>
					</div>
					
					
					<div class="table-responsive">
						<table class="table table-striped table-hover table-bordered border-top">
							<thead>
								<tr>
									<%-- 
									<th></th>
									--%>
									<th width="5%">序号</th>
									<th>行编码</th>
									<th>物料编码</th>
									<th>物料名称</th>
									<th>库存来源</th>
									<th>单据编码</th>
									<th>数量</th>
									<th>保留标识</th>
									<th>备注</th>
									<th>创建时间</th>
									<th>创建人</th>
								</tr>
							</thead>
							<tbody>
							
							    <c:forEach items="${requestScope.invStockList}" var="data" varStatus="status">
								<tr>
									<%-- 
									<td><input type="checkbox" class="i-checks"
										name="input[]"></td>
									--%>	
									<td>${status.count}</td>
									<td>${data.stockCode}</td>
									<td>${requestScope.mdMaterial.materialCode}</td>
									<td>${requestScope.mdMaterial.materialName}</td>
									<td>${stockBillTypeMap[data.billType]}</td>
									<td>${data.billHeadCode}</td>
									<td>${data.stockNumber}</td>
									<td style="text-align: center;">
										<c:choose>
											<c:when test="${data.retainFlag=='Y'}">
												<span class="label label-info">保留</span>
											</c:when>
										</c:choose>
									</td>
									<td>${data.memo}</td>
									<td><fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${data.createdBy}</td>
								</tr>
								</c:forEach>
								
							</tbody>
							<tfoot>
							    <%-- 导入页码 --%>
								<jsp:include page="../common/pages.jsp"></jsp:include>
							</tfoot>
						</table>
					</div>
					</c:if>
					
				</div>
			</div>
		</div>
		
	</div>
</div>

<script>
	$(document).ready(function() {
	});
</script>       