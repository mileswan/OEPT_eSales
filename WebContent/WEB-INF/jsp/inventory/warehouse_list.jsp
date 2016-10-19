<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/12/18
 * Description:  Warehouse list management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 仓库管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="../assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<!-- BEGIN APPLICATION STYLES -->
<link href="../css/autods.css" rel="stylesheet" type="text/css"/>
<!-- END APPLICATION STYLES -->
<link rel="shortcut icon" href="../favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square"> 
<!-- BEGIN HEADER -->
<jsp:include page="../header.jsp" />
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<jsp:include page="../sidebar.jsp" />
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN DELETE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除</h4>
						</div>
						<div class="modal-body">
							 确认要删除所选内容？
						</div>
						<div class="modal-footer">
							<button type="button" id="delWarehouse-confirm" class="btn blue" data-dismiss="modal">删除</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END DELETE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN NEW WAREHOUSE CONFIRM MODAL FORM -->
			<div class="modal fade" id="new-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建仓库</h4>
						</div>
						<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<label class="control-label">仓库编号：<span class="required">* </span></label>
								<input type="text" class="form-control" placeholder="请输入仓库编号…" id="warehouse-number" name="warehouse[number]">
								<label class="control-label">仓库名称：<span class="required">* </span></label>
								<input type="text" class="form-control" placeholder="请输入仓库名称…" id="warehouse-name" name="warehouse[name]">
								<label>描述</label>
								<textarea class="form-control" rows="2"	placeholder="输入描述…" id="warehouse-description"></textarea>
								<div class="form-group">
															<label class="control-label">仓库区域：</label>
															<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择区域…" name="warehouse[address]" readonly>
																<span class="input-group-addon">
																	<a href="#address-pick" class="address-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																</span>
															</div>
															<label class="control-label">仓库详细地址：</label>
								<input type="text" class="form-control" placeholder="请输入详细地址…" name="warehouse[address_name]">
								</div>
								<label class="control-label">仓库联系人：</label>
								<input type="text" class="form-control" placeholder="请输入仓库联系人名称…" id="warehouse-contact-name" name="warehouse[contact_name]">
								<label class="control-label">联系人电话：</label>
								<input type="text" class="form-control" placeholder="请输入仓库联系人电话…" id="warehouse-contact-phone" name="warehouse[contact_phone]">
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="new-warehouse-confirm" class="btn blue" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END NEW WAREHOUSE CONFIRM MODAL FORM -->
			<!-- BEGIN ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<!-- BEGIN SUCCESS MESSAGE -->
						<div class="alert alert-success display-hide alert-success-addr"  style="display: none; width: 300px; position: absolute; z-index:9999; margin-left: 30%;margin-top: 2%;" >
							<button class="close" data-close="alert"></button>
							<span>成功修改!</span>
						</div>
						<!-- END SUCCESS MESSAGE -->
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择仓库区域</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue address-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END ADDRESS PICK MODAL FORM-->
			<!-- BEGIN NEW ADDRESS MODAL-->
			<div class="modal fade" id="new-addr-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" >
					<div class="modal-content">
						<form method="post" >
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建区域</h4>
						</div>
						<div class="modal-body">
<!-- 						<div> -->
<!-- 							<span>联系人：</span><br/> -->
<!-- 							<input id="contactName" name="" class="newAddressInput" type="text"  placeholder="请输入联系人"> -->
<!-- 						</div> -->
						<div>
     						<span>国家：</span><br/>
							<input id="country" name="" class="newAddressInput" type="text" value="中国" readonly="readonly">
     					</div>
						<div class="controls">
							<span>所在地区：</span><br/>
							<select name="location_p" id="location_p" class="newAssressSelect"></select>
    						<select name="location_c" id="location_c" class="newAssressSelect"></select>
    						<select name="location_a" id="location_a" class="newAssressSelect"></select>
     					</div>
