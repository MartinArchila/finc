package com.finc.src.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finc.src.exceptions.NecessitiesException;
import com.finc.src.models.Necessities;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.NecessitiesDto;
import com.finc.src.repositories.NecessitiesRepository;

import jakarta.transaction.Transactional;

@Service
public class NecessitiesService {
    
    @Autowired
    NecessitiesRepository necessitiesRepository;

    //Create
    @Transactional
    public Necessities createNecessityRecord(Users user, NecessitiesDto dto ){
        Necessities new_necessity = new Necessities(user.getId(),dto.getName(), 
                         new BigDecimal(dto.getAmount()),dto.getDescription());

        return necessitiesRepository.save(new_necessity);
    }

    //get list of all Necessities
    @Transactional
    public List<Necessities> getNecessitiesList(Users user){
        return necessitiesRepository.findByUserId(user.getId());
    }

    //get specific necessity record
    @Transactional
    public Necessities getNecessityRecord(UUID Id){
        Necessities necessity = necessitiesRepository.findById(Id)
                    .orElseThrow(() -> new NecessitiesException("Cannot find that Necessity"));

        return necessity;
    }
    //update necessity record
    @Transactional
    public Necessities updateNecessityRecord(UUID Id, NecessitiesDto updatedNecessityDto){
        Necessities existingNecessities = necessitiesRepository.findById(Id)
                        .orElseThrow(() -> new NecessitiesException("Necessity not found"));
        BigDecimal AMT = new BigDecimal(updatedNecessityDto.getAmount());
        existingNecessities.setName(updatedNecessityDto.getName());
        existingNecessities.setAmount(AMT);
        existingNecessities.setDescription(updatedNecessityDto.getDescription());

        return necessitiesRepository.save(existingNecessities);
    }


    //delete necessity record
    @Transactional
    public void deleteNecessityRecord(UUID Id){
        Necessities necessity = necessitiesRepository.findById(Id)
                        .orElseThrow(()-> new NecessitiesException("Necessity Not Found"));
        necessitiesRepository.delete(necessity);
    }


}
