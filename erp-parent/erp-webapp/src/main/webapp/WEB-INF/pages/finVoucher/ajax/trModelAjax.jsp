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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<tr class="addLineModel dataTr">
	<td>
		<div class="addDiv" style="position: absolute; margin-left: 0px; margin-top: -28px; cursor: pointer; display: none;"><i class="fa fa-plus-circle fa-lg" title="添加行"></i></div>
		<div class="removeDiv" style="position: absolute; margin-left: 0px; margin-top: -7px; cursor: pointer; display: none;"><i class="fa fa-minus-circle fa-lg" title="删除行"></i></div>
		<input name="voucherLineId" type="hidden" class="form-control" value="">
		<input name="memo" type="text" class="form-control" value="">
	</td>
	<td>
		<input name="subjectDesc" type="text" class="form-control" value="">
		<input name="subjectCode" type="hidden" class="form-control" value="">
	</td>
	<td>
		<div class="drDiv" style="position: absolute; margin-left: 8px; ">
			<input name="drAmount" type="text" class="form-control drAmount" value="0" style="width: 150%;">
			
			<select class="form-control drAmountSelect" name="drAmount" style="width: 250%; display: none;" disabled="true">
            	<option value="" selected="selected">无</option>
            	<option value="AMOUNT" selected="selected">金额</option>
            </select>	
		</div>
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
	<td>
		<div class="crDiv" style="position: absolute; margin-left: 8px; ">
			<input name="crAmount" type="text" class="form-control crAmount" value="0" style="width: 150%;">
			
			<select class="form-control crAmountSelect" name="crAmount" style="width: 250%; display: none;" disabled="true">
               	<option value="" selected="selected">无</option>
               	<option value="AMOUNT" selected="selected">金额</option>
            </select>
		</div>
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
</tr>

<script>
$(document).ready(function(){
	
	//移除行效果
	$(".addLineModel").on("mouseover",function(){
		$(this).find(".removeDiv").show();
	});
	
	$(".addLineModel").on("mouseleave",function(){
		$(this).find(".removeDiv").hide();
	});
	
	//金额效果
	$(".addLineModel").each(function(i){
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
	});
	
	//移除行
	$(".removeDiv").click(function(){
		var tempObj = $(this);
		redragonJS.confirm("确认删除凭证行？", function(){
			if(tempObj.parent("td").parent("tr").find("input[name='voucherLineId']").val()==""){
				tempObj.parent("td").parent("tr").remove();
				redragonJS.close();
			}
		});
	});
	
	//科目选择框
	$("input[name='subjectDesc']").focus(function(){
		selectSubjectInput = $(this);
		$('#subjectTreeDiv').modal('show');
	});
});
</script>