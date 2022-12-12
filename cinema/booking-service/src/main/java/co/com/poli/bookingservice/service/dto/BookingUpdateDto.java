package co.com.poli.bookingservice.service.dto;

import lombok.Data;

import java.util.List;
@Data
public class BookingUpdateDto {
    private Long userId;
    private Long showtimeId;
    private List<MovieItemDto> movies;
}
