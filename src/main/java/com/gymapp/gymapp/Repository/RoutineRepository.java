package com.gymapp.gymapp.Repository;

import com.gymapp.gymapp.Entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long>  {
}
