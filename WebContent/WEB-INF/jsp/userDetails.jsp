<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String url = basePath + "";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 
/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/11/17
 * Description:  User details management page.
 */
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>用户管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link
	href="../assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link
	href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/bootstrap-datepicker/css/datepicker3.css">
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="../assets/admin/pages/css/tasks.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="../assets/global/css/components.css" id="style_components"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<link
	href="../assets/global/plugins/fancybox/source/jquery.fancybox.css"
	rel="stylesheet" type="text/css">
<!-- END THEME STYLES -->
<!-- BEGIN APPLICATION STYLES -->
<link href="../css/autods.css" rel="stylesheet" type="text/css" />
<!-- END APPLICATION STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body
	class="page-header-fixed page-quick-sidebar-over-content page-style-square">
	<!-- BEGIN HEADER -->
	<jsp:include page="header.jsp" />
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<jsp:include page="sidebar.jsp" />
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<h3 class="page-title">
					用户管理 <small>用户信息设置</small>
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<%=path%>/auth/dashboard.do">首页</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a href="<%=path%>/user/list.do">用户列表</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a
							href="<%=path%>/user/details.do?userId=${userDetails.userId}">用户详细信息</a>
						</li>
					</ul>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN Table ROWS -->
				<div class="row">
					<div class="col-md-12">
						<div class="tabbable tabbable-custom boxless tabbable-reversed">
							<div class="tab-content">
								<!-- BEGIN TAB0 -->
								<div class="portlet-body">
									<div class="tabbable">
										<ul class="nav nav-tabs">
											<li class="active"><a href="#tab_general"
												data-toggle="tab"> 基本信息 </a></li>
											<li><a href="#tab_image" data-toggle="tab"> 更多职位 </a></li>
										</ul>
										<div class="tab-content no-space">
											<div class="tab-pane active" id="tab_general">
												<div class="form-body">
													<div class="portlet-body form">
														<!-- BEGIN FORM-->
														<form action="" id="update_form" method="post">
															<div class="form-body">
																<!--/row-->
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="control-label">用户名：</label> <input
																				type="text" class="form-control" readonly="readonly"
																				placeholder="请输入用户名…" name="username" id="username"
																				value="${userDetails.userName}">
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label>电子邮箱：</label>
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-envelope"></i>
																				</span> <input type="text" class="form-control"
																					name="emailaddress" id="emailaddress"
																					value="${userDetails.email}" placeholder="电子邮箱…">
																				<input type="text" name="userId" value="${userId }"
																					style="display: none;">
																			</div>
																		</div>
																	</div>
																</div>
																<!--/row-->
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="control-label">姓氏：</label> <input
																				type="text" class="form-control"
																				placeholder="请输入姓氏…" name="lastname" id="lastname"
																				value="${userDetails.lastName}">
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="control-label">名字：</label> <input
																				type="text" class="form-control"
																				placeholder="请输入名字…" name="firstname" id="firstname"
																				value="${userDetails.firstName}">
																		</div>
																	</div>
																</div>

																<!--/row-->
																<div class="row">
																	<div class="col-md-6 ">
																		<div class="form-group">
																			<label for="exampleInputPassword1">新密码</label>
																			<div class="input-group">
																				<input type="password" class="form-control"
																					name="password" id="newPassword"
																					placeholder="请输入新密码…" readonly> <span
																					class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<div class="theme-option">
																				<label class="control-label">地址：</label> <input
																					type="text" class="form-control"
																					placeholder="请输入地址…" name="address" id="address"
																					value="${userDetails.address}">
																			</div>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label for="exampleInputPassword1">确认新密码</label>
																			<div class="input-group">
																				<input type="password" class="form-control"
																					id="confirmPassword" placeholder="请确认新密码…" readonly
																					onblur="passwordVerify()"> <span
																					class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span>
																			</div>
																			<span id="passwordVerify"></span>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label>用户状态：</label>
																			<div class="theme-option">
																				<select class="form-control" id="active">
																					<c:if test="${userDetails.active == true }">
																						<option value="1" selected>已激活</option>
																						<option value="0">未激活</option>
																					</c:if>
																					<c:if test="${userDetails.active == false }">
																						<option value="1">已激活</option>
																						<option value="0" selected>未激活</option>
																					</c:if>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
																<!--/row-->
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="checkbox-inline"> <input
																				type="checkbox" id="changePassword"
																				value="changePassword" onclick="ifReadOnly()">是否需要修改密码
																			</label>
																		</div>
																		<div style="display: none;">
																			<input type="text" name="userId" id="userId"
																				value="${userDetails.userId }">
																			<input type="text" name="positionId" id="positionId"
																				value="${userDetails.position.positionId }">
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="control-label">主职位：</label> <select
																				class="form-control" id="position">
																				<c:choose>
																					<c:when
																						test="${userDetails.position.positionName == ''}">
																						<option value="" selected>无</option>
																					</c:when>
																					<c:otherwise>
																						<option value="">无</option>
																					</c:otherwise>
																				</c:choose>
																				<c:forEach var="positions" items="${positions}">
																					<c:choose>
																						<c:when
																							test="${positions.positionName == userDetails.position.positionName}">
																							<option value="${positions.positionId}" selected>${positions.positionName}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${positions.positionId}">${positions.positionName}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<!--/row-->
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<p class="form-control-static">最近一次登录是在
																				${userDetails.lastLogin}</p>
																		</div>
																	</div>
																	<div class="col-md-6"></div>
																</div>
																<div class="row"></div>
															</div>
															<div class="form-actions right">
																<button type="button" onclick="ajaxPostSubmit()"
																	class="btn blue" id="updateUser-confirm">
																	<i class="fa fa-check"></i>确认
																</button>
																<button type="button" onclick="goBack()"
																	class="btn default cancel">取消</button>
															</div>
														</form>
														<!-- END FORM-->
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab_image">
											<div>
												<!--/row-->
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<select
																				class="form-control" id="newPosition">
																				<option ></option>
																				<c:forEach var="positions" items="${positions}">
																					<option value="${positions.positionId}">${positions.positionName}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<a id="tab_images_uploader_uploadfiles" style="float: left;" class="btn green" onclick="newPosition()">
																			<i class="fa fa-plus"></i> 添加职位</a>
																		</div>
																	</div>
																</div>
											</div>
											<table align="center"
															class="table table-striped table-bordered table-hover"
															id="sample_1">
															<thead>
																<tr>
																	<th>职位</th>
																	<th>创建时间</th>
																	<th>创建人</th>
																	<th>操作</th>
																	<th></th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="pPositions" items="${pPositions}">
																	<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
																	<tr class="odd gradeX">
																		<td>${pPositions.osa_position_name}</td>
																		<td>${pPositions.osa_created }</td>
																		<td>${pPositions.osa_username }</td>
																<td>
																	<c:choose>
																		<c:when
																			test="${pPositions.osa_position_name!=userDetails.position.positionName}">
																			<a
																				onclick="deletePosition(${pPositions.osa_post_per_id},p=2)">删除</a>
																							/<a onclick="updatePrimaryPosition(${pPositions.osa_position_id})">设为主职位</a>
																		</c:when>
																		<c:otherwise>
																			<a
																				onclick="deletePosition(${pPositions.osa_post_per_id},p=1)">删除</a>
																		</c:otherwise>
																	</c:choose>
																</td>
																<td>
																			<c:if test="${pPositions.osa_position_name==userDetails.position.positionName}">主职位</c:if>
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
											</div>
										</div>
									</div>
								</div>


							</div>
						</div>
						<!-- END TAB0 -->
					</div>
				</div>
			</div>
		</div>
		<!-- END Table ROWS -->
		<!--  div class="clearfix" -->
		<!--  div-->

		<!-- END CONTENT -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<jsp:include page="footer.jsp" />
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="../assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script
		src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/moment.min.js"></script>
	<script type="text/javascript"
		src="../assets/admin/pages/scripts/components-pickers.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript"
		src="../assets/global/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script type="text/javascript"
		src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/quick-sidebar.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/demo.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- begin user update -->
	<script src="../scripts/user-page.js" type="text/javascript"></script>
	<!-- end  -->
	<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
	<!-- 用户详情页面js -->
	<script src="../scripts/jquery/jQuery.md5.js" type="text/javascript"></script>
	<script src="../scripts/users/userDetails.js" type="text/javascript"></script>

	<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   navigationBar.activeUserMenu();
});
</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>