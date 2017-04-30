package model;

import java.util.Date;

/**
 * Created by $Hamid on 3/12/2017.
 */
public class LegalPersonCustomerModel extends CustomerModel {
    private String name;
    private Date registrationDate;
    private Long economicalID;

    public LegalPersonCustomerModel() {
    }

    public LegalPersonCustomerModel(Integer customerID, String name, Date registrationDate, Long economicalID) {
        this.name = name;
        this.registrationDate = registrationDate;
        this.economicalID = economicalID;
        this.setCustomerID(customerID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getEconomicalID() {
        return economicalID;
    }

    public void setEconomicalID(Long economicalID) {
        this.economicalID = economicalID;
    }
}
