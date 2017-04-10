<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <h2>Search Results</h2>
    <p>Search result based on your input parameters are listed here:</p>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>National ID</th>
            <th>Father Name</th>
            <th>Birth Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${naturalPersons}" var="legalPerson">
            <tr>
                <td><c:out value="${legalPerson.getCustomerID()}"/></td>
                <td><c:out value="${legalPerson.getName()}"/></td>
                <td><c:out value="${legalPerson.getFamily()}"/></td>
                <td><c:out value="${legalPerson.getNationalID()}"/></td>
                <td><c:out value="${legalPerson.getFatherName()}"/></td>
                <td><c:out value="${legalPerson.getBirthDate()}"/></td>
                <td>
                    <form action="editNaturalPerson" method="get">
                        <input type="hidden" name="customerID" value="<c:out value="${legalPerson.getCustomerID()}"/>">
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-edit"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="deleteNaturalPerson" method="get">
                        <input type="hidden" name="customerID" value="<c:out value="${legalPerson.getCustomerID()}"/>">
                        <button type="submit" class="btn btn-danger">
                            <i class="glyphicon glyphicon-remove"></i>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>