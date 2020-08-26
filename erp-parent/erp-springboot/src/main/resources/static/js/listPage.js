/**
 * 列表页通用JS
 */
$(document).ready(function() {
	
	//查询按钮效果
	$("#searchButton").click(function(){
	    if($("#searchDiv").css("display")=="none"){
	        $("#searchDiv").show();
	        $("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;关闭查询');
	        $("#searchButton").addClass("btn-outline btn-warning");
	        $("#searchButton").blur();
	    }else{
	        $("#searchDiv").hide();
	        $("#searchButton").html('<i class="fa fa-search"></i>&nbsp;&nbsp;展开查询');
	        $("#searchButton").removeClass("btn-outline btn-warning");
	        $("#searchButton").blur();
	    }
	});
});