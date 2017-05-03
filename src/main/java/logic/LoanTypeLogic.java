package logic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import crud.LoanTypeCRUD;
import exception.DatabaseQueryException;
import javafx.util.Pair;
import model.GrantConditionModel;
import model.LoanTypeModel;
import utils.MathUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by $Hamid on 4/30/2017.
 */
public class LoanTypeLogic {
    public static void saveLoanTypeAndGrants(String loanTypeName, double interestRate, Set<GrantConditionModel> grantConditionSet) throws DatabaseQueryException {
        LoanTypeModel loanType = new LoanTypeModel(loanTypeName, interestRate, grantConditionSet);
        LoanTypeCRUD.insert(loanType);
    }
    public static Pair<List<String>,List<Integer>> getListOfNameAndID() throws DatabaseQueryException {
        List<String> nameList = new ArrayList<>();
        List<Integer> iDList = new ArrayList<>();
        List<LoanTypeModel> loanTypeModelList = LoanTypeCRUD.getLoanTypeList();
        for(LoanTypeModel loanType: loanTypeModelList){
            nameList.add(loanType.getName());
            iDList.add(loanType.getId());
        }
        return new Pair<>(nameList,iDList);
    }

    public static Boolean checkLoanCriteria(Integer loanTypeID, Integer contractDuration, BigDecimal contractAmount) throws DatabaseQueryException {
        LoanTypeModel loanType = LoanTypeCRUD.getLoanType(loanTypeID);
        for(GrantConditionModel grantCondition: loanType.getGrantConditionModels()){
            if(checkGrantCriteria(grantCondition,contractDuration,contractAmount)){
                return true;
            }
        }
        return false;
    }

    private static Boolean checkGrantCriteria(GrantConditionModel grantCondition, Integer contractDuration, BigDecimal contractAmount){
        return MathUtils.between(contractDuration, grantCondition.getMinContractDuration(), grantCondition.getMaxContractDuration())
                && MathUtils.between(contractAmount, grantCondition.getMinContractAmount(), grantCondition.getMaxContractAmount());
    }
}
