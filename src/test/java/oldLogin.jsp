<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<link href="/css/app.css" rel="stylesheet" type="text/css"/>
	<body class="rest">
		<div class="container" align="center">
			<h2>Login form</h2>
			<form  action="/login" method="post">
				<div>
					<p><label>Login:</label></p>
					<p><input id="loginInput" type="text" name="username" required="required"/></p>
				</div>
				<div>
					<p><label>Password:</label></p>
					<p><input id="passwordInput" type="password" name="password" required="required"/></p>
				</div>
				<div>
					<button type="submit">Login</button>
				</div>
				<c:if test="${param.error ne null}">
					<div class="alert-danger">Invalid username or password</div>
				</c:if>
				<c:if test="${param.logout ne null}">
					<div class="alert-normal">You have been logged out</div>
				</c:if>
				<div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
			</form>	
		</div>
	</body>
</html>