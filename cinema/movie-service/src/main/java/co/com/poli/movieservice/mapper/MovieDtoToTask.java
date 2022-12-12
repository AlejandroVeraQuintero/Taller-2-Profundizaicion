package co.com.poli.movieservice.mapper;

import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.service.dto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoToTask implements IMapper<MovieDto, Movie> {
    @Override
    public Movie map(MovieDto in) {
        Movie movie = new Movie();
        movie.setId(in.getId());
        movie.setTitle(in.getTitle());
        movie.setDirector(in.getDirector());
        movie.setRating(in.getRating());
        return movie;
    }
}
