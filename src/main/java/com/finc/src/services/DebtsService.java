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
        return debtRepository.findByUserId(user.getId());
    }

    @Transactional
    public Debts createDebtRecord(Users user, String name, String total_amount, String minimum_payment, String description){
        Debts new_debt = new Debts(user.getId(), name, new BigDecimal(total_amount), new BigDecimal(minimum_payment), description);
        return debtRepository.save(new_debt);
    }

    @Transactional
    public void deleteDebtRecord(UUID debtId, Users user){
        
        Debts debt = debtRepository.findById(debtId).
                        orElseThrow(() -> new DebtsException("Debt record not found"));

        if(!debt.getUserId().equals(user.getId())){
           throw new DebtsException("This debt does not belong to the user"); 
        }

        debtRepository.delete(debt);
    }
     
}
