package com.test_webflux.Spring_with_webflux.repository;
import com.test_webflux.Spring_with_webflux.model.Studio;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudioRepository extends ReactiveMongoRepository <Studio, String> {
}
