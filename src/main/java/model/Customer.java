package model;

/**
 * Created by $Hamid on 3/12/2017.
 */
public class Customer {

    private Integer customerID;
    private CustomerType type;

    public enum CustomerType {
        naturalPerson("natural-person"),
        legalPerson("legal-person");
        private String value;

        CustomerType(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public Integer getCustomerID() {
        return customerID;
    }

//    TODO: get use of boolean return value in used cases if necessary
    public Boolean setCustomerID(Integer customerID) {
        if (this.customerID == null) {
            this.customerID = customerID;
            return true;
        }
        return false;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
