package com.finc.src.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "necessities")
public class Necessities {
    
    @Id
    @GeneratedValue
    private UUID Id;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "amount", nullable = false)
    private BigDecimal amount;

    @Column (name = "description")
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Necessities [Id=" + Id + ", userId=" + userId + ", name=" + name + ", amount=" + amount
                + ", description=" + description + "]";
    }

    public Necessities(){}

    public Necessities(UUID userId, String name, BigDecimal amount, String description) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

}
