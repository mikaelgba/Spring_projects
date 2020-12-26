package test_with_webFlux.Spring_webflux_studios.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;

public interface StudioRepository extends ReactiveMongoRepository<Studio, Integer> {
}
