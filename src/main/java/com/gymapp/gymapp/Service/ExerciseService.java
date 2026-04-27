package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymapp.gymapp.Entities.Exercise;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    // Retrieve all exercise from the database
    public Collection<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    // Find a specific exercise by its ID
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    // Persist a new exercise in the database
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // Update an existing exercise completely (PUT equivalent)
    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        if (exerciseRepository.existsById(id)) {
            updatedExercise.setId(id);
            return exerciseRepository.save(updatedExercise);
        }
        return null;
    }

    // Remove a exercise from the database
    public void deleteExercise(Long id) {
            exerciseRepository.deleteById(id);
    }

    // Apply partial updates to a exercise (PATCH equivalent)
    public Exercise patchExercise(Long id, Map<String, Object> updates) {
        Optional<Exercise> op = exerciseRepository.findById(id);

        if (op.isPresent()) {
            Exercise exercise = op.get();
            if (updates.containsKey("name")) {
                exercise.setName((String) updates.get("name"));
            }
            if (updates.containsKey("description")) {
                exercise.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("muscleGroup")) {
                exercise.setMuscleGroup((String) updates.get("muscleGroup"));
            }
            if (updates.containsKey("difficulty")) {
                exercise.setDifficulty((String) updates.get("difficulty"));
            }
            if (updates.containsKey("imageUrl")) {
                exercise.setImageUrl((String) updates.get("imageUrl"));
            }
            return exerciseRepository.save(exercise);
        }
        return null;
    }
}