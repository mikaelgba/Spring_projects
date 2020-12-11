package test_Spring.packages.project_two.repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import test_Spring.packages.project_two.entites.HQ;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Teste para HQRespository")
class HQRepositoryTest {

    @Autowired
    private HQRepository hqRepository;

    @Test
    @DisplayName("Teste para HQRespository - Salvar no banco")
    void test_save_persist_hq_when_successful(){

        HQ hq = CreateHQ();
        HQ save_hq = this.hqRepository.save(hq);

        Assertions.assertThat(save_hq).isNotNull();
        Assertions.assertThat(save_hq.getId()).isNotNull();
        Assertions.assertThat(save_hq.getName()).isEqualTo(hq.getName());
    }
    @Test
    @DisplayName("Teste para HQRespository - exceção quando tentar salvar com o nome vazio")
    void test_save_persist_hq_exception_when_name_empty(){

        HQ hq = new HQ();
        /*Assertions.assertThatThrownBy(() ->
                this.hqRepository.save(hq))
                .isInstanceOf(ConstraintViolationException.class);*/
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.hqRepository.save(hq))
                .withMessageContaining("nome não pode estar vazio");
    }
    @Test
    @DisplayName("Teste para HQRespository - atualizar alguém no banco")
    void test_update_persist_hq_when_successful(){

        HQ hq = CreateHQ();
        HQ save_hq = this.hqRepository.save(hq);
        save_hq.setName("League of Justice - War");
        HQ update_hq = this.hqRepository.save(save_hq);

        Assertions.assertThat(update_hq).isNotNull();
        Assertions.assertThat(update_hq.getId()).isNotNull();
        Assertions.assertThat(update_hq.getName()).isEqualTo(save_hq.getName());
    }
    @Test
    @DisplayName("Teste para HQRespository - deletar alguém no banco")
    void test_delete_persist_hq_when_successful(){

        HQ hq = CreateHQ();
        HQ create_hq_for_delete = this.hqRepository.save(hq);
        this.hqRepository.delete(create_hq_for_delete);

        Optional<HQ> hq_optional = this.hqRepository.findById(create_hq_for_delete.getId());
        Assertions.assertThat(hq_optional).isEmpty();
    }
    @Test
    @DisplayName("Teste para HQRespository - encontrar alguém pelo nome no banco")
    void test_findById_hq_when_successful(){

        HQ hq = CreateHQ();
        HQ create_hq = this.hqRepository.save(hq);
        String hq_name = create_hq.getName();
        List<HQ> hqs_for_name = this.hqRepository.findByName(hq_name);

        Assertions.assertThat(hqs_for_name).isNotNull().contains(create_hq);
    }
    @Test
    @DisplayName("Teste para HQRespository - se não encontrar alguém pelo nome no banco, retorna uma lista vazia")
    void test_if_not_findByName(){

        List<HQ> hqs_for_name = this.hqRepository.findByName("Marvel");
        Assertions.assertThat(hqs_for_name).isEmpty();
    }
    private HQ CreateHQ(){
        return HQ.builder()
                .name("Shazam")
                .build();
    }
}