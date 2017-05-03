package logic;

import crud.CustomerCRUD;
import crud.LegalPersonCRUD;
import crud.NaturalPersonCRUD;
import exception.DatabaseQueryException;
import exception.InputDataException;
import model.CustomerModel;
import model.LegalPersonCustomerModel;
import model.NaturalPersonCustomerModel;

import java.util.List;

/**
 * Created by $Hamid on 4/29/2017.
 */
public class NaturalPersonLogic {
    static public void addPerson(NaturalPersonCustomerModel naturalPerson) throws InputDataException, DatabaseQueryException {
        validateInput(naturalPerson);
        CustomerModel customer = new CustomerModel();
//        customer.setType(CustomerModel.CustomerType.naturalPerson);
        CustomerCRUD.insert(customer);
        naturalPerson.setCustomerID(customer.getCustomerID());
        try {
            NaturalPersonCRUD.insert(naturalPerson);
        } catch (DatabaseQueryException e){
            CustomerCRUD.delete(customer);
            throw e;
        }
    }

    static public void updatePerson(NaturalPersonCustomerModel naturalPerson) throws InputDataException, DatabaseQueryException {
        validateInput(naturalPerson);
        NaturalPersonCRUD.update(naturalPerson);
    }

    private static void validateInput(NaturalPersonCustomerModel naturalPerson) throws InputDataException {
        if( naturalPerson.getName()==null || naturalPerson.getName().equals("") || naturalPerson.getFamily()==null
                || naturalPerson.getFamily().equals("") || naturalPerson.getFatherName()==null
                || naturalPerson.getFatherName().equals("") || naturalPerson.getBirthDate()==null
                || naturalPerson.getNationalID()==null)
            throw new InputDataException();
    }
    public static void deletePerson(Integer customerID) throws DatabaseQueryException {
        //TODO: bring related logic operation in CRUD file to this part
        NaturalPersonCRUD.delete(customerID);
    }

    public static NaturalPersonCustomerModel getPersonByCustomerID(Integer customerID) throws DatabaseQueryException {
        NaturalPersonCustomerModel searchPerson = new NaturalPersonCustomerModel();
        searchPerson.setCustomerID(customerID);
        return NaturalPersonCRUD.search(searchPerson).get(0);
    }

    static public List<NaturalPersonCustomerModel> searchPerson(NaturalPersonCustomerModel naturalPerson) throws DatabaseQueryException {
        if (naturalPerson.getName().equals("")){
            naturalPerson.setName(null);
        }
        if (naturalPerson.getFamily().equals("")){
            naturalPerson.setFamily(null);
        }
        return NaturalPersonCRUD.search(naturalPerson);
    }
}
