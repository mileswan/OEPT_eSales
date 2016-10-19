/**
 * @author zhujj
 * Version: 1.1
 * Date: 2015/11/18
 * Description: Login events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
function testingUser(obj){
	 var from_data = {
			 username:$(obj).val()
	 };
	 var res = "0";
	 var url = "user/testingUesr.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == "1"){
				   $("#testingUser").text("已存在！").css({"color":"#A94442"});
			   }
			   if(res == "2"){
				   $("#testingUser").text("可以使用").css({"color":"green"});
			   }
		   }
		});
	 
}
function testingUser2(obj){
	 var from_data = {
			 username:$(obj).val()
	 };
	 var res = "0";
	 var url = "user/testingUesr.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == "1"){
				   $("#register-submit-btn").css({"display":"none"});
				   $("#register-submit-btn2").css({"display":"inline"});
			   }
			   if(res == "2"){
				   $("#register-submit-btn").css({"display":"inline"});
				   $("#register-submit-btn2").css({"display":"none"});
			   }
		   }
		});
	 
}
//获取当前url参数
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
/**
 * 登录提交
 */
function login(){
	var str = $.md5($("#password2").val())+$("#username2").val();
	var password = $.md5(str);
	 var from_data = {
			 username:$("#username2").val(),
			 password:password
	 };
	 var url = "auth/login.do";
	 var redirect = getQueryString("redirect");
	$.ajax({
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == 1){
				   if( redirect==null || redirect==""){
					   window.location.href = "dashboard/list.do";
				   }else{
					   window.location.href = redirect;
				   }
			   }
			   if(res == 0){
				   $("#loginResult").text("该用户名已删除！").css({
						"color" : "red"
					});
			   }
			   if(res == 2){
				   $("#loginResult").text("用户名密码错误！").css({
						"color" : "red"
					});
			   }
		   },
		   error: function(res){
			   alert(res);
		   }
		});
}
/**
 * 注册提交
 */
function signin(){
	var str1 = $("#username").val();
	var str2 = $("#rpassword").val();
	var str = $.md5(str2)+str1;
	var password = $.md5(str);
	var from_data = {
			firstname:$("#firstname").val(),
			lastname:$("#lastname").val(),
			email:$("#email").val(),
			address:$("#address").val(),
			username:$("#username").val(),
			password:password
	 };
	 var url = "user/signin.do";
	$.ajax({
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == 1){
				   alert("注册成功!");
				   window.location.href = "dashboard/list.do";
			   }
			   if(res == 0){
				  alert("注册失败");
			   }
		   },
		   error: function(res){
			   alert(res);
		   }
		});
}
