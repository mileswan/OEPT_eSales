<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="scripts/user-authentication.js" type="text/javascript"></script>
</head>
<body>
<h1>Hello index page!</h1>
<h1>
<%-- <spring:message code="main.title" /> --%>
<%
	String code = request.getParameter("code");
	if(code != null){
		if("en".equals(code)){
			session.setAttribute("locale", Locale.US);
		}else if("zh".equals(code)){
			session.setAttribute("locale", Locale.CHINESE);
		}
	}
%>
<c:if test="${sessionScope.locale != null }">
	<fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="language/messages" />
<fmt:message key="main.title" />
<%= request.getLocale() %>
</h1>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">用户名</label> 
			<input
				class="form-control form-control-solid placeholder-no-fix"
				type="text" autocomplete="off" placeholder="用户名" name="username" id="username"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label> 
			<input
				class="form-control form-control-solid placeholder-no-fix"
				type="password" autocomplete="off" placeholder="密码" name="password" id="password"/>
		</div>
		<div class="form-actions">
			<button class="btn btn-primary btn-block uppercase">登陆</button>
		</div>
	<br>
	<a href="index.jsp?code=en">English</a>
	<a href="index.jsp?code=zh">中文</a>
	<p id="response"></p>
	<br>
	<label>First name: </label><p id="firstname"></p>
	<label>Last name: </label><p id="lastname"></p>
<script>   
jQuery(document).ready(function() {  
	$("button").click(function(){
		UserAuthentication.post(); // test post operation
	})
});
</script>
</body>
</html>