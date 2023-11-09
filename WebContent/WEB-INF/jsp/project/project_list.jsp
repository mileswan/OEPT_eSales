<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2016/4/18
 * Description:  Projects list management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 项目管理</title>
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
<link href="../assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
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
							<h4 class="modal-title">新建商机</h4>
						</div>
						<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
								<label class="control-label">商机名称：</label>
								<input type="text" class="form-control" placeholder="请输入商机名称…" id="po-man-number" name="po[man-number]">
								</div>
								<div class="form-group">
									<label class="control-label">客户：<span class="required">* </span></label>
									<input type="text" name="po[supplier-id]" style="display: none;">
									<div class="input-group">
											<input type="text" class="form-control" placeholder="请选择客户…" id="po-supplier-name" name="po[supplier-name]" readonly>
											<span class="input-group-addon">
											<a href="#supplier-pick" class="supplier-pick" data-toggle="modal"><i class="fa fa-search"></i>
											</a>
											</span>
									</div>
								</div>
								<div class="form-group">
								<label>商机描述</label>
								<textarea class="form-control" rows="2"	placeholder="输入商机描述…" id="po-description" name="po[description]"></textarea>
								</div>
								<div class="form-group">
								<label>赢率</label>
								<select class="form-control currency" name="po[currency]">
										<option value="1">25%</option>
										<option value="1">50%</option>
										<option value="1">75%</option>
										<option value="1">100%</option>
								</select>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">收货仓库：<span class="required">* </span></label> -->
<!-- 									<input type="text" name="po[warehouse-id]" style="display: none;"> -->
<!-- 									<div class="input-group"> -->
<!-- 											<input type="text" class="form-control" placeholder="请选择收货仓库…" id="po-warehouse-name" name="po[warehouse-name]" readonly> -->
<!-- 											<span class="input-group-addon"> -->
<!-- 											<a href="#warehouse-pick" class="warehouse-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 											</a> -->
<!-- 											</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="new-po-confirm" class="btn blue" data-dismiss="modal">确认</button>
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
			项目管理 <small>我的项目列表</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">项目管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/opty/opty_list.do">我的项目列表</a>
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
								<i class="fa fa-globe"></i>我的项目列表
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
<!-- 									<div class="col-md-6"> -->
<!-- 											<div class="actions"> -->
<!-- 												<a href="#new-confirm" data-toggle="modal" class="btn green"> -->
<!-- 													<i class="fa fa-plus"></i> 新建 -->
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-6"> -->
<!-- 											<div class="actions pull-right"> -->
<!-- 												<a href="po_import_view.do" class="btn blue"> -->
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
							<table class="table table-striped table-bordered table-hover" id="po_list">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#po_list .checkboxes"/>
								</th>
								<th>
									 项目编号
								</th>
								<th>
									 项目名称
								</th>
								<th>
									 客户名称
								</th>
								<th>
									 项目阶段
								</th>
								<th>
									 创建时间
								</th>
								<th>
									 创建人
								</th>
								<th>
									 来源商机编号
								</th>
							</tr>
							</thead>
							<tbody>
	                		<tr class="odd gradeX" id="1">
	                			<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
	                    		<td><a href="<%=path%>/project/project_details.do">
	                    		2-19282461782</a></td>
	                    		<td>
	                    			****网络改造项目        		
								</td>
	                    		<td>
	                    			****集团总部 	
	                    		</td>
	                    		<td>
	                    			准备阶段 	
	                    		</td>
	                    		<td>
	                    		   	2016-04-25 14:30:51      								
	                    		</td>
	                    		<td>
	                    			sales1
	                    		</td>
	                    		<td>
	                    			<a href="">123456</a>
	                    		</td>
	                  		</tr>
	                  		<tr class="odd gradeX" id="2">
	                			<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
	                    		<td><a href="<%=path%>/project/project_details.do">
	                    		2-12822331782</a></td>
	                    		<td>
	                    			****网络设备采购项目      		
								</td>
	                    		<td>
	                    			**** 	
	                    		</td>
	                    		<td>
	                    			实施阶段 	
	                    		</td>
	                    		<td>
	                    		   	2016-04-25 15:30:51      								
	                    		</td>
	                    		<td>
	                    			sales1
	                    		</td>
	                    		<td>
	                    			<a href="">654321</a>
	                    		</td>
	                  		</tr>
	                  		<tr class="odd gradeX" id="3">
	                			<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
	                    		<td><a href="<%=path%>/project/project_details.do">
	                    		2-28224282</a></td>
	                    		<td>
	                    			数据库维护服务项目      		
								</td>
	                    		<td>
	                    			****	
	                    		</td>
	                    		<td>
	                    			收尾阶段 	
	                    		</td>
	                    		<td>
	                    		   	2016-01-25 15:30:51      								
	                    		</td>
	                    		<td>
	                    			sales1
	                    		</td>
	                    		<td>
	                    			<a href="">654321</a>
	                    		</td>
	                  		</tr>
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
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/common-events.js" type="text/javascript"></script>
<script src="../scripts/order/po-list-events.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   navigationBar.activePOMenu();
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.po-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   //CommonEvents.init();
	   POEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
