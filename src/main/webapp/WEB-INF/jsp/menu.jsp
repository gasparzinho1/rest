<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	<!-- <link href="/css/app.css" rel="stylesheet" type="text/css"/>-->
	<link href="/css/app.css" rel="stylesheet" type="text/css"/>
	<body class="rest">
		<div class="container" align="center">
			<h2>Menu</h2>
			<form class="centralForm" action="/books" method="get">
				<button type="submit">Show books</button>
			</form>
			<form action="/users" method="get">
				<button type="submit">Show users</button>
			</form>
			<form action="/logout" method="post">
				<input type="submit" value="Logout"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
	</body>
</html>