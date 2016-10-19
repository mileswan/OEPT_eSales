/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/02/03
 * Description: Stock in requisition approval view events handlers.
 */
var StockInApprovalViewApprovalEvents = function(){
	// initialize events function for all elements.
    var initStockInApprovalEvents = function() {
    	
    	$('a.approve-req').click(function() {
    		updateReqApprovalStatus("submit - approved");
    	});
    	$('a.reject-req').click(function() {
    		updateReqApprovalStatus("submit - rejected");
    	});
    	
//    	$('a.generate-req').click(function() {
//    		generateReq();
//    	});
    }
    //update requisition approval status
    var updateReqApprovalStatus = function(status_code) {
    	var reqId = $('input[name="req[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateReqStatus.do",
            data: {status_code:status_code,
            	type_code:"Stock In Requisition",
            	reqId:reqId},
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
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            },
            beforeSend:function(){
            	//这里是开始执行方法，显示效果，效果自己写
            	$('div.approval-content').html('<div class="col-md-12">请求处理中，请耐心等候……</div>');
            	$('div.approval-content').append('<img src="../img/loading.gif">');
            	$('html, body').animate({scrollTop:0}, 'slow');
            }
        });
    }
    
	return {
		init: function(){
			
			initStockInApprovalEvents();
			
		},
		
	};

}();