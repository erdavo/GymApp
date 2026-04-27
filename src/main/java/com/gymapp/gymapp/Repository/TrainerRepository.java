package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Trainer entity persistence.
 * Extends JpaRepository for standard CRUD operations.
 */
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}