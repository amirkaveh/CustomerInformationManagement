<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="margin-top-30" align="left">
        <h2> Customer Loan Saving Result </h2>
        <p > Result: <span id="result"> <c:out value="${result}"/> </span> </p>
        <p > Info: <span class="font-size-medium"> <c:out value="${info}"/> </span> </p>
    </div>
</div>

<script src="resources/jquery.min.js"></script>
<script>
    if ($('#result').text().trim()=="Fail") {
        $('#result').attr("class", "font-size-xlarge red");

    }else{
        $('#result').attr("class", "font-size-large green");

    }
</script>