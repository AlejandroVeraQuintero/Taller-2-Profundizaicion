package co.com.poli.bookingservice.clientFeigh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import co.com.poli.bookingservice.helpers.Response;

@FeignClient(name = "user-service", fallback = UserClientImplHystrixFallBack.class)
public interface UserClient {

    @GetMapping("/cinema/api/v1/users/{id}")
    Response findByID(@PathVariable("id") Long id);
}
