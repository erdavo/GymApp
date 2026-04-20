package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Exercise;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExerciseService {

    private final Map<Integer, Exercise> exercises;
    private int nextId = 1;

    public ExerciseService() {
        exercises = new ConcurrentHashMap<>();

        createExercise(new Exercise(
                null,
                "Push Up",
                "Chest exercise",
                "Chest",
                "Medium",
                "/images/push_up_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Squat",
                "Leg exercise",
                "Legs",
                "Easy",
                "/images/squad_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Bench press",
                "Chest exercise",
                "Chest",
                "Medium",
                "/images/bench_press_exercise.jpg"
        ));


    }

    public Collection<Exercise> getAllExercises() {
        return exercises.values();
    }

    public Exercise getExerciseById(Integer id) {
        return exercises.get(id);
    }

    public Exercise createExercise(Exercise exercise) {
        exercise.setId(nextId);
        exercises.put(nextId, exercise);
        nextId++;
        return exercise;
    }

    public Exercise updateExercise(Integer id, Exercise updatedExercise) {
        if (exercises.containsKey(id)) {
            updatedExercise.setId(id);
            exercises.put(id, updatedExercise);
            return updatedExercise;
        }
        return null;
    }

    public Exercise deleteExercise(Integer id) {
        return exercises.remove(id);
    }

    public Exercise patchExercise(Integer id, Map<String, Object> updates) {
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
        if (updates.containsKey("imageUrl")) {
            exercise.setImageUrl((String) updates.get("imageUrl"));
        }

        return exercise;
    }
}