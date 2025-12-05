package com.victor.cinelovers.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score; // Del 1 al 5

    private String review; // Comentario opcional

    // --- RELACIONES (El Puente) ---

    @ManyToOne // Muchas valoraciones son de 1 usuario
    @JoinColumn(name = "user_id", nullable = false) // Conecta con la columna 'user_id' de la DB
    private User user;

    @ManyToOne // Muchas valoraciones son de 1 película
    @JoinColumn(name = "movie_id", nullable = false) // Conecta con la columna 'movie_id' de la DB
    private Movie movie;

    // --- CONSTRUCTORES ---
    public Rating() {}

    public Rating(User user, Movie movie, Integer score, String review) {
        this.user = user;
        this.movie = movie;
        this.score = score;
        this.review = review;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}