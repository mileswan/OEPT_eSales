<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
 * Author: mwan
 * Version: 1.0
 * Date: 2016/1/6
 * Description:  Purchase Return Order details management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 采购退货单详情</title>
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
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<!-- END PAGE LEVEL STYLES -->
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
			<!-- BEGIN DELETE CONFIRM MODAL FORM-->
			<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除购买项</h4>
						</div>
						<div class="modal-body">
						<input type="text" name="orderItem[id]" style="display :none;" value="">
							 确认删除此购买项？
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue delete-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END DELETE CONFIRM MODAL FORM-->
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="add-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建退货项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="add-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="order[id]" style="display :none;" value="${order.order_id}">
									<input type="text" name="product[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">产品名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
<!-- 														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly> -->
<!-- 														<br><a href="#product-pick" class="col-md-8 product-pick" data-toggle="modal">点击此处选择产品</a> -->
														<input type="text" name="poItem[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" id="po-product-name" name="po[product-name]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础单价（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[price]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">退货单价（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_price" placeholder="请选择产品……" value="">
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="base_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">折扣金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="discount_amount" placeholder="请设定折扣率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">税额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="tax_amount" placeholder="请设定税率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">合计金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">退货数量：</label>
										<div class="col-md-9">
											<div class="purchase-quantity">
												<div class="input-group input-small">
													<input type="text" name="purchase_quantity" class="spinner-input form-control" maxlength="4">
													<div class="spinner-buttons input-group-btn btn-group-vertical">
														<button type="button" class="btn spinner-up btn-xs blue">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down btn-xs blue">
														<i class="fa fa-angle-down"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								<div class="form-group">
										<label class="control-label col-md-3">税率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="4" name="tax_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
								<div class="form-group">
										<label class="control-label col-md-3">折扣率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="0" name="discount_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
							</div>
						</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue add-item" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN EDIT ORDER ITEM MODAL FORM-->
			<div class="modal fade" id="edit-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">编辑退货项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="edit-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="order_item[id]" style="display :none;" value="">
									<input type="text" name="product[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">产品名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
