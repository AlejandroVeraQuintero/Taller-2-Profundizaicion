package co.com.poli.bookingservice;

import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;


@DataJpaTest
public class BookingRepositoryMockTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void when_findById_return_ListBookings() {
        long id = 1;
        Booking booking = Booking.builder()
                .id(id)
                .userId(5L)
                .showtimeId(2L)
                .build();

        bookingRepository.save(booking);
        List<Booking> bookingDt = bookingRepository.findByUserId(booking.getUserId());
        Assertions.assertThat(bookingDt.size()).isEqualTo(1);
    }
}
