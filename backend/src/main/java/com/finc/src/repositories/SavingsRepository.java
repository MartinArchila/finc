package com.finc.src.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Savings;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, UUID> {
    
    List<Savings> findByUserId(UUID userId);

    Optional<Savings> findById(UUID Id);
}
