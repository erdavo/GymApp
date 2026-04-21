package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Routine;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class RoutineService {
    private final Map<Integer, Routine> routines;
    private int nextId = 1;

    public RoutineService() {
        routines = new ConcurrentHashMap<>();
        createRoutine(new Routine(
                null,
                "PPL",
                "A Push Pull Legs routine designed to build muscle and improve overall strength with a balanced weekly split.",
                "Medium",
                "/images/ppl_routine.jpg"
        ));

        createRoutine(new Routine(
                null,
                "Full Body",
                "A full body routine ideal for beginners who want to train all major muscle groups in a simple and effective way.",
                "Easy",
                "/images/full_body_routine.jpg"
        ));

        createRoutine(new Routine(
                null,
                "Upper-Lower",
                "An upper lower split focused on building strength and muscle by dividing workouts between upper-body and lower-body days.",
                "Hard",
                "/images/upper_lower_routine.jpg"
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