package test_Spring.packages.project_two.handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test_Spring.packages.project_two.exception.BadRequestException;
import test_Spring.packages.project_two.exception.BadRequestExceptionDetails;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestExeception(BadRequestException e){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .dateTime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception")
                        .details(e.getMessage())
                        .devMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}