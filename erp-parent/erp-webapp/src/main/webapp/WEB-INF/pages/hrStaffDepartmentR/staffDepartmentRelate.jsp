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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%-- 导入面包屑 --%>
<jsp:include page="../common/nav.jsp"></jsp:include>

<div class="wrapper wrapper-content animated fadeInRight">

	<%-- 导入提示信息框 --%>
	<c:if test="${hint!=null&&hint!=''}">
		<jsp:include page="../common/alert/alert.jsp">
			<jsp:param value="${hint}" name="alertType" />
			<jsp:param value="${hintMessage}" name="alertMessage" />
		</jsp:include>
	</c:if>

	<div class="row">
		<div class="col-sm-6">
			<c:import url="/web/hrDepartment/getHrDepartmentTreeReadOnly" charEncoding="utf-8"></c:import>
		</div>
		<div id="relateListDiv" class="col-sm-6"></div>
		
	</div>
	
</div>

<%-- 编辑值的模式对话框 --%>
<div id="addModal"></div>

<script>
	//jstree单击事件
	var selectedNodeId = -1;
	function clickJsTree(nodeId){
		if(selectedNodeId!=nodeId){
			selectedNodeId = nodeId;
			getRelateListAjax(selectedNodeId);
		}
	}
	
	//默认jstree选择
	function stateReadyJsTree(nodeId){
		if("${param.departmentId}"==""){
			selectedNodeId = nodeId;
			getRelateListAjax(selectedNodeId);
		}else{
			selectedNodeId = nodeId;
		}
	}
	
	//ajax获取关联列表
	function getRelateListAjax(id){
		redragonJS.loading("relateListDiv");
	
		$.ajax({
			type: "get",
			url: "web/hrStaffDepartmentR/getRelateListAjax",
			data: {"departmentId": id},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("relateListDiv");
				if(data!=""){
					$("#relateListDiv").html(data);
					initControlAuth();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	function editData(id){
		var nodeId = getTreeSelectNodeId();
		if(nodeId!=""){
			getAddRelateModal(nodeId, id);
		}else{
			redragonJS.alert("请先选择一个组织节点");
		}	
		
	}
	
	function deleteData(id) {
		redragonJS.confirm("确认删除数据？", function(){
			window.location.href="web/hrStaffDepartmentR/deleteHrStaffDepartmentR?sdId="+id;
		});
	}
	
	function getAddRelateModal(nodeId, id){
		$.ajax({
			type: "post",
			url: "web/hrStaffDepartmentR/getHrStaffDepartmentR",
			data: {"departmentId": nodeId,"sdId": id},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#addModal").html(data);
					$('#addDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
</script>  