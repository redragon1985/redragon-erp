<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="item none-item" style="display: none">
	<div class="empty text-grey-100"> <i class="fa fa-file mr-5"></i>还没有选择文件</div>
</div>

<div id="itemModel" style="display: none;">
<div id="fileId" class="item">
	<a class="remove-this fa fa-remove"></a>
	<div class="thumb-area">
		<span class="extIcon"></span>
		<img />
	</div>
	<div class="status-area">
		<h4 class="info">fileName</h4>
		<p class="file-upload-info">
			文件大小：fileSize<span class="progress-percent"> - 0%</span> 
			<span class="progress-speed"> - fileStatus</span>
		</p>
		<div class="file-progress">
		</div>
	</div>
</div>
</div>
