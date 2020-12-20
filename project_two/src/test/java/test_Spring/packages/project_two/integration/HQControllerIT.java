package test_Spring.packages.project_two.integration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.repository.HQRepository;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.util.HQCreator;
import test_Spring.packages.project_two.util.HQPostRequestBodyCreator;
import test_Spring.packages.project_two.wapper.PageableResponse;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)// Para deletar tudo no banco antes de cada test
public class HQControllerIT {

    //Tetes de intregarção inicia junto ao servidor
    @Autowired
    private TestRestTemplate testTemplate;
    @Autowired
    private HQRepository hqRepository;

    @Test
    @DisplayName("Teste para HQControllerIT 1 - retorna lista com paginas quando sucesso")
    void method_list_page_when_sucessful_return_listpage(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());
        String name_wait = HQ_save.getName();

        PageableResponse<HQ> hqPage = testTemplate.exchange("/hqs", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<HQ>>(){}).getBody();

        Assertions.assertThat(hqPage)
                .isNotNull();
        Assertions.assertThat(hqPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hqPage.toList()
                .get(0).getName()).isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControllerIT 2 - retorna toda a lista")
    void method_listAll_when_sucessful_return_all_list(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());
        String name_wait = HQ_save.getName();

        List<HQ> hqs = testTemplate.exchange("/hqs/list",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<HQ>>(){}).getBody();

        Assertions.assertThat(hqs)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(hqs.get(0).getName())
                .isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControllerIT 3 - retorna da lista o id procurado")
    void method_finfById_when_sucessful_object_with_id(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());
        Long id_wait = HQ_save.getId();

        HQ hq_with_id = testTemplate.getForObject("/hqs/{id}", HQ.class, id_wait);

        Assertions.assertThat(hq_with_id).
                isNotNull();
        Assertions.assertThat(hq_with_id.getId())
                .isEqualTo(id_wait);
    }
    @Test
    @DisplayName("Teste para HQControllerIT 4 - retorna uma lista com o nome procurado")
    void method_finfByName_when_sucessful_list_with_name(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());
        String name_wait = HQ_save.getName();
        String url_name = String.format("/hqs/find?name=%s", name_wait);

        List<HQ> hq_with_name = testTemplate.exchange(url_name,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<HQ>>(){}).getBody();


        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hq_with_name.get(0).getName())
                .isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControllerIT 5 - quando não encontra alguem com o nome na lista")
    void method_finfByName_when_sucessful_not_found_in_list(){

        List<HQ> hq_with_name = testTemplate.exchange("/hqs/find?name=irineu",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<HQ>>(){}).getBody();

        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isEmpty();
    }
    @Test
    @DisplayName("Teste para HQControllerIT 6 - Salvar na lista")
    void method_save_when_sucessful(){

        HQPostRequestBody hqPostRequestBody = HQPostRequestBodyCreator.creatorHQPostRequestBody();
        ResponseEntity<HQ> response_hq= testTemplate.postForEntity("/hqs", hqPostRequestBody, HQ.class);

        Assertions.assertThat(response_hq)
                .isNotNull();
        Assertions.assertThat(response_hq.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response_hq.getBody())
                .isNotNull();
        Assertions.assertThat(response_hq.getBody().getName())
                .isNotNull();
    }
    @Test
    @DisplayName("Teste para HQControllerIT 7 - atualizar alguém na lista")
    void method_update_when_sucessful(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());
        HQ_save.setName("Irineu Iruneu");

        ResponseEntity<Void> response_hq= testTemplate.exchange(
                "/hqs", HttpMethod.PUT,
                new HttpEntity<>(HQ_save),
                Void.class);

        Assertions.assertThat(response_hq)
                .isNotNull();
        Assertions.assertThat(response_hq.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    @DisplayName("Teste para HQControllerIT 8 - deletando alguém na lista")
    void method_delete_when_sucessful(){

        HQ HQ_save = hqRepository.save(HQCreator.CreateHQ_Saved());

        ResponseEntity<Void> response_hq= testTemplate.exchange(
                "/hqs/{id}", HttpMethod.DELETE,
                null,
                Void.class, HQ_save.getId());

        Assertions.assertThat(response_hq)
                .isNotNull();
        Assertions.assertThat(response_hq.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }
}