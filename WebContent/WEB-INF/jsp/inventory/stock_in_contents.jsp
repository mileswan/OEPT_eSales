<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN PAGE CONTENT-->
			<input type="text" class="form-control" name="readOnlyFlag" value="${readOnlyFlag}" style="display: none;">
			<div class="row approval-content">
				<div class="col-md-12">
					<!-- Begin: life time stats -->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-shopping-cart"></i>系统单号${requisitionDetails.requisition_number} <span class="hidden-480">
								| 创建时间：${requisitionDetails.created_date} </span>
							</div>
							<div class="actions">
								<a onclick="history.go(-1)" class="btn default yellow-stripe">
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
<%-- 										<c:if test="${order.status_code != 'completed' && order.status_code != 'cancelled'}"> --%>
<!-- 										<li> -->
<!-- 											<a href="#" class="hold-order"> -->
<!-- 											搁置订单 </a> -->
<!-- 										</li> -->
<%-- 										</c:if> --%>
<%-- 										<c:if test="${order.status_code != 'completed' && order.status_code != 'cancelled'}"> --%>
<!-- 										<li> -->
<!-- 											<a href="#" class="cancel-order"> -->
<!-- 											取消订单</a> -->
<!-- 										</li> -->
<%-- 										</c:if> --%>
										<c:if test="${requisitionDetails.status_code == 'submit - approved' || requisitionDetails.status_code == 'submitted'}">
										<li>
											<a href="#" class="complete-order">
											完成入库单 </a>
										</li>
										</c:if>
										<c:if test="${requisitionDetails.status_code == 'cancelled' || requisitionDetails.status_code == 'submit - rejected'}">
										<li>
											<a href="#" class="reopen-order">
											重开入库单 </a>
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
									<li>
										<a href="#tab_4" data-toggle="tab">
										关联订单 </a>
									</li>
									<li>
										<a href="#tab_5" data-toggle="tab" class="queryApprovalStatus">
										查看审批流程 </a>
									</li>
									<li>
										<a href="#tab_6" data-toggle="tab">
										审批历史记录 </a>
									</li>
									<li>
										<a href="#tab_7" data-toggle="tab">
										更新历史记录 </a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_1">
										<div class="row">
											<div class="col-md-6 col-sm-12">
												<div class="portlet yellow-crusta box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>单据基本信息
														</div>
														<c:if test="${readOnlyFlag == false}">
														<div class="actions">
															<a href="javascript:;" class="btn btn-default btn-sm req-base-update">
															<i class="fa fa-pencil"></i> 保存更改 </a>
														</div>
														</c:if>
													</div>
													<div class="portlet-body">
														<div class="row static-info">
															<div class="col-md-5 name">
																系统单据号 #:
															</div>
															<div class="col-md-7 value">
																 ${requisitionDetails.requisition_number}
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 人工单据号 #:
															</div>
															<div class="col-md-7 value">
																 <input type="text" class="form-control" placeholder="请输入人工单据号…" name="req[man_number]" value="${requisitionDetails.requisition_manual_number}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
															</div>
														</div>
														<div class="row static-info">
																<div class="col-md-5 name">
																	入库类型：
																</div>
																<div class="col-md-7 value">
																	<select class="form-control" name="req[subtype]" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
																		<c:forEach var="stock_in_type" items="${stock_in_type_list}">
																			<option value="${stock_in_type.list_name}" <c:if test="${requisitionDetails.requisition_subtype_cd == stock_in_type.list_name}">selected</c:if> >${stock_in_type.list_value}</option>
																		</c:forEach>
																	</select>
																</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 入库日期:
															</div>
															<div class="col-md-7 value">
																<div class="input-group input-medium date date-picker"
																	data-date-format="yyyy-mm-dd"
																	data-date="">
																	<input type="text" class="form-control" readonly
																		name="req[receive_date]" value="${requisitionDetails.receive_date}">
																	<span class="input-group-btn">
																		<button class="btn default" type="button">
																			<i class="fa fa-calendar"></i>
																		</button>
																	</span>
																</div>
															</div>
														</div>
														<div class="row static-info">
															<div class="col-md-5 name">
																 入库单状态:
															</div>
															<div class="col-md-7 value">
																<input type="text" class="form-control" name="req[status_code]" value="${requisitionDetails.status_code}" style="display: none;">
																<span class="label label-success">
																${requisitionDetails.status_value} </span>
															</div>
														</div>
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<%-- 																入单总金额（${requisitionDetails.currency_val}）: --%>
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value static-total-due"> -->
<%-- 																 ${requisitionDetails.due_amount} --%>
<!-- 															</div> -->
<!-- 														</div> -->
														<div class="row static-info">
															<div class="col-md-5 name">
																 创建人:
															</div>
															<div class="col-md-7 value">
																 ${requisitionDetails.created_by_name}
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
														<c:if test="${readOnlyFlag == false}">
														<div class="actions">
															<a href="javascript:;" class="btn btn-default btn-sm req-comment-update">
															<i class="fa fa-pencil"></i> 保存更改 </a>
														</div>
														</c:if>
													</div>
													<div class="portlet-body">
														<div class="form-group">
															<textarea class="form-control" rows="6" <c:if test="${readOnlyFlag == true}">readonly</c:if>
																placeholder="输入备注…" name="req[description]">${requisitionDetails.requisition_comment}</textarea>
														</div>
													</div>
												</div>
											</div>
