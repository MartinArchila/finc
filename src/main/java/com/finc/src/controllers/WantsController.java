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

import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.models.Wants;
import com.finc.src.models.DTOs.WantsDto;
import com.finc.src.services.WantsService;

@RestController
@RequestMapping("/wants")
public class WantsController {
    
    @Autowired
    WantsService wantsService;

    //get list
    @GetMapping
    public ResponseEntity<List<Wants>> getWantsList(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Users user = userPrincipal.getUser();
        List<Wants> wantList = wantsService.getWantsList(user);
        return ResponseEntity.ok(wantList);
    }
    //get record
    @GetMapping("/{wantsId}")
    public ResponseEntity<Wants> getWantsRecord(@PathVariable UUID wantsId){
        Wants want = wantsService.getWantsRecord(wantsId);
        return ResponseEntity.ok(want);
    }

    //create record
    @PostMapping
    public ResponseEntity<Wants> createWantsRecord(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody WantsDto dto ){
        Users user = userPrincipal.getUser();

        Wants wants = wantsService.createWantsRecord(user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(wants);
    }

    //edit record
    @PostMapping("/{wantsId}")
    public ResponseEntity<Wants> editWantsRecord(@PathVariable UUID wantsId, @RequestBody WantsDto dto){
        Wants updatedWants = wantsService.editWantsRecord(wantsId, dto);
        return ResponseEntity.ok(updatedWants);
    }

    //delte record
    @DeleteMapping("/{wantsId}")
    public ResponseEntity<Void> deleteWantsRecord(@PathVariable UUID wantsId){

        wantsService.deleteWantsRecord(wantsId);

        return ResponseEntity.noContent().build();
    }
}
