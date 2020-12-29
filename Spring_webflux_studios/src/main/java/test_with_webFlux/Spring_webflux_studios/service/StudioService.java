package test_with_webFlux.Spring_webflux_studios.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;
import test_with_webFlux.Spring_webflux_studios.repository.StudioRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudioService implements StudioServiceInterface {

    private final StudioRepository studioRepository;

    public Flux<Studio> findAll() {
        return studioRepository.findAll();
    }
    public Mono<Studio> findById(String id) {
        return studioRepository.findById(id)
                .switchIfEmpty(notFoundResponseException(id))
                .log();
    }
    public Mono<Studio> save(Studio studio) {
        return studioRepository.save(studio);
    }
    public Mono<Void> update(Studio studio) {
        return findById(studio.getId())
                .map(studioExist -> studio.withId(studioExist.getId()))
                .flatMap(studioRepository::save)
                .then();
    }
    public Mono<Void> delete(String id) {
        return findById(id)
                .flatMap(studioRepository::delete);
    }
    public <T> Mono<T> notFoundResponseException(String id){
        return Mono.error(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Studio com Id {" + id + "} não encontrado"));
    }
}