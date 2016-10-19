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
							<h4 class="modal-title">删除出库项</h4>
						</div>
						<div class="modal-body">
						<input type="text" name="orderItem[id]" style="display :none;" value="">
						<!-- test -->
						<input type="text" name="product[name]" style="display :none;" value="">
						<input type="text" name="requisition[id]" style="display :none;" value="${requisitionDetails.requisition_id}">
						<input type="text" name="requisition[type_value]" style="display: none;" value="${requisitionDetails.requisition_type }">
						<!-- test -->
						<input type="reset" name="reset" style="display: none;"/>
							 确认删除此出库项？
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
							<h4 class="modal-title">新建出库项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="add-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="req[id]" style="display :none;" value="${requisitionDetails.requisition_id}">
									<input type="text" name="product[id]" style="display :none;" value="">
									<input type="text" name="requisition[type_value]" style="display: none;" value="${requisitionDetails.requisition_type }">
									<!-- test -->
									<input type="reset" name="reset" style="display: none;"/>
													<label class="col-md-3 control-label">产品编号： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
<!-- 														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly> -->
<!-- 														<br><a href="#product-pick" class="col-md-8 product-pick" data-toggle="modal">点击此处选择产品</a> -->
														<input type="text" name="product[id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="product[number]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" name="warehouse[id]" style="display :none;" value=""> -->
<!-- 													<label class="col-md-3 control-label">收货仓库名称： <span class="required"> -->
<!-- 													* </span> -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<div class="input-group"> -->
<!-- 																<input type="text" class="form-control" placeholder="请选择收货仓库…" name="warehouse[name]" readonly> -->
<!-- 																	<span class="input-group-addon"> -->
<!-- 																	<a href="#receive-wh-pick" class="receive-wh-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																	</span> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" name="contract[id]" style="display :none;" value=""> -->
<!-- 													<label class="col-md-3 control-label">合同编号： <span class="required"> -->
<!-- 													* </span> -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<div class="input-group"> -->
<!-- 																<input type="text" class="form-control" placeholder="请选择合同…" name="contract[number]" readonly> -->
<!-- 																	<span class="input-group-addon"> -->
<!-- 																	<a href="#contract-pick" class="contract-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																	</span> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 													<label class="col-md-3 control-label">平均单价（元）： -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<input type="text" class="form-control" name="product[price]" placeholder="请选择产品……" value="" readonly> -->
<!-- 													</div> -->
<!-- 								</div> -->
								<div class="form-group">
													<label class="col-md-3 control-label">产品名称：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">产品型号：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[model]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">出库数量：</label>
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
							<h4 class="modal-title">编辑出库项目</h4>
						</div>
						<div class="modal-body">
						<form class="form-horizontal form-bordered" id="edit-item-form">
							<div class="form-body">
								<div class="form-group">
									<input type="text" name="req[id]" style="display :none;" value="${requisitionDetails.requisition_id}">
									<input type="text" name="order_item[id]" style="display :none;" value="">
									<input type="text" name="product[id]" style="display :none;" value="">
									<input type="text" name="requisition[type_value]" style="display: none;" value="${requisitionDetails.requisition_type }">
													<label class="col-md-3 control-label">产品编号： <span class="required">
													* </span>
													</label>
													<div class="col-md-8">
														<input type="text" name="poItem[product-id]" style="display: none;">
														<div class="input-group">
																<input type="text" class="form-control" placeholder="请选择产品…" name="product[number]" readonly>
																	<span class="input-group-addon">
																	<a href="#product-pick" class="product-pick" data-toggle="modal"><i class="fa fa-search"></i>
																	</a>
																	</span>
														</div>
													</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" name="warehouse[id]" style="display :none;" value=""> -->
<!-- 													<label class="col-md-3 control-label">收货仓库名称： <span class="required"> -->
<!-- 													* </span> -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<div class="input-group"> -->
<!-- 																<input type="text" class="form-control" placeholder="请选择收货仓库…" name="warehouse[name]" readonly> -->
<!-- 																	<span class="input-group-addon"> -->
<!-- 																	<a href="#receive-wh-pick" class="receive-wh-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																	</span> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input type="text" name="contract[id]" style="display :none;" value=""> -->
<!-- 													<label class="col-md-3 control-label">合同编号： <span class="required"> -->
<!-- 													* </span> -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<div class="input-group"> -->
<!-- 																<input type="text" class="form-control" placeholder="请选择合同…" name="contract[number]" readonly> -->
<!-- 																	<span class="input-group-addon"> -->
<!-- 																	<a href="#contract-pick" class="contract-pick" data-toggle="modal"><i class="fa fa-search"></i> -->
<!-- 																	</a> -->
<!-- 																	</span> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 													<label class="col-md-3 control-label">平均单价（元）： -->
<!-- 													</label> -->
<!-- 													<div class="col-md-8"> -->
<!-- 														<input type="text" class="form-control" name="product[price]" placeholder="请选择产品……" value="" readonly> -->
<!-- 													</div> -->
<!-- 								</div> -->
								<div class="form-group">
													<label class="col-md-3 control-label">产品名称：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[name]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
								<div class="form-group">
													<label class="col-md-3 control-label">产品型号：
													</label>
													<div class="col-md-8">
														<input type="text" class="form-control" name="product[model]" placeholder="请选择产品……" value="" readonly>
													</div>
								</div>
							 	<div class="form-group">
										<label class="control-label col-md-3">出库数量：</label>
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
			<!-- BEGIN CUSTOMER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="customer-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择客户/集成商</h4>
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
							<h4 class="modal-title">选择收货仓库</h4>
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
			<!-- BEGIN CONTRACT PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="contract-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择合同</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue contract-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END CONTRACT PICK MODAL FORM-->
			<!-- BEGIN ORDER PICK MODAL FORM-->
			<div class="modal fade bs-modal-lg" id="order-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">选择关联销售订单</h4>
						</div>
						<div class="modal-body">
						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue order-pick" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- END ORDER PICK MODAL FORM-->