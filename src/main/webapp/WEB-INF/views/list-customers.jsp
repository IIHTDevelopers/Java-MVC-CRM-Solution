<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded"><a href="${pageContext.request.contextPath}/customer/list" class="text-white headerLink">CRM - Customer Relationship Manager</a></h2>

		<!-- put button: Add Customer -->
		<input type="button" class="btn btn-outline-info" value="Add Customer" onclick="window.location.href='addCustomerForm'; return false;"/>
		<!--  add a search box -->
        <form:form action="search" method="POST">
			    <input type="text" placeholder="Search Customer Here" name="theSearchName">
			    <input type="submit" value="Search" />
        </form:form>
			<!-- add html table here -->
		<table border="1">
			<tr>
				<th>Sno.</th>
				<th>First Name
				   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=firstName,desc"> Desc </a>
                   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=firstName"> Asc </a>
				</th>
				<th>Last Name
				   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=lastName,desc"> Desc </a>
                   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=lastName"> Asc </a>
				</th>
				<th>Email
				   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=email,desc"> Desc </a>
                   &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=email"> Asc </a>
				</th>
				<th colspan="2">Action</th>
				<th>Customer Verified</th>
			</tr>
			<!-- loop over and print customers -->
			<c:set var="index" value="${page * 5 + 1}" />
			<c:forEach var="customer" items="${customers}">
				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/customer/updateCustomerForm">
					<c:param name="customerId" value="${customer.id}"></c:param>
				</c:url>

				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${customer.id}"></c:param>
				</c:url>
                <c:url var="verifyUser" value="/customer/updateVerified">
            		<c:param name="id" value="${customer.id}"/>
            	</c:url>
				<tr>
					<td>${index}</td>
					<td>${customer.firstName}</td>
					<td>${customer.lastName}</td>
					<td>${customer.email}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" class="btn btn-danger btn-sm" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
					</td>

                    <td>
                            <c:if test = "${customer.verify eq true}">
                                     Verified User
                            </c:if>
                            <c:if test = "${customer.verify eq false}">
                                  <a href="${verifyUser}">Verify Now</a>
                            </c:if>
					</td>
				</tr>
				<c:set var="index" value="${index + 1}" />
			</c:forEach>
		</table>
	</div>
<br><br>
        	<c:choose>
                <c:when test="${totalPage == 0}">
                    No Record Found
                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                            &nbsp &nbsp<a href="/list?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
	</body>
</html>