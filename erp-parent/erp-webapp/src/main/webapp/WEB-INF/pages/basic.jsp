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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ERP</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="ERP系统">
	<meta http-equiv="description" content="ERP系统">

    <%-- head部分导入 --%>
    <jsp:include page="common/head.jsp"></jsp:include>
    
    <%-- javascript导入 --%>
    <jsp:include page="common/script.jsp"></jsp:include>
    
  </head>
  
  <body>
    <div id="wrapper">
    
        <%-- left部分导入 --%>
        <jsp:include page="common/left.jsp">
        	<jsp:param value="${param.content}" name="contentPage"/>
        </jsp:include>

        <div id="page-wrapper" class="gray-bg">

	        <%-- top部分导入 --%>
	        <jsp:include page="common/top.jsp"></jsp:include>
	        
	        <%-- 页面内容导入 --%>
	        <div id="page-content">
	        	<jsp:include page="${param.content}"></jsp:include>
	        </div>
	        
	        <%-- foot部分导入 --%>
	        <jsp:include page="common/foot.jsp"></jsp:include>
        
        </div>
        
        <%-- right部分导入 --%>
        <jsp:include page="common/right.jsp"></jsp:include>
    
    </div>
    
  </body>
</html>
