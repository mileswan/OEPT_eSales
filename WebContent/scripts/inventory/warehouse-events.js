/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/12/21
 * Description: Warehouse events handlers.
 */
var WarehouseEvents = function(){
	// initialize events function for all elements.
    var initWarehouseEvents = function() {
    	
    	$('#new-warehouse-confirm').click(function() {
    		addWarehouse();
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
    	
    	$('a.address-pick').click(function(){
			loadAvailAddr();
    	});
    	$('button.address-pick').click(function() {
    		var table=document.getElementById("address-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var address_name = table.rows[i].cells[1].innerText + table.rows[i].cells[2].innerText
    				+ table.rows[i].cells[3].innerText;
    				//for new dialog
    				$('input[name="warehouse[address]"]').attr('id',id);
    				$('input[name="warehouse[address]"]').val(address_name);
    				//for detail form
    				$('input[name="warehouse[area_name]"]').val(address_name);
    				$('input[name="warehouse[address_id]"]').val(id);
    				break;
    			}
    		}
    		
    	});
    	
    	$('#delWarehouse-confirm').click(function() {
			deleteRecords();
	    });
    	
    	$('#update-warehouse-confirm').click(function() {
			updateRecords();
	    });
    	
    	new PCAS('location_p', 'location_c', 'location_a', '北京', '', '');
    	$('button.new-addr-submit').click(function() {
    		newAddressSubmit();
	    });
    	
    	$('#add-stock-info').find(".stock-quantity").spinner();
    	
    	$('a.product-pick').each(function() {
    		$(this).click(function(){
    			loadAllProd();
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
    		$('input[name="stock[product-id]"]').val(id);
    		$('input[name="stock[product-name]"]').val(name);
    		$('input[name="stock[product-price]"]').val(price);
    	});
    	
    	$('button.add-stock-info').click(function() {
    		addStockInfo();
    	});
    	
    	$('a.delete-stock-info').each(function() {
    		$(this).click(function() {
    			var item_id = $(this).parents('tr').attr('id');
    			$('#delete-stock-confirm').find('input[name="stockInfo[id]"]').val(item_id);
    		});
    	});
    	$('button.delete-stock-confirm').click(function() {
    		var item_id = $('input[name="stockInfo[id]"]').val();
    		delStockInfo(item_id);
    	});
    	$('a.edit-stock-info').each(function() {
    		$(this).click(function() {
    			var item_id = $(this).parents('tr').attr('id');
    			var product_id = $(this).parents('tr').children('td:eq(0)').attr('id');
    			var product_name = $(this).parents('tr').children('td:eq(0)').find('a').text();
    			var stock = $(this).parents('tr').children('td:eq(3)').text();
    			var sku = $(this).parents('tr').children('td:eq(4)').text();
    			$('#edit-stock-info').find('input[name="stockInfo[id]"]').val(item_id);
        		$('#edit-stock-info').find('input[name="stock[product-id]"]').val(product_id);
        		$('#edit-stock-info').find('input[name="stock[product-name]"]').val(product_name);
        		$('#edit-stock-info').find(".stock-quantity").spinner("value",stock);
        		$('#edit-stock-info').find('input[name="stock[sku]"]').val(sku);
    		});
    	});
    	$('button.edit-stock-info').click(function() {
    		editStockInfo();
    	});
    }
  //add a new stock info
    var addStockInfo = function() {
		
    	var product_id = $('#add-stock-info').find('input[name="stock[product-id]"]').val();
		if(product_id==null || product_id==""){
			alert("入库产品不能为空！");
			return;
		}
		
		$.ajax({
            type: "POST",
            url: "add_stock_info.do",
            data: $("#add-stock-info-form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功创建！");
    	   	 	 $('.alert-success').show();
    	   	 	setTimeout(function(){
    				window.location.reload();
    			},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
  //delete a stock info item
    var delStockInfo = function(item_id) {
		
		$.ajax({
            type: "GET",
            url: "del_stock_info.do",
            data: {item_id:item_id},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();
    	   	 	setTimeout(function(){
    				window.location.reload();
    			},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
  //edit stock info
    var editStockInfo = function() {
		
		$.ajax({
            type: "POST",
            url: "edit_stock_info.do",
            data: $("#edit-stock-info-form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 $('.alert-success').show();
    	   	 	$('html, body').animate({scrollTop:0}, 'slow');
    	   	 	setTimeout(function(){
    	   	 		$('.alert-success').fadeOut();
    			},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
  //Generate all products list dynamically
    var loadAllProd = function() {
    	
    	$.ajax({
            type: "GET",
            url: "loadAllProd.do",
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
    /**
     * 提交数据 addressList  newAddress
     */
    function newAddressSubmit(){
    	var from_data = {
    			contactName:$("#contactName").val(),
    			location_p:$("#location_p").val(),
    			location_c:$("#location_c").val(),
    			location_a:$("#location_a").val(),
    			detailsAddress:"",
    			zipcode:$("#zipcode").val(),
    			contactTel:$("#contactTel").val(),
    			country:$("#country").val()
    	 };
    	var url = "../user/newAddress.do";
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: from_data,
    		success: function(res){ 
    			if(res == "1"){
	    			$('.alert-success-addr span').text("保存成功！");
	       	   	 	$('.alert-success-addr').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success-addr').fadeOut();
	    				loadAvailAddr();
	     			},2000);
    		    }
    			if(res == "0"){
    				$('.alert-success-addr span').text("保存失败！");
	       	   	 	$('.alert-success-addr').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success-addr').fadeOut();
	     			},2000);
    			}
    			if(res == "2"){
    				$('.alert-success-addr span').text("已经存在该区域，请直接选择！");
	       	   	 	$('.alert-success-addr').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success-addr').fadeOut();
	     			},2000);
    			}
    		}
    	});
    }
  //Generate available address list dynamically
    var loadAvailAddr = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailArea.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<div class="table-toolbar"> \
            						<div class="row">\
            						<div class="col-md-6">\
										<div class="actions">\
											<a href="#new-addr-confirm" data-toggle="modal" class="btn green">\
												<i class="fa fa-plus"></i> 新建区域\
											</a>\
										</div>\
									</div>\
									</div>\
									</div> \
            		<table class="table table-striped table-bordered table-hover" id="address-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>省/直辖市</th>\
								<th>市/区</th>\
								<th>区/县</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#address-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var address = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="address_pick" value="'+address.addressId+'"/> </td> \
            		<td id="'+address.addressId+'"> '+address.province +'</span> </td> \
            		<td> '+address.city +' </td> \
            		<td> '+address.county +' </td> \
            		</tr>';
            		
            		$("table#address-pick-table").find("tbody").append(tableData);
                }
            	initAddrPickTable();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
    var addWarehouse = function() {
		var number = $("#warehouse-number").val();
		var name = $("#warehouse-name").val();
		if(number==null || number==""){
			alert("仓库编号不能为空！");
			return;
		}
		if(name==null || name==""){
			alert("仓库名称不能为空！");
			return;
		}
		var comment = $('#warehouse-description').val();
		var addressId = $('input[name="warehouse[address]"]').attr('id');
		var addressName = $('input[name="warehouse[address_name]"]').val();
		var contactName = $('#warehouse-contact-name').val();
		var contactPhone = $('#warehouse-contact-phone').val();
			
		$.ajax({
            type: "POST",
            url: "warehouse_create.do",
            data: {number:number,
            	   name:name,
            	   comment:comment,
            	   addressId:addressId,
            	   addressName:addressName,
            	   contactName:contactName,
            	   contactPhone:contactPhone},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if( responseJson.id ){
            		$('.alert-success span').text("仓库创建成功！");
       	   	 	 	$('.alert-success').show();
       	   	 	 	$('html, body').animate({scrollTop:0}, 'slow');
       	   	 	 	setTimeout(function(){
       	   	 	 		$('.alert-success').fadeOut();
       	   	 	 		window.location.reload();
       	   	 	 	},2000);
            	}else{
            		$('.alert-danger span').text("仓库创建失败，请重新操作！");
            		$('.alert-danger').show();
            		$('html, body').animate({scrollTop:0}, 'slow');
       	   	 	 	setTimeout(function(){
       	   	 	 		$('.alert-danger').fadeOut();
       	   	 	 		window.location.reload();
       	   	 	 	},2000);
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
		
    }
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
            url: "warehouse_delete.do",
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
    };
    //update action
    var updateRecords = function() {
    	var number = $("#warehouse-number").val();
		var name = $("#warehouse-name").val();
		if(number==null || number==""){
			alert("仓库编号不能为空！");
			return;
		}
		if(name==null || name==""){
			alert("仓库名称不能为空！");
			return;
		}
		$.ajax({
            type: "POST",
            url: "warehouse_update.do",
            data: $("#update_form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 $('.alert-success').show();
    	   	 	$('html, body').animate({scrollTop:0}, 'slow');
   	   	 	 	setTimeout(function(){
   	   	 	 		$('.alert-success').fadeOut();
   	   	 	 	},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    };
    
    var initAddrPickTable = function() {
    	var table = $('#address-pick-table');
    	
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
		
    };
  //handle stock history list
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
    	
    	var warehouse_id = $('input[name="warehouse_id"]').val();

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
                    "url": "history_listdata.do?warehouse_id="+warehouse_id, // ajax source
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
    };
    //initialize Stock In list table
    var initStockItemsTable = function () {

        var table = $('table.stock-items');

        // begin first table
        table.dataTable({

            "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

            "columns": [{
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
                },
                "emptyTable": "表格中无有效的记录",
                "infoEmpty": "记录为空 ",
                "zeroRecords": "找不到匹配的记录"
            },
            "columnDefs": [{  // set default column settings
                'orderable': false,
                'targets': [0]
            }, {
                "searchable": false,
                "targets": [0]
            }],
            "order": [
                [1, "asc"]
            ] // set first column as a default sort by asc
        });

        var tableWrapper = jQuery('#sample_1_wrapper');

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

        tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    };
  //initialize Stock In list table
    var initWarehouseStockListTable = function () {

        var table = $('#warehouse_stock');

        // begin first table
        table.dataTable({

            "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

            "columns": [{
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
                },
                "emptyTable": "表格中无有效的记录",
                "infoEmpty": "记录为空 ",
                "zeroRecords": "找不到匹配的记录"
            },
            "columnDefs": [{  // set default column settings
                'orderable': false,
                'targets': [0]
            }, {
                "searchable": false,
                "targets": [0]
            }],
            "order": [
                [1, "asc"]
            ] // set first column as a default sort by asc
        });

        var tableWrapper = jQuery('#sample_1_wrapper');

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

        tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    }
    
	return {
		init: function(){
			
			initWarehouseEvents();
			initStockItemsTable();
			initWarehouseStockListTable();
			handleHistory();
			
		}
		
	};

}();