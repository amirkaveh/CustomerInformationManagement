<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="margin-top-30" align="left">
        <h2> Edit legal person customer</h2>
        <p class="font-size-medium"> Edit any part of the information you want to change, left others as they are:</p>
        <form class="form-horizontal" action="editLegalCustomer" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="customerID">Customer ID:</label>
                <div class="col-sm-3">
                    <p class="form-control-static" id="customerID"><c:out value="${legalPerson.getCustomerID()}"/></p>
                    <input type="hidden" name="customerID" value="<c:out value="${legalPerson.getCustomerID()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Company name:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" required name="name" id="name" placeholder="Enter company name" value="<c:out value="${legalPerson.getName()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="registration">Registration date:</label>
                <div class="col-sm-3">
                    <input type="date" class="form-control" required name="registration" id="registration" placeholder="Enter registration date" value="<c:out value="${legalPerson.getRegistrationDate()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="economicalID">Economical ID:</label>
                <div class="col-sm-3">
                    <input type="number" class="form-control" required name="economicalID" id="economicalID" placeholder="Enter economical ID" value="<c:out value="${legalPerson.getEconomicalID()}"/>">
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