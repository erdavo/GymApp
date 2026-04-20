package com.gymapp.gymapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String id;
    private String name;
    private String email;
    private String specialty;
    private String imageUrl;
    // private List<Routine> routines; // 1:N con Routine

    public Trainer(String id, String name, String email, String specialty, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialty = specialty;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-trainer.jpg"
                : imageUrl;
        // this.routines = new ArrayList<>();
    }

    public Trainer(String id, String name, String email, String specialty) {
        this(id, name, email, specialty, "/images/default-exercise.jpg");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-exercise.jpg"
                : imageUrl;
    }

    /*
     * Todavia no porque para la tarea 1 las entidades no se relacionan entre si.
     * Para la tarea 2 se relacionan.
     * 
     * public List<Routine> getRoutines() { return routines; }
     * public void setRoutines(List<Routine> routines) { this.routines = routines; }
     * 
     * public void addRoutine(Routine routine) {
     * this.routines.add(routine);
     * }
     */
}