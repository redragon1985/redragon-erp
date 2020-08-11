
var redragonJS = {
		
		//确认弹层
　　　　confirm: function(content,func){
	 		swal({
				title : "",
				text : content,
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确定",
				cancelButtonText : "取消",
				closeOnConfirm: false
			},function(isConfirm){
				if(isConfirm){
					func();
				}
			});
　　　　},
    
    	//确认弹层
　　　　confirm2: function(content,func){
	 		swal({
				title : "",
				text : content,
				showCancelButton : true,
				confirmButtonColor : "#ec4758",
				cancelButtonText : "返回",
				confirmButtonText : "删除",
				closeOnConfirm: false,
			},function(isConfirm){
				if(isConfirm){
					func();
				}
			});
　　　　},

		//alert弹层
		alert: function(content, type){
			swal({
				title: "",
				type: type,
				text : content,
				confirmButtonText:"确定",
				confirmButtonColor: '#3085d6',
			});
		},
		
		//关闭弹层
		close: function(){
			swal.close();
		},
		
		//loading弹层
		loading: function(divId){
			$("#"+divId).append('<div id="loadingDiv" class="sk-spinner sk-spinner-wave"><div class="sk-rect1"></div><div class="sk-rect2"></div><div class="sk-rect3"></div><div class="sk-rect4"></div><div class="sk-rect5"></div></div>');
			$("#"+divId).addClass('ibox-content sk-loading');
		},

		//移除loading弹层
		removeLoading: function(divId){
			$("#"+divId).find("#loadingDiv").remove();
			$("#"+divId).removeClass('sk-loading');
		},

};
