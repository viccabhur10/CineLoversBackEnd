package com.victor.cinelovers.service;

import com.victor.cinelovers.entity.Movie;
import com.victor.cinelovers.entity.Rating;
import com.victor.cinelovers.repository.MovieRepository;
import com.victor.cinelovers.repository.RatingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RecommendationService(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    public List<Movie> getRecommendationsForUser(Long userId) {
        List<Rating> myRatings = ratingRepository.findByUserId(userId);

        if (myRatings.isEmpty()) {
            return getTopRatedMoviesFallback();
        }

        List<Long> myLikedMovieIds = myRatings.stream()
                .filter(r -> r.getScore() >= 4)
                .map(r -> r.getMovie().getId())
                .toList();

        if (myLikedMovieIds.isEmpty()) {
             return getTopRatedMoviesFallback();
        }

        List<Rating> allRatings = ratingRepository.findAll();

        Set<Long> similarUserIds = allRatings.stream()
                .filter(r -> !r.getUser().getId().equals(userId))
                .filter(r -> myLikedMovieIds.contains(r.getMovie().getId()))
                .filter(r -> r.getScore() >= 4)
                .map(r -> r.getUser().getId())
                .collect(Collectors.toSet());

        List<Movie> recommendedMovies = allRatings.stream()
                .filter(r -> similarUserIds.contains(r.getUser().getId()))
                .filter(r -> r.getScore() >= 4)
                .filter(r -> !myLikedMovieIds.contains(r.getMovie().getId()))
                .map(Rating::getMovie)
                .distinct()
                .limit(5)
                .collect(Collectors.toList());

        if (recommendedMovies.isEmpty()) {
            return getTopRatedMoviesFallback();
        }

        return recommendedMovies;
    }

    private List<Movie> getTopRatedMoviesFallback() {
        return movieRepository.findTopRatedMovies(PageRequest.of(0, 5));
    }
}