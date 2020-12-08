package test_Spring.packages.project_two.resquest;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class HQPostRequestBody {
    @NotEmpty(message = "nome não pode estar vazio")
    private String name;
}