package com.test_webflux.Spring_with_webflux.controller;
import com.test_webflux.Spring_with_webflux.model.Studio;
import com.test_webflux.Spring_with_webflux.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/studios")
public class StudioController {

    @Autowired
    private StudioService studioService;

    public StudioController(StudioService studioService){
        this.studioService = studioService;
    }

    @GetMapping
    public Flux<Studio> listAll(){
        return studioService.findAll();
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Studio>> findById(@PathVariable String id){
        return studioService.findById(id)
                .map(studio -> ResponseEntity.ok(studio))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Studio> save(@RequestBody Studio studio){
        return studioService.save(studio);
    }

}
