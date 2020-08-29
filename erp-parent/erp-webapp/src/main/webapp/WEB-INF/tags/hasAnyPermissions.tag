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
<%@ tag import="org.apache.shiro.util.StringUtils" %> 
<%@ tag import="org.apache.shiro.SecurityUtils" %> 
<%@ tag import="java.util.Arrays" %> 
<%@ tag import="org.apache.shiro.subject.Subject" %> 
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> 
<%@ attribute name="name" type="java.lang.String" required="true" description="权限字符串列表" %> 
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="权限字符串列表分隔符" %>
<% 
    if(!StringUtils.hasText(delimiter)) { 
        delimiter = ",";//默认逗号分隔 
    } 

    if(!StringUtils.hasText(name)) { 
%> 

        <jsp:doBody/> 

<% 
        return; 
    } 

    String[] permissions = name.split(delimiter); 

    Subject subject = SecurityUtils.getSubject(); 

    for(String permission : permissions) { 
        if(subject.isPermitted(permission)) { 
%> 

            <jsp:doBody/> 

<% 
        } 
    } 
%> 
