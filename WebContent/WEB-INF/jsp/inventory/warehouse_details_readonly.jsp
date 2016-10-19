<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 2.0
 * Date: 2016/3/1
 * Description:  Warehouse details read only page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 仓库设置</title>
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
<!-- <link href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/> -->
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
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
			<!-- BEGIN DELETE CONFIRM MODAL FORM-->
			<div class="modal fade" id="delete-stock-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除库存产品</h4>
						</div>
						<div class="modal-body">
						<input type="text" name="stockInfo[id]" style="display :none;" value="">
							 确认删除此库存产品？
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue delete-stock-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END DELETE CONFIRM MODAL FORM-->
			<!-- BEGIN ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择仓库地址</h4>
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
			<!-- BEGIN EDIT STOCK INFO MODAL FORM-->
			<div class="modal fade" id="edit-stock-info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">编辑库存产品</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="edit-stock-info-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="stockInfo[id]" style="display :none;">
													<label class="col-md-3 control-label">产品名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<input type="text" name="stock[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="stock[product-name]" readonly>
<!-- 																	<span class="input-group-addon"> -->
<!-- 																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																	</span> -->
														</div>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">库存数量：</label>
										<div class="col-md-9">
											<div class="stock-quantity">
												<div class="input-group input-small">
													<input type="text" name="stock_quantity" class="spinner-input form-control" maxlength="4">
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
													<label class="col-md-3 control-label">库存单位：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="stock[sku]" placeholder="请输入SKU……" value="">
													</div>
								</div>
							</div>
						</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue edit-stock-info" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END EDIT STOCK INFO MODAL FORM-->
			<!-- BEGIN ADD STOCK INFO MODAL FORM-->
			<div class="modal fade" id="add-stock-info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建库存产品</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="add-stock-info-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="warehouse[id]" style="display :none;" value="${warehouseDetails.id}">
													<label class="col-md-3 control-label">产品名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<input type="text" name="stock[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="stock[product-name]" readonly>
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
														<input type="text" class="form-control" name="stock[product-price]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">库存数量：</label>
										<div class="col-md-9">
											<div class="stock-quantity">
												<div class="input-group input-small">
													<input type="text" name="stock_quantity" class="spinner-input form-control" maxlength="4">
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
													<label class="col-md-3 control-label">库存单位：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="stock[sku]" placeholder="请输入SKU……" value="">
													</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">税率：</label> -->
<!-- 										<div class="col-md-6"> -->
<!-- 											<div class="input-inline input-medium"> -->
<!-- 												<input type="text" value="4" name="tax_percent" class="form-control touchspin_percent"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 								</div> -->
							</div>
						</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue add-stock-info" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END ADD STOCK INFO MODAL FORM-->
			<!-- BEGIN PRODUCT PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="product-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择产品</h4>
						</div>
						<div class="modal-body">
						<table class="table table-striped table-bordered table-hover" id="product_pick">
							<thead>
							<tr>
								<th>
								</th>
								<th>
									 产品编号
								</th>
								<th>
									 产品名称
								</th>
								<th>
									 产品类别
								</th>
								<th>
									 产品描述
								</th>
								<th>
									 单价（元）
								</th>
								<th>
									 库存
								</th>
							</tr>
							</thead>
							<tbody>
												
							</tbody>
							</table>
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
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			仓库管理 <small>仓库信息设置</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
<!-- 					</li> -->
<!-- 					<li> -->
<%-- 						<a href="<%=path%>/inventory/warehouse_list.do">仓库列表</a> --%>
<!-- 						<i class="fa fa-angle-right"></i> -->
<!-- 					</li> -->
					<li>
						<a href="<%=path%>/inventory/warehouse_details.do?id=${warehouseDetails.id}">仓库详细信息</a>
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
						<ul class="nav nav-tabs nav-tabs-lg">
									<li class="active">
										<a href="#tab_1" data-toggle="tab">
										详细信息 </a>
									</li>
									<li>
										<a href="#tab_2" data-toggle="tab">
										出入库历史 </a>
									</li>
						</ul>
						<div class="tab-content">
								<!-- BEGIN TAB0 -->
							<div class="tab-pane active" id="tab_1">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>${warehouseDetails.name}
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
															<label class="control-label">仓库编号：<span class="required">* </span></label>
															<input type="text" name="warehouse_id" value="${warehouseDetails.id}" style="display: none;">
															<input type="text" class="form-control" placeholder="请输入仓库编号…" name="warehouse[number]" id="warehouse-number" value="${warehouseDetails.number}" readonly>													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">仓库名称：<span class="required">* </span></label>
															<input type="text" class="form-control" placeholder="请输入仓库名称…" name="warehouse[name]" id="warehouse-name" value="${warehouseDetails.name}" readonly>													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
																<label class="control-label">仓库联系人：</label>
															<input type="text" class="form-control" placeholder="请输入联系人名称…" name="warehouse[contact_name]" id="wh_contact_name" value="${warehouseDetails.primary_contact_name}" readonly>	
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">仓库联系人电话：</label>
															<input type="text" class="form-control" placeholder="请输入联系人电话…" name="warehouse[contact_phone]" id="wh_contact_phone" value="${warehouseDetails.primary_contact_cellphone}" readonly>	
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">仓库区域：</label>
															<input type="text" name="warehouse[address_id]" value="${warehouseDetails.primary_addr_id}" style="display: none;">
															<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择区域…" id="wh_area_name" name="warehouse[area_name]" value="${warehouseDetails.primary_addr_name}" readonly>
