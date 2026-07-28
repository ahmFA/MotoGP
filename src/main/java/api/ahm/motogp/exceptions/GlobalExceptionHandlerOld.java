package api.ahm.motogp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerOld {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error)->
                errors.put(error.getField(),error.getDefaultMessage())
        );

        ErrorResponseDTO erDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStatusException(IllegalStateException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyResponse(ex, HttpStatus.CONFLICT));
    }

    private ErrorResponseDTO bodyResponse(Exception ex, HttpStatus status){
        Map<String,String> errors = new HashMap<>();
        errors.put("description", ex.getMessage());

        return new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                errors
        );
    }
}
