<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<body>
	<div class="container-fluid">
		
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 100px"></div>
		</div>
		
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4" style="height: 250px">
	
				<h2 class="text-center">Menu</h2>
	
				<form class="form-group" action="/books" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Show books</button>
				</form>
	
				<form class="form-group" action="/users" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Show users</button>
				</form>
	
				<form class="form-group" action="/logout" method="post">
					<input class="btn btn-md btn-primary btn-block" type="submit" value="Logout"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
	
			</div>
		</div>
	
	</div>
</body>

</html>