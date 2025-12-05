package com.victor.cinelovers.entity;

import jakarta.persistence.*;

@Entity // 1. Esto le dice a Java: "Esta clase es una tabla de la DB"
@Table(name = "movies") // 2. "Concretamente, la tabla 'movies'"
public class Movie {

    @Id // 3. "Esta es la llave primaria (Primary Key)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. "El ID es Auto-Incremental"
    private Long id;

    private String title;

    @Column(name = "release_year") // 5. En Java usamos CamelCase, en DB snake_case. Esto los une.
    private Integer releaseYear;

    private String director;

    @Column(columnDefinition = "TEXT") // 6. "Esto es un texto largo"
    private String description;

    // --- CONSTRUCTORES ---
    public Movie() {} 
    public Movie(String title, Integer releaseYear, String director, String description) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.description = description;
    }

    // --- GETTERS Y SETTERS ---
    // (Son los botones para leer/escribir datos en privado)
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}