<!-- 											<div class="col-md-6 col-sm-12"> -->
<!-- 												<div class="portlet red-sunglo box"> -->
<!-- 													<div class="portlet-title"> -->
<!-- 														<div class="caption"> -->
<!-- 															<i class="fa fa-cogs"></i>经手人信息 -->
<!-- 														</div> -->
<%-- 														<c:if test="${readOnlyFlag == false}"> --%>
<!-- 														<div class="actions"> -->
<!-- 															<a href="#owner-pick" class="btn btn-default btn-sm owner-pick" data-toggle="modal"> -->
<!-- 															<i class="fa fa-pencil"></i> 更改经手人 </a> -->
<!-- 														</div> -->
<%-- 														</c:if> --%>
<!-- 													</div> -->
<!-- 													<div class="portlet-body owner-info"> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 经手人账号: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${owner.userName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 经手人姓: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${owner.lastName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 经手人名字: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${owner.firstName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 经手人职位: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${owner.position.positionName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 经手人邮箱: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${owner.email} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-md-6 col-sm-12"> -->
<!-- 												<div class="portlet blue-hoki box"> -->
<!-- 													<div class="portlet-title"> -->
<!-- 														<div class="caption"> -->
<!-- 															<i class="fa fa-cogs"></i>供应商信息 -->
<!-- 														</div> -->
<!-- 														<div class="actions"> -->
<!-- 															<a href="#supplier-pick" class="btn btn-default btn-sm supplier-pick" data-toggle="modal"> -->
<!-- 															<i class="fa fa-pencil"></i> 选取供应商 </a> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="portlet-body supplier-info"> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 供应商编号: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${supplier.accountNumber} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 供应商名称: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${supplier.accountName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 电子邮件: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${supplier.email} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 供应商地址: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${supplier.addressName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 电话号码: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${supplier.workphone} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
										</div>
										<div class="row">
											<div class="col-md-6 col-sm-12">
												<div class="portlet green-meadow box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>收货仓库信息
														</div>
														<c:if test="${readOnlyFlag == false}">
														<div class="actions">
															<a href="#receive-wh-pick" class="btn btn-default btn-sm receive-wh-pick" data-toggle="modal">
															<i class="fa fa-pencil"></i> 选取收货仓库 </a>
														</div>
														</c:if>
													</div>
													<div class="portlet-body receive-wh-info">
													<input type="text" name="warehouse[id]" style="display :none;" value="">
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
																 <a href="<%=path%>/inventory/warehouse_details_readonly.do?id=${warehouse.id}">${warehouse.name}</a>
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
<!-- 											<div class="col-md-6 col-sm-12"> -->
<!-- 												<div class="portlet blue-hoki box"> -->
<!-- 													<div class="portlet-title"> -->
<!-- 														<div class="caption"> -->
<!-- 															<i class="fa fa-cogs"></i>账单地址详情 -->
<!-- 														</div> -->
<!-- 														<div class="actions"> -->
<!-- 															<a href="#bill-address-pick" class="btn btn-default btn-sm bill-address-pick" data-toggle="modal"> -->
<!-- 															<i class="fa fa-pencil"></i> 选取账单地址 </a> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="portlet-body bill-address-info"> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 联系人: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.contactName} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 联系人电话: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.contactCell} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 省/直辖市: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.province} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 城市: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.city} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 区/县: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.county} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 街道: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.street} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 														<div class="row static-info"> -->
<!-- 															<div class="col-md-5 name"> -->
<!-- 																 邮编: -->
<!-- 															</div> -->
<!-- 															<div class="col-md-7 value"> -->
<%-- 																 ${bill_address.zipcode} --%>
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
										</div>
										<div class="row">
											<div class="col-md-12 col-sm-12">
												<div class="portlet grey-cascade box">
													<div class="portlet-title">
														<div class="caption">
															<i class="fa fa-cogs"></i>入库明细
														</div>
														<c:if test="${readOnlyFlag == false}">
														<div class="actions">
															<a href="#add-item" class="btn btn-default btn-sm" data-toggle="modal">
															<i class="fa fa-plus"></i> 添加 </a>
														</div>
														</c:if>
													</div>
													<div class="portlet-body">
														<div class="table-responsive">
															<table class="table table-hover table-bordered table-striped order-items">
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
																	 入库数量
																</th>
																<th>
																	 备注
																</th>
																<th>
																	操作
																</th>
															</tr>
															</thead>
															<tbody>
															<!-- Generated by stock-in-view-events.js -->
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
													<div class="row static-info align-reverse approval-buttons">
														<div class="col-md-8">
														</div>
															<c:choose>
																<c:when test="${requisitionDetails.status_code != 'new' && requisitionDetails.status_code != 'reopen' && requisitionDetails.status_code != 'submit-rejected'}">
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
										<div class="table-toolbar">
										<div class="row">
										<div class="col-md-6">
										<c:if test="${readOnlyFlag == false}">
											<div class="actions">
												<a href="#order-pick" data-toggle="modal" class="btn green order-pick">
													<i class="fa fa-plus"></i> 选择关联采购订单
												</a>
											</div>
										</c:if>
										</div>
										</div>
										</div>
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_related_orders">
											<thead>
											<tr role="row" class="heading">
												<th width="15%">
													 订单系统编号
												</th>
												<th width="10%">
													 订单人工编号
												</th>
												<th width="10%">
													 订单类型
												</th>
												<th width="10%">
													 订单状态
												</th>
												<th width="10%">
													 创建时间
												</th>
												<th width="10%">
													 经手人
												</th>
											</tr>
											</thead>
											<tbody class="table-related-orders">
	                		<tr class="odd gradeX" id="${related_order.order_id}">
	                    		<td><a href="<%=path%>/order/po_details.do?id=${related_order.order_id}">
	                    		${related_order.order_number}</a></td>
	                    		<td>
	                    			${related_order.order_manual_number}        		
								</td>
	                    		<td>
	                    			${related_order.order_type} 	
	                    		</td>
	                    		<td>
	                    			${related_order.status_value} 	
	                    		</td>
	                    		<td>
	                    		   	${related_order.created_date}      								
	                    		</td>
	                    		<td>
	                    			${related_order.owner_name}
	                    		</td>
	                  		</tr>
											</tbody>
											</table>
										</div>
									</div>
									<!-- Start approval status tab -->
									<div class="tab-pane" id="tab_5">
										<div class="portlet">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-shopping-cart"></i>当前审批状态
											</div>
										</div>
										<div class="portlet-body">
										<div class="table-container">
											<!-- BEGIN APPROVAL STATUS CONTENT-->
											<div class="row">
												<div class="col-md-12" id="approvalStatusDiv">
													
												</div>
											</div>
											<!-- END APPROVAL STATUS CONTENT-->
										</div>
									</div>
									</div>
									</div>
									<!-- End approval status tab -->
									<!-- Start approval history tab -->
									<div class="tab-pane" id="tab_6">
										<div class="portlet">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-shopping-cart"></i>所有审批历史记录
											</div>
											<div class="actions">
												<button class="btn btn-sm green approval-history-query-assistant"><i class="fa fa-search"></i> 搜索</button>
											<button class="btn btn-sm yellow approval-history-query-cancel" style="display: none;"><i class="fa fa-search"></i> 取消搜索</button>
											</div>
										</div>
										<div class="portlet-body">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_approval_history">
											<thead>
											<tr role="row" class="heading">
												<th width="25%">
													 更新时间
												</th>
												<th width="55%">
													 更新描述
												</th>
												<th width="10%">
													 更新人
												</th>
												<th width="10%">
													 操作
												</th>
											</tr>
											<tr role="row" class="approval-history-filter" style="display: none;">
												<td>
													<div class="input-group date datetime-picker margin-bottom-5" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_from" placeholder="从">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date datetime-picker" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_to" placeholder="到">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_history_desc" placeholder="请输入描述"/>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_created_by_username" placeholder="请输入更新人名字"/>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 开始搜索</button>
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
									<!-- End approval history tab -->
									<!-- Start history tab -->
									<div class="tab-pane" id="tab_7">
										<div class="portlet">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-shopping-cart"></i>所有更新历史记录
											</div>
											<div class="actions">
												<button class="btn btn-sm green history-query-assistant"><i class="fa fa-search"></i> 搜索</button>
											<button class="btn btn-sm yellow history-query-cancel" style="display: none;"><i class="fa fa-search"></i> 取消搜索</button>
											</div>
										</div>
										<div class="portlet-body">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_history">
											<thead>
											<tr role="row" class="heading">
												<th width="25%">
													 更新时间
												</th>
												<th width="55%">
													 更新描述
												</th>
												<th width="10%">
													 更新人
												</th>
												<th width="10%">
													 操作
												</th>
											</tr>
											<tr role="row" class="history-filter" style="display: none;">
												<td>
													<div class="input-group date datetime-picker margin-bottom-5" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_from" placeholder="从">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
													<div class="input-group date datetime-picker" data-date-format="dd/mm/yyyy hh:ii">
														<input type="text" class="form-control form-filter input-sm" readonly name="order_history_date_to" placeholder="到">
														<span class="input-group-btn">
														<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
														</span>
													</div>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_history_desc" placeholder="请输入描述"/>
												</td>
												<td>
													<input type="text" class="form-control form-filter input-sm" name="order_created_by_username" placeholder="请输入更新人名字"/>
												</td>
												<td>
													<div class="margin-bottom-5">
														<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 开始搜索</button>
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
									<!-- End: history tab -->
								</div>
							</div>
						</div>
					</div>
					<!-- End: life time stats -->
				</div>
			</div>
			<!-- END PAGE CONTENT-->