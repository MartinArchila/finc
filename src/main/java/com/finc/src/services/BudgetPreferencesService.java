package com.finc.src.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finc.src.exceptions.InvalidBudgetPreferenceException;
import com.finc.src.models.BudgetPreferences;
import com.finc.src.models.Users;
import com.finc.src.repositories.BudgetPreferencesRepository;

@Service
public class BudgetPreferencesService {
    
    @Autowired
    private BudgetPreferencesRepository budgetPreferencesRepository;

    @Transactional
    public BudgetPreferences createOrUpdatePreferences(Users user, double expenses, double wants, double savings){
        
        //Validate percentages add up to 100
        if((expenses+wants+savings) != 100){
            throw new InvalidBudgetPreferenceException("The percentages for necessities, wants, and savings&debts should add up to 100.");
        }

        //Fetch existing preferences or create new ones
        BudgetPreferences prefs = budgetPreferencesRepository.findByUser(user)
                .orElseGet(() -> {
                    BudgetPreferences newPrefs = new BudgetPreferences(user, 0.0,0.0,0.0);
                    return newPrefs;
                });

        //Update the fields
        prefs.setNecessities_percent(expenses);
        prefs.setWants_percent(wants);
        prefs.setSavings_debt_percent(savings);

        //Save to DB
        return budgetPreferencesRepository.save(prefs);
    }

    public Optional<BudgetPreferences> getPreferencesByUser(Users user) {
        return budgetPreferencesRepository.findByUser(user);
    }

    public Optional<BudgetPreferences> getPreferencesByUserId(UUID userId){
        return budgetPreferencesRepository.findByUserId(userId);
    }
}
