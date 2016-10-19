/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/10
 * Description: System settings events handlers.
 */
var SystemSettingsEvents = function(){
	// initialize events function for all elements.
    var initSystemSettingsEvents = function() {
    	
    	$('#updateEmail-confirm').click(function() {
    		updateEmailPreferences();
	    });
    	
    	$('#updateDefaultSettings-confirm').click(function() {
    		updateDefaultPreferences();
	    });
    	
    	if($('input[name="mail_available"]').is(":checked")){
    		$('.all-email-settings').show();
    	}else{
    		$('.all-email-settings').hide();
    	}
    	$('input[name="mail_available"]').change(function () {
            var checked = jQuery(this).is(":checked");
            if (checked) {
            	$('.all-email-settings').show();
            } else {
            	$('.all-email-settings').hide();
            }
        });
    	
    	if($('input[name="mail_smtp_auth"]').is(":checked")){
    		$('input[name="mail_username"]').removeAttr('readonly');
        	$('input[name="mail_password"]').removeAttr('readonly');
    	}else{
    		$('input[name="mail_username"]').attr('readonly','readonly');
        	$('input[name="mail_password"]').attr('readonly','readonly');
    	}
    	$('input[name="mail_smtp_auth"]').change(function () {
            var checked = jQuery(this).is(":checked");
            if (checked) {
            	$('input[name="mail_username"]').removeAttr('readonly');
            	$('input[name="mail_password"]').removeAttr('readonly');
            } else {
            	$('input[name="mail_username"]').attr('readonly','readonly');
            	$('input[name="mail_password"]').attr('readonly','readonly');
            }
        });
    	
    	$(".touchspin_percent").TouchSpin({
    		buttondown_class: 'btn blue',
    		buttonup_class: 'btn blue',
    		min: 0,
    		max: 100,
    		step: 0.1,
    		decimals: 2,
    		boostat: 5,
    		maxboostedstep: 10,
    		postfix: '%'
    	});
    }
    
    var updateEmailPreferences = function() {
			
		$.ajax({
            type: "POST",
            url: "updateEmailSettings.do",
            data: $('.email-settings-form').serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
   	   	 	 	$('.alert-success span').text(responseJson.objname+"已成功更新！");
   	   	 	 	$('.alert-success').show();
            	setTimeout(function(){
            		$('.alert-success').fadeOut();
        		},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
    var updateDefaultPreferences = function() {
		
		$.ajax({
            type: "POST",
            url: "updateDefaultSettings.do",
            data: $('.default-settings-form').serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
   	   	 	 	$('.alert-success span').text(responseJson.objname+"已成功更新！");
   	   	 	 	$('.alert-success').show();
            	setTimeout(function(){
            		$('.alert-success').fadeOut();
        		},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
	return {
		init: function(){
			
			initSystemSettingsEvents();
			
		}
		
	};

}();