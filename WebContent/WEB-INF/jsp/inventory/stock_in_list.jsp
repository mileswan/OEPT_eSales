<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/11
 * Description:  Stock In requisition list management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT EIMS - 库存管理</title>
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
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
			<!-- BEGIN NEW PO CONFIRM MODAL FORM -->
			<div class="modal fade" id="new-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建入库单</h4>
						</div>
						<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<label class="control-label">入库单人工编号：</label>
								<input type="text" class="form-control" placeholder="请输入入库单编号…" name="req[man-number]">
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">供应商：<span class="required">* </span></label> -->
<!-- 									<input type="text" name="po[supplier-id]" style="display: none;"> -->
<!-- 									<div class="input-group"> -->
<!-- 											<input type="text" class="form-control" placeholder="请选择供应商…" id="po-supplier-name" name="po[supplier-name]" readonly> -->
<!-- 											<span class="input-group-addon"> -->
<!-- 											<a href="#supplier-pick" class="supplier-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 											</a> -->
<!-- 											</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<label class="control-label">入库类型：<span class="required">* </span></label>
								<select class="form-control" name="req[type]">
									<c:forEach var="stock_in_type" items="${stock_in_type_list}">
										<option value="${stock_in_type.list_name}">${stock_in_type.list_value}</option>
									</c:forEach>
								</select>
								<div class="form-group">
									<label class="control-label">收货仓库：<span class="required">* </span></label>
									<input type="text" name="req[warehouse-id]" style="display: none;">
									<div class="input-group">
											<input type="text" class="form-control" placeholder="请选择收货仓库…" name="req[warehouse-name]" readonly>
											<span class="input-group-addon">
											<a href="#warehouse-pick" class="warehouse-pick" data-toggle="modal"><i class="fa fa-search"></i>
											</a>
											</span>
									</div>
								</div>
								<div class="form-group">
															<label class="control-label" for="endtime">入库日期：</label>
																<div class="input-group input-medium date date-picker"
																	data-date-format="yyyy-mm-dd"
																	data-date="">
																	<input type="text" class="form-control" readonly
																		name="req[receive_date]" value="">
																	<span class="input-group-btn">
																		<button class="btn default" type="button">
																			<i class="fa fa-calendar"></i>
																		</button>
																	</span>
																</div>
								</div>
								<label>描述</label>
								<textarea class="form-control" rows="2"	placeholder="输入描述…" name="req[description]"></textarea>
								
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="new-req-confirm" class="btn blue" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END NEW PO CONFIRM MODAL FORM -->
			<!-- BEGIN SUPPLIER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="supplier-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择供应商</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue supplier-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END SUPPLIER PICK MODAL FORM-->
			<!-- BEGIN WAREHOUSE PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="warehouse-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择收货仓库</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue warehouse-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END WAREHOUSE PICK MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			库存管理 <small>我的入库单列表</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/stock_in_list.do">库存管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/stock_in_list.do">我的入库单列表</a>
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
					<!-- BEGIN STOCK IN REQUISITION LIST TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>我的入库单列表
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
<!-- 												<a href="sio_import_view.do" class="btn blue"> -->
<!-- 													<i class="fa fa-file-excel-o"></i> 从Excel导入 -->
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 									</div> -->
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
							<table class="table table-striped table-bordered table-hover" id="stock_in_list">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#stock_in_list .checkboxes"/>
								</th>
								<th>
									 系统编号
								</th>
								<th>
									 人工编号
								</th>
								<th>
									 入库仓库
								</th>
								<th>
									 入库类型
								</th>
								<th>
									 开单时间
								</th>
								<th>
									 开单人
								</th>
								<th>
									 订单状态
								</th>
							</tr>
							</thead>
							<tbody>
	                		<c:forEach var="requisition" items="${requisitionList}">
	                 		<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%> 
	                		<tr class="odd gradeX" id="${requisition.order_id}">
	                			<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
	                    		<td><a href="<%=path%>/inventory/stock_in_details.do?id=${requisition.requisition_id}">
	                    		${requisition.requisition_number}</a></td>
	                    		<td>
	                    			${requisition.requisition_manual_number}        		
								</td>
	                    		<td>
	                    			${requisition.receive_wh_name} 	
	                    		</td>
	                    		<td>
	                    		   	${requisition.requisition_subtype_val}      								
	                    		</td>
	                    		<td>
	                    		   	${requisition.created_date}      								
	                    		</td>
	                    		<td>
	                    			${requisition.created_by_name}
	                    		</td>
	                    		<td>
	                    			${requisition.status_value}
	                    		</td>
	                  		</tr>	
							</c:forEach>		
							</tbody>
							</table>
						</div>
					</div>
					<!-- END STOCK IN REQUISITION LIST TABLE PORTLET-->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/inventory/stock-in-list-events.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   navigationBar.activeInventoryMenu();
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.inventory-menu').find('ul.sub-menu').children('li:eq(1)').addClass('active');
	   Layout.init(); // init current layout
	   Demo.init(); // init demo features
	   //CommonEvents.init();
	   StockInEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>