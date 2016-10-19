<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: zhujj
 * Version: 1.0
 * Date: 2015/12/22
 * Description:  customer management page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 审批规则设置</title>
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
<link href="../assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
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
<!-- END THEME STYLES -->
<link href="../css/system/system-style.css" rel="stylesheet" type="text/css"/>
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
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			系统设置 <small>审批规则设置</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/system/settings.do">系统设置</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path %>/system/approval_rule.do">审批规则设置</a>
					</li>
				</ul>
			</div>
			<div class="portlet light bordered">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-bookmark font-blue"></i>
											<span class="caption-subject font-gary-sunglo bold uppercase">审核规则设置</span>
										</div>
										<div class="actions">
											<div class="portlet-input input-inline input-small">
												<div class="input-icon right">
												</div>
											</div>
										</div>
									</div>
									<!-- BEGIN user pick FORM-->
										<div class="modal fade bs-modal-lg" id="user-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h4 class="modal-title">选择用户</h4>
													</div>
															<div class="modal-body2">
																<table style=" width: 100%;">
																	<tr>
																		<td>
																			<div class="col-md-offset-3 col-md-3 button-right">
																				<button class="btn green btn-block" id="saveAppr"  data-dismiss="modal">保存审核过程</button>
																			</div>
																			
																		</td>
																	</tr>
																</table>
																<table class="item-t">
																	<tr class="item-t-tr">
																		<td class="item-t-td-l">方式：</td>
																		<td class="item-t-td-r" colspan="2">
																			<div class="radio-list">
																				<label class="radio-inline">
																				<input type="radio" name="optionsRadios3" id="Anyone Pass" value="一个通过即可"> 一个通过即可</label>
																				<label class="radio-inline">
																				<input type="radio" name="optionsRadios3" id="All Must Pass" value="全部通过才可以"> 全部通过才可以 </label>
																				<label class="radio-inline">
																				<input type="radio" name="optionsRadios3" id="Anyone Pass and Anyone Reject" value="一个通过即可，一个拒绝就回退" checked="checked"> 一个通过即可，一个拒绝就回退 </label>
																			</div>
																		</td>
																	</tr>
																	<tr><td></td><td></td></tr>
																	<tr class="item-t-tr">
																		<td class="item-t-td-l">审核人：</td>
																		<td class="item-t-td-r">
																			<input type="text" class="form-control" placeholder="请选择.." value="" name="approval-per" id="">
																		</td>
																		<td>
																			<a class="btn default dark-stripe" id="clear">清除 </a>

																		</td>
																	</tr>
																	<tr class="item-t-tr" style="display: none;">
																		<td>
																			<input type="text" value="" name="approval-id" id="">
																		</td>
																	</tr>
																</table>
															</div>
													<div class="modal-body">
														
													</div>
													<div class="modal-footer">
														<button type="button" class="btn blue user-pick">确认</button>
														<button type="button" class="btn default" data-dismiss="modal">取消</button>
													</div>
												</div>
											</div>
										</div>
									<!-- END -->
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal">
											<div class="form-body">
												<div class="form-group">
													<span class="col-md-6 sys-group-span">审核流程名称</span>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">名称：</label>
													<div class="col-md-4">
														<input type="text" class="form-control" placeholder="输入名称.." value="" id="aName">
													</div>
												</div>
												<div class="form-group">
													<span class="col-md-6 sys-group-span">选择审核对象</span>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">审核对象：</label>
													<div class="col-md-4">
														<input type="text" class="form-control" placeholder="请选择.." value="" name="" id="aObject">
													</div>
													<div class="col-md-2">
														<div class="btn-group">
															<button id="btnGroupVerticalDrop5" type="button" class="btn yellow dropdown-toggle" data-toggle="dropdown">
															选择 <i class="fa fa-angle-down"></i>
															</button>
															<ul class="dropdown-menu" role="menu" aria-labelledby="btnGroupVerticalDrop5">
																<li>
																	<a class="approvalObject" id="Purchase Order">采购订单 </a>
																</li>
