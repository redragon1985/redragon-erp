/**
 * 结构树弹出层组件
 * author：jianghong
 * time:2015-5-09
 */
var treeType;//类型
var strParam;//自定义参数
var treeName="";//树路径
var resultJson="";//返回json
var resultFunction="";//返回方法
var singleCheckbox=false;//默认多选，如果为true为单选
var treeCheckBox=false;//树默认不加载复选框。如果为true则有复选框。
var wageNY="";//核算模块中输入年月时间，如“2017-02”。
var isAuth=false;
var divId="";
var ismodel=1;
var structureCodes="";//已选择的节点编码
var layerTree={
	
	//打开弹出层
	beforeTreeList:function(options){
		var url="";
		singleCheckbox=options.singleCheckbox;
		treeCheckBox=options.treeCheckBox;
		strParam=options.strParam;
		treeType=options.treeType;
		resultFunction=options.resultFunction;
		wageNY=options.wageNY;
		isAuth=options.isAuth;
		structureCodes=options.structureCodes;
		if(structureCodes==undefined){
			structureCodes="";
		}
		if(options.wageNY==undefined){
			wageNY="";
		}
		if(treeType=="structure"||treeType=="structure_position"){//组织架构弹层
			url="orgStructure/beforeStructureTree.action";
		}else if(treeType=="structure_staff"){//组织架构关联职员弹层
			url="orgStructure/beforeStructureTreeStaff.action";
		}else if(treeType=="cost_center"){//成本中心弹层
			url="costCenter/beforeCostCenterTree.action";
		}else if(treeType=="my_cost_center"){//成本中心弹层
			url="costCenter/beforeCostCenterTree.action";
		}else if(treeType=="first_cost_center"){//成本中心弹层
			url="costCenter/beforeCostCenterTree.action";
		}else if(treeType=="staff"){
			url="staff/beforeStaffLayer.action";
		}
		$.ajax({
			type: "post",
			url: url,
			data:{
				
			},
			cache: false, 
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			dataType:"html",
			success: function(data) {
				if(treeType=="structure_staff"){
					layer.open({
						title : '职员结构树',
						type : 1,
						btn : ['清空','确定'],
						closeBtn : 2,
						area : [ '1200px', '650px' ],
						content : data,
						yes : function(index) {
							var reJson = "[{\"staffNumber\":\"\",\"staName\":\"\"}]";
							options.staffClick(reJson);
							layer.close(index);
						},
						btn2 : function(index) {
							//返回选择的数据
							if(checkStaffOrs()!=""){
								if(singleCheckbox){
									if(eval(checkStaffOrs()).length>1){
										alertWarn("只能选择一条数据");
										return false;
									}else{
										options.staffClick(checkStaffOrs());
										layer.close(index);
									}
								}else{
									options.staffClick(checkStaffOrs());
									layer.close(index);
								}
							}else{
								alertWarn("请选择数据");
								return false;
							}
						}
						
					});
				}else if(treeType=="staff"){
					layer.open({
						title : '职员信息',
						type : 1,
						btn : ['清空','确定'],
						closeBtn : 2,
						area : [ '1000px', '600px' ],
						content : data,
						yes : function(index) {
							var reJson = "[{\"staffNumber\":\"\",\"staName\":\"\"}]";
							options.staffClick(reJson);
							layer.close(index);
						},
						btn2 : function(index) {
							//返回选择的数据
							if(checkStaffLayer()!=""){
								if(singleCheckbox){
									if(eval(checkStaffLayer()).length>1){
										alertWarn("只能选择一条数据");
										return false;
									}else{
										options.staffClick(checkStaffLayer());
										layer.close(index);
									}
								}else{
									options.staffClick(checkStaffLayer());
									layer.close(index);
								}
							}else{
								alertWarn("请选择数据");
								return false;
							}
						}
						
					});
				}else{
					layer.open({
						title : '结构树',
						type : 1,
						btn : ['清空','确定'],
						closeBtn : 2,
						area : [ '500px', '600px' ],
						content : data,
						btn1 : function(index) {
							var reJson="[{\"code\":\"\",\"name\":\"\"}]";
							eval(resultFunction+'('+reJson+')');
							layer.close(index);
						},
						btn2 : function(index) {
							if(treeType=="structure_staff"){
							}else{
								if(treeCheckBox){
									//返回相应数据
									var seNode = $("#"+divId).fancytree("getTree").getSelectedNodes();
									resultJson="[";
									for (var i = 0; i < seNode.length; i++) {
										initTreeName(divId,seNode[i].key);
										treeName=replaceStr(treeName,"\\[未关联\\]", "");
										if (i == seNode.length - 1) {
											resultJson+="{\"code\":\""+seNode[i].key+"\",\"name\":\""+treeName+"\",\"segmentvalue\":\""+seNode[i].data.segmentvalue+"\"";
											if(seNode[i].data.poCode!="null"){
												resultJson+=",\"position\":\""+seNode[i].key+"\"";
											}
											resultJson+="}";
										} else {
											resultJson+="{\"code\":\""+seNode[i].key+"\",\"name\":\""+treeName+"\",\"segmentvalue\":\""+seNode[i].data.segmentvalue+"\"";
											if(seNode[i].data.poCode!="null"){
												resultJson+=",\"position\":\""+seNode[i].key+"\"";
											}
											resultJson+="},";
										}
									}
									resultJson+="]";
								}
								layer.close(layer.index);
								eval(resultFunction+'('+resultJson+',"'+strParam+'")');
							}
						}
					});
				}
			}
		});
	},
	//加载树数据
	loadTree:function(options){
		divId=options.divId;
		var url;
		var ischeckbox=false;
		if(treeType=="structure_staff"){
			ischeckbox=true;
		}else{
			if(treeCheckBox){
				ischeckbox=true;
				ismodel=3;
			}
		}
		if(treeType=="structure_position"||treeType=="structure_staff"){
			url="orgStructure/getOrsTreeJson.action?auth="+isAuth+"&orgStructureCo.parentStructureCode=";
		}else if(treeType=="structure"){
			url="orgStructure/getOrsTreeJson.action?orgStructureCo.poStatus='Y'&auth="+isAuth+"&orgStructureCo.parentStructureCode=";
		}else if(treeType=="cost_center"){
			url="costCenter/getCostCenterTreeJson.action?costCenterCO.parentCostCenterCode=";
		}else if(treeType=="my_cost_center"){
			url="costCenter/getCostCenterTreeJson.action?costCenterCO.myCostCenterFlag=Y&costCenterCO.parentCostCenterCode=";
		}else if(treeType=="first_cost_center"){
			url="costCenter/getCostCenterTreeJson.action?costCenterCO.firstCostCenterFlag=Y&costCenterCO.parentCostCenterCode=";
		}
		$("#"+divId).fancytree({
			selectMode : ismodel,
			checkbox:ischeckbox,
			quicksearch : true,
			source : {
				url : url+0+"&structureCodes="+structureCodes
			},
			lazyLoad : function(event, data) {
				data.result = {
					url : url+ data.node.key
				}
			},
			click: function(event, data) {
				treeName="";
				if(treeType=="structure_staff"){
					//data.node.setSelected();
				}else{
					initTreeName(options.divId,data.node.key);
					treeName=replaceStr(treeName,"\\[未关联\\]", "");
					//返回相应数据111
					resultJson="[";
					var structureNodeProperties3="";
					if(data.node.data.structureNodeProperties3!="\"null\""){
						structureNodeProperties3=data.node.data.structureNodeProperties3;
					}
					resultJson+="{\"code\":\""+data.node.key+"\",\"name\":\""+treeName+"\",\"segmentvalue\":\""+data.node.data.segmentvalue+"\",\"checkSubject\":\""+structureNodeProperties3+"\"";
					if(data.node.data.poCode!="null"){
						resultJson+=",\"position\":\""+data.node.key+"\"";
					}
					resultJson+="}]";
					//layer.close(layer.index);
					//eval(resultFunction+'('+resultJson+',"'+strParam+'")');
				}
			},
			dblclick: function(event, data) {
				/*
				 * 不要再改了dongbin
				 */
				data.node.toggleExpanded();
            },
            select: function(event, data) {
            	if(treeType=="structure_staff"){
            		var treeNodeAtr=$("#"+options.divId).fancytree("getTree").getSelectedNodes();
                	var treeCodes="";
        			for(var i=0;i<treeNodeAtr.length;i++){
        				treeCodes+=treeNodeAtr[i].key+",";
        			} 
        			$("#structureCode").val(treeCodes);
    				//查询职员信息
    				getStaffListByOrgStructure(treeCodes);
				}
            }
		});
	}
}

/**
 * 拼接树路径名称
 */
function initTreeName(treeDiv,node){
	var rootNode=$("#"+treeDiv).fancytree("getTree").getRootNode();//系统默认顶级节点
	var dataNode=$("#"+treeDiv).fancytree("getTree").getNodeByKey(node);//当前节点
	if(treeName!=""){
		treeName=dataNode.title+"-"+treeName;
	}else{
		treeName=dataNode.title;
	}
	if(rootNode.key!=dataNode.getParent().key){//如果不是系统默认顶级节点
		initTreeName(treeDiv,dataNode.getParent().key);
	}
}
/**
 * 全部替换
 * @param atr：原数据
 * @param str：被替换值
 * @param replaceStr：替换值
 * @returns
 */
function replaceStr(atr,str,replaceStr){
	var restr = new RegExp(str,"g");
	var newstr = atr.replace(restr, replaceStr);
	return newstr;
}