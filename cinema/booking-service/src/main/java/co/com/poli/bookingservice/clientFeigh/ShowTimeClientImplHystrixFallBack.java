package co.com.poli.bookingservice.clientFeigh;


import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.ShowTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowTimeClientImplHystrixFallBack implements ShowTimeClient {

    private final ResponseBuild build;

    @Override
    public Response findByID(Long id) {
        return build.success(new ShowTime());
    }
}
