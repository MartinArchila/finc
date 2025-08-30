package com.finc.src.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finc.src.exceptions.DebtsException;
import com.finc.src.models.Debts;
import com.finc.src.models.Users;
import com.finc.src.repositories.DebtRepository;

@Service
public class DebtsService {

    @Autowired
    private DebtRepository debtRepository;

    public List<Debts> getDebts(Users user){
        //Fetch existing debts
        List<Debts> debts = debtRepository.findByUser(user);

        //if no debts are returned
        if(debts.isEmpty()){
            throw new DebtsException("The user: " + user.getUsername() + "does not have any debts");
        }

        return debts;
    }

    public Debts createDebtRecord(Users user, String name, double total_amount, double minimum_payment, String description){
        Debts new_debt = new Debts();

        new_debt.setUserId(user.getId());
        new_debt.setName(name);
        new_debt.setTotal_amount(new BigDecimal(total_amount));
        new_debt.setMin_payment(new BigDecimal(minimum_payment));
        new_debt.setDesc(description);

        return new_debt;
    }

    @Transactional
    public void deleteDebRecord(UUID debtId, Users user){
        
        Debts debt = debtRepository.findById(debtId).
                        orElseThrow(() -> new DebtsException("Debt record not found"));

        if(!debt.getUserId().equals(user.getId())){
           throw new DebtsException("This debt does not belong to the user"); 
        }

        debtRepository.delete(debt);
    }
     
}
