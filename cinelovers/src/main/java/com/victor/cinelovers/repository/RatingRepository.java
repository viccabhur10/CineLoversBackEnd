package com.victor.cinelovers.repository;

import com.victor.cinelovers.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    // Spring crea esta consulta automáticamente solo por el nombre del método:
    // "Busca Ratings donde el User Id sea igual a..."
    List<Rating> findByUserId(Long userId);

    // "Busca Ratings donde el Movie Id sea igual a..."
    List<Rating> findByMovieId(Long movieId);
}