package com.finc.src.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Debts;

@Repository
public interface DebtRepository extends JpaRepository<Debts, UUID>{
    
    Optional<Debts> findById(UUID Id);

    List<Debts> findByUserId(UUID userId);
    
}
