<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>

<html>

<link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<body>

	<div class="container-fluid">
	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 100px"></div>
		</div>
	
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4" style="height: 250px">
				
				<c:choose>
					<c:when test="${param.id ne null}">
						<h2 class="text-center">Edit user</h2>
						<form action="/users/addUser" method="post">
				
							<input type="hidden" id="flag" name="update" value="true"/>
							<input type="hidden" name="id" value="${param.id}"/>
								
							<div class="form-group">
								<label>User name</label>
								<input class="form-control" type="text" name="username" required="required" value="${param.username}"/>
							</div>
					
							<div class="form-group">
								<label>Login</label>
								<input class="form-control" type="text" name="login" required="required" value="${param.login}"/>
							</div>
						
							<div class="form-group">
								<label>Password</label>
								<input class="form-control" type="text" step="0.01" name="password" required="required" value="${param.password}"/>			
							</div>
							
							<div class="form-group">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<label>Role:</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		  							<label><input type="radio" id="userRole" name="role" value="ROLE_USER">User</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		  							<label><input type="radio" id="adminRole" name="role" value="ROLE_ADMIN">Admin</label>
								</div>
								<c:set var="role" value="${param.role}"/>
									<c:choose>
										<c:when test="${fn:contains(role, 'admin')}">
											<script>document.getElementById('adminRole').checked = true;</script>
										</c:when>
										<c:otherwise>
											<script>document.getElementById('userRole').checked = true;</script>
										</c:otherwise>
									</c:choose>
							</div>
					
							<div class="form-group">
								<button class="btn btn-md btn-primary btn-block" type="submit">Update</button>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</c:when>
					<c:otherwise>
						<h2 class="text-center">Add user</h2>
						<form action="/users/addUser" method="post">
				
							<input type="hidden" id="flag" name="update" value="false"/>
							<input type="hidden" name="id" value="0"/>
								
							<div class="form-group">
								<label>User name</label>
								<input class="form-control" type="text" name="username" required="required"/>
							</div>
					
							<div class="form-group">
								<label>Login</label>
								<input class="form-control" type="text" name="login" required="required"/>
							</div>
						
							<div class="form-group">
								<label>Password</label>
								<input class="form-control" type="text" step="0.01" name="password" required="required"/>			
							</div>
							
							<div class="form-group">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<label>Role:</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		  							<label><input type="radio" id="userRole" name="role" value="ROLE_USER">User</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		  							<label><input type="radio" id="adminRole" name="role" value="ROLE_ADMIN">Admin</label>
								</div>
								<c:set var="role" value="${param.role}"/>
									<c:choose>
										<c:when test="${fn:contains(role, 'admin')}">
											<script>document.getElementById('adminRole').checked = true;</script>
										</c:when>
										<c:otherwise>
											<script>document.getElementById('userRole').checked = true;</script>
										</c:otherwise>
									</c:choose>
							</div>
					
							<div class="form-group">
								<button class="btn btn-md btn-primary btn-block" type="submit">Add</button>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
				
				<form class="form-group" action="users" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Back</button>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>