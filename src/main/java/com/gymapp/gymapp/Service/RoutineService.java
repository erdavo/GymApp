package com.gymapp.gymapp.Service;


import com.gymapp.gymapp.Entities.Exercise;
import org.springframework.stereotype.Service;

import com.gymapp.gymapp.Entities.Routine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class RoutineService {
    private final Map<Integer, Routine> routines;
    private int nextId = 1;
    private final ExerciseService exerciseService ;
    private final TrainerService trainerService ;

    public RoutineService(ExerciseService exerciseService, TrainerService trainerService) {
        this.exerciseService = exerciseService;
        this.trainerService = trainerService;
        this.routines = new ConcurrentHashMap<>();

        createRoutine(new Routine(
                null,
                "PPL",
                "A Push Pull Legs routine designed to build muscle and improve overall strength with a balanced weekly split.",
                "Medium",
                "/images/ppl_routine.jpg",
                getExercisesByIds(3, 5, 2, 8),
                trainerService.getTrainerById(1)
        ));

        createRoutine(new Routine(
                null,
                "Full Body",
                "A full body routine ideal for beginners who want to train all major muscle groups in a simple and effective way.",
                "Easy",
                "/images/full_body_routine.jpg",
                getExercisesByIds(3, 17, 14, 15),
                trainerService.getTrainerById(2)
        ));

        createRoutine(new Routine(
                null,
                "Upper-Lower",
                "An upper lower split focused on building strength and muscle by dividing workouts between upper-body and lower-body days.",
                "Hard",
                "/images/upper_lower_routine.jpg",
                getExercisesByIds(1, 2, 13, 11),
                trainerService.getTrainerById(3)
        ));
    }

    public Collection<Routine> getAllRoutines() {
        return routines.values();
    }

    public Routine getRoutineById(Integer id) {
        return routines.get(id);
    }

    public Routine createRoutine(Routine routine) {
        routine.setId(nextId);
        routines.put(nextId, routine);
        nextId++;

        if (routine.getTrainer() != null) {
            routine.getTrainer().getRoutines().add(routine);
        }

        return routine;
    }

    public Routine updateRoutine(Integer id, Routine updatedRoutine) {
        if (routines.containsKey(id)) {
            updatedRoutine.setId(id);
            routines.put(id, updatedRoutine);
            return updatedRoutine;
        }
        return null;
    }

    private List<Exercise> getExercisesByIds(Integer... ids) {
        List<Exercise> exercises = new ArrayList<>();

        for (Integer id : ids) {
            Exercise exercise = exerciseService.getExerciseById(id);

            if (exercise != null) {
                exercises.add(exercise);
            }
        }

        return exercises;
    }
    public Routine deleteRoutine(Integer id) {
        return routines.remove(id);
    }

    public Routine patchRoutine(Integer id, Map<String, Object> updates) {
        Routine routine = routines.get(id);

        if (routine != null) {
            if (updates.containsKey("name")) {
                routine.setName((String) updates.get("name"));
            }
            if (updates.containsKey("description")) {
                routine.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("difficulty")) {
                routine.setDifficulty((String) updates.get("difficulty"));
            }
            if (updates.containsKey("imageUrl")) {
                routine.setImageUrl((String) updates.get("imageUrl"));
            }
            return routine;
        }
        return null;
    }
}