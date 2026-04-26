package com.gymapp.gymapp.Entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String difficulty;
    private String imageUrl;

    // Relationship 1:N - Many routines belong to one trainer
    @ManyToOne
    private Trainer trainer;

    // Relationship N:M - Many routines can have many exercises
    // mappedBy = "trainer" indicates that this is the "many" side of the
    // relationship
    @ManyToMany
    private List<Exercise> exercises = new ArrayList<>();

    public Routine() {
        this.imageUrl = "/images/default-routine.png";
    }

    public Routine(String name, String description, String difficulty, String imageUrl,
            List<Exercise> exercises, Trainer trainer) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-routine.png"
                : imageUrl;
        this.exercises = (exercises != null) ? exercises : new ArrayList<>();
        this.trainer = trainer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}