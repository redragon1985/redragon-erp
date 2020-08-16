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
