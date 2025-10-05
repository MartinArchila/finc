package com.finc.src.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Debts;
// import com.finc.src.models.Users;

@Repository
public interface DebtRepository extends JpaRepository<Debts, UUID>{
    
    // List<Debts> findByUser(Users user);

    List<Debts> findByUserId(UUID userId);
    
}
