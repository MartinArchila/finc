package com.finc.src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finc.src.models.Users;
import com.finc.src.services.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }

    @PostMapping("/register")
    public void register(@RequestBody Users user){
        // return userService.register(user);
        return;
    }

    @PostMapping("/user/retrieveId")
    public String returnUserId(@RequestBody Users user){
        return userService.returnUserId(user);
    }

}
