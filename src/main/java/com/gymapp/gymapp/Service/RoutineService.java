package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Entities.Exercise;
import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Repository.ExerciseRepository;
import com.gymapp.gymapp.Repository.RoutineRepository;
import com.gymapp.gymapp.Repository.TrainerRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    // Retrieve all routines from the database
    public Collection<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    // Find a specific routine by its ID
    public Routine getRoutineById(Long id) {
        return routineRepository.findById(id).orElse(null);
    }

    // Persist a new routine in the database
    public Routine createRoutine(Routine routine) {
        return routineRepository.save(routine);
    }

    // Resolves exercise and trainer relationships by ID and persists the routine
    public void saveRoutineWithIds(Routine routine, List<Long> exerciseIds, Long trainerId) {
        if (exerciseIds != null) {
            routine.setExercises(exerciseRepository.findAllById(exerciseIds));
        }
        if (trainerId != null) {
            trainerRepository.findById(trainerId).ifPresent(routine::setTrainer);
        }
        routineRepository.save(routine);
    }

    // Update an existing routine completely (PUT equivalent)
    public Routine updateRoutine(Long id, Routine updatedRoutine) {
        if (routineRepository.existsById(id)) {
            updatedRoutine.setId(id);
            return routineRepository.save(updatedRoutine);
        }
        return null;
    }

    // Remove a routine from the database
    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id).orElse(null);
        if (routine != null) {
            Hibernate.initialize(routine.getExercises());
            routineRepository.deleteById(id);
        }
    }

    // Apply partial updates to a routine (PATCH equivalent)
    public Routine patchRoutine(Long id, Map<String, Object> updates) {
        updates.remove("id");
        updates.remove("_method");
        updates.remove("_csrf");

        Optional<Routine> op = routineRepository.findById(id);

        if (op.isPresent()) {
            Routine routine = op.get();

            if (updates.containsKey("name") && updates.get("name") != null) {
                routine.setName((String) updates.get("name"));
            }
            if (updates.containsKey("description") && updates.get("description") != null) {
                routine.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("difficulty") && updates.get("difficulty") != null) {
                routine.setDifficulty((String) updates.get("difficulty"));
            }
            if (updates.containsKey("trainerId") && updates.get("trainerId") != null) {
                Long tId = Long.valueOf(updates.get("trainerId").toString());
                trainerRepository.findById(tId).ifPresent(routine::setTrainer);
            }
            if (updates.containsKey("exerciseIds") && updates.get("exerciseIds") != null) {
                List<?> rawIds = (List<?>) updates.get("exerciseIds");
                List<Long> ids = rawIds.stream()
                        .map(idObj -> Long.valueOf(idObj.toString()))
                        .collect(Collectors.toList());
                List<Exercise> exercises = exerciseRepository.findAllById(ids);

                routine.setExercises(new ArrayList<>(exercises));
            }


            return routineRepository.save(routine);
        }
        return null;
    }
}