package com.test_webflux.Spring_with_webflux.service;
import com.test_webflux.Spring_with_webflux.model.Studio;
import com.test_webflux.Spring_with_webflux.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<Studio>> findById(String id) {
        return studioRepository.findById(id)
                .map(studio -> ResponseEntity.ok(studio))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @Override
    public Mono<Studio> save(Studio studio) {
        return studioRepository.save(studio);
    }
    @Override
    public Mono<ResponseEntity<Studio>> update(String id, Studio studio) {

        return studioRepository.findById(id)
                .flatMap(exist_studio -> {
                    exist_studio.setName(studio.getName());
                    exist_studio.setFoundation_origin(studio.getFoundation_origin());
                    exist_studio.setFounded_in(studio.getFounded_in());
                    return studioRepository.save(exist_studio);
                })
                .map(studio_update -> ResponseEntity.ok(studio_update))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @Override
    public Mono<ResponseEntity<Void>> delete(String id) {
        return studioRepository.findById(id)
                .flatMap(exist_studio ->
                    studioRepository.delete(exist_studio)
                            .then(Mono.just(ResponseEntity.ok().<Void>build())))
                            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}