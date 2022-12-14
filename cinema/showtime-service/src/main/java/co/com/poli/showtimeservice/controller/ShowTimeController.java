package co.com.poli.showtimeservice.controller;

import co.com.poli.showtimeservice.helpers.ResponseBuild;
import co.com.poli.showtimeservice.helpers.Response;
import co.com.poli.showtimeservice.persistence.entity.ShowTime;
import co.com.poli.showtimeservice.service.MovieItemService;
import co.com.poli.showtimeservice.service.ShowTimeService;
import co.com.poli.showtimeservice.service.dto.ShowTimeDto;
import co.com.poli.showtimeservice.service.dto.ShowTimeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {
    private final ShowTimeService showTimeService;
    private final MovieItemService movieItemService;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody ShowTimeDto showTimeDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }
        showTimeService.save(showTimeDto);
        return builder.success(showTimeDto);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        ShowTime showTime = (ShowTime) findByID(id).getData();
        if(showTime==null){
            return builder.failed("Not found");
        }
        showTimeService.delete(showTime);
        return builder.success(showTime);
    }
    @GetMapping
    public Response findAll(){
        return builder.success(showTimeService.findAll());
    }
    @GetMapping("/{id}")
    public Response findByID(@PathVariable("id") Long id){
        return builder.success(showTimeService.findById(id));
    }

    @GetMapping("/movieItem/{id}")
    public Response findByMovieItemID(@PathVariable("id") Long id){
        return builder.success(movieItemService.findById(id));
    }
    
    @PutMapping("/{id}")
    public  Response update(@PathVariable("id") Long id, @RequestBody ShowTimeUpdateDto showTimeDto){
        ShowTime showTime = (ShowTime) findByID(id).getData();
        if(showTime==null){
            return builder.failed("Not found");
        }
        showTimeService.update(showTimeDto, showTime.getId());
        return builder.success(showTime);
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
