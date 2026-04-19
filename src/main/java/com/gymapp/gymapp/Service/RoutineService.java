package com.gymapp.gymapp.Service;

// 
import com.gymapp.gymapp.Model.Routine;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Collection;

@Service
public class RoutineService {
    private final Map<String, Routine> routines;

    public RoutineService() {
        routines = new ConcurrentHashMap<>();
        routines.put("1", new Routine("r1", "PPL", "Skinny routines", "Medium"));
        routines.put("2", new Routine("r2", "Full body", "To skinny routine", "Too easy"));
        routines.put("3", new Routine("r3", "Upper-Lower", "Light weight baibe routine", "Too hard"));
    }

    public Collection<Routine> getAllRoutines() {
        return routines.values();
    }

    public Routine getRoutineById(String id) {
        return routines.get(id);
    }
    
    public Routine createRoutine(Routine routine) {
        routines.put(routine.getId(), routine);
        return routine;
    }

    public Routine updateRoutine(String id, Routine updatedRoutine) {
        if (routines.containsKey(id)) {
            updatedRoutine.setId(id);
            routines.put(id, updatedRoutine);
            return updatedRoutine;
        }
        return null;
    }

    public Routine deleteRoutine(String id) {
        return routines.remove(id);
    }

    public Routine patchRoutine(String id, Map<String, Object> updates) {
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
            return routine;
        }
        return null;
    }

}