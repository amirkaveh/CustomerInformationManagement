package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by $Hamid on 4/18/2017.
 */
@Entity
@Table(name="grant_conditions")
public class GrantCondition {
    @Id @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name = "grant_name")
    private String name;
    @Column(name = "min_contract_duration")
    private Date minContractDuration;
    @Column(name = "max_contract_duration")
    private Date maxContractDuration;
    @Column(name = "min_contract_amount")
    private BigDecimal minContractAmount;
    @Column(name = "max_contract_amount")
    private BigDecimal maxContractAmount;

    public GrantCondition(){}

    public GrantCondition(String name, Date minContractDuration, Date maxContractDuration, BigDecimal minContractAmount, BigDecimal maxContractAmount) {
        this.name = name;
        this.minContractDuration = minContractDuration;
        this.maxContractDuration = maxContractDuration;
        this.minContractAmount = minContractAmount;
        this.maxContractAmount = maxContractAmount;
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

    public Date getMinContractDuration() {
        return minContractDuration;
    }

    public void setMinContractDuration(Date minContractDuration) {
        this.minContractDuration = minContractDuration;
    }

    public Date getMaxContractDuration() {
        return maxContractDuration;
    }

    public void setMaxContractDuration(Date maxContractDuration) {
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
