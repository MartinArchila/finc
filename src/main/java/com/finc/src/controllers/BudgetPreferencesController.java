package com.finc.src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.models.BudgetPreferences;
import com.finc.src.models.PreferencesDto;
import com.finc.src.models.Users;
import com.finc.src.repositories.UserRepository;
// import com.finc.src.models.Users;
import com.finc.src.services.BudgetPreferencesService;

@RestController
public class BudgetPreferencesController {
    
    @Autowired
    BudgetPreferencesService budgetPreferencesService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/preferences")
    public ResponseEntity<BudgetPreferences> createOrUpdatePreferences(@RequestBody PreferencesDto dto){
        Users user = null;

        try{
        user = userRepository.findById(dto.getUserId());
        }catch (Exception e){}

        if(user == null){
            //Returns 404 if the user doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        BudgetPreferences prefs = budgetPreferencesService.createOrUpdatePreferences(user,dto.getExpenses(),
                                        dto.getWants(), dto.getSavings());   

        return ResponseEntity.ok(prefs);
    }
}
