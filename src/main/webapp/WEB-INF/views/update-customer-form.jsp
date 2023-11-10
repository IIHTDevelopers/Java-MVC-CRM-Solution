<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Customer</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded">CRM - Customer Relationship Manager</h2>
		<h3 class="text-center">Update Customer</h3>
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<p class="text-center"><i class="text-monospace text-info">Fill out the form. Asterisk <i class="fas fa-asterisk"></i> means required.</i></p>
				<hr>
				<form:form action="saveCustomer" modelAttribute="customer" method="POST">
					<!-- need to associate this data with customer id -->
					<form:hidden path="id"/>
					<div class="form-group">
						<label for="firstName">First Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:errors path="firstName" cssClass="text-danger"/>
						<form:input path="firstName" name="firstName" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:errors path="lastName" cssClass="text-danger"/>
						<form:input path="lastName" name="lastName" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="email">Email <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:errors path="email" cssClass="text-danger"/>
						<form:input path="email" name="email" class="form-control"/>
					</div>
					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/customer/list" class="badge badge-secondary">Back to List</a>
	</div>
	</body>
</html>