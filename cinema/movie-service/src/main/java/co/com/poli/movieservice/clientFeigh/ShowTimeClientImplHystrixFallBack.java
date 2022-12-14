package co.com.poli.movieservice.clientFeigh;

import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.model.MovieItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowTimeClientImplHystrixFallBack implements  ShowTimeClient{

    private final ResponseBuild build;
    @Override
    public Response findByMovieItemID(Long id) {
            return  build.success(new MovieItem());
    }
}
