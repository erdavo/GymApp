package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Exercise;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    private final List<Exercise> exercises = new ArrayList<>();

    public ExerciseService() {
        exercises.add(new Exercise("1", "Push Up", "Chest exercise", "Chest", "Medium"));
        exercises.add(new Exercise("2", "Squat", "Leg exercise", "Legs", "Easy"));
    }

    public List<Exercise> getAllExercises() {
        return exercises;
    }

    public Exercise createExercise(Exercise exercise) {
        exercises.add(exercise);
        return exercise;
    }
}