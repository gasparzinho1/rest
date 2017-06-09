<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 100px"></div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4" style="height: 250px">
				<form action="/login" method="post">
				
					<div class="form-group">
						<label for="inputLogin">Login</label>
					    <input type="text" name="username" id="inputLogin" class="form-control" placeholder="Login" required="required">
					</div>
					
					<div class="form-group">
						<label for="inputPassword">Password</label>
				    	<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
					</div>
					
					<div class="form-group">
						<button class="btn btn-md btn-primary btn-block" type="submit" >Log in</button>
				    </div>
					
					<div class="form-group">
					    <c:if test="${param.error ne null}">
							<div class="alert alert-danger text-center">
								<h4>Invalid login or password</h4>
							</div>
						</c:if>
						<c:if test="${param.logout ne null}">
							<div class="alert alert-success text-center">
								<h4>You have been logged out</h4>
							</div>
						</c:if>
					</div>
				
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
			    </form>
			</div>
		</div>
	</div>
</body>

</html>