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