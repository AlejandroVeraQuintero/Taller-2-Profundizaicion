package co.com.poli.showtimeservice.service.dto;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShowTimeDto {

    private Long id;
    private Date date;
    private List<MovieItemDto> movies;

}
