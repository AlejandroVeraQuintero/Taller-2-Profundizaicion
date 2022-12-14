package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import co.com.poli.showtimeservice.persistence.entity.ShowTime;

public interface MovieItemService {
    MovieItem findById(Long id);
}
