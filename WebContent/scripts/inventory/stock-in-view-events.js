/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/13
 * Description: Stock in requisition view events handlers.
 */
var StockInViewEvents = function(){
	// initialize events function for all elements.
    var initStockInViewEvents = function() {
    	
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
    	
    	$('button.history-query-assistant').click(function() {
    		$("tr.history-filter").show();
    		$(this).hide();
    		$('button.history-query-cancel').show();
	    });
    	$('button.history-query-cancel').click(function() {
    		$("tr.history-filter").hide();
    		$(this).hide();
    		$('button.history-query-assistant').show();
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
    	$('.spinner-buttons').spinner({
    		step: 2
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
    		var number="";
    		var model="";
    		var price=0;

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				number = table.rows[i].cells[1].innerText;
    				name = table.rows[i].cells[2].innerText;
    				model = table.rows[i].cells[4].innerText;
    				//price = table.rows[i].cells[4].innerText;
    			}
    		}
    		$('input[name="product[id]"]').val(id);
    		$('input[name="product[name]"]').val(name);
    		$('input[name="product[number]"]').val(number);
    		$('input[name="product[model]"]').val(model);
    		//$('input[name="product[price]"]').val(price);
    		$('input[name="purchase_price"]').val(price);
    		//updateNewItemData();
    		//updateEditItemData();
    	});
    	//begin contract pick
    	$('a.contract-pick').each(function() {
    		$(this).click(function(){
    			loadAvailContract();
    		});	
    	});
    	$('button.contract-pick').click(function() {
    		var table=document.getElementById("contract_pick_table");
    		var id="";
    		var number="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				number = table.rows[i].cells[1].innerText;
    			}
    		}
    		$('input[name="contract[id]"]').val(id);
    		$('input[name="contract[number]"]').val(number);
    	});
    	//begin order pick
    	$('a.order-pick').click(function() {
    			loadAvailOrders();
    	});
    	$('button.order-pick').click(function() {
    		var table=document.getElementById("order-pick-table");
    		var id="";
    		var number="";
    		var man_number="";
    		var status="";
    		var created_date="";
    		var owner="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				number = table.rows[i].cells[1].innerText;
    				man_number = table.rows[i].cells[2].innerText;
    				status = table.rows[i].cells[4].innerText;
    				created_date = table.rows[i].cells[5].innerText;
    				owner = table.rows[i].cells[6].innerText;
    			}
    		}
    		var related_order_data = '<tr class="odd gradeX" id='+id+'>\
        		<td><a href="../order/so_details.do?id='+id+'">'+number+'</a></td>\
        		<td>'+man_number+'</td>\
        		<td>采购订单</td>\
        		<td>'+status+'</td>\
        		<td>'+created_date+'</td>\
        		<td>'+owner+'</td>\
      		</tr>';
    		$('tbody.table-related-orders').html(related_order_data);
    		
    		updateOrderInfo({"related_order_id":id});
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
    		updateOrderInfo(id,null,null,null);
    	}); 
    	//End supplier pick
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
    		updateOrderInfo({"receive_wh_id":id});
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
    	$('a.req-comment-update').click(function() {
    		var comm = $('textarea[name="req[description]"]').val();
    		updateOrderInfo({"comment":comm});
    	});
    	$('a.req-base-update').click(function() {
    		var subtype_code = $('select[name="req[subtype]"] option:selected').val();
    		var man_number = $('input[name="req[man_number]"]').val();
    		var receive_date = $('input[name="req[receive_date]"]').val();
    		updateOrderInfo({"subtype_code":subtype_code,
    			"man_number":man_number,
    			"receive_date":receive_date});
    	});
    	
    	$('button.add-item').click(function() {
    		addOrderItem();
    		
    		$('input[name="reset"]').trigger("click");
    		
    	});
    	
    	$('button.default').click(function(){
    		$('input[name="reset"]').trigger("click");
    	});
    	
    	$('button.edit-item').click(function() {
    		editOrderItem();
    	});
    	$('button.delete-confirm').click(function() {
    		var item_id = $('input[name="orderItem[id]"]').val();
    		//test
    		var product_name = $('input[name="product[name]"]').val();
    		var requisition_id = $('input[name="requisition[id]"]').val();
    		var requisiton_type = $('input[name="requisition[type_value]"]').val();
    		
    		delOrderItem(item_id, product_name, requisition_id, requisiton_type);
    		
    		//test
    		$('input[name="reset"]').trigger("click");
    	});
    	
    	$('a.hold-order').click(function() {
    		updateReqStatus("hold");
    	});
    	$('a.cancel-order').click(function() {
    		updateReqStatus("cancelled");
    	});
    	$('a.complete-order').click(function() {
    		updateReqStatus("completed");
    	});
    	$('a.submit-order').click(function() {
    		updateReqStatus("submitted");
    	});
    	$('a.reopen-order').click(function() {
    		updateReqStatus("reopen");
    	});
    	$('a.submit-approve-order').click(function() {
    		updateReqStatus("submit - approved");
    	});
    	$('a.submit-reject-order').click(function() {
    		updateReqStatus("submit - rejected");
    	});
    	
    	//查看审核流程状态
    	$('a.queryApprovalStatus').click(function(){
    		queryApprovalStatus();
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
//    	$('#add-item').find("input[name='discount_percent']").on( "change", function() {
//    		updateNewItemData();
//		});
//    	$('#add-item').find("input[name='discount_percent']").on( "keyup", function() {
//    		updateNewItemData();
//		});
    	
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
//    	$('#edit-item').find("input[name='discount_percent']").on( "change", function() {
//    		updateEditItemData();
//		});
//    	$('#edit-item').find("input[name='discount_percent']").on( "keyup", function() {
//    		updateEditItemData();
//		});	
		
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
		//var base_price = $('#add-item').find('input[name="product[price]"]').val()*1;
		//var tax_percent = $('#add-item').find("input[name='tax_percent']").val()/100;
		//var discount_percent = $('#add-item').find("input[name='discount_percent']").val()/100;
		
		//$('#add-item').find('input[name="base_amount"]').val( (quantity * base_price).toFixed(2) );
		//$('#add-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		//$('#add-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#add-item').find('input[name="purchase_amount"]').val( (quantity * price).toFixed(2) );
		//$('#add-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //Update edit order item related data
    var updateEditItemData = function() {
    	var quantity = $('#edit-item').find('.purchase-quantity').find('input').val()*1;
    	//var base_price = $('#edit-item').find('input[name="product[price]"]').val()*1;
		var price = $('#edit-item').find('input[name="purchase_price"]').val()*1;
		//var tax_percent = $('#edit-item').find("input[name='tax_percent']").val()/100;
		//var discount_percent = $('#edit-item').find("input[name='discount_percent']").val()/100;
		
		//$('#edit-item').find('input[name="base_amount"]').val( (quantity * base_price).toFixed(2) );
		//$('#edit-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		//$('#edit-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#edit-item').find('input[name="purchase_amount"]').val( (quantity * price).toFixed(2) );
		//$('#edit-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //add a new order item
    var addOrderItem = function() {
		
    	var product_id = $('#add-item').find('input[name="product[id]"]').val();
		var warehouse_id = $('input[name="warehouse[id]"]').val();
		//test
		var orderId = $('input[name="order[id]"]').val();
		//var contract_id = $('#add-item').find('input[name="contract[id]"]').val();
		
		if(product_id==null || product_id==""){
			alert("产品不能为空！");
			return;
		}
//		if(contract_id==null || contract_id==""){
//			alert("合同不能为空！");
//			return;
//		}
		
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
       	   	 	 	loadReqItems();
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
       	   	 	 	loadReqItems();
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
    var delOrderItem = function(item_id, product_name, requisition_id, requisiton_type) {
		
		$.ajax({
            type: "GET",
            url: "delItem.do",
            data: {item_id:item_id, product_name:product_name, requisition_id:requisition_id, requisiton_type:requisiton_type},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();
    	   	 	loadReqItems();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate available related sales orders list dynamically
    var loadAvailOrders = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailPO.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="order-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>订单系统编号</th>\
								<th>订单人工编号</th>\
								<th>订单类型</th>\
								<th>订单状态</th>\
								<th>创建时间</th>\
            					<th>经手人</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#order-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var order = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="order_pick" value="'+order.order_id+'"/> </td> \
            		<td id="'+order.order_id+'"> '+order.order_number +'</span> </td> \
            		<td> '+order.order_manual_number +' </td> \
            		<td> 采购订单 </td> \
            		<td> '+order.status_value +' </td> \
            		<td> '+order.created_date+'</td> \
            		<td> '+order.created_by_name+'</td> \
            		</tr>';
            		
            		$("table#order-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#order-pick-table');
            	initOrderPickTable(table);
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
								<th>供应商地址</th>\
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
            		<td> '+supplier.addressName +' </td> \
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
    //Generate available contracts list dynamically
    var loadAvailContract = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailContracts.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="contract_pick_table"> \
					<thead> \
					<tr>\
						<th></th>\
						<th>合同编号</th>\
						<th>合同名称</th>\
						<th>合同类型</th>\
						<th>合同时间</th>\
						<th>负责人</th>\
					</tr>\
					</thead>\
					<tbody></tbody> \
					</table>';
            	$("div#contract-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var contract = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="contract_pick" value="'+contract.id+'"/> </td> \
            		<td id="'+contract.id+'"> '+contract.number +'</span> </td> \
            		<td> '+contract.name +'</span> </td> \
            		<td> '+contract.type_value +' </td> \
            		<td> '+contract.contract_date +' </td> \
            		<td> '+contract.owner_name+'</td> \
            		</tr>';
            		
            		$("table#contract_pick_table").find("tbody").append(tableData);
                }
            	
            	var table = $('#contract_pick_table');
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
    //Initialize order pick dataTable
    var initOrderPickTable = function(table) {
    	
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
    //update requisition amounts
    var updateReqAmounts = function(base_total,due_total) {
    	var reqId = $('input[name="req[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "editReqAmounts.do",
            data: {base_total:base_total,
            	due_total:due_total,
            	reqId:reqId},
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
    //update requisition status
    var updateReqStatus = function(status_code) {
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
            		$('.alert-success span').text(responseJson.objname+"已成功！");
       	   	 	 	$('.alert-success').show();	
            	}else{
            		alert(responseJson.errmsg);
            	}
            	window.location.reload();      	   	 	 
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
    //update requisition info
    var updateOrderInfo = function(dataJson) {
    	var reqId = $('input[name="req[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateReqInfo.do",
            data: {customer_id:dataJson.customer_id,
            	receive_wh_id:dataJson.receive_wh_id,
            	owner_id:dataJson.owner_id,
            	subtype_code:dataJson.subtype_code,
            	comment:dataJson.comment,
            	ship_addr_id:dataJson.ship_addr_id,
            	bill_addr_id:dataJson.bill_addr_id,
            	man_number:dataJson.man_number,
            	receive_date:dataJson.receive_date,
            	related_order_id:dataJson.related_order_id,
            	reqId:reqId},
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
    //Generate available requisition items list dynamically
    var loadReqItems = function() {
    	var reqId = $('input[name="req[id]"]').val();
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
            data: {reqId:reqId},
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
            		//<td id='+item.item_contract_id+'> <a href="../contract/all_details.do?id='+item.item_contract_id+'">'+item.item_contract_number +'</a> </td>
            		tableData = '<tr id='+item.item_id+'> <td id='+item.item_prod_id+'><a href="../prodadmin/details.do?id='+item.item_prod_id+'">'+ item.item_prod_number +'</a> </td> \
            		<td> '+item.item_prod_name +' </td> \
            		<td> '+item.item_prod_model +' </td> \
            		<td> '+item.item_quantity+'</td> \
            		<td> '+item.item_comment+'</td>'
            		+editColumn+'</tr>';
            		
            		$("table.order-items").find("tbody").append(tableData);
            		
            		base_total = base_total + item.item_base_amount;
            		//tax_total = tax_total + item.item_tax_amount;
            		//discount_total = discount_total + item.item_discount_amount;
            		due_total = due_total + item.item_due_amount;
                }
            	$('a.delete-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		$('input[name="orderItem[id]"]').val(item_id);
            		//test
            		var product_name = $(this).parents('tr').children('td:eq(1)').text();
            		$('input[name="product[name]"]').val(product_name);
            		
            		
            	});
            	$('a.edit-item').click(function() {
            		var item_id = $(this).parents('tr').attr('id');
            		var product_id = $(this).parents('tr').children('td:eq(0)').attr('id');
            		var product_name = $(this).parents('tr').children('td:eq(1)').text();
            		var product_number = $(this).parents('tr').children('td:eq(0)').text();
            		var product_model = $(this).parents('tr').children('td:eq(2)').text();
            		var item_comment = $(this).parents('tr').children('td:eq(4)').text();
            		var quantity = $(this).parents('tr').children('td:eq(3)').text()*1;
            		//var purchase_price = $(this).parents('tr').children('td:eq(1)').text()*1;
            		//var tax_amount = $(this).parents('tr').children('td:eq(5)').text()*1;
            		//var tax_ratio = $(this).parents('tr').children('td:eq(6)').text()*1;
            		//var discount_amount = $(this).parents('tr').children('td:eq(7)').text()*1;
            		//var purchase_amount = $(this).parents('tr').children('td:eq(3)').text()*1;
            		//var base_amount = (base_price * quantity).toFixed(2);
            		//var discount_ratio = (discount_amount*100/base_amount).toFixed(2);
            		
            		$('#edit-item').find('input[name="product[id]"]').val(product_id);
            		$('#edit-item').find('input[name="product[name]"]').val(product_name);
            		//$('#edit-item').find('input[name="warehouse[id]"]').val(warehouse_id);
            		//$('#edit-item').find('input[name="warehouse[name]"]').val(warehouse_name);
            		//$('#edit-item').find('input[name="contract[id]"]').val(contract_id);
            		//$('#edit-item').find('input[name="contract[number]"]').val(contract_number);
            		$('#edit-item').find('input[name="product[model]"]').val(product_model);
            		$('#edit-item').find('input[name="product[number]"]').val(product_number);
            		$('#edit-item').find('input[name="order_item[id]"]').val(item_id);
            		$('#edit-item').find('textarea[name="item_comment"]').val(item_comment);
            		$('#edit-item').find(".purchase-quantity").spinner("value",quantity);
            		//$('#edit-item').find('input[name="tax_amount"]').val(tax_amount);
            		//$('#edit-item').find('input[name="tax_percent"]').val(tax_ratio);
            		//$('#edit-item').find('input[name="discount_amount"]').val(discount_amount);
            		//$('#edit-item').find('input[name="base_amount"]').val(base_amount);
            		//$('#edit-item').find('input[name="purchase_amount"]').val(purchase_amount);
            		//$('#edit-item').find('input[name="discount_percent"]').val(discount_ratio);
            	});
            	
            	//$('div.base-total').text(base_total.toFixed(2));
            	//$('div.tax-total').text(tax_total.toFixed(2));
            	//$('div.discount-total').text(discount_total.toFixed(2));
            	//$('div.due-total').text(due_total.toFixed(2));
            	//$('div.static-total-due').text(due_total.toFixed(2));
            	//updateReqAmounts(base_total,due_total);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //handle stock in requisition history list
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
    	
    	var reqId = $('input[name="req[id]"]').val();

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
                    "url": "req_history_listdata.do?reqId="+reqId, // ajax source
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
    //handle stock in requisition approval history list
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
    	
    	var reqId = $('input[name="req[id]"]').val();

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
                    "url": "req_approval_history_listdata.do?reqId="+reqId,// ajax source
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
    	var reqId = $('input[name="req[id]"]').val();
    	var type = "Requisition";
    	var from_data = {
    			reqId:reqId,
    			type:type
    	 };
    	$.ajax({
            type: "POST",
            url: "queryApprovalStatus.do",
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
    
	return {
		init: function(){
			
			initStockInViewEvents();
			loadReqItems();
			handleHistory();
			handleApprovalHistory();
		},
		
	};

}();