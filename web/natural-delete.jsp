<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="margin-top-30" align="left">
        <h2> Delete natural person customer</h2>
        <p class="font-size-medium"> Do you really want to delete this customer?</p>
        <form class="form-horizontal" action="deleteNaturalCustomer" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="customerID">Customer ID:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="customerID"><c:out value="${naturalPerson.getCustomerID()}"/></p>
                    <input type="hidden" name="customerID" value="<c:out value="${naturalPerson.getCustomerID()}"/>">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="name"><c:out value="${naturalPerson.getName()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="family">Family:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="family"><c:out value="${naturalPerson.getFamily()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="father">Father name:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="father"><c:out value="${naturalPerson.getFatherName()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="birth">Birth date:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="birth"><c:out value="${naturalPerson.getBirthDate()}"/></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="nationalID">National ID:</label>
                <div class="col-sm-7">
                    <p class="form-control-static" id="nationalID"><c:out value="${naturalPerson.getNationalID()}"/></p>
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