package co.com.poli.movieservice;


import co.com.poli.movieservice.clientFeigh.ShowTimeClient;
import co.com.poli.movieservice.mapper.MovieDtoToTask;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import co.com.poli.movieservice.service.MovieService;
import co.com.poli.movieservice.service.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieService movieService;
    private MovieDtoToTask movieDtoToTask;
    private ShowTimeClient showClientClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        movieService = new MovieServiceImpl(movieRepository,movieDtoToTask,showClientClient);

        long id = 5;
        Movie movie = Movie.builder()
                .id(id)
                .title("Test")
                .director("Vera")
                .rating(4)
                .build();

        Mockito.when(movieRepository.findById(5L)).thenReturn(Optional.of(movie));
    }

    @Test
    public void when_findById_return_Movie() {
        Movie movie = movieService.findById(5L);
        Assertions.assertThat(movie.getTitle()).isEqualTo("Test");
    }

}
