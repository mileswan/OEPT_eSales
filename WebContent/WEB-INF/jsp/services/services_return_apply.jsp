<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String url = basePath + "";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 
/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/12/3
 * Description:  Services List management page.
 */
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>服务管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="../assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link
	href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/bootstrap-datepicker/css/datepicker3.css">
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="../assets/admin/pages/css/tasks.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="../assets/global/css/components.css" id="style_components"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<link
	href="../assets/global/plugins/fancybox/source/jquery.fancybox.css"
	rel="stylesheet" type="text/css">
<!-- END THEME STYLES -->
<!-- BEGIN APPLICATION STYLES -->
<link href="../css/autods.css" rel="stylesheet" type="text/css" />
<!-- END APPLICATION STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
<!-- 本页样式 -->
<link href="../css/service/serviceStyle.css" rel="stylesheet"
	type="text/css" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body
	class="page-header-fixed page-quick-sidebar-over-content page-style-square">
	<!-- BEGIN HEADER -->
	<jsp:include page="../header.jsp" />
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<jsp:include page="../sidebar.jsp" />
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<h3 class="page-title">
					服务中心 <small>返修/退货申请</small>
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<%=path%>/auth/dashboard.do">首页</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a href="">服务中心</a> <i class="fa fa-angle-right"></i></li>
						<li><a>返修/退货申请</a></li>
					</ul>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN Table ROWS -->
				<div class="row">
					<div class="col-md-12">
						<div class="tabbable tabbable-custom boxless tabbable-reversed">
							<div class="tab-content">
								<!-- BEGIN TAB0 -->
								<div class="tab-pane active" id="tab_0">

									<div class="tabbable">
										<ul class="nav nav-tabs">
											<li class="active"><a href="#tab_return"
												data-toggle="tab"> 我要退货 </a></li>
											<li><a href="#tab_repair" data-toggle="tab">我要返修 </a></li>
										</ul>

										<div class="tab-content no-space">
											<div class="tab-pane active" id="tab_return">
												<div class="form-body">
													<div class="div_up" id="div_up">
														<div class="div_row" style="padding-left: 80px;">
															<span style="font-size: 15px; color: red;">退货申请</span>
														</div>
														<div class="div_rows">
															<div class="r_l">
																<label class="returnFont">订单编号：</label>
															</div>
															<div class="r_r">
																<input id="returnId2" name="returnId2" class="form-control" readonly="readonly"
																	type="text" value="${od.order_number }">
																	<input id="returnId" name="returnId" class="form-control"
																	type="text" value="${od.order_id }" style="display: none;">
															</div>
														</div>
														<div class="div_rows">
															<div class="r_l">
																<label class="returnFont">退货原因：</label>
															</div>
															<div class="r_r">
																<input id="returnDesc" name="returnDesc" class="form-control"
																	type="text" value="">
															</div>
														</div>
														<div class="div_rows">
															<div class="r_l">
																<label class="returnFont">退货金额：</label>
															</div>
															<div class="r_r">
																<input id="returnAmt" name="returnAmt" class="form-control" readonly="readonly"
																	type="text" value="${od.purchase_amount }">
															</div>
														</div>
														<div class="div_row2s">
															<div class="r_l">
																<label class="returnFont">退货说明：</label>
															</div>
															<div class="r_r">
																<textarea rows="4" cols="57" class="form-control"
																	id="returnComm" name="returnComm"></textarea>
															</div>
														</div>
														<div class="div_rows">
															<div class="r_l"></div>
															<div class="r_r">
																<button type="button" onclick="returnApply()"
																	class="btn blue" id="updateUser-confirm"
																	style="background-color: red;">
																	<i class="fa fa-check"></i>提交退货申请
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab_repair">
												<div class="form-body">
													<div class="div_up" id="div_up">
														<div class="div_row" style="padding-left: 80px;">
															<span style="font-size: 15px; color: red;">返修申请</span>
														</div>
														<div class="div_rows">
															<div class="r_l">
																<label class="returnFont">订单编号：</label>
															</div>
															<div class="r_r">
																<input id="repairId2" name="repairId2" class="form-control" readonly="readonly"
																	type="text" value="${od.order_number }">
																<input id="repairId" name="repairId" class="form-control"
																	type="text" value="${od.order_id }" style="display: none;">
															</div>
														</div>
														<div class="div_rows">
															<div class="r_l">
																<label class="returnFont">返修原因：</label>
															</div>
															<div class="r_r">
																<input id="repairDesc" name="repairDesc" class="form-control"
																	type="text" value="">
															</div>
														</div>
														<div class="div_row2s">
															<div class="r_l">
																<label class="returnFont">返修说明：</label>
															</div>
															<div class="r_r">
																<textarea rows="4" cols="57" class="form-control"
																	id="repairComm" name="repairComm"></textarea>
															</div>
														</div>
														<div class="div_rows">
															<div class="r_l"></div>
															<div class="r_r">
																<button type="button" onclick="repairApply()"
																	class="btn blue" id="updateUser-confirm"
																	style="background-color: red;">
																	<i class="fa fa-check"></i>提交返修申请
																</button>
															</div>
														</div>
													</div>
												</div>
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
			<!-- END CONTAINER -->
			<!-- BEGIN FOOTER -->
			<jsp:include page="../footer.jsp" />
			<!-- END FOOTER -->
			<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
			<!-- BEGIN CORE PLUGINS -->
			<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
			<script src="../scripts/service/serConsult.js" type="text/javascript"></script>
			<!-- begin 省市级联js -->
			<script src="../scripts/users/addressList.js" type="text/javascript"></script>
			<script src="../scripts/jquery/region_select.js"
				type="text/javascript"></script>
			<!-- end -->
			<script src="../assets/global/plugins/jquery.min.js"
				type="text/javascript"></script>
			<script src="../assets/global/plugins/jquery-migrate.min.js"
				type="text/javascript"></script>
			<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
			<script
				src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
				type="text/javascript"></script>
			<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
				type="text/javascript"></script>
			<script
				src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
				type="text/javascript"></script>
			<script
				src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
				type="text/javascript"></script>
			<script src="../assets/global/plugins/jquery.blockui.min.js"
				type="text/javascript"></script>
			<script src="../assets/global/plugins/jquery.cokie.min.js"
				type="text/javascript"></script>
			<script src="../assets/global/plugins/uniform/jquery.uniform.min.js"
				type="text/javascript"></script>
			<script
				src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
				type="text/javascript"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/moment.min.js"></script>
			<script type="text/javascript"
				src="../assets/admin/pages/scripts/components-pickers.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
			<!-- END CORE PLUGINS -->
			<!-- BEGIN PAGE LEVEL PLUGINS -->
			<script type="text/javascript"
				src="../assets/global/plugins/select2/select2.min.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
			<script type="text/javascript"
				src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
			<!-- END PAGE LEVEL PLUGINS -->
			<!-- BEGIN PAGE LEVEL SCRIPTS -->
			<script src="../assets/global/scripts/metronic.js"
				type="text/javascript"></script>
			<script src="../assets/admin/layout/scripts/layout.js"
				type="text/javascript"></script>
			<script src="../assets/admin/layout/scripts/quick-sidebar.js"
				type="text/javascript"></script>
			<script src="../assets/admin/layout/scripts/demo.js"
				type="text/javascript"></script>
			<!-- END PAGE LEVEL SCRIPTS -->
			<!-- begin user update -->
			<script src="../scripts/user-page.js" type="text/javascript"></script>
			<!-- end  -->
			<script src="../scripts/navigationbar-action.js"
				type="text/javascript"></script>

			<script src="../scripts/users/userDetails.js" type="text/javascript"></script>

			<script>
				jQuery(document).ready(function() {
					Metronic.init(); // init metronic core components
					Layout.init(); // init current layout
					QuickSidebar.init(); // init quick sidebar
					Demo.init(); // init demo features
					navigationBar.activeServiceMenu();
				});
			</script>
			<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>