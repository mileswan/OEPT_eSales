/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/27
 * Description: Shopping cart initializer and handlers.
 */
var ShoppingCart = function(){
	// initialize events function for all elements.
    var initShopcartEvents = function() {
    	
    	if (jQuery().dataTable) {
    		initShopcartItemsTable();
	    }
    	
    	var initValue = 0;
    	var quantity = 0;
    	var price = 0.0;
    	var amount = 0.0;
    	
    	$('.shop-quantity').each(function(){
    		
    		$(this).find('input.spinner-input').keyup(function() {
    			quantity = $(this).val();
    			price = $(this).parents('tr.gradeX').find('td.item-price').text() *1;
    			amount = quantity * price;
    	    	$(this).parents('tr.gradeX').find('td.item-amount').text(amount.toFixed(2));
    	    	var item_id = $(this).parents('tr.gradeX').find('td.item-id').attr('id');
    	    	calcTotalAmount( document.getElementById("shopcart-items") );
    	    	updateCurrentRow(item_id,quantity,price,amount);
    	    });
    		
    	    initValue =  $(this).find('span.item-quantity').text();
    		$(this).spinner({value:parseInt(initValue), step: 1, min: 0, max: 2000}).on( "change", function() {
    				$(this).find('input.spinner-input').keyup();
    			} );
    		
    	  });
    	
    	$('body').on('click', '.checkboxes', function() {
            var table1 = document.getElementById("shopcart-items");
			if(table1){
				checkDeleteAndOrder(table1);
				calcTotalAmount(table1);
			}			
        });
		$('body').on('click', '.group-checkable', function() {
			var table1 = document.getElementById("shopcart-items");
            if(table1){
            	checkDeleteAndOrder(table1);
            	calcTotalAmount(table1);
			}
        });
		
		$('#del-confirm').click(function() {
			deleteRecords();
	    });
		
		$('a.order-confirm').click(function() {
			generateOrder();
	    });
    	
    }
 // Initialize shopping cart items table.  
    var initShopcartItemsTable = function () {

        var table = $('#shopcart-items');

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
    // Caculate and display total amount of any checked.
    var calcTotalAmount = function(table) {

		var len=table.rows.length - 1;
		var amount=0;
		
		for(var i=1;i<len;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				var currentAmount = table.rows[i].cells[5].innerText * 1
				amount = amount + currentAmount;
	        }
		}
		$('th.total-amount').text(amount.toFixed(2)+"（元）");
		$('span.total-amount').text(amount.toFixed(2)+"（元）");
    }
    // display delete and order option if any checked.
    var checkDeleteAndOrder = function(table) {

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
			$('.order_option').show();
			$('span.total-amount').show();
		}else{
			$('.order_option').hide();
			$('#delete_option').hide();
			$('span.total-amount').hide();
		}
    }
    //delete action
    var deleteRecords = function() {

		var table=document.getElementById("shopcart-items");
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
    }
    //update action
    var updateCurrentRow = function(item_id,quantity,price,amount) {
		
		$.ajax({
            type: "POST",
            url: "update.do",
            data: {item_id:item_id,
            	quantity:quantity,
            	price:price,
            	amount:amount},
            dataType: "text",
            success: function (result) {	
//            	var responseJson = eval ("(" + result + ")");
//    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
//    	   	 	 $('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    //Generate order base on shopping cart items
    var generateOrder = function() {

		var table=document.getElementById("shopcart-items");
		var item_ids="";
		
		for(var i=0;i<table.rows.length;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				if(item_ids == ""){
					item_ids = table.rows[i].cells[0].id;
				}else{
					item_ids = item_ids + "," + table.rows[i].cells[0].id;
				}
	        }
		}
		
		$.ajax({
            type: "GET",
            url: "generateOrder.do",
            data: {item_ids:item_ids},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	window.location.href = '../order/details.do?id='+responseJson.order_id;
    	   	 	 //$('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 //$('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
	return {
		init: function(){
			
			initShopcartEvents();
			
		},
		
	};

}();