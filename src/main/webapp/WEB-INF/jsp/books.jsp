<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<link href="/css/app.css" rel="stylesheet" type="text/css"/>
<body class="rest">
	<div>
	<c:choose>
		<c:when test="${not empty books}">
			<h2 align="center">Books list</h2>
			<div align="center">
				<table class="entityTable" border="1">
					<thead>
						<tr>
						    <th>№</th>
						    <th>Author</th>
						    <th>Name</th>
						    <th>Price</th>
						    <th>Delete</th>
						    <th>Update</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${books}">
							<tr>
							    <td><c:out value="${book.bookId}"/></td>
							    <td><c:out value="${book.author}"/></td>
							    <td><c:out value="${book.name}"/></td>
							    <td><c:out value="${book.price}"/></td>
							    <td>
							    	<form action="/books/deleteBook" method="post">
							    		<input type="hidden" name="id" value="${book.bookId}"/>
							    		<button class="buttonInsideTable" id="deleteButton" type="submit">Delete</button>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							    	</form>
							    </td>
							    <td>
							    	<form action="/addBook" method="get">
							    		<input type="hidden" name="id" value="${book.bookId}"/>
							    		<input type="hidden" name="author" value="${book.author}"/>
							    		<input type="hidden" name="name" value="${book.name}"/>	
							    		<input type="hidden" name="price" value="${book.price}"/>		
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
			<h2>Sorry, but books list is empty</h2>
		</c:otherwise>
	</c:choose>
	</div>
	<div class="buttonsContainer" align="center">
		<table class="buttonsTable">
			<tr>
				<td>
					<form action="/menu" method="get">
						<button class="buttonInTable" id="backButton" type="submit">Menu</button>
					</form>
				</td>
				<td>
					<form action="/books" method="get">
						<button class="buttonInTable" id="showButton" type="submit">Show all books</button>
					</form>
				</td>
				<td>
					<form action="/addBook" method="get">
						<button class="buttonInTable" id="addButton" type="submit">Add book</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div class="findContainer" align="center">
		<form id="findForm" action="/books/getBookBy" method="post" onSubmit="findBy()">
			<input id="findInput" type="number" placeholder="Find book by" name=""></input>
			<select id="select" onchange="control()">
				<option value="id">Id</option> 
				<option value="author">Author</option>
				<option value="name">Name</option>
			</select>
			<button id="findButton" type="submit">Find</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
	
	<script>
		function findBy(){
		   var select = document.getElementById('select');
		   var val = select.options[select.selectedIndex].value;
		   document.getElementById('findForm').action = "/books/getBookBy"+val;
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