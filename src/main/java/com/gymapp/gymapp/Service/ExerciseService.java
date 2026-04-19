package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Exercise;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExerciseService {

    private final Map<String, Exercise> exercises;

    public ExerciseService() {
        exercises = new ConcurrentHashMap<>();
        exercises.put("1", new Exercise("1", "Push Up", "Chest exercise", "Chest", "Medium"));
        exercises.put("2", new Exercise("2", "Squat", "Leg exercise", "Legs", "Easy"));
    }

    public Collection<Exercise> getAllExercises() {
        return exercises.values();
    }

    public Exercise getExerciseById(String id) {
        return exercises.get(id);
    }

    public Exercise createExercise(Exercise exercise) {
        exercises.put(exercise.getId(), exercise);
        return exercise;
    }

    public Exercise updateExercise(String id, Exercise updatedExercise) {
        if (exercises.containsKey(id)) {
            updatedExercise.setId(id);
            exercises.put(id, updatedExercise);
            return updatedExercise;
        }
        return null;
    }

    public Exercise deleteExercise(String id) {
        return exercises.remove(id);
    }

    public Exercise patchExercise(String id, Map<String, Object> updates) {
        Exercise exercise = exercises.get(id);

        if (exercise == null) {
            return null;
        }

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

        return exercise;
    }
}