package com.finc.src.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

    Users findByUsername(String username);

    Users findById(UUID id);
    
}
