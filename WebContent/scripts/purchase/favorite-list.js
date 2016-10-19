/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/12/8
 * Description: Favorite list initializer and handlers.
 */
var FavoriteList = function(){
	// initialize events function for all elements.
    var initFavoriteList = function() {
    	
    	if (jQuery().dataTable) {
    		initFavoriteItemsTable();
	    }
    	
    	$('body').on('click', '.checkboxes', function() {
            var table = document.getElementById("favorite-items");
			if(table){
				checkDelete(table);
			}			
        });
		$('body').on('click', '.group-checkable', function() {
			var table = document.getElementById("favorite-items");
            if(table){
            	checkDelete(table);
			}
        });
		
		$('#del-confirm').click(function() {
			deleteRecords();
	    });
    	
    }
 // Initialize favorite items table.  
    var initFavoriteItemsTable = function () {

        var table = $('#favorite-items');

        // begin first table
        table.dataTable({
            
        	"bPaginate": false,
        	"bInfo": false,
        	"bFilter": false,
        	"bSort": false
        });

        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });

        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
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
    }
    //delete action
    var deleteRecords = function() {

		var table=document.getElementById("favorite-items");
		var ids="";
		
		for(var i=0;i<table.rows.length;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				if(ids == ""){
					ids = table.rows[i].cells[0].id;
				}else{
					ids = ids + "," + table.rows[i].cells[0].id;
				}
				table.deleteRow(i);
				i--;
	        }
		}
		
		$.ajax({
            type: "GET",
            url: "../favorite/delete.do",
            data: {ids:ids},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
		
		setTimeout(function(){
			window.location.reload();
		},2000);
    }
    
	return {
		init: function(){
			
			initFavoriteList();
			
		},
		
	};

}();