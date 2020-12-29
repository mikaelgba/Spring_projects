package test_with_webFlux.Spring_webflux_studios;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import test_with_webFlux.Spring_webflux_studios.entity.Studio;
import test_with_webFlux.Spring_webflux_studios.repository.StudioRepository;

@SpringBootApplication
public class SpringWebfluxStudiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxStudiosApplication.class, args);
	}
	@Bean
	CommandLineRunner init (ReactiveMongoOperations operations,
							StudioRepository repository){
		return args -> {
			Flux<Studio> studioFlux = Flux.just(
					new Studio(null, "Warner Bros", "Hollywood, Los Angeles, Califórnia, EUA", "1923"),
					new Studio(null, "Paramount Pictures", "Hollywood, Los Angeles, Califórnia, EUA", "1912"),
					new Studio(null, "Sony Pictures Studios", "West Washington Boulevard, Culver City, CA.", "1989"))
					.flatMap(repository::save);

			studioFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}