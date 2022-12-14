package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.persistence.entity.MovieItem;
import co.com.poli.showtimeservice.persistence.repository.MovieItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieItemServiceImpl implements  MovieItemService{

    private final MovieItemRepository movieItemRepository;
    @Override
    @Transactional(readOnly = true)
    public MovieItem findById(Long id) {
        return movieItemRepository.findById(id).orElse(null);
    }
}
