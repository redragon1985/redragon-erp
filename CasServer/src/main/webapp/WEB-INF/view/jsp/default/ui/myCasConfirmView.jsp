<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
  <meta charset="UTF-8" />
  
  <title>CAS &#8211; Central Authentication Service</title>
  
  <spring:theme code="standard.custom.css.file" var="customCssFile" />
  <link rel="stylesheet" href="<c:url value="${customCssFile}" />" />
  <link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
  
  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.1/html5shiv.js" type="text/javascript"></script>
  <![endif]-->
</head>
<body id="cas">
  <div id="container">
      <header>
        <a id="logo" href="http://www.jasig.org" title="<spring:message code="logo.title" />">Jasig</a>
        <h1>Central Authentication Service (CAS)</h1>
      </header>
      <div id="content">


		  <div id="msg" class="info">
		    <p><spring:message code="screen.confirmation.message" arguments="${fn:escapeXml(param.service)}${fn:indexOf(param.service, '?') eq -1 ? '?' : '&'}ticket=${serviceTicketId}" /></p>
		  </div>
		  
		  <script>
		  $("").
		  </script>


      </div> <!-- END #content -->
      
      <footer>
        <div id="copyright">
          <p><spring:message code="copyright" /></p>
          <p>Powered by <a href="http://www.jasig.org/cas">Jasig Central Authentication Service <%=org.jasig.cas.CasVersion.getVersion()%></a></p>
        </div>
      </footer>

    </div> <!-- END #container -->
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
    
    <%-- 
        JavaScript Debug: A simple wrapper for console.log 
        See this link for more info: http://benalman.com/projects/javascript-debug-console-log/
    --%>
    <script type="text/javascript" src="https://github.com/cowboy/javascript-debug/raw/master/ba-debug.min.js"></script>
    
    <spring:theme code="cas.javascript.file" var="casJavascriptFile" text="" />
    <script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>
  </body>
</html>
