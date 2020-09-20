/**
 * 二次封装JS组件
 */
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
		
		
		
		//=======================================float计算================================
		/** 
		* 加法运算，避免数据相加小数点后产生多位数和计算精度损失。 
		* 
		* @param num1加数1 | num2加数2 
		*/ 
		numberAdd: function(num1, num2) { 
			var baseNum, baseNum1, baseNum2; 
			try { 
				baseNum1 = num1.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum1 = 0; 
			} 
			try { 
				baseNum2 = num2.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum2 = 0; 
			}
			
			baseNum = Math.pow(10, Math.max(baseNum1, baseNum2)); 
			return (num1 * baseNum + num2 * baseNum) / baseNum; 
		},
		
		/** 
		* 加法运算，避免数据相减小数点后产生多位数和计算精度损失。 
		* 
		* @param num1被减数 | num2减数 
		*/ 
		numberSub: function(num1, num2) { 
			var baseNum, baseNum1, baseNum2; 
			var precision;// 精度 
			try { 
				baseNum1 = num1.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum1 = 0; 
			} 
			try { 
				baseNum2 = num2.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum2 = 0; 
			}
			
			baseNum = Math.pow(10, Math.max(baseNum1, baseNum2)); 
			precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2; 
			return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision); 
		},
		
		/** 
		* 乘法运算，避免数据相乘小数点后产生多位数和计算精度损失。 
		* 
		* @param num1被乘数 | num2乘数 
		*/ 
		numberMulti: function(num1, num2) {
			var baseNum = 0; 
			try { 
				baseNum += num1.toString().split(".")[1].length; 
			} catch (e) { 
			} 
			try { 
				baseNum += num2.toString().split(".")[1].length; 
			} catch (e) { 
			}
			
			return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum); 
		},
		
		/** 
		* 除法运算，避免数据相除小数点后产生多位数和计算精度损失。 
		* 
		* @param num1被除数 | num2除数 
		*/ 
		numberDiv: function(num1, num2) {
			var baseNum1 = 0, baseNum2 = 0; 
			var baseNum3, baseNum4; 
			try { 
				baseNum1 = num1.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum1 = 0; 
			} 
			try { 
				baseNum2 = num2.toString().split(".")[1].length; 
			} catch (e) { 
				baseNum2 = 0; 
			} 
			
			baseNum3 = Math.Number(num1.toString().replace(".", "")); 
			baseNum4 = Math.Number(num2.toString().replace(".", "")); 
			return (baseNum3 / baseNum4) * pow(10, baseNum2 - baseNum1); 
		},

};
