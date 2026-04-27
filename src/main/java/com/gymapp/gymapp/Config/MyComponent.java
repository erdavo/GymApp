package com.gymapp.gymapp.Config;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Entities.Trainer;
import com.gymapp.gymapp.Repository.ExerciseRepository;
import com.gymapp.gymapp.Repository.RoutineRepository;
import com.gymapp.gymapp.Repository.TrainerRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class MyComponent {

    // Auto-inject repositories for data access
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostConstruct
    public void populateDB() {
        // 1. Create and save a Trainer (Owning side of 1:N)
        Trainer trainer1 = new Trainer("David", "david@example.com", "Expert in bodybuilding", null);
        trainerRepository.save(trainer1);

        // 2. Create and save an Exercise (Owning side of N:M)
        Exercise ex1 = new Exercise("Squat", "Lower body compound exercise", "Legs", "Hard", "/images/squat.jpg");
        exerciseRepository.save(ex1);

        // 3. Create Routine and link relationships
        Routine routine1 = new Routine("PPL", "Push Pull Legs split", "Medium", "/images/ppl.jpg", null, trainer1);
        routine1.getExercises().add(ex1);

        // 4. Persist the complete routine
        routineRepository.save(routine1);
    }
}