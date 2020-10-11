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
<%-- 分页页码 --%>
<%-- 注意事项：1.需要保证页码的对象名称为pages 2.需要保证页面跳转的方法名为gotoPage() --%>

<tr>
	<td colspan="30" class="footable-visible">
		<ul class="pagination float-right m-b-none">
		    <li><a href="javascript:void(0)">共${requestScope.pages.dataNumber}条</a></li>
			<li class="footable-page-arrow disabled">
			   <a data-page="prev" href="javascript:void(0)" onclick="gotoPage(${requestScope.pages.lastPage})"><i class="fa fa-angle-left"></i></a>
			</li>
			
			<c:forEach items="${requestScope.pages.paginationList}" var="data">
			    <c:choose>
			        <c:when test="${data.toString()==requestScope.pages.page.toString()}">
			            <li class="footable-page active"><a data-page="0" href="javascript:void(0)">${data}</a></li>
			        </c:when>
			        <c:when test="${data=='...'}">
                        <li class="footable-page"><a data-page="0" href="javascript:void(0)">${data}</a></li>
                    </c:when>
			        <c:otherwise>
			            <li class="footable-page"><a data-page="1" href="javascript:void(0)" onclick="gotoPage(${data})">${data}</a></li>
			        </c:otherwise>
			    </c:choose>
			</c:forEach>
			
			<li class="footable-page-arrow">
			   <a data-page="next" href="javascript:void(0)" onclick="gotoPage(${requestScope.pages.nextPage})"><i class="fa fa-angle-right"></i></a>
			</li>
		</ul>
	</td>
</tr>
