<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/10
 * Description:  Dashboard homepage list page.
 */-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT EIMS - 首页</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="../favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
<!-- BEGIN HEADER -->
<jsp:include page="header.jsp" />
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<jsp:include page="sidebar.jsp" />
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			系统首页 <small>个人工作中心</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="#">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">个人工作中心</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="portlet box blue-steel">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bell-o"></i>最近编辑过的订单
							</div>
<!-- 							<div class="actions"> -->
<!-- 								<div class="btn-group"> -->
<!-- 									<a class="btn btn-sm btn-default dropdown-toggle" href="#" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> -->
<!-- 									Filter By <i class="fa fa-angle-down"></i> -->
<!-- 									</a> -->
<!-- 									<div class="dropdown-menu hold-on-click dropdown-checkboxes pull-right"> -->
<!-- 										<label><input type="checkbox"/> Finance</label> -->
<!-- 										<label><input type="checkbox" checked=""/> Membership</label> -->
<!-- 										<label><input type="checkbox"/> Customer Support</label> -->
<!-- 										<label><input type="checkbox" checked=""/> HR</label> -->
<!-- 										<label><input type="checkbox"/> System</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
						<div class="portlet-body">
							<div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">
									<c:forEach var="recentOrder" items="${recentOrdersList}">
	                 				<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
	                 					<li>
	                 					<c:if test="${recentOrder.order_type_cd == 'Purchase Order'}">
	                 					<a href="<%=path%>/order/po_details.do?id=${recentOrder.order_id}">
	                 					</c:if>
	                 					<c:if test="${recentOrder.order_type_cd == 'Sales Order'}">
	                 					<a href="<%=path%>/order/so_details.do?id=${recentOrder.order_id}">
	                 					</c:if>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-info">
														<i class="fa fa-shopping-cart"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														 系统单号：${recentOrder.order_number}，订单类型：${recentOrder.order_type}
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="datetime">
												 ${recentOrder.update_date}
											</div>
										</div>
										</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="scroller-footer">
								<div class="btn-arrow-link pull-right">
									<a href="#">查看更多</a>
									<i class="icon-arrow-right"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="portlet box blue-steel">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bell-o"></i>最近编辑过的出入库单
							</div>
						</div>
						<div class="portlet-body">
							<div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">
									<c:forEach var="recentReq" items="${recentReqsList}">
	                 				<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
	                 					<li>
	                 					<c:if test="${recentReq.requisition_type_cd == 'Stock In Requisition'}">
	                 					<a href="<%=path%>/inventory/stock_in_details.do?id=${recentReq.requisition_id}">
	                 					</c:if>
	                 					<c:if test="${recentReq.requisition_type_cd == 'Stock Out Requisition'}">
	                 					<a href="<%=path%>/inventory/stock_out_details.do?id=${recentReq.requisition_id}">
	                 					</c:if>
										<div class="col1">
											<div class="cont">
												<div class="cont-col1">
													<div class="label label-sm label-info">
														<i class="fa fa-shopping-cart"></i>
													</div>
												</div>
												<div class="cont-col2">
													<div class="desc">
														 系统单号：${recentReq.requisition_number}，单据类型：${recentReq.requisition_type}
													</div>
												</div>
											</div>
										</div>
										<div class="col2">
											<div class="datetime">
												 ${recentReq.update_date}
											</div>
										</div>
										</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="scroller-footer">
								<div class="btn-arrow-link pull-right">
									<a href="#">查看更多</a>
									<i class="icon-arrow-right"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-6"> -->
