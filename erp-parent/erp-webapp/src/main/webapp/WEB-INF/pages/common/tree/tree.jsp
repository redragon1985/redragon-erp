<%--

    Copyright 2020-2021 redragon.dongbin
 
    This file is part of redragon-erp/赤龙ERP.

    redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
	
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

	
	
		<div class="ibox ">
			<div class="ibox-title">
				<h4>${requestScope.title}</h4>
				
				<c:if test="${requestScope.treeType!='read'}">
				<div class="ibox-tools">
					<button id="addRootButton" class="btn btn-default btn-sm" type="button">
						<i class="fa fa-plus-square-o"></i>&nbsp;&nbsp;<span class="bold">新增根节点</span>
					</button>
					<button id="addButton" class="btn btn-success btn-sm" type="button">
						<i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增节点</span>
					</button>
					<button id="updateButton" class="btn btn-warning btn-sm"
						type="button">
						<i class="fa fa-pencil"></i>&nbsp;&nbsp;<span class="bold">修改节点</span>
					</button>
					<button id="deleteButton" class="btn btn-danger btn-sm"
						type="button">
						<i class="fa fa-trash"></i>&nbsp;&nbsp;删除节点
					</button>
					<button id="refreshButton" class="btn btn-default btn-circle btn-sm" type="button">
						<i class="fa fa-refresh" title="刷新"></i>
                	</button>
				</div>
				</c:if>
				
			</div>
			<div class="ibox-content border-bottom">
				<div class="input-group col-sm-12">
					<div class="input-group-addon bg-info">
						<i class="fa fa-search"></i>
					</div>
					<input type="text" class="form-control" id="searchTree"
						placeholder="输入查询条件...">
				</div>
				<div class="hr-line-dashed"></div>

				<div id="jstree_div" style="overflow: auto;"></div>
				
			</div>
		</div>
		

<%-- 编辑节点的模态对话框div --%>
<div id="addModal"></div>

<!-- jsTree -->
<link href="css/plugins/jsTree/style.min.css" rel="stylesheet">
<script src="js/plugins/jsTree/jstree.min.js"></script>
	
<script>

//jsTree全局变量
var g_inst = null;
var g_clickedNode = null;
var g_obj = null;

