package co.com.poli.showtimeservice.mapper;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import co.com.poli.showtimeservice.service.dto.MovieItemDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeUpdateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowTimeUpdateDtoToTask implements IMapper<ShowTimeUpdateDto, ShowTime> {
    @Override
    public ShowTime map(ShowTimeUpdateDto in) {
        ShowTime showTime = new ShowTime();
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
