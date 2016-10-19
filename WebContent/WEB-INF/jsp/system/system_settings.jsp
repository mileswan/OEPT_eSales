<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/09/09
 * Description:  System settings management page.
 */
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>系统参数设置</title>
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
<link href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker3.css">
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
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			系统设置管理 <small>系统参数设置</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/auth/dashboard.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/system/settings.do">系统设置管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/system/settings.do">系统参数设置</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN Table ROWS -->
			<div class="row">
				<div class="col-md-12">
				<div class="tabbable tabbable-custom boxless tabbable-reversed">
						<ul class="nav nav-tabs">
<!-- 							<li style="display: none;"> -->
<!-- 								<a href="#tab_0" data-toggle="tab"> -->
<!-- 								连接设置 </a> -->
<!-- 							</li> -->
							<li class="active">
								<a href="#tab_1" data-toggle="tab">
								邮件服务器设置 </a>
							</li>
							<li>
								<a href="#tab_2" data-toggle="tab">
								系统信息 </a>
							</li>
							<li>
								<a href="#tab_3" data-toggle="tab">
								系统默认参数设置 </a>
							</li>
						</ul>
						<div class="tab-content">
								<div class="alert alert-danger display-hide"
									style="display: none;">
									<button class="close" data-close="alert"></button>
									<span>You have some form errors. Please check below.</span>
								</div>
								<div class="alert alert-success display-hide"
									style="display: none;">
									<button class="close" data-close="alert"></button>
									<span>成功修改!</span>
								</div>
								<!-- BEGIN TAB0 -->
