package co.com.poli.bookingservice.service.dto;

import co.com.poli.bookingservice.model.ShowTime;
import co.com.poli.bookingservice.model.User;
import co.com.poli.bookingservice.persistence.entity.MovieItem;
import lombok.Data;

import java.util.List;
@Data
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private Long showtimeId;
    private List<MovieItem> movies;
    private User user;
    private ShowTime showTime;
}
