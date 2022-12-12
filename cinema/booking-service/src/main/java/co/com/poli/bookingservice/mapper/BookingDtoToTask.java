package co.com.poli.bookingservice.mapper;

import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.entity.MovieItem;
import co.com.poli.bookingservice.service.dto.BookingDto;
import co.com.poli.bookingservice.service.dto.MovieItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingDtoToTask implements IMapper<BookingDto, Booking> {
    @Override
    public Booking map(BookingDto in) {
        Booking booking = new Booking();
        booking.setId(in.getId());
        booking.setUserId(in.getUserId());
        booking.setShowtimeId(in.getShowtimeId());
        booking.setMovies(ConvertirLista(in.getMovies()));
        return  booking;
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
