<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/25
 * Description:  products portfolio page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 产品浏览中心</title>
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
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="../assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/pages/css/portfolio.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
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
			<!-- BEGIN SHOPPING CART CONFRIM MODAL FORM-->
			<div class="modal fade bs-modal-sm" id="add-shopping-cart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">添加购物车</h4>
						</div>
						<div class="modal-body">
							 确认将此产品加入到您的购物车？<span class="product-id" style="display: none;"></span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue add-shopcart-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SHOPPING CART CONFRIM MODAL FORM-->
			<!-- BEGIN GENERATE ORDER CONFIRM MODAL FORM-->
			<div class="modal fade bs-modal-sm" id="generate-order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择购买数量</h4>
						</div>
						<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
							<label class="control-label">产品单价（元）：</label>
							<span class="order-product-price"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							 <span class="order-product-id" style="display: none;"></span>
								<div class="shop-quantity">
									<div class="input-group" style="width: 150px;">
										<div class="spinner-buttons input-group-btn">
											<button type="button" class="btn spinner-down red">
												<i class="fa fa-minus"></i>
											</button>
										</div>
										<input type="text" class="spinner-input form-control"
											name="shop-quantity" maxlength="3">
										<div class="spinner-buttons input-group-btn">
											<button type="button" class="btn spinner-up blue">
												<i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							<label class="control-label">购买金额合计（元）：</label>
							<span class="purchase-amount"></span>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue generate-order-confirm" data-dismiss="modal">确认下单</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END GENERATE ORDER CONFIRM MODAL FORM-->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			采购中心 <small>产品浏览</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/portfolio/list.do">网上商城</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/portfolio/list.do">产品浏览</a>
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
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable tabbable-custom boxless">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="#tab_1" data-toggle="tab">
								产品展示 </a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_1">
								<!-- BEGIN FILTER -->
								<div class="margin-top-10">
									<ul class="mix-filter">
										<li class="filter" data-filter="all">
											 所有
										</li>
										<c:forEach var="category" items="${disctinct_categories}">
											<li class="filter" data-filter="category_${category.categoryId}">
											 ${category.categoryName}
											</li>
										</c:forEach>
									</ul>
									<div class="row mix-grid">
										<c:forEach var="product" items="${available_products}">
											<div class="col-sm-4 col-md-3 mix category_${product.categoryId}">
												<div class="thumbnail mix-inner">
													<img class="img-responsive" src="<%=path%>${product.image_context_path}/${product.image_original_filename}" alt=""
														style="width: 100%; height: 200px;">
													<div class="caption">
														<b>价格：</b><span class="product-price">${product.price}</span> 元
														<b>库存：</b>${product.stock} ${product.sku}
														<p>${product.desc}</p>
														<p class="favorite-status-prod${product.id}">
																<c:choose>
																	<c:when test="${product.check_collected == true}">
																		<span> 已收藏<i class="fa fa-check"></i></span>
																	</c:when>
																	<c:otherwise>
																		<a class="green add-to-favorite"> 点击收藏 <i
																			class="fa fa-plus"></i>
																		</a>
																	</c:otherwise>
																</c:choose>
														</p>
														<p id="${product.id}" class="product-id">
															<a href="#generate-order" class="btn blue generate-order" data-toggle="modal"> 直接购买 </a> 
															<a href="#add-shopping-cart" class="btn default" data-toggle="modal"> 添加到购物车 </a>
														</p>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<!-- END FILTER -->
							</div>
						</div>
					</div>
				</div>
			</div>
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
<script type="text/javascript" src="../assets/global/plugins/jquery-mixitup/jquery.mixitup.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="../assets/global/plugins/fuelux/js/spinner.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/portfolio.js"></script>
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/purchase/portfolio-events.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
   	Metronic.init(); // init metronic core components
   	navigationBar.activePurchaseMenu();
	Layout.init(); // init current layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features
   	Portfolio.init();
   	$('ul.sub-menu').find('li').removeClass('active');
   	$('li.purchase-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
   	PortfolioEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>