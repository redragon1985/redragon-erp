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
<%
String alertType = request.getParameter("alertType");
String alertMessage = request.getParameter("alertMessage");
%>

<%if(alertType!=null&&!alertType.equals("")){ %>
<%if(alertType.equals("success")){ %>
<div class="alert alert-success alert-dismissable">
	<button aria-hidden="true" data-dismiss="alert" class="close"
		type="button">×</button>操作成功
</div>
<%}else if(alertType.equals("hint")&&alertMessage!=null&&!alertMessage.equals("")){ %>
<div class="alert alert-warning alert-dismissable">
	<button aria-hidden="true" data-dismiss="alert" class="close"
		type="button">×</button><%=alertMessage %>
</div>
<%}else if(alertType.equals("error")){ %>
<div class="alert alert-danger alert-dismissable">
	<button aria-hidden="true" data-dismiss="alert" class="close"
		type="button">×</button>操作失败
</div>
<%}%>
<%}%>