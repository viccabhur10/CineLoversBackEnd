package com.victor.cinelovers.controller;

import com.victor.cinelovers.entity.Rating;
import com.victor.cinelovers.repository.RatingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // 1. Ver TODAS las valoraciones del sistema
    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    // 2. Ver las valoraciones de un usuario concreto (ej: las de Ana)
    // Se usa así en el navegador: /ratings/user/1
    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUser(@PathVariable Long userId) {
        return ratingRepository.findByUserId(userId);
    }


    // 3. Ver las valoraciones de una película concreta (ej: las de Inception)
    // Se usa así en el navegador: /ratings/movie/1
    @GetMapping("/movie/{movieId}")
    public List<Rating> getRatingsByMovie(@PathVariable Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }
    @PostMapping // Para guardar una valoración nueva
    public Rating addRating(@RequestBody Rating newRating) {
        return ratingRepository.save(newRating);
    }
}