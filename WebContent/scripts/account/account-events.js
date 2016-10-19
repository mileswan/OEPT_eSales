/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/12/24
 * Description: Account events handlers.
 */
var AccountEvents = function(){
	
	var catId;		//类id
	var aId;		//单位id

	// initialize events function for all elements.
    var initAccountEvents = function() {
    	var addrType;
    	
    	$('#newAccountCatButton').click(function(){
        	addAccountCat();
    	});
    	//
    	$('a.from-c23a').click(function(){
			loadAvailAddr();
			addrType = 1;
    	});
    	$('a.from-c23a2').click(function(){
			loadAvailAddr();
			addrType = 11;
    	});
    	
    	//删除单位信息事件
    	$('#deleteAtButton').click(function(){
    		alert("系统升级中，请持续关注官方公告！");
//    		deleteAt();
    	});
    	//关闭窗口事件
    	$('a.closeViwe').click(function(){
    		closeViwe();
    	});
    	//显示添加单位信息
    	$('a.addAt').click(function(){
    		showAddAt();
    	});
    	//编辑单位信息保存事件
    	$('a.saveEdit').click(function(){
    		saveEdit();
    	});
    	$('button.address-pick').click(function() {
    		var table=document.getElementById("address-pick-table");

    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){

    				var id = table.rows[i].cells[1].id;
//    				var address_name = table.rows[i].cells[1].innerText;
    				var a_country = '中国';
    				var a_province = table.rows[i].cells[2].innerText;
    				var a_city = table.rows[i].cells[3].innerText;
    				var a_county = table.rows[i].cells[4].innerText;
    				if(addrType == 1){
    					$('input[name="address"]').attr('id',id);
        				$('input[name="address"]').val(a_country+a_province+a_city+a_county);
    				}
//    				if(addrType == 2){
//    					$('input[name="shipAddress"]').attr('id',id);
//        				$('input[name="shipAddress"]').val(address_name);
//    				}
//    				if(addrType == 3){
//    					$('input[name="billAddress"]').attr('id',id);
//        				$('input[name="billAddress"]').val(address_name);
//    				}
    				if(addrType == 11){
    					$('input[name="address2"]').attr('id',id);
        				$('input[name="address2"]').val(a_country+a_province+a_city+a_county);
    				}
//    				if(addrType == 22){
//    					$('input[name="shipAddress2"]').attr('id',id);
//        				$('input[name="shipAddress2"]').val(address_name);
//    				}
//    				if(addrType == 33){
//    					$('input[name="billAddress2"]').attr('id',id);
//        				$('input[name="billAddress2"]').val(address_name);
//    				}
    			}
    		}
    	});
    	new PCAS('location_p', 'location_c', 'location_a', '北京', '', '');
    	$('button.new-addr-submit').click(function() {
    		newAddressSubmit();
	    });
    }
    
    /**
     * 添加单位节点
     */
    var addAccountCat = function(){
    	var name = $("#acsName").val();
    	var from_data = {
    			catId : $("#acpName").val(),
    			catName : name
    	 };
    	var url = "addAccountCat.do";
    	if(name==""||name==null){
    		$('.alert-success span').text("单位名称不能为空！");
       	 	$('.alert-success').show();
       	 	setTimeout(function(){
       	 		$('.alert-success').fadeOut();
       	 		location.reload();
    		},2000);
    	}else{
    		$.ajax({
    			type: "POST",
    			url: url,
    			data: from_data,
    			success: function(res){ 
    				if(res == 1){
    					$('.alert-success span').text("创建成功！");
    			   	 	$('.alert-success').show();
    			    }
    				if(res == 0){
    					$('.alert-success span').text("创建失败！");
    			   	 	$('.alert-success').show();
    				}
    				setTimeout(function(){
    		   	 		$('.alert-success').fadeOut();
    					location.reload();
    				},2000);
    			}
    		});
    	}
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
    
    /**
     * 保存编辑
     */
    var saveEdit = function(){
    	var from_data = {
    			aName : $("#aName2").val(),
    			aNumber : $("#aNumber2").val(),
    			aType : $("#aType2").val(),
    			aAddrDet : $("#aAddrDet2").val(),
    			aAddress : $('input[name="address2"]').attr('id'),
    			aFax : $("#aFax2").val(),
    			aEmail : $("#aEmail2").val(),
    			aDesc : $("#aDesc2").val(),
    			aZipcode : $('input[name="aZipcode2"]').attr('id'),
    			aTel : $('input[name="aTel2"]').attr('id'),
    			aId : aId
    	 };
    	var url = "../account/saveEdit.do";
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: from_data,
    		success: function(res){ 
    			if(res == 1){
    				alert("保存成功");
    				detail(catId);
    				document.getElementById("m-add").style.display = "none";
    				document.getElementById("m-edit").style.display = "none";
    		    }
    			if(res == 0){
    				alert("保存失败");
    			}
    		}
    	});
    }
    
    /**
     * 删除单位信息
     */
   var deleteAt =  function(){
    	if(aId == null){
    		alert("请先选中一条信息！");
    	}else{
    		var from_data = {
    				aId : aId
    		 };
    		var url = "../account/deleteAt.do";
    		$.ajax({
    			type: "POST",
    			url: url,
    			data: from_data,
    			success: function(res){ 
    				if(res == 1){
    					alert("删除成功");
    					location.reload();
    			    }
    				if(res == 0){
    					alert("删除失败");
    				}
    			}
    		});
    	}
    }
   
   /**
    * 显示添加单位信息
    */
   var showAddAt = function(){
   	document.getElementById("m-add").style.display = "block";
   	document.getElementById("m-edit").style.display = "none";
   }

   
   /**
    * 关闭窗口
    */
   var closeViwe = function(){
   	document.getElementById("m-add").style.display = "none";
   	document.getElementById("m-edit").style.display = "none";
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
//    //请求加载树
//    var loadTree = function(){
//		var url = "../account/loadTree.do";
//		$.ajax({
//			type: "Get",
//			url: url,
//			data: "",
//			success: function(res){ 
//				var responseJson = eval ("(" + result + ")");
//				var tableWrapper = 
//			}
//		});
//    }
    
//    //页面加载时
//    var pageLoad = function(){
//    	loadTree();
//    } 
	return {
		init: function(){
//			pageLoad();
			initAccountEvents();
		}
		
	};

}();

