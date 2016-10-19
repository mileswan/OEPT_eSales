/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/12/29
 * Description: Contact events handlers.
 */
var ContactEvents = function(){
	/**
	 * 初始化ContactEvents
	 */
	var initContactEvents = function(){
		
		//选择出生日期计算年龄(update)
		$('#updateBirthday').blur(function(){
			updateCountAge();
		});
		//选择出生日期计算年龄(add)
		$('#addBirthday').blur(function(){
			addCountAge();
		});
		//保存更改后的联系人信息
		$('#updateContactButton').click(function(){
			updateContact();
		});
		//取消后退（返回列表）
		$('#n-back').click(function() {
    		newBack();
	    });
		//选择地址
		$('a.address-pick').click(function(){
			loadAvailAddr();
    	});
		//选择地址
		$('button.address-pick').click(function() {
    		var table=document.getElementById("address-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var address_name = table.rows[i].cells[1].innerText;
    				
    				$('input[name="addAddressId"]').val(id);
        			$('input[name="addAddressName"]').val(address_name);
    				
    				
    			}
    		}
    		
    	});
		//选择单位
		$('a.account-pick').click(function(){
			loadAvailAccount();
    	});
		//选择单位
		$('button.account-pick').click(function() {
    		var table=document.getElementById("account-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
    				var address_name = table.rows[i].cells[1].innerText;
    				
    				$('input[name="addAccountId"]').val(id);
        			$('input[name="addAccountName"]').val(address_name);
    				
    				
    			}
    		}
    		
    	});
		//添加联系人信息
		$('button#addContactButton').click(function(){
			addContact();
    	});
		
		//更改联系人信息
		$('button#updateContactButton').click(function(){
			updateContact();
    	});
		
		//删除联系人
		$('#delContact').click(function(){
			delContact();
    	});
		
		new PCAS('location_p', 'location_c', 'location_a', '北京', '', '');
    	$('button.new-addr-submit').click(function() {
    		newAddressSubmit();
	    });
		
		//初始化计算年龄
		addCountAge();
		updateCountAge();
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
	
	/**
	 * 计算年龄(add)
	 */
	var addCountAge = function(){
		var addBirthday = $('#addBirthday').val();
		var bday = addBirthday.substr(0,4);
		var date = new Date();
		var year = date.getFullYear();
		if(addBirthday != ""){
			var resAge = year - bday;
			$('#addAge').val(resAge);
		}
	}
	
	/**
	 * 计算年龄(update)
	 */
	var updateCountAge = function(){
		var updateBirthday = $('#updateBirthday').val();
		var bday = updateBirthday.substr(0,4);
		var date = new Date();
		var year = date.getFullYear();
		if(updateBirthday != ""){
			var resAge = year - bday;
			$('#updateAge').val(resAge);
		}
	}
	
	/**
	 * 加载地址信息
	 */
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
            			<br>\
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
	/**
	 * 初始化地址table
	 */
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
	
	/**
	 * 加载单位信息
	 */
	var loadAvailAccount = function() {
    	
		$.ajax({
            type: "GET",
            url: "loadAvailAccount.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="account-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>单位名称</th>\
								<th>单位编号</th>\
								<th>地址</th>\
								<th>类型</th>\
								<th>办公电话</th>\
								<th>E-mial</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#account-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var account = responseJson[i];
            		
            		tableData = '<tr> <td><input type="radio" name="account_pick" value="'+account.accountId+'"/> </td> \
            		<td id="'+account.accountId+'"> '+account.accountName +'</span> </td> \
            		<td> '+account.accountNumber +' </td> \
            		<td> '+account.addressName +' </td> \
            		<td> '+account.accountType +' </td> \
            		<td> '+account.workphone+'</td> \
            		<td> '+account.email+'</td> \
            		</tr>';
            		
            		$("table#account-pick-table").find("tbody").append(tableData);
                }
            	initAccountPickTable();
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
	/**
	 * 初始化单位table
	 */
	var initAccountPickTable = function() {
    	var table = $('#account-pick-table');
    	
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
	
	/**
	 * 添加联系人信息
	 */
	var addContact = function(){
		var from_data = {
			addFirstname : $("#addFirstname").val(),
			addLastname : $("#addLastname").val(),
			addBirthday : $("#addBirthday").val(),
			addAge : $("#addAge").val(),
			addFax : $("#addFax").val(),
			addEmail : $("#addEmail").val(),
			addCellphone : $("#addCellphone").val(),
			addWorkphone : $("#addWorkphone").val(),
			addTitle : $("#addTitle").val(),
			addDepartment : $("#addDepartment").val(),
			addGender : $("input[name='addGender']:checked").val(), 
			addAddressId : $("#addAddressId").val(),
			addAccountId : $("#addAccountId").val(),
			addComment : $("#addComment").val()
		 };
		var url = "addContact.do";
		$.ajax({
			type: "POST",
			url: url,
			data: from_data,
			success: function(res){ 
				if(res == 1){
					alert("添加成功");
					window.location.href="list.do"; 
			    }
				if(res == 0){
					alert("添加失败");
				}
			}
		});
	}
    
	/**
	 * 更改的联系人信息
	 */
	var updateContact = function(){
		var from_data = {
			updateId : $("#updateId").val(),
			updateFirstname : $("#updateFirstname").val(),
			updateLastname : $("#updateLastname").val(),
			updateBirthday : $("#updateBirthday").val(),
			updateAge : $("#updateAge").val(),
			updateFax : $("#updateFax").val(),
			updateEmail : $("#updateEmail").val(),
			updateCellphone : $("#updateCellphone").val(),
			updateWorkphone : $("#updateWorkphone").val(),
			updateTitle : $("#updateTitle").val(),
			updateDepartment : $("#updateDepartment").val(),
			updateGender : $("input[name='updateGender']:checked").val(), 
			updateAddressId : $("#updateAddressId").val(),
			updateAccountId : $("#updateAccountId").val(),
			updateComment : $("#updateComment").val()
		 };
		var url = "updateContact.do";
		$.ajax({
			type: "POST",
			url: url,
			data: from_data,
			success: function(res){ 
				if(res == 1){
					alert("更新成功");
					window.location.href="list.do"; 
			    }
				if(res == 0){
					alert("更新失败");
				}
			}
		});
	}
	
	/**
	 * 删除联系人信息
	 */
	var delContact = function(){
		var table=document.getElementById("sample_1");
		var ids="";
		
		for(var i=0;i<table.rows.length;i++){		
			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
				if(ids == ""){
					ids = table.rows[i].cells[1].id;
				}else{
					ids = ids + "," + table.rows[i].cells[1].id;
				}
	        }
		}
		var from_data = {
			ids : ids
		 };
		var url = "delContact.do";
		$.ajax({
			type: "POST",
			url: url,
			data: from_data,
			success: function(res){ 
				if(res == 1){
					alert("删除成功");
					window.location.href="list.do"; 
			    }
				if(res == 0){
					alert("删除失败");
				}
			}
		});
	}
	
	/**
	 * 返回列表
	 */
	var newBack = function(){
		window.location.href="list.do"; 
	}
	return {
		init:function(){
			initContactEvents();
		}
	}
}();