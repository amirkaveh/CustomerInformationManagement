package logic;

import crud.CustomerCRUD;
import crud.LegalPersonCRUD;
import exception.DatabaseQueryException;
import exception.InputDataException;
import model.CustomerModel;
import model.LegalPersonCustomerModel;

import java.util.List;

/**
 * Created by $Hamid on 4/29/2017.
 */
public class LegalPersonLogic {
    static public void addPerson(LegalPersonCustomerModel legalPerson) throws InputDataException, DatabaseQueryException {
        validateInput(legalPerson);
        CustomerModel customer = new CustomerModel();
//        customer.setType(CustomerModel.CustomerType.legalPerson);
        CustomerCRUD.insert(customer);
        legalPerson.setCustomerID(customer.getCustomerID());
        try {
            LegalPersonCRUD.insert(legalPerson);
        }catch (DatabaseQueryException e){
            CustomerCRUD.delete(customer);
            throw e;
        }
    }

    static public List<LegalPersonCustomerModel> searchPerson(LegalPersonCustomerModel legalPerson) throws DatabaseQueryException {
        if (legalPerson.getName().equals("")){
            legalPerson.setName(null);
        }
        return LegalPersonCRUD.search(legalPerson);
    }

    static public void updatePerson(LegalPersonCustomerModel legalPerson) throws InputDataException, DatabaseQueryException {
        validateInput(legalPerson);
        LegalPersonCRUD.update(legalPerson);
    }

    private static void validateInput(LegalPersonCustomerModel legalPerson) throws InputDataException {
        if (legalPerson.getName() == null || legalPerson.getName().equals("") || legalPerson.getEconomicalID() == null
                || legalPerson.getRegistrationDate() == null)
            throw new InputDataException();
    }

    public static LegalPersonCustomerModel getPersonByCustomerID(Integer customerID) throws DatabaseQueryException {
        LegalPersonCustomerModel searchPerson = new LegalPersonCustomerModel();
        searchPerson.setCustomerID(customerID);
        return LegalPersonCRUD.search(searchPerson).get(0);
    }

    public static void deletePerson(Integer customerID) throws DatabaseQueryException {
        //TODO: bring related logic operation in CRUD file to this part
        LegalPersonCRUD.delete(customerID);
    }
}
