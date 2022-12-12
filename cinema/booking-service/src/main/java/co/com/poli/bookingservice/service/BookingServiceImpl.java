package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.mapper.BookingDtoToTask;
import co.com.poli.bookingservice.mapper.BookingUpdateDtoToTask;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import co.com.poli.bookingservice.service.dto.BookingDto;
import co.com.poli.bookingservice.service.dto.BookingUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{


    private final BookingRepository bookingRepository;
    private final BookingDtoToTask bookingDtoToTask;
    private final BookingUpdateDtoToTask bookingUpdateDtoToTask;
    @Override
    public void save(BookingDto bookingDto) {
        Booking booking = bookingDtoToTask.map(bookingDto);
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public void update(BookingUpdateDto bookingUpdateDto, Long id) {
        Booking booking = bookingUpdateDtoToTask.map(bookingUpdateDto);
        booking.setId(id);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return  bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
