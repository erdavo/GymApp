package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Routine;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class RoutineService {
    private final Map<Integer, Routine> routines;
    private int nextId = 4;

    public RoutineService() {
        routines = new ConcurrentHashMap<>();
        routines.put(1, new Routine(1, "PPL", "Skinny routines", "Medium"));
        routines.put(2, new Routine(2, "Full body", "To skinny routine", "Too easy"));
        routines.put(3, new Routine(3, "Upper-Lower", "Light weight baibe routine", "Too hard"));
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