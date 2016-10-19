/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contract list events handlers.
 */
var ContractEvents = function(){
	// initialize events function for all elements.
    var initContractEvents = function() {
    	
    	$('#new-contract-confirm').click(function() {
    		addContract();
	    });
    	
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	
//    	if (jQuery().datepicker) {
//            $('.date-picker').datepicker({
//                rtl: Metronic.isRTL(),
//                orientation: "left",
//                autoclose: true,
//                language: 'zh-CN'
//            }).on('changeDate',function(ev){    	
//		    	 var end = $("#endtime").val();
//		    	 var start = $("#starttime").val();
//		    	  if(end<start && end != null && end !=""){
//		    	   alert("“有效结束日期 ”不能早于“有效结束日期 ” ！");	    	   
//		    	  }
//		    });
//	    };
    	if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true,
                language: 'zh-CN'
            });
	    };
	    
	    $("#contract_amount").inputmask('decimal', {
            rightAlignNumerics: false
        }); //1234567.89
    	
    	$('a.supplier-pick').click(function(){
    		loadAvailSupplier();
    	});
    	$('button.supplier-pick').click(function() {
    		var table=document.getElementById("supplier-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var supplier_name = table.rows[i].cells[2].innerText;
    				//for new dialog
    				$('input[name="contract[supplier_id]"]').val(id);
    				$('input[name="contract[supplier_name]"]').val(supplier_name);
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
    				var customer_name = table.rows[i].cells[2].innerText;
    				//for new dialog
    				$('input[name="contract[customer_id]"]').val(id);
    				$('input[name="contract[customer_name]"]').val(customer_name);
    				break;
    			}
    		}
    	});
    }
    //Add new contract
    var addContract = function() {
    	
		var number = $('input[name="contract[number]"]').val();
		var name = $('input[name="contract[name]"]').val();
		var amount = $('input[name="contract[amount]"]').val();
		var supplier_id = $('input[name="contract[supplier_id]"]').val();
		var customer_id = $('input[name="contract[customer_id]"]').val();
		var desc = $('textarea[name="contract[description]"]').val();
		var contract_date = $('input[name="contract[date]"]').val();
		var type_code = jQuery(".contract-type  option:selected").val();
		var type_value = jQuery(".contract-type  option:selected").text();
		if(supplier_id==null || supplier_id==""){
			alert("供应商不能为空！");
			return;
		}
		if(customer_id==null || customer_id==""){
			alert("客户不能为空！");
			return;
		}
		if(number==null || number==""){
			alert("合同编号不能为空！");
			return;
		}
		if(type_code==null || type_code==""){
			alert("合同类型不能为空！");
			return;
		}
		
		$.ajax({
            type: "POST",
            url: "create.do",
            dataType: "text",
            data: {number:number,
            	name:name,
            	customer_id:customer_id,
            	contract_date:contract_date,
            	amount:amount,
            	desc:desc,
            	supplier_id:supplier_id,
            	type_code:type_code,
            	type_value:type_value},
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if( responseJson.id ){
            		$('.alert-success span').text("合同创建成功！");
       	   	 	 	$('.alert-success').show();
            	}else{
            		$('.alert-danger span').text("合同创建失败，请重新操作！");
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
								<th>客户编号</th>\
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
    //initialize Contract list table
    var initContractTable = function () {

        var table = $('#contract_list');

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
			
			initContractTable();
			initContractEvents();
			
		},
		
	};

}();