<!-- 																<span class="input-group-addon"> -->
<!-- 																	<a href="#address-pick" class="address-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																</span> -->
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">仓库详细地址：</label>
															<input type="text" class="form-control" placeholder="请输入详细地址…" id="wh_address_name" name="warehouse[address_name]" value="${warehouseDetails.address_name}" readonly>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label>状态</label>
															 <div class="radio-list">
																<label class="radio-inline">
																<input type="radio" name="warehouse[acitve]" id="warehouse-active" value="true" disabled="disabled" <c:if test="${warehouseDetails.active==true}"> checked</c:if> readonly> 可用</label>
																<label class="radio-inline">
																<input type="radio" name="warehouse[acitve]" id="warehouse-active" value="false" disabled="disabled" <c:if test="${warehouseDetails.active==false}"> checked</c:if> readonly> 禁用 </label>
															</div> 
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>描述</label>
															<textarea class="form-control" rows="3" readonly
																placeholder="输入描述…" name="description">${warehouseDetails.comment}</textarea>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																此记录是由${warehouseDetails.created_by_user_name}在 ${warehouseDetails.created_date}创建</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																此记录最近一次修改是由${warehouseDetails.updated_by_user_name}在 ${warehouseDetails.updated_date}更新</p>
														</div>
													</div>
												</div>
											</div>
											<div class="form-actions right">
												<!-- <button type="button" class="btn blue" id="update-warehouse-confirm">
													<i class="fa fa-check"></i>确认？？？？
												</button>
												<button type="button" onclick="goBack()" class="btn default cancel">取消</button> -->
											</div>
										</form>
										<div class="row">
											<div class="col-md-12 col-sm-12">
												<div class="portlet grey-cascade box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>库存产品
														</div>
