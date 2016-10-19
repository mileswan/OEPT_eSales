<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/12/24
 * Description:  Warehouse details management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 联系人管理</title>
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
<link href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker3.css">
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
<link href="../assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css">
<!-- begin time control -->
<link href="../assets/admin/pages/css/search.css" rel="stylesheet" type="text/css"/>
<!-- end time control -->
<!-- END THEME STYLES -->
<!-- BEGIN APPLICATION STYLES -->
<link href="../css/autods.css" rel="stylesheet" type="text/css"/>
<link href="../css/contact/contact-ui.css" rel="stylesheet" type="text/css"/>
<!-- END APPLICATION STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
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
			<!-- BEGIN ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择地址</h4>
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
			<!-- BEGIN ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="account-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择单位</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue account-pick" data-dismiss="modal">确认</button>
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
							<h4 class="modal-title">新建地址</h4>
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
     					<div>
     						<span>详细地址：</span><br/>
							<input id="detailsAddress" name="" class="newAddressInput" type="text"  placeholder="请输入详细地址">
     					</div>
     					<div>
     						<span>邮编：</span><br/>
							<input id="zipcode" name="" class="newAddressInput" type="text"  placeholder="请输入邮编"
							onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="6">
     					</div>
<!--      					<div> -->
<!--      						<span>电话号码：</span><br/> -->
<!-- 							<input id="contactTel" name="" class="newAddressInput" type="text"  placeholder="请输入电话号码，只能输入数字" -->
<%-- 							onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" maxlength="11"> --%>
<!--      					</div> -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue new-addr-submit" data-dismiss="modal">保存地址</button>
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
			联系人管理 <small>联系人信息设置</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/warehouse_list.do">联系人列表</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/inventory/warehouse_details.do?id=${warehouseDetails.id}">添加联系人信息</a>
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
				<div class="tabbable tabbable-custom boxless tabbable-reversed">
						<div class="tab-content">
								<!-- BEGIN TAB0 -->
							<div class="tab-pane active" id="tab_0">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>新建联系人信息
										</div>
										<div class="tools">
											<a href="" class="reload"> </a>
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="" id="update_form"  method="post">
											<div class="form-body">
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">姓氏：<span class="required">* </span></label>
															<input type="text" name="warehouse_id" value="" style="display: none;">
															<input type="text" class="form-control" placeholder="请输入联系人姓氏…" name="addFirstname" id="addFirstname" value="">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">名字：<span class="required">* </span></label>
															<input type="text" class="form-control" placeholder="请输入联系人名字…" name="addLastname" id="addLastname" value="">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
																<label class="control-label">出生日期：<span class="required">* </span></label>
															<div class="input-icon">
															<i class="fa fa-calendar"></i>
															<input class="form-control date-picker" size="16" type="text" value="1990-01-01" data-date="1990-01-01" data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="addBirthday" name="addBirthday"/>
															</div>

																
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">年龄：<span class="required">* </span></label>
															<input type="text" class="form-control" placeholder="请输入联系人年龄…" name="addAge" id="addAge" value="" readonly="readonly">	
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">传真：</label>
															<input type="text" class="form-control" placeholder="请输入联系人传真…" name="addFax" id="addFax" value=""
															onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="11">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">E-mail：</label>
															<input type="text" class="form-control" placeholder="请输入联系人E-mail…" name="addEmail" id="addEmail" value="">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
																<label class="control-label">个人电话：</label>
															<input type="text" class="form-control" placeholder="请输入联系人个人电话…" name="addCellphone" id="addCellphone" value=""
															onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="11">	
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">办公电话：<span class="required">* </span></label>
															<input type="text" class="form-control" placeholder="请输入联系人办公电话…" name="addWorkphone" id="addWorkphone" value=""
															onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="11">	
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
																<label class="control-label">职称：</label>
															<input type="text" class="form-control" placeholder="请输入联系人职称…" name="addTitle" id="addTitle" value="">	
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">部门：</label>
															<input type="text" class="form-control" placeholder="请输入联系人部门…" name="addDepartment" id="addDepartment" value="">	
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>性别：</label>
															<div class="radio-list">
																<label class="radio-inline">
																<input type="radio" name="addGender" id="addGender" value="true" checked="checked">男</label>
																<label class="radio-inline">
																<input type="radio" name="addGender" id="addGender" value="false">女 </label>
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">地址<span class="required">* </span>：</label>
															<input type="text" name="addAddressId" id="addAddressId" value="" style="display: none;">
															<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择地址…" id="addAddressName" name="addAddressName" value="" readonly>
																<span class="input-group-addon">
																	<a href="#address-pick" class="address-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																</span>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">单位<span class="required">* </span>：</label>
															<input type="text" name="addAccountId" id="addAccountId" value="" style="display: none;">
															<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择单位…" id="addAccountName" name="addAccountName" value="" readonly>
																<span class="input-group-addon">
																	<a href="#account-pick" class="account-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																</span>
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>描述</label>
															<textarea class="form-control" rows="3"
																placeholder="输入描述…" name="addComment" id="addComment"></textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="a-div-b">
											<button type="button" class="btn blue" id="addContactButton">
												<i class="fa fa-check"></i>新建
											</button>
											<button type="button" class="btn default cancel" id="n-back">取消</button>
										</div>
										</form>
									</div>
								</div>
							</div>
							<!-- END TAB0 -->
						</div>
					</div>
				</div>
			</div>
			<!-- END Table ROWS -->
			<!--  div class="clearfix" -->
			<!--  div-->
		</div>
	</div>
	<!-- END CONTENT -->
<!-- BEGIN FOOTER -->
<jsp:include page="../footer.jsp" />
<!-- END FOOTER -->
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
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script> -->
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script> -->
<script type="text/javascript" src="../assets/global/plugins/moment.min.js"></script>
<script type="text/javascript" src="../assets/admin/pages/scripts/components-pickers.js"></script>
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script> -->
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- <script type="text/javascript" src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script> -->
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<!-- begin time control -->
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="../assets/admin/pages/scripts/search.js"></script>
<!-- end time control -->

<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/common-events.js" type="text/javascript"></script>
<script src="../scripts/contact/contact-events.js" type="text/javascript"></script>
<!-- begin 省市级联js -->
<script src="../scripts/jquery/region_select.js" type="text/javascript"></script>
<!-- end -->
<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   navigationBar.activeInventoryMenu();
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Search.init();
	   Demo.init(); // init demo features
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.inventory-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
	   CommonEvents.init();
	   ContactEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>