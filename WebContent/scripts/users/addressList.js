/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/18
 * Description: Address management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */

/**
 * 页面加载后加载
 */
window.onload = function() {
	var sheng = $("#sheng").val();
	var shi = $("#shi").val();
	var xian = $("#qu").val();
	if (sheng == "") {
		new PCAS('location_p', 'location_c', 'location_a', '北京', '', '');
	} else {
		new PCAS('location_p', 'location_c', 'location_a', sheng, shi, xian);
	}

	$.ajax({
		type : "POST",
		url : "ajaxUserNameList.do",
		success : function(usernames) {
			$("#tags1").autocomplete({
				source : usernames
			});
		}
	});
}

/**
 * 点击复选框显示删除按钮
 */
var checkboxNum = 0;
function checkboxClick(obj) {
	if (document.getElementById(obj).checked) {
		checkboxNum = checkboxNum + 1;
		document.getElementById("delectButton").style.display = "block";
	} else {
		checkboxNum = checkboxNum - 1;
		if (checkboxNum < 1) {
			document.getElementById("delectButton").style.display = "none";
		}
	}
}

/**
 * 全选复选框显示隐藏删除按钮
 * 
 * @param obj
 */
function checkboxAll(obj) {
	if (document.getElementById(obj).checked) {
		checkboxNum = 0;
		document.getElementById("delectButton").style.display = "block";
	} else {
		document.getElementById("delectButton").style.display = "none";
	}
}

/**
 * ajax提交数据
 */
function ajaxPostSubmitDelect() {
	var boxs = document.getElementsByName("checkbox");
	var val = new Array();
	var stringVal = "s";
	for (i = 0; i < boxs.length; i++) {
		if (boxs[i].checked == true) {
			stringVal = stringVal + boxs[i].value + "s"
		}
	}
	var from_data = {
		boxs : stringVal
	};
	var res = "0";
	var url = "delectAddress.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(result) {
			if (result == "1") {
				alert("删除成功");
				window.location.href = "address.do";
			}
			if (result == "0") {
				alert("删除失败");
			}
		}
	});
}

/**
 * ajax提交数据,更改地址信息
 */
function submitUpdateAddress() {
	var from_data = {
		contactName : $("#contactName").val(),
		location_p : $("#location_p").val(),
		location_c : $("#location_c").val(),
		location_a : $("#location_a").val(),
		detailsAddress : null,
		zipcode : $("#zipcode").val(),
		contactTel : $("#contactTel").val(),
		addressId : $("#addressId").val(),
		country:$("#country").val()
	};
	var url = "updateAddress.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == "1") {
				alert("保存成功");
				window.location.href = "address.do";
			}
			if (res == "0") {
				alert("保存失败");
			}
		}
	});
}

/**
 * ajax提交数据,分配地址
 */
function submitAllotAddress() {
	var from_data = {
		addressId : $("#addressId").val(),
		username : $("#tags1").val()
	};
	var url = "allotAddress.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == "1") {
				alert("分配成功");
				location.reload();
			}
			if (res == "0") {
				alert("分配失败");
			}
		}
	});
}

/**
 * ajax提交数据,删除已分配地址
 */
function deleteUserAddress(params1) {
	var from_data = {
		addressId : $("#addressId").val(),
		userId : params1
	};
	var url = "deleteUserAddr.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == "1") {
				alert("删除成功");
				location.reload();
			}
			if (res == "0") {
				alert("删除失败");
			}
		}
	});
}


/**
 * 提交数据 addressList  newAddress
 */
function newAddressSubmit(){
	var from_data = {
			contactName:$("#contactName").val(),
			location_p:$("#location_p").val(),
			location_c:$("#location_c").val(),
			location_a:$("#location_a").val(),
			detailsAddress:null,
			zipcode:$("#zipcode").val(),
			contactTel:$("#contactTel").val(),
			country:$("#country").val()
	 };
	var url = "newAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == "1"){
    			$('.alert-success span').text("保存成功！");
       	   	 	$('.alert-success').show();
     	   	 	setTimeout(function(){
     	   	 		$('.alert-success').fadeOut();
    				loadAvailAddr();
     			},2000);
				window.location.href="address.do"; 
		    }
			if(res == "0"){
				$('.alert-success span').text("保存失败！");
       	   	 	$('.alert-success').show();
     	   	 	setTimeout(function(){
     	   	 		$('.alert-success').fadeOut();
    				loadAvailAddr();
     			},2000);
			}
			if(res == "2"){
				$('.alert-success span').text("已经存在该区域，请重新选择！");
       	   	 	$('.alert-success').show();
     	   	 	setTimeout(function(){
     	   	 		$('.alert-success').fadeOut();
    				loadAvailAddr();
     			},2000);
			}
		}
	});
}
