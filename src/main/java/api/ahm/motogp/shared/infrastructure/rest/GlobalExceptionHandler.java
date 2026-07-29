package api.ahm.motogp.shared.infrastructure.rest;

import api.ahm.motogp.exceptions.ErrorResponseDTO;
import api.ahm.motogp.championship.application.exception.ChampionshipAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNameAlreadyExistsException;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.rider.application.exception.RiderNameAlreadyExistsException;
import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.exception.RiderNumberAlreadyExistException;
import api.ahm.motogp.shared.category.exception.CategoryNotFoundException;
import api.ahm.motogp.shared.country.exception.CountryNotFoundException;
import api.ahm.motogp.team.application.exception.TeamNameAlreadyExistsException;
import api.ahm.motogp.team.application.exception.TeamNotFoundException;
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
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) ->
                errors.put(error.getField(), error.getDefaultMessage())
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
    public ResponseEntity<?> handleIllegalStatusException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyResponse(ex, HttpStatus.CONFLICT));
    }

    // RIDERS
    @ExceptionHandler(RiderNotFoundException.class)
    public ResponseEntity<?> handleRiderNotFoundException(RiderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(RiderNameAlreadyExistsException.class)
    public ResponseEntity<?> handleRiderNameAlreadyExistsException(RiderNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(RiderNumberAlreadyExistException.class)
    public ResponseEntity<?> handleRiderNumberAlreadyExistException(RiderNumberAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // TEAMS
    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<?> handleTeamNotFoundException(TeamNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(TeamNameAlreadyExistsException.class)
    public ResponseEntity<?> handleTeamNameAlreadyExistsException(TeamNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // GRAND PRIXES
    @ExceptionHandler(GrandPrixNotFoundException.class)
    public ResponseEntity<?> handleGrandPrixNotFoundException(GrandPrixNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(GrandPrixNameAlreadyExistsException.class)
    public ResponseEntity<?> handleGrandPrixNameAlreadyExistsException(GrandPrixNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // CHAMPIONSHIPS
    @ExceptionHandler(ChampionshipNotFoundException.class)
    public ResponseEntity<?> handleChampionshipNotFoundException(ChampionshipNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ChampionshipAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipAlreadyExistsException(ChampionshipAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // CATEGORIES
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    //COUNTRIES
    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> handleCountryNotFoundException(CountryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    private ErrorResponseDTO bodyResponse(Exception ex, HttpStatus status) {
        Map<String, String> errors = new HashMap<>();
        errors.put("description", ex.getMessage());

        return new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                errors
        );
    }
}
