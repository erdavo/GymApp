package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Trainer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrainerService {
    private final Map<String, Trainer> trainers;

    public TrainerService(){
        trainers = new ConcurrentHashMap<>();
        trainers.put("1", new Trainer("1", "Trainer 1", "City/Country, years old", "Speciality"));
        trainers.put("2", new Trainer("2", "Trainer 2", "City/Country, years old", "Speciality"));
        trainers.put("3", new Trainer("3", "Trainer 3", "City/Country, years old", "Speciality"));
    }

    public Collection<Trainer> getAllTrainers() { return trainers.values(); }

    public Trainer createTrainer(Trainer trainer) {
        trainers.put(trainer.getId(), trainer);
        return trainer;
    }

    public Trainer updateTrainer(String id, Trainer updatedTrainer) {
        if (trainers.containsKey(id)) {
            updatedTrainer.setId(id);
            trainers.put(id, updatedTrainer);
            return updatedTrainer;
        }
        return null;
    }

    public Trainer deleteTrainer(String id) {
        return trainers.remove(id);
    }

    public Trainer patchTrainer(String id, Map<String, Object> updates) {
        Trainer trainer = trainers.get(id);

        if (trainer != null) {

            if (updates.containsKey("name")) {
                trainer.setName((String) updates.get("name"));
            }
            if (updates.containsKey("email")) {
                trainer.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("speciality")) {
                trainer.setSpeciality((String) updates.get("speciality"));
            }
            if (updates.containsKey("imageUrl")) {
                trainer.setImageUrl((String) updates.get("imageUrl"));
            }
            return trainer;
        }
        return null;
    }
}
