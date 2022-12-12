package co.com.poli.movieservice.service;

import co.com.poli.movieservice.mapper.MovieDtoToTask;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import co.com.poli.movieservice.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService  {

    private final MovieRepository movieRepository;
    private final MovieDtoToTask movieDtoToTask;

    @Override
    public void save(MovieDto movieDto) {
        Movie movie =  movieDtoToTask.map(movieDto);
        movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
