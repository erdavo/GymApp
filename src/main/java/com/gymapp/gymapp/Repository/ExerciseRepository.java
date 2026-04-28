package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface is used to interact with the Exercise entity in the database
// It extends JpaRepository which provides methods for CRUD operations
// The @Repository annotation is used to indicate that this is a repository class
// The generic types are Exercise (the entity) and Long (the primary key)
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}