<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<!-- BEGIN DELETE CONFIRM MODAL FORM-->
			<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除销售项</h4>
						</div>
						<div class="modal-body">
						<input type="text" name="orderItem[id]" style="display :none;" value="">
						<input type="text" name="product[name]" style="display :none;">
							 确认删除此销售项？
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
			<!-- BEGIN REMOVE ATTACHMENT FORM-->
			<div class="modal fade" id="remove-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除附件</h4>
						</div>
						<div class="modal-body">
							 确认删除此附件？
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue remove-image-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END REMOVE ATTACHMENT MODAL FORM-->
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="add-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建销售项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="add-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="order[id]" style="display :none;" value="${order.order_id}">
									<input type="text" name="product[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">产品型号： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
<!-- 														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly> -->
<!-- 														<br><a href="#product-pick" class="col-md-8 product-pick" data-toggle="modal">点击此处选择产品</a> -->
														<input type="text" name="poItem[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="product[name]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
								<div class="form-group">
									<input type="text" name="warehouse[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">发货仓库名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择发货仓库…" name="warehouse[name]" readonly>
																	<span class="input-group-addon">
																	<a href="#delivery-wh-pick" class="delivery-wh-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
<!-- 								<div class="form-group"> -->
<%-- 													<label class="col-md-3 control-label">基础单价（${order.currency_val}）： --%>
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<input type="text" class="form-control" name="product[price]" placeholder="请选择产品……" value="" readonly> -->
<!-- 													</div> -->
<!-- 								</div> -->
								<div class="form-group">
													<label class="col-md-3 control-label">销售单价（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_price" placeholder="请选择产品……" value="">
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础金额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="base_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
<!-- 								<div class="form-group"> -->
<%-- 													<label class="col-md-3 control-label">折扣金额（${order.currency_val}）： --%>
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<input type="text" class="form-control" name="discount_amount" placeholder="请设定折扣率……" value="" readonly> -->
<!-- 													</div> -->
<!-- 								</div> -->
								<div class="form-group">
													<label class="col-md-3 control-label">税额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="tax_amount" placeholder="请设定税率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">合计金额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">销售数量：</label>
										<div class="col-md-9">
											<div class="purchase-quantity">
												<div class="input-group input-small">
													<input type="text" name="purchase_quantity" class="spinner-input form-control" maxlength="4">
													<!-- <div class="spinner-buttons input-group-btn btn-group-vertical">
														<button type="button" class="btn spinner-up btn-xs blue">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down btn-xs blue">
														<i class="fa fa-angle-down"></i>
														</button>
													</div> -->
												</div>
											</div>
										</div>
									</div>
								<div class="form-group">
										<label class="control-label col-md-3">税率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="${default_tax_ratio}" name="tax_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">折扣率：</label> -->
<!-- 										<div class="col-md-6"> -->
<!-- 											<div class="input-inline input-medium"> -->
<!-- 												<input type="text" value="0" name="discount_percent" class="form-control touchspin_percent"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 								</div> -->
								<div class="form-group">
													<label class="col-md-3 control-label">备注：
													</label>
													<div class="col-md-8">
														<textarea class="form-control" rows="2"	placeholder="输入备注…" name="item_comment"></textarea>
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
							<h4 class="modal-title">编辑销售项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="edit-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="order[id]" style="display :none;" value="${order.order_id}">
									<input type="text" name="order_item[id]" style="display :none;" value="">
									<input type="text" name="product[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">产品名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="product[name]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
								<div class="form-group">
									<input type="text" name="warehouse[id]" style="display :none;" value="">
													<label class="col-md-3 control-label">发货仓库名称： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择发货仓库…" name="warehouse[name]" readonly>
																	<span class="input-group-addon">
																	<a href="#delivery-wh-pick" class="delivery-wh-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">销售单价（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_price" placeholder="请选择产品……" value="">
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">基础金额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="base_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">税额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="tax_amount" placeholder="请设定税率……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">合计金额（${order.currency_val}）：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="purchase_amount" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">销售数量：</label>
										<div class="col-md-9">
											<div class="purchase-quantity">
												<div class="input-group input-small">
													<input type="text" name="purchase_quantity" class="spinner-input form-control" maxlength="4">
													<!-- <div class="spinner-buttons input-group-btn btn-group-vertical">
														<button type="button" class="btn spinner-up btn-xs blue">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down btn-xs blue">
														<i class="fa fa-angle-down"></i>
														</button>
													</div> -->
												</div>
											</div>
										</div>
									</div>
								<div class="form-group">
										<label class="control-label col-md-3">税率：</label>
										<div class="col-md-6">
											<div class="input-inline input-medium">
												<input type="text" value="${default_tax_ratio}" name="tax_percent" class="form-control touchspin_percent">
											</div>
										</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">备注：
													</label>
													<div class="col-md-8">
														<textarea class="form-control" rows="2"	placeholder="输入备注…" name="item_comment"></textarea>
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
			<!-- BEGIN SHIP ADDRESS PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="ship-address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择收货地址</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue ship-address-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END SHIP ADDRESS PICK MODAL FORM-->
			<!-- BEGIN AREA PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="area-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
							<h4 class="modal-title">选择区域</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue area-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END AREA PICK MODAL FORM-->
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
			<!-- BEGIN CUSTOMER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="customer-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择直接客户</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue customer-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END CUSTOMER PICK MODAL FORM-->
			<!-- BEGIN LV2 CUSTOMER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="customer-lv2-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择间接客户</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue customer-lv2-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END LV2 CUSTOMER PICK MODAL FORM-->
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
			<div class="modal fade bs-modal-lg" id="delivery-wh-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择发货仓库</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue delivery-wh-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END WAREHOUSE PICK MODAL FORM-->