package com.test_webflux.Spring_with_webflux.service;
import com.test_webflux.Spring_with_webflux.model.Studio;
import com.test_webflux.Spring_with_webflux.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudioService implements StudioServiceInterface{

    @Autowired
    private StudioRepository studioRepository;

    @Override
    public Flux<Studio> findAll() {
        return studioRepository.findAll();
    }
    @Override
    public Mono<Studio> findById(String id) {
        return studioRepository.findById(id);
    }
    @Override
    public Mono<Studio> save(Studio studio) {
        return studioRepository.save(studio);
    }
    @Override
    public Mono<Studio> update() {
        return null;
    }
    @Override
    public Mono<Studio> delete() {
        return null;
    }
}