package com.finc.src.models.DTOs;

import java.util.UUID;

public class UserIdRequestDto {
    private UUID userId;

    public UUID getUserId(){
        return userId;
    }

    public void setUserId(UUID userId){
        this.userId = userId; 
    }
}
