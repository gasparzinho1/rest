<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<h2 class="text-center">Edit book</h2>
						<form action="/books/addBook" method="post">

							<input type="hidden" id="flag" name="update" value="true"/>
							<input type="hidden" name="id" value="${param.id}"/>
						
							<div class="form-group">
								<label>Author</label>
								<input class="form-control" type="text" name="author" required="required" value="${param.author}"/>
							</div>
					
							<div class="form-group">
								<label>Name</label>
								<input class="form-control" type="text" name="name" required="required" value="${param.name}"/>
							</div>
						
							<div class="form-group">
								<label>Price</label>
								<input class="form-control" type="number" step="0.01" name="price" required="required" value="${param.price}"/>			
							</div>
					
							<div class="form-group">
								<button class="btn btn-md btn-primary btn-block" type="submit">Update</button>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</c:when>
					<c:otherwise>
						<h2 class="text-center">Add book</h2>
						<form action="/books/addBook" method="post">
						
							<input type="hidden" id="flag" name="update" value="false"/>
							<input type="hidden" name="id" value="0"/>
							
							<div class="form-group">
								<label>Author</label>
								<input class="form-control" type="text" name="author" required="required"/>
							</div>
					
							<div class="form-group">
								<label>Name</label>
								<input class="form-control" type="text" name="name" required="required"/>
							</div>
						
							<div class="form-group">
								<label>Price</label>
								<input class="form-control" type="number" step="0.01" name="price" required="required"/>			
							</div>
					
							<div class="form-group">
								<button class="btn btn-md btn-primary btn-block" type="submit">Add</button>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
				
				<form class="form-group" action="books" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Back</button>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>