/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/18
 * Description: User management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */

/*
 * 点击复选框显示删除按钮
 */
var checkboxNum = 0;
function checkboxClick(obj){
	if(document.getElementById(obj).checked){
		checkboxNum = checkboxNum+1;
		document.getElementById("delectButton").style.display = "block";
	}else{
		checkboxNum = checkboxNum-1;
		if(checkboxNum<1){
		document.getElementById("delectButton").style.display = "none";
		}
	}
}

/**
 * 全选复选框显示隐藏删除按钮
 * @param obj
 */
function checkboxAll(obj){
	if(document.getElementById(obj).checked){
		checkboxNum = 0;
		document.getElementById("delectButton").style.display = "block";
	}else{
		document.getElementById("delectButton").style.display = "none";
	}
}

/**
 * ajax提交数据
 */
function ajaxPostSubmitDelect(){
	var boxs = document.getElementsByName("checkbox");
	var val = new Array();  
	var stringVal = "s";
	for(i=0;i<boxs.length;i++){
		if(boxs[i].checked == true){
			stringVal = stringVal + boxs[i].value + "s"
		}
	}
	var from_data = {
		 boxs:stringVal
	};
	var res = "0";
	var url = "delect.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(result){
			if(result == "1"){
				alert("删除成功");
				window.location.href="list.do"; 
			}
			if(result == "0"){
				alert("删除失败");
			}
		}
	});
}