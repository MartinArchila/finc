package com.finc.src.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finc.src.exceptions.NecessitiesException;
import com.finc.src.exceptions.SavingsException;
import com.finc.src.models.Savings;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.SavingsDto;
import com.finc.src.repositories.SavingsRepository;

import jakarta.transaction.Transactional;

@Service
public class SavingsService {
    @Autowired
    SavingsRepository savingsRepository;

    //Create
    @Transactional
    public Savings createSavingsRecord(Users user, SavingsDto dto){
        Savings new_saving = new Savings(user.getId(), dto.getName(), 
                        new BigDecimal(dto.getTotal_amount()),new BigDecimal(dto.getMinimum_payment()), dto.getDescription());
        
        return savingsRepository.save(new_saving);
    }
    //get List of Savings
    @Transactional
    public List<Savings> getSavingsList(Users user){
        return savingsRepository.findByUserId(user.getId());
    }
    //get Savings Record
    @Transactional
    public Savings getSavingsRecord(UUID Id){
        Savings savings = savingsRepository.findById(Id)
                    .orElseThrow(() -> new SavingsException("Savings not found"));
        
        return savings;
    }
    //update Savings Record
    @Transactional
    public Savings updateSavingsRecord(UUID Id, SavingsDto updatedDto){
        Savings existingSavings = savingsRepository.findById(Id)
                    .orElseThrow(() -> new SavingsException("Savings not found"));
        BigDecimal TOTAMT = new BigDecimal(updatedDto.getTotal_amount());
        BigDecimal MINAMT = new BigDecimal(updatedDto.getMinimum_payment());

        existingSavings.setName(updatedDto.getName());
        existingSavings.setTotal_amount(TOTAMT);
        existingSavings.setMinimum_payment(MINAMT);
        existingSavings.setDescription(updatedDto.getDescription());

        return savingsRepository.save(existingSavings);
    }

    //delete Savings Record
    @Transactional
    public void deleteSavingsRecord(UUID Id){
        Savings savings = savingsRepository.findById(Id)
                        .orElseThrow(()-> new NecessitiesException("Savings Not Found"));
        savingsRepository.delete(savings);
    }
}
