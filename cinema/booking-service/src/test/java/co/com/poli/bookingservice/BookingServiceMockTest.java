package co.com.poli.bookingservice;

import co.com.poli.bookingservice.clientFeigh.ShowTimeClient;
import co.com.poli.bookingservice.clientFeigh.UserClient;
import co.com.poli.bookingservice.mapper.BookingDtoToTask;
import co.com.poli.bookingservice.mapper.BookingUpdateDtoToTask;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import co.com.poli.bookingservice.service.BookingService;
import co.com.poli.bookingservice.service.BookingServiceImpl;
import co.com.poli.bookingservice.service.dto.BookingResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookingServiceMockTest {

    @Mock
    private BookingRepository bookingRepository;
    private BookingService bookingService;
    private BookingDtoToTask bookingDtoToTask;

    private BookingUpdateDtoToTask bookingUpdateDtoToTask;
    private ShowTimeClient showClientClient;
    private UserClient userClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingServiceImpl(bookingRepository,bookingDtoToTask,bookingUpdateDtoToTask,userClient,showClientClient);
        long id = 1;
        List<Booking> listBoking = new ArrayList<Booking>();
        Booking booking = Booking.builder()
                .id(id)
                .userId(5L)
                .showtimeId(2L)
                .build();

        listBoking.add(booking);

        Mockito.when(bookingRepository.findByUserId(booking.getUserId())).thenReturn(listBoking);
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
    }

    @Test
    public void when_findByUserId_return_ListBooking() {
        List<Booking> bookings = bookingService.findByUserId(5L);
        Assertions.assertThat(bookings.size()).isEqualTo(1);
    }

    @Test
    public void when_findById_return_Booking() {
       BookingResponseDto booking = bookingService.findById(1L);
        Assertions.assertThat(booking.getId()).isEqualTo(1);
    }
}
