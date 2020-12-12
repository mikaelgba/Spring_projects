package test_Spring.packages.project_two.resquest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HQPutRequestBody {
    private long id;
    private String name;
}