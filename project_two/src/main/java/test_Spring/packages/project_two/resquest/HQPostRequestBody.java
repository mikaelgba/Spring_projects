package test_Spring.packages.project_two.resquest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HQPostRequestBody {
    @NotEmpty(message = "nome não pode estar vazio")
    private String name;
}