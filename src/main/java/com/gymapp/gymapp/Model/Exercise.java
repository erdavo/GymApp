package com.gymapp.gymapp.Model;

public class Exercise {
    private String id;
    private String name;
    private String description;
    private String muscleGroup;
    private String difficulty;
    private String imageUrl;


    public Exercise(String id, String name, String description, String muscleGroup, String difficulty, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-exercise.jpg"
                : imageUrl;
    }


    public Exercise(String id, String name, String description, String muscleGroup, String difficulty) {
        this(id, name, description, muscleGroup, difficulty, "/images/default-exercise.jpg");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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