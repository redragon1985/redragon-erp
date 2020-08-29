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

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h2>库存[${requestScope.invWarehouse.warehouseName}(${param.warehouseCode})]盘点</h2>
					<div class="ibox-tools">
						<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/invStockCheckHead/getInvStockCheckHeadList?warehouseCode=${param.warehouseCode}'">返回</button>&nbsp;
						
						<c:if test="${param.checkHeadCode==null||param.checkHeadCode==''||requestScope.invStockCheckHead.approveStatus=='UNSUBMIT'||requestScope.invStockCheckHead.approveStatus=='REJECT' }">
							<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-check-square-o"></i></button>
						</c:if>
						
						<c:if test="${param.checkHeadCode!=null&&param.checkHeadCode!=''}">
							<c:if test="${requestScope.invStockCheckHead.approveStatus=='UNSUBMIT'||requestScope.invStockCheckHead.approveStatus=='REJECT' }">
								<button class="btn btn-primary btn-lg" type="button" onclick="window.location.href='web/invStockCheckHead/updateApproveStatus?code=${requestScope.invStockCheckHead.checkHeadCode}&warehouseCode=${param.warehouseCode}&approveStatus=SUBMIT'">&nbsp;&nbsp;提交&nbsp;&nbsp;<i class="fa fa-arrow-circle-right"></i></button>&nbsp;
							</c:if>
							<c:if test="${requestScope.invStockCheckHead.approveStatus=='SUBMIT' }">
								<button class="btn btn-warning btn-lg" type="button" onclick="window.location.href='web/invStockCheckHead/updateApproveStatus?code=${requestScope.invStockCheckHead.checkHeadCode}&warehouseCode=${param.warehouseCode}&approveStatus=APPROVE'">&nbsp;&nbsp;审核通过&nbsp;&nbsp;<i class="fa fa-check-circle"></i></button>&nbsp;
								<button class="btn btn-danger btn-lg" type="button" onclick="window.location.href='web/invStockCheckHead/updateApproveStatus?code=${requestScope.invStockCheckHead.checkHeadCode}&warehouseCode=${param.warehouseCode}&approveStatus=REJECT'">&nbsp;&nbsp;驳回&nbsp;&nbsp;<i class="fa fa-times-circle"></i></button>&nbsp;
							</c:if>
							<c:if test="${requestScope.invStockCheckHead.approveStatus=='APPROVE' }">
								<button class="btn btn-success btn-lg" type="button" onclick="window.location.href='web/invStockCheckHead/updateApproveStatus?code=${requestScope.invStockCheckHead.checkHeadCode}&warehouseCode=${param.warehouseCode}&approveStatus=UNSUBMIT'">&nbsp;&nbsp;变更&nbsp;&nbsp;<i class="fa fa-retweet"></i></button>&nbsp;
							</c:if>
						</c:if>
					</div>
				</div>

				<div class="ibox-content">
					<form id="editForm" action="web/invStockCheckHead/editInvStockCheckHead" method="post">
					
						<%-- 头 --%>
						<div class="form-group row">
							<label class="col-sm-2 col-form-label"><span class="text-danger">*</span>盘点月份</label>
							<div class="col-sm-4">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="checkDate" name="checkDate" type="text" class="form-control" value="${requestScope.invStockCheckHead.checkDate}" autocomplete="off">
								</div>
							</div>
						
	                        <label class="col-sm-2 col-form-label"><span class="text-danger">*</span>盘点名称</label>
	                        <div class="col-sm-4">
								<input id="checkName" name="checkName" type="text" class="form-control" value="${requestScope.invStockCheckHead.checkName}">
							</div>
	                    </div>
	                    <div class="hr-line-dashed"></div>
	                    
	                    <div class="form-group row">
	                        <label class="col-sm-2 col-form-label">状态</label>
	                        <div class="col-sm-4">
								<input type="text" class="form-control" value="${requestScope.statusMap[requestScope.invStockCheckHead.status]}" readonly="readonly">
							</div>
							
							<label class="col-sm-2 col-form-label">备注</label>
	                        <div class="col-sm-4">
								<input id="memo" name="memo" type="text" class="form-control" value="${requestScope.invStockCheckHead.memo}">
							</div>
	                    </div>
	                    <hr/>
	                    
	                    <input type="hidden" name="checkHeadCode" value="${param.checkHeadCode}">
	                    <input type="hidden" name="checkHeadId" value="${requestScope.invStockCheckHead.checkHeadId}">
						<input type="hidden" name="warehouseCode" value="${param.warehouseCode}">
						
						<%-- 行列表 --%>
						<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered border-top">
								<thead>
									<tr>
										<th width="5%">序号</th>
										<th width="10%">物料编码</th>
										<th width="15%">物料名称</th>
										<th width="15%">盘点前数量</th>
										<th width="15%">盘点后数量</th>
										<th width="40%">备注</th>
									</tr>
								</thead>
								<tbody>
								
								    <c:forEach items="${requestScope.invStockCheckLineList}" var="data" varStatus="status">
									<tr>
										<td style="vertical-align: middle;">${status.count}</td>
										<td style="vertical-align: middle;">${data.materialCode}</td>
										<td style="display: none;">
											<input name="checkLineId" type="text" class="form-control" value="${data.checkLineId}">
											<input name="materialCode" type="text" class="form-control" value="${data.materialCode}">
											<input name="checkBeforeQuantity" type="text" class="form-control" value="${data.checkBeforeQuantity}">
										</td>
										<td style="vertical-align: middle;">${requestScope.materialMap[data.materialCode]}</td>
										<td style="text-align: right; vertical-align: middle;">${data.checkBeforeQuantity}</td>
										<td style="text-align: right;"><input name="checkAfterQuantity" type="text" class="form-control" value="${data.checkAfterQuantity}"></td>
										<td><input name="lineMemo" type="text" class="form-control" value="${data.memo}"></td>
									</tr>
									</c:forEach>
									
								</tbody>
								<tfoot></tfoot>
							</table>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
	
		//设置日期插件
		$('#checkDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : false,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm',
			language: 'zh-CN',
			minViewMode:1,
            startView:1,
		});
		
		//初始化checkName
		setCheckName();
		
		//设置盘点日期的修改效果
		$("#checkDate").change(function(){
			setCheckName();
		});
	

		
		//表单提交
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").submit();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				checkDate : {
					required : true,
				},
				checkName : {
					required : true,
				},
			},
			submitHandler: function(form) {
				var submitFlag = "Y";
			
				//验证
				$("input[name='checkAfterQuantity']").each(function(){
					if($(this).val()==""||!$.isNumeric($(this).val())||parseFloat($(this).val())<0){
						submitFlag = "N";
						redragonJS.alert("盘点后数量必须大于等于0");
						return false;
					}
				});
				
				//提交
				if(submitFlag=="Y"){
					l.ladda('start');
		        	form.submit();
				}
		    }
		});
	});
	
	function setCheckName(){
		$("#checkName").val("${requestScope.invWarehouse.warehouseName}"+"["+$("#checkDate").val()+"]盘点");
	}
</script>