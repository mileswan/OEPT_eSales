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
 * Date: 2015/12/22
 * Description:  customer management page.
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
<title>OEPT eSales - 单位管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/jstree/dist/themes/default/style.min.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link id="style_color" href="../assets/admin/layout/css/themes/blue.css"
	rel="stylesheet" type="text/css" />
<link href="../css/account/account-ui.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="../favicon.ico" />
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
			
				
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<h3 class="page-title">
					单位管理 <small>${typeFlag}信息管理</small>
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<%=path%>/auth/dashboard.do">首页</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a href="#">单位管理</a> <i
							class="fa fa-angle-right"></i></li>
						<li><a>${typeFlag}信息管理</a></li>
					</ul>
				</div>
				<div class="content">
					<div>
					<input type="text" value="${typeFlag}" name="typeFlag" style="display: none;">
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
						<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
						<div class="modal fade" id="portlet-config" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" id="modal-content">
									<div id="m-top">
										<div id="m-tr">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true"></button>
										</div>
										<h4 class="modal-title">新增单位类</h4>
									</div>
									<div id="m-body">
										<table class="m-table5">
											<tr>
												<td class="m-tl">父类名称：</td>
												<td><select class="m-bor" id="acpName">
														<c:forEach var="acName" items="${aCatName }">
															<option value="${acName.catId }">${acName.catName }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td class="m-tl">类名称：</td>
												<td><input type="text" class="m-bor" id="acsName"></td>
											</tr>
										</table>
									</div>
									<div id="m-bottom">
										<button type="button" class="btn blue" id="newAccountCatButton"  data-dismiss="modal">保存</button>
										<button type="button" class="btn default" data-dismiss="modal">取消</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
						<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
						<div class="modal fade" id="portlet-config2" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" id="modal-content">
									<div id="m-top">
										<div id="m-tr">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true"></button>
										</div>
										<h4 class="modal-title">更改单位类</h4>
									</div>
									<div id="m-body">
										<table class="m-table5">
											<tr>
												<td class="m-tl">父类名称：</td>
												<td><select class="m-bor" id="acpName2">
														<c:forEach var="acName" items="${aCatName }">
															<option value="${acName.catId }">${acName.catName }</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td class="m-tl">类名称：</td>
												<td><input type="text" class="m-bor" id="acsName2"></td>
											</tr>
										</table>
									</div>
									<div style="display: none;">
										<input type="text" id="dataNone">
									</div>
									<div id="m-bottom">
										<button type="button" class="btn blue" data-dismiss="modal" onclick="updateAccountCat()">保存</button>
										<button type="button" class="btn default" data-dismiss="modal">取消</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
						<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
						<div class="modal fade" id="portlet-config3" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" id="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true"></button>
										<h4 class="modal-title">删除类</h4>
									</div>
									<div class="modal-body">是否删除？（删除该文件夹，文件夹内所有内容会被清空！）</div>
									<div class="modal-footer">
										<button type="button" class="btn blue"
											onclick="deleteAcNode()">删除</button>
										<button type="button" class="btn default" data-dismiss="modal">取消</button>
									</div>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
					</div>
					<div>
						<!-- BEGIN SOURCE MESSAGE -->
						<div class="alert alert-danger display-hide" style="display: none;">
							<button class="close" data-close="alert"></button>
							<span>You have some form errors. Please check below.</span>
						</div>
						<div class="alert alert-success display-hide" style="display: none;">
							<button class="close" data-close="alert"></button>
							<span>成功修改!</span>
						</div>
						<!-- END SOURCE MESSAGE -->
						<div class="content-b">
							<!-- BEGIN TREE VIWE -->
							<div class="m-tree">
								<div class="form-group">
									<div class="portlet box yellow" id="m-tree">
										<div class="portlet-title">
											<div class="caption">单位信息</div>
											<div class="tools">
												<a href="#portlet-config" data-toggle="modal"
													onclick="selectCname()"><i class="fa fa-plus"
													style="color: white;"></i></a> <a href="#portlet-config2"
													data-toggle="modal" onclick="selectAcDe()"><i
													class="fa fa-edit" style="color: white;"></i></a> 
