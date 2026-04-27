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
    private final Map<Long, Routine> routines;
    private Long nextId = 1L;
    private final ExerciseService exerciseService ;
    private final TrainerService trainerService ;

    public RoutineService(ExerciseService exerciseService, TrainerService trainerService) {
        this.exerciseService = exerciseService;
        this.trainerService = trainerService;
        this.routines = new ConcurrentHashMap<>();

        createRoutine(new Routine(
                "PPL",
                "A Push Pull Legs routine designed to build muscle and improve overall strength with a balanced weekly split.",
                "Medium",
                "/images/ppl_routine.jpg",
                getExercisesByIds(3L, 5L, 2L, 8L),
                trainerService.getTrainerById(1L)
        ));

        createRoutine(new Routine(
                "Full Body",
                "A full body routine ideal for beginners who want to train all major muscle groups in a simple and effective way.",
                "Easy",
                "/images/full_body_routine.jpg",
                getExercisesByIds(3L, 17L, 14L, 15L),
                trainerService.getTrainerById(2L)
        ));

        createRoutine(new Routine(
                "Upper-Lower",
                "An upper lower split focused on building strength and muscle by dividing workouts between upper-body and lower-body days.",
                "Hard",
                "/images/upper_lower_routine.jpg",
                getExercisesByIds(1L, 2L, 13L, 11L),
                trainerService.getTrainerById(3L)
        ));
    }

    public Collection<Routine> getAllRoutines() {
        return routines.values();
    }

    public Routine getRoutineById(Long id) {
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

    public Routine updateRoutine(Long id, Routine updatedRoutine) {
        if (routines.containsKey(id)) {
            updatedRoutine.setId(id);
            routines.put(id, updatedRoutine);
            return updatedRoutine;
        }
        return null;
    }

    private List<Exercise> getExercisesByIds(Long... ids) {
        List<Exercise> exercises = new ArrayList<>();

        for (Long id : ids) {
            Exercise exercise = exerciseService.getExerciseById(id);

            if (exercise != null) {
                exercises.add(exercise);
            }
        }

        return exercises;
    }
    public Routine deleteRoutine(Long id) {
        return routines.remove(id);
    }

    public Routine patchRoutine(Long id, Map<String, Object> updates) {
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