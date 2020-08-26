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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<div class="modal" id="selectSOLineDiv" tabindex="-1" role="dialog" aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document">

		<div class="modal-content animated bounceInRight">

			<div class="modal-header">
				<h4 class="modal-title">选择要出库的单据行</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body" style="padding-bottom: 20px; padding-top: 5px;">

				<div class="table-responsive">
					
					<table class="table table-striped table-hover table-bordered border-top">
						<thead>
							<tr>
								<th></th>
								<th>物料编码</th>
								<th>物料名称</th>
								<th>单价</th>
								<th>数量</th>
								<th>单位</th>
								<th>金额</th>
								<th>摘要</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.soLineList}" var="data" varStatus="status">
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td class="soLineCode" style="display: none;">${data.soLineCode}</td>
									<td class="materialCode">${data.materialCode}</td>
									<td class="materialName">${requestScope.materialMap[data.materialCode]}</td>
									<td class="price">${data.price}</td>
									<td class="quantity">${data.quantity}</td>
									<td class="unit">${requestScope.materialUnitMap[data.unit]}</td>
									<td class="soLineAmount">${data.amount}</td>
									<td>${data.memo}</td>
								</tr>
							</c:forEach>

						</tbody>
						<tfoot>
							<%-- 导入页码 --%>
							<jsp:include page="../../common/pages.jsp"></jsp:include>
						</tfoot>
					</table>
				</div>

				<div class="form-group row m-b-none">
					<div class="col-sm-12 col-sm-offset-2 text-right">
						<button class="btn btn-white btn-lg" type="button" data-dismiss="modal">返回</button>
						&nbsp;
						<button id="selectButton" class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">
							&nbsp;&nbsp;继续&nbsp;&nbsp;<i class="fa fa-check-square-o"></i>
						</button>
					</div>
				</div>

			</div>

		</div>

	</div>

</div>

<script>
	$(document).ready(function() {
		//初始化checkbox
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
		
		
		//checkbox选中效果
		$("tr").click(function(){
			if($(this).find(".i-checks").prop("checked")){
				$(this).find(".i-checks").iCheck('uncheck');
			}else{
				$(".i-checks").iCheck('uncheck');
				$(this).find(".i-checks").iCheck('check');
			}
			
		});

		//确认按钮
		$("#selectButton").click(function(){
			var selectFlag = "N"
			
			var materialCode =  "";
			var materialName = "";
			var price = "";
			var quantity = "";
			var unit = "";
			var lineAmount = "";
			var soLineCode = "";
			
			$('.i-checks').each(function(){
				if($(this).prop("checked")){
					selectFlag = "Y";
					
					soLineCode = $(this).parents("tr").find("td.soLineCode").text();
					materialCode = $(this).parents("tr").find("td.materialCode").text();
					materialName = $(this).parents("tr").find("td.materialName").text();
					price = $(this).parents("tr").find("td.price").text();
					quantity = $(this).parents("tr").find("td.quantity").text();
					unit = $(this).parents("tr").find("td.unit").text();
					lineAmount = $(this).parents("tr").find("td.soLineAmount").text();
					return false;
				}
			});
			
			if(selectFlag=="N"){
				redragonJS.alert("必须选择一个单据行");
			}else{
				$('#selectSOLineDiv').modal('hide');
				getLineModal(null, soLineCode, materialCode, materialName, price, quantity, unit, lineAmount);
			}
		});
	});
</script>
