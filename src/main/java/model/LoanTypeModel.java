package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by $Hamid on 4/18/2017.
 */


@Entity
@Table(name = "loan_types")
public class LoanTypeModel {
    @SequenceGenerator(name = "loan_type_id_generator" , sequenceName = "seq1", allocationSize = 1)
    @Id @GeneratedValue(generator = "loan_type_id_generator")
    @Column(name = "loan_type_id")
    private Integer id;
    @Column(name = "loan_type_name")
    private String name;
    @Column(name = "interest_rate")
    private Double interestRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loanTypeModel", orphanRemoval = true)
    private Set<GrantConditionModel> grantConditionModels = new HashSet<>();

    public LoanTypeModel() {}

    public LoanTypeModel(String name, Double interestRate, Set<GrantConditionModel> grantConditionModels) {
        this.name = name;
        this.interestRate = interestRate;
        this.grantConditionModels = grantConditionModels;
        referGrantConditionsToThisLoanType(this.grantConditionModels);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Set<GrantConditionModel> getGrantConditionModels() {
        return grantConditionModels;
    }

    public void setGrantConditionModels(Set<GrantConditionModel> grantConditionModels) {
        this.grantConditionModels = grantConditionModels;
        referGrantConditionsToThisLoanType(this.grantConditionModels);
    }

    private void referGrantConditionsToThisLoanType(Set<GrantConditionModel> grantConditionModelSet){
        for (GrantConditionModel grantConditionModel : grantConditionModelSet){
            grantConditionModel.setLoanTypeModel(this);
        }
    }
}
