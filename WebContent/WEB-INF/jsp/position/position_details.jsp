<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/12/24
 * Description:  Warehouse details management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 职位管理</title>
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
<!-- begin time control -->
<link href="../assets/admin/pages/css/search.css" rel="stylesheet" type="text/css"/>
<!-- end time control -->
<!-- END THEME STYLES -->
<!-- BEGIN APPLICATION STYLES -->
<link href="../css/autods.css" rel="stylesheet" type="text/css"/>
<link href="../css/contact/contact-ui.css" rel="stylesheet" type="text/css"/>
<!-- END APPLICATION STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!-- 本页样式 -->
<link href="../css/users/positionStyle.css" rel="stylesheet"/>
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
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			用户管理 <small>职位管理</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/user/list.do"">用户管理</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/user/position.do">职位管理</a>
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
			<div class="tab-content">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab_auth" data-toggle="tab"> 权限管理 </a></li>
					<li><a href="#tab_base"
						data-toggle="tab"> 基本信息 </a></li>
				</ul>
				<!-- BEGIN Media List TABLE PORTLET-->
				<div class="tab-pane " id="tab_base">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>职位基本信息
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="<%=path%>/media/list.do" class="reload">
								</a>
							</div>
						</div>
						<div class="portlet-body" style="height: 340px;">	
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">职位名称：<span class="required">* </span></label>
										<input type="text" name="positionId" id="positionId" value="${position.positionId }" style="display: none;">
										<input type="text" class="form-control" readonly="readonly" placeholder="请输入联系人姓氏…" name="positionName" id="positionName" value="${position.positionName }">													
									</div>
								</div>
							</div>	
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">分管上级：<span class="required">* </span></label>
										<select id="parentPosition" class="form-control" disabled="disabled">
											<c:forEach var="ps" items="${positionList }">
												<c:choose>
													<c:when test="${ps.positionId == position.parentPositionId}">
														<option value="${ps.positionId }" selected="selected">${ps.positionName }</option>
													</c:when>
													<c:otherwise>
														<option value="${ps.positionId }">${ps.positionName }</option>
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
										<p class="form-control-static">
											此记录是在 ${position.created}创建</p>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<p class="form-control-static">
											此记录最近一次修改是在 ${position.update}更新</p>
									</div>
								</div>
							</div>					
						</div>
					</div>
				</div>
				<!-- BEGIN Auth manager -->
				<div class="tab-pane active " id="tab_auth">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-list-ul"></i>${position.positionName }&nbsp;权限
							</div>
							<div class="actions">
								<a href="#" class="btn btn-default btn-sm" id="save"> 
								<i class="fa fa-print"></i> 保存 </a>
							</div>

						</div>
						<div class="portlet-body" style="height: 450px;" id="container">	
							<div id="body-auth">
							<div class="item">
									<ul class="ul-left" >
										<li class="font-lvl1"><input type="checkbox" name="alls"  class="checkboxes"  id="all">
										全选<span class="required">* </span>
										</li>
									</ul>
							</div>
							<c:forEach var="a1" items="${auth1 }">
								<div class="item">
									<ul class="ul-left" >
										<li class="font-lvl1">
										<input type="checkbox" name="ca" value="${a1.id}" id="${a1.id}"  class="checkboxes">
										${a1.perm_name}
										</li>
										<li >
											<ul >
												<c:forEach var="a2" items="${auth2 }">
													<c:if test="${a2.par_id == a1.id }">
														<li>
															<input type="checkbox" name="ca"  value="${a2.id}" id="${a2.id}" class="checkboxes">${a2.perm_name}
														</li>
														<li>
															<ul>
																<c:forEach var="a3" items="${auth3 }">
																	<c:if test="${a3.par_id == a2.id }">
																	<li><input type="checkbox"  name="ca" value="${a3.id}" id="${a3.id}" class="checkboxes">${a3.perm_name}</li>
																	<ul>
																		<c:forEach var="a4" items="${auth4 }">
																			<c:if test="${a4.par_id == a3.id }">
																			<li><input type="checkbox"  name="ca" value="${a4.id}" id="${a4.id}" class="checkboxes">${a4.perm_name}</li>
																			</c:if>
																		</c:forEach>
																	</ul>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
												</c:forEach>
											</ul>
										</li>
									</ul>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!-- END Auth manager -->
			</div>
			<!-- END Table ROWS -->
			<!--  div class="clearfix" -->
			<!--  div-->
		</div>
	</div>
	<!-- END CONTENT -->
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
<script src="../scripts/common-events.js" type="text/javascript"></script>
<!-- end -->

<script src="../scripts/users/position.js" type="text/javascript"></script>
<!-- 瀑布流布局插件 -->
<script src="../scripts/jquery/jquery.masonry.min.js" type="text/javascript"></script>
<script src="../scripts/users/position-events.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$('#container').masonry({
			itemSelector : '.item',
			columnWidth : 20
		});
	});
	
</script>
<script>
jQuery(document).ready(function() {   

	   PositionEvents.init();
	   Metronic.init(); // init metronic core components
	   navigationBar.activeUserMenu();
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   TableManaged.init();
	   $('ul.sub-menu').find('li').removeClass('active');
	   $('li.inventory-menu').find('ul.sub-menu').children('li:eq(0)').addClass('active');
	   CommonEvents.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>