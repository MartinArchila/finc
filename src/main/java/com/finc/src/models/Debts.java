package com.finc.src.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "debts")
public class Debts {
    
    @Id
    @GeneratedValue
    private UUID Id;

    @Column (name ="user_id", nullable = false)
    private UUID userId;

    @Column (name="name", nullable = false)
    private String name;

    @Column (name="total_amount", nullable = false)
    private BigDecimal total_amount;

    @Column (name ="minimum_payment",nullable = false)
    private BigDecimal min_payment;

    @Column (name="description", nullable = false)
    private String desc;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getMin_payment() {
        return min_payment;
    }

    public void setMin_payment(BigDecimal min_payment) {
        this.min_payment = min_payment;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Debts [Id=" + Id + ", userId=" + userId + ", name=" + name + ", total_amount=" + total_amount
                + ", min_payment=" + min_payment + ", desc=" + desc + "]";
    }

    public Debts(){}

    public Debts(UUID userId, String name, BigDecimal total_amount, BigDecimal min_payment, String desc) {
        this.userId = userId;
        this.name = name;
        this.total_amount = total_amount;
        this.min_payment = min_payment;
        this.desc = desc;
    }

    
    
}
