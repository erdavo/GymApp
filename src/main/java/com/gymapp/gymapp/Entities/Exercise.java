package com.gymapp.gymapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String muscleGroup;
    private String difficulty;
    private String imageUrl;

    // Relationship N:M - Many exercises can belong to many routines
    @ManyToMany(mappedBy = "exercises")
    private List<Routine> routines = new ArrayList<>();

    public Exercise() {
        this.imageUrl = "/images/default-exercise.png";
    }

    public Exercise(Long id, String name, String description, String muscleGroup, String difficulty, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-exercise.png"
                : imageUrl;
    }

    public Exercise(Long id, String name, String description, String muscleGroup, String difficulty) {
        this(id, name, description, muscleGroup, difficulty, "/images/default-exercise.png");
    }

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

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
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
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-exercise.png"
                : imageUrl;
    }
}