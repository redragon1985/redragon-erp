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
	<c:if test="${requestScope.hints!=null&&requestScope.hints!=''}">
		<jsp:include page="../common/alert/alert.jsp">
			<jsp:param value="hint" name="alertType" />
			<jsp:param value="${fn:replace(requestScope.hints,';', '<br/>')}"
				name="alertMessage" />
		</jsp:include>
	</c:if>


	<div class="row">
		<form id="editForm" method="post" action="web/finVoucherHead/editFinVoucherHead">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title" style="text-align: left;">
					<button class="btn btn-white btn-lg" type="button" onclick="window.location.href='web/finVoucherHead/getFinVoucherHeadList'">返回</button>&nbsp;
					
					<c:if test="${param.voucherHeadId==null||param.voucherHeadId==''||((requestScope.finVoucherHead.approveStatus==null||requestScope.finVoucherHead.approveStatus=='')&&requestScope.finVoucherHead.status=='Y')}">
						<c:if test="${param.voucherHeadId==null||param.voucherHeadId==''}">
							<select id="voucherModel" name="voucherModel" class="form-control" style="display: inline; width: 200px; vertical-align: middle;">
								<option value="">选择要复制的模板...</option>
								<c:forEach items="${requestScope.finVoucherModelHeadList}" var="finVoucherModelHead">
	                        		<option value="${finVoucherModelHead.voucherHeadId}">${finVoucherModelHead.modelName}</option>
	                        	</c:forEach>
							</select>
						</c:if>
					</c:if>
					
					<c:if test="${param.voucherHeadId==null||param.voucherHeadId==''||requestScope.finVoucherHead.approveStatus=='UNSUBMIT'||requestScope.finVoucherHead.approveStatus=='REJECT' }">
						<button class="ladda-button ladda-button-demo btn btn-success btn-lg" data-style="expand-right">&nbsp;&nbsp;凭证保存&nbsp;&nbsp;<i class="fa fa-check-square-o"></i></button>
					</c:if>
					
					<c:if test="${param.voucherHeadId!=null&&param.voucherHeadId!=''}">
						<c:if test="${requestScope.finVoucherHead.approveStatus=='UNSUBMIT'||requestScope.finVoucherHead.approveStatus=='REJECT' }">
							<button id="submitApproveButton" type="button" class="btn btn-primary btn-lg">凭证提交</button>
						</c:if>			
						<c:if test="${requestScope.finVoucherHead.approveStatus=='SUBMIT' }">
							<button id="approveButton" type="button" class="btn btn-warning btn-lg">凭证审核</button>
							<button id="rejectApproveButton" type="button" class="btn btn-danger btn-lg">凭证驳回</button>
						</c:if>
						<c:if test="${requestScope.finVoucherHead.approveStatus=='APPROVE'&&requestScope.finVoucherHead.status!='N' }">
							<button id="cancelButton" type="button" class="btn btn-danger btn-lg">凭证作废</button>
						</c:if>
					</c:if>
				</div>

				<div class="ibox-content">
					<div class="text-center">
						<h2>
							<strong>记账凭证
								<c:if test="${requestScope.finVoucherHead.status=='N'}">
									<span class="text-danger" style="font-size: 16px;">(作废)</span>
								</c:if>
								<c:if test="${requestScope.finVoucherHead.status!='N'&&requestScope.finVoucherHead.approveStatus!=null&&requestScope.finVoucherHead.approveStatus!=''}">
									<span class="text-danger" style="font-size: 16px;">(${requestScope.approveStatusMap[requestScope.finVoucherHead.approveStatus]})</span>
								</c:if>
							</strong>
						</h2>
					</div>
					
						<div class="form-group row" style="margin-bottom: 0px;">
							
							<label class="col-sm-4 col-form-label">凭证字 
								<select id="voucherType" name="voucherType" class="form-control" style="display: inline; width: 65px; vertical-align: middle;">
									<c:forEach items="${requestScope.voucherTypeMap}" var="voucherType">
		                        		<option value="${voucherType.key}">${voucherType.value}</option>
		                        	</c:forEach>
								</select>&nbsp; 
								<input id="voucherNumber" name="voucherNumber" type="text" class="form-control" value="${requestScope.finVoucherHead.voucherNumber}" style="display: inline; width: 130px; vertical-align: middle;" placeholder="自动生成凭证号">
							</label> 
							
							<label class="col-sm-4 col-form-label">
								<div class="input-group date">
									<span style="padding: 9px 12px 4px 12px;">凭证日期</span><span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="voucherDate" name="voucherDate" type="text" class="form-control" value="<fmt:formatDate value="${requestScope.finVoucherHead.voucherDate}" pattern="yyyy-MM-dd"/>" style="display: inline; width: 35px;" autocomplete="off">
								</div>
							</label> 
							
							<label class="col-sm-4 col-form-label text-right"> 附单据数
								<input id="billNum" name="billNum" type="text" class="form-control" value="${requestScope.finVoucherHead.billNum}" style="display: inline; width: 65px; vertical-align: middle;">
							</label>
						</div>

						<div class="form-group row" style="margin-bottom: 0px;">
							<div class="table-responsive">
								<table class="table table-hover" style="border: 2px solid; font-weight: bold; margin-bottom: 5px;" border="2">
									<thead>
									</thead>
									<tbody>
										<tr>
											<th width="30%" class="text-center" rowspan="2" style="vertical-align: middle;">摘要</th>
											<th width="30%" class="text-center" rowspan="2" style="vertical-align: middle;">会计科目</th>
											<th width="20%" class="text-center" colspan="11">借方金额</th>
											<th width="20%" class="text-center" colspan="11">贷方金额</th>
										</tr>
										<tr style="font-size: 11px; font-weight: normal;">
											<td>亿</td>
											<td>千</td>
											<td>百</td>
											<td>十</td>
											<td>万</td>
											<td>千</td>
											<td>百</td>
											<td>十</td>
											<td>元</td>
											<td style="color: silver;">角</td>
											<td style="color: silver;">分</td>
											<td>亿</td>
											<td>千</td>
											<td>百</td>
											<td>十</td>
											<td>万</td>
											<td>千</td>
											<td>百</td>
											<td>十</td>
											<td>元</td>
											<td style="color: silver;">角</td>
											<td style="color: silver;">分</td>
										</tr>
										
										<c:forEach items="${requestScope.finVoucherLineList}" var="data">
										<tr class="dataTr">
											<td>
												<div class="addDiv" style="position: absolute; margin-left: 0px; margin-top: -28px; cursor: pointer; display: none;"><i class="fa fa-plus-circle fa-lg" title="添加行"></i></div>
												<div class="removeDiv" style="position: absolute; margin-left: 0px; margin-top: -7px; cursor: pointer; display: none;"><i class="fa fa-minus-circle fa-lg" title="删除行"></i></div>
												<input name="voucherLineId" type="hidden" value="${data.voucherLineId}">
												<input name="memo" type="text" class="form-control" value="${data.memo}">
											</td>
											<td>
												<input name="subjectDesc" type="text" class="form-control" value="${data.subjectDesc}">
												<input name="subjectCode" type="hidden" class="form-control" value="${data.subjectCode}">
											</td>
											
											<c:forEach items="${data.drAmountArray}" var="dr" varStatus="status">
												<c:choose>
													<c:when test="${status.count==1}">
														<td style="vertical-align: middle; font-size: 17px;">${dr}
															<div class="drDiv" style="position: absolute; margin-left: 8px; display: none;">
																<input name="drAmount" type="text" class="form-control" value="${data.drAmount}" style="width: 150%;">
															</div>
														</td>
													</c:when>
													<c:when test="${status.count==10||status.count==11}">
														<td style="vertical-align: middle; color: silver; font-size: 12px;">${dr}</td>
													</c:when>
													<c:otherwise>
														<td style="vertical-align: middle; font-size: 17px;">${dr}</td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											
											
											<c:forEach items="${data.crAmountArray}" var="cr" varStatus="status">
												<c:choose>
													<c:when test="${status.count==1}">
														<td style="vertical-align: middle; font-size: 17px;">${cr}
															<div class="crDiv" style="position: absolute; margin-left: 8px; display: none;">
																<input name="crAmount" type="text" class="form-control" value="${data.crAmount}" style="width: 150%;">
															</div>
														</td>
													</c:when>
													<c:when test="${status.count==10||status.count==11}">
														<td style="vertical-align: middle; color: silver; font-size: 12px;">${cr}</td>
													</c:when>
													<c:otherwise>
														<td style="vertical-align: middle; font-size: 17px;">${cr}</td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											
											
										</tr>
										</c:forEach>
										
										<tr id="emptyTr">
											<td style="height: 36px;">
												<div class="addDiv" style="position: absolute; margin-left: 0px; margin-top: 2px; cursor: pointer; display: none;"><i class="fa fa-plus-circle fa-lg" title="添加行"></i></div>
												<div class="removeDiv" style="position: absolute; margin-left: 0px; margin-top: 18px; cursor: pointer; display: none;"><i class="fa fa-minus-circle fa-lg" title="删除行"></i></div>
											</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr id="sumTr">
											<td colspan="2">合计：&nbsp;&nbsp;${requestScope.finVoucherHead.amountDesc}</td>
											
											<c:forEach items="${requestScope.finVoucherHead.drAmountArray}" var="amount" varStatus="status">
												<c:choose>
													<c:when test="${status.count==1}">
														<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
													</c:when>
													<c:when test="${status.count==10||status.count==11}">
														<td style="vertical-align: middle; color: silver; font-size: 12px;">${amount}</td>
													</c:when>
													<c:otherwise>
														<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											
											<c:forEach items="${requestScope.finVoucherHead.crAmountArray}" var="amount" varStatus="status">
												<c:choose>
													<c:when test="${status.count==1}">
														<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
													</c:when>
													<c:when test="${status.count==10||status.count==11}">
														<td style="vertical-align: middle; color: silver; font-size: 12px;">${amount}</td>
													</c:when>
													<c:otherwise>
														<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</tr>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
							</div>
						</div>

						<div class="form-group row" style="margin-bottom: 0px;">
							<div class="col-sm-4">制单人： ${requestScope.finVoucherHead.staffName}</div>
						</div>
						
						
				</div>
			</div>
		</div>
		
			<input type="hidden" name="voucherHeadId" value="${requestScope.finVoucherHead.voucherHeadId}">
			<input type="hidden" name="voucherHeadCode" value="${requestScope.finVoucherHead.voucherHeadCode}">
			<input type="hidden" name="staffCode" value="${requestScope.finVoucherHead.staffCode}">
			<input type="hidden" name="departmentCode" value="${requestScope.finVoucherHead.departmentCode}">
			<input type="hidden" name="createdDate" value="${requestScope.finVoucherHead.createdDate}">
			<input type="hidden" name="createdBy" value="${requestScope.finVoucherHead.createdBy}">
		</form>
	</div>
