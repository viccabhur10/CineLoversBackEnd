package com.victor.cinelovers.repository;

import com.victor.cinelovers.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
@Query("SELECT m FROM Movie m JOIN Rating r ON m.id = r.movie.id GROUP BY m.id ORDER BY AVG(r.score) DESC")
    List<Movie> findTopRatedMovies(Pageable pageable);

@Query(value = """
    SELECT DISTINCT m.* FROM movies m
    JOIN movie_user_tags mut ON m.id = mut.movie_id
    JOIN tags t ON mut.tag_id = t.id
    WHERE t.name LIKE %:keyword%
    """, nativeQuery = true)
List<Movie> searchByTag(String keyword);
}