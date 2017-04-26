package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by $Hamid on 4/18/2017.
 */


@Entity
@Table(name = "loan_types")
public class LoanType {
    @SequenceGenerator(name = "loan_type_id_generator" , sequenceName = "seq1", allocationSize = 1)
    @Id @GeneratedValue(generator = "loan_type_id_generator")
    @Column(name = "loan_type_id")
    private Integer id;
    @Column(name = "loan_type_name")
    private String name;
    @Column(name = "interest_rate")
    private Double interestRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loanType", orphanRemoval = true)
    private Set<GrantCondition> grantConditions = new HashSet<>();

    public LoanType() {}

    public LoanType(String name, Double interestRate, Set<GrantCondition> grantConditions) {
        this.name = name;
        this.interestRate = interestRate;
        this.grantConditions = grantConditions;
        referGrantConditionsToThisLoanType(this.grantConditions);
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

    public Set<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
        referGrantConditionsToThisLoanType(this.grantConditions);
    }

    private void referGrantConditionsToThisLoanType(Set<GrantCondition> grantConditionSet){
        for (GrantCondition grantCondition: grantConditionSet){
            grantCondition.setLoanType(this);
        }
    }
}
