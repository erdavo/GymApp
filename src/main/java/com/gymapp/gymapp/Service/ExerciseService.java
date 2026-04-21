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
                "A bodyweight chest exercise that also works the shoulders and triceps. Keep your body straight and lower yourself under control before pushing back up.",
                "Chest",
                "Medium",
                "/images/push_up_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Squat",
                "A fundamental lower-body exercise that targets the quadriceps, glutes, and hamstrings. Keep your chest up and lower your hips until your thighs are parallel to the floor.",
                "Legs",
                "Easy",
                "/images/squat_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Bench Press",
                "A classic strength exercise for building the chest, shoulders, and triceps. Lower the bar with control to the chest and press it back up powerfully.",
                "Chest",
                "Medium",
                "/images/bench_press_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Dips",
                "A powerful upper-body exercise that mainly targets the chest and triceps. Lower your body slowly and push back up while keeping control of the movement.",
                "Chest",
                "Medium",
                "/images/dips_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Pull Up",
                "A bodyweight back exercise that develops the lats, upper back, and biceps. Pull yourself up until your chin passes the bar, then lower with control.",
                "Back",
                "Hard",
                "/images/pull_up_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Deadlift",
                "A compound exercise that strengthens the back, glutes, hamstrings, and core. Lift the weight by driving through the legs while keeping your back straight.",
                "Back",
                "Hard",
                "/images/deadlift_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Lunges",
                "A lower-body exercise that improves balance and works the quadriceps, glutes, and hamstrings. Step forward, lower your body, and return to the starting position.",
                "Legs",
                "Medium",
                "/images/lunges_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Shoulder Press",
                "An upper-body exercise focused on the shoulders and triceps. Press the weight overhead in a controlled motion and lower it back down steadily.",
                "Arms",
                "Medium",
                "/images/shoulder_press_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Bicep Curl",
                "An isolation exercise for the biceps. Curl the weight upward without swinging your body, then lower it slowly to maximize muscle activation.",
                "Arms",
                "Easy",
                "/images/bicep_curl_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Tricep Extension",
                "An isolation exercise that targets the triceps. Extend the arms fully and return to the starting position with control for proper muscle engagement.",
                "Arms",
                "Easy",
                "/images/tricep_extension_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Plank",
                "A core stability exercise that strengthens the abs, lower back, and shoulders. Hold your body in a straight line while keeping the core engaged.",
                "Abs",
                "Easy",
                "/images/plank_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Crunch",
                "A basic abdominal exercise that focuses on the upper abs. Lift your shoulders off the floor using your core and lower back down slowly.",
                "Abs",
                "Easy",
                "/images/crunch_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Lat Pulldown",
                "A machine-based back exercise that targets the latissimus dorsi and upper back. Pull the bar down toward your upper chest and return it slowly.",
                "Back",
                "Medium",
                "/images/lat_pulldown_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Leg Press",
                "A machine exercise for building leg strength, especially in the quadriceps and glutes. Push the platform away in a controlled movement without locking the knees.",
                "Legs",
                "Medium",
                "/images/leg_press_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Romanian Deadlift",
                "A lower-body and posterior-chain exercise that emphasizes the hamstrings and glutes. Hinge at the hips while keeping the back straight and the weight close to the body.",
                "Legs",
                "Hard",
                "/images/romanian_deadlift_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Lateral Raise",
                "A shoulder isolation exercise that targets the side delts. Raise the weights to shoulder height with slight control and lower them slowly.",
                "Arms",
                "Easy",
                "/images/lateral_raise_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Barbell Row",
                "A compound back exercise that builds thickness in the upper and middle back. Pull the bar toward your torso while maintaining a stable bent-over position.",
                "Back",
                "Hard",
                "/images/barbell_row_exercise.jpg"
        ));

        createExercise(new Exercise(
                null,
                "Treadmill Running",
                "A cardio exercise that helps improve endurance, stamina, and cardiovascular health. Maintain a steady pace or vary the intensity with intervals.",
                "Cardio",
                "Medium",
                "/images/treadmill_running_exercise.jpg"
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