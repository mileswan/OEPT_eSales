/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/18
 * Description: Products events handlers.
 */
var ProductsEvents = function(){
	var catId;
	// initialize events function for all elements.
    var initProductsEvents = function() {
    	
    	$('#new-prod-confirm').click(function() {
    		addProduct();
	    });
    	
    	$('a.submit-prod').click(function() {
    		
        	//提交之前保存事件
        	
        	if($("[name='product[name]']").val()==""){
        		alert("产品名称不能为空");
        	}else if($("[name='product[number]']").val()==""){
        		alert("产品编号不能为空");
        	}else if($("[name='product[model]']").val()==""){
        		alert("产品型号不能为空");
        	}else{
        		updateRecords();
        	}
    		updateProdStatus("submitted");
    	});
    	
    	$('a.approve-prod').click(function() {
    		updateProdStatus("Published");
    	});
    	$('a.reject-prod').click(function() {
    		updateProdStatus("Rejected");
    	});
    	$('#reopen-product').click(function() {
    		updateProdStatus("Reopen");
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
    	
    	$('#update-confirm').click(function(e) {
    		if($("[name='product[name]']").val()==""){
    			alert("产品名称不能为空");
    		}else if($("[name='product[number]']").val()==""){
    			alert("产品编号不能为空");
    		}else if($("[name='product[model]']").val()==""){
    			alert("产品型号不能为空");
    		}else{
    			updateRecords();
    		}
	    });
    
    	$('button.reset').click(function() {
	    	window.location.reload();
	    });
    	
    	$('button.remove-image-confirm').click(function() {
    		removeImage();
	    });
    	
    	//查看审核流程状态
    	$('a.queryApprovalStatus').click(function(){
    		queryApprovalStatus();
    	});
    	
    	//新建产品产品类型选择
    	$('.modal-body').find('.categories').click(function(){
    		$('select.categories').val($(this).val());
    	});
    	
    	$('#prod-number').blur(function(){
    		var param = 'num';
    		queryAddRepetition(param);
    	});
    	$('#prod-model').blur(function(){
    		var param = 'model';
    		queryAddRepetition(param);
    	});
    }
    
    //queryAddRepetition
    var queryAddRepetition = function(obj){
    	var num = "";
    	var model = "";
    	if(obj == 'num'){
    		num = $('#prod-number').val();
    	}else if(obj == 'model'){
    		model = $('#prod-model').val();
    	}
    	if(num==""&model==""){
    		$('#message').attr('style','display: none;');
    		$('#messages1').text("");
    	}else{
    		$.ajax({
                type: "POST",
                url: "queryAddRepetition.do",
                data: {num:num,
                	model:model},
                dataType: "text",
                success: function (res) {	
                	if(res == 1){
    					$('#message').removeAttr('style');
    					if(num!=""){
    						$('#messages1').text("产品/服务编号  已存在，请重新输入！");
    					}
    					if(model!=""){
        					$('#messages1').text("产品型号  已存在，请重新输入！");
    					}
    			    }
                	if(res == 0){
                		$('#message').attr('style','display: none;');
                		$('#messages1').text("");
                	}
                },
                error: function(xhr, textStatus, thrownError) {
                	alert("errorinfo");
               	 alert(thrownError);
                }
            });
    	}
    	
    }
    
    //update product status
    var updateProdStatus = function(status_code) {
    	var productId = $("[name='product[id]']").val();
    	
    	
		$.ajax({
            type: "POST",
            url: "updateProdStatus.do",
            data: {status_code:status_code,
            	productId:productId},
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
            		alert("before");
            		alert(responseJson.errmsg);
            		alert("after");
            	}
            },
            error: function(xhr, textStatus, thrownError) {
            	alert("errorinfo");
           	 alert(thrownError);
            },
            beforeSend:function(){
            	//这里是开始执行方法，显示效果，效果自己写
            	$('div.product-content').html('<div class="col-md-12">请求处理中，请耐心等候……</div>');
            	$('div.product-content').append('<img src="../img/loading.gif">');
            	$('html, body').animate({scrollTop:0}, 'slow');
            }
        });
    }
    //Create new product
    var addProduct = function() {
		var number = $("#prod-number").val();
		var name = $("#prod-name").val();
		var description = $("#prod_desc").val();
		var categoryId = $("div.modal-body select").val();
		if(categoryId==""||categoryId==null){
			categoryId = 2;
		}
		var model = $("#prod-model").val();
		var sku = $('#prod-sku').val();
		if(name==""||name==null){
			$('.alert-success span').text("产品名称不能为空！");
   	 	 	$('.alert-success').show();
		}else if(model==""||model==null){
			$('.alert-success span').text("产品型号不能为空！");
   	 	 	$('.alert-success').show();
		}else{
			$.ajax({
	            type: "POST",
	            url: "create.do",
	            data: {categoryId:categoryId,
	            	   name:name,
	            	   number:number,
	            	   description:description,
	            	   model:model,
	            	   sku:sku},
	            dataType: "text",
	            success: function (result) {	
	            	var responseJson = eval ("(" + result + ")");
	            	if( responseJson.id ){
	            		$('.alert-success span').text("产品创建成功！");
	       	   	 	 	$('.alert-success').show();
						var checkCatId = $('#checkCatId').val();
						tableReload(checkCatId);
	            	}else{
	            		$('.alert-danger span').text("产品创建失败，请重新操作！");
	            		$('.alert-danger').show();
	            	} 
	            },
	            error: function(xhr, textStatus, thrownError) {
	           	 alert(textStatus);
	            }
	        });
		}
		setTimeout(function(){
			$('.alert-success').fadeOut();
		},2000);
    }
  //Generate categories list dynamically
    var loadCategoriesData = function() {
			
		$.ajax({
            type: "POST",
            url: "loadcat.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var list = '<li> <label><input type="radio" name="product[category]" value="1">无</label> </li>';
            	var cat = new Array();
            	var catWrapper = '<ul class="list-unstyled"> </ul>';
            	
            	for(var i=0;i<responseJson.length;i++){
            		var category = responseJson[i];
            		var catIndex = category.parentCatId;
                    if(category.hierlvl == "1"){

                    	list = list + '<li><label><input type="radio" name="product[category]" value="'+category.id+'">'+category.name+'</label></li>';
                    	$("ul.categories").html(list);
                    }else{
                    	
                    	var parentCat = $("ul.categories").find('[value="'+category.parentCatId +'"]').parent().parent();
                    	if( !(parentCat.children("ul.list-unstyled").length > 0) ){
                    		parentCat.append(catWrapper);
                    	}
                    	cat[catIndex] = '<li> <label><input type="radio" name="product[category]" value="'+category.id+'">'+category.name+'</label> </li>';
                    	parentCat.children("ul.list-unstyled").append(cat[catIndex]);
                    }
                }
            	
            	var prodcatid = $('span.category-id').text();
            	$("ul.categories").find('[value="'+ prodcatid +'"]').attr("checked",true);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    //Generate categories list dynamically in new product dialog
    var loadNewProdCategoriesData = function() {
			
		$.ajax({
            type: "POST",
            url: "loadcat.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var cat = new Array();
            	var option = '';
            	
            	//$("select.categories").append('<option value="">所有类别</option>');
            	for(var i=0;i<responseJson.length;i++){
            		var category = responseJson[i];
            		var catIndex = category.parentCatId;
                    if(category.hierlvl == "1"||category.hierlvl == "0"){
                    	option = '<option value="'+category.id+'">'+category.name+'</option>';
                    	$("select.categories").append(option);
                    }else{
                    	var parentCat = $("select.categories").find('option[value="'+category.parentCatId +'"]');
                    	var indent = '';
                    	for(var j=0;j<category.hierlvl*3;j++){
                    		indent = indent + '&nbsp;'
                    	}
                    	cat[catIndex] = '<option value="'+category.id+'">'+category.name+'</option>';
                    	parentCat.after(cat[catIndex]);
                    	$("select.categories").find('option[value="'+category.id +'"]').prepend(indent);
                    }
                }
            	
            	$('select.category-search').append($("select.categories").html());
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
  //update action
    var updateRecords = function() {
		
    	$('select[name="product[status]"]').removeAttr('disabled');
		$.ajax({
            type: "POST",
            url: "update.do",
            data: $("#update_form").serialize(),
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功更新！");
    	   	 	 $('.alert-success').show();
    	   	 	setTimeout(function(){
    	   	 		$('.alert-success').fadeOut();
    	   	 		window.location.reload();
    			},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
  //Generate images list dynamically
    var loadImagesData = function() {
    	var productId = $("[name='product[id]']").val();
    	var readOnlyFlag = $('input[name="readOnlyFlag"]').val();
    	if(readOnlyFlag == 'true'){
			var deleteColumn = '<td></td>';
		}else{
			var deleteColumn = '<td> <a href="#remove-confirm" class="btn default btn-sm remove-button" data-toggle="modal"> <i class="fa fa-times"></i> 删除 </a> </td>';
		}
    	
		$.ajax({
            type: "POST",
            url: "loadimg.do",
            data: {productId:productId},
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
            		tableData = '<tr id="'+image.id+'"> <td><a href="..'+image.contextpath+'/'+image.original_filename +'" class="fancybox-button" data-rel="fancybox-button"> \
            		<img class="img-responsive" src="..'+image.contextpath+'/'+image.original_filename +'" alt=""></a> </td> \
            		<td> <span class="original_filename" style="display: none;">'+image.original_filename +'</span> \
            		<input type="text" class="form-control" name="product[images][id][]" style="display: none;" value="'+image.id+'"> \
            		<input type="text" class="form-control" name="product[images][name][]" value="'+image.filename+'"> </td> \
            		<td> <span class="context_path" style="display: none;">'+image.contextpath +'</span> <input type="text" class="form-control" name="product[images]['+image.id+'][filesize]" value="'+file_size+'" readonly> </td> \
            		<td> <label><input type="radio" name="product[images][image_type]['+image.id+']" value="Base Image"></label> </td> \
            		<td> <label><input type="radio" name="product[images][image_type]['+image.id+']" value="Small Image"></label> </td> \
            		<td> <label><input type="radio" name="product[images][image_type]['+image.id+']" value="Thumbnail image"></label> </td>'+deleteColumn+
            		'</tr>';
            		
            		$("table.product-images").find("tbody").append(tableData);
            		$("table.product-images").find('tr[id="'+image.id+'"]').find('input[value="'+ image.image_type +'"]').attr("checked",true);
                }
            	$('.fancybox-button').fancybox();
            	
            	$('a.remove-button').click(function() {
        	    	$('span.remove_image_id').text( $(this).parents('tr').attr('id') );
        	    	$('span.remove_image_filename').text( $(this).parents('tr').find('span.original_filename').text() );
        	    	$('span.remove_image_filepath').text( $(this).parents('tr').find('span.context_path').text() );
        	    });
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
  //delete action
    var removeImage = function() {
		var image_id = $('span.remove_image_id').text();
		
		$.ajax({
            type: "POST",
            url: "delImage.do",
            data: {image_id:$('span.remove_image_id').text(),
            	image_filename:$('span.remove_image_filename').text(),
            	remove_image_filepath:$('span.remove_image_filepath').text()},
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
    	   	 	 $('.alert-success span').text(responseJson.objname+"已成功删除！");
    	   	 	 $('.alert-success').show();
    	   	 	 $("table.product-images").find('tr[id="'+image_id+'"]').remove();
    	   	 	setTimeout(function(){
            		$('.alert-success').fadeOut();
        		},2000);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    
//    //加载类别tree
//    var loadCategoriesTree = function(){
//    	$.ajax({
//            type: "POST",
//            url: "loadProdCat.do",
//            dataType: "text",
//            success: function (result) {	
//            	var responseJson = eval ("(" + result + ")");
//            	for(var i=0;i<responseJson.length;i++){
//            		var prodCat = responseJson[i];
//            		if(i == 0){
//	            		for(var j=0;j<prodCat.length;j++){
//	            			var pc = prodCat[j];
//	            			alert("pc.name:"+pc.name);
//	            			tableData = '<ul>\
//	            				<li id="'+pc.id+'" data-jstree="{ "opened" : true }"><a\
//								id="lia'+pc.id+'">'+pc.name+'</a> \
//								</li>\
//								</ul>';
//	    		
//	            			$("#tree_1").append(tableData);
//	            		}
//            		}
//            	}
//            },
//            error: function(xhr, textStatus, thrownError) {
//           	 alert(textStatus);
//            }
//        });
//    }
    var handleApprovalListTable = function() {
        var grid = new Datatable();

        grid.init({
            src: $("#approval_datatable_products"),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            loadingMessage: '加载数据中...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",

                "lengthMenu": [
                    [10, 20, 50, 100, 150],
                    [10, 20, 50, 100, 150] // change per page values here 
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": "listdata_approval.do", // ajax source
                },
                "order": [
                    [6, "desc"]
                ] // set first column as a default sort by asc
            }
        });

         // handle group actionsubmit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
            e.preventDefault();
            var action = $(".table-group-action-input", grid.getTableWrapper());
            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
                grid.setAjaxParam("customActionType", "group_action");
                grid.setAjaxParam("customActionName", action.val());
                grid.setAjaxParam("id", grid.getSelectedRows());
                grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
            } else if (action.val() == "") {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'No record selected',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
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

//        $(".datetime-picker").datetimepicker({
//            isRTL: Metronic.isRTL(),
//            autoclose: true,
//            todayBtn: true,
//            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
//            minuteStep: 10
//        });
    	
    	var grid = new Datatable();
    	
    	var productId = $("[name='product[id]']").val();

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
                    "url": "approval_history_listdata.do?id="+productId, // ajax source
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
    	var productId = $("[name='product[id]']").val();
    	var type = "Product";
    	var from_data = {
    			productId:productId,
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
    
    //table列表刷新方法
    var tableReload = function(obj){
    	var catId = obj;
    	$('#categoryIdValue').val(catId);
    	document.getElementById("customButton2").click();
    	$('select.categories').val(catId);
    	$('#checkCatId').val(catId);
    }
    
    
	return {
		init: function(){
			initProductsEvents();
			handleApprovalHistory();
		},
		
		loadCategories: function(){
			loadCategoriesData();
		},
		
		loadNewProdCategories: function(){
			loadNewProdCategoriesData();
		},
		
		loadImages: function(){
			loadImagesData();
		},
		
		handleApprovalList: function(){
			handleApprovalListTable();
		}
	};

}();
var catId;
//产品tree方法
//(调用模板写好的方法，迫不得已)
function detail2(obj){
	catId = obj;
	$('#categoryIdValue').val(catId);
	document.getElementById("customButton2").click();
	$('select.categories').val(catId);
	$('#checkCatId').val(catId);
}

//跳转更改 产品类别页面
function updateCategory(){
	if(catId!=""&&catId!=null){
		window.location.href="../category/details.do?categoryId="+catId+""; 
	}else{
  	 	 $('.alert-success span').text("请先选择一个产品类别！");
  	 	 $('.alert-success').show();
  	 	setTimeout(function(){
   		$('.alert-success').fadeOut();
		},2000);
	}
}