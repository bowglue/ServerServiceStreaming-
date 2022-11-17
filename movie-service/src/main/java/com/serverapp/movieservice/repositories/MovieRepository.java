package com.serverapp.movieservice.repositories;

import com.serverapp.movieservice.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT m.name as name, m.wide_image as wide_image, m.title_image as title_image FROM movie m")
    Page<WideMovie> findWide(Pageable page);

    @Query(value = "SELECT m.name as name, m.poster_image as poster_image, m.title_image as title_image FROM movie m")
    Page<PosterMovie> findPoster(Pageable page);


}