<!--      					<div> -->
<!--      						<span>详细地址：</span><br/> -->
<!-- 							<input id="detailsAddress" name="" class="newAddressInput" type="text"  placeholder="请输入详细地址"> -->
<!--      					</div> -->
<!--      					<div> -->
<!--      						<span>邮编：</span><br/> -->
<!-- 							<input id="zipcode" name="" class="newAddressInput" type="text"  placeholder="请输入邮编" -->
<%-- 							onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="6"> --%>
<!--      					</div> -->
<!--      					<div> -->
<!--      						<span>电话号码：</span><br/> -->
<!-- 							<input id="contactTel" name="" class="newAddressInput" type="text"  placeholder="请输入电话号码，只能输入数字" -->
<%-- 							onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" maxlength="11"> --%>
<!--      					</div> -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue new-addr-submit" data-dismiss="modal">保存</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END NEW ADDRESS MODAL-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			仓库管理 <small>仓库列表</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/warehouse_list.do">仓库管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/warehouse_list.do">仓库列表</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
				<div class="alert alert-danger display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					<span>You have some form errors. Please check below.</span>
				</div>
				<div class="alert alert-success display-hide" style="display: none;">
					<button class="close" data-close="alert"></button>
					<span>成功修改!</span>
				</div>
				<!-- BEGIN Table ROWS -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN WAREHOUSE LIST TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>仓库列表
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#" class="reload">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
											<div class="actions">
												<a href="#new-confirm" data-toggle="modal" class="btn green">
													<i class="fa fa-plus"></i> 新建
												</a>
											</div>
									</div>
<!-- 									<div class="col-md-6"> -->
<!-- 											<div class="actions pull-right"> -->
<!-- 												<a href="#delete-confirm" data-toggle="modal" -->
<!-- 													class="btn red" id="delete_option" style="display: none;"> -->
<!-- 													<i class="fa fa-minus"></i> 删除项 -->
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 									</div> -->
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="sample_1">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/>
								</th>
								<th>
									 仓库编号
								</th>
								<th>
									 基本信息
								</th>
								<th>
									 仓库地址
								</th>
								<th>
									 仓库联系人
								</th>
								<th>
									 联系人电话
								</th>
							</tr>
							</thead>
							<tbody>
	                		<c:forEach var="warehouse" items="${warehouseList}">
	                 		<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%> 
	                		<tr class="odd gradeX">
	                			<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
	                    		<td id="${warehouse.id}"><a href="<%=path%>/inventory/warehouse_details.do?id=${warehouse.id}">${warehouse.number}</a></td>
	                    		<td>
	                    			仓库名称：${warehouse.name}<br>
	                    			仓库状态：
	                    			<c:choose>
										<c:when test="${warehouse.active == true}">
											<span class="label label-sm label-success">可用</span>
										</c:when>
										<c:otherwise>
											<span class="label label-sm label-danger">禁用</span>
										</c:otherwise>
									</c:choose>              		
								</td>
	                    		<td>
	                    		   	${warehouse.address_name}      								
	                    		</td>
	                    		<td>
	                    			${warehouse.primary_contact_name}
	                    		</td>
	                    		<td>
	                    			${warehouse.primary_contact_cellphone}			
	                    		</td>
	                  		</tr>	
							</c:forEach>		
							</tbody>
							</table>
						</div>
					</div>
					<!-- END WAREHOUSE LIST TABLE PORTLET-->
				</div>
			</div>
			<!-- END Table ROWS -->
			<!--  div class="clearfix" -->
			<!--  div-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../footer.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/table-managed.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/common-events.js" type="text/javascript"></script>
<script src="../scripts/inventory/warehouse-events.js" type="text/javascript"></script>
<!-- begin 省市级联js -->
<script src="../scripts/jquery/region_select.js" type="text/javascript"></script>
<!-- end -->
<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   navigationBar.activeInventoryMenu();
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   TableManaged.init();
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.inventory-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
	   CommonEvents.init();
	   WarehouseEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>