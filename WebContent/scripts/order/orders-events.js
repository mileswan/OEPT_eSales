/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/30
 * Description: Orders events handlers.
 */
var OrdersEvents = function(){
	// initialize events function for all elements.
    var initOrdersEvents = function() {
    	
    	$('#new-order-confirm').click(function() {
    		addOrder();
	    });
    	
    	$('button.query-assistant').click(function() {
    		$("tr.filter").show();
    		$(this).hide();
    		$('button.query-cancel').show();
	    });
    	$('button.query-cancel').click(function() {
    		$("tr.filter").hide();
    		$(this).hide();
    		$('button.query-assistant').show();
	    });
    	
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	
    	$("button.back").click(function() {
	    	window.location.href='list.do';
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
    	
    	initNewItemEvents();
    	initEditItemEvents();
    	 
    	$('a.product-pick').each(function() {
    		$(this).click(function(){
    			loadAvailProd();
    		});	
    	});
    	$('button.product-pick').click(function() {
    		var table=document.getElementById("product_pick");
    		var id="";
    		var name="";
    		var price=0;

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				name = table.rows[i].cells[2].innerText;
    				price = table.rows[i].cells[5].innerText;
    			}
    		}
    		$('input[name="product[id]"]').val(id);
    		$('input[name="product[name]"]').val(name);
    		$('input[name="product[price]"]').val(price);
    		updateNewItemData();
    		updateEditItemData();
    	});
    	
    	$('a.bill-address-pick').click(function(){
    			loadAvailBillAddr();
    	});
    	$('button.bill-address-pick').click(function() {
    		var table=document.getElementById("bill-address-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
    				id = table.rows[i].cells[1].id;
    				var addressInfo = '联系人：'+table.rows[i].cells[1].innerText + '<br> \
    				电话号码：'+table.rows[i].cells[2].innerText + '<br> \
    				'+table.rows[i].cells[3].innerText+'<br> \
    				'+table.rows[i].cells[4].innerText+'<br> \
    				'+table.rows[i].cells[5].innerText+'<br> \
    				'+table.rows[i].cells[6].innerText+'<br> \
    				'+table.rows[i].cells[7].innerText+'<br>';
    				$('div.bill-address-info').html(addressInfo);
    				break;
    			}
    		}
    		$('span.bill-address-id').text(id);
    		updateAddr(id,"bill-address");
    	});
    	
    	$('a.ship-address-pick').click(function(){
			loadAvailShipAddr();
    	});
    	$('button.ship-address-pick').click(function() {
    		var table=document.getElementById("ship-address-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var addressInfo = '联系人：'+table.rows[i].cells[1].innerText + '<br> \
    				电话号码：'+table.rows[i].cells[2].innerText + '<br> \
    				'+table.rows[i].cells[3].innerText+'<br> \
    				'+table.rows[i].cells[4].innerText+'<br> \
    				'+table.rows[i].cells[5].innerText+'<br> \
    				'+table.rows[i].cells[6].innerText+'<br> \
    				'+table.rows[i].cells[7].innerText+'<br>';
    				$('div.ship-address-info').html(addressInfo);
    				break;
    			}
    		}
    		$('span.ship-address-id').text(id);
    		updateAddr(id,"ship-address");
    	});
    	
    	$('button.add-item').click(function() {
    		addOrderItem();
    	});
    	$('button.edit-item').click(function() {
    		editOrderItem();
    	});
    	$('button.delete-confirm').click(function() {
    		var item_id = $('input[name="orderItem[id]"]').val();
    		delOrderItem(item_id);
    	});
    	
    	$('a.hold-order').click(function() {
    		updateOrderStatus("hold");
    	});
    	$('a.cancel-order').click(function() {
    		updateOrderStatus("cancel");
    	});
    	$('a.close-order').click(function() {
    		updateOrderStatus("closed");
    	});
    	$('a.submit-order').click(function() {
    		updateOrderStatus("submit");
    	});
    }
    //initialize add item events
    var initNewItemEvents = function() {
    	$('#add-item').find(".purchase-quantity").spinner().on( "change", function() {
    		updateNewItemData();
		});
    	$('#add-item').find(".purchase-quantity").spinner().on( "keyup", function() {
    		updateNewItemData();
		});	
    	$('#add-item').find("input[name='tax_percent']").on( "change", function() {
    		updateNewItemData();
		});
    	$('#add-item').find("input[name='tax_percent']").on( "keyup", function() {
    		updateNewItemData();
		});
    	$('#add-item').find("input[name='discount_percent']").on( "change", function() {
    		updateNewItemData();
		});
    	$('#add-item').find("input[name='discount_percent']").on( "keyup", function() {
    		updateNewItemData();
		});
    	
    }
    //initialize edit item events
    var initEditItemEvents = function() {
    	$('#edit-item').find(".purchase-quantity").spinner().on( "change", function() {
    		updateEditItemData();
		});
    	$('#edit-item').find(".purchase-quantity").spinner().on( "keyup", function() {
    		updateEditItemData();
		});	
    	$('#edit-item').find("input[name='tax_percent']").on( "change", function() {
    		updateEditItemData();
		});
    	$('#edit-item').find("input[name='tax_percent']").on( "keyup", function() {
    		updateEditItemData();
		});
    	$('#edit-item').find("input[name='discount_percent']").on( "change", function() {
    		updateEditItemData();
		});
    	$('#edit-item').find("input[name='discount_percent']").on( "keyup", function() {
    		updateEditItemData();
		});	
		
    }
    //Add new order
    var addOrder = function() {
			
		$.ajax({
            type: "GET",
            url: "create.do",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if( responseJson.id ){
            		$('.alert-success span').text("订单创建成功！");
       	   	 	 	$('.alert-success').show();
            	}else{
            		$('.alert-danger span').text("订单创建失败，请重新操作！");
            		$('.alert-danger').show();
            	} 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
		setTimeout(function(){
			window.location.reload();
		},2000);
    }
    //update order's address
    var updateAddr = function(address_id,address_type) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateOrderAddr.do",
            data: {address_id:address_id,
            	address_type:address_type,
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 $('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Update new order item related data
    var updateNewItemData = function() {
    	var quantity = $('#add-item').find('.purchase-quantity').find('input').val()*1;
		var price = $('#add-item').find('input[name="product[price]"]').val()*1;
		var tax_percent = $('#add-item').find("input[name='tax_percent']").val()/100;
		var discount_percent = $('#add-item').find("input[name='discount_percent']").val()/100;
		
		$('#add-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#add-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		$('#add-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#add-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent - discount_percent)).toFixed(2) );
		$('#add-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //Update edit order item related data
    var updateEditItemData = function() {
    	var quantity = $('#edit-item').find('.purchase-quantity').find('input').val()*1;
		var price = $('#edit-item').find('input[name="product[price]"]').val()*1;
		var tax_percent = $('#edit-item').find("input[name='tax_percent']").val()/100;
		var discount_percent = $('#edit-item').find("input[name='discount_percent']").val()/100;
		
		$('#edit-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#edit-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		$('#edit-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#edit-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent - discount_percent)).toFixed(2) );
		$('#edit-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //add a new order item
    var addOrderItem = function() {
		
		$.ajax({
            type: "POST",
            url: "addItem.do",
            data: $("#add-item-form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功创建！");
    	   	 	 $('.alert-success').show();
    	   	 	loadOrderItems();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
  //edit an order item
    var editOrderItem = function() {
		
		$.ajax({
            type: "POST",
            url: "editItem.do",
            data: $("#edit-item-form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 //$('.alert-success').show();
    	   	 	loadOrderItems();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //delete a order item
    var delOrderItem = function(item_id) {
		
		$.ajax({
            type: "GET",
            url: "delItem.do",
            data: {item_id:item_id},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();
    	   	 	loadOrderItems();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available products list dynamically
    var loadAvailProd = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailProd.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	$("table#product_pick").find("tbody").html('');
            	for(var i=0;i<responseJson.length;i++){
            		var product = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="product_pick" value="'+product.id+'"/> </td> \
            		<td id="'+product.id+'"> '+product.number +'</span> </td> \
            		<td> '+product.name +'</span> </td> \
            		<td> '+product.categoryName +' </td> \
            		<td> '+product.desc +' </td> \
            		<td> '+product.price.toFixed(2) +' </td> \
            		<td> '+product.stock + product.sku+'</td> \
            		</tr>';
            		
            		$("table#product_pick").find("tbody").append(tableData);
                }
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available bill address list dynamically
    var loadAvailBillAddr = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailAddr.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	$("table#bill-address-pick-table").find("tbody").html('');
            	for(var i=0;i<responseJson.length;i++){
            		var address = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="bill_address_pick" value="'+address.addressId+'"/> </td> \
            		<td id="'+address.addressId+'"> '+address.contactName +'</span> </td> \
            		<td> '+address.contactCell +'</span> </td> \
            		<td> '+address.province +' </td> \
            		<td> '+address.city +' </td> \
            		<td> '+address.county +' </td> \
            		<td> '+address.street+'</td> \
            		<td> '+address.zipcode+'</td> \
            		</tr>';
            		
            		$("table#bill-address-pick-table").find("tbody").append(tableData);
                }
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available ship address list dynamically
    var loadAvailShipAddr = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailAddr.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	$("table#ship-address-pick-table").find("tbody").html('');
            	for(var i=0;i<responseJson.length;i++){
            		var address = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="ship_address_pick" value="'+address.addressId+'"/> </td> \
            		<td id="'+address.addressId+'"> '+address.contactName +'</span> </td> \
            		<td> '+address.contactCell +'</span> </td> \
            		<td> '+address.province +' </td> \
            		<td> '+address.city +' </td> \
            		<td> '+address.county +' </td> \
            		<td> '+address.street+'</td> \
            		<td> '+address.zipcode+'</td> \
            		</tr>';
            		
            		$("table#ship-address-pick-table").find("tbody").append(tableData);
                }
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //update order amounts
    var updateOrderAmounts = function(base_total,due_total) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "editOrderAmounts.do",
            data: {base_total:base_total,
            	due_total:due_total,
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 //$('.alert-success').show();	     	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //update order status
    var updateOrderStatus = function(status_code) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateOrderStatus.do",
            data: {status_code:status_code,
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
		
		setTimeout(function(){
			window.location.reload();
		},2000);
    }
    //Generate available order items list dynamically
    var loadOrderItems = function() {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "GET",
            url: "loadItems.do",
            data: {orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var base_total = 0;
            	var tax_total = 0;
            	var discount_total = 0;
            	var due_total = 0;
            	
            	$("table.order-items").find("tbody").html('');
            	for(var i=0;i<responseJson.length;i++){
            		var item = responseJson[i];
            		
            		tableData = '<tr id='+item.item_id+'> <td id='+item.item_prod_id+'><a href="../prodadmin/details.do?id='+item.item_prod_id+'">'+ item.item_prod_name +'</a> </td> \
            		<td> <span class="label label-sm label-success">'+item.item_status_value +'</span> </td> \
            		<td> '+item.item_base_price +'</span> </td> \
            		<td> '+item.item_due_price +' </td> \
            		<td> '+item.item_quantity +' </td> \
            		<td> '+item.item_tax_amount +' </td> \
            		<td> '+item.item_tax_ratio+'</td> \
            		<td> '+item.item_discount_amount+'</td> \
            		<td> '+item.item_due_amount+'</td> \
            		<td> <a href="#edit-item" class="btn btn-xs default btn-editable edit-item" data-toggle="modal"><i class="fa fa-pencil"></i> 编辑</a> \
					<a href="#delete-confirm" class="btn btn-xs default btn-editable delete-item" data-toggle="modal"><i class="fa fa-minus"></i> 删除</a> </td> \
            		</tr>';
            		
            		$("table.order-items").find("tbody").append(tableData);
            		
            		base_total = base_total + item.item_base_amount;
            		tax_total = tax_total + item.item_tax_amount;
            		discount_total = discount_total + item.item_discount_amount;
            		due_total = due_total + item.item_due_amount;
                }
            	$('a.delete-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		$('input[name="orderItem[id]"]').val(item_id);
            	});
            	$('a.edit-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		var product_id = $(this).parents('tr').children('td:eq(0)').attr('id');
            		var product_name = $(this).parents('tr').children('td:eq(0)').text();
            		var base_price = $(this).parents('tr').children('td:eq(2)').text()*1;
            		var quantity = $(this).parents('tr').children('td:eq(4)').text()*1;
            		var purchase_price = $(this).parents('tr').children('td:eq(3)').text()*1;
            		var tax_amount = $(this).parents('tr').children('td:eq(5)').text()*1;
            		var tax_ratio = $(this).parents('tr').children('td:eq(6)').text()*1;
            		var discount_amount = $(this).parents('tr').children('td:eq(7)').text()*1;
            		var purchase_amount = $(this).parents('tr').children('td:eq(8)').text()*1;
            		var base_amount = (base_price * quantity).toFixed(2);
            		var discount_ratio = (discount_amount*100/base_amount).toFixed(2);
            		
            		$('#edit-item').find('input[name="product[id]"]').val(product_id);
            		$('#edit-item').find('input[name="product[name]"]').val(product_name);
            		$('#edit-item').find('input[name="product[price]"]').val(base_price);
            		$('#edit-item').find('input[name="purchase_price"]').val(purchase_price);
            		$('#edit-item').find('input[name="order_item[id]"]').val(item_id);
            		$('#edit-item').find(".purchase-quantity").spinner("value",quantity);
            		$('#edit-item').find('input[name="tax_amount"]').val(tax_amount);
            		$('#edit-item').find('input[name="tax_percent"]').val(tax_ratio);
            		$('#edit-item').find('input[name="discount_amount"]').val(discount_amount);
            		$('#edit-item').find('input[name="base_amount"]').val(base_amount);
            		$('#edit-item').find('input[name="purchase_amount"]').val(purchase_amount);
            		$('#edit-item').find('input[name="discount_percent"]').val(discount_ratio);
            	});
            	
            	$('div.base-total').text(base_total.toFixed(2));
            	$('div.tax-total').text(tax_total.toFixed(2));
            	$('div.discount-total').text(discount_total.toFixed(2));
            	$('div.due-total').text(due_total.toFixed(2));
            	$('div.static-total-due').text(due_total.toFixed(2)+"元");
            	updateOrderAmounts(base_total,due_total);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
	return {
		init: function(){
			
			initOrdersEvents();
			loadOrderItems();
			
		},
		
	};

}();