package co.com.poli.bookingservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookingException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public BookingException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
