/**
 * 复制表格的数据到剪贴板
 */
function copyTable(buttonId,tableId){
	$("#"+buttonId).zclip({
		path : "module/zclip/ZeroClipboard.swf",
		copy : function() {
			var tableContent = "";
			$("#"+tableId).find("tr").each(function(){
				$(this).find("td").each(function(i, n){
					var value = $(this).text().trim();
					value = value.replace(/[\t]/g, "");    //去掉tab
					value = value.replace(/[\r\n]/g, ""); //去掉回车换行
					
					//判断text文本不为空
					if(value!=""){
						if(i==0){
							tableContent = tableContent + value;
						}else{
							tableContent = tableContent + "\t" + value;
						}
					}
				});
				tableContent = tableContent + "\r\n";
			});
			return tableContent;
		},
        afterCopy:function(){
            alert("表格复制成功！");
        }

	});
}