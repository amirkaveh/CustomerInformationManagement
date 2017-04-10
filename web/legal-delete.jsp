<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="margin-top-30" align="left">
        <h2> Delete legal person customer</h2>
        <p class="font-size-medium"> Do you really want to delete this customer?</p>
        <form class="form-horizontal" action="deleteLegalPerson" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="customerID">Customer ID:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="customerID"><c:out value="${legalPerson.getCustomerID()}"/></p>
                    <input type="hidden" name="customerID" value="<c:out value="${legalPerson.getCustomerID()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Company name:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="name"><c:out value="${legalPerson.getName()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="registration">Birth date:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="registration"><c:out value="${legalPerson.getRegistrationDate()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="economicalID">National ID:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="economicalID"><c:out value="${legalPerson.getEconomicalID()}"/></p>
                </div>
            </div>


            <div class="form-group col-xs-12 col-sm-4 ">
                <div class="row">
                    <div class="col-sm-6 col-xs-12">
                        <button type="submit" name="delete" value="Delete Button" class="btn btn-danger col-xs-12">
                            Delete
                        </button>
                    </div>
                    <div class="col-sm-6 col-xs-12">
                        <button type="submit" name="cancel" value="Cancel Button" class="btn btn-success col-xs-12">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>