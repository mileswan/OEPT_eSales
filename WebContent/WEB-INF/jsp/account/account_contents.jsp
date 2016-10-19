<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- BEGIN PAGE CONTENT-->
			<input type="text" class="form-control" name="readOnlyFlag" value="${readOnlyFlag}" style="display: none;">
			<div class="row product-content">
				<div class="col-md-12">
					<form class="form-horizontal form-row-seperated" action="#" id="update_form">
						<div class="portlet">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-shopping-cart"></i>${account.accountName}
								</div>
								<div class="actions btn-set">
									<button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-angle-left"></i> 返回</button>
									<button type="button" class="btn default reset"><i class="fa fa-reply"></i> 重置</button>
									<c:if test="${readOnlyFlag == false}">
									<button type="button" class="btn green" id="update-confirm"><i class="fa fa-check"></i> 保存</button>
									</c:if>
									<c:if test="${account.accountStatus == 'Published'}">
									<button type="button" class="btn blue" id="reopen-account"><i class="fa fa-check"></i> 重开</button>
									</c:if>
<!-- 									<button class="btn green"><i class="fa fa-check-circle"></i> 保存并继续编辑</button> -->
									<div class="btn-group" style="display :none;">
										<a class="btn yellow dropdown-toggle" href="#" data-toggle="dropdown">
										<i class="fa fa-share"></i> More <i class="fa fa-angle-down"></i>
										</a>
										<ul class="dropdown-menu pull-right">
											<li>
												<a href="#">
												Duplicate </a>
											</li>
											<li>
												<a href="#">
												Delete </a>
											</li>
											<li class="divider">
											</li>
											<li>
												<a href="#">
												Print </a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="portlet-body">
								<div class="tabbable">
									<ul class="nav nav-tabs">
										<li class="active">
											<a href="#tab_general" data-toggle="tab">
											基本信息 </a>
										</li>
										<li>
											<a href="#tab_approval_status" data-toggle="tab" class="queryApprovalStatus">
											当前审核状态 </a>
										</li>
										<li>
											<a href="#tab_approval_history" data-toggle="tab">
											所有审核历史</a>
										</li>
									</ul>
									<div class="tab-content no-space">
										<div class="tab-pane active" id="tab_general">
											<div class="form-body">
												<div class="form-group">
													<input type="text" name="account[id]" style="display :none;" value="${account.accountId}">
													<label class="col-md-2 control-label">公司名称: <span class="required">
													* </span>
													</label>
													<div class="col-md-10">
														<input type="text" class="form-control" name="account[name]" placeholder="请输入公司名称……" value="${account.accountName}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">公司编号: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[number]" placeholder="请输入编号……" value="${account.accountNumber}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">公司类型：<span class="required">
													* </span>
													</label>
													<div class="col-md-6">
														<select class="table-group-action-input form-control " name="account[types]" <c:if test="${readOnlyFlag == true}">disabled</c:if>>
															<option <c:if test='${account.accountType == "客户"}'>selected="selected"</c:if> value="客户">客户</option>
															<option <c:if test='${account.accountType == "供应商"}'>selected="selected"</c:if> value="供应商">供应商</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">联系人及电话: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[tel]" placeholder="请输入联系人姓名和电话号码……" value="${account.workphone}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">传真: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[fax]" placeholder="请输入传真号码……" value="${account.fax}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">邮编: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[zipcode]" placeholder="请输入邮政编码……" value="${account.zipcode}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">E-mail: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[email]" placeholder="请输入E-mail……" value="${account.email}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">区域: <span class="required">
													* </span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" style="width: 92%; float: left;" name="account[address]" placeholder="请选择区域……" value="${account.address.allAddress}" readonly>
														<c:if test="${readOnlyFlag != true}">
														<a href="#address-pick" data-toggle="modal" class="btn default address-pick">
															<i class="fa fa-search"></i>
															</a>
														</c:if>
														<input type="text" class="form-control" name="account[addressId]" id="account_addressId" placeholder="请选择区域……" value="${account.address.addressId}" style="display: none;">
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">详细地址: <span class="required">
													* </span>
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[addressDetail]" placeholder="请输入详细地址……" value="${account.addrName}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">其他地址1: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[otherAddress1]" placeholder="请输入详细地址……" value="${account.otherAddress1}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">其他地址2: 
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" name="account[otherAddress2]" placeholder="请输入详细地址……" value="${account.otherAddress2}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">公司描述: 
													</label>
													<div class="col-md-10">
														<textarea id="description" class="form-control"  name="account[description]" value="" maxlength="255" <c:if test="${readOnlyFlag == true}">readonly</c:if>>${account.accountDesc}</textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">公司类别: <span class="required">
													* </span>
													<span class="category-id" style="display :none;">${account.aCatId}</span>
													</label>
													<div class="col-md-10">
														<div class="form-control height-auto">
															<div class="scroller" style="height:275px;" data-always-visible="1">
																<ul class="list-unstyled categories">
																<!-- Generated by JS -->
																</ul>
															</div>
														</div>
														<span class="help-block">
														请选择一个公司类别 </span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">备注: 
													</label>
													<div class="col-md-10">
														<textarea id="comment" class="form-control" name="account[spec]" <c:if test="${readOnlyFlag == true}">readonly</c:if>>${account.accountComm}</textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">状态: <span class="required">
													* </span>
													</label>
													<div class="col-md-6">
														<select class="table-group-action-input form-control input-medium" name="account[status]" disabled>
															<option <c:if test='${account.accountStatus == "Published"}'>selected="selected"</c:if> value="Published">已发布</option>
															<option <c:if test='${account.accountStatus == "Not Published"}'>selected="selected"</c:if> value="Not Published">未发布</option>
															<option <c:if test='${account.accountStatus == "Deleted"}'>selected="selected"</c:if> value="Deleted">已删除</option>
															<option <c:if test='${account.accountStatus == "Pending"}'>selected="selected"</c:if> value="Pending">待审核</option>
															<option <c:if test='${account.accountStatus == "Rejected"}'>selected="selected"</c:if> value="Rejected">已拒绝</option>
														</select>
