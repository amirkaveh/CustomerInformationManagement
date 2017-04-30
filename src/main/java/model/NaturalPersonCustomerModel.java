package model;

import java.util.Date;

/**
 * Created by $Hamid on 3/12/2017.
 */
public class NaturalPersonCustomerModel extends CustomerModel {
    private String name;
    private String family;
    private String fatherName;
    private Date birthDate;
    private Long nationalID;

    public NaturalPersonCustomerModel() {
    }

    public NaturalPersonCustomerModel(Integer customerID, String name, String family, String fatherName, Date birthDate, Long nationalID) {
        this.name = name;
        this.family = family;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalID = nationalID;
        this.setCustomerID(customerID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getNationalID() {
        return nationalID;
    }

    public void setNationalID(Long nationalID) {
        this.nationalID = nationalID;
    }
}
