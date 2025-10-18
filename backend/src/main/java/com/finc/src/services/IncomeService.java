package com.finc.src.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finc.src.exceptions.IncomeException;
import com.finc.src.models.Income;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.IncomeDto;
import com.finc.src.repositories.IncomeRepository;
import com.finc.src.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class IncomeService {
    
    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Income createIncome(Users user, IncomeDto dto) {
        
        // Check if already exists
        if (incomeRepository.findByUser(user).isPresent()) {
            throw new IllegalStateException("Income already exists for this user");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate dbPayD = LocalDate.parse(dto.getPayDate(), formatter);
        LocalDate dbNextPayD = LocalDate.parse(dto.getNextPayDate(), formatter);
        BigDecimal AMT = new BigDecimal(dto.getAmount());
        int NUMPAY = Integer.parseInt(dto.getNumInMonth());

        Income newIncome = new Income(
            user, 
            AMT, 
            dbPayD, 
            dbNextPayD, 
            NUMPAY
        );

        return incomeRepository.save(newIncome);
    }

    @Transactional
    public Income updateIncome(UUID Id, IncomeDto dto) {
        
        Income existingIncome = incomeRepository.findById(Id)
                .orElseThrow(() -> new IncomeException("Income not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate dbPayD = LocalDate.parse(dto.getPayDate(), formatter);
        LocalDate dbNextPayD = LocalDate.parse(dto.getNextPayDate(), formatter);
        BigDecimal AMT = new BigDecimal(dto.getAmount());
        int NUMMONTH = Integer.parseInt(dto.getNumInMonth());

        existingIncome.setAmount(AMT);
        existingIncome.setPayDate(dbPayD);
        existingIncome.setNextPayDate(dbNextPayD);
        existingIncome.setNumInMonth(NUMMONTH);

        return incomeRepository.save(existingIncome);
    }

    public Optional<Income> getIncomeInformation(UUID userId){
        return incomeRepository.findByUserId(userId);
    }
}
