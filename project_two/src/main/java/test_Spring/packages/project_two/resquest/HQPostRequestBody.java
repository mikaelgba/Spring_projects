package test_Spring.packages.project_two.resquest;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class HQPostRequestBody {
    @NotEmpty(message = "nome não pode estar vazio")
    private String name;
}