/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/18
 * Description: User management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */

/*
 * 判断是否需要更改密码
 */
function ifReadOnly(){
	if(document.getElementById('changePassword').checked){
		document.getElementById("newPassword").readOnly=false;
		document.getElementById("confirmPassword").readOnly=false;	
	}else{
		document.getElementById("newPassword").value="";
		document.getElementById("confirmPassword").value="";
		document.getElementById("newPassword").readOnly=true;
		document.getElementById("confirmPassword").readOnly=true;	
	}

}


//定义变量
var globalVariable = 0;
var userVariable = 0;
var emailVariable = 0;
var pwdVariable = 0;


/**
 * ajax提交数据userNews
 */
function ajaxPostSubmit(){
	if($("#username").val()==""){
		$("#testingUser").text("用户名不能为空！").css({"color":"#A94442"});
		   userVariable = 0;
	}else if($("#confirmPassword").val()==""){
		$("#confirmPassword").text("不能为空").css({"color":"#A94442"});
		globalVariable = 0;
	}else{
		if(globalVariable == 1&&userVariable==1&&emailVariable==1&&pwdVariable==1){
			var str1 = $("#username").val();
			var str2 = $("#newPassword").val();
			var password = null;
			if(str2!=""){
				var str = $.md5(str2)+str1;
				password = $.md5(str);
			}
			 var from_data = {
					 username:$("#username").val(),
					 password:password,
					 emailaddress:$("#emailaddress").val(),
					 address:$("#address").val(),
					 lastname:$("#lastname").val(),
					 firstname:$("#firstname").val()
			 };
			 var res = "0";
			 var url = "newUser.do";
			$.ajax({
				   type: "POST",
				   url: url,
				   data: from_data,
				   success: function(res){
					   if(res == "1"){
						   alert("创建成功");
						   window.location.href="list.do"; 
					   }
					   if(res == "0"){
						   alert("创建失败");
					   }
				   }
				});
		}
	}
}

/**
 * 验证密码
 */
function passwordSpan(){
	var nPwd = $("#newPassword").val();
	if(nPwd == ""){
		$("#passwordSpan").text("不能为空，请输入!").css({"color":"#A94442"});
		pwdVariable = 0;
	}else{
		$("#passwordSpan").text("");
		pwdVariable = 1;
	}
}

/**
 * 验证再次输入密码
 */
function passwordVerify(){
	var nPwd = $("#newPassword").val();
	var cPwd = $("#confirmPassword").val();
	if(cPwd == "" && nPwd == ""){
		$("#passwordVerify").text("不能为空，请输入!").css({"color":"#A94442"});
		globalVariable = 0;
	}else{
		if(nPwd != cPwd){
			$("#passwordVerify").text("两次密码不一致，请重新输入!").css({"color":"#A94442"});
			globalVariable = 0;
		}else{
			$("#passwordVerify").empty();
			globalVariable = 1;
		}
	}
	
	
}

/**
 * 验证邮箱
 */
function checkEmail(){
	var email = $("#emailaddress").val();
	if($("#emailaddress").val()==""){
		$("#testingemail").text("邮箱地址不能为空!").css({"color":"#A94442"});
		emailVariable = 0;
	}else{
		var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		if(re.test(email)){
			$("#testingemail").text("");
			emailVariable = 1;
		}else{
			$("#testingemail").text("邮箱格式不正确!").css({"color":"#A94442"});
			emailVariable = 0;
		}
	}
	
}

/**
 * 验证用户名是否存在
 * @param obj
 */
function testingUser(obj){
	if($(obj).val() == ""){
		 $("#testingUser").text("用户名不能为空！").css({"color":"#A94442"});
		   userVariable = 0;
	}else{
		var from_data = {
				 username:$(obj).val()
		 };
		 var res = "0";
		 var url = "testingUesr.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   data: from_data,
			   success: function(res){
				   if(res == "1"){
					   $("#testingUser").text("已存在！").css({"color":"#A94442"});
					   userVariable = 0;
				   }
				   if(res == "2"){
					   $("#testingUser").text("可以使用").css({"color":"green"});
					   userVariable = 1;
				   }
			   }
			});
	}
	 
	 
}

/**
 * 返回
 */
function goBack(){
	window.location.href="list.do"; 
}