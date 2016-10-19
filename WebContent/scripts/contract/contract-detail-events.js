/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description: Contract detail view events handlers.
 */
var ContractEvents = function(){
	// initialize events function for all elements.
    var initContractEvents = function() {
    	
    	$('#update-contract-confirm').click(function() {
    		updateContract();
	    });
    	
    	$('button.remove-image-confirm').click(function() {
    		removeAttach();
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
    //Update contract
    var updateContract = function() {
		
		$.ajax({
            type: "POST",
            url: "update.do",
            dataType: "text",
            data: $('#update_contract_form').serialize(),
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	if( responseJson.objname ){
            		$('.alert-success span').text("合同更新成功！");
       	   	 	 	$('.alert-success').show();
            	}else{
            		$('.alert-danger span').text("合同更新失败，请重新操作！");
            		$('.alert-danger').show();
            	}
            	
            	setTimeout(function(){
            		$('.alert-success').fadeOut();
            		$('.alert-danger').fadeOut();
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
    //Handle attachments upload
    var handleAttchments = function() {

    	var contractId = $("input[name='contract[id]']").val();
        // see http://www.plupload.com/
        var uploader = new plupload.Uploader({
            runtimes : 'html5,flash,silverlight,html4',
             
            browse_button : document.getElementById('tab_images_uploader_pickfiles'), // you can pass in id...
            container: document.getElementById('tab_images_uploader_container'), // ... or DOM Element itself
             
            url : "upload.do?contractId="+contractId,
            
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
    	var contractId = $("[name='contract[id]']").val();
    	
		$.ajax({
            type: "POST",
            url: "loadattach.do",
            data: {contractId:contractId},
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
            		
            		$("table.contract-attachments").find("tbody").append(tableData);
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
    	   	 	 $("table.contract-attachments").find('tr[id="'+image_id+'"]').remove();
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
			initContractEvents();
			handleAttchments();
			loadAttachmentsData();
		},
		
	};

}();