</div>


<jsp:include page="pop/subjectTreeModal.jsp"></jsp:include>

<script>
	var selectSubjectInput;
	$(document).ready(function() {
		//初始化status
		if("${requestScope.finVoucherHead.voucherType}"!=""){
			$("#voucherType").val("${requestScope.finVoucherHead.voucherType}");
		}
		//初始化code只读
		//if("${requestScope.finVoucherHead.voucherNumber}"!=""){
		$("#voucherNumber").prop("readonly", true);
		//}
		
		//设置日期插件
		$('#voucherDate').datepicker({
			todayBtn : "linked",
			keyboardNavigation : true,
			forceParse : true,
			calendarWeeks : false,
			autoclose : true,
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
		});
		
		//设置汇总金额
		/*
		var sumFlag = "N";
		$("#sumTr").find("td").each(function(i){
			if(i>0){
				sumFlag = "N";
				var index = i+1;
				var sum = 0;
				$("tr").each(function(n){
					if(n>1&&n<$("tr").length-2){
						var num = $(this).find("td:eq("+index+")").text();
						if($.isNumeric(num)){
							sum = sum + parseFloat(num);
							sumFlag = "Y";
						}
					}
				});
				
				if(sumFlag=="Y"){
					$(this).text(sum);
				}
			}
		});
		*/
	
		//设置tr的默认鼠标放上及移出的效果
		$("tr").each(function(i){
			if(i>1&&i<$("tr").length-2){
				//移除行效果
				$(this).on("mouseover",function(){
					$(this).find(".removeDiv").show();
				});
				
				$(this).on("mouseleave",function(){
					$(this).find(".removeDiv").hide();
				});
			
				//金额效果
				$(this).find("td").each(function(n){
					if(n>1&&n<=12){
						$(this).attr("title","点击编辑");
						$(this).on("click",function(){
							$(this).parent().find(".drDiv").show();
						});
						/*
						$(this).on("mouseover",function(){
							$(this).parent().find(".drDiv").show();
						});
						
						$(this).on("mouseleave",function(){
							$(this).parent().find(".drDiv").hide();
						});
						*/
					}else if(n>12){
						$(this).attr("title","点击编辑");
						$(this).on("click",function(){
							$(this).parent().find(".crDiv").show();
						});
						/*
						$(this).on("mouseover",function(){
							$(this).parent().find(".crDiv").show();
						});
						
						$(this).on("mouseleave",function(){
							$(this).parent().find(".crDiv").hide();
						});
						*/
					}
				});
			}else if(i==$("tr").length-2){
				//添加行效果
				$(this).on("mouseover",function(){
					$(this).find(".addDiv").show();
				});
				
				$(this).on("mouseleave",function(){
					$(this).find(".addDiv").hide();
				});
			}
		});
		
		//添加行
		$(".addDiv").click(function(){
			$.ajax({
				type: "post",
				url: "web/finVoucherHead/getTrModelAjax",
				//data: {"payHeadCode": code},
				async: false,
				dataType: "html",
				cache: false,
				success: function(data){
					if(data!=""){
						$("#emptyTr").before(data);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					redragonJS.alert(textStatus);
				}
			});
		});
		
		//如果没有凭证行，默认添加两行
		if($("tr").length==4){
			$(".addDiv").click();
			$(".addDiv").click();
		}
		
		//移除行
		$(".removeDiv").click(function(){
			var tempObj = $(this);
			redragonJS.confirm("确认删除凭证行？", function(){
				if(tempObj.parent("td").parent("tr").find("input[name='voucherLineId']").val()==""){
					tempObj.parent("td").parent("tr").remove();
					redragonJS.close();
				}else{
					var id = tempObj.parent("td").parent("tr").find("input[name='voucherLineId']").val();
					window.location.href = "web/finVoucherLine/deleteFinVoucherLine?voucherHeadId=${param.voucherHeadId}&voucherHeadCode=${param.voucherHeadCode}&voucherLineId="+id;
				}
				
			});
		});
		
		//作废按钮
		$("#cancelButton").click(function(){
			redragonJS.confirm("确认作废凭证？", function(){
				window.location.href='web/finVoucherHead/editFinVoucherHeadStatus?voucherHeadId=${param.voucherHeadId}&voucherHeadCode=${param.voucherHeadCode}&status=N';
			});
		});
		
		//审批按钮
		$("#approveButton").click(function(){
			redragonJS.confirm("确认凭证生效？", function(){
				window.location.href='web/finVoucherHead/editFinVoucherHeadStatus?voucherHeadId=${param.voucherHeadId}&voucherHeadCode=${param.voucherHeadCode}&approveStatus=APPROVE';
			});
		});
		
		//提交审批按钮
		$("#submitApproveButton").click(function(){
			redragonJS.confirm("确认凭证提交？", function(){
				window.location.href='web/finVoucherHead/editFinVoucherHeadStatus?voucherHeadId=${param.voucherHeadId}&voucherHeadCode=${param.voucherHeadCode}&approveStatus=SUBMIT';
			});
		});
		
		//驳回审批按钮
		$("#rejectApproveButton").click(function(){
			redragonJS.confirm("确认凭证驳回？", function(){
				window.location.href='web/finVoucherHead/editFinVoucherHeadStatus?voucherHeadId=${param.voucherHeadId}&voucherHeadCode=${param.voucherHeadCode}&approveStatus=REJECT';
			});
		});
		
		//科目选择框
		$("input[name='subjectDesc']").focus(function(){
			selectSubjectInput = $(this);
			$('#subjectTreeDiv').modal('show');
		});
		
		//模板选择
		$("#voucherModel").change(function(){
			if($(this).val()!=""){
				$.ajax({
					type: "post",
					url: "web/finVoucherModelHead/getVoucherModelForJson",
					data: {"voucherHeadId": $(this).val()},
					async: false,
					dataType: "json",
					cache: false,
					success: function(data){
						//设置凭证头数据
						$("#voucherType").val(data.voucherType);
						//判断凭证行数
						if(data.finVoucherModelLineList.length>$(".dataTr").length){
							var addLineNum = data.finVoucherModelLineList.length-$(".dataTr").length;
							//添加更多的凭证行
							for(i=0;i<addLineNum;i++){
								$(".addDiv").click();
							}
						}
						//设置凭证行数据
						$(".dataTr").each(function(n){
							$(this).find("input[name='memo']").val(data.finVoucherModelLineList[n].memo);
							$(this).find("input[name='subjectCode']").val(data.finVoucherModelLineList[n].subjectCode);
							$(this).find("input[name='subjectDesc']").val(data.finVoucherModelLineList[n].subjectDesc);
						});
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						redragonJS.alert(textStatus);
					}
				});
			}
		});
		
		//form处理
		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			$("#editForm").valid();
			//l.ladda('stop');
		});

		$("#editForm").validate({
			rules : {
				voucherType : {
					required : true,
				},
				voucherDate : {
					required : true,
				},
				billNum : {
					required : true,
				},
				/*
				voucherNumber : {
					required : true,
				},
				memo : {
					required : true,
				},
				subjectCode : {
					required : true,
				},
				
				drAmount : {
					required : true,
				},
				crAmount : {
					required : true,
				},*/
			},
			submitHandler: function(form) {
				var sumitFlag = 'Y';
			
				//验证凭证行字段
				$("input[name='memo']").each(function(){
					if($.trim($(this).val())==""){
						sumitFlag = "N";
						redragonJS.alert("摘要不能为空");	
						return false;
					}
				});
				
				$("input[name='subjectCode']").each(function(){
					if($.trim($(this).val())==""){
						sumitFlag = "N";
						redragonJS.alert("科目不能为空");	
						return false;
					}
				});
				
				if($("tr.dataTr").length<2){
					sumitFlag = "N";
					redragonJS.alert("凭证行必须超过两行");
					return false;
				}
				
				var drAmountSum = 0;
				var crAmountSum = 0;
				var amountNum = $("input[name='drAmount']").length;
				for(a=0;a<amountNum;a++){
					var drAmount = $("input[name='drAmount']").eq(a).val();
					var crAmount = $("input[name='crAmount']").eq(a).val();
					
					if(drAmount==""||crAmount==""){
						sumitFlag = "N";
						redragonJS.alert("借方和贷方金额不能为空");	
						return false;
					}else if(!$.isNumeric(drAmount)||!$.isNumeric(crAmount)){
						sumitFlag = "N";
						redragonJS.alert("借方和贷方金额必须填写数字");	
						return false;
					}else{
						drAmountSum = drAmountSum + parseFloat(drAmount);
						crAmountSum = crAmountSum + parseFloat(crAmount);
					
						if(parseFloat(drAmount)==0&&parseFloat(crAmount)==0){
							sumitFlag = "N";
							redragonJS.alert("借方和贷方金额不能都为0");	
							return false;
						}
						if(parseFloat(drAmount)<0||parseFloat(crAmount)<0){
							sumitFlag = "N";
							redragonJS.alert("借方和贷方金额不能小于0");	
							return false;
						}
						if(parseFloat(drAmount)>0&&parseFloat(crAmount)>0){
							sumitFlag = "N";
							redragonJS.alert("凭证行借方或贷方金额只能填写一个");	
							return false;
						}
					}
				}
				if(drAmountSum!=crAmountSum){
					sumitFlag = "N";
					redragonJS.alert("借方和贷方金额不相等");	
					return false;
				}
				
				//提交表单
				if(sumitFlag=="Y"){
					l.ladda('start');
		        	form.submit();
				}
				
		    }
		});
	});
</script>