package com.finc.src.repositories;

import org.springframework.stereotype.Repository;

import com.finc.src.models.BudgetPreferences;
import com.finc.src.models.Users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BudgetPreferencesRepository extends JpaRepository<BudgetPreferences, UUID>{

    Optional<BudgetPreferences> findByUser(Users user);

    Optional<BudgetPreferences> findByUserId(UUID userId);
}
