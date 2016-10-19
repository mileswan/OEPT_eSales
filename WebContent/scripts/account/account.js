/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/22
 * Description: Account management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */
var catId;		//类id
var aId;		//单位id
function detail(obj){
	catId = obj;
	aId = null;
	var from_data = {
			catId : obj
	 };
	var url = "loadAccount.do";
	$.ajax({
        type: "GET",
        url: url,
        data: from_data,
        dataType: "text",
        success: function (result) {	
        	var responseJson = eval ("(" + result + ")");
        	var tableData = "";
        	var tableWrapper = '<table class="table table-bordered table-hover" style=" position: relative; z-index: 1;" id="m-table-b-t"> \
						<thead> \
        				<tr>\
						<th>行号</th>\
						<th>单位编号</th>\
						<th>单位名称</th>\
						<th>区域</th>\
						<th>类型</th>\
        				<th>操作</th>\
        				</tr>\
						</thead>\
						<tbody></tbody> \
						</table>';
        	$("#m-table-b").find(".table-scrollable").html(tableWrapper);
        	for(var i=0;i<responseJson.length;i++){
        		var account = responseJson[i];
        		if(account.accountDesc == null){
        			account.accountDesc = "";
        		}
        		if(account.accountNumber == null){
        			account.accountNumber = "";
        		}
        		if(account.workphone == null){
        			account.workphone = "";
        		}
        		if(account.email == null){
        			account.email = "";
        		}
        		if(account.accountType == "客户"){
        			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
					<td>'+(i+1)+'</td>\
					<td>'+account.accountNumber+'</td>\
					<td>'+account.accountName+'</td>\
					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
					<td><span class="label label-sm label-success">'+account.accountType+'</span></td>\
					<td><a>查看</a></td>\
					</tr>';
        		}
//        		else if(account.accountType == "供应商"){
//        			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
//					<td>'+(i+1)+'</td>\
//					<td>'+account.accountNumber+'</td>\
//					<td>'+account.accountName+'</td>\
//					<td>Tel：'+account.workphone+'</td>\
//					<td>'+account.addressName+'</td>\
//					<td>'+account.email+'</td>\
//					<td><span class="label label-sm label-warning">'+account.accountType+'</span></td>\
//					<td>'+account.accountDesc+'</td>\
//					</tr>';
//        		}
        		
        		
        		$("table#m-table-b-t").find("tbody").append(tableData);
            }
        },
        error: function(xhr, textStatus, thrownError) {
       	 alert(thrownError);
        }
    });
}


//查询所有产品
function allProd(){
	//
	location.reload();
}

//跳转删除类别action
function delCategory(){
	var from_data = {
			id : catId
	 };
	var url = "../prodadmin/deleteCategory.do";
	$.ajax({
		type: "GET",
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

/**
 * 新增选择父类名称
 */
function selectCname(){
	var url = "selectCname.do";
	$.ajax({
		type: "POST",
		url: url,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("acpName").value = dataObj.catId;
		}
	});
}

/**
 * 添加单位节点
 */
function addAccountCat(){
	var from_data = {
			catId : $("#acpName").val(),
			catName : $("#acsName").val()
	 };
	var url = "addAccountCat.do";
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
}

/**
 * 查询节点信息
 */
function selectAcDe(){
	var from_data = {
			catId : catId
	 };
	var url = "selectAcDe.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("acpName2").value = dataObj.catParNameId;
			document.getElementById("acsName2").value = dataObj.catName;
			document.getElementById("dataNone").value = dataObj.catId;
		}
	});
}

/**
 * 更改单位节点
 */
function updateAccountCat(){
	var from_data = {
			catId : $("#acpName2").val(),
			catName : $("#acsName2").val(),
			id : $("#dataNone").val()
	 };
	var url = "updateAccountCat.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("更改成功");
				location.reload();
		    }
			if(res == 0){
				alert("更改失败");
			}
		}
	});
}

/**
 * 删除单位tree node
 */
function deleteAcNode(){
	var from_data = {
			catId : catId
	 };
	var url = "deleteAcNode.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("删除成功！");
				location.reload();
		    }
			if(res == 0){
				alert("删除失败！");
			}
		}
	});
}

