/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/12/28
 * Description: Purchase Orders view events handlers.
 */
var POViewEvents = function(){
	// initialize events function for all elements.
    var initPOViewEvents = function() {
    	
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	
    	$('button.approval-history-query-assistant').click(function() {
    		$("tr.approval-history-filter").show();
    		$(this).hide();
    		$('button.approval-history-query-cancel').show();
	    });
    	$('button.approval-history-query-cancel').click(function() {
    		$("tr.approval-history-filter").hide();
    		$(this).hide();
    		$('button.approval-history-query-assistant').show();
	    });
    	
    	$('button.order-history-query-assistant').click(function() {
    		$("tr.order-history-filter").show();
    		$(this).hide();
    		$('button.order-history-query-cancel').show();
	    });
    	$('button.order-history-query-cancel').click(function() {
    		$("tr.order-history-filter").hide();
    		$(this).hide();
    		$('button.order-history-query-assistant').show();
	    });
    	
    	$("button.back").click(function() {
    		history.back();
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
    	
    	//test
    	
    	$('.spinner-input').TouchSpin({
    		buttondown_class: 'btn blue',
    		buttonup_class: 'btn blue'
    	});
    	
    	
    	initNewItemEvents();
    	initEditItemEvents();
    	 
    	$('a.product-pick').each(function() {
    		$(this).click(function(){
    			loadAvailProd();
    		});	
    	});
    	$('button.product-pick').click(function() {
    		var table=document.getElementById("product_pick_table");
    		var id="";
    		var name="";
    		var price=0;

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				name = table.rows[i].cells[4].innerText;
    			}
    		}
    		$('input[name="product[id]"]').val(id);
    		$('input[name="product[name]"]').val(name);
    		$('input[name="product[price]"]').val(price);
    		$('input[name="purchase_price"]').val(price);
    		updateNewItemData();
    		updateEditItemData();
    	});
    	//Begin bill address pick
    	$('a.bill-address-pick').click(function(){
    			loadAvailBillAddr();
    	});
    	$('button.bill-address-pick').click(function() {
    		var table=document.getElementById("bill_address_pick_table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var addressInfo = '<div class="row static-info"><div class="col-md-5 name">\
					 联系人:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 电话号码:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 省/直辖市:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 城市:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 区/县:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 街道:</div><div class="col-md-7 value">'+table.rows[i].cells[6].innerText+'</div></div> \
					 <div class="row static-info"><div class="col-md-5 name">\
					 邮编:</div><div class="col-md-7 value">'+table.rows[i].cells[7].innerText+'</div></div>';
    				$('div.bill-address-info').html(addressInfo);
    				break;
    			}
    		}
    		updateAddr(id,"bill-address");
    	});
    	//End bill address pick
    	//Begin supplier pick
    	$('a.supplier-pick').click(function(){
    		loadAvailSupplier();
    	});
    	$('button.supplier-pick').click(function() {
    		var table=document.getElementById("supplier-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var supplierInfo = '<div class="row static-info"><div class="col-md-5 name">\
    					供应商编号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					供应商名称:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电子邮件:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					供应商地址:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电话号码:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div>';
    				$('div.supplier-info').html(supplierInfo);
    				break;
    			}
    		}
    		updateOrderInfo({"supplier_id":id});
    	}); 
    	//End supplier pick
    	//Begin lv2 supplier pick
    	$('a.supplier-lv2-pick').click(function(){
    		loadAvailLv2Supplier();
    	});
    	$('button.supplier-lv2-pick').click(function() {
    		var table=document.getElementById("supplier-lv2-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var supplierInfo = '<div class="row static-info"><div class="col-md-5 name">\
    					供应商编号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					供应商名称:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电子邮件:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					供应商地址:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电话号码:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div>';
    				$('div.supplier-lv2-info').html(supplierInfo);
    				break;
    			}
    		}
    		updateOrderInfo({"supplier_lv2_id":id});
    	}); 
    	//End lv2 supplier pick
    	//Begin receive warehouse pick
    	$('a.receive-wh-pick').click(function(){
    		loadAvailWarehouse();
    	});
    	$('button.receive-wh-pick').click(function() {
    		var table=document.getElementById("warehouse-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
//    				var supplierInfo = '<div class="row static-info"><div class="col-md-5 name">\
//    					仓库编号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
//    					<div class="row static-info"><div class="col-md-5 name">\
//    					仓库名称:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
//    					<div class="row static-info"><div class="col-md-5 name">\
//    					仓库联系人:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div> \
//    					<div class="row static-info"><div class="col-md-5 name">\
//    					联系人电话:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
//    					<div class="row static-info"><div class="col-md-5 name">\
//    					仓库地址:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div>';
//    				$('div.receive-wh-info').html(supplierInfo);
    				$('input[name="warehouse[id]"]').val(id);
    				$('input[name="warehouse[name]"]').val(table.rows[i].cells[2].innerText);
    				break;
    			}
    		}
    		//updateOrderInfo(null,id,null,null);
    	});
    	//End receive warehouse pick
    	//Begin owner pick
    	$('a.owner-pick').click(function(){
    		loadAvailOwner();
    	});
    	$('button.owner-pick').click(function() {
    		var table=document.getElementById("owner-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var ownerInfo = '<div class="row static-info"><div class="col-md-5 name">\
    					经手人账号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					经手人姓:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					经手人名:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					经手人职位:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div>\
    					<div class="row static-info"><div class="col-md-5 name">\
    					经手人邮箱:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div>';
    				$('div.owner-info').html(ownerInfo);
    				break;
    			}
    		}
    		updateOrderInfo({"owner_id":id});
    	});
    	//End owner pick
    	$('a.po-comment-update').click(function() {
    		var comm = $('textarea[name="order[description]"]').val();
    		updateOrderInfo({"comment":comm});
    	});
    	
    	$('a.order-base-update').click(function() {
    		var man_number = $('input[name="order[man_number]"]').val();
    		var payment_status = $('select[name="order[payment_status]"]').val();
    		var payment_ratio = $('input[name="order[payment_ratio]"]').val();
    		updateOrderInfo({"man_number":man_number,
    			"payment_status":payment_status,
    			"payment_ratio":payment_ratio
    			});
    	});
    	
    	
    	$('button.add-item').click(function() {
    		
    		//test:保存之前清除
    		addOrderItem();
    		
    		$('input[name="product[id]"]').val("");
    		$('input[name="poItem[product-id]"]').val("");
    		$('input[name="product[name]"]').val("");
    		$('input[name="warehouse[name]"]').val("");
    		$('input[name="purchase_price"]').val("");
    		$('input[name="base_amount"]').val("");
    		$('input[name="tax_amount"]').val("");
    		$('input[name="purchase_amount"]').val("");
    		$('input[name="purchase_quantity"]').val(1);
    		$('input[name="tax_percent"]').val("0.00");
    		$('textarea[name="item_comment"]').val("");

    	});
    	
    	//test
    	$('button.default').click(function(){
    		$('input[name="product[id]"]').val("");
    		$('input[name="poItem[product-id]"]').val("");
    		$('input[name="product[name]"]').val("");
    		$('input[name="warehouse[name]"]').val("");
    		$('input[name="purchase_price"]').val("");
    		$('input[name="base_amount"]').val("");
    		$('input[name="tax_amount"]').val("");
    		$('input[name="purchase_amount"]').val("");
    		$('input[name="purchase_quantity"]').val(1);
    		$('input[name="tax_percent"]').val("0.00");
    		$('textarea[name="item_comment"]').val("");
    	});
    	
    	$('button.edit-item').click(function() {
    		editOrderItem();
    	});
    	$('button.delete-confirm').click(function() {
    		var item_id = $('input[name="orderItem[id]"]').val();
    		var orderId = $('input[name="order[id]"]').val();
    		//test
    		var productName = $('input[name="product[name]"]').val();
    		
    		delOrderItem(item_id,orderId,productName);
    	});
    	
    	$('a.hold-order').click(function() {
    		updateOrderStatus("hold");
    	});
    	$('a.cancel-order').click(function() {
    		updateOrderStatus("cancelled");
    	});
    	$('a.complete-order').click(function() {
    		updateOrderStatus("completed");
    	});
    	$('a.submit-order').click(function() {
    		updateOrderStatus("submitted");
    	});
    	$('a.reopen-order').click(function() {
    		updateOrderStatus("reopen");
    	});
    	$('a.scan-order').click(function() {
    		updateOrderStatus("scanned");
    	});
    	$('a.archive-order').click(function() {
    		updateOrderStatus("archived");
    	});
    	
    	$('a.generate-req').click(function() {
    		generateReq();
    	});
    	
    	//查看审核流程状态
    	$('a.queryApprovalStatus').click(function(){
    		queryApprovalStatus();
    	});
    	//删除附件
       	$('button.remove-image-confirm').click(function() {
    		removeAttach();
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
    	$('#add-item').find("input[name='purchase_price']").on( "change", function() {
    		updateNewItemData();
		});
    	$('#add-item').find("input[name='purchase_price']").on( "keyup", function() {
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
    	$('#edit-item').find("input[name='purchase_price']").on( "change", function() {
    		updateEditItemData();
		});
    	$('#edit-item').find("input[name='purchase_price']").on( "keyup", function() {
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
		var price = $('#add-item').find('input[name="purchase_price"]').val()*1;
		var tax_percent = $('#add-item').find("input[name='tax_percent']").val()/100;
		//var discount_percent = $('#add-item').find("input[name='discount_percent']").val()/100;
		
		$('#add-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#add-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		//$('#add-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#add-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent)).toFixed(2) );
		//$('#add-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //Update edit order item related data
    var updateEditItemData = function() {
    	var quantity = $('#edit-item').find('.purchase-quantity').find('input').val()*1;
		var price = $('#edit-item').find('input[name="purchase_price"]').val()*1;
		var tax_percent = $('#edit-item').find("input[name='tax_percent']").val()/100;
		//var discount_percent = $('#edit-item').find("input[name='discount_percent']").val()/100;
		
		$('#edit-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#edit-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		//$('#edit-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#edit-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent)).toFixed(2) );
		//$('#edit-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //add a new order item
    var addOrderItem = function() {
		
    	var product_id = $('#add-item').find('input[name="product[id]"]').val();
		var warehouse_id = $('#add-item').find('input[name="warehouse[id]"]').val();
		
		if(product_id==null || product_id==""){
			alert("产品不能为空！");
			return;
		}
		if(warehouse_id==null || warehouse_id==""){
			alert("收货仓库不能为空！");
			return;
		}
		
		$.ajax({
            type: "POST",
            url: "addItem.do",
            data: $("#add-item-form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
//            		$('.alert-success span').text(responseJson.objname+"已成功创建！");
//       	   	 	 	$('.alert-success').show();
       	   	 	 	loadOrderItems();
            	}else{
            		alert(responseJson.errmsg);
            	}
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
            	if(responseJson.objname){
//            		$('.alert-success span').text(responseJson.objname+"已成功更新！");
//       	   	 	 	$('.alert-success').show();
       	   	 	 	loadOrderItems();
            	}else{
            		alert(responseJson.errmsg);
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //delete a order item
    var delOrderItem = function(item_id, orderId, productName) {
		
		$.ajax({
            type: "GET",
            url: "delItem.do",
            data: {item_id:item_id, orderId:orderId, productName:productName},
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
    //Generate available warehouse list dynamically
    var loadAvailWarehouse = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailWarehouse.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="warehouse-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>仓库编号</th>\
								<th>仓库名称</th>\
								<th>仓库地址</th>\
								<th>仓库联系人</th>\
								<th>联系人电话</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#receive-wh-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var warehouse = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="warehouse_pick" value="'+warehouse.id+'"/> </td> \
            		<td id="'+warehouse.id+'"> '+warehouse.number +'</span> </td> \
            		<td> '+warehouse.name +' </td> \
            		<td> '+warehouse.primary_addr_name +' </td> \
            		<td> '+warehouse.primary_contact_name +' </td> \
            		<td> '+warehouse.primary_contact_cellphone+'</td> \
            		</tr>';
            		
            		$("table#warehouse-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#warehouse-pick-table');
            	initPickTable(table);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available suppliers list dynamically
    var loadAvailSupplier = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailSupplier.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="supplier-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>单位编号</th>\
								<th>供应商名称</th>\
								<th>供应商区域</th>\
								<th>电话</th>\
								<th>Email</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#supplier-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var supplier = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="supplier_pick" value="'+supplier.accountId+'"/> </td> \
            		<td id="'+supplier.accountId+'"> '+supplier.accountNumber +'</span> </td> \
            		<td> '+supplier.accountName +' </td> \
            		<td> '+supplier.address.country+ supplier.address.province + supplier.address.city + supplier.address.county +' </td> \
            		<td> '+supplier.workphone +' </td> \
            		<td> '+supplier.email+'</td> \
            		</tr>';
            		
            		$("table#supplier-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#supplier-pick-table');
            	initPickTable(table);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available suppliers list dynamically
    var loadAvailLv2Supplier = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailSupplier.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="supplier-lv2-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>单位编号</th>\
								<th>供应商名称</th>\
								<th>供应商区域</th>\
								<th>电话</th>\
								<th>Email</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#supplier-lv2-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var supplier = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="supplier_pick" value="'+supplier.accountId+'"/> </td> \
            		<td id="'+supplier.accountId+'"> '+supplier.accountNumber +'</span> </td> \
            		<td> '+supplier.accountName +' </td> \
            		<td> '+supplier.address.country+ supplier.address.province + supplier.address.city + supplier.address.county +' </td> \
            		<td> '+supplier.workphone +' </td> \
            		<td> '+supplier.email+'</td> \
            		</tr>';
            		
            		$("table#supplier-lv2-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#supplier-lv2-pick-table');
            	initPickTable(table);
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
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="product_pick_table"> \
					<thead> \
					<tr>\
						<th></th>\
						<th>产品编号</th>\
						<th>产品名称</th>\
						<th>产品类别</th>\
						<th>产品型号</th>\
						<th>库存</th>\
					</tr>\
					</thead>\
					<tbody></tbody> \
					</table>';
            	$("div#product-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var product = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="product_pick" value="'+product.id+'"/> </td> \
            		<td id="'+product.id+'"> '+product.number +'</span> </td> \
            		<td> '+product.name +'</span> </td> \
            		<td> '+product.categoryName +' </td> \
            		<td> '+product.model +' </td> \
            		<td> '+product.stock + product.sku+'</td> \
            		</tr>';
            		
            		$("table#product_pick_table").find("tbody").append(tableData);
                }
            	
            	var table = $('#product_pick_table');
            	initPickTable(table);
            	
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
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="bill_address_pick_table"> \
					<thead> \
					<tr>\
						<th></th>\
						<th>联系人</th>\
						<th>联系电话</th>\
						<th>省/直辖市</th>\
						<th>市/区</th>\
						<th>区/县</th>\
            			<th>街道</th>\
            			<th>邮编</th>\
					</tr>\
					</thead>\
					<tbody></tbody> \
					</table>';
            	$("div#bill-address-pick").find(".modal-body").html(tableWrapper);
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
            		
            		$("table#bill_address_pick_table").find("tbody").append(tableData);
                }
            	
            	var table = $('#bill_address_pick_table');
            	initAddrPickTable(table);
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available owners list dynamically
    var loadAvailOwner = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailOwner.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="owner-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>人员账号</th>\
								<th>人员姓</th>\
								<th>人员名字</th>\
								<th>人员职位</th>\
								<th>Email</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#owner-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var owner = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="owner_pick" value="'+owner.userId+'"/> </td> \
            		<td id="'+owner.userId+'"> '+owner.userName +'</span> </td> \
            		<td> '+owner.lastName +' </td> \
            		<td> '+owner.firstName +' </td> \
            		<td> '+owner.position.positionName +' </td> \
            		<td> '+owner.email+'</td> \
            		</tr>';
            		
            		$("table#owner-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#owner-pick-table');
            	initPickTable(table);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Initialize dataTable
    var initPickTable = function(table) {
    	
    	table.find("tbody").children("tr").hover(function(){
    		$(this).css({
    					"cursor":"pointer",
    					"background":"#d8dfd0"
    					})
    	},function(){
    		$(this).css({
				"cursor":"",
				"background":""
				})
    	});
    	
    	table.find("tbody").children("tr").click(function(){
    		$(this).find("input[type='radio']").attr("checked","checked");
    	});
    	
        table.dataTable({

            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "表格中无有效的记录",
                "info": "正在显示  _TOTAL_ 项记录中的第  _START_ 到  _END_ 项",
                "infoEmpty": "记录为空 ",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "显示 _MENU_ 记录",
                "search": "Search:",
                "zeroRecords": "找不到匹配的记录"
            },

            "columns": [{
                "orderable": false
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "全部"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,            
            "pagingType": "bootstrap_full_number",
            "language": {
                "search": "快速搜索: ",
                "lengthMenu": "  _MENU_ 记录",
                "info": "正在显示_TOTAL_项记录中的第 _START_ 到 _END_项",
                "paginate": {
                    "previous":"上一页",
                    "next": "下一页",
                    "last": "末页",
                    "first": "首页"
                }
            },
            "order": [
                      [1, "asc"]
                  ]
        });
		
    }
    //Initialize address dataTable
    var initAddrPickTable = function(table) {
    	
    	table.find("tbody").children("tr").hover(function(){
    		$(this).css({
    					"cursor":"pointer",
    					"background":"#d8dfd0"
    					})
    	},function(){
    		$(this).css({
				"cursor":"",
				"background":""
				})
    	});
    	
    	table.find("tbody").children("tr").click(function(){
    		$(this).find("input[type='radio']").attr("checked","checked");
    	});
    	
        table.dataTable({

            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "表格中无有效的记录",
                "info": "正在显示  _TOTAL_ 项记录中的第  _START_ 到  _END_ 项",
                "infoEmpty": "记录为空 ",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "显示 _MENU_ 记录",
                "search": "Search:",
                "zeroRecords": "找不到匹配的记录"
            },

            "columns": [{
                "orderable": false
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }, {
                "orderable": true
            }],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "全部"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,            
            "pagingType": "bootstrap_full_number",
            "language": {
                "search": "快速搜索: ",
                "lengthMenu": "  _MENU_ 记录",
                "info": "正在显示_TOTAL_项记录中的第 _START_ 到 _END_项",
                "paginate": {
                    "previous":"上一页",
                    "next": "下一页",
                    "last": "末页",
                    "first": "首页"
                }
            },
            "order": [
                      [1, "asc"]
                  ]
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
            	type_code:"Purchase Order",
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		window.location.reload();
            		$('.alert-success span').text(responseJson.objname+"已成功！");
       	   	 	 	$('.alert-success').show();
       	   	 	 	setTimeout(function(){
       	   	 	 		$('.alert-success').fadeOut();
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
            }
        });
    }
    //update purchase order info
    var updateOrderInfo = function(dataJson) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updatePOInfo.do",
            data: {supplier_id:dataJson.supplier_id,
            	supplier_lv2_id:dataJson.supplier_lv2_id,
            	man_number:dataJson.man_number,
            	owner_id:dataJson.owner_id,
            	comment:dataJson.comment,
            	payment_status:dataJson.payment_status,
            	payment_ratio:dataJson.payment_ratio,
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
            	setTimeout(function(){
            		$('.alert-success').fadeOut();	
        		},2000);       	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //generate stock in requisitions
    var generateReq = function() {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "generateReq.do",
            data: {orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		$('.alert-success span').text("入库单已成功生成！");
       	   	 	 	$('.alert-success').show();	
       	   	 	 	window.location.reload();
            	}else{
            		alert(responseJson.errmsg);
            		window.location.reload();
            	}
            	setTimeout(function(){
            		$('.alert-success').fadeOut();
            		window.location.reload();
        		},2000);       	   	 	 
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
            }
        });
    }
    //Generate available order items list dynamically
    var loadOrderItems = function() {
    	var orderId = $('input[name="order[id]"]').val();
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
		
		if(readOnlyFlag == 'true'){
			var editColumn = '<td></td>';
		}else{
			var editColumn = '<td> <a href="#edit-item" class="btn btn-xs default btn-editable edit-item" data-toggle="modal"><i class="fa fa-pencil"></i> 编辑</a> \
			<a href="#delete-confirm" class="btn btn-xs default btn-editable delete-item" data-toggle="modal"><i class="fa fa-minus"></i> 删除</a> </td>';
		}
		
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
            		
            		
            		tableData = '<tr id='+item.item_id+'> <td id='+item.item_prod_id+'><a href="../prodadmin/details.do?id='+item.item_prod_id+'">'+ item.item_prod_model +'</a> </td> \
            		<td id='+item.item_warehouse_id+'> <a href="../inventory/warehouse_details_readonly.do?id='+item.item_warehouse_id+'">'+item.item_warehouse_name +'</a> </td> \
            		<td> '+item.item_due_price +' </td> \
            		<td> '+item.item_quantity +' </td> \
            		<td> '+item.item_tax_amount +' </td> \
            		<td> '+item.item_tax_ratio+'</td> \
            		<td> '+item.item_due_amount+'</td> \
            		<td> '+item.item_comment +'</td>'
            		+editColumn+'</tr>';
            		
            		$("table.order-items").find("tbody").append(tableData);
            		
            		base_total = base_total + item.item_base_amount;
            		tax_total = tax_total + item.item_tax_amount;
            		discount_total = discount_total + item.item_discount_amount;
            		due_total = due_total + item.item_due_amount;
                }
            	$('a.delete-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		$('input[name="orderItem[id]"]').val(item_id);
            		//test
            		var product_name = $(this).parents('tr').children('td:eq(0)').text();
            		//$('input[name="product[name]"]').val(product_name);
            		$('#delete-confirm').find('input[name="product[name]"]').val(product_name);
            	});
            	$('a.edit-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		var product_id = $(this).parents('tr').children('td:eq(0)').attr('id');
            		var product_name = $(this).parents('tr').children('td:eq(0)').text();
            		var warehouse_id = $(this).parents('tr').children('td:eq(1)').attr('id');
            		var warehouse_name = $(this).parents('tr').children('td:eq(1)').text();
            		//var base_price = $(this).parents('tr').children('td:eq(2)').text()*1;
            		var quantity = $(this).parents('tr').children('td:eq(3)').text()*1;
            		var purchase_price = $(this).parents('tr').children('td:eq(2)').text()*1;
            		var tax_amount = $(this).parents('tr').children('td:eq(4)').text()*1;
            		var tax_ratio = $(this).parents('tr').children('td:eq(5)').text()*1;
            		//var discount_amount = $(this).parents('tr').children('td:eq(7)').text()*1;
            		var item_comment = $(this).parents('tr').children('td:eq(7)').text();
            		var purchase_amount = $(this).parents('tr').children('td:eq(6)').text()*1;
            		var base_amount = (purchase_price * quantity).toFixed(2);
            		//var discount_ratio = (discount_amount*100/base_amount).toFixed(2);
            		
            		$('#edit-item').find('input[name="product[id]"]').val(product_id);
            		$('#edit-item').find('input[name="product[name]"]').val(product_name);
            		$('#edit-item').find('input[name="warehouse[id]"]').val(warehouse_id);
            		$('#edit-item').find('input[name="warehouse[name]"]').val(warehouse_name);
            		//$('#edit-item').find('input[name="product[price]"]').val(base_price);
            		$('#edit-item').find('input[name="purchase_price"]').val(purchase_price);
            		$('#edit-item').find('input[name="order_item[id]"]').val(item_id);
            		$('#edit-item').find(".purchase-quantity").spinner("value",quantity);
            		$('#edit-item').find('input[name="tax_amount"]').val(tax_amount);
            		$('#edit-item').find('input[name="tax_percent"]').val(tax_ratio);
            		$('#edit-item').find('textarea[name="item_comment"]').val(item_comment);
            		//$('#edit-item').find('input[name="discount_amount"]').val(discount_amount);
            		$('#edit-item').find('input[name="base_amount"]').val(base_amount);
            		$('#edit-item').find('input[name="purchase_amount"]').val(purchase_amount);
            		//$('#edit-item').find('input[name="discount_percent"]').val(discount_ratio);
            	});
            	
            	$('div.base-total').text(base_total.toFixed(2));
            	$('div.tax-total').text(tax_total.toFixed(2));
            	//$('div.discount-total').text(discount_total.toFixed(2));
            	$('div.due-total').text(due_total.toFixed(2));
            	$('div.static-total-due').text(due_total.toFixed(2));
            	updateOrderAmounts(base_total,due_total);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //handle purchase order history list
    var handleHistory = function() {
    	//init date pickers
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        });

        $(".datetime-picker").datetimepicker({
            isRTL: Metronic.isRTL(),
            autoclose: true,
            todayBtn: true,
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            minuteStep: 10
        });
    	
    	var grid = new Datatable();
    	
    	var orderId = $('input[name="order[id]"]').val();

        grid.init({
            src: $("#datatable_history"),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            loadingMessage: '数据加载中...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": "history_listdata.do?orderId="+orderId, // ajax source
                },
                "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                    'orderable': true,
                    'targets': [0]
                }],
                "order": [
                    [0, "desc"]
                ] // set first column as a default sort by asc
            }
        });
    }
    //handle purchase order approval history list
    var handleApprovalHistory = function() {
    	//init date pickers
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true
        });

        $(".datetime-picker").datetimepicker({
            isRTL: Metronic.isRTL(),
            autoclose: true,
            todayBtn: true,
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            minuteStep: 10
        });
    	
    	var grid = new Datatable();
    	
    	var orderId = $('input[name="order[id]"]').val();

        grid.init({
            src: $("#datatable_approval_history"),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            loadingMessage: '数据加载中...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": "approval_history_listdata.do?orderId="+orderId, // ajax source
                },
                "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                    'orderable': true,
                    'targets': [0]
                }],
                "order": [
                    [0, "desc"]
                ] // set first column as a default sort by asc
            }
        });
    }
    
    //查询审核流程
    var queryApprovalStatus = function (){
    	//
    	var orderId = $('input[name="order[id]"]').val();
    	var type = "Order";
    	var from_data = {
    			orderId:orderId,
    			type:type
    	 };
    	$.ajax({
            type: "POST",
            url: "../order/queryApprovalStatus.do",
            data: from_data,
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableWrapper = '<ul class="timeline">\
					</ul>';
            		$("#approvalStatusDiv").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var step = responseJson[i];
        			tableData = '<li class="" id="a-step-li'+(i+1)+'">\
						<div class="timeline-time" id="timeline-time'+(i+1)+'">\
						</div>\
						<div class="timeline-icon " id="timeline-icon'+(i+1)+'">\
							'+(i+1)+'\
						</div>\
						<div class="timeline-body">\
							<div class="a-method" style="float: left;margin-right: 40px;">审核方法：'+step.method_name+'</div>\
							<div class="a-rollback" style="float: left;">回退方式：'+step.rollback_type_name+'</div>\
							<div id="approve-time'+(i+1)+'" style="float: right;"></div>\
							<hr>\
							<div class="timeline-content" id="timeline-content'+(i+1)+'">\
							</div>\
							<div class="timeline-footer" id="timeline-footer'+(i+1)+'">\
								<a href="/OEPT_eSales/order/po_approval_list.do" class="nav-link pull-right">\
								去审核 <i class="m-icon-swapright m-icon-white"></i>\
								</a>\
							</div>\
						</div>\
					</li>';
        			$("#approvalStatusDiv").find("ul.timeline").append(tableData);
        			//设定步骤颜色,判断是否为最后一步
        			if(i!=responseJson.length-1){
        				if(step.process_flg==true){
            				$("#a-step-li"+(i+1)).attr("class","timeline-blue");
            				$("#timeline-icon"+(i+1)).attr("style","background:#4d8bf8");
            			}else if(step.status_cd=="Pending"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-yellow");
            				$("#timeline-icon"+(i+1)).attr("style","background:#ffb848");
            			}else if(step.status_cd=="Approved"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-green");
            				$("#timeline-icon"+(i+1)).attr("style","background:#35aa47");
            			}else if(step.status_cd=="Rejected"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-red");
            				$("#timeline-icon"+(i+1)).attr("style","background:#e02222");
            			}
        			}else{
        				if(step.process_flg==true){
            				$("#a-step-li"+(i+1)).attr("class","timeline-blue timeline-noline");
            				$("#timeline-icon"+(i+1)).attr("style","background:#4d8bf8");
            			}else if(step.status_cd=="Pending"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-yellow timeline-noline");
            				$("#timeline-icon"+(i+1)).attr("style","background:#ffb848");
            			}else if(step.status_cd=="Approved"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-green timeline-noline");
            				$("#timeline-icon"+(i+1)).attr("style","background:#35aa47");
            			}else if(step.status_cd=="Rejected"){
            				$("#a-step-li"+(i+1)).attr("class","timeline-red timeline-noline");
            				$("#timeline-icon"+(i+1)).attr("style","background:#e02222");
            			}
        			}
        			
        			//加载步数，审核状态
    				tableDataTitle = '<span class="date">\
						第'+(i+1)+'步 </span>\
						<span class="time" style="font-size: 28px;">\
						'+step.status_val+' </span>';
        			$("#timeline-time"+(i+1)).append(tableDataTitle);
        			//判断是否正在执行
        			if(step.process_flg==true){
        				tableDataTitle = '<span class="date">\
							正在执行 </span>';
            			$("#timeline-time"+(i+1)).append(tableDataTitle);
        			}
        			//加载时间显示
        			if(step.status_cd=="Pending"){
        				//加载时间显示
            			approveTime = '创建审核时间：'+step.created;
            			$("#approve-time"+(i+1)).append(approveTime);
        			}else{
        				//加载时间显示
            			approveTime = '最近审核时间：'+step.updated;
            			$("#approve-time"+(i+1)).append(approveTime);
        			}
        			//加载审核人信息
        			for(var j=0;j<step.stepDetails.length;j++){
        				var detail = step.stepDetails[j];
        				approvalDetail = '<div><div style="float: left;margin-right: 40px;">审核人：'+detail.user.userName+'</div>\
        				<div style="float: left;">审核人审批状态:<span class="" id="to-approve'+i+""+j+'">'+detail.status_val+'</span></div></div><br>';
            			$("#timeline-content"+(i+1)).append(approvalDetail);
            			//判断审批人审批状态
            			if(detail.status_cd=="Pending"){
                			$("#to-approve"+i+""+j).attr("class","label label-sm label-info");
            			}else if(detail.status_cd=="Approved"){
                			$("#to-approve"+i+""+j).attr("class","label label-sm label-warning");
            			}else if(detail.status_cd=="Rejected"){
                			$("#to-approve"+i+""+j).attr("class","label label-sm label-danger");
            			}
        			}
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Handle attachments upload
    var handleAttchments = function() {

    	var orderId = $('input[name="order[id]"]').val();
        // see http://www.plupload.com/
        var uploader = new plupload.Uploader({
            runtimes : 'html5,flash,silverlight,html4',
             
            browse_button : document.getElementById('tab_images_uploader_pickfiles'), // you can pass in id...
            container: document.getElementById('tab_images_uploader_container'), // ... or DOM Element itself
             
            url : "upload.do?id="+orderId,
            
            unique_names : true,
             
            filters : {
                max_file_size : '30mb',
                mime_types: [
                    {title : "Office files", extensions : "doc,docx,xls,xlsx"},
                    {title : "Zip files", extensions : "zip"}
                ]
            },
         
            // Flash settings
            flash_swf_url : '../global/assets/plugins/plupload/js/Moxie.swf',
     
            // Silverlight settings
            silverlight_xap_url : '../global/assets/plugins/plupload/js/Moxie.xap',             
         
            init: {
                PostInit: function() {
                    $('#tab_images_uploader_filelist').html("");
         
                    $('#tab_images_uploader_uploadfiles').click(function() {
                        uploader.start();
                        return false;
                    });

                    $('#tab_images_uploader_filelist').on('click', '.added-files .remove', function(){
                        uploader.removeFile($(this).parent('.added-files').attr("id"));    
                        $(this).parent('.added-files').remove();                     
                    });
                },
         
                FilesAdded: function(up, files) {
                    plupload.each(files, function(file) {
                        $('#tab_images_uploader_filelist').append('<div class="alert alert-warning added-files" id="uploaded_file_' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ') <span class="status label label-info"></span>&nbsp;<a href="javascript:;" style="margin-top:-5px" class="remove pull-right btn btn-sm red"><i class="fa fa-times"></i> 删除</a></div>');
                    });
                },
         
                UploadProgress: function(up, file) {
                    $('#uploaded_file_' + file.id + ' > .status').html(file.percent + '%');
                },

                FileUploaded: function(up, file, response) {
                    var response = $.parseJSON(response.response);

                    if (response.result && response.result == 'OK') {
                        var id = response.id; // uploaded file's unique name. Here you can collect uploaded file names and submit an jax request to your server side script to process the uploaded files and update the images tabke

                        $('#uploaded_file_' + file.id + ' > .status').removeClass("label-info").addClass("label-success").html('<i class="fa fa-check"></i> 上传成功'); // set successfull upload
                        window.location.reload();
                    } else {
                        $('#uploaded_file_' + file.id + ' > .status').removeClass("label-info").addClass("label-danger").html('<i class="fa fa-warning"></i> 上传失败'); // set failed upload
                        Metronic.alert({type: 'danger', message: '有文件上传失败，请重新尝试！', closeInSeconds: 10, icon: 'warning'});
                    }
                },
         
                Error: function(up, err) {
                    Metronic.alert({type: 'danger', message: err.message, closeInSeconds: 10, icon: 'warning'});
                }
            }
        });

        uploader.init();

    }
  //Generate attachments list dynamically
    var loadAttachmentsData = function() {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "loadattach.do",
            data: {orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	for(var i=0;i<responseJson.length;i++){
            		var image = responseJson[i];
            		var file_size = "";
            		if(image.filesize/1000 > 1000){
            			file_size = (image.filesize/(1000*1000)).toFixed(2) + "MB";
            		}else if(image.filesize/1000 < 1){
            			file_size = image.filesize + "B";
            		}else{
            			file_size = (image.filesize/1000).toFixed(2) + "KB";
            		}
            		tableData = '<tr id="'+image.id+'"> <td><a href="..'+image.contextpath+'/'+image.original_filename +'" > \
            		'+image.original_filename +'</td> \
            		<td> '+file_size+'</td> \
            		<td> '+image.uploaded_date+' </td> \
            		<td> '+image.uploaded_by_user+'</td> \
            		<td> <a href="#remove-confirm" class="btn default btn-sm remove-button" data-toggle="modal"> <i class="fa fa-times"></i> 删除 </a> </td> \
            		</tr>';
            		
            		$("table.order-attachments").find("tbody").append(tableData);
                }
            	
            	$('a.remove-button').click(function() {
        	    	$('span.remove_image_id').text( $(this).parents('tr').attr('id') );
        	    	$('span.remove_image_filename').text( $(this).parents('tr').find('span.original_filename').text() );
        	    	$('span.remove_image_filepath').text( $(this).parents('tr').find('span.context_path').text() );
        	    });
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
  //delete attachment action
    var removeAttach = function() {
		var image_id = $('span.remove_image_id').text();
		
		$.ajax({
            type: "POST",
            url: "delAttach.do",
            data: {image_id:$('span.remove_image_id').text(),
            	image_filename:$('span.remove_image_filename').text(),
            	remove_image_filepath:$('span.remove_image_filepath').text()},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();
    	   	 	 $("table.order-attachments").find('tr[id="'+image_id+'"]').remove();
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
			
			initPOViewEvents();
			loadOrderItems();
			handleHistory();
			handleApprovalHistory();
			handleAttchments();
			loadAttachmentsData();
		},
		
	};

}();