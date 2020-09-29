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
	<jsp:include page="search/voucherSearch.jsp"></jsp:include>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
		        <div class="ibox-title">
		            <h4>凭证列表</h4>
		            <div class="ibox-tools">
		                
		                <div class="btn-group" role="group">
						    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						      自动凭证
						      <span class="caret"></span>
						    </button>
						    <ul class="dropdown-menu">
						      <li><a id="selectPayButton" href="javascript:void(0)">付款单生成凭证</a></li>
						      <li><a id="selectReceiptButton" href="javascript:void(0)">收款单生成凭证</a></li>
						    </ul>
						</div>
		                
		                <button id="addButton" class="btn btn-success btn-sm" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">手工凭证</span></button>
		                <button id="searchButton" class="btn btn-default btn-sm btn-notcontrol" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
		            
		            	<div class="btn-group">
							<button type="button" class="btn btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    	<i class="fa fa-download"></i>&nbsp;&nbsp;报表下载 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
					    		<li><a href="web/voucherReport/getVoucherReportList?voucherStartDate=${param.voucherStartDate}&voucherEndDate=${param.voucherEndDate}">导出凭证数据</a></li>
							</ul>
						</div>
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
									<th>凭证字</th>
									<th>凭证号</th>
									<th>凭证日期</th>
									<th>凭证金额</th>
									<th>制单人</th>
									<th width="5%">状态</th>
									<th width="7%">审批状态</th>
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
									<td>${requestScope.voucherTypeMap[data.voucherType]}</td>
									<td>${data.voucherNumber}</td>
									<td><fmt:formatDate value="${data.voucherDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${data.amount}</td>
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
									<c:choose>
									   <c:when test="${data.approveStatus=='UNSUBMIT'}">
									       <span class="label">${requestScope.approveStatusMap[data.approveStatus]}</span>
									   </c:when>
									   <c:when test="${data.approveStatus=='SUBMIT'}">
									       <span class="label label-primary">${requestScope.approveStatusMap[data.approveStatus]}</span>
									   </c:when>
									   <c:when test="${data.approveStatus=='APPROVE'}">
									       <span class="label label-success">${requestScope.approveStatusMap[data.approveStatus]}</span>
									   </c:when>
									   <c:when test="${data.approveStatus=='REJECT'}">
									       <span class="label label-warning">${requestScope.approveStatusMap[data.approveStatus]}</span>
									   </c:when>
									</c:choose>
									</td>
									<td>
										<div class="btn-group">
											<button class="btn-white btn btn-xs btn-notcontrol" onclick="editData(${data.voucherHeadId},'${data.voucherHeadCode}')"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
											
											<c:if test="${data.approveStatus!='APPROVE'&&data.approveStatus!='SUBMIT'}">
												<button class="btn-white btn btn-xs" onclick="deleteData(${data.voucherHeadId},'${data.voucherHeadCode}','${data.approveStatus}')"><i class="fa fa-trash"></i>&nbsp;删除</button>
											</c:if>
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

<div id="selectBillModal"></div>

<script>
	$(document).ready(function() {
		/*
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
		*/
		$("#addButton").click(function(){
		    window.location.href="web/finVoucherHead/getFinVoucherHead";
		});
		
		$("#selectPayButton").click(function(){
		    getSelectBillModal("PAY");
		});
		
		$("#selectReceiptButton").click(function(){
		    getSelectBillModal("RECEIPT");
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
		window.location.href="web/finVoucherHead/getFinVoucherHead?voucherHeadId="+id+"&voucherHeadCode="+code;
	}
	
	function deleteData(id, code, approveStatus) {
		redragonJS.confirm("确认删除数据？", function(){
			window.location.href="web/finVoucherHead/deleteFinVoucherHead?voucherHeadId="+id+"&voucherHeadCode="+code+"&approveStatus="+approveStatus;
		});
	}
	
	//返回单据选择框
	function getSelectBillModal(businessType){
		$('#selectBillDiv').modal('hide');
		redragonJS.loading("ibox-content");
		$.ajax({
			type: "post",
			url: "web/finVoucherModelHead/getSelectBillModal",
			data: {"businessType": businessType, 
				   "payHeadCode": $("#payHeadCodeSearch").val(), "amount": $("#payAmountSearch").val(), "vendorCode": $("#vendorCodeSearch").val(), "payDate": $("#payDateSearch").val(),
				   "receiptHeadCode": $("#receiptHeadCodeSearch").val(), "amount": $("#receiptAmountSearch").val(), "customerCode": $("#customerCodeSearch").val(), "receiptDate": $("#receiptDateSearch").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content");
				if(data!=""){
					$("#selectBillModal").html(data);
					$('#selectBillDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script>       