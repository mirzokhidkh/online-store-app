package uz.mk.onlinestoreapp.exception;

import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        final StringBuffer errors = new StringBuffer();

        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": " + error.getDefaultMessage());
            errors.append(", ");
        }


        ApiExceptionResponse exceptionResponse = new ApiExceptionResponse(errors.toString());

        return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
