<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/11
 * Description:  product list management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 产品/服务管理</title>
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
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link id="style_color" href="../assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/jstree/dist/themes/default/style.min.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css" />
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css" />
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css" />
<link href="../css/account/account-ui-prod-tree.css" rel="stylesheet" type="text/css" />
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
			<!-- BEGIN DELETE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除</h4>
						</div>
						<div class="modal-body">
							 确认要删除该类型？
						</div>
						<div class="modal-footer">
							<button type="button" id="del-confirm" class="btn blue" data-dismiss="modal" onclick="delCategory()">删除</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END DELETE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN NEW CATEGORY CONFIRM MODAL FORM -->
			<div class="modal fade bs-modal-lg" id="new-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog bs-modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建产品/服务类别</h4>
						</div>
						<div class="modal-body">
						<div class="row">
							<div class="col-md-6">
								<label class="control-label">类别名称：</label><span class="required">* </span>
								<input type="text" class="form-control" placeholder="请输入类别名…" id="category-name" name="category-name">
								<label>描述</label>
								<textarea class="form-control" rows="2"	placeholder="输入描述…" id="description"></textarea>
							</div>
							<div class="col-md-6">
								<div class="theme-option">
							<label class="control-label">父类别：</label>
							<select class="form-control categories">
								<option value="">无</option>
<%-- 								<c:forEach var="category" items="${categoryList}"> --%>
<%-- 	                 				<option value="${category.id}">${category.name}</option> --%>
<%-- 								</c:forEach> --%>
							</select>
							</div>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="new-cat-confirm" class="btn blue">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END NEW CATEGORY CONFIRM MODAL FORM -->
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="new-product" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">新建产品/服务</h4>
						</div>
						<div class="modal-body">
						<div class="row" id="message" style="display: none;"><span class="label label-sm label-danger" id="messages1" style="margin-left: 30%;"></span></div>
						<div class="row">
							<div class="col-md-6">
								<label class="control-label">产品/服务编号：</label>
								<input type="text" class="form-control" placeholder="请输入产品编号…" id="prod-number">
								<label class="control-label">产品/服务名称：</label><span class="required">* </span>
								<input type="text" class="form-control" placeholder="请输入产品名称…" id="prod-name">
								<label>描述</label>
								<textarea class="form-control" rows="2"	placeholder="输入描述…" id="prod_desc"></textarea>
							</div>
							<div class="col-md-6">
								<div class="theme-option">
							<label class="control-label">产品/服务类别：</label>
							<select class="form-control categories">
								<!-- Generated by JS -->
							</select>
							<label class="control-label">产品型号：</label><span class="required">* </span>
							<input type="text" class="form-control" placeholder="请输入产品型号…" id="prod-model">
							<label class="control-label">SKU：</label>
							<input type="text" class="form-control" placeholder="请输入SKU…" id="prod-sku">
							</div>
							</div>
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue" id="new-prod-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
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
			产品/服务目录管理 <small>产品/服务列表</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/prodadmin/listTree.do">产品/服务目录管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">产品/服务列表</a>
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
			<div>
						<div class="content-b">
							<!-- BEGIN TREE VIEW -->
							<div class="m-tree">
								<div class="form-group">
									<div class="portlet box yellow" id="m-tree">
										<div class="portlet-title">
											<div class="caption">产品/服务类别</div>
											<div class="tools">
												<a href="#new-confirm" class="new-category" data-toggle="modal" 
													><i class="fa fa-plus"
													style="color: white;"></i></a>
													<a onclick="updateCategory()"><i
													class="fa fa-edit" style="color: white;"></i></a> 
