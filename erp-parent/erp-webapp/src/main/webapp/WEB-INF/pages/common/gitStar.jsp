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
				<i class="fa fa-warning modal-icon text-danger" style="font-size: 60px;"></i>
				<h4 class="modal-title" style="color: black;">赤龙ERP社区版授权</h4>
			</div>
			<div class="modal-body">
				<h3 style="line-height:28px; text-align: center;">
					【赤龙ERP社区版】现免费对用户开放“授权申请”，申请后可获取更多软件权益，使用或商用前建议先获得授权书。企业或个人均可申请使用授权或商用授权
				</h3>
				<center><a href="https://www.redragon-erp.com/grant-community.html" target="_blank"><i class="fa fa-hand-o-right"></i> 免费获取授权书</a></center>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white btn-notcontrol" data-dismiss="modal">关闭</button>
				
				<button type="button" class="btn btn-danger" id="gitstarButton1" style="font-weight: bold;"><i class="fa fa-chevron-circle-right"></i> 去点星</button>

			</div>
		</div>
	</div>
</div>
