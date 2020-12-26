package com.test_webflux.Spring_with_webflux.service;
import com.test_webflux.Spring_with_webflux.model.Studio;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudioServiceInterface {

    Flux<Studio> findAll();
    Mono<ResponseEntity<Studio>> findById(String id);;
    Mono<Studio> save(Studio studio);
    Mono<ResponseEntity<Studio>> update(String id, Studio studio);
    Mono<ResponseEntity<Void>> delete(String id);
}