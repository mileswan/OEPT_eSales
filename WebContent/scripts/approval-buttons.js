/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/2/22
 * Description: Initialize approval buttons.
 */
var ApprovalButtons = function(){
	// initialize events function for all elements.
    var initOrderApprovalButtons = function() {
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
    	
    	if(readOnlyFlag=='false'){
    		var buttons_content = '<div class="col-md-4"></div> \
						<div class="col-md-4">\
							<a class="btn btn-lg green m-icon-big approve-order">\
								审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a>\
						</div>\
						<div>\
						<a href="#" class="btn btn-lg red m-icon-big reject-order">\
						拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
						</a>\
						</div>';
    	}else{
    		var buttons_content = '<div class="col-md-4"></div> \
						<div class="col-md-4">\
							<a class="btn btn-lg dark m-icon-big">\
								审核通过 <i class="m-icon-big-swapup m-icon-white"></i>\
							</a>\
						</div>\
						<div>\
							<a href="#" class="btn btn-lg dark m-icon-big">\
								拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
							</a>\
						</div>';
    	}
    	
    	
    	$('div.approval-buttons').html(buttons_content);
    	
    }
 // initialize events function for all elements.
    var initReqApprovalButtons = function() {
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
    	
    	if(readOnlyFlag=='false'){
    		var buttons_content = '<div class="col-md-4"></div> \
						<div class="col-md-4">\
							<a class="btn btn-lg green m-icon-big approve-req">\
								审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a>\
						</div>\
						<div>\
						<a href="#" class="btn btn-lg red m-icon-big reject-req">\
						拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
						</a>\
						</div>';
    	}else{
    		var buttons_content = '<div class="col-md-4"></div> \
						<div class="col-md-4">\
							<a class="btn btn-lg dark m-icon-big">\
								审核通过 <i class="m-icon-big-swapup m-icon-white"></i>\
							</a>\
						</div>\
						<div>\
							<a href="#" class="btn btn-lg dark m-icon-big">\
								拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
							</a>\
						</div>';
    	}
    	
    	
    	$('div.approval-buttons').html(buttons_content);
    	
    }
 // initialize events function for all elements.
    var initProdApprovalButtons = function() {
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
    	
    	if(readOnlyFlag=='false'){
    		var buttons_content = '<div class="col-md-2"></div>\
				<div class="col-md-2">\
    			<a class="btn btn-lg green m-icon-big approve-prod">\
    			审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a>\
    			</div>\
    			<div>\
    			<a href="#" class="btn btn-lg red m-icon-big reject-prod">\
    			拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
    			</a>\
    			</div>';
    	}else{
    		var buttons_content = '<div class="col-md-2"></div>\
				<div class="col-md-2">\
    			<a class="btn btn-lg dark m-icon-big">\
    			审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a>\
    			</div>\
    			<div>\
    			<a href="#" class="btn btn-lg dark m-icon-big">\
    			拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
    			</a>\
    			</div>';
    	}
    	
    	
    	$('div.approval-buttons').html(buttons_content);
    	
    }
    
    var initAccntApprovalButtons = function() {
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
    	
    	if(readOnlyFlag=='false'){
    		var buttons_content = '<div class="col-md-2"></div>\
				<div class="col-md-2">\
    			<a class="btn btn-lg green m-icon-big approve-account">\
    			审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a>\
    			</div>\
    			<div>\
    			<a href="#" class="btn btn-lg red m-icon-big reject-account">\
    			拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
    			</a>\
    			</div>';
    	}else{
    		var buttons_content = '<div class="col-md-2"></div>\
				<div class="col-md-2">\
    			<a class="btn btn-lg dark m-icon-big">\
    			审核通过  <i class="m-icon-big-swapup m-icon-white"></i></a>\
    			</div>\
    			<div>\
    			<a href="#" class="btn btn-lg dark m-icon-big">\
    			拒绝<i class="m-icon-big-swapdown m-icon-white"></i>\
    			</a>\
    			</div>';
    	}
    	
    	
    	$('div.approval-buttons').html(buttons_content);
    	
    }
    
    
	return {
	
		initOrder: function(){
			
			initOrderApprovalButtons();
		},
		initReq: function(){
			
			initReqApprovalButtons();
		},
		initProd: function(){
			
			initProdApprovalButtons();
		},
		initAccnt: function(){
			initAccntApprovalButtons();
		}
		
	};

}();