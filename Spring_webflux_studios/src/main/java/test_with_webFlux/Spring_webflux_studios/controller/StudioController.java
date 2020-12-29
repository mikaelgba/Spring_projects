package test_with_webFlux.Spring_webflux_studios.controller;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;
import test_with_webFlux.Spring_webflux_studios.service.StudioService;

@RestController
@RequestMapping("studios")
@Slf4j
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;

    @GetMapping
    public Flux<Studio> listAll(){
        return studioService.findAll();
    }
    @GetMapping("/{id}")
    public Mono<Studio> findById(@PathVariable String id){
        return studioService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Studio> save(@Valid @RequestBody Studio studio){
        return studioService.save(studio);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable String id, @Valid @RequestBody Studio studio){
        return studioService.update(studio.withId(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id){
        return studioService.delete(id);
    }
}
