package com.victor.cinelovers.controller;

import com.victor.cinelovers.entity.Movie;
import com.victor.cinelovers.repository.MovieRepository;
import com.victor.cinelovers.service.RecommendationService; // <--- Importante
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final RecommendationService recommendationService; // <--- Nuevo cerebro

    // Inyectamos tanto el repositorio como el servicio
    public MovieController(MovieRepository movieRepository, RecommendationService recommendationService) {
        this.movieRepository = movieRepository;
        this.recommendationService = recommendationService;
    }

    // 1. Ver todas las películas 
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // 2. Pedir recomendaciones para un usuario
   
    @GetMapping("/recommendations/{userId}")
    public List<Movie> getRecommendations(@PathVariable Long userId) {
        return recommendationService.getRecommendationsForUser(userId);
    }
    
    // 3. BUSCADOR POR ETIQUETAS
    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String query) {
        return movieRepository.searchByTag(query);
    }
}