<!-- 														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly> -->
<!-- 														<br><a href="#product-pick" class="col-md-8 product-pick" data-toggle="modal">点击此处选择产品</a> -->
														<input type="text" name="poItem[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" id="po-product-name" name="po[product-name]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础单价（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[price]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">购买单价（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_price" placeholder="请选择产品……" value="">
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="base_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">折扣金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="discount_amount" placeholder="请设定折扣率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">税额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="tax_amount" placeholder="请设定税率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">合计金额（元）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">退货数量：</label>
										<div class="col-md-9">
											<div class="purchase-quantity">
												<div class="input-group input-small">
													<input type="text" name="purchase_quantity" class="spinner-input form-control" maxlength="4">
													<div class="spinner-buttons input-group-btn btn-group-vertical">
														<button type="button" class="btn spinner-up btn-xs blue">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down btn-xs blue">
														<i class="fa fa-angle-down"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								<div class="form-group">
										<label class="control-label col-md-3">税率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="4" name="tax_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
								<div class="form-group">
										<label class="control-label col-md-3">折扣率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="0" name="discount_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
							</div>
						</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue edit-item" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END EDIT ORDER ITEM MODAL FORM-->
			<!-- BEGIN PRODUCT PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="product-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择产品</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue product-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END PRODUCT PICK MODAL FORM-->
			<!-- BEGIN BILL ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="bill-address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择账单地址</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue bill-address-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END BILL ADDRESS PICK MODAL FORM-->
			<!-- BEGIN SUPPLIER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="supplier-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择收货单位</h4>
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
			<!-- BEGIN OWNER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="owner-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择经手人</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue owner-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END OWNER PICK MODAL FORM-->
			<!-- BEGIN WAREHOUSE PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="receive-wh-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择发货仓库</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue receive-wh-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END WAREHOUSE PICK MODAL FORM-->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			采购退货订单详情 <small>查看采购退货订单详情</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/order/po_return_list.do">采购退货单列表</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/order/por_details.do?id=${order.order_id}">采购退货订单详情</a>
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
					<!-- Begin: life time stats -->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-shopping-cart"></i>采购退货单号${order.order_number} <span class="hidden-480">
								| ${order.purchase_date} </span>
							</div>
							<div class="actions">
								<a href="po_return_list.do" class="btn default yellow-stripe">
								<i class="fa fa-angle-left"></i>
								<span class="hidden-480">
								返回 </span>
								</a>
								<div class="btn-group">
									<a class="btn default yellow-stripe dropdown-toggle" href="#" data-toggle="dropdown">
									<i class="fa fa-cog"></i>
									<span class="hidden-480">
									工具 </span>
									<i class="fa fa-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right">
										<c:if test="${order.status_code != 'completed' && order.status_code != 'cancelled'}">
										<li>
											<a href="#" class="hold-order">
											搁置订单 </a>
										</li>
										</c:if>
										<c:if test="${order.status_code != 'completed' && order.status_code != 'cancelled'}">
										<li>
											<a href="#" class="cancel-order">
											取消订单</a>
										</li>
										</c:if>
										<c:if test="${order.status_code != 'completed' && order.status_code != 'cancelled' && order.status_code != 'hold'}">
										<li>
											<a href="#" class="complete-order">
											完成订单 </a>
										</li>
										</c:if>
										<c:if test="${order.status_code == 'cancelled'}">
										<li>
											<a href="#" class="reopen-order">
											重开订单 </a>
										</li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="tabbable">
								<ul class="nav nav-tabs nav-tabs-lg">
									<li class="active">
										<a href="#tab_1" data-toggle="tab">
										详细信息 </a>
									</li>
									<li style="display :none;">
										<a href="#tab_2" data-toggle="tab">
										Invoices <span class="badge badge-success">
										4 </span>
										</a>
									</li>
									<li style="display :none;">
										<a href="#tab_3" data-toggle="tab">
										Credit Memos </a>
									</li>
									<li style="display :none;">
										<a href="#tab_4" data-toggle="tab">
										Shipments <span class="badge badge-danger">
										2 </span>
										</a>
									</li>
									<li style="display :none;">
										<a href="#tab_5" data-toggle="tab">
										History </a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_1">
										<div class="row">
											<div class="col-md-6 col-sm-12">
												<div class="portlet yellow-crusta box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>采购退货订单基本信息
														</div>
													</div>
													<div class="portlet-body">
														<div class="row static-info">
															<div class="col-md-5 name">
																 系统订单号 #:
															</div>
															<div class="col-md-7 value">
																 ${order.order_number}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 人工订单号 #:
															</div>
															<div class="col-md-7 value">
																 ${order.order_manual_number}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 下单时间:
															</div>
															<div class="col-md-7 value">
																 ${order.purchase_date}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 订单状态:
															</div>
															<div class="col-md-7 value">
																<span class="label label-success" style="display: none;">
																${order.status_code} </span>
																<span class="label label-success">
																${order.status_value} </span>
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																订单退货总金额:
															</div>
															<div class="col-md-7 value static-total-due">
																 ${order.due_amount}元
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 开单人:
															</div>
															<div class="col-md-7 value">
																 ${order.created_by_name}
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6 col-sm-12">
												<div class="portlet blue-hoki box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>收货单位信息
														</div>
														<div class="actions">
															<a href="#supplier-pick" class="btn btn-default btn-sm supplier-pick" data-toggle="modal">
															<i class="fa fa-pencil"></i> 选取收货单位 </a>
														</div>
													</div>
													<div class="portlet-body supplier-info">
														<div class="row static-info">
															<div class="col-md-5 name">
																 收货单位编号:
															</div>
															<div class="col-md-7 value">
																 ${supplier.accountNumber}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																  收货单位名称:
															</div>
															<div class="col-md-7 value">
																 ${supplier.accountName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 电子邮件:
															</div>
															<div class="col-md-7 value">
																 ${supplier.email}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																  收货单位地址:
															</div>
															<div class="col-md-7 value">
																 ${supplier.addressName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 电话号码:
															</div>
															<div class="col-md-7 value">
																 ${supplier.workphone}
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 col-sm-12">
												<div class="portlet green-meadow box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>发货仓库信息
														</div>
														<div class="actions">
															<a href="#receive-wh-pick" class="btn btn-default btn-sm receive-wh-pick" data-toggle="modal">
															<i class="fa fa-pencil"></i> 选取发货仓库 </a>
														</div>
													</div>
													<div class="portlet-body receive-wh-info">
														<div class="row static-info">
															<div class="col-md-5 name">
																 仓库编号:
															</div>
															<div class="col-md-7 value">
																 ${warehouse.number}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 仓库名称:
															</div>
															<div class="col-md-7 value">
																 <a href="<%=path%>/inventory/warehouse_details.do?id=${warehouse.id}">${warehouse.name}</a>
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 仓库联系人:
															</div>
															<div class="col-md-7 value">
																 ${warehouse.primary_contact_name}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 联系人电话:
															</div>
															<div class="col-md-7 value">
																 ${warehouse.primary_contact_cellphone}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 仓库地址:
															</div>
															<div class="col-md-7 value">
																 ${warehouse.primary_addr_name}
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6 col-sm-12">
												<div class="portlet red-sunglo box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>经手人信息
														</div>
														<div class="actions">
															<a href="#owner-pick" class="btn btn-default btn-sm owner-pick" data-toggle="modal">
															<i class="fa fa-pencil"></i> 更改经手人 </a>
														</div>
													</div>
													<div class="portlet-body owner-info">
														<div class="row static-info">
															<div class="col-md-5 name">
																 经手人账号:
															</div>
															<div class="col-md-7 value">
																 ${owner.userName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 经手人姓:
															</div>
															<div class="col-md-7 value">
																 ${owner.lastName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 经手人名字:
															</div>
															<div class="col-md-7 value">
																 ${owner.firstName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 经手人职位:
															</div>
															<div class="col-md-7 value">
																 ${owner.position.positionName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 经手人邮箱:
															</div>
															<div class="col-md-7 value">
																 ${owner.email}
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 col-sm-12">
												<div class="portlet blue-hoki box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>账单地址详情
														</div>
														<div class="actions">
															<a href="#bill-address-pick" class="btn btn-default btn-sm bill-address-pick" data-toggle="modal">
															<i class="fa fa-pencil"></i> 选取账单地址 </a>
														</div>
													</div>
													<div class="portlet-body bill-address-info">
														<div class="row static-info">
															<div class="col-md-5 name">
																 联系人:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.contactName}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 联系人电话:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.contactCell}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 省/直辖市:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.province}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 城市:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.city}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 区/县:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.county}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 街道:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.street}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 邮编:
															</div>
															<div class="col-md-7 value">
																 ${bill_address.zipcode}
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6 col-sm-12">
												<div class="portlet red-sunglo box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>订单备注
														</div>
														<div class="actions">
															<a href="javascript:;" class="btn btn-default btn-sm po-comment-update">
															<i class="fa fa-pencil"></i> 保存更改 </a>
														</div>
													</div>
													<div class="portlet-body">
														<div class="form-group">
															<textarea class="form-control" rows="6"
																placeholder="输入订单备注…" name="order[description]">${order.order_comment}</textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12 col-sm-12">
												<div class="portlet grey-cascade box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>采购退货明细
														</div>
														<div class="actions">
															<a href="#add-item" class="btn btn-default btn-sm" data-toggle="modal">
															<i class="fa fa-plus"></i> 添加 </a>
														</div>
													</div>
													<div class="portlet-body">
														<div class="table-responsive">
															<table class="table table-hover table-bordered table-striped order-items">
															<thead>
															<tr>
																<th>
																	 产品
																</th>
																<th>
																	状态
																</th>
																<th>
																	 基础单价（元）
																</th>
																<th>
																	 退货单价（元）
																</th>
																<th>
																	 退货数量
																</th>
																<th>
																	 税额（元）
																</th>
																<th>
																	 税率（%）
																</th>
																<th>
																	 折扣金额（元）
																</th>
																<th>
																	 合计金额（元）
																</th>
																<th>
																	操作
																</th>
															</tr>
															</thead>
															<tbody>
															<!-- Generated by po-view-events.js -->
															</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
											</div>
											<div class="col-md-6">
												<div class="well">
													<div class="row static-info align-reverse">
														<div class="col-md-8 name">
															 基础金额合计（元）:
														</div>
														<div class="col-md-3 value base-total">
															 $1,124.50
														</div>
													</div>
													<div class="row static-info align-reverse">
														<div class="col-md-8 name">
															 税额合计（元）:
														</div>
														<div class="col-md-3 value tax-total">
															 $1,260.00
														</div>
													</div>
													<div class="row static-info align-reverse">
														<div class="col-md-8 name">
															 折扣金额合计（元）:
														</div>
														<div class="col-md-3 value discount-total">
															 $1,260.00
														</div>
													</div>
													<div class="row static-info align-reverse">
														<div class="col-md-8 name">
															 应收金额合计（元）:
														</div>
														<div class="col-md-3 value due-total">
															 $1,124.50
														</div>
													</div>
													<div class="row static-info align-reverse">
														<div class="col-md-8">
														</div>
															<c:choose>
																<c:when test="${order.status_code != 'new' && order.status_code != 'reopen'}">
																	<a class="btn btn-lg dark m-icon-big">
																		提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:when>
																<c:otherwise>
																	<a href="#"
																		class="btn btn-lg green m-icon-big submit-order">
																		提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:otherwise>
															</c:choose>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="tab_2">
										<div class="table-container">
											<div class="table-actions-wrapper">
												<span>
												</span>
												<select class="table-group-action-input form-control input-inline input-small input-sm">
													<option value="">Select...</option>
													<option value="pending">Pending</option>
													<option value="paid">Paid</option>
													<option value="canceled">Canceled</option>
												</select>
												<button class="btn btn-sm yellow table-group-action-submit"><i class="fa fa-check"></i> Submit</button>
											</div>
											<table class="table table-striped table-bordered table-hover" id="datatable_invoices">
											<thead>
											<tr role="row" class="heading">
												<th width="5%">
													<input type="checkbox" class="group-checkable">
												</th>
												<th width="5%">
													 Invoice&nbsp;#
												</th>
												<th width="15%">
													 Bill To
												</th>
												<th width="15%">
													 Invoice&nbsp;Date
												</th>
												<th width="10%">
													 Amount
												</th>
												<th width="10%">
													 Status
												</th>
												<th width="10%">
													 Actions
												</th>
											</tr>
											<tr role="row" class="filter">
												<td>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_invoice_no">
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_invoice_bill_to">
												</td>
												<td>
													<div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_invoice_date_from" placeholder="From">
														<span class="input-group-btn">
														<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_invoice_date_to" placeholder="To">
														<span class="input-group-btn">
														<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<div class="margin-bottom-5">
														<input type="text" class="form-control form-filter input-sm" name="order_invoice_amount_from" placeholder="From"/>
													</div>
													<input type="text" class="form-control form-filter input-sm" name="order_invoice_amount_to" placeholder="To"/>
												</td>
												<td>
													<select name="order_invoice_status" class="form-control form-filter input-sm">
														<option value="">Select...</option>
														<option value="pending">Pending</option>
														<option value="paid">Paid</option>
														<option value="canceled">Canceled</option>
													</select>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> Search</button>
													</div>
													<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> Reset</button>
												</td>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
										</div>
									</div>
									<div class="tab-pane" id="tab_3">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_credit_memos">
											<thead>
											<tr role="row" class="heading">
												<th width="5%">
													 Credit&nbsp;Memo&nbsp;#
												</th>
												<th width="15%">
													 Bill To
												</th>
												<th width="15%">
													 Created&nbsp;Date
												</th>
												<th width="10%">
													 Status
												</th>
												<th width="10%">
													 Actions
												</th>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
										</div>
									</div>
									<div class="tab-pane" id="tab_4">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_shipment">
											<thead>
											<tr role="row" class="heading">
												<th width="5%">
													 Shipment&nbsp;#
												</th>
												<th width="15%">
													 Ship&nbsp;To
												</th>
												<th width="15%">
													 Shipped&nbsp;Date
												</th>
												<th width="10%">
													 Quantity
												</th>
												<th width="10%">
													 Actions
												</th>
											</tr>
											<tr role="row" class="filter">
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_shipment_no">
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_shipment_ship_to">
												</td>
												<td>
													<div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_shipment_date_from" placeholder="From">
														<span class="input-group-btn">
														<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_shipment_date_to" placeholder="To">
														<span class="input-group-btn">
														<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<div class="margin-bottom-5">
														<input type="text" class="form-control form-filter input-sm" name="order_shipment_quantity_from" placeholder="From"/>
													</div>
													<input type="text" class="form-control form-filter input-sm" name="order_shipment_quantity_to" placeholder="To"/>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> Search</button>
													</div>
													<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> Reset</button>
												</td>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
										</div>
									</div>
									<div class="tab-pane" id="tab_5">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_history">
											<thead>
											<tr role="row" class="heading">
												<th width="25%">
													 Datetime
												</th>
												<th width="55%">
													 Description
												</th>
												<th width="10%">
													 Notification
												</th>
												<th width="10%">
													 Actions
												</th>
											</tr>
											<tr role="row" class="filter">
												<td>
													<div class="input-group date datetime-picker margin-bottom-5" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_from" placeholder="From">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date datetime-picker" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_to" placeholder="To">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_history_desc" placeholder="To"/>
												</td>
												<td>
													<select name="order_history_notification" class="form-control form-filter input-sm">
														<option value="">Select...</option>
														<option value="pending">Pending</option>
														<option value="notified">Notified</option>
														<option value="failed">Failed</option>
													</select>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> Search</button>
													</div>
													<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> Reset</button>
												</td>
											</tr>
											</thead>
											<tbody>
											</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End: life time stats -->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/fuelux/js/spinner.min.js"></script>
<script src="../assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<!-- <script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script> -->
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js"></script>
<script src="../assets/admin/pages/scripts/ecommerce-orders-view.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/order/por-view-events.js" type="text/javascript"></script>
<script>
        jQuery(document).ready(function() {    
           	Metronic.init(); // init metronic core components
           	navigationBar.activePOMenu();
            $('ul.sub-menu').find('li').removeClass('active');
     	   	$('li.po-menu').find('ul.sub-menu').children('li:eq(1)').addClass('active');
			Layout.init(); // init current layout
			//QuickSidebar.init(); // init quick sidebar
			Demo.init(); // init demo features
           	EcommerceOrdersView.init();
           	PORViewEvents.init();
        });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>