<%-- 														<input type="text" class="form-control" name="product[status]" value="${product.status}" readonly> --%>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-2">
													</div>
													<div class="col-md-6">
															<p class="form-control-static">
																此记录是由${account.createdBy}在 ${account.created}创建</p>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-2">
													</div>
													<div class="col-md-6">
															<p class="form-control-static">
																此记录最近一次修改是由${account.updateBy}在 ${account.update}更新</p>
													</div>
												</div>
												<div class="form-group approval-buttons">
														<div class="col-md-2">
														</div>
														<div class="col-md-2">
															<c:choose>
																<c:when test="${account.accountStatus == 'Not Published'}">
																	<a href="#" class="btn btn-lg green m-icon-big submit-account" id="saveCommit">
																		保存并提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:when>
																<c:when test="${account.accountStatus == 'Rejected'}">
																	<a href="#" class="btn btn-lg green m-icon-big submit-account">
																		重新提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:when>
																<c:otherwise>
																	<a class="btn btn-lg dark m-icon-big">
																		提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:otherwise>
															</c:choose>
														</div>
<!-- 													<div class="col-md-2"></div> -->
<!-- 						<div class="col-md-2"> -->
<!-- 							<a class="btn btn-lg green m-icon-big approve-order"> -->
<!-- 								审核通过 <i class="m-icon-big-swapup m-icon-white"></i></a> -->
<!-- 						</div> -->
<!-- 						<div> -->
<!-- 						<a href="#" class="btn btn-lg red m-icon-big reject-order"> -->
<!-- 						拒绝<i class="m-icon-big-swapdown m-icon-white"></i> -->
<!-- 						</a> -->
<!-- 						</div> -->
												</div>
											</div>
										</div>
									<!-- Start approval status tab -->
									<div class="tab-pane" id="tab_approval_status">
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
									<div class="tab-pane" id="tab_approval_history">
										<div class="portlet">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-shopping-cart"></i>所有审核历史记录
											</div>
											<div class="actions">
												<button type="button" class="btn btn-sm green query-assistant"><i class="fa fa-search"></i> 搜索</button>
												<button type="button" class="btn btn-sm yellow query-cancel" style="display: none;"><i class="fa fa-search"></i> 取消搜索</button>
											</div>
										</div>
										<div class="portlet-body">
										<div class="table-container">
											<table class="table table-striped table-bordered table-hover" id="datatable_approval_history">
											<thead>
											<tr role="row" class="heading">
												<th width="25%">
													 审核时间
												</th>
												<th width="55%">
													 审核描述
												</th>
												<th width="10%">
													 审核人
												</th>
												<th width="10%">
													 操作
												</th>
											</tr>
											<tr role="row" class="filter" style="display: none;">
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
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- END PAGE CONTENT-->