package test_Spring.packages.project_two.exception;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {

    private String title;
    private int status;
    private String details;
    private String devMessage;
    private LocalDateTime dateTime;


}
