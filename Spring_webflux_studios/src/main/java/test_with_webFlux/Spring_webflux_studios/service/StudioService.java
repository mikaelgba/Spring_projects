package test_with_webFlux.Spring_webflux_studios.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;
import test_with_webFlux.Spring_webflux_studios.repository.StudioRepository;

@Service
@RequiredArgsConstructor
public class StudioService implements StudioServiceInterface {

    private final StudioRepository studioRepository;

    @Override
    public Flux<Studio> findAll() {
        return studioRepository.findAll();
    }
    @Override
    public Mono<Studio> findById(int id) {
        return null;
    }
    @Override
    public Mono<Studio> save(Studio studio) {
        return null;
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