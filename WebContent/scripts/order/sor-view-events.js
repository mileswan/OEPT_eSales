/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/7
 * Description: Sales Return Orders view events handlers.
 */
var SORViewEvents = function(){
	// initialize events function for all elements.
    var initSORViewEvents = function() {
    	
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	
    	$("button.back").click(function() {
	    	window.location.href='so_return_list.do';
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
    		var table=document.getElementById("product_pick_table");
    		var id="";
    		var name="";
    		var price=0;

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				name = table.rows[i].cells[2].innerText;
    				price = table.rows[i].cells[4].innerText;
    			}
    		}
    		$('input[name="poItem[product-id]"]').val(id);
    		$('input[name="po[product-name]"]').val(name);
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
    	//Begin customer pick
    	$('a.customer-pick').click(function(){
    		loadAvailCustomer();
    	});
    	$('button.customer-pick').click(function() {
    		var table=document.getElementById("customer-pick-table");
    		var id="";

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				id = table.rows[i].cells[1].id;
    				var customerInfo = '<div class="row static-info"><div class="col-md-5 name">\
    					单位编号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					退货单位名称:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电子邮件:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					退货单位地址:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					电话号码:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div>';
    				$('div.customer-info').html(customerInfo);
    				break;
    			}
    		}
    		updateOrderInfo({"customer_id":id});
    	}); 
    	//End customer pick
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
    				var warehouseInfo = '<div class="row static-info"><div class="col-md-5 name">\
    					仓库编号:</div><div class="col-md-7 value">'+table.rows[i].cells[1].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					仓库名称:</div><div class="col-md-7 value">'+table.rows[i].cells[2].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					仓库联系人:</div><div class="col-md-7 value">'+table.rows[i].cells[4].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					联系人电话:</div><div class="col-md-7 value">'+table.rows[i].cells[5].innerText+'</div></div> \
    					<div class="row static-info"><div class="col-md-5 name">\
    					仓库地址:</div><div class="col-md-7 value">'+table.rows[i].cells[3].innerText+'</div></div>';
    				$('div.receive-wh-info').html(warehouseInfo);
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
    	$('a.sor-comment-update').click(function() {
    		var comm = $('textarea[name="order[description]"]').val();
    		updateOrderInfo({"comment":comm});
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
		var discount_percent = $('#add-item').find("input[name='discount_percent']").val()/100;
		
		$('#add-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#add-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		$('#add-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#add-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent - discount_percent)).toFixed(2) );
		//$('#add-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
    }
    //Update edit order item related data
    var updateEditItemData = function() {
    	var quantity = $('#edit-item').find('.purchase-quantity').find('input').val()*1;
		var price = $('#edit-item').find('input[name="purchase_price"]').val()*1;
		var tax_percent = $('#edit-item').find("input[name='tax_percent']").val()/100;
		var discount_percent = $('#edit-item').find("input[name='discount_percent']").val()/100;
		
		$('#edit-item').find('input[name="base_amount"]').val( (quantity * price).toFixed(2) );
		$('#edit-item').find('input[name="tax_amount"]').val( (quantity * price * tax_percent).toFixed(2) );
		$('#edit-item').find('input[name="discount_amount"]').val( (quantity * price * discount_percent).toFixed(2) );
		$('#edit-item').find('input[name="purchase_amount"]').val( (quantity * price * (1 + tax_percent - discount_percent)).toFixed(2) );
		//$('#edit-item').find('input[name="purchase_price"]').val( (price * (1 + tax_percent - discount_percent)).toFixed(2) );
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
    //Generate available customer list dynamically
    var loadAvailCustomer = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailCustomer.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="customer-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>单位编号</th>\
								<th>退货单位名称</th>\
								<th>退货单位地址</th>\
								<th>电话</th>\
								<th>Email</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#customer-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var customer = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="customer_pick" value="'+customer.accountId+'"/> </td> \
            		<td id="'+customer.accountId+'"> '+customer.accountNumber +'</span> </td> \
            		<td> '+customer.accountName +' </td> \
            		<td> '+customer.addressName +' </td> \
            		<td> '+customer.workphone +' </td> \
            		<td> '+customer.email+'</td> \
            		</tr>';
            		
            		$("table#customer-pick-table").find("tbody").append(tableData);
                }
            	
            	var table = $('#customer-pick-table');
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
						<th>单价</th>\
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
            		<td> '+product.price.toFixed(2) +' </td> \
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
            	type_code:"Sales Return Order",
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		$('.alert-success span').text(responseJson.objname+"已成功！");
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
    //update sales return order info
    var updateOrderInfo = function(dataJson) {
    	var orderId = $('input[name="order[id]"]').val();
    	
		$.ajax({
            type: "POST",
            url: "updateSORInfo.do",
            data: {customer_id:dataJson.customer_id,
            	receive_wh_id:dataJson.receive_wh_id,
            	owner_id:dataJson.owner_id,
            	comment:dataJson.comment,
            	ship_addr_id:dataJson.ship_addr_id,
            	bill_addr_id:dataJson.bill_addr_id,
            	orderId:orderId},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if(responseJson.objname){
            		$('.alert-success span').text(responseJson.objname+"已成功更新！");
       	   	 	 	$('.alert-success').show();
       	   	 	 	setTimeout(function(){
       	   	 	 		window.location.reload();
       	   	 	 	},2000);
            	}else{
            		alert(responseJson.errmsg);
            	}
    	   	 	      	   	 	 
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
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
            		<td id='+item.item_warehouse_id+'> <a href="../inventory/warehouse_details.do?id='+item.item_warehouse_id+'">'+item.item_warehouse_name +'</a> </td> \
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
			
			initSORViewEvents();
			loadOrderItems();
			
		},
		
	};

}();