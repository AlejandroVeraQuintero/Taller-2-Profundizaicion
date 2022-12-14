package co.com.poli.movieservice;

import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
public class MovieRepositoryMockTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findById_return_Booking() {
        long id = 1;
        Movie movie = Movie.builder()
                .id(id)
                .title("Test")
                .director("Vera")
                .rating(4)
                .build();

        movieRepository.save(movie);
        Optional<Movie> movieDt = movieRepository.findById(id);
        Movie movieData = movieDt.orElse(null);
        Assertions.assertThat(movieData.getId()).isEqualTo(id);
    }
}
