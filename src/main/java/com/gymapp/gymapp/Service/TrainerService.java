package com.gymapp.gymapp.Service;

import org.springframework.stereotype.Service;

import com.gymapp.gymapp.Entities.Routine;
import com.gymapp.gymapp.Entities.Trainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrainerService {
    private final Map<Long, Trainer> trainers;
    private Long nextId = 1L;

    public TrainerService(){
        trainers = new ConcurrentHashMap<>();

        createTrainer(new Trainer(
                "Alex Carter",
                "alex.carter@gymapp.com",
                "Madrid, Spain - 28 years old, Strength & Conditioning Specialist",
                "/images/alex_carter_trainer.jpg",
                new ArrayList<>()

        ));

        createTrainer(new Trainer(
                "Sofia Martinez",
                "sofia.martinez@gymapp.com",
                "Barcelona, Spain - 32 years old, Functional Training & HIIT Coach",
                "/images/sofia_martinez_trainer.jpg",
                new ArrayList<>()

        ));

        createTrainer(new Trainer(
                "Daniel Lopez",
                "daniel.lopez@gymapp.com",
                "Valencia, Spain - 35 years old, Personal Trainer & Nutrition Expert",
                "/images/daniel_lopez_trainer.jpg",
                new ArrayList<>()
        ));
    }


    public Collection<Trainer> getAllTrainers() { return trainers.values(); }

    public Trainer getTrainerById(Long id) {
        return trainers.get(id);
    }

    public Trainer createTrainer(Trainer trainer) {
        trainer.setId(nextId);
        trainers.put(nextId, trainer);
        nextId++;
        return trainer;
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        if (trainers.containsKey(id)) {
            updatedTrainer.setId(id);
            trainers.put(id, updatedTrainer);
            return updatedTrainer;
        }
        return null;
    }

    public Trainer deleteTrainer(Long id) {
        return trainers.remove(id);
    }

    public Trainer patchTrainer(Long id, Map<String, Object> updates) {
        Trainer trainer = trainers.get(id);

        if (trainer != null) {

            if (updates.containsKey("name")) {
                trainer.setName((String) updates.get("name"));
            }
            if (updates.containsKey("email")) {
                trainer.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("description")) {
                trainer.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("imageUrl")) {
                trainer.setImageUrl((String) updates.get("imageUrl"));
            }
            return trainer;
        }
        return null;
    }
}
