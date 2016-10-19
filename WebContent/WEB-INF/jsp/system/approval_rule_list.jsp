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
 * Date: 2015/11/07
 * Description:  User List management page.
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
<title>审批规则设置</title>
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
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css"
	rel="stylesheet" type="text/css" />
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
<link href="../assets/admin/layout/css/themes/blue.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
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
	<jsp:include page="../header.jsp" />
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<jsp:include page="../sidebar.jsp" />
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">删除</h4>
							</div>
							<div class="modal-body">确认要删除所选审批规则？</div>
							<div class="modal-footer">
								<button type="button" id="deleteUser-confirm" class="btn blue"
									data-dismiss="modal">删除</button>
								<button type="button" class="btn default" data-dismiss="modal">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<h3 class="page-title">
					系统设置 <small>审批规则设置</small>
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<%=path %>/dashboard/list.do">首页</a> <i
							class="fa fa-angle-right"></i></li>
						<li>
							<a href="<%=path %>/system/settings.do">系统设置</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="<%=path %>/system/approval_rule_list.do">审批规则列表</a>
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
						<!-- BEGIN Media List TABLE PORTLET-->
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"> </a> <a href="#"
										class="reload"> </a>
								</div>
							</div>
							<div class="portlet-body">
<!-- 								测试 -->
<!-- 								<div><input id="test1" type="text"> -->
<!-- 								<input id="test2" type="text"> -->
<!-- 								<input id="test3" type="text"> -->
<!-- 								<input id="test4" type="text"> -->
<!-- 								<input id="test_s" type="button" value="t s"></div> -->
<!-- 								测试 -->
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div class="actions">
												<a href="<%=path%>/system/approval_rule_add.do" data-toggle="modal" class="btn green">
													<i class="fa fa-plus"></i> 新建
												</a>
											</div>
										</div>
										<div class="col-md-6">
											<div class="actions pull-right" id="delectButton" style="display: none;">
												<a href="#portlet-config" data-toggle="modal"
													class="btn red" id="delete_option">
													<i class="fa fa-minus"></i> 删除项
												</a>
											</div>
										</div>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover"
									id="sample_1">
									<thead>
										<tr>
											<th class="table-checkbox">
											<input type="checkbox" id="checkboxAlls" onclick="checkboxAll('checkboxAlls')"
												class="group-checkable" data-set="#sample_1 .checkboxes"/>
											</th>
											<th>审批名称</th>
											<th>审批对象</th>
											<th>审批类型</th>
											<th>审批方式</th>
											<th>通知方式</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="approval" items="${approvalList}">
											<%--用EL表达式调用list对象的属性循环输出对象的各个属性值--%>
											<tr class="odd gradeX">
												<td><input type="checkbox" name="checkbox" value="${approval.rule_id}" class="checkboxes" data-set="#sample_1 .checkboxes"/>
												</td>
												<td><a href="<%=path %>/system/approvalDetailPage.do?ruleId=${approval.rule_id}" class = "approvalDetail" id="${approval.rule_id}">${approval.rule_name}</a></td>
												<td>${approval.object_name }</td>
												<td>${approval.action_name }</td>
												<td>${approval.rollback_type_name }</td>
												<td>
													<c:if test="${approval.email == true }">邮箱<br></c:if>
													<c:if test="${approval.inbox == true }">站内</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
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

<script src="../scripts/users/userList.js" type="text/javascript"></script>

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
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/table-managed.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/system/approval-events.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {      
	   ApprovalEvents.init();
	   Metronic.init(); // init metronic core components
	   navigationBar.activeSystemMenu();
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   TableManaged.init();
});
</script>
<!-- END JAVASCRIPTS -->
	
</body>
<!-- END BODY -->
</html>