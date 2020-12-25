package com.test_webflux.Spring_with_webflux.service;
import com.test_webflux.Spring_with_webflux.model.Studio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudioServiceInterface {

    Flux<Studio> findAll();
    Mono<Studio> findById(String id);;
    Mono<Studio> save(Studio studio);
    Mono<Studio> update();
    Mono<Studio> delete();
}