<!-- 					Begin: life time stats -->
<!-- 					<div class="portlet box blue-steel"> -->
<!-- 						<div class="portlet-title"> -->
<!-- 							<div class="caption"> -->
<!-- 								<i class="fa fa-thumb-tack"></i>购买数量概览 -->
<!-- 							</div> -->
<!-- 							<div class="tools"> -->
<!-- 								<a href="javascript:;" class="collapse"> -->
<!-- 								</a> -->
<!-- 								<a href="javascript:;" class="reload"> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="portlet-body"> -->
<!-- 							<div class="tabbable-line"> -->
<!-- 								<ul class="nav nav-tabs"> -->
<!-- 									<li class="active"> -->
<!-- 										<a href="#overview_1" data-toggle="tab"> -->
<!-- 										产品购买量排行榜 </a> -->
<!-- 									</li> -->
<!-- 								</ul> -->
<!-- 								<div class="tab-content"> -->
<!-- 									<div class="tab-pane active" id="overview_1"> -->
<!-- 										<div class="table-responsive"> -->
<!-- 											<table class="table table-striped table-hover table-bordered"> -->
<!-- 											<thead> -->
<!-- 											<tr> -->
<!-- 												<th> -->
<!-- 													 产品名称 -->
<!-- 												</th> -->
<!-- 												<th> -->
<!-- 													 价格（元） -->
<!-- 												</th> -->
<!-- 												<th> -->
<!-- 													 购买量 -->
<!-- 												</th> -->
<!-- 												<th> -->
<!-- 												</th> -->
<!-- 											</tr> -->
<!-- 											</thead> -->
<!-- 											<tbody> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													Z-MAX 6A屏蔽模块化跳线 </a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 488.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 809 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													Siemon超五类非屏蔽</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 915.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 789 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													6A类 F/UTP 4对线缆</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 878.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 784 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													Z-MAX 6A类非屏蔽模块化跳线</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 325.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 698 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													MapIT G2智能配线架</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 725.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 567 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													6A类屏蔽BladePatch模块化跳线</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 125.50 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 465 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td> -->
<!-- 													<a href="#"> -->
<!-- 													MapIT G2智能配线架</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 1328.00 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 214 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a href="#" class="btn default btn-xs green-stripe"> -->
<!-- 													查看详情</a> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											</tbody> -->
<!-- 											</table> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					End: life time stats -->
<!-- 				</div> -->
<!-- 				<div class="col-md-6"> -->
<!-- 					Begin: life time stats -->
<!-- 					<div class="portlet box red-sunglo"> -->
<!-- 						<div class="portlet-title"> -->
<!-- 							<div class="caption"> -->
<!-- 								<i class="fa fa-bar-chart-o"></i>购买金额概览 -->
<!-- 							</div> -->
<!-- 							<div class="tools"> -->
<!-- 								<a href="javascript:;" class="reload"> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 							<ul class="nav nav-tabs" style="margin-right: 10px"> -->
<!-- 								<li> -->
<!-- 									<a href="#portlet_tab1" data-toggle="tab" id="statistics_amounts_tab"> -->
<!-- 									购买金额统计 </a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 						<div class="portlet-body"> -->
<!-- 							<div class="tab-content"> -->
<!-- 								<div class="tab-pane active" id="portlet_tab1"> -->
<!-- 									<div id="statistics_1" class="chart"> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="well no-margin no-border"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-md-3 col-sm-3 col-xs-6 text-stat"> -->
<!-- 										<span class="label label-success"> -->
<!-- 										购买金额: </span> -->
<!-- 										<h3>￥1,234,112.20</h3> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3 col-sm-3 col-xs-6 text-stat"> -->
<!-- 										<span class="label label-info"> -->
<!-- 										税款金额: </span> -->
<!-- 										<h3>￥134,90.10</h3> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3 col-sm-3 col-xs-6 text-stat"> -->
<!-- 										<span class="label label-danger"> -->
<!-- 										物流金额: </span> -->
<!-- 										<h3>￥1,134,90.10</h3> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3 col-sm-3 col-xs-6 text-stat"> -->
<!-- 										<span class="label label-warning"> -->
<!-- 										订单数量: </span> -->
<!-- 										<h3>2357</h3> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					End: life time stats -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="footer.jsp" />
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
<script src="../assets/global/plugins/flot/jquery.flot.js" type="text/javascript"></script>
<script src="../assets/global/plugins/flot/jquery.flot.resize.js" type="text/javascript"></script>
<script src="../assets/global/plugins/flot/jquery.flot.categories.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/ecommerce-index.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script>
        jQuery(document).ready(function() {    
           	Metronic.init(); // init metronic core components
           	navigationBar.activeHomeMenu();
			Layout.init(); // init current layout
			QuickSidebar.init(); // init quick sidebar
			Demo.init(); // init demo features
           	EcommerceIndex.init();
        });
    </script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>