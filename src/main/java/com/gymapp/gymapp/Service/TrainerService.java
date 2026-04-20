package com.gymapp.gymapp.Service;

import com.gymapp.gymapp.Model.Routine;
import com.gymapp.gymapp.Model.Trainer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrainerService {
    private final Map<Integer, Trainer> trainers;
    private int nextId = 1;

    public TrainerService(){
        trainers = new ConcurrentHashMap<>();

        createTrainer(new Trainer(null, "Trainer 1", "email@email.com", "City/Country, years old"));
        createTrainer(new Trainer(null, "Trainer 2", "email@email.com", "City/Country, years old"));
        createTrainer(new Trainer(null, "Trainer 3", "email@email.com", "City/Country, years old"));
    }


    public Collection<Trainer> getAllTrainers() { return trainers.values(); }

    public Trainer getTrainerById(Integer id) {
        return trainers.get(id);
    }

    public Trainer createTrainer(Trainer trainer) {
        trainer.setId(nextId);
        trainers.put(nextId, trainer);
        nextId++;
        return trainer;
    }

    public Trainer updateTrainer(Integer id, Trainer updatedTrainer) {
        if (trainers.containsKey(id)) {
            updatedTrainer.setId(id);
            trainers.put(id, updatedTrainer);
            return updatedTrainer;
        }
        return null;
    }

    public Trainer deleteTrainer(Integer id) {
        return trainers.remove(id);
    }

    public Trainer patchTrainer(Integer id, Map<String, Object> updates) {
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
