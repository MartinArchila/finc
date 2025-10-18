package com.finc.src.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget_preferences")
public class BudgetPreferences {
    
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @Column (name="necessities_percent",nullable = false)
    private Double necessities_percent;

    @Column (name = "wants_percent", nullable = false)
    private Double wants_percent;

    @Column (name = "savings_debt_percent", nullable = false)
    private Double savings_debt_percent;

    public Double getNecessities_percent() {
        return necessities_percent;
    }

    public void setNecessities_percent(Double necessities_percent) {
        this.necessities_percent = necessities_percent;
    }

    public Double getWants_percent() {
        return wants_percent;
    }

    public void setWants_percent(Double wants_percent) {
        this.wants_percent = wants_percent;
    }

    public Double getSavings_debt_percent() {
        return savings_debt_percent;
    }

    public void setSavings_debt_percent(Double savings_debt_percent) {
        this.savings_debt_percent = savings_debt_percent;
    }

    @Override
    public String toString() {
        return "BudgetPreferences [id=" + id + ", user_id=" + user.getId() + ", necessities_percent=" + necessities_percent
                + ", wants_percent=" + wants_percent + ", savings_debt_percent=" + savings_debt_percent + "]";
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @JsonIgnore
    public Users getUser(){
        return user;
    }

    public BudgetPreferences(){}

    public BudgetPreferences(Users user, Double necessities_percent, Double wants_percent,
            Double savings_debt_percent) {
        this.user = user;
        this.necessities_percent = necessities_percent;
        this.wants_percent = wants_percent;
        this.savings_debt_percent = savings_debt_percent;
    }

    

}
