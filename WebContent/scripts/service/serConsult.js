/**
 * @author zhujj
 * Version: 1.0
 * Date: 2015/12/2
 * Description: Service management events handlers.
 * Copyright (c) 2015 上海基辕科技有限公司版权所有.
 */

function showConsult() {
	document.getElementById("div_up").style.display = "block";
}

/**
 * 创建咨询服务申请
 */
function createConsultSer() {
	var from_data = {
		serName : $("#serName").val(),
		serDesc : $("#serDesc").val(),
		serType : $("#serType").val(),
		serDetail : $("#serDetail").val()
	};
	var url = "createConsultSer.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				alert("保存成功");
				document.getElementById("div_up").style.display = "none";
				location.reload();
			}
			if (res == 0) {
				alert("保存失败");
			}
		}
	});
}

/**
 * 创建咨询服务子订单
 */
function createConSerItem(obj) {
	var from_data = {
		serviceId : obj
	};
	var url = "createConSerItem.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				alert("受理成功");
				window.location.href = "consultDetails.do?serviceId=" + obj
						+ "";
			} else if (res == 0) {
				alert("受理失败");
			}
		}
	});

}

/**
 * 提交回复内容
 */
function submitReplyContent(obj) {
	var from_data = {
		replyContent : $("#replyContent").val(),
		serviceId : obj
	};
	var url = "replyContent.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				location.reload();
			}
		}
	});
}

/**
 * 更改订单状态(结束订单)
 * @param obj
 */
function updateServiceStatus(obj) {
	var from_data = {
		serviceId : obj
	};
	var url = "updateServiceStatus.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				alert("服务结束");
				window.location.href = "list.do?type=consult";
			}
			if (res == 0) {
				alert("服务结束失败");
			}
		}
	});
}

/**
 * 创建投诉订单
 */
function createComplaint(){
	var from_data = {
			orderId : $("#select_order").val(),
			compType : $("#select_comp").val(),
			serDesc : $("#serDesc").val(),
			serDetail : $("#serDetail").val(),
			select_level : $("#select_level").val()
		};
		var url = "createComplaint.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if (res == 1) {
					alert("申请成功");
					location.reload();
				}
				if (res == 0) {
					alert("申请失败");
				}
			}
		});
}
/**
 * 回复投诉问题
 */
function replyComplaint(obj){
	var from_data = {
			replyComplaint : $("#replyComplaint").val(),
			serviceId : obj
		};
	var url = "replyComplaint.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				alert("回复成功");
				location.reload();
			}
			if (res == 0) {
				alert("回复失败");
			}
		}
	});
}

/**
 * 更改订单状态(结束订单，投诉)
 * @param obj
 */
function updateServiceStatusComp(obj) {
	var from_data = {
		serviceId : obj
	};
	var url = "updateServiceStatusComp.do";
	$.ajax({
		type : "POST",
		url : url,
		data : from_data,
		success : function(res) {
			if (res == 1) {
				alert("服务结束");
				window.location.href = "list.do?type=complaint";
			}
			if (res == 0) {
				alert("服务结束失败");
			}
		}
	});
}
/**
 * 选择查询
 * @param obj
 */
function selectQuery(obj){
	if(obj == "0"){
		
	}else{
		window.location.href = "selectQuery.do?level="+obj+"";
	}
}

/*
 * 退返货
 * ----------------------------------------------------------
 */


/**
 * 根据订单id查询金额
 * @param obj
 */
function selectAmt(obj){
if(obj != "0"){
	var from_data = {
			orderId : obj
		};
		var url = "selectAmt.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				//解析json
				var dataObj = eval("("+res+")");
				document.getElementById("returnAmt").value = dataObj.amt;
			}
		});	
	}
}

/**
 * 退货申请
 * @returns
 */
function returnApply(){
		var from_data = {
			orderId : $("#returnId").val(),
			returnDesc : $("#returnDesc").val(),
			returnComm : $("#returnComm").val()
		};
		var url = "returnApply.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("申请成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("申请失败");
				}
			}
		});	
}

/**
 * 返修申请
 * @returns
 */
function repairApply(){
		var from_data = {
			orderId : $("#repairId").val(),
			repairDesc : $("#repairDesc").val(),
			repairComm : $("#repairComm").val()
		};
		var url = "repairApply.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("申请成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("申请失败");
				}
			}
		});	
}

/**
 * 提交操作
 * @param obj
 */
function disposeReturn(obj,type){
	var from_data = {
			serviceId : obj,
			type : type
		};
		var url = "disposeReturn.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("处理成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("处理失败");
				}
			}
		});	
}

/**
 * 提交操作
 * @param obj
 */
function disposeReturn2(obj){
	var from_data = {
			serviceId : obj,
			type : $("#serStatus").val()
		};
		var url = "disposeReturn.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("处理成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("处理失败");
				}
			}
		});	
}

/**
 * 提交操作
 * @param obj
 */
function disposeReturn3(obj){
	var from_data = {
			serviceId : obj,
			type : $("#serStatus2").val()
		};
		var url = "disposeReturn2.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("处理成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("处理失败");
				}
			}
		});	
}

/**
 * 提交操作,客户已收货
 * @param obj
 */
function disposeReturn4(obj,obj2){
	var from_data = {
			serviceId : obj,
			type : obj2
		};
		var url = "disposeReturn2.do";
		$.ajax({
			type : "POST",
			url : url,
			data : from_data,
			success : function(res) {
				if(res == 1){
					alert("处理成功");
					window.location.href = "list.do?type=return";
				}
				if (res == 0) {
					alert("处理失败");
				}
			}
		});	
}

