package test_with_webFlux.Spring_webflux_studios.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("studio")
public class Studio {

    //Usando Id como String porque estou usando o mongoDb embarcado, como padrão o  id é alfanumerico
    @Id
    private String id;
    @NotNull
    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;
    @NotNull
    @NotEmpty(message = "Local da fundação não pode ser vazio")
    private String foundation_origin;
    @NotNull
    @NotEmpty(message = "Ano da fundação não pode ser vazio")
    private String founded_in;
}