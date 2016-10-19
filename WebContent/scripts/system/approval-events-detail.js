/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2016/01/07
 * Description: Contact events handlers.
 */

var ApprovalEvents = function(){
	//审核流程类型
	var approvalType = 0;
	//需要修改的div id
	var divId = "";
	//记录所选用户
	var allUser = "";
	var allUserId = "";
	//记录所有审核过程
	var divAppr = "";
	//计算审核过程的数量
	var count = 0;
	//审核过程的数组标记
	var countLvl = 0;
	//声明一个数组，用来存放动态添加的div
	var apprList = new Array();
	//提交类型
	submitType = "";
	
	/**
	 * 初始化ContactEvents
	 */
	var initApprovalEvents = function(){
		
		//选择审核对象
		$('.approvalObject').click(function(){
			approvalObject(this);
		});
		//增加审核流程
		$('a#addApproval').click(function(){
			//清除选择用户框内容
        	clearUserInput();
        	//初始化参数
        	allUser = "";
        	allUserId = "";
        	approvalType = 0;
        	//加载审核界面
			loadAvailUser();
			
    	});
		//增加审核流程，选择用户
		$('button#saveAppr').click(function() {
			loadApproval();
    	});
		//清除选择用户框内容
		$('a#clear').click(function() {
			clearUserInput();
		});
		//删除审核流程
		$('a.clearLoad').click(function() {
			clearDiv(this);
		});
		//保存整个审核流程
		$('#submitApprDetail').click(function(){
			submitApproval();
		});
		//选择用户
		$('button.user-pick').click(function() {
    		var table=document.getElementById("user-pick-table");
    		for(var i=1;i<table.rows.length;i++){		
    			if(table.rows[i].cells[0].getElementsByTagName('input')[0].checked){
    				var id = table.rows[i].cells[1].id;
    				var userName = table.rows[i].cells[1].innerText;
    				//拆解字符串，获取单个用户
    				var s,sv;
    				var sId;
    				s = allUser;
    				sv = s.split(";");
    				//是否为重复
    				var repeat = 1;
    				for(var j=0;j<sv.length;j++){
    					if(sv[j] == userName){
    						//为重复
    						repeat = 0;
    					}
    				}
    				//如果不重复，
    				if(repeat == 1){
    					allUser = allUser + userName + ";";
    					allUserId = allUserId + id + ";";
    					//添加审批人
        				$('input[name="approval-per"]').attr("id",allUserId);
            			$('input[name="approval-per"]').val(allUser);
    				}
    			}
    		}
    	});
		
		//产品、客户审核对象更改审核控制类型
		$('.approvalObject').click(function() {
			var type = $(this).attr('id');
			if(type == "Product"||type == "Account"){
				$('.input-group').find('#type1').attr('style','display: none');
				$('.input-group').find('#type2').removeAttr('style');
				$('#type2').find('span').attr('class','checked');
			}else{
				$('.input-group').find('#type2').attr('style','display: none');
				$('.input-group').find('#type1').removeAttr('style');
				$('#type1').find('#Submit').attr('checked','checked');
				$('#type1').find('span').each(function(){
					var span = $(this).find('#Submit').attr('checked');
					if(span == "checked"){
						$(this).attr('class','checked');
					}
				});
			}
		});
	}
	
	
	/**
	 * 清除选择用户框内容
	 */
	var clearUserInput = function(){
		allUser = "";
		allUserId = "";
		//清除
		$('input[name="approval-per"]').attr("id","");
		$('input[name="approval-per"]').val("");
	}
	/**
	 * 初始化
	 */
	var initMethod = function(){
		//删除审核流程
		$('a.clearLoad').click(function() {
			clearDiv(this);
		});
		//修改审核流程
		$('a.updateLoad').click(function(){
			//初始化参数
        	allUser = "";
        	allUserId = "";
			updateApprDiv(this);
		});
	}

	/**
	 * 选择审核对象
	 */
	var approvalObject = function(obj){
		$("#aObject").attr("value",$(obj).text());
		$("#aObject").attr("name",$(obj).attr("id"));
	}
	
	/**
	 * 动态改变步数
	 */
	var unStep = function(){
		//计步
		var i  = 0;
		$('.unStep').each(function(){
			i = i + 1;
			$(this).html("第"+i+"步");
			
		});
		//动态改变div元素值
		var j = 0;
		$('.approvalCourse-div').each(function(){
			j = j+1;
			$(this).attr('id','jq-div'+j+'');
			$(this).find('a.updateLoad').attr('id','updateApprDiv'+j+'');
			$(this).find('a.clearLoad').attr('id',''+j+'');
			$(this).attr('class','approvalCourse-div  updateApprDiv'+j+'');
		})
	}
	
	/**
	 * 更改审核流程
	 */
	var updateApprDiv = function(obj){
		//获取要更改的div内容，赋值给选择用户框显示
		//获取 update button id
		var id = $(obj).attr("id");
		//获取 div id
		divId = $('div.'+id+'').attr("id");
		//获取div内容
		var divInputValue = $('#'+divId+'').find('input[name="apprPerNames"]').val();
		var divInputValueId = $('#'+divId+'').find('input[name="apprPerNames"]').attr("id");
		var divInputValueItemId = $('#'+divId+'').find('input[name="itemValue"]').attr("id");
		$('input[name="approval-per"]').attr("id",divInputValueId);
		$('input[name="approval-per"]').val(divInputValue); 
		$('#methodDiv').find('span').removeAttr("class");
		$('input#'+divInputValueItemId+'').attr("checked","checked");
		$('label[name="'+divInputValueItemId+'"]').find('span').attr('class','checked');
		allUser = allUser + divInputValue;
		allUserId = allUserId + divInputValueId;
		//改变审核类型的值
		approvalType = 1;
		//加载审核界面
		loadAvailUser();
	}
	
	/**
	 * 删除审核流程
	 */
	var clearDiv = function(obj){
		//获取要删除div id
		var divId = $(obj).attr("id");
		var i  = 0;
		$('.approvalCourse-div').each(function(){
			i = i + 1;
			if(i == divId){
				//移除该div
				$(this).remove();
				count = count - 1;
				//审核过程数组中移除该数组
				apprList.splice(i,1);
				//重新计步
				unStep();
			}
		});
	}
	
	/**
	 * 保存整个审核流程
	 */
	var submitApproval = function(){
		//获取表单id
		var id = $('input[name="aName"]').attr('id');
		//获取全部表单信息
		var name = $('input[name="aName"]').val();
		var apprObject = $('#aObject').val();
		var apprObjectCd = $('#aObject').attr("name");
		var type;
		var typeCd;
		if(apprObjectCd == "Product"||apprObjectCd == "Account"){
			type = $('input[name="optionsRadiosType2"]:checked').val();
			typeCd = $('input[name="optionsRadiosType2"]:checked').attr("id");
		}else{
			type = $('input[name="optionsRadios"]:checked').val();
			typeCd = $('input[name="optionsRadios"]:checked').attr("id");
		}
		var rollback = $('input[name="optionsRadios2"]:checked').val();
		var rollbackCd = $('input[name="optionsRadios2"]:checked').attr("id");
		var email = $('#email_notify').attr("checked");
		var inbox = $('#inbox_notify').attr("checked");
		//遍历审核过程，获取未知的表单信息
		var users = "";
		var usersId = "";
		var method = "";
		var methodCd = "";
		$('.approvalCourse-div').each(function(){
			usersId = usersId + $(this).find('input.apprPerNames').attr('id') + "/";
			users = users + $(this).find('input.apprPerNames').val() + "/";
			methodCd = methodCd + $(this).find('input.itemValue').attr('id') + "/";
			method = method + $(this).find('input.itemValue').val() + "/";
		});
		var from_data = {
				name:name,
				apprObject:apprObject,
				apprObjectCd:apprObjectCd,
				type:type,
				typeCd:typeCd,
				rollback:rollback,
				rollbackCd:rollbackCd,
				email:email,
				inbox:inbox,
				users:users,
				usersId:usersId,
				method:method,
				methodCd:methodCd,
				id:id,
    	 };
		var url = "submitUpdateApproval.do";
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: from_data,
            dataType: "text",
    		success: function(result){ 
    			var responseJson = eval ("(" + result + ")");
    			if(responseJson.res == "1"){
    				alert("保存成功");
    				window.location.href="approval_rule_list.do"; 
    		    }
    			if(responseJson.res == "0"){
    				alert("保存失败");
    				window.location.href="approval_rule_list.do"; 
    			}
    			if(responseJson.res == "3"){
    				alert("该审核规则已存在");
    			}
    		},
            error: function(xhr, textStatus, thrownError, responseText) {
              	 alert(textStatus);
              	 alert(responseText.responseText);
               }
    	});
	}
	
	/**
	 * 加载审核流程
	 */
	var loadApproval = function(obj){
		var itemValue = $('input[name="optionsRadios3"]:checked').val();
		var itemValueId = $('input[name="optionsRadios3"]:checked').attr("id");
		var apprPerNames = $('input[name="approval-per"]').val();
		var apprPerNamesId = $('input[name="approval-per"]').attr("id");
		if(approvalType == 0){
			count = count + 1;
			var tableWrapper = '<div class="approvalCourse-div  updateApprDiv'+count+'" id="jq-div'+count+'">\
				<span class="unStep"></span>\
				'+apprPerNames+'('+itemValue+')\
				<input type="text" value="'+apprPerNames+'" name="apprPerNames" class="apprPerNames" id="'+apprPerNamesId+'" style="display: none;">\
				<input type="text" value="'+itemValue+'" name="itemValue" class="itemValue" id="'+itemValueId+'" style="display: none;">\
				<a href="#user-pick"  data-toggle="modal" class="btn default dark-stripe updateLoad" id="updateApprDiv'+count+'">修改 </a>\
				<a class="btn default dark-stripe clearLoad" id="'+count+'">删除 </a>\
			</div>\
				';
			apprList[count] = tableWrapper;
//			divAppr = divAppr + tableWrapper;
			divAppr = "";
			for(var i=0;i<apprList.length;i++){
				if(apprList[i] != null){
					divAppr = divAppr + apprList[i];
				}
			}
			$("#approvalCourse").find(".approvalCourse-body").html(divAppr);
			//动态改变步数
			unStep();
			//删除审批过程
			initMethod();
		}else{
			//重新写入div内容
			$('#'+divId+'').find('input[name="apprPerNames"]').val(apprPerNames);
			$('#'+divId+'').find('input[name="apprPerNames"]').attr("id",apprPerNamesId);
			var tableWrapper = '<span class="unStep"></span>\
					'+apprPerNames+'('+itemValue+')\
					<input type="text" value="'+apprPerNames+'" name="apprPerNames" class="apprPerNames" id="'+apprPerNamesId+'" style="display: none;">\
					<input type="text" value="'+itemValue+'" name="itemValue"  class="itemValue" id="'+itemValueId+'" style="display: none;">\
					<a href="#user-pick"  data-toggle="modal" class="btn default dark-stripe updateLoad" id="updateApprDiv'+count+'">修改 </a>\
					<a class="btn default dark-stripe clearLoad" id="'+count+'">删除 </a>\
					';
			var tableWrapper3 = '<div class="approvalCourse-div  updateApprDiv'+count+'" id="jq-div'+count+'">\
					<span class="unStep"></span>\
					'+apprPerNames+'('+itemValue+')\
					<input type="text" value="'+apprPerNames+'" name="apprPerNames" class="apprPerNames" id="'+apprPerNamesId+'" style="display: none;">\
					<input type="text" value="'+itemValue+'" name="itemValue" class="itemValue" id="'+itemValueId+'" style="display: none;">\
					<a href="#user-pick"  data-toggle="modal" class="btn default dark-stripe updateLoad" id="updateApprDiv'+count+'">修改 </a>\
					<a class="btn default dark-stripe clearLoad" id="'+count+'">删除 </a>\
				</div>\
					';
			$('#'+divId+'').html(tableWrapper);
			apprList[divId.substring(6,7)] = tableWrapper3;
//			count = count - 1;
			//动态改变步数
			unStep();
			//删除审批过程
			initMethod();
		}
	}
	/**
	 * 加载用户
	 */
	var loadAvailUser = function() {
		$('#methodDiv').find('span').removeAttr("class");
		$('input#Anyone Pass').attr("checked","checked");
		$('label[name="Anyone Pass"]').find('span').attr('class','checked');
		$.ajax({
            type: "GET",
            url: "../system/loadAvailUser.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var tableData = "";
            	var tableWrapper = '<table class="table table-striped table-bordered table-hover" id="user-pick-table"> \
							<thead> \
							<tr>\
								<th></th>\
								<th>用户名</th>\
								<th>姓名</th>\
								<th>职位</th>\
								<th>邮箱</th>\
								<th>地址</th>\
								<th>状态</th>\
							</tr>\
							</thead>\
							<tbody></tbody> \
							</table>';
            	$("#user-pick").find(".modal-body").html(tableWrapper);
            	for(var i=0;i<responseJson.length;i++){
            		var user = responseJson[i];
            		
            		tableData = '<tr > <td><input type="radio" name="user_pick" value="'+user.userId+'"/> </td> \
            		<td id="'+user.userId+'"> '+user.userName +'</span> </td> \
            		<td> '+user.firstName +' '+user.lastName+' </td> \
            		<td> '+user.position.positionName +' </td> \
            		<td> *** </td> \
            		<td> *** </td> \
            		<td> *** </td> \
            		</tr>';
            		
            		$("table#user-pick-table").find("tbody").append(tableData);
                }
            	initUserPickTable();
            	
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
	 }
	/**
	 * 初始化tableStyle
	 */
	var initUserPickTable = function() {
    	var table = $('#user-pick-table');
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
	//审批规则的详细信息
	var approvalDetail = function(obj){
		var dataValue = {id:obj};
		$.ajax({
            type: "GET",
            url: "../system/approvalDetail.do",
            data: dataValue,
            dataType: "text",
            success: function (result) {	
            	//返回Json
            	var responseJson = eval ("(" + result + ")");
            	//基本数据
            	$('input[name="aName"]').val(responseJson.rule_name);
            	$('#aObject').val(responseJson.object_name);
            	$('#aObject').attr('name',responseJson.object_cd);
            	$('.radio').find('span').removeAttr("class");
            	//更改对象类型
            	if(responseJson.object_cd=='Account'||responseJson.object_cd=='Product'){
                	$('#uniform-'+responseJson.action_cd+'').find('span').attr("class","checked");
                	$('#uniform-'+responseJson.action_cd+'').find('input[name="optionsRadiosType2"]').attr("checked","checked");
                	$('.input-group').find('#type1').attr('style','display: none');
    				$('.input-group').find('#type2').removeAttr('style');
            	}else{
                	$('#uniform-'+responseJson.action_cd+'').find('span').attr("class","checked");
                	$('#uniform-'+responseJson.action_cd+'').find('input[name="optionsRadios"]').attr("checked","checked");
                	$('.input-group').find('#type2').attr('style','display: none');
    				$('.input-group').find('#type1').removeAttr('style');
            	}
            	$('label[name="'+responseJson.rollback_type_cd+'"]').find('span').attr("class","checked");
            	$('label[name="'+responseJson.rollback_type_cd+'"]').find('input[name="optionsRadios2"]').attr("checked","checked");
            	if(responseJson.email == true){
            		$('#uniform-email_notify').find('span').attr("class","checked");
            		$('input[name="email_notify"]').attr("checked","checked");
            	}
            	if(responseJson.inbox == true){
            		$('#uniform-inbox_notify').find('span').attr("class","checked");
            		$('input[name="inbox_notify"]').attr("checked","checked");
            	}
            	//遍历div数据
            	for(var i=0;i<responseJson.approvalItem.length;i++){
            		//存入总数量
            		count = responseJson.approvalItem.length;
            		//计算步数
            		var step = i+1;
            		//方法名称
            		var itemValue = responseJson.approvalItem[i].method_name;
            		//方法code
            		var itemCode = responseJson.approvalItem[i].method_cd;
            		//遍历审核人
            		var apprPerName = "";
            		//遍历审核人id
            		var apprPerId = "";
            		for(var k=0;k<responseJson.approvalItem[i].approvalItemPer.length;k++){
            			apprPerName = apprPerName + responseJson.approvalItem[i].approvalItemPer[k].user.userName + ";";
            			apprPerId = apprPerId + responseJson.approvalItem[i].approvalItemPer[k].user.userId + ";";
            		}
            		//选中流程方式
            		$('#uniform-'+itemCode+'').find('span').attr("class","checked");
                	$('#uniform-'+itemCode+'').find('input[name="optionsRadios3"]').attr("checked","checked");
        			var tableWrapper = '<div class="approvalCourse-div  updateApprDiv'+step+'" id="jq-div'+step+'">\
		        				<span class="unStep"></span>\
        						'+apprPerName+'('+itemValue+')\
		        				<input type="text" value="'+apprPerName+'" name="apprPerNames" class="apprPerNames" id="'+apprPerId+'" style="display: none;">\
		        				<input type="text" value="'+itemValue+'" name="itemValue" class="itemValue" id="'+itemCode+'" style="display: none;">\
		        				<a href="#user-pick"  data-toggle="modal" class="btn default dark-stripe updateLoad" id="updateApprDiv'+step+'">修改 </a>\
		        				<a class="btn default dark-stripe clearLoad" id="'+step+'">删除 </a>\
		        				</div>\
		        				';
        			apprList[step] = tableWrapper;
        			divAppr = "";
        			for(var j=0;j<apprList.length;j++){
        				if(apprList[j] != null){
        					divAppr = divAppr + apprList[j];
        				}
        			}
            	}
    			$("#approvalCourse").find(".approvalCourse-body").html(divAppr);
    			//动态改变步数
    			unStep();
    			//删除审批过程
    			initMethod();
            }
		,
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
	}
	
	//页面加载时执行
	var pageLoad = function(){
		var id = $('input[name="aName"]').attr('id');
		//发送id
		if(id != ""){
			approvalDetail(id);
		}
	}
	
	return {
		init:function(){
			pageLoad();
			initApprovalEvents();
		}
	}
}();

