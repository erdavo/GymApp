package com.gymapp.gymapp.RestController;

import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Service.RoutineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class RoutineRestController {
    private final RoutineService routineService;

    public RoutineRestController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping("/api/routines")
    public ResponseEntity<Collection<Routine>> getAllRoutines() {
        Collection<Routine> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/api/routines/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable Long id) {
        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/routines")
    public ResponseEntity<Routine> createRoutine(@RequestBody Map<String, Object> routineData) {
        // Extract data from the received Map
        String name = (String) routineData.get("name");  // Routine name
        String description = (String) routineData.get("description");  // Routine description
        String difficulty = (String) routineData.get("difficulty");  // Routine difficulty (e.g., "Easy", "Medium", "Hard")
        String imageUrl = (String) routineData.get("imageUrl");  // Image URL for the routine
        Long trainerId = Long.valueOf(routineData.get("trainerId").toString());  // Trainer ID to associate with the routine
        List<Long> exerciseIds = (List<Long>) routineData.get("exerciseIds");  // List of exercise IDs to associate with the routine

        // Create a new routine object with the extracted data
        Routine routine = new Routine();
        routine.setName(name);  // Set the routine's name
        routine.setDescription(description);  // Set the routine's description
        routine.setDifficulty(difficulty);  // Set the routine's difficulty
        routine.setImageUrl(imageUrl);  // Set the routine's image URL

        // Call the service method to save the routine, associating the exercises and trainer by their IDs
        routineService.saveRoutineWithIds(routine, exerciseIds, trainerId);

        // Return the created routine with a 201 (Created) status
        return ResponseEntity.status(HttpStatus.CREATED).body(routine);
    }

    @PutMapping("/api/routines/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable Long id, @RequestBody Map<String, Object> routineData) {
        String name = (String) routineData.get("name");
        String description = (String) routineData.get("description");
        String difficulty = (String) routineData.get("difficulty");
        String imageUrl = (String) routineData.get("imageUrl");
        Long trainerId = Long.valueOf(routineData.get("trainerId").toString());
        List<Long> exerciseIds = (List<Long>) routineData.get("exerciseIds");

        // Buscar la rutina existente
        Routine existingRoutine = routineService.getRoutineById(id);

        if (existingRoutine != null) {

            existingRoutine.setName(name);
            existingRoutine.setDescription(description);
            existingRoutine.setDifficulty(difficulty);
            existingRoutine.setImageUrl(imageUrl);

            routineService.saveRoutineWithIds(existingRoutine, exerciseIds, trainerId);

            return ResponseEntity.ok(existingRoutine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/routines/{id}")
    public ResponseEntity<Routine> deleteRoutine(@PathVariable Long id) {
        Routine routine = routineService.getRoutineById(id);

        if (routine != null) {
            routineService.deleteRoutine(id);
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/routines/{id}")
    public ResponseEntity<Routine> patchRoutine(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Routine routine = routineService.patchRoutine(id, updates);

        if (routine != null) {
            return ResponseEntity.ok(routine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}