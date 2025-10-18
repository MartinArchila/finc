package com.finc.src.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Income;
import com.finc.src.models.Users;

@Repository
public interface IncomeRepository extends JpaRepository<Income, UUID>{
    
    Optional<Income> findByUser(Users user);
    
    Optional<Income> findByUserId(UUID userId);

    Optional<Income> findById(UUID Id);
}
