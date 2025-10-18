package com.finc.src.models.DTOs;

public class IncomeDto {
    
    private String amount;
    private String payDate;
    private String nextPayDate;
    private String numInMonth;

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getPayDate() {
        return payDate;
    }
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public String getNextPayDate() {
        return nextPayDate;
    }
    public void setNextPayDate(String nextPayDate) {
        this.nextPayDate = nextPayDate;
    }
    public String getNumInMonth() {
        return numInMonth;
    }
    public void setNumInMonth(String numInMonth) {
        this.numInMonth = numInMonth;
    }

    
}
