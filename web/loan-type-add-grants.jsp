<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="functions.js"></script>

<div class="container margin-top-30">
    <h2>Define grant conditions for the loan type:</h2>
    <div class="container margin-top-30">
        <div class="row">
            <p class="font-size-medium col-md-2 col-sm-3 col-xs-5"> Loan Type name: </p>
            <p class="font-size-medium col-md-10 col-sm-9 col-xs-7"><strong> <c:out value="${loanTypeName}"/> </strong></p>
        </div>
        <div class="row">
            <p class="font-size-medium col-md-2 col-sm-3 col-xs-5"> Interest Rate: </p>
            <p class="font-size-medium col-md-10 col-sm-9 col-xs-7"><strong> <c:out value="${interestRate}"/>
                % </strong></p>
        </div>
    </div>
</div>

<div class="container margin-top-30">
    <div class="form-horizontal">
        <div class="row">
            <div class="form-group col-md-6 col-sm-6">
                <label class="control-label col-sm-4" for="name">Name:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="name" id="name"
                           placeholder="Enter grant condition name">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6 col-sm-6">
                <label class="control-label col-sm-4" for="minContractDuration">Minimum Duration:</label>
                <div class="col-sm-7">
                    <input type="number" step="1" min="0" class="form-control" name="minContractDuration"
                           id="minContractDuration"
                           placeholder="Minimum duration in months">
                </div>
            </div>
            <div class="form-group col-md-6 col-sm-6">
                <label class="control-label col-sm-4" for="maxContractDuration">Maximum Duration:</label>
                <div class="col-sm-7">
                    <input type="number" step="1" min="0" class="form-control" name="maxContractDuration"
                           id="maxContractDuration"
                           placeholder="Maximum duration in months">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6 col-sm-6">
                <label class="control-label col-sm-4" for="minContractAmount">Minimum Amount:</label>
                <div class="col-sm-7">
                    <input type="number" step="0.01" min="0" class="form-control" name="minContractAmount"
                           id="minContractAmount"
                           placeholder="Minimum contract amount">
                </div>
            </div>
            <div class="form-group col-md-6 col-sm-6">
                <label class="control-label col-sm-4" for="maxContractAmount">Maximum Amount:</label>
                <div class="col-sm-7">
                    <input type="number" step="0.01" min="0" class="form-control" name="maxContractAmount"
                           id="maxContractAmount"
                           placeholder="Maximum contract amount">
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-6" style="padding-right: 40px">
                <button type="submit" class="btn btn-warning col-sm-offset-4 col-sm-7 col-xs-12" onclick="addRow()">
                    <i class="glyphicon glyphicon-plus"></i>
                    Add Grant Condition
                </button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <h3>Grant conditions table</h3>
    <form action="addGrantsToLoanType" method="POST" onsubmit="return grantTableNotEmpty()">
        <input type="hidden" name="loanTypeName" value="<c:out value="${loanTypeName}"/>">
        <input type="hidden" name="interestRate" value="<c:out value="${interestRate}"/>">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Grant Condition</th>
                <th>Min Duration</th>
                <th>Max Duration</th>
                <th>Min Amount</th>
                <th>Max Amount</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody id="tableBody">
            </tbody>
        </table>
        <div class="form-group">
            <div class="col-sm-12">
                <button type="submit" class="btn btn-success col-sm-offset-3 col-sm-6 col-xs-12">
                    <i class="glyphicon glyphicon-save"></i>
                    Save In Database
                </button>
            </div>
        </div>
    </form>
</div>