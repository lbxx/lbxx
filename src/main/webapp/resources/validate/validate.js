/*�򵥵�jquery��֤���*/
var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var url_js=localhostPaht+'/';//
    //alert(localhostPaht);
/**
*
*author 361346846@qq.com
*date 2015-02-04
*/
// �Զ�����֤����
var way = {'number':/^\d+$/,'ipv4':/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,'chinse':/^[\u0391-\uFFE5]+$/,
           'pinteger':/^[0-9]\d*$/,'image':/(.jpg|.jpeg|.gif|.bmp|.png)$/,'basecode':/^\w*$/,'isPhone':/^(((1[3,4,5,8][0-9]{1})|159|152|153|156|186)+\d{8})$/,'numeric':/^(\d|[1-9]\d+)((\.\d+)?)$/,'uppercase':/^[A-Z]+$/,'englishzf':/^[a-zA-Z]+$/,'email':/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/,
		   'url':/^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/,'telphone':/^((\d{3,4})-(\d{7,8}))$/,'postcode':/(^$)|(^\d{6}$)/,'capt':/(^$)|(^\w{4}$)/,'idcard':/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
		   'pwd':/\w{6,12}/,'english':/[a-zA-Z]/,'phone':/^(((13|15|18|14)+[0-9]{9})|((\d{3,4})-(\d{7,8})))$/,'lng':/^([0-9]{3}.[0-9]{6})$/,'lat':/^([0-9]{2}.[0-9]{6})$/,'age':/^([1-9]{2}-[1-9]{2})$/,'filename':/^[^ ]{0,32}$/
		  };
var set ;
function r_check(obj){
	submitVal($(obj),set);
	var inspan = $(".infospan").size();
	if (inspan==0){
		SimplePop.load("提交中...");
		return true;
	}
	return false;
}

function checkRight(obj){
	submitVal($(obj),set);
}

