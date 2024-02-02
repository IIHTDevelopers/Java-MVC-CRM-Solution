<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Customer</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded">CRM - Customer Relationship Manager</h2>
		<h3 class="text-center">Add Customer</h3>
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<hr>
				<form:form action="saveCustomer" modelAttribute="customer" method="POST">
					<div class="form-group">
						<label for="firstName">First Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="firstName" name="firstName" class="form-control"/>
						<form:errors path="firstName" cssClass="text-danger"/>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="lastName" name="lastName" class="form-control"/>
						<form:errors path="lastName" cssClass="text-danger"/>
					</div>
					<div class="form-group">
						<label for="email">Email <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="email" name="email" class="form-control"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/customer/list" class="badge badge-secondary">Back to List</a>
	</div>
	</body>
</html>