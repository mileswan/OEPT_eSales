/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/2/24
 * Description: Tree events handlers.
 */
var TreeEvents = function(){
	// initialize events function for all elements.
    var initTreeEvents = function() {
    	var addrType;
    	$('a.from-c23a').click(function(){
			loadAvailAddr();
			addrType = 1;
    	});
    	$('a.from-c23s').click(function(){
			loadAvailAddr();
			addrType = 2;
    	});
    	$('a.from-c23b').click(function(){
			loadAvailAddr();
			addrType = 3;
    	});
    	$('a.from-c23a2').click(function(){
			loadAvailAddr();
			addrType = 11;
    	});
    	$('a.from-c23s2').click(function(){
			loadAvailAddr();
			addrType = 22;
    	});
    	$('a.from-c23b2').click(function(){
			loadAvailAddr();
			addrType = 33;
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
    			detailsAddress:$("#detailsAddress").val(),
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
    				loadAvailAddr(); 
    		    }
    			if(res == "0"){
    				alert("保存失败");
    			}
    		}
    	});
    }
  //Generate available address list dynamically
    var loadAvailAddr = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailAddr.do",
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
										<i class="fa fa-plus"></i> 新建地址\
									</a>\
								</div>\
							</div>\
							</div>\
							</div> \
		            		<table class="table table-striped table-bordered table-hover" id="address-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>地址全称</th>\
								<th>省份</th>\
								<th>城市</th>\
								<th>区/县</th>\
								<th>街道</th>\
								<th>邮编</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#address-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var address = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="address_pick" value="'+address.addressId+'"/> </td> \
            		<td id="'+address.addressId+'"> '+address.allAddress +'</span> </td> \
            		<td> '+address.province +' </td> \
            		<td> '+address.city +' </td> \
            		<td> '+address.county +' </td> \
            		<td> '+address.street+'</td> \
            		<td> '+address.zipcode+'</td> \
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
			
			initTreeEvents();
			
		}
		
	};

}();