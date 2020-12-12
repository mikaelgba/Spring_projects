package test_Spring.packages.project_two.controller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.resquest.HQPostRequestBody;
import test_Spring.packages.project_two.resquest.HQPutRequestBody;
import test_Spring.packages.project_two.service.HQService;
import test_Spring.packages.project_two.util.HQCreator;
import test_Spring.packages.project_two.util.HQPostRequestBodyCreator;
import test_Spring.packages.project_two.util.HQPutRequestBodyCreator;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class HQControllerTest {

    @InjectMocks // InjectMocks é usado quando quero testar a classe em si
    private HQController hqController;
    @Mock // Mock é usado quando quero testar todas as classes em sique estão sendo chamadas pela classe "principal", neste caso HQController
    private HQService hqServiceMock;

    @BeforeEach
    void setUp(){

        PageImpl<HQ> hqPage = new PageImpl<>(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqServiceMock.allHQs(ArgumentMatchers.any()))
                .thenReturn(hqPage);

        BDDMockito.when(hqServiceMock.allHQsNotPage())
                .thenReturn(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqServiceMock.findByIdHQOrException(ArgumentMatchers.anyLong()))
                .thenReturn(HQCreator.CreateHQ_Valid());

        BDDMockito.when(hqServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqServiceMock.save(ArgumentMatchers.any(HQPostRequestBody.class)))
                .thenReturn(HQCreator.CreateHQ_Valid());

        BDDMockito.doNothing().when(hqServiceMock)
                .update(ArgumentMatchers.any(HQPutRequestBody.class));

        BDDMockito.doNothing().when(hqServiceMock)
                .delete(ArgumentMatchers.anyLong());
    }
    @Test
    @DisplayName("Teste para HQControle 1 - retorna lista com paginas quando sucesso")
    void method_list_page_when_sucessful_return_listpage(){

        String name_wait = HQCreator.CreateHQ_Saved().getName();
        Page<HQ> hqPage_Body = hqController.list(null).getBody();

        Assertions.assertThat(hqPage_Body).isNotNull();
        Assertions.assertThat(hqPage_Body.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hqPage_Body.toList().get(0).getName()).isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControle 2 - retorna toda a lista")
    void method_listAll_when_sucessful_return_all_list(){

        String name_wait = HQCreator.CreateHQ_Saved().getName();
        List<HQ> hqList = hqController.listAll().getBody();

        Assertions.assertThat(hqList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hqList.get(0).getName()).isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControle 3 - retorna da lista o id procurado")
    void method_finfById_when_sucessful_object_with_id(){

        Long id_wait = HQCreator.CreateHQ_Valid().getId();
        HQ hq_with_id = hqController.findById(1).getBody();

        Assertions.assertThat(hq_with_id).
                isNotNull();
        Assertions.assertThat(hq_with_id.getId())
                .isEqualTo(id_wait);
    }
    @Test
    @DisplayName("Teste para HQControle 4 - retorna uma lista com o nome procurado")
    void method_finfByName_when_sucessful_list_with_name(){

        String name_wait = HQCreator.CreateHQ_Valid().getName();
        List<HQ> hq_with_name = hqController.findByName("hq").getBody();

        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hq_with_name.get(0).getName())
                .isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQControle 5 - quando não encontra alguem com o nome na lista")
    void method_finfByName_when_sucessful_not_found_in_list(){

        BDDMockito.when(hqServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<HQ> hq_with_name = hqController.findByName("hq").getBody();

        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isEmpty();
    }
    @Test
    @DisplayName("Teste para HQControle 6 - Salvar na lista")
    void method_save_when_sucessful(){

        Long id_wait = HQCreator.CreateHQ_Valid().getId();
        HQ hq_with_id = hqController.save(HQPostRequestBodyCreator
                .creatorHQPostRequestBody()).getBody();

        Assertions.assertThat(hq_with_id).
                isNotNull()
                .isEqualTo(HQCreator.CreateHQ_Valid());
        Assertions.assertThat(hq_with_id.getId())
                .isEqualTo(id_wait)
                .isNotNull();
    }
    @Test
    @DisplayName("Teste para HQControle 7 - atualizar alguém na lista")
    void method_update_when_sucessful(){

        Assertions.assertThatCode(() -> hqController.update(HQPutRequestBodyCreator
                .creatorHQPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<HQ> entity_response = hqController.update(HQPutRequestBodyCreator
                .creatorHQPutRequestBody());

        Assertions.assertThat(entity_response).
                isNotNull();
        Assertions.assertThat(entity_response
                .getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    @DisplayName("Teste para HQControle 8 - deletando alguém na lista")
    void method_delete_when_sucessful(){

        Assertions.assertThatCode(() -> hqController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<HQ> entity_response = hqController.delete(1L);

        Assertions.assertThat(entity_response).
                isNotNull();
        Assertions.assertThat(entity_response
                .getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }
}