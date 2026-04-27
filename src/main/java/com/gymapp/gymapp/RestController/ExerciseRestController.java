package com.gymapp.gymapp.RestController;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/api/exercises")
    public ResponseEntity<Collection<Exercise>> getAllExercises() {
        Collection<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/exercises")
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise created = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(created, org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise updatedExercise) {
        Exercise exercise = exerciseService.updateExercise(id, updatedExercise);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> patchExercise(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Exercise exercise = exerciseService.patchExercise(id, updates);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
