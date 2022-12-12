package co.com.poli.bookingservice.controller;

import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.service.BookingService;
import co.com.poli.bookingservice.service.dto.BookingDto;
import co.com.poli.bookingservice.service.dto.BookingUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody BookingDto bookingDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }
        bookingService.save(bookingDto);
        return builder.success(bookingDto);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Booking booking = (Booking) findByID(id).getData();
        if(booking==null){
            return builder.failed("Not found");
        }
        bookingService.delete(booking);
        return builder.success(booking);
    }
    @GetMapping
    public Response findAll(){
        return builder.success(bookingService.findAll());
    }
    @GetMapping("/{id}")
    public Response findByID(@PathVariable("id") Long id){
        return builder.success(bookingService.findById(id));
    }

    @PutMapping("/{id}")
    public  Response update(@PathVariable("id") Long id, @RequestBody BookingUpdateDto bookingUpdateDto){
        Booking booking = (Booking) findByID(id).getData();
        if(booking==null){
            return builder.failed("Not found");
        }
        bookingService.update(bookingUpdateDto, booking.getId());
        return builder.success(booking);
    }

    @GetMapping("/user/{userId}")
    public Response findByUserID(@PathVariable("userId") Long userid){
        return builder.success(bookingService.findByUserId(userid));
    }


    private List<Map<String, String>> formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        return errors;
    }

}