/**
 * 选中指定行
 * @param obj
 */
function check(obj){
	if(aId == null){
		document.getElementById("a"+obj).style.backgroundColor = "#f5f5f5";
		aId = obj;
	}else if(aId != obj){
		document.getElementById("a"+obj).style.backgroundColor = "#f5f5f5";
		document.getElementById("a"+aId).style.backgroundColor = "#ffffff";
		aId = obj;
	}
	
}

/**
 * 编辑单位信息
 */
function editAt(){
	if(aId == null){
		alert("请先选中一条信息！");
	}else{
	document.getElementById("m-edit").style.display = "block";
	document.getElementById("m-add").style.display = "none";
	var from_data = {
			aId : aId
	 };
	var url = "editAt.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("aId2").value = dataObj.accountId;
			document.getElementById("aName2").value = dataObj.accountName;
			document.getElementById("aNumber2").value = dataObj.accountNumber;
			document.getElementById("aType2").value = dataObj.accountType;
			document.getElementById("aTel2").value = dataObj.aTel;
			document.getElementById("aFax2").value = dataObj.aFax;
			document.getElementById("aEmail2").value = dataObj.aEmail;
			document.getElementById("aDesc2").value = dataObj.aDesc;
			$('input[name="address2"]').val(dataObj.pAddr);
			$('input[name="shipAddress2"]').val(dataObj.pAddr);
			$('input[name="billAddress2"]').val(dataObj.pAddr);
			$('input[name="address2"]').attr('id',dataObj.pAddrId);
			$('input[name="shipAddress2"]').attr('id',dataObj.pShipAddrId);
			$('input[name="billAddress2"]').attr('id',dataObj.pBillAddrId);
		}
	});
	}
}

/**
 * 保存编辑
 */
function saveEdit(){
	var from_data = {
			aName : $("#aName2").val(),
			aNumber : $("#aNumber2").val(),
			aType : $("#aType2").val(),
			aTel : $("#aTel2").val(),
			aAddress : $('input[name="address2"]').attr('id'),
			aFax : $("#aFax2").val(),
			aEmail : $("#aEmail2").val(),
			aDesc : $("#aDesc2").val(),
			shipAddr : $('input[name="shipAddress2"]').attr('id'),
			billAddr : $('input[name="billAddress2"]').attr('id'),
			catId : catId,
			aId : aId
	 };
	var url = "saveEdit.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("保存成功");
				detail(catId);
				document.getElementById("m-add").style.display = "none";
				document.getElementById("m-edit").style.display = "none";
		    }
			if(res == 0){
				alert("保存失败");
			}
		}
	});
}

/**
 * 添加单位信息
 */
function addAt(){
	document.getElementById("m-add").style.display = "block";
	document.getElementById("m-edit").style.display = "none";
}

/**
 * 保存添加
 */
function saveAdd(){
	var from_data = {
			aName : $("#aName").val(),
			aNumber : $("#aNumber").val(),
			aType : $("#aType").val(),
			aAddrDet : $("#aAddrDet").val(),
			aAddress : $('input[name="address"]').attr('id'),
			aFax : $("#aFax").val(),
			aEmail : $("#aEmail").val(),
			aDesc : $("#aDesc").val(),
			aZipcode : $('input[name="aZipcode"]').attr('id'),
			aAddrDet : $('input[name="aAddrDet"]').attr('id'),
			catId : catId
	 };
	var url = "saveAdd.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("保存成功");
				detail(catId);
				document.getElementById("m-add").style.display = "none";
				document.getElementById("m-edit").style.display = "none";
		    }
			if(res == 0){
				alert("保存失败");
			}
		}
	});
}

/**
 * 删除单位信息
 */
function deleteAt(){
	if(aId == null){
		alert("请先选中一条信息！");
	}else{
		var from_data = {
				aId : aId
		 };
		var url = "deleteAt.do";
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
}


/**
 * 关闭窗口
 */
function closeViwe(){
	document.getElementById("m-add").style.display = "none";
	document.getElementById("m-edit").style.display = "none";
}