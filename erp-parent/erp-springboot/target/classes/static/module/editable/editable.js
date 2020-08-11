/* 
可编辑表格js
导入editable.js
给标签增加class为editable-*
*/

$(document).ready(function(){	
	fixedTableTitle: true,
	editable.init({
	    beforeNew: function(){
	    	return true;
	    },
	    afterNew: function(obj){
	    	//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
	    },
	    beforeAdd: function(obj){
	    	return true;
	    },
	    afterAdd: function(){
	    	//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
	    },
		add: function(json,obj){
			alert(json);
			editable.setID(obj,456);
		},
		save: function(json,obj){
			alert(json);
			//获取可编辑行的数量
			//$("table.editable-table").find("tbody").find("tr:has(.editable-input)").not("tr.editable-trModel").length
		},
		del: function(json,obj){
			alert(json);
			obj.remove();
		},
		editEnable: function(obj){
			if($(obj).find("td").eq(2).text()=="000"){
				return true;
			}else{
				return true;
			}
		},
		beforeUpdate: function(obj){
			return true;
		},
		afterUpdate: function(){
			//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
		},
		beforeSave: function(obj){
			return true;
		},
		afterSave: function(){
			//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
		},
		afterUpdateAll: function(){
			//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
		},
		afterSaveAll:function(){
			//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
		},
		beforeSaveAll:function(){
			//$('#attendLineTable').dataTable().fnAdjustColumnSizing(false);
		}
	});
});


