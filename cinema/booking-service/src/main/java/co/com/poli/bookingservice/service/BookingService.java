package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.service.dto.BookingDto;
import co.com.poli.bookingservice.service.dto.BookingResponseDto;
import co.com.poli.bookingservice.service.dto.BookingUpdateDto;

import java.util.List;

public interface BookingService {
    void save(BookingDto bookingDto);
    void delete(Booking booking);

    void update(BookingUpdateDto bookingUpdateDto, Long id);
    List<Booking> findAll();
    BookingResponseDto findById(Long id);
    List<Booking> findByUserId(Long userId);
}
