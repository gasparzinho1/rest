<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<link href="/css/app.css" rel="stylesheet" type="text/css"/>
	<body class="rest">
		<div class="container" align="center">
			<h2>New book</h2>
			<form action="/books/addBook" method="post">
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
						<td><label>Author:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.author}">
									<input type="text" name="author" value="${param.author}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="text" name="author" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><label>Name:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.name}">
									<input type="text" name="name" value="${param.name}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="text" name="name" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td><label>Price:</label></td>
						<td>
							<c:choose>
								<c:when test="${not empty param.price}">
									<input type="number" step="0.01" name="price" value="${param.price}" required="required"/>
								</c:when>
								<c:otherwise>
									<input type="number" step="0.01" name="price" required="required"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
				<div>
					<c:choose>
						<c:when test="${not empty param.id}">
							<button type="submit">Update</button>
						</c:when>
						<c:otherwise>
							<button type="submit">Add</button>
						</c:otherwise>
					</c:choose>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<form action="books" method="get">
				<button type="submit">Back</button>
			</form>
		</div>
	</body>
</html>