jQuery.fn.validate_now=function(setting){
	submitVal($(this),setting);
	var inspan = $(".infospan").size();

}
jQuery.fn.validate=function(setting){
	set = setting;
    validateForm($(this),setting);
}
var isOk = 0;
function validateForm(form,setting){
form.find("*").each(function(i){
	 try { //处理异常
    if ($(this).attr("simple")!=undefined){ // ��ʾ��Ҫ��֤
	 var  obj = eval("("+$(this).attr('simple')+")"); // ��Ϣ����
	   obj.emp = obj.emp == undefined?false:obj.emp;
	  //alert(way[obj.way]);
	  // ��ݶ�Ӧ�ķ�������������ʽ����true or false �ٵ���
	  // ע����¼���blur ,keyup,���ύ�¼���
		if($(this).is("select")) {
			$(this).bind("change",function(){
			   if (obj.emp){
				   if ($(this).val()==""){
						  infoPosition($(this),setting,'empmsg',i);
						    return;
						  }else{
							  infoPosition($(this),setting,'succ',i);
					 }
			   }
			});
	   }
	  $(this).bind("keyup",function(){
	       var reg;
		   if (obj.emp){ // ������Ϊ��
		      if ($(this).val()==""){
			  infoPosition($(this),setting,'empmsg',i);
			    return;
			  }else{
				  infoPosition($(this),setting,'succ',i);
			  }
		   }
		   // Զ����֤
		   if (obj.strong){
		   var now_val = $(this).val();
		       if (now_val!=""){
				 var middle = /^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$/; // �е�
				 var strong = /^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$/; // ǿ
			   }
			    return false
		   }
		  
		   if(obj.length && ($(this).val() != null || $(this).val()!="")) {
			   
			   var length = $(this).val().length;
			   var limitLength = obj.length.substr(1);
			   var judge = obj.length.substr(0,1);
			   if(judge == ">") {
				   if(length < limitLength) {
					   setting.length = "字符长度必须大于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return;
				   }else{
					   infoPosition($(this),setting,'succ',i);
					   return ;
				   }
			   }
			   if(judge == "<") {
				   if(length > limitLength) {
					   setting.length = "字符长度必须小于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return;
				   }else{
					   infoPosition($(this),setting,'succ',i);
					   return ;
				   }
			   }
			   if(judge == "=") {
				   if(length != limitLength) {
					   setting.length = "字符长度必须等于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return;
				   }else{
					   infoPosition($(this),setting,'succ',i);
					   return ;
				   }
			   }
			   if(judge == "~") {
				   var ll = limitLength.split("-");
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length < min || length > max ) {
					   setting.length = "字符长度必须在" + min + "和" + max + "之间";
					   infoPosition($(this),setting,'length',i);
					   return;
				   }else{
					   infoPosition($(this),setting,'succ',i);
					   return ;
				   }
			   }
			   if(judge == "|"){
				   var ll = limitLength.split("-");
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length != min && length != max ) {
					   setting.length = "字符长度必须等于" + min + "或" + max + "";
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }
			   }
		   }
		   if (obj.remote != undefined){
		      var url = obj.remote;
			  var name = $(this).attr("name");
			  var val1 = $(this).val();
			  if (val1 == "")return;
			  infoPosition($(this),setting,'val',i);
			  var param = new Object();
			  param[name] = val1;
			  var dom = $(this);
			  $.ajax({
			    type:'post',
				url:url,
				data:param,
				success:function(data){
				  if ( data == "false"){ // ��ʾ��֤�ɹ�
				    infoPosition(dom,setting,'remotemsg',i);
			       return ;
				  }else{
				    infoPosition(dom,setting,'succ',i);
				  }
				},
				error:function(){
				 // alert('error!');
				}
			   });
			   
		    }
		    if (obj.way == "equalto"){
		    	if (obj.emp && $(this).val() == ""){
		    		infoPosition($(this),setting,'empmsg',i);
		    	}
		    	else if($(this).val()!=$("#"+obj.target).val() || $("#"+obj.target).val()==""){
			   infoPosition($(this),setting,'err',i);
		       return;
			 }else{
			 infoPosition($(this),setting,'succ',i);
			 }
		   }
	       reg = way[obj.way];
	       if ($(this).val() == "" && !obj.emp){
	    	   $(this).next(".infospan").remove();
	    	   $(this).next(".infomsg").remove();
	    	   $(this).css("border","1px solid #d6d6d6");
	    	   return;
	       }
	       if (reg == undefined || $(this).val() == "")return;
		   if ( !reg.test($(this).val())){ // ��֤ʧ��
		   
		    infoPosition($(this),setting,'err',i);
			   return;		      
		   }else if (reg.test($(this).val())){
		   $(".info"+i).remove();
		      infoPosition($(this),setting,'succ',i);
		   }
	  });
	  $(this).bind("blur",function(){
	      var reg;
		  if (obj.emp){ // ������Ϊ��
		      if ($(this).val()==""){
			  infoPosition($(this),setting,'empmsg',i);
		       return;
			  }else{
				  infoPosition($(this),setting,'succ',i);
			  }
		   }
		  if(obj.endWith) {
			   var str = obj.endWith;
			   if(str==null||str==""||this.length==0||str.length>this.length)
				   return;
			   var currentData = $(this).val();
				 if(currentData.substring(currentData.length-str.length)==str){
					 setting.length = "后四位不能为" + str;
					 infoPosition($(this),setting,'length',i);
					 return;
				 }else {
					 infoPosition($(this),setting,'succ',i);
				 }
		   }
		  
		  if(obj.length && ($(this).val() != null || $(this).val()!="")) {
			   var length = $(this).val().length;
			   var limitLength = obj.length.substr(1);
			   var judge = obj.length.substr(0,1);
			   if(judge == ">") {
				   if(length < limitLength) {
					   setting.length = "字符长度必须大于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "<") {
				   if(length > limitLength) {
					   setting.length = "字符长度必须小于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "=") {
				   if(length != limitLength) {
					   setting.length = "字符长度必须等于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "~") {
				   var ll = limitLength.split("-");
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length < min || length > max ) {
					   setting.length = "字符长度必须在" + min + "和" + max + "之间";
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge=="|"){
				   var ll = limitLength.split("-");
				   
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length != min && length != max ) {
					   setting.length = "字符长度必须等于" + min + "或" + max + "";
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }
			   }
			   
			   
		   }
		  
		   if (obj.remote != undefined){//Զ��������
		      var url = obj.remote;
			  var name = $(this).attr("name");
			  var val1 = $(this).val();
			  if (val1 == "")return;
			  infoPosition($(this),setting,'val',i);
			  var param = new Object();
			  param[name] = val1;
			  var dom = $(this);
			  $.ajax({
			    type:'post',
				url:url,
				data:param,
				success:function(data){
				  if ( data == "false"){ // ��ʾ��֤�ɹ�
				    infoPosition(dom,setting,'remotemsg',i);
			       return ;
				  }else{
				    infoPosition(dom,setting,'succ',i);
				  }
				},
				error:function(){
				  //alert('error!');
				}
			   });
			  
		    }
		   
		   
		   
		    if (obj.way == "equalto"){
		    	if (obj.emp && $(this).val() == ""){
		    		infoPosition($(this),setting,'empmsg',i);
		    	}
		    	else if($(this).val()!=$("#"+obj.target).val() || $("#"+obj.target).val()==""){
			   infoPosition($(this),setting,'err',i);
		       return;
			 }else{
			 infoPosition($(this),setting,'succ',i);
			 }
		   }
	       reg = way[obj.way];
	       if ($(this).val() == "" && !obj.emp){
	    	   $(this).next(".infospan").remove();
	    	   $(this).next(".infomsg").remove();
	    	   $(this).css("border","1px solid #d6d6d6");
	    	   return;
	       }
	       if (reg == undefined || $(this).val() == "")return;
	       
	       
	       
	       
	       if (reg!=undefined && !reg.test($(this).val())){ // ��֤ʧ��
		       infoPosition($(this),setting,'err',i);
			   return;		  
		  }else if (reg!=undefined && reg.test($(this).val())){
		   infoPosition($(this),setting,'succ',i);
		   }
	  });
	  if (obj.info!=undefined){
	     $(this).bind("focus",function(){
		     var span = $("body").find(".errinfo"+i+"").attr("class");
			 if (span == undefined && $(this).val()==""){
		     infoPosition($(this),setting,'info',i);
		    }
		 });
	  }
	}
	 } catch (e) {
			//console.log("发生异常"+e.message);
			return true; //继续遍历
		}
  });
  return isOk;
}
// ������Ϣλ��
var info = {'info':'background: url('+url_js+'/controls/img/inf2.png) left no-repeat;color:rgb(39, 144, 204);','err':'background: url('+url_js+'/controls/img/alt_04.jpg) left no-repeat;color:#f00;','succ':'background: url('+url_js+'/controls/img/alt_01.jpg) left no-repeat;color:rgb(48, 182, 48);'};
function infoPosition(dom,setting,msg,index){
       var x =  dom.offset().left;
       var width = dom.width();
	   var hei = dom.height();
	   var psucc = parseInt(dom.css("paddingTop").replace("px",""));
	   var pb = parseInt(dom.css("paddingBottom").replace("px",""));
	   var rpx = parseInt(dom.css("paddingRight").replace("px",""))+parseInt(dom.css("paddingLeft").replace("px",""));
       var y =  dom.offset().top;
	   var  obj = eval("("+dom.attr('simple')+")"); // ��Ϣ����
	   obj.err = obj.err==undefined?"":obj.err;
	   obj.succ = obj.succ==undefined?"":obj.succ;
	   obj.way = obj.way == undefined?"":obj.way;
	   obj.empmsg = obj.empmsg == undefined?obj.err:obj.empmsg;
	   obj.remotemsg = obj.remotemsg == undefined?obj.err:obj.remotemsg;
	   obj.emp = obj.emp ==undefined?false:true;
	   var pos = setting.position == undefined?'right':setting.position; // ��Ϣ��λ��
	    var style ;
	   if (msg == 'empmsg' || msg == 'remotemsg' || msg == 'length'){ // ��ʽ���Զ���
	       style = info['err'];
	   }else{
	    style = info[msg];
	   }
	   var span = "<span class='infospan "+msg+"info"+index+"'  style='margin-left:3px;"+style+"padding:"+(psucc)+"px 10px;font-size:12px;padding-left:19px;z-index:2;height:"+hei+"px;line-height:"+parseInt(hei)+"px;display:inline-block;";
	   if (obj.succ == "" && 'succ' == msg){ // δ������֤�ɹ���Ϣ��
	      span = span = "<span class='infomsg "+msg+"info"+index+"'  style='margin-left:3px;background: url("+url_js+"/controls/img/alt_01.jpg) left  no-repeat;padding:"+(psucc+1)+"px 10px;font-size:13px;padding-left:19px;z-index:2;height:"+hei+"px;line-height:24px;display:inline;"
	   }if((obj.err == "" && 'err' == msg) || (obj.empmsg == "" && 'empmsg' == msg) || (obj.remotemsg == "" && 'remotemsg' == msg)){
	     span = span = "<span class='infospan "+msg+"info"+index+"'  style='margin-left:3px;background: url("+url_js+"/controls/img/alt_04.jpg) left  no-repeat;padding:"+(psucc+1)+"px 10px;font-size:13px;padding-left:19px;z-index:2;height:"+hei+"px;line-height:24px;display:inline;"
	   }
	  
	   if ( msg == "succ"){
		   dom.css("border","1px solid #d6d6d6");
	   }else if ("info" == msg){
		   dom.css("border","1px solid rgb(64, 160, 245)");
	   }else{
		   dom.css("border","1px solid rgb(245, 98, 132)");
	   }
	  dom.next(".infospan").remove();
	  dom.next(".infomsg").remove();
	  if("val" == msg){
		      span = "<span class='infomsg "+msg+"info"+index+"'  style='margin-left:3px;left:"+(x+width+rpx+6)+"px;top:"+(y)+"px;font-size:13px;z-index:2;height:"+hei+"px;line-height:24px;display:inline;'><img src='controls/img/04.gif'/></span>"
		     dom.after(span);
		     return;
	  }
	   if ( "top" == pos){
	      span+="left:"+(x)+"px;top:"+(y-hei-psucc-pb-4)+"px;'>"+obj[msg]+"</span>";
	   }else if ( "bottom" == pos){
	      span+="left:"+(x)+"px;top:"+(y+hei+psucc+pb+4)+"px;'>"+obj[msg]+"</span>";
	   }else if ( "right" == pos){
	      span+="left:"+(x+width+rpx+6)+"px;top:"+(y)+"px;'>"+ (msg == 'length' ? setting.length : obj[msg]) +"</span>";
	   }else{
	      $("."+pos+"_"+index).remove();
		  if (msg == 'succ')return;
		  $("#"+pos).append("<li class='"+pos+"_"+index+"'>"+obj[msg]+"</li>");
		  return;
	   }
	   dom.after(span);
	   
}

