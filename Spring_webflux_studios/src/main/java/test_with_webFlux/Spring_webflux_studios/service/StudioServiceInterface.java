package test_with_webFlux.Spring_webflux_studios.service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;

public interface StudioServiceInterface {

    Flux<Studio> findAll();
    Mono<Studio> findById(String id);
    Mono<Studio> save(Studio studio);
    Mono<Void> update(Studio studio);
    Mono<Void> delete(String id);
}
