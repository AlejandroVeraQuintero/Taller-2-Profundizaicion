package co.com.poli.userservice.clientFeigh;

import co.com.poli.userservice.helpers.Response;
import co.com.poli.userservice.helpers.ResponseBuild;
import co.com.poli.userservice.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClientImplHystrixFallBack implements BookingClient {

    private final ResponseBuild build;
    @Override
    public Response findByUserId(Long userId) {
        return build.success(new Booking());
    }
}
