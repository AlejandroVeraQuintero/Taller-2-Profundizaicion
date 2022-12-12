package co.com.poli.movieservice.service.dto;

import lombok.Data;


@Data
public class MovieDto {

    private Long id;
    private String title;
    private String director;
    private int rating;
}
