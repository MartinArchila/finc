package com.finc.src.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.models.Savings;
import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.SavingsDto;
import com.finc.src.services.SavingsService;

@RestController
@RequestMapping("/savings")
public class SavingsController {
    
    @Autowired
    SavingsService savingsService;

    //get list
    @GetMapping
    public ResponseEntity<List<Savings>> getSavingsList(@AuthenticationPrincipal UserPrincipal userPrincipal){
       Users user = userPrincipal.getUser();
       List<Savings> savingsList = savingsService.getSavingsList(user);

       return ResponseEntity.ok(savingsList);
    }
    //get
    @GetMapping("/{savingsId}")
    public ResponseEntity<Savings> getSavingsRecord(@PathVariable UUID savingsId){

        Savings savings = savingsService.getSavingsRecord(savingsId);
        return ResponseEntity.ok(savings);
    }
    //create
    @PostMapping
    public ResponseEntity<Savings> createSavingsRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody SavingsDto dto){
        Users user = userPrincipal.getUser();
        Savings savings = savingsService.createSavingsRecord(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savings);
    }
    //update
    @PostMapping("/{savingsId}")
    public ResponseEntity<Savings> updateSavingsRecord(@PathVariable UUID savingsId, @RequestBody SavingsDto dto){
        savingsService.updateSavingsRecord(savingsId, dto);
       return ResponseEntity.noContent().build();
    }

    //delete
    @DeleteMapping("/{savingsId}")
    public ResponseEntity<Savings> deleteSavingsRecord(@PathVariable UUID savingsId){
        savingsService.deleteSavingsRecord(savingsId);
        return ResponseEntity.noContent().build();
    }
}
