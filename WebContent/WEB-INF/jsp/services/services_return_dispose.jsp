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
					服务中心 <small>返修/退货</small>
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<%=path%>/auth/dashboard.do">首页</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a href="">服务中心</a> <i class="fa fa-angle-right"></i></li>
						<li>返修/退货</li>
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
												data-toggle="tab"> 我的退货 </a></li>
												<li><a href="#tab_repair" data-toggle="tab">我的返修 </a></li>
											<li><a href="#tab_history" data-toggle="tab">已完成订单 </a></li>
										</ul>

										<div class="tab-content no-space">
											<div class="tab-pane" id="tab_history">
												<div class="div_select_cho"></div>
												<div class="form-body">
													<div class="div_up" id="div_up">
														<table
															class="table table-striped table-bordered table-hover"
															id="sample_1">
															<thead>
																<tr>
																	<th>返修/退货编号</th>
																	<th>返修/退货类型</th>
																	<th>订单编号</th>
																	<th>返修/退货原因</th>
																	<th>退货金额</th>
																	<th>返修/退货状态</th>
																	<th>完成时间</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="list" items="${orasList }">
																<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
																<tr class="odd gradeX">
																	<td>${list.serviceId }</td>
																	<td>${list.serviceType }</td>
																	<td><a href="<%=path %>/order/details.do?id=${list.orderId }">${list.order.order_number }</a></td>
																	<td>${list.serviceDesc }</td>
																	<c:if test="${list.serviceType == '退货' }">
																	<td>${list.order.purchase_amount}</td>
																	</c:if>
																	<c:if test="${list.serviceType == '返修' }">
																	<td></td>
																	</c:if>
																	<td>
																		<c:if test="${list.serviceStatus ==7}">已终止</c:if>
																		<c:if test="${list.serviceStatus ==3}">已完成</c:if>
																		<c:if test="${list.serviceStatus ==8}">拒绝受理</c:if>
																	</td>
																	<td>${list.serviceCreated }</td>
																</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											<div class="tab-pane active" id="tab_return">
												<div class="div_select_cho"></div>
												<div class="div_down">
													<table class="table table-striped table-bordered table-hover"
														id="sample_1">
														<thead>
															<tr>
																<th>退货编号</th>
																<th>订单编号</th>
																<th>退货原因</th>
																<th>退货金额</th>
																<th>退货状态</th>
																<th>申请时间</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="list" items="${rtsList }">
																<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
																<tr class="odd gradeX">
																	<td>${list.serviceId }</td>
																	<td><a href="<%=path %>/order/details.do?id=${list.orderId }">${list.order.order_number }</a></td>
																	<td>${list.serviceDesc }</td>
																	<td>${list.order.purchase_amount}</td>
																	<td>
																		<c:if test="${list.serviceStatus ==1}">正在审核</c:if>
																		<c:if test="${list.serviceStatus ==2}">等待收货</c:if>
																		<c:if test="${list.serviceStatus ==3}">已完成</c:if>
																		<c:if test="${list.serviceStatus ==4}">正在退款</c:if>
																	</td>
																	<td>${list.serviceCreated }</td>
																	<td>
																		<c:if test="${list.serviceStatus ==1}"><a href="<%=path %>/service/selectServDetail.do?id=${list.serviceId }">审核</a></c:if>
																		<c:if test="${list.serviceStatus !=1}"><a href="<%=path %>/service/selectServDetail.do?id=${list.serviceId }">处理</a></c:if>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<div class="tab-pane" id="tab_repair">
												<div class="div_select_cho"></div>
												<div class="form-body">
													<div class="div_down">
														<table
															class="table table-striped table-bordered table-hover"
															id="sample_1">
															<thead>
																<tr>
																	<th>返修编号</th>
																	<th>订单编号</th>
																	<th>返修原因</th>
																	<th>返修状态</th>
																	<th>申请时间</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="list" items="${rpsList }">
																<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
																<tr class="odd gradeX">
																	<td>${list.serviceId }</td>
																	<td><a href="<%=path %>/order/details.do?id=${list.orderId }">${list.order.order_number }</a></td>
																	<td>${list.serviceDesc }</td>
																	<td>
																		<c:if test="${list.serviceStatus ==1}">正在审核</c:if>
																		<c:if test="${list.serviceStatus ==2}">等待收货</c:if>
																		<c:if test="${list.serviceStatus ==3}">已完成</c:if>
																		<c:if test="${list.serviceStatus ==4}">正在修理</c:if>
																		<c:if test="${list.serviceStatus ==5}">已发货</c:if>
																	</td>
																	<td>${list.serviceCreated }</td>
																	<td>
																		<c:if test="${list.serviceStatus ==1}"><a href="<%=path %>/service/selectServDetail.do?id=${list.serviceId }">审核</a></c:if>
																		<c:if test="${list.serviceStatus !=1}"><a href="<%=path %>/service/selectServDetail.do?id=${list.serviceId }">处理</a></c:if>
																	</td>
																</tr>
																</c:forEach>
															</tbody>
														</table>
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
	<script src="../scripts/jquery/region_select.js" type="text/javascript"></script>
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
	<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>

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