<!-- 														<div class="actions"> -->
<!-- 															<a href="#add-stock-info" class="btn btn-default btn-sm" data-toggle="modal"> -->
<!-- 															<i class="fa fa-plus"></i> 添加 </a> -->
<!-- 														</div> -->
													</div>
													<div class="portlet-body">
														<div class="table-responsive">
															<table class="table table-hover table-bordered table-striped stock-items">
															<thead>
															<tr>
																<th>
																	 产品编号
																</th>
																<th>
																	 产品名称
																</th>
																<th>
																	 产品型号
																</th>
																<th>
																	库存数量
																</th>
																<th>
																	SKU
																</th>
																<th>
																	待入库数量
																</th>
																<th>
																	待出库数量
																</th>
																<th>
																	最后更新时间
																</th>
															</tr>
															</thead>
															<tbody>
																		<c:forEach var="item" items="${items_list}">
																			<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
																			<tr class="odd gradeX" id="${item.item_id}">
																				<td id="${item.item_product_id}">
																				<a href="<%=path%>/prodadmin/details.do?id=${item.item_product_id}">${item.item_product_number}</a>
																				</td>
																				<td>${item.item_product_name}</td>
																				<td>${item.item_product_model}</td>
																				<td>${item.item_stock}</td>
																				<td>${item.item_sku}</td>
																				<td>${item.item_ordered_stock_in}</td>
																				<td>${item.item_ordered_stock_out}</td>
																				<td>${item.item_updated_date}</td>
																			</tr>
																		</c:forEach>
															</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- END FORM-->
									</div>
								</div>
							</div>
							<!-- END TAB0 -->
							<!-- BEGIN TAB2 -->
							<div class="tab-pane" id="tab_2">
							<div class="portlet">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-shopping-cart"></i>所有出入库历史记录
									</div>
									<div class="actions">
										<button class="btn btn-sm green query-assistant"><i class="fa fa-search"></i> 搜索</button>
										<button class="btn btn-sm yellow query-cancel" style="display: none;"><i class="fa fa-search"></i> 取消搜索</button>
									</div>
								</div>
								<div class="portlet-body">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_history">
											<thead>
											<tr role="row" class="heading">
												<th width="15%">
													 出入库时间
												</th>
												<th width="10%">
													 出入库产品
												</th>
												<th width="10%">
													 操作类型
												</th>
												<th width="10%">
													 当前库存
												</th>
												<th width="10%">
													 出入库数量
												</th>
<!-- 												<th width="10%"> -->
<!-- 													出入库单价 -->
<!-- 												</th> -->
<!-- 												<th width="10%"> -->
<!-- 													出入库金额 -->
<!-- 												</th> -->
												<th width="15%">
													操作描述
												</th>
												<th width="10%">
													出入库单号
												</th>
												<th width="10%">
													操作
												</th>
											</tr>
											<tr role="row" class="filter" style="display: none;">
												<td>
													<div class="input-group date datetime-picker margin-bottom-5" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="stock_history_date_from" placeholder="从">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date datetime-picker" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="stock_history_date_to" placeholder="到">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="stock_history_product_name" placeholder="请输入搜索条件…"/>
												</td>
												<td>
													<select name="stock_history_type" class="form-control form-filter input-sm">
														<option value="">请选择...</option>
														<option value="Stock In">入库</option>
														<option value="Stock Out">出库</option>
													</select>
												</td>
												<td>
													<div class="margin-bottom-5">
														<input type="text" class="form-control form-filter input-sm" name="stock_history_original_from" placeholder="从"/>
													</div>
													<input type="text" class="form-control form-filter input-sm" name="stock_history_original_to" placeholder="到"/>
												</td>
												<td>
													<div class="margin-bottom-5">
														<input type="text" class="form-control form-filter input-sm" name="stock_history_quantity_from" placeholder="从"/>
													</div>
													<input type="text" class="form-control form-filter input-sm" name="stock_history_quantity_to" placeholder="到"/>
												</td>
<!-- 												<td> -->
<!-- 													<div class="margin-bottom-5"> -->
<!-- 														<input type="text" class="form-control form-filter input-sm" name="stock_history_price_from" placeholder="从"/> -->
<!-- 													</div> -->
<!-- 													<input type="text" class="form-control form-filter input-sm" name="stock_history_price_to" placeholder="到"/> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<div class="margin-bottom-5"> -->
<!-- 														<input type="text" class="form-control form-filter input-sm" name="stock_history_amount_from" placeholder="从"/> -->
<!-- 													</div> -->
<!-- 													<input type="text" class="form-control form-filter input-sm" name="stock_history_amount_to" placeholder="到"/> -->
<!-- 												</td> -->
												<td>
													<input type="text" class="form-control form-filter input-sm" name="stock_history_desc" placeholder="请输入搜索条件…"/>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="stock_history_req_number" placeholder="请输入搜索条件…"/>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 提交搜索</button>
													</div>
													<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 重置搜索</button>
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
								<!-- END TAB2 -->
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
</div>
<!-- END CONTAINER -->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script> -->
<!-- <script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script> -->
<script type="text/javascript" src="../assets/global/plugins/moment.min.js"></script>
<script type="text/javascript" src="../assets/admin/pages/scripts/components-pickers.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="../assets/global/plugins/fuelux/js/spinner.min.js"></script>
<script src="../assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script> -->
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js"></script>
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
	   Demo.init(); // init demo features
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