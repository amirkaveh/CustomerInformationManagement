<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="container margin-top-30">
        <h2>File loan for natural customers</h2>
        <p class="font-size-medium"> Enter a customer ID to grant a loan.</p>
    </div>
    <div class="container col-md-10 col-sm-11">
        <form class="form-horizontal col-sm-6" id="customerForm" action="fileNaturalPersonLoan" method="post">
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-sm-4" for="customerID">Customer ID:</label>
                    <div class="col-sm-7">
                        <input required type="number" class="form-control" name="customerID" id="customerID"
                               placeholder="Enter Customer ID">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-7">
                        <input type="submit" class="btn btn-success col-xs-12" id="customerFormButton"
                               value="Load Customer"/>
                    </div>
                </div>
            </div>
        </form>
        <div class="form-horizontal col-sm-6">
            <div class="row">
                <div class="form-group">
                    <label class="control-label col-sm-4" for="name">Name:</label>
                    <div class="col-sm-7">
                        <input type="text" disabled class="form-control" name="name"
                               id="name" value="">
                        <%--<div id="name"></div>--%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4" for="family">Family:</label>
                    <div class="col-sm-7">
                        <input type="text" disabled class="form-control" name="family"
                               id="family">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container margin-top-30 col-md-10 col-sm-11" hidden id="loanTypeForm">
        <form class="form-horizontal" id="loanForm" method="post" action="resultFilingNaturalPersonLoan">
            <div class="col-sm-6">
                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="loanType">Loan Type:</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="loanType"
                                    id="loanType">
                                <c:forEach var="counter" begin="0" end="${loanTypeNameList.size()-1}">
                                    <option id="<c:out value="${loanTypeIDList.get(counter)}"/>">
                                        ID: <c:out value="${loanTypeIDList.get(counter)}"/>, Name: <c:out value="${loanTypeNameList.get(counter)}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="contractDuration">Contract Duration:</label>
                        <div class="col-sm-7">
                            <input required type="number" min="0" step="1" class="form-control" name="contractDuration"
                                   id="contractDuration"
                                   placeholder="Enter Contract Duration">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="row">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="contractAmount">Contract Amount:</label>
                        <div class="col-sm-7">
                            <input required type="number" min="0" step="0.01" class="form-control" name="contractAmount"
                                   id="contractAmount"
                                   placeholder="Enter Contract Amount">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-7">
                            <button type="submit" class="btn btn-success col-xs-12">Grant Loan</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="resources/jquery.min.js"></script>
<script src="natural-loan-file.js"></script>