var catId;		//类id
var aId;		//单位id
function detail(obj){
	catId = obj;
	aId = null;
	var typeFlag = $('input[name="typeFlag"]').val();
	var from_data = {
			catId : catId,
			typeFlag : typeFlag
	 };
	var url = "loadAccount.do";
	$.ajax({
        type: "POST",
        url: url,
        data: from_data,
        dataType: "text",
        success: function (result) {
        	var responseJson = eval ("(" + result + ")");
        	var tableData = "";
        	var tableWrapper = '<table class="table table-bordered table-hover" style=" position: relative; z-index: 1;" id="m-table-b-t"> \
						<thead> \
        				<tr>\
						<th>行号</th>\
						<th>单位编号</th>\
						<th>单位名称</th>\
						<th>区域</th>\
						<th>类型</th>\
        				<th>状态</th>\
        				<th>操作</th>\
        				</tr>\
						</thead>\
						<tbody></tbody> \
						</table>';
        	$("#m-table-b").find(".table-scrollable").html(tableWrapper);
        	for(var i=0;i<responseJson.length;i++){
        		var account = responseJson[i];
        		if(account.accountType == "客户"){
        			if(account.accountStatus=="Pending"){
            			account.accountStatus="待审核";
            			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-success">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-info">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else if(account.accountStatus=="Not Published"){
        				account.accountStatus="未发布";
            			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-success">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-warning">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else if(account.accountStatus=="Published"){
        				account.accountStatus="已发布";
            			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-success">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-success">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else if(account.accountStatus=="Rejected"){
        				account.accountStatus="已拒绝";
            			tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-success">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-danger">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}
        			
        		}
        		else if(account.accountType == "供应商"){
        			if(account.accountStatus=="Pending"){
        				account.accountStatus="待审核";
        				tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-warning">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-info">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else if(account.accountStatus=="Not Published"){
        				account.accountStatus="未发布";
        				tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-warning">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-warning">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else  if(account.accountStatus=="Published"){
        				account.accountStatus="已发布";
        				tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-warning">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-success">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}else  if(account.accountStatus=="Rejected"){
        				account.accountStatus="已拒绝";
        				tableData = '<tr id="a'+account.accountId+'" onclick="check('+account.accountId+')">\
    					<td>'+(i+1)+'</td>\
    					<td>'+account.accountNumber+'</td>\
    					<td>'+account.accountName+'</td>\
    					<td>'+account.address.country+account.address.province+account.address.city+account.address.county+'</td>\
    					<td><span class="label label-sm label-warning">'+account.accountType+'</span></td>\
    					<td><span class="label label-sm label-danger">'+account.accountStatus+'</span></td>\
    					<td><a href="accountDetail.do?accountId='+account.accountId+'" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></td>\
    					</tr>';
        			}
        			
        		}
        		
        		$("table#m-table-b-t").find("tbody").append(tableData);
            }
        },
        error: function(xhr, textStatus, thrownError) {
       	 alert(thrownError);
        }
    });
}

//产品tree方法
//(调用模板写好的方法，迫不得已)
function detail2(obj){
	catId = obj;
	$('#categoryIdValue').val(catId);
	document.getElementById("customButton2").click();
	$('select.categories').val(catId);
}
//查询所有产品
function allProd(){
	//
	location.reload();
}
//跳转更改 产品类别页面
function updateCategory(){
	window.location.href="../category/details.do?categoryId="+catId+""; 
}
//跳转删除类别action
function delCategory(){
	var from_data = {
			id : catId
	 };
	var url = "../prodadmin/deleteCategory.do";
	$.ajax({
		type: "GET",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("删除成功");
				location.reload();
		    }
			if(res == 0){
				alert("删除失败");
			}
		}
	});
}

/**
 * 新增选择父类名称
 */
function selectCname(){
	var url = "selectCname.do";
	$.ajax({
		type: "POST",
		url: url,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("acpName").value = dataObj.catId;
		}
	});
}


/**
 * 查询节点信息
 */
function selectAcDe(){
	var from_data = {
			catId : catId
	 };
	var url = "selectAcDe.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("acpName2").value = dataObj.catParNameId;
			document.getElementById("acsName2").value = dataObj.catName;
			document.getElementById("dataNone").value = dataObj.catId;
		}
	});
}

