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
					if(hasPermission("module.scm", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">进销存管理</h3>
				</li>
				<% 
					if(hasPermission("scm.poadmin", permissionList)){
				%>
				<li class="po-menu">
					<a href="javascript:;">
					<i class="icon-compass"></i>
					<span class="title">采购管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
					<% 
					if(hasPermission("poadmin.purchaseorders", permissionList)){
					%>
						<li>
							<a href="javascript:;">
							采购进项 <span class="arrow"></span></a>
									<ul class="sub-menu">
									<% 
									if(hasPermission("purchaseorders.my.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/po_list.do">我的采购单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("purchaseorders.all.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/po_all_list.do">所有采购单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("purchaseorders.approval.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/po_approval_list.do">待审核采购单</a>
										</li>
									<%} %>
									</ul>
						</li>
					<%} %>
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/order/po_return_list.do"> --%>
<!-- 							采购退货出项</a> -->
<!-- 						</li> -->
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("scm.soadmin", permissionList)){
				%>
				<li class="so-menu">
					<a href="javascript:;">
					<i class="icon-wallet"></i>
					<span class="title">销售管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
					<% 
					if(hasPermission("soadmin.salesorders", permissionList)){
					%>
						<li>
							<a href="javascript:;">
							销售出项 <span class="arrow"></span></a>
									<ul class="sub-menu">
									<% 
									if(hasPermission("salesorders.my.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/so_list.do">我的销售单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("salesorders.all.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/so_all_list.do">所有销售单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("salesorders.approval.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/order/so_approval_list.do">待审核销售单</a>
										</li>
									<%} %>
									</ul>
						</li>
					<%} %>
<!-- 						<li> -->
<%-- 							<a href="<%=path %>/order/so_return_list.do"> --%>
<!-- 							销售退货进项</a> -->
<!-- 						</li> -->
					</ul>
				</li>
				<%} %>
				<% 
					if(hasPermission("scm.inventoryadmin", permissionList)){
				%>
				<li class="inventory-menu">
					<a href="javascript:;">
					<i class="icon-basket-loaded"></i>
					<span class="title">库存管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
					<% 
					if(hasPermission("inventoryadmin.warehouses", permissionList)){
					%>
						<li>
							<a href="<%=path %>/inventory/warehouse_list.do">
							仓库管理</a>
						</li>
					<%} %>
					<% 
					if(hasPermission("inventoryadmin.stockin.req", permissionList)){
					%>
						<li>
							<a href="javascript:;">
							入库单管理 <span class="arrow"></span></a>
									<ul class="sub-menu">
									<% 
									if(hasPermission("stockin.req.my.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_in_list.do">我的入库单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stockin.req.all.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_in_all_list.do">所有入库单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stockin.req.approval.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_in_approval_list.do">待审核入库单</a>
										</li>
									<%} %>
									</ul>
						</li>
					<%} %>
					<% 
					if(hasPermission("inventoryadmin.stockout.req", permissionList)){
					%>
						<li>
							<a href="javascript:;">
							出库单管理 <span class="arrow"></span></a>
									<ul class="sub-menu">
									<% 
									if(hasPermission("stockout.req.my.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_out_list.do">我的出库单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stockout.req.all.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_out_all_list.do">所有出库单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stockout.req.approval.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_out_approval_list.do">待审核出库单</a>
										</li>
									<%} %>
									</ul>
						</li>
					<%} %>
					<% 
					if(hasPermission("inventoryadmin.stocktrans.req", permissionList)){
					%>
						<li>
							<a href="javascript:;">
							调拨单管理 <span class="arrow"></span></a>
									<ul class="sub-menu">
									<% 
									if(hasPermission("stocktrans.req.my.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_transfer_list.do">我的调拨单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stocktrans.req.all.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_transfer_all_list.do">所有调拨单</a>
										</li>
									<%} %>
									<% 
									if(hasPermission("stocktrans.req.approval.list", permissionList)){
									%>
										<li>
											<a href="<%=path %>/inventory/stock_transfer_approval_list.do">待审核调拨单</a>
										</li>
									<%} %>
									</ul>
						</li>
					<%} %>
					</ul>
				</li>
				<%} %>
<%-- 				<%  if(hasPermission("scm.contract.admin", permissionList)){--%>
<!-- 				<li class="contract-menu"> -->
<!-- 					<a href="javascript:;"> -->
<!-- 					<i class="icon-compass"></i> -->
<!-- 					<span class="title">合同管理</span> -->
<!-- 					<span class="arrow "></span> -->
<!-- 					</a> -->
<!-- 					<ul class="sub-menu"> -->
<%-- 									<%  if(hasPermission("contract.admin.my.list", permissionList)){
<%-- 									%> --%>
<!-- 										<li> -->
<%-- 											<a href="<%=path %>/contract/my_list.do">我的合同</a> --%>
<!-- 										</li> -->
<%-- 									<%} %> --%>
<%-- 									<%  if(hasPermission("contract.admin.all.list", permissionList)){
<%-- 									%> --%>
<!-- 										<li> -->
<%-- 											<a href="<%=path %>/contract/all_list.do">所有合同</a> --%>
<!-- 										</li> -->
<%-- 									<%} %> --%>
<%-- 									<%  if(hasPermission("contract.admin.approval.list", permissionList)){
<%-- 									%> --%>
<!-- 										<li> -->
<%-- 											<a href="<%=path %>/contract/approval_list.do">待审核合同</a> --%>
<!-- 										</li> -->
<%-- 									<%} %> --%>
<!-- 					</ul> -->
<!-- 				</li> -->
<%-- 				<%} %> --%>
				<%} %>
				<% 
					if(hasPermission("module.crm", permissionList)){
				%>
				<li class="heading">
					<h3 class="uppercase">客户关系管理</h3>
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
				<% 
					if(hasPermission("crm.contactadmin", permissionList)){
				%>
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
							<a href="<%=path %>/oppty/list.do">
							商机信息管理</a>
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
							项目信息管理</a>
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