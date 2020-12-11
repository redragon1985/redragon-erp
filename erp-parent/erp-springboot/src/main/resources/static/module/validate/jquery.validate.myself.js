/**
 * 为表单验证增加功能
 */

//字段大于0
$.validator.addMethod("gtZero",function(value,element,params){
	if(this.optional(element)){
		return true;
	}else if(value>0){
		return true;
	}else{
		return false;
	}
}, "字段必须大于0");

//字段大于等于0
$.validator.addMethod("gteZero",function(value,element,params){
	if(this.optional(element)){
		return true;
	}else if(value>=0){
		return true;
	}else{
		return false;
	}
}, "字段必须大于等于0");

//小数点后几位
$.validator.addMethod("dotNum",function(value,element,params){
	var dotNumber = value.substring(value.indexOf(".")+1);

	if(this.optional(element)){
		return true;
	}else if(dotNumber.length==parseInt(params[0])){
		return true;
	}else{
		return false;
	}
});

//数字比较
$.validator.addMethod("compareNumber",function(value, element, param) {
	
  var cnum = jQuery(param).val();

  var num1 = parseFloat(cnum);
  var num2 = parseFloat(value);

  return num1 >= num2;
});

//日期比较
$.validator.addMethod("compareDate",function(value, element, param) {
    //var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式
    //value = value + ":00";

    var startDate = jQuery(param).val();
    
    var date1 = new Date(Date.parse(startDate.replace("-", "/").replace("-", "/")));
    var date2 = new Date(Date.parse(value.replace("-", "/").replace("-", "/")));

    return date1 < date2;
});

//验证电话    
$.validator.addMethod("isPhone", function(value,element) {   
    var length = value.length;   
     var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
 	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 

     
    return this.optional(element) || (tel.test(value)||(length==11&&mobile.test(value))); 
    }, "电话号码格式错误");  
   

//验证中文字
$.validator.addMethod("isChinese", function(value, element) {
     var chinese = /^[\u4e00-\u9fa5]+$/;
     return this.optional(element) || (chinese.test(value));
 }, "只能输入中文"); 

//字符验证 
$.validator.addMethod("isUsername", function(value, element) {
return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value); 
}, "只能包括中文字、英文字母、数字和下划线");

$.validator.addMethod("isCode", function(value, element) { 
	var str=/^[a-zA-Z0-9-/_]*$/;
	return this.optional(element) || (str.test(value)); 
}, "只能输入包括英文字母、数字和字符（ - ，/，_ ）");

//密码验证
$.validator.addMethod("isPassword", function(value, element) { 
	var str=/^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)[0-9A-Za-z]{6,40}$/;
	return this.optional(element) || (str.test(value)); 
}, "密码必须包含6位以上数字和英文字母");
//字符验证 
$.validator.addMethod("contains", function(value, element) { 
	if(this.optional(element)){
		return true;
	}else if(value.indexOf("金牌")!=-1){
		return false;
	}else if(value.indexOf("旗舰")!=-1){
		return false;
	}else{
		return true;
	}
}, "不能包含“金牌”，“旗舰”等关键字！"); 

//验证是否有空格
$.validator.addMethod("isNotOnlyContainSpace", function(value, element) {
    return value.indexOf(" ")==-1;
}, "不支持空格字符"); 

//验证是否为整数
$.validator.addMethod("isNotOnlyInteger", function(value, element) {
	if(value % 1 === 0){
		return true;
	}else{
		return false;
	}
}, "请输入整数"); 