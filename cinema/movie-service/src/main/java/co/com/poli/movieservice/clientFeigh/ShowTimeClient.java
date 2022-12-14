package co.com.poli.movieservice.clientFeigh;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import  co.com.poli.movieservice.helpers.Response;


@FeignClient(name = "showtime-service", fallback = ShowTimeClientImplHystrixFallBack.class)
public interface ShowTimeClient {
    @GetMapping("/cinema/api/v1/showtimes/movieItem/{id}")
    Response findByMovieItemID(@PathVariable("id") Long id);
}
