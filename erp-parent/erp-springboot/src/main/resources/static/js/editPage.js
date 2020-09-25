/**
 * 编辑页通用JS
 */
/*
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
*/

/**
 * 返回会计分录模式对话框
 */
function getVoucherEntryModal(billType, billHeadCode){
	$('#voucherEntryDiv').modal('hide');
	redragonJS.loading("ibox-content1");
	$.ajax({
		type: "post",
		url: "web/finVoucherModelHead/getVoucherEntryModal",
		data: {"billType": billType, "billHeadCode": billHeadCode},
		async: false,
		dataType: "html",
		cache: false,
		success: function(data){
			redragonJS.removeLoading("ibox-content1");
			if(data!=""){
				$("#voucherEntryModal").html(data);
				$('#voucherEntryDiv').modal('show');
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			if(textStatus=="error"){
				redragonJS.alert("会计分录未生成或生成失败");
			}
		}
	});
}