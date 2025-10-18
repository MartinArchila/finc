package com.finc.src.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finc.src.models.Wants;

public interface WantsRepository extends JpaRepository<Wants, UUID>{
    
    List<Wants> findByUserId(UUID userId);
}
