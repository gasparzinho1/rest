<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
	
<link rel='stylesheet' href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 100px"></div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5" style="height: 250px">
				<h2 class="text-center">Access denied</h2>
				<form class="form-group" action="/menu" method="get">
					<button class="btn btn-md btn-primary btn-block" type="submit">Back to menu</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>