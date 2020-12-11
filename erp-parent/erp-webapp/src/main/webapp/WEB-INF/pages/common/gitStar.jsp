<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<script>
//自动加载star模式对话框
$(document).ready(function(){
	$('#gitstartModal').modal({backdrop: 'static', keyboard: false});
	$('#gitstartModal').modal('show');
	
	$("#gitstarButton").mouseover(function(){
		$(this).find("i").removeClass("fa-star-o");
		$(this).find("i").addClass("fa-star");
	});
	
	$("#gitstarButton").mouseout(function(){
		$(this).find("i").removeClass("fa-star");
		$(this).find("i").addClass("fa-star-o");
	});
	
	$("#gitstarButton1").click(function(){
		window.open("https://gitee.com/redragon/redragon-erp/stargazers");
	});
});
</script>

<div class="modal inmodal" id="gitstartModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<%-- 
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				--%>
				<i class="fa fa-comments-o modal-icon text-danger"></i>
				<h4 class="modal-title" style="color: black;">入群赤龙ERP</h4>
			</div>
			<div class="modal-body">
				<h3 style="line-height:28px; text-align: center;">
					别犹豫，马上入群！与我们一起探讨【赤龙ERP】的技术或业务，带你走进不一样的开源ERP世界！
				</h3>
				<center><img src="http://www.redragon-erp.com/images/redragon.png" width="150" height="150" alt="赤龙ERP沟通群"/></center>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white btn-notcontrol" data-dismiss="modal">再想想</button>
				<%-- 
				<button type="button" class="btn btn-danger" id="gitstarButton1" style="font-size: 16px; font-weight: bold;"><i class="fa fa-chevron-circle-right"></i> 去点星</button>
				--%>
			</div>
		</div>
	</div>
</div>
