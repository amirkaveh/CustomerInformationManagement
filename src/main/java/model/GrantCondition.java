package model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by $Hamid on 4/18/2017.
 */
@Entity
@Table(name="grant_conditions")
public class GrantCondition {
    @SequenceGenerator(name = "grant_condition_id_generator", sequenceName = "seq2", allocationSize = 1)
    @Id @GeneratedValue(generator = "grant_condition_id_generator")
    @Column(name="grant_condition_id")
    private int id;
    @Column(name = "grant_name")
    private String name;
    @Column(name = "min_contract_duration")
    private Integer minContractDuration;
    @Column(name = "max_contract_duration")
    private Integer maxContractDuration;
    @Column(name = "min_contract_amount")
    private BigDecimal minContractAmount;
    @Column(name = "max_contract_amount")
    private BigDecimal maxContractAmount;

    @ManyToOne
    @JoinColumn(name = "loan_type_id",referencedColumnName = "loan_type_id")
    private LoanType loanType;

    public GrantCondition(){}

    public GrantCondition(String name, Integer minContractDuration, Integer maxContractDuration, BigDecimal minContractAmount, BigDecimal maxContractAmount) {
        this.name = name;
        this.minContractDuration = minContractDuration;
        this.maxContractDuration = maxContractDuration;
        this.minContractAmount = minContractAmount;
        this.maxContractAmount = maxContractAmount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinContractDuration() {
        return minContractDuration;
    }

    public void setMinContractDuration(Integer minContractDuration) {
        this.minContractDuration = minContractDuration;
    }

    public Integer getMaxContractDuration() {
        return maxContractDuration;
    }

    public void setMaxContractDuration(Integer maxContractDuration) {
        this.maxContractDuration = maxContractDuration;
    }

    public BigDecimal getMinContractAmount() {
        return minContractAmount;
    }

    public void setMinContractAmount(BigDecimal minContractAmount) {
        this.minContractAmount = minContractAmount;
    }

    public BigDecimal getMaxContractAmount() {
        return maxContractAmount;
    }

    public void setMaxContractAmount(BigDecimal maxContractAmount) {
        this.maxContractAmount = maxContractAmount;
    }
}
