<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="margin-top-30" align="left">
        <h2> Edit natural person customer</h2>
        <p class="font-size-medium"> Edit any part of the information you want to change, left others as they are:</p>
        <form class="form-horizontal" action="editNaturalCustomer" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="customerID">Customer ID:</label>
                <div class="col-sm-3">
                    <p class="form-control-static" id="customerID"><c:out value="${naturalPerson.getCustomerID()}"/></p>
                    <input type="hidden" name="customerID" value="<c:out value="${naturalPerson.getCustomerID()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" required name="name" id="name" placeholder="Enter name" value="<c:out value="${naturalPerson.getName()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="family">Family:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" required name="family" id="family" placeholder="Enter family" value="<c:out value="${naturalPerson.getFamily()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="father">Father name:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" required name="father" id="father" placeholder="Enter father name" value="<c:out value="${naturalPerson.getFatherName()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="birth">Birth date:</label>
                <div class="col-sm-3">
                    <input type="date" class="form-control" required name="birth" id="birth" placeholder="Enter birth date" value="<c:out value="${naturalPerson.getBirthDate()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="nationalID">National ID:</label>
                <div class="col-sm-3">
                    <input type="number" class="form-control" required name="nationalID" id="nationalID" placeholder="Enter national ID" value="<c:out value="${naturalPerson.getNationalID()}"/>">
                </div>
            </div>

            <div class="form-group col-xs-12 col-sm-5 ">
                <div class="row">
                    <div class="col-sm-6 col-xs-12">
                        <button type="submit" name="ok" value="OK Button" class="btn btn-warning col-xs-12">
                            OK
                        </button>
                    </div>
                    <div class="col-sm-6 col-xs-12">
                        <button type="submit" name="cancel" value="Cancel Button" class="btn btn-danger col-xs-12">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>