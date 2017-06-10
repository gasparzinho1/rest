<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<link href="/css/table.css" rel="stylesheet" type="text/css"/>
<body>
	
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 30px"></div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
				<form class="form-group" action="/menu" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Menu</button>
				</form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				<form class="form-group" action="/addUser" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Add user</button>
				</form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				<form class="form-group" action="/users" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Show all users</button>
				</form>
			</div>
			<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
				<form class="form-group" id="findForm" action="/users/getUserBy" method="get" onSubmit="findBy()">
					<div class="row">
						<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
							<input class="form-control" id="findInput" type="number" placeholder="Find user by" name="" required="required"></input>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
							<select class="form-control" id="select" onchange="control()">
								<option value="id">id</option> 
								<option value="username">User name</option>
								<option value="login">Login</option>
							</select>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<button class="btn btn-md btn-primary btn-block" type="submit">Find</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
			<c:choose>
				<c:when test="${not empty users}">
					<h2 class="text-center">Users</h2>
						<table class="table table-striped">
							<thead>
								<tr>
								    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">id</th>
								    <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3">User name</th>
								    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">Login</th>
								    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">Password</th>
								    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">Role</th>
								    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Update</th>
								    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${users}" varStatus="loop">
									<tr>
									    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1"><c:out value="${user.userId}"/></td>
									    <td class="col-lg-3 col-md-3 col-sm-3 col-xs-3"><c:out value="${user.userName}"/></td>
									    <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2"><c:out value="${user.login}"/></td>
									    <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2"><c:out value="${user.password}"/></td>
									    <td class="col-lg-2 col-md-2 col-sm-2 col-xs-2"><c:out value="${roles[loop.index]}"/></td>
									    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									    	<form action="/addUser" method="get">
									    		<input type="hidden" name="id" value="${user.userId}"/>
									    		<input type="hidden" name="username" value="${user.userName}"/>
									    		<input type="hidden" name="login" value="${user.login}"/>	
									    		<input type="hidden" name="password" value="${user.password}"/>	
								    			<input type="hidden" name="role" value="${roles[loop.index]}"/>			
									    		<button class="btn btn-xs btn-success" type="submit">Update</button>	
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>				    	
									    	</form>
									    </td>
									    <td class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
									    	<form action="/users/deleteUser" method="post">
									    		<input type="hidden" name="id" value="${user.userId}"/>
												<c:choose>
									    			<c:when test="${user.userId ne currentId}">
										    			<button class="btn btn-xs btn-danger" type="submit">Delete</button>
									    			</c:when>
									    			<c:otherwise>
									    				<button class="btn btn-xs btn-danger disabled" type="submit">Delete</button>
									    			</c:otherwise>
									    		</c:choose>
									    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									    	</form>
									    </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</c:when>
				<c:otherwise>
					<h2 class="text-center">Users list is empty</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
<script>
	function findBy(){
		var select = document.getElementById('select');
	    var val = select.options[select.selectedIndex].value;
	    document.getElementById('findForm').action = "/users/getUserBy"+val;
	    document.getElementById('findInput').name = val;
	}
	function control(){
		var select = document.getElementById('select');
		var val = select.options[select.selectedIndex].value;
		if (val == 'id') {
			document.getElementById('findInput').type = 'number';
		} else {
			document.getElementById('findInput').type = 'text';
		}
	}
</script>
	
</body>
</html>