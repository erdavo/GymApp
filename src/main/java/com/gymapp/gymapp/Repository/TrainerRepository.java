package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long>  {
}
