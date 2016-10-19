/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/6
 * Description: Common events handlers for POR list.
 */
var PORCommonEvents = function(){
	// initialize events function for all elements.
    var initCommonEvents = function() {
    	
    	$('a.reload').click(function() {
	    	window.location.reload();
	    });
    	
    	$('body').on('click', '.checkboxes', function() {
            var table1 = document.getElementById("sample_1");
            var table2 = document.getElementById("playlistItems");
			if(table1){
				checkDelete(table1);
			}
            if(table2){
            	checkDelete(table2);
            }				
        });
		$('body').on('click', '.group-checkable', function() {
			var table1 = document.getElementById("sample_1");
            var table2 = document.getElementById("playlistItems");
            if(table1){
				checkDelete(table1);
			}
            if(table2){
            	checkDelete(table2);
            }
        });
		
		$('#del-confirm').click(function() {
			deleteRecords();
	    });
		
		$('#update-confirm').click(function() {
			updateRecords();
	    });
    }
    // display delete option if any checked.
    var checkDelete = function(table) {

		var len=table.rows.length;
		var checked=false;
		
		for(var i=0;i<len;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				checked = true;
				break;
	        }
		}
		if(checked){
			$('#delete_option').show();
		}else{
			$('#delete_option').hide();
		}
    };
    //delete action
    var deleteRecords = function() {

		var table=document.getElementById("sample_1");
		var ids="";
		
		for(var i=0;i<table.rows.length;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				if(ids == ""){
					ids = table.rows[i].cells[1].id;
				}else{
					ids = ids + "," + table.rows[i].cells[1].id;
				}
				table.deleteRow(i);
				i--;
	        }
		}
		
		$.ajax({
            type: "GET",
            url: "delete.do",
            data: {ids:ids},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
		
		setTimeout(function(){
			window.location.reload();
		},2000);
    };
  //update action
    var updateRecords = function() {
		
		$.ajax({
            type: "POST",
            url: "update.do",
            data: $("#update_form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 $('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
		
		setTimeout(function(){
			window.location.reload();
		},2000);
    };
    
	return {
		init: function(){
			
			initCommonEvents();
			
		}
		
	};

}();