/**
 * 更改单位节点
 */
function updateAccountCat(){
	var from_data = {
			catId : $("#acpName2").val(),
			catName : $("#acsName2").val(),
			id : $("#dataNone").val()
	 };
	var url = "updateAccountCat.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				$('.alert-success span').text("修改成功！");
       	   	 	$('.alert-success').show();
     	   	 	setTimeout(function(){
     	   	 		$('.alert-success').fadeOut();
    				location.reload();
     			},2000);
		    }
			if(res == 0){
				$('.alert-success span').text("修改失败！");
       	   	 	$('.alert-success').show();
     	   	 	setTimeout(function(){
     	   	 		$('.alert-success').fadeOut();
     			},2000);
			}
		}
	});
}

/**
 * 删除单位tree node
 */
function deleteAcNode(){
	var from_data = {
			catId : catId
	 };
	var url = "deleteAcNode.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				alert("删除成功！");
				location.reload();
		    }
			if(res == 0){
				alert("删除失败！");
			}
		}
	});
}

/**
 * 选中指定行
 * @param obj
 */
function check(obj){
	if(aId == null){
		document.getElementById("a"+obj).style.backgroundColor = "#f5f5f5";
		aId = obj;
	}else if(aId != obj){
		document.getElementById("a"+obj).style.backgroundColor = "#f5f5f5";
		document.getElementById("a"+aId).style.backgroundColor = "#ffffff";
		aId = obj;
	}
}

