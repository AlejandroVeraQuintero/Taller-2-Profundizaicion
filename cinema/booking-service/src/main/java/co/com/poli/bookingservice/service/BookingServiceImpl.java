package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.clientFeigh.ShowTimeClient;
import co.com.poli.bookingservice.clientFeigh.UserClient;
import co.com.poli.bookingservice.exception.BookingException;
import co.com.poli.bookingservice.mapper.BookingDtoToTask;
import co.com.poli.bookingservice.mapper.BookingUpdateDtoToTask;
import co.com.poli.bookingservice.model.ShowTime;
import co.com.poli.bookingservice.model.User;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import co.com.poli.bookingservice.service.dto.BookingDto;
import co.com.poli.bookingservice.service.dto.BookingResponseDto;
import co.com.poli.bookingservice.service.dto.BookingUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{


    private final BookingRepository bookingRepository;
    private final BookingDtoToTask bookingDtoToTask;
    private final BookingUpdateDtoToTask bookingUpdateDtoToTask;
    private final UserClient userClient;

    private final ShowTimeClient showTimeClient;
    @Override
    public void save(BookingDto bookingDto) {
        ModelMapper modelMapper = new ModelMapper();
        User responseUser = modelMapper.map(userClient.findByID(bookingDto.getUserId()).getData(), User.class);
        ShowTime responseShowTime = modelMapper.map(showTimeClient.findByID(bookingDto.getShowtimeId()).getData(),ShowTime.class);

        if (responseUser == null ){
                throw  new BookingException("El usuario debe ser existente para crea la reserva ", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        if (responseShowTime == null) {
            throw  new BookingException("El la función debe ser existente para crea la reserva ", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        Booking booking = bookingDtoToTask.map(bookingDto);
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public void update(BookingUpdateDto bookingUpdateDto, Long id) {
        ModelMapper modelMapper = new ModelMapper();
        User responseUser = modelMapper.map(userClient.findByID(bookingUpdateDto.getUserId()).getData(), User.class);
        ShowTime responseShowTime = modelMapper.map(showTimeClient.findByID(bookingUpdateDto.getShowtimeId()).getData(),ShowTime.class);

        if (responseUser == null ){
            throw  new BookingException("El usuario debe ser existente para crea la reserva ", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        if (responseShowTime == null) {
            throw  new BookingException("El la función debe ser existente para crea la reserva ", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        Booking booking = bookingUpdateDtoToTask.map(bookingUpdateDto);
        booking.setId(id);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {

        return  bookingRepository.findAll();
    }

    @Override
    public BookingResponseDto findById(Long id) {
        Booking  booking = bookingRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userClient.findByID(booking.getUserId()).getData(), User.class);
        ShowTime showTime = modelMapper.map(showTimeClient.findByID(booking.getShowtimeId()).getData(),ShowTime.class);
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(booking.getId());
        bookingResponseDto.setUserId(booking.getUserId());
        bookingResponseDto.setShowtimeId(booking.getShowtimeId());
        bookingResponseDto.setMovies(booking.getMovies());
        bookingResponseDto.setUser(user);
        bookingResponseDto.setShowTime(showTime);
        return bookingResponseDto;
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
