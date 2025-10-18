package com.finc.src.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finc.src.exceptions.WantsException;
import com.finc.src.models.Users;
import com.finc.src.models.Wants;
import com.finc.src.models.DTOs.WantsDto;
import com.finc.src.repositories.WantsRepository;

import jakarta.transaction.Transactional;

@Service
public class WantsService {
    
    @Autowired
    WantsRepository wantsRepository;

    //get list
    @Transactional
    public List<Wants> getWantsList(Users user){
        return wantsRepository.findByUserId(user.getId());
    }

    //get record
    @Transactional
    public Wants getWantsRecord(UUID wantsId){
        Wants wants = wantsRepository.findById(wantsId)
                    .orElseThrow(() -> new WantsException("Wants record not found"));

        return wants;
    }

    //create record
    @Transactional
    public Wants createWantsRecord(Users user, WantsDto dto){
        Wants want = new Wants(user.getId(), dto.getName(), new BigDecimal(dto.getAmount()), dto.getDescription());
        return wantsRepository.save(want);
    }

    //edit record
    @Transactional
    public Wants editWantsRecord(UUID wantsId, WantsDto updatedWant){
        Wants existingWants = wantsRepository.findById(wantsId)
                        .orElseThrow(() -> new WantsException("Want record not found"));
        BigDecimal AMT = new BigDecimal(updatedWant.getAmount());

        existingWants.setName(updatedWant.getName());
        existingWants.setAmount(AMT);
        existingWants.setDescription(updatedWant.getDescription());
        return wantsRepository.save(existingWants);
    }

    //delete record
    @Transactional
    public void deleteWantsRecord(UUID wantsId){
        Wants want = wantsRepository.findById(wantsId)
                .orElseThrow(() -> new WantsException("Want record does not exist"));
        wantsRepository.delete(want);
    }
}
