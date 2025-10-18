package com.finc.src.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finc.src.models.Necessities;

@Repository
public interface NecessitiesRepository extends JpaRepository<Necessities, UUID> {
    
    List<Necessities> findByUserId(UUID userId);

    Optional<Necessities> findById(UUID Id);
}
