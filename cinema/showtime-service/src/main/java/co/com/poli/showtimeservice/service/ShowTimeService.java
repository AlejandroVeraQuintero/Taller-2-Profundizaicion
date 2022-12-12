package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import co.com.poli.showtimeservice.service.dto.ShowTimeDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeUpdateDto;

import java.util.List;

public interface ShowTimeService {

    void save(ShowTimeDto showTimeDto);
    void delete(ShowTime showTime);

    void update(ShowTimeUpdateDto showTimeDto, Long id);
    List<ShowTime> findAll();
    ShowTime findById(Long id);
}
