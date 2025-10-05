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
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.models.DebtDto;
import com.finc.src.models.Debts;
import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.repositories.DebtRepository;
import com.finc.src.repositories.UserRepository;
import com.finc.src.services.DebtsService;

@RestController
public class DebtController {
    
    @Autowired
    DebtRepository debtRepository;

    @Autowired
    DebtsService debtsService;

    @Autowired
    UserRepository userRespository;

    @GetMapping("/debts")
    public ResponseEntity<List<Debts>> getDebts(@AuthenticationPrincipal UserPrincipal userPrincipal){

        Users user = userPrincipal.getUser();
        List<Debts> debts = debtsService.getDebts(user);

        return ResponseEntity.ok(debts);
    }

    @PostMapping("/debts")
    public ResponseEntity<Debts> createDebtRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody DebtDto dto){

        Users user = userPrincipal.getUser();
        Debts debt = debtsService.createDebtRecord(
            user,
            dto.getName(),
            dto.getTotal_amount(),
            dto.getMinimum_payment(),
            dto.getDescription()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(debt);
    }

    @DeleteMapping("/debts/{debtId}")
    public ResponseEntity<Void> deleteDebt(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable UUID debtId){
        Users user = userPrincipal.getUser();

        debtsService.deleteDebtRecord(debtId, user);

        return ResponseEntity.noContent().build();


    }
}
