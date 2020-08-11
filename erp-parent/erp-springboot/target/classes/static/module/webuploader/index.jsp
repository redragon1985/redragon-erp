<%@page import="redragon.util.i18n.i18n"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String cssUrl=i18n.getKeyValue("global", "cssStyle_ip");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--引入CSS-->
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/icons/fontawesome/styles.min.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/icons/fileicon/style.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/components.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/colors.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/css/sidebar_t.css">
	<link rel="stylesheet" type="text/css" href="<%=cssUrl%>/assets/js/plugins/webuploader-0.1.5/webuploader.css">


	<!--引入JS-->
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>module/webuploader/webuploader.js"></script>

	<script type="text/javascript" src="<%=basePath%>module/webuploader/mywebuploader.js"></script>
  </head>
  
  <body class="navbar-top pace-done sidebar-xs">
  
  	<jsp:include page="fileItem.jsp"></jsp:include>

	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<div class="content-wrapper">

				<!-- Content area -->
				<div class="content">
					<div class="row" style="width: 800px;">
						<div id="uploader" class="wu-example">
							<div class="btns">
								<div class="pull-right mt-5">文件数量：<span class="text-primary mr-5 file-all-num">0</span> 总大小：<span class="text-primary file-all-size">0.00M</span></div>
								<button id="fileUpload" class="btn btn-success">开始上传</button>
								<div id="picker" class="webuploader-container">
									<div id="webuploader-pick" class="webuploader-pick">选择文件</div>
									<div id="rt_rt_1c3fpgp00270105sj71cv26ou1"
										style="position: absolute; top: 0px; left: 0px; width: 88px; height: 34px; overflow: hidden; bottom: auto; right: auto;">
										<input type="file" name="file"
											class="webuploader-element-invisible" multiple="multiple">
										<label
											style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
									</div>
								</div>
							</div>
							
							<div style="height: 300px;overflow-y:auto;">
								<div id="thelist" class="uploader-list"></div>
							</div>
						</div>
					</div>
				</div>

			</div>
			
		</div>
		
	</div>

	<script>
	/*
	初始化上传对象
	*/
	var uploader = WebUploader.create({
		
		// 选完文件后，是否自动上传。  
		auto: false,
		
	    // swf文件路径
	    swf: '<%=basePath%>module/webuploader/Uploader.swf',

	    // 文件接收服务端。
	    server: 'http://localhost:8081/baseProject/file/uploadFile.action',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false,
	    
       	method:'POST',

		allowFile:['png','jpg','xlsx','pdf','7z'],
		
		allowFileSize:100*1024*1024
	});
	
	//验证文件类型的提示
	function allowFileAlert(allowfile, fileExt){
		alert("文件格式不匹配");
	}
	
	//验证文件大小的提示
	function allowFileSizeAlert(allowfileSize, fileSize){
		alert("上传文件过大");
	}
	
	</script>

  </body>
</html>
