/**
 * 由于img在某些浏览器中显示异常，替换成div显示图片
 */
function initDiv(id){
	this.IMG = document.getElementById(id); 
    var masker = document.createElement("div");  
    masker.id = "mask_" + id;  
    var positionTop = $("#"+id).offset().top; 
    var positionLeft = $("#"+id).offset().left; 
    masker.style.width =  $("#"+id).width()+"px";  
    masker.style.height = $("#"+id).height()+"px";  
    masker.style.left = positionLeft;  
    masker.style.top = positionTop;  
    masker.style.position="relative";
    masker.style["background-image"] = "url("+this.IMG.src+")";  
    masker.className = "imgmasker";  
    this.masker = masker;  
    this.IMG.parentNode.appendChild(masker);  
    this.IMG.parentNode.removeChild(this.IMG);  
}
/**
 * 设置框框属性
 */
var index=0;
function drawTemplate(pointX,pointY,pointWidth,pointHeight){
	$(".rectangle").remove();
	//创建一个框框div
	var d = document.createElement("div");    
	$("#mask_templateImage").append(d);
	//以下设置div属性
	d.style.border = "solid #ff0000";
	if(pointWidth==""){
		d.style.borderRadius = "50%";
		d.style.left = (parseInt(pointX)-10) + "px";  
	    d.style.top = (parseInt(pointY)-10) + "px";
		d.style.width = "20px";    
	    d.style.height = "20px"; 
	}else{
		d.style.left = pointX + "px";  
	    d.style.top = pointY + "px";
		d.style.width = pointWidth+"px";    
	    d.style.height = pointHeight+"px";    
	}
    d.style.overflow = "hidden";    
    d.style.position = "absolute";    
    d.style.opacity = 0.5;  
    d.style["z-index"] = 2;  
    d.id = "draw" + index; 
    d.className="rectangle";
    index++;
}