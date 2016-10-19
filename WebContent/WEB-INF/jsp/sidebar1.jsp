<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<% String url = basePath+""; %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.oept.esales.model.Auth"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<% List<Auth> permissionList = (List<Auth>)session.getAttribute("authList"); %>
<% String username = session.getAttribute("username").toString();%>
<%!
	public boolean hasPermission(String permCode, List<Auth> permissionList){
		if(permissionList == null){
			return false;
		}
		Iterator<Auth> iter = permissionList.iterator();
		Auth permission = new Auth();
		while(iter.hasNext())  
        {  
			permission = iter.next();
			if(permission.getPerm_code().equals(permCode)){
				return true;
			}		              
        }
		return false;
	}
%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu page-sidebar-menu-closed" data-keep-expanded="false" data-auto-scroll="false" data-slide-speed="200">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="start home-menu">
					<a href="javascript:;">
					<i class="icon-home"></i>
					<span class="title">首页</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li class="active">
							<a href="<%=path %>/dashboard/list.do">
							<i class="icon-bar-chart"></i>
							个人工作中心</a>
						</li>
					</ul>
				</li>
				<% 
					if(hasPermission("module.ecommerce", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">电子商务</h3>
				</li>
				<li class="purchase-menu">
					<a href="javascript:;">
					<i class="icon-basket"></i>
					<span class="title">网上商城</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/portfolio/list.do">
							<i class="icon-handbag"></i>
							产品浏览</a>
						</li>
						<li>
							<a href="<%=path %>/shopcart/list.do">
							<i class="icon-basket"></i>
							购物车</a>
						</li>
						<li>
							<a href="<%=path %>/favorite/list.do">
							<i class="icon-tag"></i>
							我的收藏</a>
						</li>
					</ul>
				</li>
				<li class="order-menu">
					<a href="javascript:;">
					<i class="icon-diamond"></i>
					<span class="title">订单中心</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/order/list.do">
							<span class="badge badge-roundless badge-success">新</span>我的订单</a>
						</li>
						<li>
							<a href="<%=path %>/order/emergencelist.do">
							<span class="badge badge-roundless badge-warning">新</span>我的紧急订单</a>
						</li>
					</ul>
				</li>
				<li class="service-menu">
					<a href="javascript:;">
					<i class="icon-rocket"></i>
					<span class="title">服务中心</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/service/list.do?type=consult">
							我的咨询</a>
						</li>
						<li>
							<a href="<%=path %>/service/list.do?type=complaint">
							我的投诉</a>
						</li>
						<li>
							<a href="<%=path %>/service/list.do?type=return">
							<span class="badge badge-roundless badge-danger">新</span>返修/退货</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("module.crm", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">客户档案管理</h3>
				</li>
				<li class="account-menu">
					<a href="javascript:;">
					<i class="icon-grid"></i>
					<span class="title">单位管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/account/category_list.do"> --%>
<!-- 							客户类别管理</a> -->
<!-- 						</li> -->
						<% 
							if(hasPermission("accountadmin.info", permissionList)){
						%>
						<li>
							<a href="<%=path %>/account/ClientList.do">
							客户信息管理</a>
						</li>
						<%} %>
						<% 
							if(hasPermission("supplieradmin.info", permissionList)){
						%>
						<li>
							<a href="<%=path %>/account/SupplierList.do">
							供应商信息管理</a>
						</li>
						<%} %>
						<% 
							if(hasPermission("accounts.approval", permissionList)){
						%>
						<li>
							<a href="<%=path %>/account/approval_list.do">
							待审核单位信息</a>
						</li>
						<%} %>
					</ul>
				</li>
				<li class="contact-menu">
					<a href="javascript:;">
					<i class="icon-link"></i>
					<span class="title">联系人管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path%>/contact/list.do">
							联系人信息管理</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("module.sfe", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">销售效力管理</h3>
				</li>
				<li class="oppty-menu">
					<a href="javascript:;">
					<i class="icon-magnet"></i>
					<span class="title">商机管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/opty/list.do">
							我的商机</a>
						</li>
						<li>
							<a href="<%=path %>/opty/list.do">
							我团队的商机</a>
						</li>
						<li>
							<a href="<%=path %>/opty/list.do">
							所有商机</a>
						</li>
						<li>
							<a href="<%=path %>/opty/list.do">
							待审核商机</a>
						</li>
					</ul>
				</li>
				<li class="project-menu">
					<a href="javascript:;">
					<i class="icon-credit-card"></i>
					<span class="title">项目管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/project/list.do">
							我的项目</a>
						</li>
						<li>
							<a href="<%=path %>/project/list.do">
							我团队的项目</a>
						</li>
						<li>
							<a href="<%=path %>/project/list.do">
							所有项目</a>
						</li>
						<li>
							<a href="<%=path %>/project/list.do">
							待审核项目</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("module.reports", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">统计报表</h3>
				</li>
				<li class="project-menu">
					<a href="javascript:;">
					<i class="icon-book-open"></i>
					<span class="title">进销存报表</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/report/sales.do">
							销售报表</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("module.personal", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">个人设置</h3>
				</li>
				<li class="person-menu">
					<a href="javascript:;">
					<i class="icon-drawer"></i>
					<span class="title">个人信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/personal/settings.do">
							账户信息设置</a>
						</li>
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/personal/address.do"> --%>
<!-- 							账户地址管理</a> -->
<!-- 						</li> -->
						<li>
							<a href="<%=path %>/personal/passwordPage.do">
							修改账户密码</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("module.data.admin", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">数据管理</h3>
				</li>
<!-- 				<li class="order-adminmenu"> -->
<!-- 					<a href="javascript:;"> -->
<!-- 					<i class="icon-docs"></i> -->
<!-- 					<span class="title">订单管理</span> -->
<!-- 					<span class="arrow "></span> -->
<!-- 					</a> -->
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/order/list.do?filter=all"> --%>
<!-- 							<span class="badge badge-roundless badge-success">新</span>所有订单管理</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
				<li class="product-menu">
					<a href="javascript:;">
					<i class="icon-bag"></i>
					<span class="title">产品/服务管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/category/list.do"> --%>
<!-- 							产品/服务类别管理</a> -->
<!-- 						</li> -->
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/prodadmin/list.do"> --%>
<!-- 							产品目录管理</a> -->
<!-- 						</li> -->
						<% 
						if(hasPermission("productsadmin.list", permissionList)){
						%>
						<li>
							<a href="<%=path %>/prodadmin/listTree.do">
							产品/服务目录管理</a>
						</li>
						<%} %>
						<% 
						if(hasPermission("products.approval.list", permissionList)){
						%>
						<li>
							<a href="<%=path %>/prodadmin/approval_list.do">
							待审核产品/服务列表</a>
						</li>
						<%} %>
					</ul>
				</li>
<!-- 				<li class="sr-adminmenu"> -->
<!-- 					<a href="javascript:;"> -->
<!-- 					<i class="icon-bell"></i> -->
<!-- 					<span class="title">服务请求管理</span> -->
<!-- 					<span class="arrow "></span> -->
<!-- 					</a> -->
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/service/list.do"> --%>
<!-- 							<span class="badge badge-roundless badge-success">新</span>所有服务请求管理</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
				<% 
						if(hasPermission("data.addressadmin", permissionList)){
				%>
				<li class="addr-adminmenu">
					<a href="javascript:;">
					<i class="icon-globe"></i>
					<span class="title">地址管理</span>
					<span class="arrow "></span>
					</a>
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/user/address.do"> --%>
<!-- 							所有地址管理</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/user/address.do">
							区域管理</a>
						</li>
					</ul>
				</li>
				<%} %>
				<%} %>
				<% 
					if(hasPermission("module.system.admin", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">系统管理</h3>
				</li>
				<% 
					if(hasPermission("user.admin", permissionList)){
				%>
				<li class="user-menu">
					<a href="javascript:;">
					<i class="icon-user"></i>
					<span class="title">人员管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=path %>/user/list.do">
							用户管理</a>
						</li>
						<li>
							<a href="<%=path %>/user/position.do">
							职位管理</a>
						</li>
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("preferences.admin", permissionList)){
				%>
				<li class="system-menu">
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title">系统设置</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<% 
							if(hasPermission("pref.basic.parameters", permissionList)){
						%>
						<li>
							<a href="<%=path %>/system/settings.do">
							系统参数设置</a>
						</li>
						<%} %>
						<% 
							if(hasPermission("pref.approval.rule", permissionList)){
						%>
						<li>
							<a href="<%=path %>/system/approval_rule_list.do">
							审批规则设置</a>
						</li>
						<%} %>
						<% 
							if(hasPermission("pref.list.of.values", permissionList)){
						%>
						<li>
							<a href="<%=path %>/system/list_of_values.do">
							系统值列表设置</a>
						</li>
						<%} %>
					</ul>
				</li>
				<%} %>
				<%} %>
<!-- 				<li class="heading"> -->
<!-- 					<h3 class="uppercase">更多</h3> -->
<!-- 				</li> -->
<!-- 				<li class="help-menu"> -->
<!-- 					<a href="javascript:;"> -->
<!-- 					<i class="icon-envelope-open"></i> -->
<!-- 					<span class="title">帮助中心</span> -->
<!-- 					<span class="arrow "></span> -->
<!-- 					</a> -->
<!-- 					<ul class="sub-menu"> -->
<!-- 						<li> -->
<!-- 							<a href="#"> -->
<!-- 							帮助文档</a> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<a href="#"> -->
<!-- 							系统API</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
<!-- END SIDEBAR -->