$(document).ready(function() {
	//动态设施树的高度
	<c:if test="${requestScope.treeType=='read'}">
		$("#jstree_div").height($(window).height()/2);
	</c:if>
	
	var jsTreeSelectedNode = null;
		
	$('#jstree_div').jstree({
        'core' : {
            "themes" : {
                 "stripes" : false,//背景是否显示间纹
                 "dots": false,//是否显示树连接线
                 "icons": true,//是否显示节点的图标
                 "ellipsis": true,//节点名过长时是否显示省略号
            },
            
            'multiple' : false,  // 可否多选
            'dblclick_toggle': true,   //允许tree的双击展开
            'expand_selected_onload': false,
            'data' : {
                'url': '${requestScope.getTreeDataUrl}',
                'dataType': 'json',
                'data': function (node) { // 传给服务端点击的节点
                     return { 'nodeId': node.id };
                 },
                 success: function (data) {
                 	if(data.id==""){
                 		$("#jstree_div").html("");
                 	}else{
                 		$("#addRootButton").hide();
                 	}
                 }
            },
            "check_callback" : true,
            /*
            "check_callback" : function (operation, node, parent, position, more) {
                if(operation === "copy_node" || operation === "move_node") {
                    if(parent.id === "#") {
                        return false; // prevent moving a child above or below the root
                    }
                };
                return true;
            }*/

        },
        // 引入插件'checkbox','sort'
        <c:if test="${requestScope.treeType!='read'}">
        	'plugins': ["state",'types','themes','contextmenu','search','unique','changed'],
        </c:if>
        <c:if test="${requestScope.treeType=='read'}">
        	'plugins': ["state",'types','themes','search','unique','changed'],
        </c:if>
        
        "types" : {
            "default" : {
				'icon' : 'fa fa-folder'
            },
            "root" : {
                "icon" : 'fa fa-home fa-lg',
            },
            "node" : {
				'icon' : 'fa fa-folder'
            },
        },
        'checkbox': {  
        	// 去除checkbox插件的默认效果
            'tie_selection': false,
            'keep_selected_style': true,
            'whole_node': false,
            'three_state': false,
        },
        'contextmenu': {
        	// 右键菜单
            'items' : customMenu
        },        
    })
    //单击事件
    .on('click.jstree', function(event) {
    	clickJsTree(getTreeSelectNodeId());
    })
    //双击事件
    .on('dblclick.jstree',function(event){
    	dblclickJsTree(getTreeSelectNodeId());
    })
    //状态恢复事件
    .on('state_ready.jstree',function(event){
    	stateReadyJsTree(getTreeSelectNodeId());
    })
    .on("check_node.jstree", function(obj, selected, event){
    	//checkbox只能单选
    	var jstree = $(this).jstree(true);
    	$(jstree.get_checked()).each(function(i, n){
    		if(n!=selected.node.id){
    			jstree.uncheck_node(jstree.get_node(n));
    		}
    	});
    })
    .on("select_node.jstree", function(obj, selected, event){
    	jsTreeSelectedNode = selected.node;
    })
    ;
		
	//设置查询功能
	var to = false;
	$('#searchTree').keyup(function() {
		if (to) {
			clearTimeout(to);
		}
		to = setTimeout(function() {
			var v = $('#searchTree').val();
			$('#jstree_div').jstree(true).search(v);
		}, 250);
	});

	var items;
		
	function customMenu(node){
    	items = {
        
	    	'add': {
	            'label': '新增节点',
	            'icon': 'fa fa-plus',
	            'action': function (obj) {
	
	                var inst = jQuery.jstree.reference(obj.reference);
	                //获取节点对象
	                var clickedNode = inst.get_node(obj.reference);
	                var ty = inst.get_type(obj.reference);
	
	                //jstree新增节点效果全局变量设置
	                g_inst = inst;
	                g_clickedNode = clickedNode;
	                g_obj = obj;
	                
	                //弹出编辑框
	                getEditNodeModal("", clickedNode.id);
	            }
	        },
	        'edit': {
	            'label': '修改节点',
	            'icon': 'fa fa-pencil',
	            'action': function (obj) {
					var inst = jQuery.jstree.reference(obj.reference);
					//获取节点对象
					var clickedNode = inst.get_node(obj.reference);
	
					//jstree编辑节点效果全局变量设置
					g_inst = inst;
					g_obj = obj;
					
					//弹出编辑框
	                getEditNodeModal(clickedNode.id, inst.get_parent(clickedNode)=="#"?"":inst.get_parent(clickedNode));
	            }
	        },
	        'delete': {
	            'label': '删除节点',
	            'icon': 'fa fa-trash',
	            'action': function (obj) {
	            	var inst = jQuery.jstree.reference(obj.reference);
					//获取节点对象
					var clickedNode = inst.get_node(obj.reference);
					//展开当前节点
					inst.open_node(clickedNode);
					var childrenNodes = inst.get_children_dom(clickedNode);
					
					if(childrenNodes.length>0){
							redragonJS.alert("当前节点存在子节点无法删除", "warning");
	            	}else{
						redragonJS.confirm("确认删除节点？", function(){
							redragonJS.confirm2("删除节点将无法恢复，请先检查数据是否使用，确认删除？", function(){
								//jstree删除节点效果全局变量设置
			            		g_inst = inst;
								g_obj = obj;
	
								//异步删除节点
								$.ajax({
									type: "post",
									url: "${requestScope.deleteNodeUrl}"+clickedNode.id,
									async: false,
									dataType: "html",
									cache: false,
									success: function(data){
										//格式化json
										var json = $.parseJSON(data);
										
										if(json.result=="success"){
											//调用jstree删除节点效果
											deleteNodeForJsTree();
											redragonJS.close();
										}else if(json.result=="hint"){
											redragonJS.alert(json.hintMessage, "warning");
										}else{
											redragonJS.close();
										}
									},
									error: function(XMLHttpRequest, textStatus, errorThrown){
										redragonJS.alert(textStatus);
									}
								});
							});
		            		
		            	});
					}
	            }
	        }
	    }
 
    	return items;
	}
	
	
	
	//添加节点按钮
	$("#addButton").click(function(){
		if(jsTreeSelectedNode!=null){
			$("#jstree_div").jstree(true).show_contextmenu(jsTreeSelectedNode);
			$(".jstree-contextmenu a[rel='0']").click();
		}else{
			redragonJS.alert("请先选中一个节点后再进行操作","warning");
		}
	    
	});
		
	//修改节点按钮
	$("#updateButton").click(function(){
		if(jsTreeSelectedNode!=null){
			$("#jstree_div").jstree(true).show_contextmenu(jsTreeSelectedNode);
			$(".jstree-contextmenu a[rel='1']").click();
		}else{
			redragonJS.alert("请先选中一个节点后再进行操作","warning");
		}
	    
	});
		
	//删除节点按钮	
	$("#deleteButton").click(function(){
		if(jsTreeSelectedNode!=null){
			$("#jstree_div").jstree(true).show_contextmenu(jsTreeSelectedNode);
			$(".jstree-contextmenu a[rel='2']").click();
		}else{
			redragonJS.alert("请先选中一个节点后再进行操作","warning");
		}
	    
	});
		
	//添加根节点按钮	
	$("#addRootButton").click(function(){
		getEditNodeModal();
	});
	
	//刷新树按钮
	$("#refreshButton").click(function(){
		$("#jstree_div").jstree(true).refresh();
	});
	
	//设置刷新按钮效果
	$("#refreshButton").hover(
	  function () {
	    $(this).find("i").addClass("fa-spin");
	  },
	  function () {
	    $(this).find("i").removeClass("fa-spin");
	  }
	);
		
});

	//jsTree 新增节点的效果
	function addNodeForJsTree(nodeId, nodeText){
		if(g_inst!=null&&g_clickedNode!=null&&g_obj!=null){
			//创建新节点
	        var newNode = g_inst.create_node(g_clickedNode, {"id": nodeId,"text": nodeText,"type":"department"});
	        //inst.edit(newNode);
	        //inst.open_node(obj.reference);
	        //取消父节点选中
	        g_inst.deselect_node(g_obj.reference);
	        //新增节点选中
	        g_inst.select_node(newNode);
	        
	        g_inst = null;
	        g_clickedNode = null;
	        g_obj = null;
		}else{
			redragonJS.alert("当前页面存在问题，请刷新查看", "warning");
		}
	}
	
	//jsTree 编辑节点的效果
	function editNodeForJsTree(nodeText){
		if(g_inst!=null&&g_obj!=null){
			//inst.edit(obj.reference,clickedNode.val);
			//重命名
			g_inst.rename_node(g_obj.reference, nodeText);
			//重置全局参数
			g_inst = null;
	        g_obj = null;
		}else{
			redragonJS.alert("当前页面存在问题，请刷新查看", "warning");
		}
	}
	
	//jsTree 删除节点的效果
	function deleteNodeForJsTree(){
		if(g_inst!=null&&g_obj!=null){
			g_inst.delete_node(g_obj.reference);
			//重置全局参数
			g_inst = null;
	        g_obj = null;
		}else{
			redragonJS.alert("当前页面存在问题，请刷新查看", "warning");
		}
		
	}
	
	function getEditNodeModal(id, parentId){
		$.ajax({
			type: "post",
			url: "${requestScope.editNodeModalUrl}",
			data: {"nodeId": id, "parentId": parentId},
			async: false,
			dataType: "html",
			cache: false,
			success: function(data){
				if(data!=""){
					$("#addModal").html(data);
					$('#addDiv').modal('show');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				redragonJS.alert(textStatus);
			}
		});
	}
	
	//返回选择的节点id
	function getTreeSelectNodeId(){
		var selectId = $("#jstree_div").jstree(true).get_selected();
		return selectId.toString();
	}
	
	//返回选择的节点type
	function getTreeSelectNodeType(){
		return $("#jstree_div").jstree(true).get_type(getTreeSelectNodeId());
	}

</script>       