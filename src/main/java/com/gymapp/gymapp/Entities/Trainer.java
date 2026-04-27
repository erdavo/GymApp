package com.gymapp.gymapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String description;
    private String imageUrl;

    /**
     * Relationship 1:N - One trainer can have many routines
     * mappedBy = "trainer" indicates that this is the "one" side of the
     * relationship,
     * and the "many" side is defined in the Routine entity with @ManyToOne.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "trainer")
    private List<Routine> routines = new ArrayList<>();

    public Trainer() {
        this.imageUrl = "/images/default-trainer.png";
    }

    // Constructor without ID for persistence (Database handles the ID)
    public Trainer(Integer id, String name, String email, String description, String imageUrl) {
        this.id = id ;
        this.name = name;
        this.email = email;
        this.description = description;
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-trainer.png"
                : imageUrl;
        this.routines = routines;
    }

    // Constructor without ID for persistence (Database handles the ID)
    public Trainer(Integer id, String name, String email, String description) {
        this(id, name, email, description, "/images/default-trainer.png");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = (imageUrl == null || imageUrl.isBlank())
                ? "/images/default-trainer.png"
                : imageUrl;
    }

    // Relationship methods are NOW ACTIVE for Part II
    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

}