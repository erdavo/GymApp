package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Routine;
import com.gymapp.gymapp.Service.RoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class RoutineController {
    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping("/api/routines")
    public Collection<Routine> getAllRoutines() {
        return routineService.getAllRoutines();
    }

    @GetMapping("/api/routines/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable Integer id) {
        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/routines")
    public Routine createRoutine(@RequestBody Routine routine) {
        return routineService.createRoutine(routine);
    }

    @PutMapping("/api/routines/{id}")
    public Routine updateRoutine(@PathVariable Integer id, @RequestBody Routine updatedRoutine) {
        return routineService.updateRoutine(id, updatedRoutine);
    }

    @DeleteMapping("/api/routines/{id}")
    public ResponseEntity<Routine> deleteRoutine(@PathVariable Integer id) {
        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            routineService.deleteRoutine(id);
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/routines/{id}")
    public ResponseEntity<Routine> patchRoutine(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Routine routine = routineService.patchRoutine(id, updates);

        if (routine != null) {
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}