package co.com.poli.bookingservice.service.dto;

import lombok.Data;


import java.util.List;

@Data
public class BookingDto {

    private Long id;
    private Long userId;
    private Long showtimeId;
    private List<MovieItemDto> movies;
}
