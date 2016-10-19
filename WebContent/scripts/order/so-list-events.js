/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/6
 * Description: Sales orders events handlers.
 */
var SOEvents = function(){
	// initialize events function for all elements.
    var initSOEvents = function() {
    	
    	$('#new-so-confirm').click(function() {
    		addSOrder();
	    });
    	
    	//test
    	$('#new-so-cancel').click(function(){
    		$('input[name="so[man-number]"]').val("");
    		$('input[name="so[customer-name]"]').val("");
    		$('textarea[name="so[description]"]').val("");
    		
    	});
    	
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	 
    	$('a.warehouse-pick').click(function(){
			loadAvailWarehouse();
    	});
    	$('button.warehouse-pick').click(function() {
    		var table=document.getElementById("warehouse-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var warehouse_name = table.rows[i].cells[2].innerText;
    				//for new dialog
    				$('input[name="so[warehouse-id]"]').val(id);
    				$('input[name="so[warehouse-name]"]').val(warehouse_name);
    				break;
    			}
    		}
    	});
    	
    	$('a.customer-pick').click(function(){
    		loadAvailCustomer();
    	});
    	$('button.customer-pick').click(function() {
    		var table=document.getElementById("customer-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var supplier_name = table.rows[i].cells[2].innerText;
    				//for new dialog
    				$('input[name="so[customer-id]"]').val(id);
    				$('input[name="so[customer-name]"]').val(supplier_name);
    				break;
    			}
    		}
    	});
    }
    //Add new sales order
    var addSOrder = function() {
    	
		var man_number = $('input[name="so[man-number]"]').val();
		var customer_id = $('input[name="so[customer-id]"]').val();
		var desc = $('textarea[name="so[description]"]').val();
		var currency_code = $('select[name="so[currency]"]').val();
//		var warehouse_id = $('input[name="so[warehouse-id]"]').val();
//		if(warehouse_id==null || warehouse_id==""){
//			alert("发货仓库不能为空！");
//			return;
//		}
		if(customer_id==null || customer_id==""){
			alert("客户不能为空！");
			return;
		}
		
		$.ajax({
            type: "POST",
            url: "so_create.do",
            dataType: "text",
            data: {man_number:man_number,
            	desc:desc,
            	customer_id:customer_id,
            	currency_code:currency_code},
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if( responseJson.id ){
            		$('.alert-success span').text("销售订单创建成功！");
       	   	 	 	$('.alert-success').show();
            	}else{
            		$('.alert-danger span').text("销售订单创建失败，请重新操作！");
            		$('.alert-danger').show();
            	}
            	
            	setTimeout(function(){
        			window.location.reload();
        		},2000);
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
            	$("#warehouse-pick").find(".modal-body").html(tableWrapper);
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
								<th>客户名称</th>\
								<th>客户地址</th>\
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
    //initialize SO list table
    var initSOTable = function () {

        var table = $('#so_list');

        // begin first table
        table.dataTable({

            "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

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
			
			initSOTable();
			initSOEvents();
			
		},
		
	};

}();