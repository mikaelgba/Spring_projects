package test_Spring.packages.project_two.handler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test_Spring.packages.project_two.exception.BadRequestException;
import test_Spring.packages.project_two.exception.BadRequestExceptionDetails;
import test_Spring.packages.project_two.exception.ExceptionDetails;
import test_Spring.packages.project_two.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestExeception
            (BadRequestException e){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .dateTime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception")
                        .details(e.getMessage())
                        .devMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
    //Sobrescrevenndo a padronização de exceçôes usando as prorpias exceçôes do Hadler
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        String fieldsMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                        .dateTime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Fields")
                        .details("Verifique as Fields")
                        .devMessage(e.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
    //Sobrescrevenndo a padronização de exceçôes usando as prorpias exceçôes do Hadler
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .dateTime(LocalDateTime.now())
                .status(status.value())
                .title(e.getCause().getMessage())
                .details(e.getMessage())
                .devMessage(e.getClass().getName())
                .build();
        return new ResponseEntity(exceptionDetails, headers, status);
    }
}