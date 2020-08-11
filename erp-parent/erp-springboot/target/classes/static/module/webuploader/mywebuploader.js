/**
 * mywebuploader处理
 */

	$(document).ready(function(){
		//按钮事件
		$("#fileUpload").on("click",function(){
			fileUpload();
		});
		
		//文件上传开始方法
		function fileUpload(){
			if(uploader.getFiles().length>0){
				
				var f = uploader.getFiles();
				
				//判断文件类型
				for(var a=0;a<f.length;a++){
					if(uploader.option('allowFile').toString().toLowerCase().indexOf(f[a].ext.toLowerCase())==-1){
						allowFileAlert(uploader.option('allowFile').toString().toLowerCase(), f[a].ext.toLowerCase());
						return;
					}
				}
				
				//判断文件大小
				for(var a=0;a<f.length;a++){
					if(f[a].size>uploader.option('allowFileSize')){
						allowFileSizeAlert(uploader.option('allowFileSize'), f[a].size);
						return;
					}
				}
				
				//控制上传按钮
				$("#fileUpload").removeClass("btn-success");
				$("#fileUpload").addClass("btn-warning");
				$("#fileUpload").text("暂停上传");
				$("#fileUpload").off("click");
				$("#fileUpload").on("click",function(){
					fileUploadStop();
				});
				
				for(var a=0;a<f.length;a++){
					//设置文字样式
					$('#'+f[a].id).find(".progress-percent").removeClass("text-danger");
					$('#'+f[a].id).find(".progress-percent").removeClass("text-success");
					$('#'+f[a].id).find(".progress-percent").removeClass("text-warning");
					$('#'+f[a].id).find(".progress-percent").addClass("text-primary");
					$('#'+f[a].id).find(".progress-speed").removeClass("text-danger");
					$('#'+f[a].id).find(".progress-speed").removeClass("text-success");
					$('#'+f[a].id).find(".progress-speed").removeClass("text-warning");
					$('#'+f[a].id).find(".progress-speed").addClass("text-primary");
					$('#'+f[a].id).find(".progress-speed").text('上传中');
				}
				
				//按钮控制
				$(".webuploader-container").fadeOut();
				$(".remove-this").fadeOut();
				
				uploader.upload();
			}
		}
		
		//文件上传暂停方法
		function fileUploadStop(){
			//控制上传按钮
			$("#fileUpload").removeClass("btn-warning");
			$("#fileUpload").addClass("btn-success");
			$("#fileUpload").text("开始上传");
			$("#fileUpload").off("click");
			$("#fileUpload").on("click",function(){
				fileUpload();
			});
			
			var f = uploader.getFiles();
			for(var a=0;a<f.length;a++){
				//设置文字样式
				$('#'+f[a].id).find(".progress-percent").removeClass("text-danger");
				$('#'+f[a].id).find(".progress-percent").removeClass("text-success");
				$('#'+f[a].id).find(".progress-percent").removeClass("text-primary");
				$('#'+f[a].id).find(".progress-percent").addClass("text-warning");
				$('#'+f[a].id).find(".progress-speed").removeClass("text-danger");
				$('#'+f[a].id).find(".progress-speed").removeClass("text-success");
				$('#'+f[a].id).find(".progress-speed").removeClass("text-primary");
				$('#'+f[a].id).find(".progress-speed").addClass("text-warning");
				$('#'+f[a].id).find(".progress-speed").text('上传暂停');
			}
			
			//按钮控制
			$(".webuploader-container").fadeIn();
			$(".remove-this").fadeIn();
			
			uploader.stop(true); 
		}
		
		//文件上传暂停方法(用于上传完成后的处理)
		function fileUploadStopError(){
			if(uploader.getFiles().length==0){
				//控制上传按钮
				$("#fileUpload").removeClass("btn-warning");
				$("#fileUpload").addClass("btn-success");
				$("#fileUpload").text("开始上传");
				$("#fileUpload").off("click");
				$("#fileUpload").on("click",function(){
					fileUpload();
				});
				
				//进度条样式控制
				$(".progress-bar").removeClass("active");
				
				//按钮控制
				$(".webuploader-container").fadeIn();
				$(".remove-this").fadeIn();
			}
		}
		
		//设置无文件DIV
		noneFileItem();
		
		//加入无文件DIV
		function noneFileItem(){
			if($("#thelist").html()==""){
				$("#thelist").append($(".none-item").clone().show());
				return true;
			}else{
				$("#thelist div.none-item").remove();
				return false;
			}
		}
		
		//beforeFileQueued
		
		var fileSumSize = 0;
		
		// 当有文件添加进来的时候  
		uploader.on('fileQueued', function(file) {
			//获取file item模板html
			var item = $("#itemModel").html();
			//获取文件总大小
			fileSumSize = fileSumSize+file.size;
			//获取文件大小
			var fileSize = file.size/1024;
			if(fileSize>1024){
				fileSize = fileSize/1024;
				fileSize = fileSize.toFixed(2)+"M";
			}else{
				fileSize = fileSize.toFixed(2)+"K";
			}
			//根据文件类型设置缩略图
			var extIcon = "";
			var ext = file.ext.toLowerCase();
			if(ext=="jpg"||ext=="jpeg"){
				//extIcon = "icon-jpg";
				extIcon = "img";
			}else if(ext=="gif"){
				//extIcon = "icon-gif";
				extIcon = "img";
			}else if(ext=="png"){
				//extIcon = "icon-png";
				extIcon = "img";
			}else if(ext=="jar"){
				extIcon = "icon-jar";
			}else if(ext=="doc"||ext=="docx"){
				extIcon = "icon-doc";
			}else if(ext=="xls"||ext=="xlsx"){
				extIcon = "icon-xls";
			}else if(ext=="ppt"||ext=="pptx"){
				extIcon = "icon-ppt";
			}else if(ext=="zip"){
				extIcon = "icon-zip";
			}else if(ext=="rar"){
				extIcon = "icon-rar";
			}else if(ext=="rmvb"){
				extIcon = "icon-rmvb";
			}else if(ext=="mov"){
				extIcon = "icon-mov";
			}else if(ext=="wmv"){
				extIcon = "icon-wmv";
			}else if(ext=="wma"){
				extIcon = "icon-wma";
			}else if(ext=="mp3"){
				extIcon = "icon-mp3";
			}else if(ext=="mp4"){
				extIcon = "icon-mp4";
			}else if(ext=="html"){
				extIcon = "icon-html";
			}else if(ext=="exe"){
				extIcon = "icon-exe";
			}else if(ext=="log"){
				extIcon = "icon-log";
			}else if(ext=="pdf"){
				extIcon = "icon-pdf";
			}else if(ext=="psd"){
				extIcon = "icon-psd";
			}else if(ext=="txt"){
				extIcon = "icon-txt";
			}else if(ext=="wps"){
				extIcon = "icon-wps";
			}else{
				extIcon = "icon-normal";
			}
			
			//替换item字符串
			item = item.replace("fileId", file.id);
			item = item.replace("fileName", file.name);
			item = item.replace("fileSize", fileSize);
			item = item.replace("fileStatus", "未上传");
			item = item.replace("extIcon", extIcon);
			
			var fileItem = $(item);
			
			//设置文字样式
			$(fileItem).find(".progress-percent").removeClass("text-danger");
			$(fileItem).find(".progress-percent").removeClass("text-success");
			$(fileItem).find(".progress-percent").removeClass("text-warning");
			$(fileItem).find(".progress-percent").addClass("text-primary");
			$(fileItem).find(".progress-speed").removeClass("text-danger");
			$(fileItem).find(".progress-speed").removeClass("text-success");
			$(fileItem).find(".progress-speed").removeClass("text-warning");
			$(fileItem).find(".progress-speed").addClass("text-primary");
			
			//删除要上传的文件
			$(fileItem).find(".remove-this").on("click",function(){
				//删除fileItem块
				$(fileItem).remove();
				//控制无文件DIV
				var flag = noneFileItem();
				//删除webuploader队列中的文件
				uploader.removeFile(file, true);
				//修改总文件数量和文件大小
				fileSumSize = fileSumSize-file.size;
				if(flag){
					$(".file-all-num").text("0");
					$(".file-all-size").text("0.00M");
				}else{
					$(".file-all-num").text(parseInt($(".file-all-num").text())-1);
					$(".file-all-size").text(parseFloat(fileSumSize/1024/1024).toFixed(2)+"M");
				}
				
			});
			
			//如果上传的图片显示缩略图
			if(extIcon=="img"){
				var img = $(fileItem).find("img");
				// 创建缩略图
			    uploader.makeThumb(file, function(error, src){
			        if(error){ 
			            $img.replaceWith('<span>不能预览</span>');
			            return;
			        }
			        $(img).attr('src', src);
			    }, 115, 115);

			}
			
			//控制无文件DIV
			noneFileItem();
			//加入fileItem块
			$("#thelist").append($(fileItem));
			
			//修改总文件数量和文件大小
			$(".file-all-num").text(parseInt($(".file-all-num").text())+1);
			$(".file-all-size").text(parseFloat(fileSumSize/1024/1024).toFixed(2)+"M");
		});	
		
		// 文件上传过程中创建进度条实时显示。
		uploader.on('uploadProgress', function(file, percentage){
		    var fileItem = $('#'+file.id);
		    var percent = $(fileItem).find('.progress .progress-bar');

		    // 避免重复创建
		    if(!percent.length){
		    	percent = $('<div class="progress progress-xs">' +
		          '<div class="progress-bar progress-bar-striped active" style="width: 0%">' +
		          '</div></div>').appendTo($(fileItem).find('.file-progress')).find('.progress-bar');
		    }

		    $(fileItem).find('p.file-upload-info .progress-speed').text('上传中');
		    $(fileItem).find('.progress-percent').text(' - '+parseFloat(percentage * 100).toFixed(2)+'%');

		    $(percent).css( 'width', percentage * 100 + '%' );
		});
		
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file){
			var fileItem = $('#'+file.id);
			$(fileItem).find('p.file-upload-info .progress-speed').text('上传成功');
			//设置文字样式
			$(fileItem).find(".progress-percent").removeClass("text-danger");
			$(fileItem).find(".progress-percent").removeClass("text-primary");
			$(fileItem).find(".progress-percent").removeClass("text-warning");
			$(fileItem).find(".progress-percent").addClass("text-success");
			$(fileItem).find(".progress-speed").removeClass("text-danger");
			$(fileItem).find(".progress-speed").removeClass("text-primary");
			$(fileItem).find(".progress-speed").removeClass("text-warning");
			$(fileItem).find(".progress-speed").addClass("text-success");
			
			//删除webuploader队列中的文件
			uploader.removeFile(file, true);
			//效果控制
			fileUploadStopError();
			
		});

		// 文件上传失败，显示上传出错。
		uploader.on('uploadError', function(file){
			var fileItem = $('#'+file.id);
			$(fileItem).find('p.file-upload-info .progress-speed').text('上传失败，错误的文件无法重新上传需手工删除');
			$(fileItem).find('.progress-percent').text(' - 0%');
			//设置文字样式
			$(fileItem).find(".progress-percent").removeClass("text-success");
			$(fileItem).find(".progress-percent").removeClass("text-primary");
			$(fileItem).find(".progress-percent").removeClass("text-warning");
			$(fileItem).find(".progress-percent").addClass("text-danger");
			$(fileItem).find(".progress-speed").removeClass("text-success");
			$(fileItem).find(".progress-speed").removeClass("text-primary");
			$(fileItem).find(".progress-speed").removeClass("text-warning");
			$(fileItem).find(".progress-speed").addClass("text-danger");
			//删除进度条
			$(fileItem).find('.file-progress').html("");
			
			//删除webuploader队列中的文件
			uploader.removeFile(file, true);
			//效果控制
			fileUploadStopError();
			
		});
		
	});