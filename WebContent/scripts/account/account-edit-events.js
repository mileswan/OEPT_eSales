/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2016/02/25
 * Description: Account edit events handlers.
 */
var AccountEditEvents = function(){
	
    var initAccountEditEvents = function() {
    	//保存事件
    	$('#update-confirm').click(function(){
    		saveEdit();
    	});
    	
    	//新建区域事件
    	$('button.new-addr-submit').click(function() {
    		newAddressSubmit();
	    });
    	//选择区域事件
    	$('a.address-pick').click(function(){
    		loadAvailAddr();
    	});
    	//提交单位
    	$('a.submit-account').click(function() {
    		saveEdit();
    		updateAccountStatus("submitted");
    	});
    	//审核通过
    	$('a.approve-account').click(function() {
    		//审核通过之前先保存事件
    		saveEdit();
    		updateAccountStatus("Published");
    	});
    	$('a.reject-account').click(function() {
    		updateAccountStatus("Rejected");
    	});
    	$('button#reopen-account').click(function() {
    		updateAccountStatus("Reopen");
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
    	
    	//查看审核流程状态
    	$('a.queryApprovalStatus').click(function(){
    		queryApprovalStatus();
    	});

    	new PCAS('location_p', 'location_c', 'location_a', '北京', '', '');
    	
    	//选择区域确认事件
    	$('button.address-pick').click(function() {
    		var table=document.getElementById("address-pick-table");

    		for(var i=1;i<table.rows.length;i++){	
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var address_name = table.rows[i].cells[1].innerText;
//    				var a_country = '中国';
//    				var a_province = table.rows[i].cells[2].innerText;
//    				var a_city = table.rows[i].cells[3].innerText;
//    				var a_county = table.rows[i].cells[4].innerText;
					$('input[name="account[addressId]"]').attr('value',id);
    				$('input[name="account[address]"]').val(address_name);
    			}
    		}
    	});
    }
    
  //更新单位状态
    var updateAccountStatus = function(status_code) {
    	var accountId = $("[name='account[id]']").val();
    	
		$.ajax({
            type: "POST",
            url: "updateAccountStatus.do",
            data: {status_code:status_code,
            	accountId:accountId},
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
            	}
            },
            error: function(xhr, textStatus, thrownError) {
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
    
  //查询审核流程
    var queryApprovalStatus = function (){
    	//
    	var accountId = $("[name='account[id]']").val();
    	var type = "Account";
    	var from_data = {
    			accountId:accountId,
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
    
    //加载单位类别
    var loadCategoriesData = function() {
			
		$.ajax({
            type: "POST",
            url: "../account/loadcat.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var list = '';
            	var cat = new Array();
            	var catWrapper = '<ul class="list-unstyled"> </ul>';
            	
            	
            	for(var i=0;i<responseJson.length;i++){
            		var category = responseJson[i];
            		var catIndex = category.catParId;
            		
                    if(category.catLvl == "1"){
                    	list = list + '<li><label><input type="radio" name="account[category]" value="'+category.catId+'">'+category.catName+'</label></li>';
                    	$("ul.categories").html(list);
                    }else{
                    	var parentCat = $("ul.categories").find('[value="'+category.catParId +'"]').parent().parent();
                    	if( !(parentCat.children("ul.list-unstyled").length > 0) ){
                    		parentCat.append(catWrapper);
                    	}
                    	cat[catIndex] = '<li> <label><input type="radio" name="account[category]" value="'+category.catId+'">'+category.catName+'</label> </li>';
                    	parentCat.children("ul.list-unstyled").append(cat[catIndex]);
                    }
                }
            	
            	var accountid = $('span.category-id').text();
            	$("ul.categories").find('[value="'+ accountid +'"]').attr("checked",true);
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    
    /**
     * 保存编辑
     */
    var saveEdit = function(){
    	var catId;
    	$("input[name='account[category]']").each(function(){
    		if($(this).attr('checked')){
    		catId = $(this).val();
    		}
    	});
    	var from_data = {
    			aId : $("input[name='account[id]']").val(),
    			aName : $("input[name='account[name]']").val(),
    			aNumber : $("input[name='account[number]']").val(),
    			aType : $("select[name='account[types]']").val(),
    			aTel :  $("input[name='account[tel]']").val(),
    			aFax :  $("input[name='account[fax]']").val(),
    			aZipcode :  $("input[name='account[zipcode]']").val(),
    			aEmail :  $("input[name='account[email]']").val(),
    			aAddrId :  $("input[name='account[addressId]']").val(),
    			aAddrDet :  $("input[name='account[addressDetail]']").val(),
    			aOtherAddr1 :  $("input[name='account[otherAddress1]']").val(),
    			aOtherAddr2 :  $("input[name='account[otherAddress2]']").val(),
    			aDesc :  $("#description").val(),
    			aComm :  $("#comment").val(),
    			aCat :  catId
    	 };
    	var url = "../account/saveEdit.do";
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: from_data,
    		success: function(res){ 	
            	if(res==1){
            		 $('.alert-success span').text("已成功更新！");
        	   	 	 $('.alert-success').show();
        	   	 	setTimeout(function(){
        	   	 		$('.alert-success').fadeOut();
        	   	 		window.location.reload();
        			},2000);
            	}else{
            		$('.alert-success span').text("更新失败！");
       	   	 	 	$('.alert-success').show();
       	   	 	 	setTimeout(function(){
       	   	 	 		$('.alert-success').fadeOut();
       	   	 	 		window.location.reload();
       	   	 	 	},2000);
            	}
    	   	 	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    
    /**
     * 提交数据 addressList  newAddress
     */
    function newAddressSubmit(){
    	var from_data = {
//    			contactName:$("#contactName").val(),
    			location_p:$("#location_p").val(),
    			location_c:$("#location_c").val(),
    			location_a:$("#location_a").val(),
    			detailsAddress:$("#detailsAddress").val(),
    			zipcode:$("#zipcode").val(),
//    			contactTel:$("#contactTel").val(),
    			country:$("#country").val()
    	 };
    	var url = "../user/newAddress.do";
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: from_data,
    		success: function(res){ 
    			if(res == "1"){
	    			$('.alert-success span').text("保存成功！");
	       	   	 	$('.alert-success').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success').fadeOut();
	    				loadAvailAddr();
	     			},2000);
    		    }
    			if(res == "0"){
    				$('.alert-success span').text("保存失败！");
	       	   	 	$('.alert-success').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success').fadeOut();
	    				loadAvailAddr();
	     			},2000);
    			}
    			if(res == "2"){
    				$('.alert-success span').text("已经存在该区域，请直接选择！");
	       	   	 	$('.alert-success').show();
	     	   	 	setTimeout(function(){
	     	   	 		$('.alert-success').fadeOut();
	    				loadAvailAddr();
	     			},2000);
    			}
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
    	
    	var accountId = $("[name='account[id]']").val();

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
                    "url": "approval_history_listdata.do?id="+accountId, // ajax source
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
    
    var handleApprovalListTable = function() {
        var grid = new Datatable();

        grid.init({
            src: $("#approval_datatable_accounts"),
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
                    "url": "../account/listdata_approval.do", // ajax source
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
    
  //Generate available address list dynamically
    var loadAvailAddr = function() {
		$.ajax({
            type: "GET",
            url: "../account/loadAvailAddr.do",
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
								<th>区域全称</th>\
								<th>省份</th>\
								<th>城市</th>\
								<th>区/县</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#address-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var address = responseJson[i];
            		//alert(responseJson);
            		
            		tableData = '<tr> <td><input type="radio" name="address_pick" value="'+address.addressId+'"/> </td> \
            		<td id="'+address.addressId+'"> '+address.allAddress +'</span> </td> \
            		<td> '+address.province +' </td> \
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
    
    var initAddrPickTable = function() {
    	var table = $('#address-pick-table');
    	
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
            }],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "全部"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,            
            "pagingType": "bootstrap_full_number",
            "language": {
                "search": "我的搜索: ",
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
    
    return {
    	init: function(){
    		initAccountEditEvents();
    		handleApprovalHistory();
    	},
    	loadCategories: function(){
    		loadCategoriesData();
    	},
		handleApprovalList: function(){
			
			handleApprovalListTable();
		
		}
    };
    
}();