function submitVal(form,setting){
   form.find("*").each(function(i){
	   try{
    if ($(this).attr("simple")!=undefined){ // ��ʾ��Ҫ��֤
	 var  obj = eval("("+$(this).attr('simple')+")"); // ��Ϣ����
	 obj.emp = obj.emp == undefined?false:obj.emp;
	       var reg;
		   reg = way[obj.way];
		  if (obj.emp){ // ������Ϊ��
		      if ($(this).val()==""){
			   infoPosition($(this),setting,'empmsg',i);
			   return;
			  }else{
				  infoPosition($(this),setting,'succ',i);
			  }
		   }
		  if ($(this).val() == "" && !obj.emp){
	    	   $(this).next(".infospan").remove();
	    	   $(this).next(".infomsg").remove();
	    	   $(this).css("border","1px solid #d6d6d6");
	    	   return;
	       }
		  if(obj.length) {
			   var length = $(this).val().length;
			   var limitLength = obj.length.substr(1);
			   var judge = obj.length.substr(0,1);
			   if(judge == ">") {
				   if(length < limitLength) {
					   setting.length = "字符长度必须大于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "<") {
				   if(length > limitLength) {
					   setting.length = "字符长度必须小于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "=") {
				   if(length != limitLength) {
					   setting.length = "字符长度必须等于" + limitLength;
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge == "~") {
				   var ll = limitLength.split("-");
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length < min || length > max ) {
					   setting.length = "字符长度必须在" + min + "和" + max + "之间";
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }else{
					   infoPosition($(this),setting,'succ',i);
				   }
			   }
			   if(judge=="|"){
				   var ll = limitLength.split("-");
				   
				   var min = parseInt(ll[0]);
				   var max = parseInt(ll[1]);
				   if(length != min && length != max ) {
					   setting.length = "字符长度必须等于" + min + "或" + max + "";
					   infoPosition($(this),setting,'length',i);
					   return ;
				   }
			   }
			  
		   }
		  
		   // Զ����֤
		   if (obj.remote != undefined){
			  
		      var url = obj.remote;
			  var name = $(this).attr("name");
			  var val1 = $(this).val();
			  if (val1 == "")return;
			  infoPosition($(this),setting,'val',i);
			  var param = new Object();
			  param[name] = val1;
			  var dom = $(this);
			  $.ajax({
			    type:'post',
				url:url,
				data:param,
				success:function(data){
				  if ( data == "false"){ // ��ʾ��֤�ɹ�
				    infoPosition(dom,setting,'remotemsg',i);
			        return;
				  }else{
				    infoPosition(dom,setting,'succ',i);
				  }
				},
				error:function(){
				  //alert('error!');
				}
			   });
		    }
		    if (obj.way == "equalto"){
		    	
		    	if (obj.emp && $(this).val() == ""){
		    		infoPosition($(this),setting,'empmsg',i);
		    	}
		    	else if($(this).val()!=$("#"+obj.target).val() || $("#"+obj.target).val()==""){
			   infoPosition($(this),setting,'err',i);
		       return;
			 }else{
			   infoPosition($(this),setting,'succ',i);
			 }
		   }
		    else if (reg == undefined || $(this).val() == "")return;
		  else if (reg!=undefined && !reg.test($(this).val())){ // ��֤ʧ��
			  infoPosition($(this),setting,'err',i);
            return;
		   }else if (reg!=undefined && reg.test($(this).val())){
			   
			   infoPosition($(this),setting,'succ',i);
		   }
	}
   } catch (e) {
		//console.log("发生异常"+e.message);
		return true; //继续遍历
	}
  });
   return isOk;
}
$(function(){
	$("#validateForm").validate({position:'right'});
})