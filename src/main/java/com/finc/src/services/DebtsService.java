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
import com.finc.src.models.DTOs.DebtDto;
import com.finc.src.repositories.DebtRepository;

@Service
public class DebtsService {

    @Autowired
    private DebtRepository debtRepository;

    public List<Debts> getDebtsList(Users user){
        //Fetch existing debts
        return debtRepository.findByUserId(user.getId());
    }

    public Debts getDebtRecord(UUID Id){

        Debts debt = debtRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Debt does not Exist"));

        return debt;
    }

    @Transactional
    public Debts createDebtRecord(Users user, DebtDto dto){
        Debts new_debt = new Debts(user.getId(), dto.getName(), new BigDecimal(dto.getTotal_amount()),
                     new BigDecimal(dto.getMinimum_payment()), dto.getDescription());
        return debtRepository.save(new_debt);
    }

    @Transactional
    public Debts editDebtRecord(UUID Id, DebtDto dto){

        Debts existingDebts = debtRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Debt not found"));

        BigDecimal AMT = new BigDecimal(dto.getTotal_amount());
        BigDecimal MINPAY = new BigDecimal(dto.getMinimum_payment());

        existingDebts.setName(dto.getName());
        existingDebts.setTotal_amount(AMT);
        existingDebts.setMin_payment(MINPAY);
        existingDebts.setDesc(dto.getDescription());

        return debtRepository.save(existingDebts);
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
