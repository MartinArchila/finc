package com.finc.src.models.DTOs;

public class DebtDto {
    private String name;
    private String total_amount;
    private String minimum_payment;
    private String description;
    
    public String getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
    public String getMinimum_payment() {
        return minimum_payment;
    }
    public void setMinimum_payment(String minimum_payment) {
        this.minimum_payment = minimum_payment;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    

}
