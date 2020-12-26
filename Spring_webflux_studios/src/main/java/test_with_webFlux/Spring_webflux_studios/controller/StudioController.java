package test_with_webFlux.Spring_webflux_studios.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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
}
