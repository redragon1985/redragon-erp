/**
 * 编辑页通用JS
 */
$(document).ready(function() {
	//设置收起的title效果
	$(".collapse-link").on("click", function(){
		if($(this).find("h5").html().indexOf("<span class=\"title-icon-spin\">")==-1){
			$(this).find("h5").append("<span class=\"title-icon-spin\"><i class=\"fa fa-chrome fa-spin\"></i></span>");
		}else{
			$(this).find("h5").find("span[class='title-icon-spin']").remove();
		}
		
	});
});