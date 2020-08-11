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
});

//字段大于等于0
$.validator.addMethod("gteZero",function(value,element,params){
	if(this.optional(element)){
		return true;
	}else if(value>=0){
		return true;
	}else{
		return false;
	}
});

//字段小于等于数字
$.validator.addMethod("lteNumber",function(value,element,params){
	if(this.optional(element)){
		return true;
	}else if(value<=parseInt(params)){
		return true;
	}else{
		return false;
	}
},"输入的数字超过实际范围");

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

//日期比较
$.validator.addMethod("compareDate",function(value, element, param) {
    //var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式
    //value = value + ":00";

    var startDate = jQuery(param).val();
    var date1 = new Date(Date.parse(startDate.replace("-", "/").replace("-", "/")));
    var date2 = new Date(Date.parse(value.replace("-", "/").replace("-", "/")));

    return date1 < date2;
},"结束日期不能小于开始日期");

//验证电话    
$.validator.addMethod("isPhone", function(value,element) {   
    var length = value.length;   
     var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/; 
 	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 

     
    return this.optional(element) || (tel.test(value)||(length==11&&mobile.test(value))); 
    }, "电话号码格式错误"); 
//身份证号码验证
$.validator.addMethod("isIdCardNo", function(value, element) {
	return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
}, "请正确输入您的身份证号码");
//身份证号码唯一性验证
$.validator.addMethod("haveSameIdCardNo", function(value, element) {
	return this.optional(element) || idCardNoUtil.haveSameIdCardNo(value);
}, "身份证号码已存在");
//护照编号验证
$.validator.addMethod("passport", function(value, element) {
return this.optional(element) || checknumber(value);
}, "请正确输入您的护照编号");

//验证银行卡
$.validator.addMethod("isBankNo", function(value,element) {   
	  var bankno = $.trim(value);
	  var num = /^\d*$/; //全数字
	  var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
	  return bankno.length < 16 || bankno.length > 19||!num.exec(bankno)||bankno.substring(0, 2) == -1||!luhmCheck(bankno);
}, "电话号码格式错误"); 

//验证邮箱   
$.validator.addMethod("isEmail", function(value,element) {   
	var email = /^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[.])+[A-Za-z0-9]{2,5}$/; 
	return this.optional(element) || (email.test(value)); 
}, "邮箱格式错误");
//验证是否含有百分号 
$.validator.addMethod("iscontainPercent", function(value,element) {   
    var content =/%+/; 
    return this.optional(element) || !(content.test(value)); 
}, "不能含有百分号");

//验证中文字
$.validator.addMethod("isChinese", function(value, element) {
     var chinese = /^[\u4e00-\u9fa5]+$/;
     return this.optional(element) || (chinese.test(value));
 }, "只能输入中文"); 

//字符验证 
$.validator.addMethod("userName", function(value, element) { 
return this.optional(element) || /^[\u0391-\uFFE5\w\.\ ]+$/.test(value); 
}, "只能包括中文字、英文字母、数字和下划线");

//字符验证 
$.validator.addMethod("isNotChinese", function(value, element) { 
return this.optional(element) || /^[\w]+$/.test(value); 
}, "只能包括英文字母、数字及下划线");

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

//验证小数
$.validator.addMethod("isDecimal", function(value, element) {
	var decimal = /^\d+(\.\d{1,2})?$/;
    return this.optional(element) || (decimal.test(value));
}, "请保留两位小数！");

//验证下限比上限小
$.validator.addMethod("compareLess", function(value, element, param) {
    var compareValue = jQuery(param).val();
    compareValue = compareValue*1;
    value = value*1;
    return value <= compareValue;
}, "下限不得大于上限！");
//验证上限比下限大
$.validator.addMethod("compareGreater", function(value, element, param) {
    var compareValue = jQuery(param).val();
    compareValue = compareValue*1;
    value = value*1;
    return value >= compareValue;
}, "上限不得小于下限！");
//验证数在上下限之间
$.validator.addMethod("between", function(value, element, param) {
	var small = "";
	var big = "";
    
    switch(param){
    case "endowment": 
    	big = $("#endowmentBaseUpper").val();
    	small = $("#endowmentBaseLower").val();
    	break;
    case "unemployment":
    	big = $("#unemploymentBaseUpper").val();
    	small = $("#unemploymentBaseLower").val();
    	break;
    case "workInjury":
    	big = $("#workInjuryBaseUpper").val();
		small = $("#workInjuryBaseLower").val();
    	break;
    case "medical":
    	big = $("#medicalBaseUpper").val();
		small = $("#medicalBaseLower").val();
    	break;
    case "maternity":
    	big = $("#maternityBaseUpper").val();
		small = $("#maternityBaseLower").val();
    	break;
    case "housing":
    	big = $("#housingBaseUpper").val();
		small = $("#housingBaseLower").val();
    	break;
    default : break; 
    }
    
    compareValue = parseInt(big);
    compareValue = parseInt(small);
    value = parseInt(value);
    
    return (value <= big) && (value >= small);
}, "必须在上下限之间！");
//只能填写数字
$.validator.addMethod("numberOnly", function(value, element) { 
	var str=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
	return this.optional(element) || (str.test(value)); 
}, "只能输入数字");

