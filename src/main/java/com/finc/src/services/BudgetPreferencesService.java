package com.finc.src.services;

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
        //Fetch existing preferences if they exist
        BudgetPreferences prefs = budgetPreferencesRepository.findByUser(user);

        if(prefs == null) {
            //Create new preferences if none exist
            prefs = new BudgetPreferences();
            prefs.setUser(user);
        }

        //Confirm that preferences add up to 100%
        if((expenses+wants+savings) != 100){
            throw new InvalidBudgetPreferenceException("The percentages for necessities, wants, and savings&debts should add up to 100.");
        }

        //Update the Fields
        prefs.setNecessities_percent(expenses);
        prefs.setWants_percent(wants);
        prefs.setSavings_debt_percent(savings);

        //Save to DB
        return budgetPreferencesRepository.save(prefs);
    }

    public BudgetPreferences getPreferencesByUser(Users user) {
        return budgetPreferencesRepository.findByUser(user);
    }

    public BudgetPreferences getPreferencesByUserId(UUID userId){
        return budgetPreferencesRepository.findByUserId(userId);
    }
}
