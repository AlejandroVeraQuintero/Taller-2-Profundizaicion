package co.com.poli.movieservice.service;

import co.com.poli.movieservice.clientFeigh.ShowTimeClient;
import co.com.poli.movieservice.exception.MovieException;
import co.com.poli.movieservice.mapper.MovieDtoToTask;
import co.com.poli.movieservice.model.MovieItem;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import co.com.poli.movieservice.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService  {

    private final MovieRepository movieRepository;
    private final MovieDtoToTask movieDtoToTask;

    private final ShowTimeClient showTimeClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MovieDto movieDto) {
        Movie movie =  movieDtoToTask.map(movieDto);
        movieRepository.save(movie);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Movie movie) {
        if(showTimeClient.findByMovieItemID(movie.getId()).getData() != null) {
            throw new MovieException("No se puede eliminar una  con  tiempo de funcion programado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        movieRepository.delete(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
