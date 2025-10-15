package com.finc.src.models.DTOs;

public class WantsDto {
    String name;
    String amount;
    String description;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
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
        return "WantsDto [name=" + name + ", amount=" + amount + ", description=" + description + "]";
    }
    
}