//验证不相等
$.validator.addMethod("notEqualTo", function(value, element, param) {
    var compareValue = jQuery(param).val();
    return value != compareValue;
}, "两个值不能相等！");

//验证岗位名称是否存在
/*
$.validator.addMethod("isExistPoName", function(value, element) {
    var url = "position/checkPoNameByOne.action";
	$.ajax({
		type: "post", 
		url: url,
		data:{
			"position.poName":value
			//"user.orgCode":$("#orgCodeAdd").val()
		},
		cache: false, 
		async: false,
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		dataType:"html",
		success: function(data) {
			if(data=="false"){
				isusername=false;
			}else{
				isusername=true;
			}
		}
	});
    return isusername;
 }, "岗位名称已被使用！"); */
//验证职级名称是否存在
/*
$.validator.addMethod("isExistRaName", function(value, element) {
    var url = "rank/checkRaNameByOne.action";
	$.ajax({
		type: "post", 
		url: url,
		data:{
			"rank.raName":value
			//"user.orgCode":$("#orgCodeAdd").val()
		},
		cache: false, 
		async: false,
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		dataType:"html",
		success: function(data) {
			if(data=="false"){
				isusername=false;
			}else{
				isusername=true;
			}
		}
	});
    return isusername;
 }, "职级名称已被使用！"); */

