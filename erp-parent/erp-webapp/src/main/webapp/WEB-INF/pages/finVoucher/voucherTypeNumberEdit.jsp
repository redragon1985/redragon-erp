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
			<jsp:param value="hint" name="alertType"/>
			<jsp:param value="${fn:replace(requestScope.hints,';', '<br/>')}" name="alertMessage"/>
		</jsp:include>
	</c:if>

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h4>凭证号维护</h4>
					<div class="ibox-tools">
					</div>
				</div>

				<div class="ibox-content">
				
					<c:forEach items="${requestScope.voucherTypeMap}" var="data">
						<div class="form-group  row">
							 <div class="col-sm-12">
                                 <div class="input-group">
                                 	<div class="input-group-prepend">
                                    	<span class="input-group-addon"><i class="fa fa-tachometer"></i>&nbsp;<strong>凭证字(${data.value})当前流水号</strong></span>
                                    </div>
                                 	<input type="text" class="form-control voucherNumber" value="${requestScope.voucherNumberMap[data.key]}">
                                 	<input type="hidden" class="form-control voucherType" value="${data.key}">
                                 	<span class="input-group-append"><button type="button" class="btn btn-primary setButton">重置初始值</button></span>
                                 </div>
                                 <c:if test="${requestScope.voucherNumberMap[data.key]==-1}">
                                 	<span style="color: silver;">当前流水号存在问题或丢失，请根据流水号使用情况重置</span>
                                 </c:if>
                             </div>
						</div>
						<div class="hr-line-dashed"></div>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		$(".setButton").click(function(){
			var voucherNumber = $(this).parent().parent().find(".voucherNumber").val();
			var voucherType = $(this).parent().parent().find(".voucherType").val();

			redragonJS.confirm("确认将流水号的初始值设置为"+voucherNumber+"？", function(){
				window.location.href="web/finVoucherModelHead/editVoucherTypeNumber?voucherNumber="+voucherNumber+"&voucherType="+voucherType;
			});
			
		});
	});
</script>