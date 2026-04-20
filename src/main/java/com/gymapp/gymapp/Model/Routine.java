package com.gymapp.gymapp.Model;

public class Routine {
    private String id;
    private String name;
    private String description;
    private String difficulty;
    private String imageUrl;
    // private List<Exercise> exercises;

    public Routine(String id, String name, String description, String difficulty, String imageUrl ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-routine.jpg"
                : imageUrl;
        // this.exercises = exercises;
    }

    public Routine(String id, String name, String description, String difficulty) {
        this(id, name, description, difficulty, "/images/default-routine.jpg");
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
                ? "/images/default-exercise.jpg"
                : imageUrl;
    }

    /* Todavia no porque para la tarea 1 las entidades no se relacionan entre si. Para la tarea 2 se relacionan.
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public void updateExercise(Exercise exercise) {
        exercises.set(exercises.indexOf(exercise), exercise);
    }
    */

}