<!-- 													<a href="#portlet-config3" data-toggle="modal"><i -->
<!-- 													class="fa fa-times" style="color: white;"></i></a> -->
											</div>
										</div>
										<div class="portlet-body">
											<div id="tree_1" class="tree-demo">
												<c:forEach var="a1" items="${aList1}" varStatus="s1">
													<ul>
														<li id=${a1.catId } data-jstree='{ "opened" : true }'><a
															onclick="detail(${a1.catId })" id="lia${a1.catId }">${a1.catName }</a> <c:forEach
																var="a2" items="${aList2}" varStatus="s2">
																<ul>
																	<c:if test="${a2.catParId == a1.catId }">
																		<li id=${a2.catId } data-jstree='{ "opened" : true }'><a
																			onclick="detail(${a2.catId })"  id="lia${a2.catId }" >${a2.catName }</a> <c:forEach
																				var="a3" items="${aList3}" varStatus="s3">
																				<ul>
																					<c:if test="${a3.catParId == a2.catId }">
																						<li id=${a3.catId } data-jstree='{ "opened" : true }'><a
																							onclick="detail(${a3.catId })"  id="lia${a3.catId }">${a3.catName }</a>
																							<c:forEach var="a4" items="${aList4}"
																								varStatus="s4">
																								<ul>
																									<c:if test="${a4.catParId == a3.catId }">
																										<li id=${a4.catId }><a
																											onclick="detail(${a4.catId })" id="lia${a4.catId }">${a4.catName }</a>
																											<c:forEach var="a5" items="${aList5}"
																												varStatus="s5">
																												<ul>
																													<c:if test="${a5.catParId == a4.catId }">
																														<li id=${a5.catId }><a
																															onclick="detail(${a5.catId })" id="lia${a5.catId }">${a5.catName }</a>
																															<c:forEach var="a6" items="${aList6}"
																																varStatus="s6">
																																<ul>
																																	<c:if test="${a6.catParId == a5.catId }">
																																		<li id=${a6.catId }><a
																																			onclick="detail(${a6.catId })" id="lia${a6.catId }">${a6.catName }</a>
																																			<c:forEach var="a7" items="${aList7}"
																																				varStatus="s7">
																																				<ul>
																																					<c:if test="${a7.catParId == a6.catId }">
																																						<li id=${a7.catId }><a
																																							onclick="detail(${a7.catId })" id="lia${a7.catId }">${a7.catName }</a>
																																							<c:forEach var="a8" items="${aList8}"
																																								varStatus="s8">
																																								<ul>
																																									<c:if test="${a8.catParId == a7.catId }">
																																										<li id=${a8.catId }><a
																																											onclick="detail(${a8.catId })" id="lia${a8.catId }">${a8.catName }</a>
																																											<c:forEach var="a9" items="${aList9}"
																																												varStatus="s9">
																																												<ul>
																																													<c:if test="${a9.catParId == a8.catId }">
																																														<li id=${a9.catId }><a
																																															onclick="detail(${a9.catId })" id="lia${a9.catId }">${a9.catName }</a>
																																															<c:forEach var="a10" items="${aList10}"
																																																varStatus="s10">
																																																<ul>
																																																	<c:if test="${a10.catParId == a9.catId }">
																																																		<li id=${a10.catId }><a
																																																			onclick="detail(${a10.catId })" id="lia${a10.catId }">${a10.catName }</a>
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
<!-- 											<a href="#" class="btn btn-circle btn-default" -->
<!-- 												style="height: 32px;" onclick="editAt()"> <i class="fa fa-pencil"></i> 编辑 -->
<!-- 											</a>  -->
											<a href="#" class="btn btn-circle btn-default addAt"
												style="height: 32px;"> <i class="fa fa-plus"></i> 添加
											</a> 
											<a href="#" class="btn btn-circle btn-default" id="deleteAtButton"
												style="height: 32px;"> <i class="fa fa-times"></i> 删除
											</a>
										</div>
									</div>
									<div class="portlet-body" id="m-table-b">
										<div class="table-scrollable">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>行号</th>
														<th>单位编号</th>
														<th>单位名称</th>
														<th>区域</th>
														<th>类型</th>
														<th>状态</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="atDe" items="${atDe }" varStatus="status">
													<tr id="a${atDe.accountId}" onclick="check(${atDe.accountId});">
														<th style="font-weight: normal;">${status.count }</th>
														<th style="font-weight: normal;">${atDe.accountNumber }</th>
														<th style="font-weight: normal;">${atDe.accountName }</th>
														<th style="font-weight: normal;">${atDe.address.allAddress }</th>
														<th><c:if test="${atDe.accountType == '客户'}">
																<span class="label label-sm label-success">${atDe.accountType }</span>
															</c:if> <c:if test="${atDe.accountType == '供应商'}">
																<span class="label label-sm label-warning">${atDe.accountType }</span>
															</c:if></th>
														<th>
															<c:if test="${atDe.accountStatus == 'Published'}">
																<span class="label label-sm label-success">${atDe.accountStatusVal }</span>
															</c:if> 
															<c:if test="${atDe.accountStatus == 'Pending'}">
																<span class="label label-sm label-info">${atDe.accountStatusVal }</span>
															</c:if> 
															<c:if test="${atDe.accountStatus == 'Not Published'}">
																<span class="label label-sm label-warning">${atDe.accountStatusVal }</span>
															</c:if>
															<c:if test="${atDe.accountStatus == 'Rejected'}">
																<span class="label label-sm label-danger">${atDe.accountStatusVal }</span>
															</c:if>
														</th>
														<th><a href="accountDetail.do?accountId=${atDe.accountId}" class="btn btn-xs default btn-editable"><i class="fa fa-pencil"></i>编辑</a></th>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>

									</div>
								</div>
								<!-- END BORDERED TABLE PORTLET-->
								<div class="m-add" id="m-add" style="display: none; position: relative; z-index: 999;">
								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box yellow" id="m-table2">
									<div class="portlet-title">
										<div class="caption"></div>
										<div class="tools" style="padding-top: 5px; padding-bottom: 0px;">
											<a href="#" class="btn " style="height: 32px;" onclick="saveAdd();afterSave()"> <i
												class="fa fa-plus"></i> 保存
											</a> <a href="javascript:;" class=" btn closeViwe" style="height: 32px;" >
												<i class="fa fa-times"></i> 取消
											</a>
										</div>
									</div>
									<div class="portlet-body" id="m-table-b2">
										<!--/row1-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">单位名称*：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aName" id="aName" value="">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">单位编号：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aNumber" id="aNumber" value="">
												</div>
											</div>
										</div>
										</div>
										<!--/row2-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">区域*：</label> 
														<a class="from-c23a" href="#address-pick"  data-toggle="modal"><span class="input-group-addon" id="from-c2">
															<i class="fa fa-search" id="from-c22"></i>
														</span></a>
														<input
														type="text" class="from-c2" placeholder="请输入…"
														name="address" value="" readonly="readonly">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">详细地址*：</label>
														<input
														type="text" class="from-c" placeholder="请输入…"
														name="aAddrDet" id="aAddrDet" value="">
												</div>
											</div>
										</div>
										</div>
										<!--/row2-2-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">其他地址1：</label> 
														<input
														type="text" class="from-c" placeholder="请输入…"
														name="aOtherAddr1" id="aOtherAddr1" value="">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">其他地址2：</label>
														<input
														type="text" class="from-c" placeholder="请输入…"
														name="aOtherAddr2" id="aOtherAddr2" value="">
												</div>
											</div>
										</div>
										</div>
										<!--/row3-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">传真：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aFax" id="aFax" value="">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">E-mail：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aEmail" id="aEmail" value="">
												</div>
											</div>
										</div>
										</div>
										<!--/row4-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">联系电话：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aTel" id="aTel" value="">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">邮政编码：</label> 
														<input
														type="text" class="from-c" placeholder="请输入…"
														name="aZipcode" id="aZipcode" value="">
												</div>
											</div>
										</div>
										</div>
										<!--/row5-->
										<div id="row33">
										<div class="row">
											<div class="col-md-6" id="col-md-6">
												<div class="form-group">
													<label class="control-label">单位描述：</label> <input
														type="text" class="from-c" placeholder="请输入…"
														name="aDesc" id="aDesc" value="">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">类型：</label> 
													
													<input
														type="text" class="from-c"
														name="aType" id="aType" value="${typeFlag }" readonly>
												</div>
											</div>
										</div>
										</div>
									</div>
								</div>
								<!-- END BORDERED TABLE PORTLET-->
								</div>
								<!-- END BORDERED TABLE PORTLET-->
								<div class="m-add" id="m-edit" style="display: none;  position: absolute; z-index: 999;">
									<!-- BEGIN BORDERED TABLE PORTLET-->
									<div class="portlet box yellow" id="m-table2">
										<div class="portlet-title">
											<div class="caption"></div>
											<div class="tools"
												style="padding-top: 5px; padding-bottom: 0px;">
												<a href="#" class="btn saveEdit " style="height: 32px;"> <i
													class="fa fa-plus"></i> 保存
												</a> <a href="javascript:;" class=" btn closeViwe" style="height: 32px;">
													<i class="fa fa-times"></i> 取消
												</a>
											</div>
										</div>
										<div class="portlet-body" id="m-table-b2">
											<!--/row1-->
											<div id="row33">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">单位名称*：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aName2" id="aName2" value="">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">单位编号：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aNumber2" id="aNumber2" value="">
													</div>
												</div>
											</div>
											</div>
											<!--/row2-->
											<div id="row33">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">区域*：</label>
															<a class="from-c23a2" href="#address-pick"  data-toggle="modal"><span class="input-group-addon" id="from-c2">
																<i class="fa fa-search" id="from-c22"></i>
															</span></a>
															<input
															type="text" class="from-c2" placeholder="请输入…"
															name="address2" value="" readonly="readonly">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">详细地址*：</label> 
														<input
															type="text" class="from-c" placeholder="请输入…"
															name="aAddrDet2" id="aAddrDet2" value="">
													</div>
												</div>
											</div>
											</div>
											<!--/row3-->
											<div id="row33">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">传真：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aFax2" id="aFax2" value="">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">E-mail：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aEmail2" id="aEmail2" value="">
													</div>
												</div>
											</div>
											</div>
											<!--/row4-->
											<div id="row33">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">联系电话：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aTel2" id="aTel2" value="">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">邮政编码：</label> 
														<input
															type="text" class="from-c" placeholder="请输入…"
															name="aZipcode2" id="aZipcode2" value="">
													</div>
												</div>
											</div>
											</div>
											<!--/row5-->
											<div id="row33">
											<div class="row">
												<div class="col-md-6" id="col-md-6">
													<div class="form-group">
														<label class="control-label">单位描述：</label> <input
															type="text" class="from-c" placeholder="请输入…"
															name="aDesc2" id="aDesc2" value="">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">类型：</label> 
														 <input type="text" class="from-c" placeholder="请输入…"
															name="aType2" id="aType2" value="" readonly>
															<input style="display: none;"
															type="text" class="from-c" placeholder="请输入…"
															name="aId2" id="aId2" value="">
													</div>
												</div>
											</div>
											</div>
										</div>
									</div>
									<!-- END BORDERED TABLE PORTLET-->
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>



		</div>
		<!-- END CONTENT -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<jsp:include page="../footer.jsp" />
	<!-- BEGIN CORE PLUGINS -->
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
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/global/plugins/jstree/dist/jstree.min.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script src="../assets/admin/pages/scripts/ui-tree.js"></script>
	<script src="../assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/quick-sidebar.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/demo.js"
		type="text/javascript"></script>
	
	<script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>	
	<!--  -->
<!-- 	<script src="../scripts/account/account.js" type="text/javascript"></script> -->
	<script src="../scripts/navigationbar-action.js" type="text/javascript"></script>
	<script src="../scripts/account/account-events.js" type="text/javascript"></script>
	<!-- begin 省市级联js -->
	<script src="../scripts/jquery/region_select.js" type="text/javascript"></script>
	<!-- end -->

	<script>
					jQuery(document).ready(function() {
						// initiate layout and plugins
						Metronic.init(); // init metronic core components
			           	navigationBar.activeAccountMenu();
						AccountEvents.init();   
						Layout.init(); // init current layout
						QuickSidebar.init(); // init quick sidebar
						Demo.init(); // init demo features
						UITree.init();
					});
				</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>