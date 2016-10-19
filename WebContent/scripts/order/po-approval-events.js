/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/02/02
 * Description: Purchase Orders approval view events handlers.
 */
var POViewApprovalEvents = function(){
	// initialize events function for all elements.
    var initPOApprovalEvents = function() {
    	
    	$('a.approve-order').click(function() {
    		//审核通过之前先保存
    		var comm = $('textarea[name="order[description]"]').val();
    		updateOrderInfo(null,null,null,comm);
    		updateOrderApprovalStatus("submit - approved");
    	});
    	$('a.reject-order').click(function() {
    		updateOrderApprovalStatus("submit - rejected");
    	});
    	
//    	$('a.generate-req').click(function() {
//    		generateReq();
//    	});
    }
    //update order approval status
    var updateOrderApprovalStatus = function(status_code) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateOrderStatus.do",
            data: {status_code:status_code,
            	type_code:"Purchase Order",
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		$('html, body').animate({scrollTop:0}, 500);
            		$('.alert-success span').text(responseJson.objname+"已成功！");
       	   	 	 	$('.alert-success').show();
       	   	 	 	setTimeout(function(){
       	   	 	 		$('.alert-success').fadeOut();
       	   	 	 		window.location.reload();
       	   	 	 	},2000);
       	   	 	 	
            	}else{
            		alert(responseJson.errmsg);
            		window.location.reload();
            	}
            },
            error: function(xhr, textStatus, thrownError) {
            	alert(thrownError);
            	window.location.reload();
            },
            beforeSend:function(){
            	//这里是开始执行方法，显示效果，效果自己写
            	$('div.approval-content').html('<div class="col-md-12">请求处理中，请耐心等候……</div>');
            	$('div.approval-content').append('<img src="../img/loading.gif">');
            	$('html, body').animate({scrollTop:0}, 'slow');
            },
            complete:function(){
            	//方法执行完毕，效果自己可以关闭，或者隐藏效果
            }
        });
    }
    
    //update purchase order info
    var updateOrderInfo = function(supplier_id,delivery_wh_id,owner_id,comment) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updatePORInfo.do",
            data: {supplier_id:supplier_id,
            	delivery_wh_id:delivery_wh_id,
            	owner_id:owner_id,
            	comment:comment,
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		$('.alert-success span').text(responseJson.objname+"已成功更新！");
       	   	 	 	$('.alert-success').show();	
            	}else{
            		alert(responseJson.errmsg);
            	}
    	   	 	      	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
	return {
		init: function(){
			
			initPOApprovalEvents();
			
		},
		
	};

}();