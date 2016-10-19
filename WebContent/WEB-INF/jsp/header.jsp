<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<img src="../img/header-logo.png" alt="logo" class="logo-default"/>
			<div class="menu-toggler sidebar-toggler hide">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="../img/avatar.png"/>
					<span class="username username-hide-on-mobile">
					${username} </span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li>
							<a href="<%=path %>/personal/settings.do">
							<i class="icon-user"></i> 个人设置 </a>
						</li>
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/inbox/list.do"> --%>
<!-- 							<i class="icon-envelope-open"></i> 站内消息 <span class="badge badge-warning"> -->
<!-- 							3 </span> -->
<!-- 							</a> -->
<!-- 						</li> -->
						<li class="divider">
						</li>
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/auth/lockscreen.do"> --%>
<!-- 							<i class="icon-lock"></i> 锁定界面 </a> -->
<!-- 						</li> -->
						<li>
							<a href="<%=path %>/auth/logout.do">
							<i class="icon-key"></i> 登出 </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
				<!-- BEGIN QUICK SIDEBAR TOGGLER -->
				<li>
					<a href="<%=path %>/auth/logout.do">
					<i class="icon-logout"></i>
					</a>
				</li>
				<!-- END QUICK SIDEBAR TOGGLER -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->