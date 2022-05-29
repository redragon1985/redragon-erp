<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ERP登陆</title>

<link rel="icon" type="image/x-icon" href="/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" />
<link rel="Bookmark" type="image/x-icon" href="/favicon.ico" />

<link rel="shortcut icon" href="erp/favicon.ico" />

<link href="erp/css/bootstrap.min.css" rel="stylesheet">
<link href="erp/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="erp/css/animate.css" rel="stylesheet">
<link href="erp/css/style.css" rel="stylesheet">
<link href="erp/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="erp/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
<script src="erp/js/jquery-3.1.1.min.js"></script>
<script src="erp/js/bootstrap.min.js"></script>
<script src="erp/js/plugins/iCheck/icheck.min.js"></script>
<script src="erp/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="erp/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="erp/js/inspinia.js"></script>
<script src="erp/js/plugins/pace/pace.min.js"></script>

</head>

<body id="login" class="gray-bg login">

	<c:if test="${not pageContext.request.secure}">
		<div id="msg" class="errors">
			<h2>不安全的链接</h2>
			<p>您当前正在通过一种不安装的方式登录CAS。 请使用HTTPS的方式登录！</p>
		</div>
	</c:if>

	<div class="loginColumns animated fadeInDown">
		<div class="row">

			<div class="col-md-6">
				<h2 class="font-bold"><img src="erp/images/logo.png" alt="赤龙ERP" width="100" height="40"><span style="vertical-align: middle; font-size: 28px;">ERP</span></h2>

				<p>由国内经验丰富的专业人士精心打造</p>

				<p>企业级的ERP开源解决方案：系统提供“<b>订单</b>”、“<b>库存</b>”、“<b>发票</b>”、“<b>收付款</b>”、“<b>凭证</b>”、“<b>主数据</b>”、“<b>权限</b>”等丰富的业务及财务功能</p>
				
				<p><span style="font-size: 14px;"><i class="fa fa-question-circle"></i> 何须高价</span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-up text-danger"></i> 让所有小微企业都用得起ERP<br/>

				<span style="font-size: 14px;"><i class="fa fa-question-circle"></i> 何须复杂</span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-up text-danger"></i> 唯一的主线流程设计，让使用变得更容易<br/>
				
				<span style="font-size: 14px;"><i class="fa fa-question-circle"></i> 何须定制</span>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-thumbs-up text-danger"></i> 领先的ERP管理理念，真正提升企业的管理</p>
				
				<p style="color: silver;">
					<small>受GPL-2.0许可协议保护，未经开发者允许，不得用于商业用途</small>
				</p>

			</div>
			<div class="col-md-6">
				<div class="ibox-content">
				
				    <form:form method="post" id="fm1" class="m-t" role="form" commandName="${commandName}" htmlEscape="true">
				    
					    <div id="alert" class="alert alert-danger alert-dismissable">
						    <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
						    <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
						</div>
				    
						<div class="form-group">
							<input id="username" name="username" class="form-control" type="text" placeholder="用户名" required="" />	
						</div>
						<div class="form-group">
							<input id="password" name="password" class="form-control" type="password" placeholder="密码" required="" />
						</div>
						<div class="form-group row">
							<div class="col-lg-offset-2 col-lg-10">
								<div class="i-checks">
									<label class="">
										<div class="icheckbox_square-green" style="position: relative;">
											<input type="checkbox" id="rememberMe" name="rememberMe" style="position: absolute; opacity: 0;" />
											<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
										</div> 
										<i></i> 记住密码
									</label>
								</div>
							</div>
						</div>
						
						<button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                        
                        <p class="text-muted text-center">
                            <small>还没有账号？码云点击<i class="fa fa-star"></i>可享如下特权</small>
                        </p>
                        <a class="btn btn-sm btn-white btn-block" href="https://www.redragon-erp.com/account.html" target="_blank">申请赤龙ERP账号</a>
                        
                        <%-- 隐藏字段 --%>
                        <input type="hidden" name="lt" value="${loginTicket}" />
                        <input type="hidden" name="execution" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submit" />
                        
					</form:form>
					<p class="m-t">
						<small>Redragon &copy; 2020</small>
					</p>
				</div>
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-md-6">Redragon</div>
			<div class="col-md-6 text-right">
				<small>© 2020-2021</small>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});
			
			//控制提示信息
			if($("#msg").text()==""){
			    $("#alert").hide();
			}
		});
	</script>

</body>
</html>
