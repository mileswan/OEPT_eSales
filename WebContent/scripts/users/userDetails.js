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

var globalVariable = 1;

/*
 * ajax提交数据userDetails
 */
function ajaxPostSubmit(){
	if(globalVariable == 1){
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
				 firstname:$("#firstname").val(),
				 active:$("#active").val(),
				 userId:$("#userId").val(),
				 position:$("#position").val(),
				 positionId:$("#positionId").val()
		 };
		 var res = "0";
		 var url = "update.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   data: from_data,
			   success: function(res){
				   if(res == "1"){
					   alert("更新成功");
					   window.location.href="list.do"; 
				   }
				   if(res == "0"){
					   alert("更新失败");
				   }
				   if(res == "3"){
					   alert("需要更改的职位还未添加，请到更多职位中添加修改！");
				   }
			   }
			});
	}
}


/*
 * 验证两次密码
 */
function passwordVerify(){
	var nPwd = $("#newPassword").val();
	var cPwd = $("#confirmPassword").val();
	if(nPwd != cPwd){
		$("#passwordVerify").text("两次密码不一致，请重新输入").css({"color":"red"});
		globalVariable = 0;
	}else{
		$("#passwordVerify").empty();
		globalVariable = 1;
	}
	
}

/*
 * 后退
 */
function goBack(){
	window.location.href="list.do"; 
}
/*
 * 添加职位
 */
function newPosition(){

	var position = $("#newPosition").val();
	var from_data = {
			 userId:$("#userId").val(),
			 position:position
	 };
	
	 var res = "0";
	 var url = "newUserPostion.do";
	 if(position!=""){
		 $.ajax({
			   type: "POST",
			   url: url,
			   data: from_data,
			   success: function(res){
				   if(res == 1){
					   alert("添加成功");
					   location.reload();
				   }
				   if(res == 0){
					   alert("添加失败");
				   }
			   }
			});
	 }else{
		 alert("不能添加空的职位!");
	 }
	
}

/*
 * 删除职位
 */
function deletePosition(obj,p){
	var from_data = {
			 userId:$("#userId").val(),
			 oppId:obj,
			 param:p
	 };
	 var res = "0";
	 var url = "deleteUserPostion.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == 1){
				   alert("删除成功");
				   location.reload();
			   }
			   if(res == 0){
				   alert("删除失败");
			   }
		   }
		});
}

/*
 * 更改主职位
 */
function updatePrimaryPosition(obj){
	var from_data = {
			 userId:$("#userId").val(),
			 oppId:obj,
	 };
	 var res = "0";
	 var url = "updatePrimaryPosition.do";
	$.ajax({
		   type: "POST",
		   url: url,
		   data: from_data,
		   success: function(res){
			   if(res == 1){
				   location.reload();
			   }
			   if(res == 0){
				   alert("设置失败");
			   }
		   }
		});
}