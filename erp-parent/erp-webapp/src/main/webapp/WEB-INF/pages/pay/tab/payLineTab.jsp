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
			<button id="addButton" class="btn btn-info btn-sm" type="button" ><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增付款行</span></button>
			<%-- 
			<button id="searchButton" class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;展开查询</button>
			--%>
		</div><br/>

		<div class="table-responsive">
			<table class="table table-stripped table-hover table-bordered border-top">

				<thead>
					<tr>
						<th width="5%">行号</th>
						<th>采购行编码</th>
						<th>物料编码</th>
						<th>物料名称</th>
						<th>规格型号</th>
						<th>采购单价</th>
						<th>入库数量</th>
						<th>单位</th>
						<th>发票行数量</th>
						<th>发票行金额</th>
						<th>税率</th>
						<th>税额</th>
						<th>摘要</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.payLineList}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${data.paySourceLineCode}</td>
						<td>${data.materialCode}</td>
						<td>${data.materialName}</td>
						<td>${data.standard}</td>
						<td>${data.price}</td>
						<td>${data.inputQuantity}</td>
						<td>${data.unit}</td>
						<td style="color: #1c84c6 !important;">${data.quantity}</td>
						<td class="lineAmount" style="color: #1c84c6 !important;">${data.amount}</td>
						<td style="color: #1c84c6 !important;">${data.taxRate}</td>
						<td class="lineTaxAmount" style="color: #1c84c6 !important;">${data.taxAmount}</td>
						<td style="color: #1c84c6 !important;">${data.memo}</td>
						<td>
							<div class="btn-group">
								<button class="btn-white btn btn-xs" onclick="editData(${data.payLineId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
								<button class="btn-white btn btn-xs" onclick="deleteData(${data.payLineId})"><i class="fa fa-trash"></i>&nbsp;删除</button>
							</div>
						</td>
					</tr>
					</c:forEach>
					
					<tr>
						<td><strong>合计</strong></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td id="lineAmountSum" style="font-weight: bold;">0.00</td>
						<td></td>
						<td id="lineTaxAmountSum" style="font-weight: bold;">0.00</td>
						<td></td>
						<td></td>
					</tr>
					
				</tbody>
				<tfoot>
					<%-- 导入页码 --%>
					<jsp:include page="../../common/pages.jsp"></jsp:include>
				</tfoot>
			</table>

		</div>

	</div>
</div>

<div id="selectPOLineModal"></div>
<div id="addLineModal"></div>

<script>
	$(document).ready(function() {
		//设置头金额
		var sumAmount = 0;
		var sumTaxAmount = 0;
		$(".lineAmount").each(function(){
			sumAmount = redragonJS.numberAdd(sumAmount, parseFloat($(this).text()));
			
		});
		$(".lineTaxAmount").each(function(){
			sumTaxAmount = redragonJS.numberAdd(sumTaxAmount, parseFloat($(this).text()));
			
		});
		//设置合计
		$("#lineAmountSum").text(sumAmount.toFixed(2));
		$("#lineTaxAmountSum").text(sumTaxAmount.toFixed(2));
		
	
		$("#addButton").click(function(){
			getSelectPOLineModal();
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
	
	function getLineModal(id, poLineCode, materialCode, materialName, price, inputQuantity, unit, lineAmount){
		$.ajax({
			type: "post",
			url: "web/payLine/getPayLine",
			data: {"payLineId": id, "payHeadCode": "${param.payHeadCode}", "paySourceType": $("#paySourceType").val(), "paySourceLineCode": poLineCode, "materialCode": materialCode,
				   "materialName": materialName, "price": price, "inputQuantity": inputQuantity, "unit": unit, "poLineAmount": lineAmount},
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
			url: "web/payLine/deletePayLine",
			data: {"payLineId": id, "payHeadCode": "${param.payHeadCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				var json = JSON.parse(data);
				if(json.result=="success"){
					redragonJS.close();
					getLineTab("${param.payHeadCode}");
				}else{
					redragonJS.alert("删除付款行错误");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//返回采购订单行选择框
	function getSelectPOLineModal(){
		$('#selectPOLineDiv').modal('hide');
		redragonJS.loading("ibox-content1");
		$.ajax({
			type: "post",
			url: "web/payLine/getSelectPOLineModal",
			data: {"poHeadCode": $("#paySourceHeadCode").val(), "payHeadCode": "${param.payHeadCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("ibox-content1");
				if(data!=""){
					$("#selectPOLineModal").html(data);
					$('#selectPOLineDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script> 