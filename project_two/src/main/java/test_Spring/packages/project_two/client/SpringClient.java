package test_Spring.packages.project_two.client;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import test_Spring.packages.project_two.entites.HQ;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[]args){

        ResponseEntity<HQ> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/hqs/{id}", HQ.class, 9);
        log.info(entity);

        HQ[] hqs = new RestTemplate().getForObject("http://localhost:8080/hqs/list", HQ[].class);
        log.info(Arrays.toString(hqs));

        //@formatter:off
        ResponseEntity<List<HQ>> exchange = new RestTemplate().exchange(
                "http://localhost:8080/hqs/list",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        //@formatter:on
        log.info(exchange.getBody());

        /*HQ hq = HQ.builder().name("Teen Titans").build();
        HQ hqPost = new RestTemplate().postForObject(
                "http://localhost:8080/hqs", hq, HQ.class);
        log.info("Salvo HQ { " + hqPost + " } ");*/

        //@formatter:off
        HQ hq = HQ.builder().name("Constatine").build();
        ResponseEntity<HQ> hqPost = new RestTemplate().exchange(
                "http://localhost:8080/hqs",
                HttpMethod.POST, new HttpEntity<>(hq),
                new ParameterizedTypeReference<>(){},
                createJsonHeader(),
                HQ.class);
        //@formatter:on
        log.info("Salvo HQ {}" + hqPost);


        //@formatter:off
        HQ hqForPut = hqPost.getBody();
        hqForPut.setName("Constatine - Hellblaze");
        ResponseEntity<Void> hqPut = new RestTemplate().exchange(
                "http://localhost:8080/hqs",
                HttpMethod.PUT, new HttpEntity<>(hqForPut),
                new ParameterizedTypeReference<>(){},
                createJsonHeader(),
                Void.class);
        //@formatter:on
        log.info("Salvo HQ {}" + hqPut);

        //@formatter:off
        ResponseEntity<Void> hqDelete = new RestTemplate().exchange(
                "http://localhost:8080/hqs/{id}",
                HttpMethod.DELETE, null,
                Void.class,
                hqForPut.getId());
        //@formatter:on
        log.info("Salvo HQ {}" + hqDelete);

    }
    public static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}