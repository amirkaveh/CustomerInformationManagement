package model;

import java.util.Date;

/**
 * Created by $Hamid on 3/12/2017.
 */
public class LegalPersonCustomer extends Customer {
    private String name;
    private Date registrationDate;
    private Long economicalID;

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
