package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Exercise;
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
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Integer id) {
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/exercises")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    @PutMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Integer id, @RequestBody Exercise updatedExercise) {
        Exercise exercise = exerciseService.updateExercise(id, updatedExercise);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Integer id) {
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> patchExercise(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Exercise exercise = exerciseService.patchExercise(id, updates);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
