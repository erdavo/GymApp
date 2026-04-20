package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Exercise;
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
    public ResponseEntity<Collection<Routine>> getAllRoutines() {
        Collection<Routine> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
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
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
        Routine created = routineService.createRoutine(routine);
        return new ResponseEntity<>(created, org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/api/routines/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable Integer id, @RequestBody Routine updatedRoutine) {
        Routine routine = routineService.updateRoutine(id, updatedRoutine);

        if (routine != null) {
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
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