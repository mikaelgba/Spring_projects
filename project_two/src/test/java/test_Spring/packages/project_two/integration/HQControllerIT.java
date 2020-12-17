package test_Spring.packages.project_two.integration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.repository.HQRepository;
import test_Spring.packages.project_two.util.HQCreator;
import test_Spring.packages.project_two.wapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
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

}