<!-- 													<a href="#delete-confirm" data-toggle="modal"><i -->
<!-- 													class="fa fa-times" style="color: white;"></i></a> -->
											</div>
										</div>
										<div class="portlet-body">
											<div id="tree_1" class="tree-demo">
												<c:forEach var="a0" items="${pList0}" varStatus="s0">
													<ul>
														<li id=${a0.id } data-jstree='{ "opened" : true }'><a
															onclick="allProd()" id="lia${a0.id }">${a0.name }</a> <c:forEach
																var="a1" items="${pList1}" varStatus="s1">
																<ul>
																	<c:if test="${a1.par_id == a0.id }">
																		<li id=${a1.id } data-jstree='{ "opened" : true }'><a
																			onclick="detail2(${a1.id })"  id="lia${a1.id }">${a1.name }</a> <c:forEach
																			var="a2" items="${pList2}" varStatus="s2">
																				<ul>
																					<c:if test="${a2.par_id == a1.id }">
																						<li id=${a2.id } data-jstree='{ "opened" : true }'><a
																							onclick="detail2(${a2.id })"  id="lia${a2.id }">${a2.name }</a>
																							<c:forEach var="a3" items="${pList3}"
																								varStatus="s4">
																								<ul>
																									<c:if test="${a3.par_id == a2.id }">
																										<li id=${a3.id } data-jstree='{ "opened" : true }'><a
																											onclick="detail2(${a3.id })" id="lia${a3.id }">${a3.name }</a>
																											<c:forEach var="a4" items="${pList4}"
																												varStatus="s5">
																												<ul>
																													<c:if test="${a4.par_id == a3.id }">
																														<li id=${a4.id }  data-jstree='{ "opened" : true }'><a
																															onclick="detail2(${a4.id })" id="lia${a4.id }">${a4.name }</a>
																															<c:forEach var="a5" items="${pList5}"
																																varStatus="s6">
																																<ul>
																																	<c:if test="${a5.par_id == a4.id }">
																																		<li id=${a5.id }  data-jstree='{ "opened" : true }'><a
																																			onclick="detail2(${a5.id })" id="lia${a5.id }">${a5.name }</a>
																																			<c:forEach var="a6" items="${pList6}"
																																				varStatus="s7">
																																				<ul>
																																					<c:if test="${a6.par_id == a5.id }">
																																						<li id=${a6.id }  data-jstree='{ "opened" : true }'><a
																																							onclick="detail2(${a6.id })" id="lia${a6.id }">${a6.name }</a>
																																							<c:forEach var="a7" items="${pList7}"
																																								varStatus="s8">
																																								<ul>
																																									<c:if test="${a7.par_id == a6.id }">
																																										<li id=${a7.id }  data-jstree='{ "opened" : true }'><a
																																											onclick="detail2(${a7.id })" id="lia${a7.id }">${a7.name }</a>
																																										</li>
																																									</c:if>
																																								</ul>
																																							</c:forEach>
																																						</li>
																																					</c:if>
																																				</ul>
																																			</c:forEach>
																																		</li>
																																	</c:if>
																																</ul>
																															</c:forEach>
																														</li>
																													</c:if>
																												</ul>
																											</c:forEach>
																										</li>
																									</c:if>
																								</ul>
																							</c:forEach></li>
																					</c:if>
																				</ul>
																			</c:forEach></li>
																	</c:if>
																</ul>
															</c:forEach></li>
													</ul>
												</c:forEach>

											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- END TREE VIWE -->
							<div class="m-table">
								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box yellow" id="m-table">
									<div class="portlet-title">
										<div class="caption"></div>
										<div class="tools"
											style="padding-top: 5px; padding-bottom: 0px;">
										</div>
									</div>
									<div class="portlet-body" id="m-table-b">
										<div class="table-scrollable">
											<!-- BEGIN PAGE CONTENT-->
											<div class="row" >
												<div class="col-md-12"style="height: 500px;overflow:auto;overflow-y:scroll;overflow-y:auto;"  >
													<!-- Begin: life time stats -->
													<div class="portlet">
														<div class="portlet-title">
															<div class="caption">
																<i class="fa fa-gift"></i>产品/服务目录
															</div>
															<div class="actions">
																<button class="btn btn-sm green query-assistant"><i class="fa fa-search"></i> 搜索</button>
																<button class="btn btn-sm yellow query-cancel" style="display: none;"><i class="fa fa-search"></i> 取消搜索</button>
																<div class="btn-group">
																	<a class="btn default yellow-stripe dropdown-toggle" href="#" data-toggle="dropdown">
																	<i class="fa fa-share"></i> 工具 <i class="fa fa-angle-down"></i>
																	</a>
																	<ul class="dropdown-menu pull-right">
																		<li>
																			<a href="#new-product" data-toggle="modal">
																			新建产品/服务</a>
																		</li>
<!-- 																		<li> -->
<!-- 																			<a href="#"> -->
<!-- 																			导出到CSV </a> -->
<!-- 																		</li> -->
<!-- 																		<li class="divider"> -->
<!-- 																		</li> -->
<!-- 																		<li> -->
<!-- 																			<a href="#"> -->
<!-- 																			打印发票 </a> -->
<!-- 																		</li> -->
																	</ul>
																</div>
															</div>
														</div>
														<div class="portlet-body">
															<div class="table-container">
<!-- 																<div class="table-actions-wrapper"> -->
<!-- 																	<span> -->
<!-- 																	</span> -->
<!-- 																	<select class="table-group-action-input form-control input-inline input-small input-sm"> -->
<!-- 																		<option value="">请选择...</option> -->
<!-- 																		<option value="publish">发布</option> -->
<!-- 																		<option value="unpublished">取消发布</option> -->
<!-- 																		<option value="delete">删除</option> -->
<!-- 																	</select> -->
<!-- 																	<button class="btn btn-sm yellow table-group-action-submit"><i class="fa fa-check"></i> 提交</button> -->
<!-- 																</div> -->
																<input type="text" style="display: none;" id="checkCatId" value="">
																<table class="table table-striped table-bordered table-hover" id="datatable_products">
																<thead>
																<tr role="row" class="heading">
																	<th width="1%">
																		<input type="checkbox" class="group-checkable">
																	</th>
																	<th width="10%">
																		 产品/服务编号
																	</th>
																	<th width="10%">
																		 产品/服务名称
																	</th>
																	<th width="15%">
																		 产品型号
																	</th>
																	<th width="15%">
																		 类别
																	</th>
