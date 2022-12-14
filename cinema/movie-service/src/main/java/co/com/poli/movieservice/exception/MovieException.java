package co.com.poli.movieservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MovieException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public MovieException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
