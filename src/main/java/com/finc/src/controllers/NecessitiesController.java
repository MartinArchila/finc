package com.finc.src.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.repositories.UserRepository;
import com.finc.src.services.NecessitiesService;
import com.finc.src.models.Necessities;
import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.models.DTOs.NecessitiesDto;

@RestController
@RequestMapping("/necessities")
public class NecessitiesController {
    
    @Autowired
    NecessitiesService necessitiesService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Necessities>> getNecessitiesList(@AuthenticationPrincipal UserPrincipal userPrincipal){

        Users user = userPrincipal.getUser();
        List<Necessities> necessities = necessitiesService.getNecessitiesList(user);

        return ResponseEntity.ok(necessities);
    }

    @GetMapping("/{necessitiesId}")
    public ResponseEntity<Necessities> getNecessityRecord(@PathVariable UUID necessitiesId){

        Necessities necessity = necessitiesService.getNecessityRecord(necessitiesId);

        return ResponseEntity.ok(necessity);
    }

    @PostMapping
    public ResponseEntity<Necessities> createNecessityRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody NecessitiesDto dto){

        Users user = userPrincipal.getUser();
        Necessities necessity = necessitiesService.createNecessityRecord(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(necessity);
    }

    @PostMapping("/{necessityId}")
    public ResponseEntity<Necessities> updateNecessityRecord(@PathVariable UUID necessityId, @RequestBody NecessitiesDto dto){

        necessitiesService.updateNecessityRecord(necessityId, dto);

       return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{necessityId}")
    public ResponseEntity<Void> deleteNecessityRecord(@PathVariable UUID necessityId){

        necessitiesService.deleteNecessityRecord(necessityId);
        return ResponseEntity.noContent().build();
    }
}
