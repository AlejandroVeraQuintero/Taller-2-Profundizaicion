package co.com.poli.showtimeservice.mapper;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import co.com.poli.showtimeservice.service.dto.MovieItemDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowTimeDtoToTask implements IMapper<ShowTimeDto, ShowTime>{
    @Override
    public ShowTime map(ShowTimeDto in) {
        ShowTime showTime = new ShowTime();
        showTime.setId(in.getId());
        showTime.setDate(in.getDate());
        showTime.setMovies(ConvertirLista(in.getMovies()));
        return showTime;
    }

    private List<MovieItem> ConvertirLista(List<MovieItemDto> fl){

        List<MovieItem> movies = fl.stream().map(movie ->{
            MovieItem movieItem = new MovieItem();
            movieItem.setId(movie.getId());
            movieItem.setTitle(movie.getTitle());
            return movieItem;
        }).collect(Collectors.toList());
        return movies;
    }
}
