package com.gymapp.gymapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * Relationship N:M - Many exercises can belong to many routines
     * mappedBy = "exercises" indicates that this is the "many" side of the
     * relationship,
     * and the "one" side is defined in the Routine entity with @ManyToMany.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "exercises")
    private List<Routine> routines = new ArrayList<>();

    public Exercise() {
        this.imageUrl = "/images/default-exercise.png";
    }

    // Constructor without ID for persistence (Database handles the ID)
    public Exercise(String name, String description, String muscleGroup, String difficulty, String imageUrl) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-exercise.png"
                : imageUrl;
    }

    public Exercise(String name, String description, String muscleGroup, String difficulty) {
        this(name, description, muscleGroup, difficulty, "/images/default-exercise.png");
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