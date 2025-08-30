package com.finc.src.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finc.src.models.UserPrincipal;
import com.finc.src.models.Users;
import com.finc.src.repositories.UserRepository;


@Service
public class myUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    public UserPrincipal loadUserByUserId(UUID userId) throws UsernameNotFoundException {
        
        Users user = userRepository.findById(userId);

        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }

     @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Users user = userRepository.findByUsername(username);
        
        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }

}
