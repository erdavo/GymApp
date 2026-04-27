package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}