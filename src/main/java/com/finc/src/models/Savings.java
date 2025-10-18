package com.finc.src.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="savings")
public class Savings {
    
    @Id
    @GeneratedValue
    private UUID Id;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Column(name ="name", nullable = false)
    private String name;

    @Column (name = "total_amount", nullable = false)
    private BigDecimal total_amount;

    @Column(name = "minimum_payment", nullable = false)
    private BigDecimal minimum_payment;

    @Column (name = "description", nullable = false)
    private String description;

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

    public BigDecimal getMinimum_payment() {
        return minimum_payment;
    }

    public void setMinimum_payment(BigDecimal minimum_payment) {
        this.minimum_payment = minimum_payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Savings [Id=" + Id + ", userId=" + userId + ", name=" + name + ", total_amount=" + total_amount
                + ", minimum_payment=" + minimum_payment + ", description=" + description + "]";
    }

    public Savings(UUID userId, String name, BigDecimal total_amount, BigDecimal minimum_payment, String description) {
        this.userId = userId;
        this.name = name;
        this.total_amount = total_amount;
        this.minimum_payment = minimum_payment;
        this.description = description;
    }

    public Savings(){}

}
