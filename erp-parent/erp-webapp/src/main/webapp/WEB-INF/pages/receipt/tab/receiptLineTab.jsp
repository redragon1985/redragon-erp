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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<div id="lineTab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">

		<div class="col-lg-12 text-right" style="padding-right: 0px;">
			<button id="addButton" class="btn btn-info btn-sm" type="button" ><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增收款行</span></button>
			<%-- 
			<button id="searchButton" class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
			--%>
		</div><br/>

		<div class="table-responsive">
			<table class="table table-stripped table-hover table-bordered border-top">

				<thead>
					<tr>
						<th width="5%">行号</th>
						<th>来源行编码</th>
						<th>物料编码</th>
						<th>物料名称</th>
						<th>单价</th>
						<th>数量</th>
						<th>单位</th>
						<th>金额</th>
						<th>付款金额</th>
						<th>摘要</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.receiptLineList}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${data.receiptSourceLineCode}</td>
						<td>${data.materialCode}</td>
						<td>${data.materialName}</td>
						<td>${data.price}</td>
						<td>${data.quantity}</td>
						<td>${data.unit}</td>
						<td>${data.soLineAmount}</td>
						<td class="lineAmount" style="color: #1c84c6 !important;">${data.amount}</td>
						<td style="color: #1c84c6 !important;">${data.memo}</td>
						<td>
							<div class="btn-group">
								<button class="btn-white btn btn-xs" onclick="editData(${data.receiptLineId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
								<button class="btn-white btn btn-xs" onclick="deleteData(${data.receiptLineId})"><i class="fa fa-trash"></i>&nbsp;删除</button>
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

<div id="selectSOLineModal"></div>
<div id="addLineModal"></div>

<script>
	$(document).ready(function() {
		//设置头金额
		var sumAmount = 0;
		$(".lineAmount").each(function(){
			sumAmount = sumAmount+parseFloat($(this).text());
			
		});
		$("#amount").val(sumAmount);
	
		$("#addButton").click(function(){
			getSelectSOLineModal();
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
		getLineModal(id);
	}
	
	function deleteData(id) {
		redragonJS.confirm("确认删除数据？", function(){
			deleteLine(id);
		});
	}
	
	function getLineModal(id, soLineCode, materialCode, materialName, price, quantity, unit, lineAmount){
		$.ajax({
			type: "post",
			url: "web/receiptLine/getReceiptLine",
			data: {"receiptLineId": id, "receiptHeadCode": "${param.receiptHeadCode}", "receiptSourceType": $("#receiptSourceType").val(), "receiptSourceLineCode": soLineCode, "materialCode": materialCode,
				   "materialName": materialName, "price": price, "quantity": quantity, "unit": unit, "soLineAmount": lineAmount},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#addLineModal").html(data);
					$('#addLineDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	function deleteLine(id){
		$.ajax({
			type: "post",
			url: "web/receiptLine/deleteReceiptLine",
			data: {"receiptLineId": id, "receiptHeadCode": "${param.receiptHeadCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				var json = JSON.parse(data);
				if(json.result=="success"){
					redragonJS.close();
					getLineTab("${param.receiptHeadCode}");
				}else{
					redragonJS.alert("删除收款行错误");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//返回销售订单行选择框
	function getSelectSOLineModal(){
		$('#selectSOLineDiv').modal('hide');
		redragonJS.loading("ibox-content1");
		$.ajax({
			type: "post",
			url: "web/receiptLine/getSelectSOLineModal",
			data: {"soHeadCode": $("#receiptSourceHeadCode").val(), "receiptHeadCode": "${param.receiptHeadCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content1");
				if(data!=""){
					$("#selectSOLineModal").html(data);
					$('#selectSOLineDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script> 