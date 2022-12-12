package co.com.poli.movieservice.controller;

import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.service.MovieService;
import co.com.poli.movieservice.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody MovieDto movieDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }
        movieService.save(movieDto);
        return builder.success(movieDto);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Movie user = (Movie) findByID(id).getData();
        if(user==null){
            return builder.failed("Not found");
        }
        movieService.delete(user);
        return builder.success(user);
    }

    @GetMapping
    public Response findAll(){
        return builder.success(movieService.findAll());
    }


    @GetMapping("/{id}")
    public Response findByID(@PathVariable("id") Long id){
        return builder.success(movieService.findById(id));
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
