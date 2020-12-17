package test_Spring.packages.project_two.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test_Spring.packages.project_two.entites.HQ;
import test_Spring.packages.project_two.exception.BadRequestException;
import test_Spring.packages.project_two.repository.HQRepository;
import test_Spring.packages.project_two.util.HQCreator;
import test_Spring.packages.project_two.util.HQPostRequestBodyCreator;
import test_Spring.packages.project_two.util.HQPutRequestBodyCreator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class HQServiceTest {

    @InjectMocks
    private HQService hqService;
    @Mock
    private HQRepository hqRepositoryMock;

    @BeforeEach
    void setUp(){

        PageImpl<HQ> hqPage = new PageImpl<>(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(hqPage);

        BDDMockito.when(hqRepositoryMock.findAll())
                .thenReturn(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(HQCreator.CreateHQ_Valid()));

        BDDMockito.when(hqRepositoryMock.save(ArgumentMatchers.any(HQ.class)))
                .thenReturn(HQCreator.CreateHQ_Valid());

        BDDMockito.doNothing().when(hqRepositoryMock)
                .delete(ArgumentMatchers.any(HQ.class));
    }
    @Test
    @DisplayName("Teste para HQService 1 - retorna lista com paginas quando sucesso")
    void method_list_page_when_sucessful_return_listpage(){

        String name_wait = HQCreator.CreateHQ_Saved().getName();
        Page<HQ> hqPage_Body = hqService.allHQs(PageRequest.of(1,2));

        Assertions.assertThat(hqPage_Body).isNotNull();
        Assertions.assertThat(hqPage_Body.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hqPage_Body.toList().get(0).getName()).isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQService 2 - retorna toda a lista")
    void method_listAll_when_sucessful_return_all_list(){

        String name_wait = HQCreator.CreateHQ_Saved().getName();
        List<HQ> hqList = hqService.allHQsNotPage();

        Assertions.assertThat(hqList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hqList.get(0).getName()).isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQService 3 - retorna da lista o id procurado")
    void method_finfById_when_sucessful_object_with_id(){

        Long id_wait = HQCreator.CreateHQ_Valid().getId();
        HQ hq_with_id = hqService.findByIdHQOrException(1);

        Assertions.assertThat(hq_with_id).
                isNotNull();
        Assertions.assertThat(hq_with_id.getId())
                .isEqualTo(id_wait);
    }
    @Test
    @DisplayName("Teste para HQService 4 - retorna um execeção por não encontrar HQ com id buscado")
    void method_finfById_when_sucessful_not_found_id(){

        BDDMockito.when(hqRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> hqService.findByIdHQOrException(1));

    }
    @Test
    @DisplayName("Teste para HQService 5 - retorna uma lista com o nome procurado")
    void method_finfByName_when_sucessful_list_with_name(){

        String name_wait = HQCreator.CreateHQ_Valid().getName();
        List<HQ> hq_with_name = hqService.findByName("hq");

        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(hq_with_name.get(0).getName())
                .isEqualTo(name_wait);
    }
    @Test
    @DisplayName("Teste para HQService 6 - quando não encontra alguem com o nome na lista")
    void method_finfByName_when_sucessful_not_found_in_list(){

        BDDMockito.when(hqRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<HQ> hq_with_name = hqService.findByName("hq");

        Assertions.assertThat(hq_with_name)
                .isNotNull()
                .isEmpty();
    }
    @Test
    @DisplayName("Teste para HQService 7 - Salvar na lista")
    void method_save_when_sucessful(){

        Long id_wait = HQCreator.CreateHQ_Valid().getId();
        HQ hq_with_id = hqService.save(HQPostRequestBodyCreator
                .creatorHQPostRequestBody());

        Assertions.assertThat(hq_with_id).
                isNotNull()
                .isEqualTo(HQCreator.CreateHQ_Valid());
        Assertions.assertThat(hq_with_id.getId())
                .isEqualTo(id_wait)
                .isNotNull();
    }
    @Test
    @DisplayName("Teste para HQService 8 - atualizar alguém na lista")
    void method_update_when_sucessful(){

        Assertions.assertThatCode(() -> hqService.update(HQPutRequestBodyCreator
                .creatorHQPutRequestBody()))
                .doesNotThrowAnyException();

    }
    @Test
    @DisplayName("Teste para HQService 9 - deletando alguém na lista")
    void method_delete_when_sucessful(){

        Assertions.assertThatCode(() -> hqService.delete(1L))
                .doesNotThrowAnyException();
    }

}