//身份证号码验证
var idCardNoUtil = {
		 /*省,直辖市代码表*/
		 provinceAndCitys: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",
		 31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
		 45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
		 65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},
		 
		 /*每位加权因子*/
		 powers: ["7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"],
		  
		 /*第18位校检码*/
		 parityBit: ["1","0","X","9","8","7","6","5","4","3","2"],
		 
		 /*性别*/
		 genders: {male:"男",female:"女"},
		 
		 /*校验地址码*/
		 checkAddressCode: function(addressCode){
		   var check = /^[1-9]\d{5}$/.test(addressCode);
		   if(!check) return false;
		   if(idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0,2))]){
		    return true;
		   }else{
		    return false;
		   }
		 },
		 
		 /*校验日期码*/
		 checkBirthDayCode: function(birDayCode){
		   var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
		   if(!check) return false;  
		   var yyyy = parseInt(birDayCode.substring(0,4),10);
		   var mm = parseInt(birDayCode.substring(4,6),10);
		   var dd = parseInt(birDayCode.substring(6),10);
		  var xdata = new Date(yyyy,mm-1,dd);
		  if(xdata > new Date()){
		   return false;//生日不能大于当前日期
		  }else if ( ( xdata.getFullYear() == yyyy ) && ( xdata.getMonth () == mm - 1 ) && ( xdata.getDate() == dd ) ){
		   return true;
		  }else{
		   return false;
		  }
		 },
		  
		 /*计算校检码*/
		 getParityBit: function(idCardNo){
		  var id17 = idCardNo.substring(0,17);  
		   /*加权 */
		   var power = 0;
		   for(var i=0;i<17;i++){
		    power += parseInt(id17.charAt(i),10) * parseInt(idCardNoUtil.powers[i]);
		   }       
		   /*取模*/
		   var mod = power % 11;
		   return idCardNoUtil.parityBit[mod];
		 },
		  
		 /*验证校检码*/
		 checkParityBit: function(idCardNo){
		   var parityBit = idCardNo.charAt(17).toUpperCase();
		   if(idCardNoUtil.getParityBit(idCardNo) == parityBit){
		     return true;
		   }else{
		     return false;
		   }
		 },
		 
		 /*校验15位或18位的身份证号码*/
		 checkIdCardNo: function(idCardNo){
			 //15位和18位身份证号码的基本校验
			 var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
			 if(!check) return false;
			 //判断长度为15位或18位 
			 if(idCardNo.length==15){
				 return idCardNoUtil.check15IdCardNo(idCardNo);
			 }else if(idCardNo.length==18){
				 return idCardNoUtil.check18IdCardNo(idCardNo);
			 }else{
				 return false;
			 }
		 },
		 /*身份证号码唯一性验证*/
		 haveSameIdCardNo: function(idCardNo){
		    //如果选择身份证件，判断身份证号唯一性验证
			var flag = true;
			var documentType = $("#staDocumentTypeEdit").val();
			if(documentType = "ID"){
				var staId = $("#staId").val();
				var url = "staff/checkIstheSameIdCard.action";
				$.ajax({
					type : "post",
					url : url,
					data : {
						"staff.staId" : staId,
						"staff.staIdnumber" : idCardNo
					},
					async : false,
					cache : false,
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					success : function(data) {
						if (data == "Y") {
							flag = false;
						}else{
							flag = true;
						}
					}
				});
			}
			return flag;
		 },
		 
		 //校验15位的身份证号码
		 check15IdCardNo: function(idCardNo){
		   //15位身份证号码的基本校验
		   var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(idCardNo);  
		   if(!check) return false;
		   //校验地址码
		   var addressCode = idCardNo.substring(0,6);
		   check = idCardNoUtil.checkAddressCode(addressCode);
		   if(!check) return false;
		   var birDayCode = '19' + idCardNo.substring(6,12);
		   //校验日期码
		   return idCardNoUtil.checkBirthDayCode(birDayCode);
		 },
		 
		 //校验18位的身份证号码
		 check18IdCardNo: function(idCardNo){
		   //18位身份证号码的基本格式校验
		   var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(idCardNo);
		   if(!check) return false;
		   //校验地址码
		   var addressCode = idCardNo.substring(0,6);
		   check = idCardNoUtil.checkAddressCode(addressCode);
		   if(!check) return false;
		   //校验日期码
		   var birDayCode = idCardNo.substring(6,14);
		   check = idCardNoUtil.checkBirthDayCode(birDayCode);
		   if(!check) return false;
		   //验证校检码  
		   return idCardNoUtil.checkParityBit(idCardNo);  
		 },
		 
		 formateDateCN: function(day){
		    var yyyy =day.substring(0,4);
		    var mm = day.substring(4,6);
		    var dd = day.substring(6);
		    return yyyy + '-' + mm +'-' + dd;
		 },
		 
		 //获取信息
		 getIdCardInfo: function(idCardNo){
		   var idCardInfo = {
		    gender:"",  //性别
		    birthday:"" // 出生日期(yyyy-mm-dd)
		   };
		  if(idCardNo.length==15){
		    var aday = '19' + idCardNo.substring(6,12);
		    idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
		    if(parseInt(idCardNo.charAt(14))%2==0){
		      idCardInfo.gender=idCardNoUtil.genders.female;
		    }else{
		      idCardInfo.gender=idCardNoUtil.genders.male;
		    }   
		  }else if(idCardNo.length==18){
		    var aday = idCardNo.substring(6,14);
		    idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
		     if(parseInt(idCardNo.charAt(16))%2==0){
		      idCardInfo.gender=idCardNoUtil.genders.female;
		    }else{
		      idCardInfo.gender=idCardNoUtil.genders.male;
		    } 
		     
		  }
		  return idCardInfo;
		 },
		  
		 /*18位转15位*/
		 getId15: function(idCardNo){
		  if(idCardNo.length==15){
		    return idCardNo;
		  }else if(idCardNo.length==18){
		    return idCardNo.substring(0,6) + idCardNo.substring(8,17); 
		  }else{
		  return null;
		  }
		 },
		  
		 /*15位转18位*/
		 getId18: function(idCardNo){
		  if(idCardNo.length==15){
		    var id17 = idCardNo.substring(0,6) + '19' + idCardNo.substring(6);
		    var parityBit = idCardNoUtil.getParityBit(id17);
		    return id17 + parityBit;
		  }else if(idCardNo.length==18){
		    return idCardNo;
		  }else{
		  return null;
		  }
		 }
		};

		//验证护照是否正确
		function checknumber(number){
		var str=number;
		//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
		var re =new RegExp("(^([PSE]{1}\\d{7}|[GS]{1}\\d{8})$)");//E字打头的后面不知道要跟几位
		var card=str.toUpperCase();
		if(re.test(card)){
			return true;
		}else{
			return false;
		}
		   
		};