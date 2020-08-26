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

<div id="lineTab" class="tab-pane">
	<div class="panel-body" style="padding-bottom: 0px; border-bottom: 0px;">

		<div class="col-lg-12 text-right" style="padding-right: 0px;">
			<button id="addButton" class="btn btn-info btn-sm" type="button" ><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增入库行</span></button>
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
						<th>规格型号</th>
						<th>单价</th>
						<th>数量</th>
						<th>单位</th>
						<th>金额</th>
						<th>入库数量</th>
						<th>摘要</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.invInputLineList}" var="data" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${data.inputSourceLineCode}</td>
						<td>${data.materialCode}</td>
						<td>${data.materialName}</td>
						<td>${data.materialStandard}</td>
						<td>${data.price}</td>
						<td>${data.quantity}</td>
						<td>${data.unit}</td>
						<td>${data.poLineAmount}</td>
						<td class="lineAmount" style="color: #1c84c6 !important;">${data.inputQuantity}</td>
						<td style="color: #1c84c6 !important;">${data.memo}</td>
						<td>
							<div class="btn-group">
								<button class="btn-white btn btn-xs" onclick="editData(${data.inputLineId})"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;
								<button class="btn-white btn btn-xs" onclick="deleteData(${data.inputLineId})"><i class="fa fa-trash"></i>&nbsp;删除</button>
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

<div id="selectPOLineModal"></div>
<div id="addLineModal"></div>

<script>
	$(document).ready(function() {
		$("#addButton").click(function(){
			getSelectPOLineModal();
		});
		
		/*
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
		*/
	});
	
	function editData(id){
		getLineModal(id);
	}
	
	function deleteData(id) {
		redragonJS.confirm("确认删除数据？", function(){
			deleteLine(id);
		});
	}
	
	function getLineModal(id, poLineCode, materialCode, materialName, price, quantity, unit, lineAmount){
		$.ajax({
			type: "post",
			url: "web/invInputLine/getInvInputLine",
			data: {"inputLineId": id, "inputHeadCode": "${param.inputHeadCode}", "inputSourceType": $("#inputSourceType").val(), "inputSourceLineCode": poLineCode, "materialCode": materialCode,
				   "materialName": materialName, "price": price, "quantity": quantity, "unit": unit, "poLineAmount": lineAmount},
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
			url: "web/invInputLine/deleteInvInputLine",
			data: {"inputLineId": id, "inputHeadCode": "${param.inputHeadCode}"},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				var json = JSON.parse(data);
				if(json.result=="success"){
					redragonJS.close();
					getLineTab("${param.inputHeadCode}");
				}else{
					redragonJS.alert("删除入库行错误");
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
			url: "web/invInputLine/getSelectPOLineModal",
			data: {"poHeadCode": $("#inputSourceHeadCode").val(), "inputHeadCode": "${param.inputHeadCode}"},
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