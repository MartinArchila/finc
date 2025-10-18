package com.finc.src.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="income")
public class Income {
    
    @Id
    @GeneratedValue
    private UUID Id;

    @OneToOne
    @JoinColumn (name="user_id")
    private Users user;

    @Column (name = "amount", nullable = false)
    private BigDecimal amount;

    @Column (name = "pay_date", nullable = false)
    private LocalDate payDate;

    @Column (name = "next_pay_date", nullable = false)
    private LocalDate nextPayDate;

    @Column (name = "num_paid_in_month", nullable = false)
    private int NumInMonth;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    @JsonIgnore
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public LocalDate getNextPayDate() {
        return nextPayDate;
    }

    public void setNextPayDate(LocalDate nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

    public int getNumInMonth() {
        return NumInMonth;
    }

    public void setNumInMonth(int numInMonth) {
        NumInMonth = numInMonth;
    }

    public Income(){}

    public Income(Users user, BigDecimal amount, LocalDate payDate, LocalDate nextPayDate, int numInMonth) {
        this.user = user;
        this.amount = amount;
        this.payDate = payDate;
        this.nextPayDate = nextPayDate;
        NumInMonth = numInMonth;
    }
    
}
