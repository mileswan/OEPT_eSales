/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/11/23
 * Description: Personal address management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
/**
 * PIAddress.jsp
 */
/**
 * 提交数据
 */
function createAddress(){
	var from_data = {
			contactName:$("#contactName").val(),
			location_p:$("#location_p").val(),
			location_c:$("#location_c").val(),
			location_a:$("#location_a").val(),
			detailsAddress:$("#detailsAddress").val(),
			zipcode:$("#zipcode").val(),
			contactTel:$("#contactTel").val(),
	 };
	var url = "createAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == "1"){
				alert("保存成功");
				location.reload();
		    }
			if(res == "0"){
				alert("保存失败");
			}
		}
	});
}

/**
 * 提交数据
 */
function deleteAddress(params){
	var from_data = {
			addressId:params
	 };
	var url = "deleteAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == "1"){
				alert("删除成功");
				location.reload();
		    }
			if(res == "0"){
				alert("删除失败");
			}
		}
	});
}

/**
 * 提交数据
 */
function updateSelectAddress(params){
	document.getElementById("div_up").style.display = "none";
	document.getElementById("div_up2").style.display = "block";
	var from_data = {
			addressId:params
	 };
	var url = "updateSelectAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("contactName2").value = dataObj.osa_addr_contact_name;
			document.getElementById("detailsAddress2").value = dataObj.osa_addr_street;
			document.getElementById("zipcode2").value = dataObj.osa_addr_zipcode;
			document.getElementById("contactTel2").value = dataObj.osa_addr_contact_cell;
			document.getElementById("addressId2").value = dataObj.osa_addr_id;
			sheng = document.getElementById("location_p2").value = dataObj.osa_addr_province;
			shi = document.getElementById("location_c2").value = dataObj.osa_addr_city;
			qu = document.getElementById("location_a2").value = dataObj.osa_addr_country;
			//初始化select标签值
			new PCAS('location_p2', 'location_c2', 'location_a2', sheng, shi, qu);
		}
	});
}

/**
 * 提交数据
 */
function submitPersonalAddressUpdate(){
	var from_data = {
			contactName : $("#contactName2").val(),
			location_p : $("#location_p2").val(),
			location_c : $("#location_c2").val(),
			location_a : $("#location_a2").val(),
			detailsAddress : $("#detailsAddress2").val(),
			zipcode : $("#zipcode2").val(),
			contactTel : $("#contactTel2").val(),
			addressId : $("#addressId2").val()
	 };
	
	var url = "updateAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == "1"){
				alert("修改成功");
				location.reload();
		    }
			if(res == "0"){
				alert("修改失败");
			}
		}
	});
}

/**
 * 提交数据
 */
function defaultAddress(params){
	var from_data = {
			addressId:params
	 };
	
	var url = "defaultAddress.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == "1"){
				location.reload();
		    }
		}
	});
}

