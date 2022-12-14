package co.com.poli.bookingservice.clientFeigh;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service", fallback = ShowTimeClientImplHystrixFallBack.class)
public interface ShowTimeClient {
    @GetMapping("/cinema/api/v1/showtimes/{id}")
    Response findByID(@PathVariable("id") Long id);
}
