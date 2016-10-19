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
									<i class="fa fa-shopping-cart"></i>${product.name}
								</div>
								<div class="actions btn-set">
									<button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-angle-left"></i> 返回</button>
									<button type="button" class="btn default reset"><i class="fa fa-reply"></i> 重置</button>
									<c:if test="${readOnlyFlag == false}">
									<button type="button" class="btn green" id="update-confirm"><i class="fa fa-check"></i> 保存</button>
									</c:if>
									<c:if test="${product.status == 'Published'}">
									<button type="button" class="btn blue" id="reopen-product"><i class="fa fa-check"></i> 重开</button>
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
											<a href="#tab_images" data-toggle="tab">
											产品图片 </a>
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
													<input type="text" name="product[id]" style="display :none;" value="${product.id}">
													<label class="col-md-2 control-label">产品名称: <span class="required">
													* </span>
													</label>
													<div class="col-md-10">
														<input type="text" class="form-control" name="product[name]" id="proName" placeholder="请输入名称……" value="${product.name}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">产品编号: <span class="required">
													* </span>
													</label>
													<div class="col-md-10">
														<input type="text" class="form-control" name="product[number]" placeholder="请输入编号……" value="${product.number}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">产品型号: <span class="required">
													* </span>
													</label>
													<div class="col-md-10">
														<textarea class="form-control" name="product[model]" maxlength="255" <c:if test="${readOnlyFlag == true}">readonly</c:if>>${product.model}</textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">产品规格: 
													</label>
													<div class="col-md-10">
														<textarea class="form-control" name="product[spec]" <c:if test="${readOnlyFlag == true}">readonly</c:if>>${product.spec}</textarea>
														<span class="help-block">
														请尽量描写详细规格描述 </span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">产品描述: 
													</label>
													<div class="col-md-10">
														<textarea class="form-control" name="product[description]" maxlength="255" <c:if test="${readOnlyFlag == true}">readonly</c:if>>${product.desc}</textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">产品类别: <span class="required">
													* </span>
													<span class="category-id" style="display :none;">${product.categoryId}</span>
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
														请选择一个产品类别 </span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">有效日期: 
													</label>
													<div class="col-md-10">
														<div class="input-group input-large date-picker input-daterange" data-date="10/11/2012" data-date-format="yyyy-mm-dd">
															<input type="text" class="form-control" name="product[available_from]" value="${product.validstart}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
															<span class="input-group-addon">
															到 </span>
															<input type="text" class="form-control" name="product[available_to]" value="${product.validend}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
														</div>
														<span class="help-block">
														有效期限. </span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">库存数量: 
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="product[stock]" placeholder="" value="${product.stock}" readonly>
													</div>
													<label class="col-md-2 control-label">SKU: 
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="product[sku]" placeholder="" value="${product.sku}" <c:if test="${readOnlyFlag == true}">readonly</c:if>>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">待入库数量: 
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="product[stock_in]" placeholder="" value="${product.ordered_stock_in}" readonly>
													</div>
													<label class="col-md-2 control-label">待出库数量: 
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="product[stock_out]" placeholder="" value="${product.ordered_stock_out}" readonly>
													</div>
												</div>
<!-- 												<div class="form-group"> -->
<!-- 													<label class="col-md-2 control-label">价格:  -->
<!-- 													</label> -->
<!-- 													<div class="col-md-10"> -->
<%-- 														<input type="text" class="form-control" name="product[price]" placeholder="" value="${product.price}" readonly> --%>
<!-- 													</div> -->
<!-- 												</div> -->
												<div class="form-group">
													<label class="col-md-2 control-label">状态: 
													</label>
													<div class="col-md-4">
														<select class="table-group-action-input form-control input-medium" name="product[status]" disabled>
															<option <c:if test='${product.status == "Published"}'>selected="selected"</c:if> value="Published">已发布</option>
															<option <c:if test='${product.status == "Not Published"}'>selected="selected"</c:if> value="Not Published">未发布</option>
															<option <c:if test='${product.status == "Deleted"}'>selected="selected"</c:if> value="Deleted">已删除</option>
															<option <c:if test='${product.status == "Pending"}'>selected="selected"</c:if> value="Pending">待审核</option>
															<option <c:if test='${product.status == "Rejected"}'>selected="selected"</c:if> value="Rejected">已拒绝</option>
														</select>
<%-- 														<input type="text" class="form-control" name="product[status]" value="${product.status}" readonly> --%>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-2">
													</div>
													<div class="col-md-6">
															<p class="form-control-static">
																此记录是由${product.createdBy}在 ${product.created}创建</p>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-2">
													</div>
													<div class="col-md-6">
															<p class="form-control-static">
																此记录最近一次修改是由${product.updateBy}在 ${product.update}更新</p>
													</div>
												</div>
												<div class="form-group approval-buttons">
														<div class="col-md-2">
														</div>
														<div class="col-md-2">
															<c:choose>
																<c:when test="${product.status == 'Not Published'}">
																	<a href="#" class="btn btn-lg green m-icon-big submit-prod">
																		保存并提交 <i class="m-icon-big-swapup m-icon-white"></i>
																	</a>
																</c:when>
																<c:when test="${product.status == 'Rejected'}">
																	<a href="#" class="btn btn-lg green m-icon-big submit-prod">
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
										<div class="tab-pane" id="tab_images">
											<div id="tab_images_uploader_container" class="text-align-reverse margin-bottom-10">
												<c:if test="${readOnlyFlag == false}">
												<a id="tab_images_uploader_pickfiles" href="javascript:;" class="btn yellow">
												<i class="fa fa-plus"></i> 选择文件 </a>
												<a id="tab_images_uploader_uploadfiles" href="javascript:;" class="btn green">
												<i class="fa fa-share"></i> 上传文件 </a>
												</c:if>
											</div>
											<div class="row">
												<div id="tab_images_uploader_filelist" class="col-md-6 col-sm-12">
												</div>
											</div>
											<div class="row" style="display: none;">
												<span class="remove_image_id"></span>
												<span class="remove_image_filename"></span>
												<span class="remove_image_filepath"></span>
											</div>
											<table class="table table-bordered table-hover product-images">
											<thead>
											<tr role="row" class="heading">
												<th width="8%">
													 图片
												</th>
												<th width="25%">
													 名称
												</th>
												<th width="8%">
													 大小
												</th>
												<th width="10%">
													 原图
												</th>
												<th width="10%">
													 小图
												</th>
												<th width="10%">
													 缩略图
												</th>
												<th width="10%">
												</th>
											</tr>
											</thead>
											<tbody>
											
											</tbody>
											</table>
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