<!-- 							<div class="tab-pane active" id="tab_0"> -->
<%-- 							<div class="display-hide" style="display: none;" id="playerId">${playerDetail.id}</div> --%>
<!-- 								<div class="portlet box blue"> -->
<!-- 									<div class="portlet-title"> -->
<!-- 										<div class="tools"> -->
<!-- 											<a href="" class="reload"> </a> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="portlet-body form"> -->
<!-- 										BEGIN FORM -->
<!-- 										<form action="#" class="horizontal-form"> -->
<!-- 											<div class="form-body"> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">连接主机地址：</label> -->
<!-- 															<input type="text" class="form-control" placeholder="请输入地址…" name="playerName" id="playerName" value="Server_HOST">													 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">最大线程数：</label> -->
<!-- 															<input type="text" class="form-control" placeholder="请输入地址…" name="playerName" id="playerName" value="5">													 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">端口：</label> -->
<!-- 															<input type="text" class="form-control" placeholder="请输入端口号…" name="playerName" id="playerName" value="8080">													 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">连接端口：</label> -->
<!-- 															<input type="text" class="form-control" placeholder="请输入端口号…" name="playerName" id="playerName" value="8080">													 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">虚拟目录：</label> -->
<!-- 															<input type="text" class="form-control" placeholder="请输入虚拟目录…" name="playerName" id="playerName" value="/OEPT_eSales">													 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">是否启用HTTPS：</label> -->
<!-- 															<div class="inlineEdit"> -->
<!-- 																<span class="button-group enabled"> -->
<%-- 																	<button data-value="true" class="${enable_true}">是</button> --%>
<!-- 																	<button data-value="false" -->
<%-- 																		class="${enable_false}">否</button> --%>
<!-- 																</span> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="form-actions right"> -->
<!-- 												<button type="button" class="btn blue" id="updatePlayer-confirm"> -->
<!-- 													<i class="fa fa-check"></i>确认 -->
<!-- 												</button> -->
<!-- 												<button type="button" class="btn default cancel">取消</button> -->
<!-- 											</div> -->
<!-- 										</form> -->
<!-- 										END FORM -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- END TAB0 -->
							<!-- BEGIN TAB1 -->
							<div class="tab-pane active" id="tab_1">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="tools">
											<a href="" class="reload"> </a>
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="horizontal-form email-settings-form">
											<div class="form-body">
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">邮件服务器是否启用：</label>
															<input type="checkbox" name="mail_available" <c:if test="${allSystemPrefMap.mail_available=='on'}"> checked</c:if>>
														</div>
													</div>
												</div>
												<div class="all-email-settings">
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">SMTP服务器地址：</label>
															<input type="text" class="form-control" placeholder="请输入服务器地址…" name="mail_host" value="${allSystemPrefMap.mail_host}">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">邮件协议：</label>
															<input type="text" class="form-control" placeholder="请输入邮件协议…" name="mail_transport_protocol" value="${allSystemPrefMap.mail_transport_protocol}">													
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">邮箱服务器是否需要验证：</label>
															<input type="checkbox" name="mail_smtp_auth" <c:if test="${allSystemPrefMap.mail_smtp_auth=='on'}"> checked</c:if>>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">SMTP用户名：</label>
															<input type="text" class="form-control" placeholder="请输入用户名…" name="mail_username" value="${allSystemPrefMap.mail_username}">													
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">SMTP密码：</label>
															<input type="text" class="form-control" placeholder="请输入密码…" name="mail_password" value="${allSystemPrefMap.mail_password}">													
														</div>
													</div>
												</div>
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="checkbox-inline"> -->
<!-- 															<input type="checkbox" id="changePassword" value="changePassword">修改密码</label> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3 "> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label for="exampleInputPassword1">新密码</label> -->
<!-- 															<div class="input-group"> -->
<!-- 																<input type="password" class="form-control" -->
<!-- 																	id="newPassword" placeholder="请输入新密码…" readonly> -->
<!-- 																<span class="input-group-addon"> <i -->
<!-- 																	class="fa fa-user"></i> -->
<!-- 																</span> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												/row -->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3 "> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label for="exampleInputPassword1">确认新密码</label> -->
<!-- 															<div class="input-group"> -->
<!-- 																<input type="password" class="form-control" -->
<!-- 																	id="newPassword" placeholder="请确认新密码…" readonly> -->
<!-- 																<span class="input-group-addon"> <i -->
<!-- 																	class="fa fa-user"></i> -->
<!-- 																</span> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
											</div>
											</div>
											<div class="form-actions right">
												<button type="button" class="btn blue" id="updateEmail-confirm">
													<i class="fa fa-check"></i>确认
												</button>
												<button type="button" class="btn default cancel">取消</button>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
							</div>
							<!-- END TAB1 -->
							<!-- BEGIN TAB2 -->
							<div class="tab-pane" id="tab_2">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="tools">
											<a href="" class="reload"> </a>
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="horizontal-form">
											<div class="form-body">
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																操作系统：Windows Server 2008 R2 6.1 (amd64)</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																数据库名：${allDataSourceMap.DB_Name}</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																Java VM：1.8.0_45 (Oracle Corporation)</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																数据库端口：3306</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																Web服务器：Apache Tomcat/7.0.53</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																数据库类型：MySQL</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																应用程序目录：C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/OEPT_eSales</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																最大连接池大小：60</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																网络服务器目录：C:/Program Files/Apache Software Foundation/Tomcat 7.0</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																最小连接池大小：5</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																服务器时间：2015-10-09 11:55:34</p>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<p class="form-control-static">
																连接池超时：360</p>
														</div>
													</div>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
							</div>
							<!-- END TAB2 -->
							<!-- BEGIN TAB3 -->
							<div class="tab-pane" id="tab_3">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="tools">
											<a href="" class="reload"> </a>
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="horizontal-form default-settings-form">
											<div class="form-body">
												<!--/row-->
<!-- 												<div class="row"> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<div class="form-group"> -->
<!-- 															<label class="control-label">邮件服务器是否启用：</label> -->
<%-- 															<input type="checkbox" name="mail_available" <c:if test="${allSystemPrefMap.mail_available=='on'}"> checked</c:if>> --%>
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">税率：</label>
															<div class="input-inline input-medium">
																<input type="text" value="${allSystemPrefMap.default_tax_ratio}" name="default_tax_ratio" class="form-control touchspin_percent">
															</div>											
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">默认合同附件完整路径：</label>
															<input type="text" class="form-control" placeholder="请输入完整路径…" name="default_contract_path" value="${allSystemPrefMap.default_contract_path}">													
														</div>
													</div>
												</div>
											</div>
											<div class="form-actions right">
												<button type="button" class="btn blue" id="updateDefaultSettings-confirm">
													<i class="fa fa-check"></i>确认
												</button>
												<button type="button" class="btn default cancel">取消</button>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
							</div>
							<!-- END TAB3 -->
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
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/moment.min.js"></script>
<script type="text/javascript" src="../assets/admin/pages/scripts/components-pickers.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/system/system-settings-events.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   navigationBar.activeSystemMenu();
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.system-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
	   Layout.init(); // init current layout
	   Demo.init(); // init demo features
	   SystemSettingsEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>