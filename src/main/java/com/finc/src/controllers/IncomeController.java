package com.finc.src.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.models.Income;
import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.IncomeDto;
import com.finc.src.services.IncomeService;

@RestController
@RequestMapping("/income")
public class IncomeController {
    
    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public ResponseEntity<Income> getIncome(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Users user = userPrincipal.getUser();
        Optional<Income> income = incomeService.getIncomeInformation(user.getId());
        return income.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Income> saveIncome(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody IncomeDto dto){
        Users user = userPrincipal.getUser();
        
        Income income = incomeService.createIncome(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(income);
    }

    @PostMapping("{incomeId}")
    public ResponseEntity<Income> updateIncome(@PathVariable UUID incomeId, @RequestBody IncomeDto dto){

        Income income = incomeService.updateIncome(incomeId, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(income);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
}
