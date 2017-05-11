<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>

<html>
	<link href="/css/app.css" rel="stylesheet" type="text/css"/>
	<body class="rest">
		<div class="container" align="center">
			<h2>Add or update user</h2>
			<form action="users/addUser" method="post">
				<c:choose>
					<c:when test="${not empty param.id}">
						<input type="hidden" name="id" value="${param.id}"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="id" value="0"/>
					</c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td><label>User name:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.username}">
									<input type="text" name="username" value="${param.username}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="text" name="username" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><label>Login:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.login}">
									<input type="text" name="login" value="${param.login}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="text" name="login" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.password}">
									<input type="text" name="password" value="${param.password}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="text" name="password" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><label>Role:</label></td>
						<td>
							<table>
								<tr>
									<td><label><input type="radio" id="userRole" name="role" value="ROLE_USER">User</label></td>
									<td><label><input type="radio" id="adminRole" name="role" value="ROLE_ADMIN">Admin</label></td>
									<c:set var="role" value="${param.role}"/>
									<c:choose>
										<c:when test="${fn:contains(role, 'admin')}">
											<script>document.getElementById('adminRole').checked = true;</script>
										</c:when>
										<c:otherwise>
											<script>document.getElementById('userRole').checked = true;</script>
										</c:otherwise>
									</c:choose>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<div>
					<input type="hidden" id="flag" name="update" value=""/>
					<input type="submit" id="btn" value=""/>
					<c:choose>
						<c:when test="${not empty param.id}">
							<script>
								document.getElementById('flag').value = "true";
								document.getElementById('btn').value = "Update";
							</script>
						</c:when>
						<c:otherwise>
							<script>
								document.getElementById('flag').value = "false";
								document.getElementById('btn').value = "Add";
							</script>
						</c:otherwise>
					</c:choose>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<form action="users" method="get">
				<button type="submit">Back</button>
			</form>
		</div>
	</body>
</html>