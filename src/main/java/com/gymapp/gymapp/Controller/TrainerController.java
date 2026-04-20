package com.gymapp.gymapp.Controller;

import com.gymapp.gymapp.Model.Routine;
import com.gymapp.gymapp.Model.Trainer;
import com.gymapp.gymapp.Model.Trainer;
import com.gymapp.gymapp.Service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) { this.trainerService = trainerService; }

    @GetMapping("/api/trainers")
    public ResponseEntity<Collection<Trainer>> getAllTrainers() {
        Collection<Trainer> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/api/trainers/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Integer id) {
        Trainer trainer = trainerService.getTrainerById(id);

        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/trainers")
    public Trainer createTrainer(@RequestBody Trainer trainer) { return trainerService.createTrainer(trainer); }

    @PutMapping("/api/trainers/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Integer id, @RequestBody Trainer updateTrainer) {
        Trainer trainer = trainerService.updateTrainer(id, updateTrainer);

        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/trainers/{id}")
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Integer id) {
        Trainer trainer = trainerService.getTrainerById(id);

        if (trainer != null) {
            trainerService.deleteTrainer(id);
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/trainers/{id}")
    public ResponseEntity<Trainer> patchTrainer(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Trainer trainer = trainerService.patchTrainer(id, updates);

        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
