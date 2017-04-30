<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="margin-top-30" align="left">
        <h2 class="red"> Something went wrong.</h2>
        <strong><c:out value="${errorTitle}"/></strong>
        <c:out value="${info}"/>
    </div>
</div>