<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <h2>Search Results</h2>
    <p>Search result based on your input parameters are listed here:</p>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Customer ID</th>
            <th>Company Name</th>
            <th>Economical ID</th>
            <th>Registration Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${legalPersons}" var="legalPerson">
            <tr>
                <td><c:out value="${legalPerson.getCustomerID()}"/></td>
                <td><c:out value="${legalPerson.getName()}"/></td>
                <td><c:out value="${legalPerson.getEconomicalID()}"/></td>
                <td><c:out value="${legalPerson.getRegistrationDate()}"/></td>
                <td>
                    <form action="editLegalCustomer" method="get">
                        <input type="hidden" name="customerID" value="<c:out value="${legalPerson.getCustomerID()}"/>">
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-edit"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="deleteLegalCustomer" method="get">
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