package logic;

import crud.LoanTypeCRUD;
import exception.DatabaseQueryException;
import model.GrantConditionModel;
import model.LoanTypeModel;

import java.util.Set;

/**
 * Created by $Hamid on 4/30/2017.
 */
public class LoanTypeLogic {
    public static void saveLoanTypeAndGrants(String loanTypeName, double interestRate, Set<GrantConditionModel> grantConditionSet) throws DatabaseQueryException {
        LoanTypeModel loanType = new LoanTypeModel(loanTypeName, interestRate, grantConditionSet);
        LoanTypeCRUD.insert(loanType);
    }
}
