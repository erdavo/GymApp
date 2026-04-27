package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for database persistence using Spring Data JPA
// Provides standard CRUD (Create, Read, Update, Delete) operations on Routine entities
// JpaRepository inherits from CrudRepository which provides basic CRUD operations on a repository
@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
}