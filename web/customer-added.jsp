<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
        <div class="col-sm-9 alert alert-success alert-dismissable fade in">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success:</strong> The new customer added successfully. The customer ID is: #<c:out value="${customerID}"/>
        </div>
    </div>
</div>