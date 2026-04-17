package com.gymapp.gymapp.Controller;



import com.gymapp.gymapp.Model.Exercise;
import com.gymapp.gymapp.Service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/api/exercises")
    public Collection<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PostMapping("/api/exercises")
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    @PutMapping("/api/exercises/{id}")
    public Exercise updateExercise(@PathVariable String id, @RequestBody Exercise updatedExercise) {
        return exerciseService.updateExercise(id, updatedExercise);
    }

    @DeleteMapping("/api/exercises/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable String id) {
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
