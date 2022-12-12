package co.com.poli.movieservice.service;

import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.service.dto.MovieDto;

import java.util.List;

public interface MovieService {

    void save(MovieDto movieDto);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(Long id);
}
