package com.gymapp.gymapp.Config;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Entities.Trainer;
import com.gymapp.gymapp.Service.ExerciseService;
import com.gymapp.gymapp.Service.RoutineService;
import com.gymapp.gymapp.Service.TrainerService;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Component that populates the database with sample data.
 * Only runs in local profile, and only if the database is empty.
 * * Requirement Check: Database access is done strictly through @Service
 * classes.
 */
@Component
@Profile("local")
public class MyComponent {

        private final RoutineService routineService;
        private final TrainerService trainerService;
        private final ExerciseService exerciseService;

        // Constructor injection for services (Requirement: Use @Service for DB
        // connection)
        public MyComponent(RoutineService routineService, TrainerService trainerService,
                        ExerciseService exerciseService) {
                this.routineService = routineService;
                this.trainerService = trainerService;
                this.exerciseService = exerciseService;
        }

        @PostConstruct
        public void populateDB() {
                // Check if database is empty using services
                if (trainerService.getAllTrainers().isEmpty() &&
                                exerciseService.getAllExercises().isEmpty() &&
                                routineService.getAllRoutines().isEmpty()) {

                        // 1. Create and Save Trainers via TrainerService
                        Trainer t1 = new Trainer("Alex Carter", "alex.carter@gymapp.com",
                                        "Madrid, Spain - 28 years old, Strength & Conditioning Specialist",
                                        "/images/alex_carter_trainer.jpg", new ArrayList<>());
                        Trainer t2 = new Trainer("Sofia Martinez", "sofia.martinez@gymapp.com",
                                        "Barcelona, Spain - 32 years old, Functional Training & HIIT Coach",
                                        "/images/sofia_martinez_trainer.jpg", new ArrayList<>());
                        Trainer t3 = new Trainer("Daniel Lopez", "daniel.lopez@gymapp.com",
                                        "Valencia, Spain - 35 years old, Personal Trainer & Nutrition Expert",
                                        "/images/daniel_lopez_trainer.jpg", new ArrayList<>());

                        trainerService.createTrainer(t1);
                        trainerService.createTrainer(t2);
                        trainerService.createTrainer(t3);

                        // 2. Create and Save Exercises via ExerciseService
                        Exercise ex1 = new Exercise("Push Up", "A bodyweight chest exercise...", "Chest", "Medium",
                                        "/images/push_up_exercise.jpg");
                        Exercise ex2 = new Exercise("Squat", "A fundamental lower-body exercise...", "Legs", "Easy",
                                        "/images/squat_exercise.jpg");
                        Exercise ex3 = new Exercise("Bench Press", "A classic strength exercise...", "Chest", "Medium",
                                        "/images/bench_press_exercise.jpg");
                        Exercise ex5 = new Exercise("Pull Up", "A bodyweight back exercise...", "Back", "Hard",
                                        "/images/pull_up_exercise.jpg");
                        Exercise ex8 = new Exercise("Shoulder Press", "An upper-body exercise...", "Arms", "Medium",
                                        "/images/shoulder_press_exercise.jpg");
                        Exercise ex11 = new Exercise("Plank", "A core stability exercise...", "Abs", "Easy",
                                        "/images/plank_exercise.jpg");
                        Exercise ex13 = new Exercise("Lat Pulldown", "A machine-based back exercise...", "Back",
                                        "Medium", "/images/lat_pulldown_exercise.jpg");
                        Exercise ex14 = new Exercise("Leg Press", "A machine exercise...", "Legs", "Medium",
                                        "/images/leg_press_exercise.jpg");
                        Exercise ex15 = new Exercise("Romanian Deadlift", "A lower-body exercise...", "Legs", "Hard",
                                        "/images/romanian_deadlift_exercise.jpg");
                        Exercise ex17 = new Exercise("Barbell Row", "A compound back exercise...", "Back", "Hard",
                                        "/images/barbell_row_exercise.jpg");

                        exerciseService.createExercise(ex1);
                        exerciseService.createExercise(ex2);
                        exerciseService.createExercise(ex3);
                        exerciseService.createExercise(ex5);
                        exerciseService.createExercise(ex8);
                        exerciseService.createExercise(ex11);
                        exerciseService.createExercise(ex13);
                        exerciseService.createExercise(ex14);
                        exerciseService.createExercise(ex15);
                        exerciseService.createExercise(ex17);

                        // 3. Create Routines, assigning exercises and trainers via RoutineService
                        Routine r1 = new Routine("PPL", "A Push Pull Legs routine...", "Medium",
                                        "/images/ppl_routine.jpg", new ArrayList<>(), t1);
                        r1.getExercises().add(ex3);
                        r1.getExercises().add(ex5);
                        r1.getExercises().add(ex2);
                        r1.getExercises().add(ex8);

                        Routine r2 = new Routine("Full Body", "A full body routine ideal for beginners...", "Easy",
                                        "/images/full_body_routine.jpg", new ArrayList<>(), t2);
                        r2.getExercises().add(ex3);
                        r2.getExercises().add(ex17);
                        r2.getExercises().add(ex14);
                        r2.getExercises().add(ex15);

                        Routine r3 = new Routine("Upper-Lower", "An upper lower split focused on building strength...",
                                        "Hard", "/images/upper_lower_routine.jpg", new ArrayList<>(), t3);
                        r3.getExercises().add(ex1);
                        r3.getExercises().add(ex2);
                        r3.getExercises().add(ex13);
                        r3.getExercises().add(ex11);

                        routineService.createRoutine(r1);
                        routineService.createRoutine(r2);
                        routineService.createRoutine(r3);
                }
        }
}