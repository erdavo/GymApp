package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository repository;

    // Retrieve all routines from the database
    public Collection<Routine> getAllRoutines() {
        return repository.findAll();
    }

    // Find a specific routine by its ID
    public Routine getRoutineById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Persist a new routine in the database
    public Routine createRoutine(Routine routine) {
        return repository.save(routine);
    }

    // Update an existing routine completely (PUT equivalent)
    public Routine updateRoutine(Long id, Routine updatedRoutine) {
        if (repository.existsById(id)) {
            updatedRoutine.setId(id);
            return repository.save(updatedRoutine);
        }
        return null;
    }

    // Remove a routine from the database
    public void deleteRoutine(Long id) {
        repository.deleteById(id);
    }

    // Apply partial updates to a routine (PATCH equivalent)
    public Routine patchRoutine(Long id, Map<String, Object> updates) {
        Optional<Routine> op = repository.findById(id);

        if (op.isPresent()) {
            Routine routine = op.get();
            if (updates.containsKey("name"))
                routine.setName((String) updates.get("name"));
            if (updates.containsKey("description"))
                routine.setDescription((String) updates.get("description"));
            if (updates.containsKey("difficulty"))
                routine.setDifficulty((String) updates.get("difficulty"));
            if (updates.containsKey("imageUrl"))
                routine.setImageUrl((String) updates.get("imageUrl"));
            return repository.save(routine);
        }
        return null;
    }
}