<!-- 																	<th width="10%"> -->
<!-- 																		 价格 -->
<!-- 																	</th> -->
																	<th width="10%">
																		库存数量
																	</th>
																	<th width="10%">
																		 最后修改时间
																	</th>
																	<th width="10%">
																		 状态
																	</th>
																	<th width="10%">
																		 操作
																	</th>
																</tr>
																<tr role="row" class="filter" style="display: none;">
																	<td>
																	</td>
																	<td>
																		<input type="text" class="form-control form-filter input-sm" name="product_number">
																	</td>
																	<td>
																		<input type="text" class="form-control form-filter input-sm" name="product_name">
																	</td>
																	<td>
																		<input type="text" class="form-control form-filter input-sm" name="product_model">
																	</td>
																	<td>
																		<select name="product_category" class="form-control form-filter input-sm category-search">
																			<option value="" id="categoryIdValue" >请选择...</option>
																				<!-- Generated by JS -->
																		</select>
																	</td>
<!-- 																	<td> -->
<!-- 																		<div class="margin-bottom-5"> -->
<!-- 																			<input type="text" class="form-control form-filter input-sm" name="product_price_from" placeholder="从"/> -->
<!-- 																		</div> -->
<!-- 																		<input type="text" class="form-control form-filter input-sm" name="product_price_to" placeholder="到"/> -->
<!-- 																	</td> -->
																	<td>
																		<div class="margin-bottom-5">
																			<input type="text" class="form-control form-filter input-sm" name="product_quantity_from" placeholder="从"/>
																		</div>
																		<input type="text" class="form-control form-filter input-sm" name="product_quantity_to" placeholder="到"/>
																	</td>
																	<td>
																		<div class="input-group date date-picker margin-bottom-5" data-date-format="yyyy/mm/dd">
																			<input type="text" class="form-control form-filter input-sm" readonly name="product_created_from" placeholder="从">
																			<span class="input-group-btn">
																			<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
																			</span>
																		</div>
																		<div class="input-group date date-picker" data-date-format="yyyy/mm/dd">
																			<input type="text" class="form-control form-filter input-sm" readonly name="product_created_to" placeholder="到">
																			<span class="input-group-btn">
																			<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
																			</span>
																		</div>
																	</td>
																	<td>
																		<select name="product_status" class="form-control form-filter input-sm">
																				<option value="">请选择...</option>
																				<option value="Published">已发布</option>
																				<option value="Not Published">未发布</option>
																				<option value="Deleted">已删除</option>
																				<option value="Pending">待审核</option>
																				<option value="Rejected">已拒绝</option>
																		</select>
																	</td>
																	<td>
																		<div class="margin-bottom-5">
																			<button class="btn btn-sm yellow filter-submit margin-bottom" id="customButton2"><i class="fa fa-search"></i> 提交搜索</button>
																		</div>
																		<button class="btn btn-sm red filter-cancel" id="filter-cancel2"><i class="fa fa-times"></i> 重置</button>
																	</td>
																</tr>
																</thead>
																<tbody>
																</tbody>
																</table>
															</div>
														</div>
													</div>
													<!-- End: life time stats -->
												</div>
											</div>
											<!-- END PAGE CONTENT-->
										</div>
									</div>
								</div>
								<!-- END BORDERED TABLE PORTLET-->
							</div>
						</div>
					</div>
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
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js"></script>
<script src="../assets/admin/pages/scripts/ecommerce-products.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/products/products-events.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/plugins/jstree/dist/jstree.min.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../assets/admin/pages/scripts/ui-tree.js"></script>
<script src="../scripts/account/account.js" type="text/javascript"></script>
<script src="../scripts/products/tree-events.js" type="text/javascript"></script>
<script src="../scripts/category/category-events.js" type="text/javascript"></script>
<script>
        jQuery(document).ready(function() {    
           	Metronic.init(); // init metronic core components
           	navigationBar.activeProductMenu();
			Layout.init(); // init current layout
			//QuickSidebar.init(); // init quick sidebar
			//Demo.init(); // init demo features
           	EcommerceProducts.init();
           	ProductsEvents.init();
           	ProductsEvents.loadNewProdCategories();
			UITree.init();
			CategoryEvents.init();
			TreeEvents.init();
        });
    </script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>