<!-- 																<li> -->
<!-- 																	<a class="approvalObject" id="Purchase Return Order">采购退货订单 </a> -->
<!-- 																</li> -->
																<li>
																	<a class="approvalObject" id="Sales Order">销售订单 </a>
																</li>
<!-- 																<li> -->
<!-- 																	<a class="approvalObject" id="Sales Return Order">销售退货订单 </a> -->
<!-- 																</li> -->
																<li>
																	<a class="approvalObject" id="Stock In Requisition">入库单 </a>
																</li>
																<li>
																	<a class="approvalObject" id="Stock Out Requisition">出库单 </a>
																</li>
																<li>
																	<a class="approvalObject" id="Stock Transfer Requisition">调拨单 </a>
																</li>
<!-- 																<li> -->
<!-- 																	<a class="approvalObject" id="Purchase Contract">采购合同 </a> -->
<!-- 																</li> -->
<!-- 																<li> -->
<!-- 																	<a class="approvalObject" id="Sales Contract">销售合同 </a> -->
<!-- 																</li> -->
															</ul>
														</div>
													</div>
												</div>
												<div class="form-group">
													<span class="col-md-6 sys-group-span">选择审核控制操作</span>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">类型：</label>
													<div class="col-md-8">
														<div class="input-group">
															<div class="radio-list">
																<label class="radio-inline">
																<input type="radio" name="optionsRadios" id="Submit" value="提交单据" checked="checked"> 提交单据 </label>
																<label class="radio-inline">
																<input type="radio" name="optionsRadios" id="Cancel" value="取消单据"> 取消单据 </label>
																<label class="radio-inline">
																<input type="radio" name="optionsRadios" id="Complete" value="完成单据"> 完成单据 </label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">回退方式：</label>
													<div class="col-md-8">
														<div class="input-icon">
															<div class="radio-list">
																<label class="radio-inline">
																<input type="radio" name="optionsRadios2" id="Revert" value="退回到上一步" checked="checked"> 退回到上一步 </label>
																<label class="radio-inline">
																<input type="radio" name="optionsRadios2" id="Restart" value="重新从第一步开始 "> 重新从第一步开始 </label>
																<label class="radio-inline">
																<input type="radio" name="optionsRadios2" id="Cancel" value="取消整个审批过程"> 取消整个审批过程 </label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<span class="col-md-6 sys-group-span">审核控制设计，增加一个或多个审核过程</span>
												</div>
												<div class="form-group" id="approvalCourse">
													<div class="approvalCourse-body">
													
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"></label>
													<div class="col-md-3">
														<div class="input-icon right">
															<a href="#user-pick"  data-toggle="modal" class="btn default btn-block" id="addApproval">增加一个新的审核过程 </a>
														</div>
													</div>
												</div>
												<div class="form-group">
													<span class="col-md-6 sys-group-span">审核通知</span>
												</div>
												<div class="form-group last">
													<label class="col-md-3 control-label">通知：</label>
													<div class="col-md-4">
														<div class="icheck-inline">
															<input type="checkbox" name="email_notify" id="email_notify" class="checkboxes"> 邮件通知
															<input type="checkbox" name="inbox_notify" id="inbox_notify" class="checkboxes"> 站内通知
														</div>
													</div>
												</div>
											</div>
											<div class="form-actions">
												<div class="row">
													<div class="col-md-offset-3 col-md-4">
														<button class="btn green btn-block" id="submitAppr">保存流程</button>
													</div>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
			
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
<script src="../scripts/system/approval-events.js" type="text/javascript"></script>

<script type="text/javascript">

</script>
<script>
jQuery(document).ready(function() {    
	   ApprovalEvents.init();
	   Metronic.init(); // init metronic core components
	   navigationBar.activeSystemMenu();
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