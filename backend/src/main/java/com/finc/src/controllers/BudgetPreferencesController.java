package com.finc.src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finc.src.models.BudgetPreferences;
import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.PreferencesDto;
import com.finc.src.repositories.UserRepository;
import com.finc.src.services.BudgetPreferencesService;

@RestController
@RequestMapping("/preferences")
public class BudgetPreferencesController {
    
    @Autowired
    BudgetPreferencesService budgetPreferencesService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<BudgetPreferences> createOrUpdatePreferences(@AuthenticationPrincipal UserPrincipal userPrincipal,
        @RequestBody PreferencesDto dto) {
        
        Users user = userPrincipal.getUser();

        BudgetPreferences prefs = budgetPreferencesService.createOrUpdatePreferences(
            user,
            dto.getExpenses(),
            dto.getWants(),
            dto.getSavings()
            );

        return ResponseEntity.ok(prefs);
    }

    @GetMapping
    public ResponseEntity<BudgetPreferences> getBudgetPreferences(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Users user = userPrincipal.getUser();
        return  budgetPreferencesService.getPreferencesByUser(user)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}