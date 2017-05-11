<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<link href="/css/app.css" rel="stylesheet" type="text/css"/>
<body class="rest">
	<div>
		<c:choose>
			<c:when test="${not empty users}">
				<h2 align="center">Users list</h2>
				<div align="center">
					<table class="entityTable" border="1">
						<thead>
							<tr>
							    <th>id</th>
							    <th>User Name</th>
							    <th>Login</th>
							    <th>Password</th>
							    <th>Role</th>
							    <th>Delete</th>
							    <th>Update</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}" varStatus="loop">
								<tr>
								    <td><c:out value="${user.userId}"/></td>
								    <td><c:out value="${user.userName}"/></td>
								    <td><c:out value="${user.login}"/></td>
								    <td><c:out value="${user.password}"/></td>
								    <td><c:out value="${roles[loop.index]}"></c:out></td>
								    <td>
								    	<form action="/users/deleteUser" method="post">
								    		<input type="hidden" name="id" value="${user.userId}"/>
								    		<button class="buttonInsideTable" id="deleteButton" type="submit">Delete</button>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								    	</form>
								    </td>
								    <td>
								    	<form action="/addUser" method="get">
								    		<input type="hidden" name="id" value="${user.userId}"/>
								    		<input type="hidden" name="username" value="${user.userName}"/>
								    		<input type="hidden" name="login" value="${user.login}"/>	
								    		<input type="hidden" name="password" value="${user.password}"/>	
								    		<input type="hidden" name="role" value="${roles[loop.index]}"/>		
								    		<button class="buttonInsideTable" id="updateButton" type="submit">Update</button>	
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>				    	
								    	</form>
								    </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<h2>Sorry, but users list is empty</h2>
			</c:otherwise>
		</c:choose>
		<div class="buttonsContainer" align="center">
			<table class="buttonsTable">
				<tr>
					<td>
						<form action="/menu" method="get">
							<button id="backButton" type="submit">Back to menu</button>
						</form>
					</td>
					<td>
						<form action="/users" method="get">
							<button id="showButton" type="submit">Show all</button>
						</form>
					</td>
					<td>
						<form action="/addUser" method="get">
							<button id="addButton" type="submit">Add/Update</button>
						</form>
					</td>
				</tr>
			</table>
			<div class="findContainer" align="center">
				<form id="findForm" action="/users/getUserBy" method="post" onSubmit="findBy()">
					<input id="findInput" type="number" placeholder="Find user by" name=""></input>
					<select id="select" onchange="control()">
						<option value="id">Id</option> 
						<option value="username">UserName</option>
						<option value="login">Login</option>
					</select>
					<button id="findButton" type="submit">Find</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
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