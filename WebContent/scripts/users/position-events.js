/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2016/01/04
 * Description: Position events handlers.
 */
var PositionEvents = function(){
	/**
	 * 初始化ContactEvents
	 */
	var initPositionEvents = function(){
		//保存事件
		$('#save').click(function(){
			//保存权限
			saveAuth();
		});
		//选择权限组
		$(':checkbox').click(function(){
			checkboxClick(this);
			
		});
		//全选
		$('input[id="all"]').click(function(){
			checkAll();
		});
		//加载职位权限
		loadAvailAuth();
		
	}
	
	/**
	 * 全选
	 */
	var checkAll = function(){
		var id = $('input')
		if($('input[id="all"]').attr("checked") == "checked"){
			$('.checker').find("span").attr("class", "checked");
    		$('input[name="ca"]').attr("checked", true);
		}else{
			$('.checker').find("span").removeAttr("class");
    		$('input[name="ca"]').attr("checked", false);
		}
		
	}
	
	/**
	 * 选择权限组
	 */
	var checkboxClick = function(obj){
		if($(obj).is(':checked') == true){
			var from_data = {
	    			id : $(obj).prop('id')
	    	 };
	    	$.ajax({
	            type: "GET",
	            url: "../auth/checkboxClick.do",
	            data: from_data,
	            dataType: "text",
	            success: function (result) {	
	            	
	            	var responseJson = eval ("(" + result + ")");
	            	for(var i=0;i<responseJson.length;i++){
	            		var auth = responseJson[i];
	            		$("#uniform-"+auth.id+"").find("span").attr("class", "checked");
	            		$("#"+auth.id+"").attr("checked", true);
	                }
	            },
	            error: function(xhr, textStatus, thrownError) {
	           	 alert(thrownError);
	            }
	        });
		}else{
			var from_data = {
	    			id : $(obj).prop('id')
	    	 };
	    	$.ajax({
	            type: "GET",
	            url: "../auth/checkboxClick.do",
	            data: from_data,
	            dataType: "text",
	            success: function (result) {	
	            	
	            	var responseJson = eval ("(" + result + ")");
	            	for(var i=0;i<responseJson.length;i++){
	            		var auth = responseJson[i];
	            		$("#uniform-"+auth.id+"").find("span").removeAttr("class");
	            		$("#"+auth.id+"").attr("checked", false);
	                }
	            },
	            error: function(xhr, textStatus, thrownError) {
	           	 alert(thrownError);
	            }
	        });
		}
		
	}
	
	/**
	 * 保存权限
	 */
	var saveAuth = function(){
		var ids = "";
		var name = document.getElementsByName("ca");
		for(var i=0;i<name.length;i++){
			if(name[i].checked){
				ids += name[i].value+",";
			}
		}
		var from_data = {
    			ids : ids,
    			id : $('#positionId').val()
    	 };
    	$.ajax({
			type: "POST",
			url: "../auth/saveAuth.do",
			data: from_data,
			success: function(res){ 
				if(res == 1){
					alert("保存成功");
					window.location.href="../user/position.do"; 
			    }
				if(res == 0){
					alert("保存失败");
				}
			},
			error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
		});
	}
	
	/**
	 * 加载职位拥有的权限
	 */
	var loadAvailAuth = function(){
		var from_data = {
    			id : $('#positionId').val()
    	 };
    	$.ajax({
            type: "GET",
            url: "../auth/loadAvailAuth.do",
            data: from_data,
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	
            	for(var i=0;i<responseJson.length;i++){
            		var auth = responseJson[i];
            		$("#uniform-"+auth.perm_id+"").find("span").attr("class", "checked");
            		$("#"+auth.perm_id+"").attr("checked", true);
                }
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
	}
	
	return {
		init:function(){
			initPositionEvents();
		}
	}
}();