//全选全不选
$(".tabSelectall").click(function(){
	if($(".tabSelectall").prop('checked')){
		$(".tabSelecton").prop("checked",true);
	}else{
		$(".tabSelecton").prop("checked",false);
	}
});
$(".tabSelecton").click(function(){
	var ischeck=true;
	$(".tabSelecton").each(function(){
		if(!$(this).prop('checked')){
			ischeck=false;
			return;
		}
	})
	if(!ischeck){
		$(".tabSelectall").prop("checked",false);
	}else{
		$(".tabSelectall").prop("checked",true);
	}
});
/**
 * 快捷键
 */
$(document).keydown(function(e){
	//console.log(e.keyCode); 
	if(e.shiftKey && e.which == 78) {//shift+n
		document.getElementById("editPage").click(); 
	}
	if(e.shiftKey && e.which == 70) {//shift+f
		document.getElementById("searchEvent").click(); 
	}
});
/**
 * 排序
 * @param tableId：表格id
 * @param field:排序字段
 * @returns {String}：排序类型：asc 倒序，desc:正序。
 */
function sortTable(tableId,field,sortValue,formValue){
	var sortstr;
	var sortStatus=$("#"+tableId+" tr:eq(1) th:eq("+field+")").prop("class").toString();
	if(sortStatus=="sorting"){//倒排序
		sortstr="asc";
	}else if(sortStatus=="sorting_asc"){//正序
		sortstr="desc";
	}else if(sortStatus=="sorting_desc"){//id排序
		sortstr="";
	}
	$("#"+sortValue).val(sortstr);
	$("#"+formValue).submit();
}

/**
 * 获取项目根路径
 * @returns http://localhost:8088/permission
 */
function getRootPath(){  
    //获取当前网址，如： http://localhost:8088/permission/userEdit.jsp  
    var curWwwPath = window.document.location.href;  
    //获取主机地址之后的目录，如： permission/userEdit.jsp  
    var pathName = window.document.location.pathname;  
    var pos = curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8088  
    var localhostPath = curWwwPath.substring(0, pos);  
    //获取带"/"的项目名，如：/permission  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1); 
    return(localhostPath + projectName);  
}