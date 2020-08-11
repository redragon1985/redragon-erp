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
<%@ tag import="org.apache.shiro.util.StringUtils" %> 
<%@ tag import="org.apache.shiro.SecurityUtils" %> 
<%@ tag import="java.util.Arrays" %> 
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> 
<%@ attribute name="name" type="java.lang.String" required="true" description="角色列表" %> 
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="角色列表分隔符" %>
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

    String[] roles = name.split(delimiter); 

    if(!SecurityUtils.getSubject().hasAllRoles(Arrays.asList(roles))) { 
        return; 
    } else { 
%> 

        <jsp:doBody/> 

<% 
    } 
%> 