var editable = {

init: function(options){
	//隐藏保存和取消按钮
	$("table.editable-table").find("tbody td .editable-save").hide();
	$("table.editable-table").find("tbody td .editable-exit").hide();
	//隐藏表格刷新按钮
	$(".editable-refresh").hide();
	//隐藏保存所有的按钮
	$(".editable-save-all").hide();
	//隐藏取消所有的按钮
	$(".editable-exit-all").hide();
	
	//获取可编辑表格的tbody
	var editable_tbody = $("table.editable-table").find("tbody");
	//获取可编辑行的模型
	var editable_trModel_original = $("table.editable-table").find("tbody tr.editable-trModel");		
	var editable_trModel = 	editable_trModel_original.clone();
	
	//将可编辑行的模型隐藏
	editable_trModel_original.hide();
	$(editable_trModel_original).find(".editable-input").hide();
	$(editable_trModel_original).find(".editable-select").hide();
	$(editable_trModel_original).find(".editable-multiselect").hide();
	$(editable_trModel_original).find(".editable-date").hide();
	
	//编辑所有时的状态
	var update_all_flag = "N";
	//新增数据状态
	var add_flag = "N";
	//修改数据状态
	var update_flag = "N";
	
	//修改所有按钮click事件
	$(".editable-update-all").on("click",function(){
		
		//当编辑所有行的时候不允许添加新行
		if(add_flag=="Y"){
			//alert("新增数据时无法编辑整个表格！");
			layer.alert("新增数据时无法编辑整个表格！",{icon:0});
			return;
		}
		
		//当编辑单行时无法点击修改所有
		if(update_flag=="Y"){
			//alert("请先保存数据后再进行其他操作！");
			layer.alert("请先保存数据后再进行其他操作！",{icon:0});
			return;
		}
		
		update_all_flag = "Y";
		editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-update:visible").click();
		
		$(".editable-save-all").show();
		$(".editable-exit-all").show();
		$(".editable-update-all").hide();
		
		//当点击修改所有时，不允许单行编辑,取消或删除
		editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-save").hide();
		editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-exit").hide();
		//如果是固定表格标题
		if(options.fixedTableTitle==true){
			$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-save").hide();
			$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-exit").hide();
		}
		
		//执行修改所有按钮后的方法
		if(options.afterUpdateAll!=undefined){
			
			//增加向上和向下快捷键功能
			$(editable_tbody).find("tr:visible").each(function(i, n){
				$(this).find("td").each(function(ii, nn){
					$(this).find(":text").on("keydown",function(event){
						//up 38 down 40
						if(event.keyCode == 38){
							$(editable_tbody).find("tr:visible").eq(i-1).find("td").eq(ii).find(":text").focus();
						}else if (event.keyCode == 40){
							$(editable_tbody).find("tr:visible").eq(i+1).find("td").eq(ii).find(":text").focus();
						} 
					});
				});
			});
			
			options.afterUpdateAll();
		}
		
	});
	
	//修改所有按钮click事件
	$(".editable-save-all").on("click",function(){
		
		//执行修改所有按钮后的方法
		if(options.beforeSaveAll!=undefined){
			options.beforeSaveAll();
		}
		
		editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-save").click();
		update_all_flag = "N";
		
		$(".editable-save-all").hide();
		$(".editable-exit-all").hide();
		$(".editable-update-all").show();
		
		//执行修改所有按钮后的方法
		if(options.afterSaveAll!=undefined){
			options.afterSaveAll();
		}
		
	});
	
	//取消所有按钮click事件
	$(".editable-exit-all").on("click",function(){
		editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-exit").click();
		update_all_flag = "N";
		
		$(".editable-save-all").hide();
		$(".editable-exit-all").hide();
		$(".editable-update-all").show();
	});
	
	//编辑后的克隆行
	var new_trModel_clone;
	var new_trModel_clone_flag = "N";
	
	//快速克隆最新的一行快捷键（ctrl+x）
	$(document).keydown(function(e){
		if(e.ctrlKey && e.keyCode == 88) {
			copyLineCtrlX();
		}
	});
	
	//克隆行快捷键方法（ctrl+x）
	function copyLineCtrlX(){
		if(new_trModel_clone!=undefined){
			new_trModel_clone_flag = "Y";
			$(".editable-add").click();
		}
	}
	
	//添加快捷键方法（ctrl+s）
	function addCtrlS(obj){
		$(obj).find(".editable-add").click();
		//去掉document keydown事件
		$(document).off("keydown");
		//重新添加 克隆行keydown事件
		//bbc 此部分处理方法有些问题，上面方法造成了取消掉了所有keydown事件，所以还要手工重新加入需要的事件
		$(document).keydown(function(e){
			if(e.ctrlKey && e.keyCode == 88) {
				copyLineCtrlX();
			}
		});
	}
		
	//可编辑表格添加行
	$(".editable-add").bind("click",function(){
		//当编辑所有行的时候不允许添加新行
		if(update_all_flag=="Y"){
			//alert("编辑整个表格时无法新增数据！");
			layer.alert("编辑整个表格时无法新增数据！",{icon:0});
			return;
		}
		//必须保存数据后再新增新的数据
		if(add_flag=="Y"){
			//alert("请先保存数据后再新增新的数据！");
			layer.alert("请先保存数据后再新增新的数据！",{icon:0});
			return;
		}
		add_flag = "Y";
		
		var trModel_clone;
		if(new_trModel_clone_flag=="Y"){
			trModel_clone = new_trModel_clone.clone();
			//循环一行中所有可编辑的列
			$(trModel_clone).find("td").each(function(n){
				//获取可编辑行的模板对应位置td内的对象
				var editable_tdModel = $(editable_trModel).find("td").eq(n);
				//替换可编辑表格td对应模板的内容，并设置组件的默认
				editable.addTDModule(editable_tdModel,$(this));
				//改变修改和保存按钮状态
				$(this).parent().find(".editable-add").show();
				$(this).parent().find(".editable-cancel").show();
				//如果是固定表格标题
				if(options.fixedTableTitle==true){
					$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-add").show();
					$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-cancel").show();
				}
			});
			new_trModel_clone_flag = "N";
		}else{
			trModel_clone = editable_trModel.first().clone();
			
			//删除模板克隆行的class
			$(trModel_clone).removeClass("editable-trModel");
			
			//初始化select组件
			$(trModel_clone).find(".editable-select").select2({
		        minimumResultsForSearch: Infinity
		    });
			//初始化select(多选)组件
			$(trModel_clone).find(".editable-multiselect").multiselect({
		        onChange: function() {
		            $.uniform.update();
		        }
		    });
		}
		
		//增加一行可编辑的行
		editable_tbody.append(trModel_clone);
		
		//改变修改和删除按钮状态
		$(trModel_clone).find(".editable-update").hide();
		$(trModel_clone).find(".editable-delete").hide();
		
		//撤销按钮添加click事件
		$(trModel_clone).find(".editable-cancel").bind("click",function(){
			//移除当前行
			$(trModel_clone).remove();
			add_flag = "N";
		});
		
		//执行新增行按钮前的方法
		if(options.beforeNew!=undefined){
			var beforeNewFlag = options.beforeNew($(trModel_clone));
			//如果返回false，则不继续执行
			if(!beforeNewFlag){
				return;
			}
		}
		
		//给保存按钮添加快捷键（ctrl+s）
		$(document).on("keydown", function(e){
			if(e.ctrlKey && e.keyCode == 83){
				e.preventDefault();
				e.keyCode = false;
				//模拟点击保存按钮
				addCtrlS($(editable_tbody).find("tr:visible").last());
			}
		});
		
		//保存按钮添加click事件
		$(trModel_clone).find(".editable-add").bind("click",function(){
			
			
			//执行添加按钮前的方法
			if(options.beforeAdd!=undefined){
				var beforeAddFlag = options.beforeAdd($(trModel_clone));
				//如果返回false，则不继续执行
				if(!beforeAddFlag){
					return;
				}
			}
			
			//保存事件返回的json
			var save_json = "";
		
			$(trModel_clone).find("td").each(function(n){
				//替换可编辑表格的td变为文本值
				var td_json = editable.recoverTDContent($("<div></div>"),$(this));
				//拼接保存按钮最终返回的json
				if(td_json!=""){
					if(save_json==""){
						save_json = td_json;
					}else{
						save_json = save_json + "," + td_json;
					}
				}
				
				//改变撤销和保存按钮状态
				$(this).parent().find(".editable-add").hide();
				$(this).parent().find(".editable-cancel").hide();
				$(trModel_clone).find(".editable-update").show();
				$(trModel_clone).find(".editable-delete").show();
				
			});
			
			//初始化所有数据行
			init_all_tr();
			
			//调用save方法保存行数据
			if(options.add!=undefined){
				options.add("{"+save_json+"}", trModel_clone);
			}
			//显示表格刷新按钮
			$(".editable-refresh").show();
			add_flag = "N";
			
			//执行添加按钮后的方法
			if(options.afterAdd!=undefined){
				options.afterAdd();
			}
			
			//重新克隆当前行
			new_trModel_clone = $(trModel_clone).clone();
		});
		
		//执行新增行按钮后的方法
		if(options.afterNew!=undefined){
			options.afterNew(trModel_clone);
		}
		
		//input框获得焦点
		$("input:visible").eq(0).focus();
		
	});
	

	
	//初始化所有数据行
	init_all_tr();
	
	
	//初始化所有数据行
	function init_all_tr(){
		//改变可编辑列为对应的样式
		//获取所有可编辑的行
		var editable_tr = editable_tbody.find("tr").not("tr.editable-trModel");
		//克隆可编辑表格的所有行
		var editable_tr_clone = $(editable_tr).clone();
		//更新按钮数量
		var updateNum = 0;
		//保存按钮数量
		var saveNum = 0;
		//循环所有可编辑的行
		$(editable_tr).each(function(i){
		//获取当前tr
		var this_tr = $(this);	
		
		//如果当前行为可编辑的行
		if(options.editEnable!=undefined){
			if(!options.editEnable(this_tr)){
				$(this).find(".editable-update").remove();
				$(this).find(".editable-delete").remove();
				$(this).find(".editable-save").remove();
			}
		}
		
		//修改按钮必须存在
		if($(this).find(".editable-update").length>0){
			//给可编辑行中的update按钮添加click事件
			var objEvt = $._data($(this).find(".editable-update")[0],"events");
			//判断当前按钮是否绑定事件，如果未绑定则绑定
			if(!(objEvt && objEvt["click"])){

			$(this).find(".editable-update").bind("click",function(){
				//执行更新按钮前的方法
				if(options.beforeUpdate!=undefined){
					var beforeUpdateFlag = options.beforeUpdate($(this_tr));
					//如果返回false，则不继续执行
					if(!beforeUpdateFlag){
						return;
					}
				}
				
				//循环累加更新数量
				if(update_all_flag=="Y"){
					updateNum++;
				}
				//标识单行修改状态
				update_flag = "Y";
				//循环一行中所有可编辑的列
				$(this_tr).find("td").each(function(n){
					//获取可编辑行的模板对应位置td内的对象
					var editable_tdModel = $(editable_trModel).find("td").eq(n);
					//替换可编辑表格td对应模板的内容，并设置组件的默认
					editable.addTDModule(editable_tdModel,$(this));
					//改变修改和保存按钮状态
					$(this).parent().find(".editable-update").hide();
					$(this).parent().find(".editable-save").show();
					$(this).parent().find(".editable-exit").show();
					//如果是固定表格标题
					if(options.fixedTableTitle==true){
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-update").hide();
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-save").show();
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-exit").show();
					}
				});
				
				//执行更新按钮后的方法
				if(update_all_flag=="Y"){
					if(updateNum==editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-update").length){
						updateNum = 0;
						if(options.afterUpdate!=undefined){
							options.afterUpdate();
						}
					}
				}else{
					if(options.afterUpdate!=undefined){
						options.afterUpdate();
					}
				}
			});
			}
		}
		
		
		//保存按钮必须存在
		if($(this).find(".editable-save").length>0){
			//给可编辑行中的save按钮添加click事件
			var objEvt = $._data($(this).find(".editable-save")[0],"events") 
			//判断当前按钮是否绑定事件，如果未绑定则绑定
			if(!(objEvt && objEvt["click"])){
			
			$(this).find(".editable-save").bind("click",function(){
				//执行更新按钮前的方法
				if(options.beforeSave!=undefined){
					var beforeSaveFlag = options.beforeSave($(this_tr));
					//如果返回false，则不继续执行
					if(!beforeSaveFlag){
						return;
					}
				}
				
				//循环累加更新数量
				if(update_all_flag=="Y"){
					saveNum++;
				}
				//取消标识单行修改状态
				update_flag = "N";
				//获取主键的json
				var dataid = $(this).attr("dataid");
				var dataid_json = "\"id\":\"" + dataid + "\"";
				//保存事件返回的json
				var save_json = "";
			
				$(this_tr).find("td").each(function(n){
					//将克隆的原td内容修改后放入当前td里
					var this_td = $(editable_tr_clone).eq(i).find("td").eq(n);
					//替换可编辑表格的td变为文本值
					var td_json = editable.recoverTDContent(this_td,$(this));
					//拼接保存按钮最终返回的json
					if(td_json!=""){
						save_json = save_json + "," + td_json;
					}
					
					//改变修改和保存按钮状态
					$(this).parent().find(".editable-save").hide();
					$(this).parent().find(".editable-exit").hide();
					$(this).parent().find(".editable-update").show();
					//如果是固定表格标题
					if(options.fixedTableTitle==true){
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-save").hide();
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-exit").hide();
						$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-update").show();
					}
					
				});
				//调用save方法保存行数据
				if(options.save!=undefined){
					options.save("{"+dataid_json+save_json+"}", this_tr);
				}
				//执行保存按钮后的方法
				if(update_all_flag=="Y"){
					if(saveNum==editable_tbody.find("tr").not("tr.editable-trModel").find(".editable-save").length){
						saveNum = 0;
						if(options.afterSave!=undefined){
							options.afterSave();
						}
					}
				}else{
					if(options.afterSave!=undefined){
						options.afterSave();
					}
				}
			});
			}
		}
		
		
		//删除按钮必须存在
		if($(this).find(".editable-delete").length>0){
			//给可编辑行中的delete按钮添加click事件
			var objEvt = $._data($(this).find(".editable-delete")[0],"events") 
			//判断当前按钮是否绑定事件，如果未绑定则绑定
			if(!(objEvt && objEvt["click"])){
			$(this).find(".editable-delete").bind("click",function(){
				//取消标识单行修改状态
				update_flag = "N";
				//获取主键的json
				var dataid = $(this).attr("dataid");
				var dataid_json = "\"id\":\"" + dataid + "\"";
				
				//调用save方法保存行数据
				if(options.del!=undefined){
					options.del("{"+dataid_json+"}",this_tr);
				}
			});
			}
		}
		
		
		//取消按钮必须存在
		if($(this).find(".editable-exit").length){
			//给可编辑行中的delete按钮添加click事件
			var objEvt = $._data($(this).find(".editable-exit")[0],"events") 
			//判断当前按钮是否绑定事件，如果未绑定则绑定
			if(!(objEvt && objEvt["click"])){
				$(this).find(".editable-exit").bind("click",function(){
					//取消标识单行修改状态
					update_flag = "N";
				
					$(this_tr).find("td").each(function(n){
						//将克隆的原td内容修改后放入当前td里
						var this_td = $(editable_tr_clone).eq(i).find("td").eq(n);
						//替换可编辑表格的td变为文本值
						var td_json = editable.recoverTDContent(this_td,$(this));
						
						//改变修改和保存按钮状态
						$(this).parent().find(".editable-save").hide();
						$(this).parent().find(".editable-exit").hide();
						$(this).parent().find(".editable-update").show();
						//如果是固定表格标题
						if(options.fixedTableTitle==true){
							$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-save").hide();
							$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-exit").hide();
							$("div.DTFC_RightBodyWrapper").find("table.DTFC_Cloned").find(".editable-update").show();
						}
					});
					
				});
			}
		}
		
		
		});	
	}
	
},


//替换可编辑表格td对应模板的内容，并设置组件的默认
addTDModule: function(moduleObj,tdObj){
	//原td值
	var value = $(tdObj).text();
	//判断如果是input
	if($(moduleObj).html().indexOf("editable-input")!=-1){
		//将可编辑行的模型对应位置的td内容替换现有td的内容
		$(tdObj).html($(moduleObj).html());
		
		//判断如果是隐藏域将元td的文本显示在对应的替换后的td中
		if($(tdObj).find(".editable-input:visible").length==0){
			$(tdObj).html(value+$(tdObj).html());
		}
		
		//将原td的值放入input框中
		$(tdObj).find(".editable-input").val(value);
		$(tdObj).find(".editable-input").show();
	}
	//判断如果是select（单选）
	else if($(moduleObj).html().indexOf("editable-select")!=-1){
		//克隆模板对象
		var moduleObjClone = $(moduleObj).clone();
		//将克隆对象select组件生成的span标签删除
//		$(moduleObjClone).contents().each(function(){
//			if($(this).hasClass("select2")){
//				$(this).remove();
//			}
//		});
		//将可编辑行的模型对应位置的td内容替换现有td的内容
		$(tdObj).html($(moduleObjClone).html());
		//将select的value转换为key
		var key = "";
		$(moduleObj).find(".editable-select").find("option").each(function(){
			if($(this).text()==value){
				key = $(this).val();
				return false;
			}
		});
		//将原td的值放入select框中
		$(tdObj).find(".editable-select").val(key);
		$(tdObj).find(".editable-select").show();
		
		//初始化select组件
		$(tdObj).find(".editable-select").select2({
	        minimumResultsForSearch: Infinity
	    });
	}
	//判断如果是select（多选）
	else if($(moduleObj).html().indexOf("editable-multiselect")!=-1){
		//克隆模板对象
		var moduleObjClone = $(moduleObj).clone();
		//将克隆对象select组件生成的span标签删除
//		$(moduleObjClone).contents().each(function(){
//			if($(this).hasClass("multiselect")){
//				$(this).remove();
//			}
//		});
		//将可编辑行的模型对应位置的td内容替换现有td的内容
		$(tdObj).html($(moduleObjClone).html());
		//将原td的值放入select多选框中
		var values = value.split(",");
		//将select的value转换为key
		var keys = values;
		for(var a=0;a<values.length;a++){
			$(moduleObj).find(".editable-multiselect").find("option").each(function(){
				if($(this).text()==values[a]){
					keys[a] = $(this).val();
					return false;
				}
			});
		}
		$(tdObj).find(".editable-multiselect").val(keys);
		$(tdObj).find(".editable-multiselect").show();
		
		//初始化select(多选)组件
		$(tdObj).find(".editable-multiselect").multiselect({
	        onChange: function() {
	            $.uniform.update();
	        }
	    });
	}
	//判断如果是date
	else if($(moduleObj).html().indexOf("editable-date")!=-1){
		//将可编辑行的模型对应位置的td内容替换现有td的内容
		$(tdObj).html($(moduleObj).html());
		//将原td的值放入input框中
		$(tdObj).find(".editable-date").val(value);
		$(tdObj).find(".editable-date").show();
	}
},

//替换可编辑表格的td变为文本值
recoverTDContent: function(tdObj,moduleObj){
	//判断如果是input
	if($(moduleObj).html().indexOf("editable-input")!=-1){
		//获取input的输入值
		var value = $(moduleObj).find(".editable-input").val();
		//获取input的name
		var name = $(moduleObj).find(".editable-input").attr("name");
		$(moduleObj).html($(tdObj).html().replace($(tdObj).text(),value));
		return "\""+name+"\":\""+value+"\"";
	}
	//判断如果是select（单选）
	else if($(moduleObj).html().indexOf("editable-select")!=-1){
		//获取select的输入值
		var key = $(moduleObj).find(".editable-select").val();
		//获取select的name
		var name = $(moduleObj).find(".editable-select").attr("name");
		var value = "";
		//将select的key转换为value
		$(moduleObj).find(".editable-select").find("option").each(function(){
			if($(this).val()==key){
				value = $(this).text();
				return false;
			}
		});
		$(moduleObj).html($(tdObj).html().replace($(tdObj).text(),value));
		return "\""+name+"\":\""+key+"\"";
	}
	//判断如果是select（多选）
	else if($(moduleObj).html().indexOf("editable-multiselect")!=-1){
		//获取select的输入值
		var key = $(moduleObj).find(".editable-multiselect").val();
		//如果select未选择，则滞空
		if(key==null){key = "";}
		var keyString = key.toString();
		//获取select的name
		var name = $(moduleObj).find(".editable-multiselect").attr("name");
		//将select的key转换为value
		var keys = key;
		var values = keys;
		for(var a=0;a<keys.length;a++){
			$(moduleObj).find(".editable-multiselect").find("option").each(function(){
				if($(this).val()==keys[a]){
					values[a] = $(this).text();
					return false;
				}
			});
		}
		$(moduleObj).html($(tdObj).html().replace($(tdObj).text(),values));
		return "\""+name+"\":\""+keyString+"\"";
	}
	//判断如果是date
	else if($(moduleObj).html().indexOf("editable-date")!=-1){
		//获取input的输入值
		var value = $(moduleObj).find(".editable-date").val();
		var name = $(moduleObj).find(".editable-date").attr("name");
		$(moduleObj).html($(tdObj).html().replace($(tdObj).text(),value));
		return "\""+name+"\":\""+value+"\"";
	}else{
		return "";
	}
},

setID: function(obj, id){
	//更新保存和删除按钮的dataid属性
	$(obj).find(".editable-save").attr("dataid", id);
	$(obj).find(".editable-delete").attr("dataid", id);
}


};
