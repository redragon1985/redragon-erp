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
	<form action="web/sysRole/relateSysRoleAuth" method="post">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox">
				<div class="ibox-title">
					<h5>角色与权限关联</h5>
					<div class="ibox-tools">
					</div>
				</div>
				<div class="ibox-content">
				
					<div class="col-sm-12">
						<span class="text-info"><label class="col-form-label"><strong><span class="text-danger">*</span>角色选择</strong></label></span>
						<select id="roleCode" name="roleCode" class="chosen-select"  tabindex="1">
			                <option value="">请选择...</option>
			                <c:forEach items="${sysRoleList}" var="data">
			                	<option value="${data.roleCode}">${data.roleName}
				                	<c:if test="${data.status=='N'}">
			                		(无效)
			                		</c:if>
			                	</option>
			                </c:forEach>
		                </select>
					</div>	
					<div class="hr-line-dashed"></div>	
					
	                <div id="authListDiv" class="wizard-big">	
						<div class="col-sm-12">
							<span class="text-info"><label class="col-form-label"><strong>权限选择</strong></label></span>
								<select id="authCode" name="authCode" class="form-control dual_select" multiple>
									<%-- 未选择项 --%>
									<c:forEach items="${sysAuthList}" var="data">
										<option value="${data.authCode}">${data.authName}
											<c:if test="${data.status=='N'}">
					                		(无效)
					                		</c:if>
										</option>
									</c:forEach>
									<%-- 已选择项 --%>
									<c:forEach items="${sysAuthRelateList}" var="data">
										<option selected value="${data.authCode}">${data.authName}
											<c:if test="${data.status=='N'}">
					                		(无效)
					                		</c:if>
										</option>
									</c:forEach>
								</select>
						</div>
						<div class="hr-line-dashed"></div>	
					</div>
					
					<div class="form-group">
						<div class="col-sm-12 col-sm-offset-2 text-right">
							<button class="ladda-button ladda-button-demo btn btn-primary btn-lg" data-style="expand-right">&nbsp;&nbsp;保存&nbsp;&nbsp;<i class="fa fa-save"></i></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</div>

<!-- Chosen -->
<script src="js/plugins/chosen/chosen.jquery.js"></script>
<link href="css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">

<!-- Dual Listbox -->
<link href="css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">
<script src="js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
<script>
$(document).ready(function(){

	//初始化roleCode
	if("${param.roleCode}"!=""){
		$("#roleCode").val("${param.roleCode}");
	}

	$('.chosen-select').chosen({width: "100%"});

	$('.dual_select').bootstrapDualListbox({
        selectorMinimalHeight: 160,
        nonSelectedListLabel: '未选择的角色',
	    selectedListLabel: '已选择的角色',
	    filterPlaceHolder: '输入查询条件...',
	    moveSelectedLabel: "添加",
	    moveAllLabel: '添加所有',
	    removeSelectedLabel: "移除",
	    removeAllLabel: '移除所有',
	    filterTextClear: "显示所有",
	    infoText: '共{0}个角色',
	    infoTextFiltered: '<span class="label label-warning">搜索到</span>&nbsp;{0}个角色 ,共{1}个角色',
	    infoTextEmpty: '列表为空'
    });
    
    var l = $('.ladda-button-demo').ladda();

	l.click(function() {
		$("form").valid();
		//l.ladda('stop');
	});
	
	$("form").validate({
		rules : {
			roleCode : {
				required : true,
			},
			/*允许取消关联
			authCode : {
				required : true,
			}
			*/
		},
		messages:{
			roleCode:{
				required:"请选择角色",
			},
			authCode:{
				required:"未给角色关联权限",
			}
		},
		submitHandler: function(form) {
			l.ladda('start');
	        form.submit();
	    }
	});
	
	
	
	//异步获取角色关联的权限
	$("#roleCode").on("change", function(){
		redragonJS.loading("authListDiv");
		
		$.ajax({
			type: "post",
			url: "web/sysRole/getAuthListRelateAjax",
			data: {"roleCode": $("#roleCode").val()},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				redragonJS.removeLoading("authListDiv");
				if(data!=""){
					$("#authListDiv").html(data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
		
	});
    
});
</script>

