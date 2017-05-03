package model;

/**
 * Created by $Hamid on 3/12/2017.
 */
public class CustomerModel {

    private Integer customerID;

    //DONE: delete this redundant variable from class and database
//    private CustomerType type;

//    public enum CustomerType {
//        naturalPerson("natural-person"),
//        legalPerson("legal-person");
//        private String value;
//
//        CustomerType(String value) {
//            this.value = value;
//        }
//
//        private String getValue() {
//            return value;
//        }
//
//        @Override
//        public String toString() {
//            return this.getValue();
//        }
//    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Boolean setCustomerID(Integer customerID) {
        if (this.customerID == null) {
            this.customerID = customerID;
            return true;
        }
        return false;
    }

}
