<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 
/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/19
 * Description:  product details edit page.
 */
-->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>OEPT eSales - 单位管理</title>
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
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<link href="../assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/pages/css/timeline.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
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
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="remove-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">删除图片</h4>
						</div>
						<div class="modal-body">
							 确认删除此图片？
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue remove-image-confirm" data-dismiss="modal">确认</button>
							<button type="button" class="btn default" data-dismiss="modal">取消</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN ADDRESS PICK MODAL FORM-->
					<div class="modal fade bs-modal-lg" id="address-pick" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<!-- BEGIN SUCCESS MESSAGE -->
							<div class="alert alert-success display-hide"  style="display: none; width: 300px; position: absolute; z-index:9999; margin-left: 30%;margin-top: 2%;" >
								<button class="close" data-close="alert"></button>
								<span>成功修改!</span>
							</div>
							<!-- END SUCCESS MESSAGE -->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title">选择区域</h4>
								</div>
								<div class="modal-body">
								
								</div>
								<div class="modal-footer">
									<button type="button" class="btn blue address-pick" data-dismiss="modal">确认</button>
									<button type="button" class="btn default" data-dismiss="modal">取消</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- END ADDRESS PICK MODAL FORM-->
					<!-- BEGIN NEW ADDRESS MODAL-->
					<div class="modal fade" id="new-addr-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" >
							<div class="modal-content">
								<form method="post" >
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title">新建区域</h4>
								</div>
								<div class="modal-body">
		<!-- 						<div> -->
		<!-- 							<span>联系人：</span><br/> -->
		<!-- 							<input id="contactName" name="" class="newAddressInput" type="text"  placeholder="请输入联系人"> -->
		<!-- 						</div> -->
								<div>
		     						<span>国家*：</span><br/>
									<input id="country" name="" class="newAddressInput" type="text" value="中国" readonly="readonly">
		     					</div>
								<div class="controls">
									<span>所在地区*：</span><br/>
									<select name="location_p" id="location_p" class="newAssressSelect"></select>
		    						<select name="location_c" id="location_c" class="newAssressSelect"></select>
		    						<select name="location_a" id="location_a" class="newAssressSelect"></select>
		     					</div>
<!-- 		     					<div> -->
<!-- 		     						<span>详细地址：</span><br/> -->
<!-- 									<input id="detailsAddress" name="" class="newAddressInput" type="text"  placeholder="请输入..（可以为空）"> -->
<!-- 		     					</div> -->
<!-- 		     					<div> -->
<!-- 		     						<span>邮编：</span><br/> -->
<!-- 									<input id="zipcode" name="" class="newAddressInput" type="text"  placeholder="请输入..（可以为空）" -->
<%-- 									onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false" onkeyup="numTesting()" maxlength="6"> --%>
<!-- 		     					</div> -->
		<!--      					<div> -->
		<!--      						<span>电话号码：</span><br/> -->
		<!-- 							<input id="contactTel" name="" class="newAddressInput" type="text"  placeholder="请输入电话号码，只能输入数字" -->
		<%-- 							onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" maxlength="11"> --%>
		<!--      					</div> -->
								</div>
								<div class="modal-footer">
									<button type="button" class="btn blue new-addr-submit" data-dismiss="modal">保存区域</button>
									<button type="button" class="btn default" data-dismiss="modal">取消</button>
								</div>
								</form>
								
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					<!-- END NEW ADDRESS MODAL-->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			客户供应商信息设置 <small>编辑客户/供应商</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=path%>/dashboard/list.do">首页</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=path%>/account/ClientList.do">客户列表</a>/
						<a href="<%=path%>/account/SupplierList.do">供应商列表</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">编辑客户/供应商</a>
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
			<!-- BEGIN PAGE CONTENT-->
			<jsp:include page="account_contents.jsp" />
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
	<!-- END QUICK SIDEBAR -->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<script type="text/javascript" src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
<script src="../assets/global/plugins/plupload/js/plupload.full.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js"></script>
<script src="../assets/admin/pages/scripts/ecommerce-products-edit.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
<script src="../scripts/account/account-edit-events.js" type="text/javascript"></script>
<!-- begin 省市级联js -->
<script src="../scripts/jquery/region_select.js" type="text/javascript"></script>
<!-- end -->
<script>
        jQuery(document).ready(function() {    
           	Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			//QuickSidebar.init(); // init quick sidebar
			//Demo.init(); // init demo features
           	EcommerceProductsEdit.init();
//            	ProductsEvents.init();
//            	ProductsEvents.loadCategories();
//            	ProductsEvents.loadImages();
           	navigationBar.activeAccountMenu();
			AccountEditEvents.loadCategories();
			AccountEditEvents.init();
        });
    </script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>