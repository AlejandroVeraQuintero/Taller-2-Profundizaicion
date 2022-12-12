package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.mapper.ShowTimeDtoToTask;
import co.com.poli.showtimeservice.mapper.ShowTimeUpdateDtoToTask;
import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import co.com.poli.showtimeservice.persistence.repository.ShowTimeRepository;
import co.com.poli.showtimeservice.service.dto.ShowTimeDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements  ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final ShowTimeDtoToTask showTimeDtoToTask;
    private final ShowTimeUpdateDtoToTask showTimeUpdateDtoToTask;

    @Override
    public void save(ShowTimeDto showTimeDto) {

        ShowTime showTime = showTimeDtoToTask.map(showTimeDto);
        showTimeRepository.save(showTime);

    }

    @Override
    public void delete(ShowTime showTime) {
        showTimeRepository.delete(showTime);
    }

    @Override
    public void update(ShowTimeUpdateDto showTimeDto , Long id) {
        ShowTime showTime = showTimeUpdateDtoToTask.map(showTimeDto);
        showTime.setId(id);
        showTimeRepository.save(showTime);
    }

    @Override
    public List<ShowTime> findAll() {
        return  showTimeRepository.findAll();
    }

    @Override
    public ShowTime findById(Long id) {
        return showTimeRepository.findById(id).orElse(null);
    }
}