/**
 * 编辑单位信息
 */
function editAt(){
	if(aId == null){
		alert("请先选中一条信息！");
	}else{
	document.getElementById("m-edit").style.display = "block";
	document.getElementById("m-add").style.display = "none";
	var from_data = {
			aId : aId
	 };
	var url = "editAt.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(msg){ 
			//解析json
			var dataObj = eval("("+msg+")");
			document.getElementById("aId2").value = dataObj.accountId;
			document.getElementById("aName2").value = dataObj.accountName;
			document.getElementById("aNumber2").value = dataObj.accountNumber;
			document.getElementById("aType2").value = dataObj.accountType;
			document.getElementById("aTel2").value = dataObj.aTel;
			document.getElementById("aFax2").value = dataObj.aFax;
			document.getElementById("aEmail2").value = dataObj.aEmail;
			document.getElementById("aDesc2").value = dataObj.aDesc;
			$('input[name="address2"]').val(dataObj.pAddr);
			$('input[name="shipAddress2"]').val(dataObj.pAddr);
			$('input[name="billAddress2"]').val(dataObj.pAddr);
			$('input[name="address2"]').attr('id',dataObj.pAddrId);
			$('input[name="aAddrDet2"]').attr('id',dataObj.pShipAddrId);
			$('input[name="aZipcode2"]').attr('id',dataObj.pBillAddrId);
		}
	});
	}
}

/**
 * 添加保存
 */
function saveAdd(){
	var from_data = {
			aName : $("#aName").val(),
			aNumber : $("#aNumber").val(),
			aType : $("#aType").val(),
			aAddrDet : $("#aAddrDet").val(),
			aAddress : $('input[name="address"]').attr('id'),
			aFax : $("#aFax").val(),
			aEmail : $("#aEmail").val(),
			aDesc : $("#aDesc").val(),
			aZipcode : $('input[name="aZipcode"]').val(),
			aTel : $('input[name="aTel"]').val(),
			catId : catId,
			aOtherAddr1 : $("#aOtherAddr1").val(),
			aOtherAddr2 : $("#aOtherAddr2").val()
	 };
	var url = "../account/saveAdd.do";
	$.ajax({
		type: "POST",
		url: url,
		data: from_data,
		success: function(res){ 
			if(res == 1){
				$('.alert-success span').text("创建成功！");
        		$('.alert-success').show();
				detail(catId);
				document.getElementById("m-add").style.display = "none";
				document.getElementById("m-edit").style.display = "none";
		    }
			if(res == 0){
				$('.alert-danger span').text("创建失败！");
        		$('.alert-danger').show();
			}
			if(res == 2){
				$('.alert-danger span').text("不能重复创建，请注意填写！");
        		$('.alert-danger').show();
			}
		}
	});
	setTimeout(function(){
		$('.alert-success').fadeOut();
		$('.alert-danger').fadeOut();
	},2000);
}

//保存之后清空输入框内容
function afterSave(){
	$("#aName").val("");//单位名称
	$("#aNumber").val("");//单位编号
	$("#aAddrDet").val("");//详细地址
	$('input[name="address"]').val("");//区域
	$("#aFax").val("");//传真
	$("#aEmail").val("");//E-Mail
	$("#aDesc").val("");//单位描述
	$("#aZipcode").val("");//邮政编码
	$("#aTel").val("");//联系电话
	$("#aOtherAddr1").val("");//其他地址1
	$("#aOtherAddr2").val("");//其他地址2
}