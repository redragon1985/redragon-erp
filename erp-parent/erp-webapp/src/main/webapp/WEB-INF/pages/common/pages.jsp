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
			        <c:when test="${data==requestScope.pages.page}">
			            <li class="footable-page active"><a data-page="0" href="javascript:void(0)">${data}</a></li>
			        </c:when>
			        <c:when test="${data=='...'}">
                        <li class="footable-page active"><a data-page="0" href="javascript:void(0)">${data}</a></li>
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

<script>
//跳转页面
function gotoPage(page){
	var pageNumber = ${requestScope.pages.pageNumber};
	var currentPage = ${requestScope.pages.page};
	//首页和尾页无需跳转
	if((currentPage==1&&page==1)||(currentPage==pageNumber&&page==pageNumber)){
		
	}else{
		if(window.location.href.indexOf("page=")==-1&&window.location.href.indexOf("?")==-1){
			window.location.href = window.location.href+"?page="+page;
		}else if(window.location.href.indexOf("page=")==-1&&window.location.href.indexOf("?")!=-1){
			window.location.href = window.location.href+"&page="+page;
		}else if(window.location.href.indexOf("page=")!=-1){
			//如果存在page参数则替换老的参数为新的参数
			var index = window.location.href.indexOf("page=");
			var str = window.location.href.substring(index+5);
			var oldPage = "";
			if(str.indexOf("&")==-1){
				oldPage = str;
			}else{
				oldPage = str.substring(0,str.indexOf("&"));
			}
			
			if(oldPage!=""){
				window.location.href = window.location.href.replace("page="+oldPage, "page="+page);
			}
			
		}
	}
}
</script>
