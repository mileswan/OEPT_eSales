/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/1
 * Description: Position management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */

/**
 * 提交数据，创建职位
 */
function createPosition(){
	var from_data = {
			positionName:$("#positionName").val(),
			parentPosition:$("#parentSelect").val(),
	 };
	var url = "createPosition.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("保存成功");
				location.reload();
		    }
			if(res == 0){
				alert("保存失败");
			}
		}
	});
}



/**
 * 提交数据，职位详情
 */
function thisPosition(obj){
	document.getElementById("div_up").style.display = "none";
	document.getElementById("div_up2").style.display = "block";
	var from_data = {
			positionId:obj
	 };
	var url = "thisPosition.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			//解析json
			var dataObj = eval("("+res+")");
			document.getElementById("positionName2").value = dataObj.osa_position_name;
			document.getElementById("parentSelect2").value = dataObj.osa_parent_position_id;
			document.getElementById("positionId2").value = dataObj.osa_position_id;
		}
	});
}

/**
 * 提交数据，修改职位
 */
function updatePosition(){
	var from_data = {
			positionName:$("#positionName2").val(),
			parentPosition:$("#parentSelect2").val(),
			positionId:$("#positionId2").val()
	 };
	var url = "updatePosition.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("修改成功");
				location.reload();
		    }
			if(res == 0){
				alert("修改失败");
			}
		}
	});
}

var deleteId;
function deleteId(obj){
	alert(3);
	deleteId = obj;
}

/**
 * 提交数据，修改职位
 */
function deletePosition(){
	alert("delete999");
	if(deleteId!=null||deleteId!=""){
		var from_data = {
				positionId:deleteId
		 };
		var url = "deletePosition.do";
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
	}else{
		alert("没有找到需要删除的职位！");
	}
	
}

function aa(){
	alert(1);
}
