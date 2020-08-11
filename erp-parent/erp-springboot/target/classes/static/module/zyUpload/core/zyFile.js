/*
 * zyFile.js 基于HTML5 文件上传的核心脚本 http://www.czlqibu.com
 * by zhangyan 2014-06-21   QQ : 623585268
*/

var ZYFILE = {
		fileInput : null,             // 选择文件按钮dom对象
		uploadInput : null,           // 上传文件按钮dom对象
		dragDrop: null,				  //拖拽敏感区域
		url : "",  					  // 上传action路径
		uploadFile : [],  			  // 需要上传的文件数组
		lastUploadFile : [],          // 上一次选择的文件数组，方便继续上传使用
		perUploadFile : [],           // 存放永久的文件数组，方便删除使用
		fileNum : 0,                  // 代表文件总个数，因为涉及到继续添加，所以下一次添加需要在它的基础上添加索引
		/* 提供给外部的接口 */
		filterFile : function(files){ // 提供给外部的过滤文件格式等的接口，外部需要把过滤后的文件返回
			return files;
		},
		onSelect : function(selectFile, files){      // 提供给外部获取选中的文件，供外部实现预览等功能  selectFile:当前选中的文件  allFiles:还没上传的全部文件
			
		},
		onDelete : function(file, files){            // 提供给外部获取删除的单个文件，供外部实现删除效果  file:当前删除的文件  files:删除之后的文件
			
		},
		onProgress : function(file, loaded, total){  // 提供给外部获取单个文件的上传进度，供外部实现上传进度效果
			
		},
		onSuccess : function(file, responseInfo){    // 提供给外部获取单个文件上传成功，供外部实现成功效果
			
		},
		onFailure : function(file, responseInfo){    // 提供给外部获取单个文件上传失败，供外部实现失败效果
		
		},
		onComplete : function(responseInfo){         // 提供给外部获取全部文件上传完成，供外部实现完成效果
			
		},
		
		/* 内部实现功能方法 */
		// 获得选中的文件
		//文件拖放
		funDragHover: function(e) {
			e.stopPropagation();
			e.preventDefault();
			this[e.type === "dragover"? "onDragOver": "onDragLeave"].call(e.target);
			return this;
		},
		// 获取文件
		funGetFiles : function(e){  
			var self = this;
			// 取消鼠标经过样式
			this.funDragHover(e);
			// 从事件中获取选中的所有文件
			var files = e.target.files || e.dataTransfer.files;
			self.lastUploadFile = this.uploadFile;
			this.uploadFile = this.uploadFile.concat(this.filterFile(files));
			var tmpFiles = [];
			
			// 因为jquery的inArray方法无法对object数组进行判断是否存在于，所以只能提取名称进行判断
			var lArr = [];  // 之前文件的名称数组
			var uArr = [];  // 现在文件的名称数组
			$.each(self.lastUploadFile, function(k, v){
				lArr.push(v.name);
			});
			$.each(self.uploadFile, function(k, v){
				uArr.push(v.name);
			});
			
			$.each(uArr, function(k, v){
				// 获得当前选择的每一个文件   判断当前这一个文件是否存在于之前的文件当中
				if($.inArray(v, lArr) < 0){  // 不存在
					tmpFiles.push(self.uploadFile[k]);
				}
			});
			
			// 如果tmpFiles进行过过滤上一次选择的文件的操作，需要把过滤后的文件赋值
			//if(tmpFiles.length!=0){
				this.uploadFile = tmpFiles;
			//}
			
			// 调用对文件处理的方法
			this.funDealtFiles();
			
			return true;
		},
		// 处理过滤后的文件，给每个文件设置下标
		funDealtFiles : function(){
			var self = this;
			// 目前是遍历所有的文件，给每个文件增加唯一索引值
			$.each(this.uploadFile, function(k, v){
				// 因为涉及到继续添加，所以下一次添加需要在总个数的基础上添加
				v.index = self.fileNum;
				// 添加一个之后自增
				self.fileNum++;
			});
			// 先把当前选中的文件保存备份
			var selectFile = this.uploadFile;  
			// 要把全部的文件都保存下来，因为删除所使用的下标是全局的变量
			this.perUploadFile = this.perUploadFile.concat(this.uploadFile);
			// 合并下上传的文件
			this.uploadFile = this.lastUploadFile.concat(this.uploadFile);
			
			// 执行选择回调
			this.onSelect(selectFile, this.uploadFile);
			console.info("继续选择");
			console.info(this.uploadFile);
			return this;
		},
		// 处理需要删除的文件  isCb代表是否回调onDelete方法  
		// 因为上传完成并不希望在页面上删除div，但是单独点击删除的时候需要删除div   所以用isCb做判断
		funDeleteFile : function(delFileIndex, isCb){
			var self = this;  // 在each中this指向没个v  所以先将this保留
			
			var tmpFile = [];  // 用来替换的文件数组
			// 合并下上传的文件
			var delFile = this.perUploadFile[delFileIndex];
			console.info(delFile);
			// 目前是遍历所有的文件，对比每个文件  删除
			$.each(this.uploadFile, function(k, v){
				if(delFile != v){
					// 如果不是删除的那个文件 就放到临时数组中
					tmpFile.push(v);
				}else{
					
				}
			});
			this.uploadFile = tmpFile;
			if(isCb){  // 执行回调
				// 回调删除方法，供外部进行删除效果的实现
				self.onDelete(delFile, this.uploadFile);
			}
			
			console.info("还剩这些文件没有上传:");
			console.info(this.uploadFile);
			return true;
		},
		// 上传多个文件
		funUploadFiles : function(){
			var self = this;  // 在each中this指向没个v  所以先将this保留
			// 遍历所有文件  ，在调用单个文件上传的方法
			$.each(this.uploadFile, function(k, v){
				self.funUploadFile(v);
			});
		},
		// 上传单个个文件
		funUploadFile : function(file){
			var self = this;  // 在each中this指向没个v  所以先将this保留
			
			var formdata = new FormData();
			formdata.append("fileList", file);	         		
			var xhr = new XMLHttpRequest();
			// 绑定上传事件
			// 进度
		    xhr.upload.addEventListener("progress",	 function(e){
		    	// 回调到外部
		    	self.onProgress(file, e.loaded, e.total);
		    }, false); 
		    // 完成
		    xhr.addEventListener("load", function(e){
	    		// 从文件中删除上传成功的文件  false是不执行onDelete回调方法
		    	self.funDeleteFile(file.index, false);
		    	// 回调到外部
		    	self.onSuccess(file, xhr.responseText);
		    	if(self.uploadFile.length==0){
		    		// 回调全部完成方法
		    		self.onComplete("全部完成");
		    	}
		    }, false);  
		    // 错误
		    xhr.addEventListener("error", function(e){
		    	// 回调到外部
		    	self.onFailure(file, xhr.responseText);
		    }, false);  
			
			xhr.open("POST",self.url, true);
			xhr.setRequestHeader("X_FILENAME", file.name);
			xhr.send(formdata);
		},
		// 返回需要上传的文件
		funReturnNeedFiles : function(){
			return this.uploadFile;
		},
		
		// 初始化
		init : function(fileInputNew){  // 初始化方法，在此给选择、上传按钮绑定事件
			var self = this;  // 克隆一个自身
			
			if (this.dragDrop) {
				this.dragDrop.addEventListener("dragover", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("dragleave", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("drop", function(e) { self.funGetFiles(e); }, false);
			}
			
			// 如果选择按钮存在
			if(self.fileInput){
				//dongbin 2016.1.8 修改 根据传入对象添加对应事件
				if($(fileInputNew).length>0){
					// 绑定change事件
					$(fileInputNew).get(0).addEventListener("change", function(e) {
						self.funGetFiles(e); 
					}, false);
				}else{
					// 绑定change事件
					this.fileInput.addEventListener("change", function(e) {
						self.funGetFiles(e); 
					}, false);
				}
					
			}
			
			// 如果上传按钮存在
			if(self.uploadInput){
				// 绑定click事件
				this.uploadInput.addEventListener("click", function(e) {
					self.funUploadFiles(e); 
				}, false);	
			}
		}
};