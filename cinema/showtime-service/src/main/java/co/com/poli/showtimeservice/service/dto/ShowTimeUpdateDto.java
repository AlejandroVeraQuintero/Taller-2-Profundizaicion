package co.com.poli.showtimeservice.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShowTimeUpdateDto {
    private Date date;
    private List<MovieItemDto> movies;

}
