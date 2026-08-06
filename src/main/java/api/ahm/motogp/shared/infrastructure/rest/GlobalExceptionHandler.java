package api.ahm.motogp.shared.infrastructure.rest;

import api.ahm.motogp.championship.application.exception.*;
import api.ahm.motogp.exceptions.ErrorResponseDTO;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNameAlreadyExistsException;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.identity.application.exception.UserEmailAlreadyExistsException;
import api.ahm.motogp.identity.application.exception.UsernameAlreadyExistsException;
import api.ahm.motogp.identity.domain.exception.InvalidEmailException;
import api.ahm.motogp.identity.domain.exception.InvalidUsernameException;
import api.ahm.motogp.identity.domain.exception.InvalidUsernameLengthException;
import api.ahm.motogp.rider.application.exception.RiderIsNotActiveException;
import api.ahm.motogp.rider.application.exception.RiderNameAlreadyExistsException;
import api.ahm.motogp.rider.application.exception.RiderNotFoundException;
import api.ahm.motogp.rider.application.exception.RiderNumberAlreadyExistException;
import api.ahm.motogp.shared.category.exception.CategoryNotFoundException;
import api.ahm.motogp.shared.country.exception.CountryNotFoundException;
import api.ahm.motogp.team.application.exception.TeamIsNotActiveException;
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

    // USERS
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<?> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<?> handleInvalidUsernameException(InvalidUsernameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidUsernameLengthException.class)
    public ResponseEntity<?> handleInvalidUsernameLengthException(InvalidUsernameLengthException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
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

    @ExceptionHandler(RiderIsNotActiveException.class)
    public ResponseEntity<?> handleRiderIsNotActiveException(RiderIsNotActiveException ex) {
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

    // CHAMPIONSHIP TEAMS
    @ExceptionHandler(ChampionshipTeamNotFoundException.class)
    public ResponseEntity<?> handleChampionshipTeamNotFoundException(ChampionshipTeamNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ChampionshipTeamAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipTeamAlreadyExistsException(ChampionshipTeamAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ChampionshipTeamNameAlreadyExistsException.class)
    public  ResponseEntity<?> handleChampionshipTeamNameAlreadyExistsException(ChampionshipTeamNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // CHAMPIONSHIP RIDERS
    @ExceptionHandler(ChampionshipRiderNotFoundException.class)
    public ResponseEntity<?> handleChampionshipRiderNotFoundException(ChampionshipRiderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ChampionshipRiderAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipRiderAlreadyExistsException(ChampionshipRiderAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // CHAMPIONSHIP GRAND PRIXES
    @ExceptionHandler(ChampionshipGrandPrixNotFoundException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixNotFoundException(ChampionshipGrandPrixNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ChampionshipGrandPrixAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixAlreadyExistsException(ChampionshipGrandPrixAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // CHAMPIONSHIP GRAND PRIX EVENTS
    @ExceptionHandler(ChampionshipEventDuplicatedInRequestException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventDuplicatedInRequestException(ChampionshipEventDuplicatedInRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ChampionshipEventAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventAlreadyExistsException(ChampionshipEventAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ChampionshipEventNotFoundException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventNotFoundException(ChampionshipEventNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    // CHAMPIONSHIP GRAND PRIX EVENT RESULTS
    @ExceptionHandler(ChampionshipEventResultDuplicatedInRequestException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventResultDuplicatedInRequestException(ChampionshipEventResultDuplicatedInRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ChampionshipEventResultAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventResultAlreadyExistsException(ChampionshipEventResultAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TeamIsNotActiveException.class)
    public ResponseEntity<?> handleTeamIsNotActiveException(TeamIsNotActiveException ex) {
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

    // CONSTRUCTOR
    @ExceptionHandler(ConstructorNotFoundException.class)
    public ResponseEntity<?> handleConstructorNotFoundException(ConstructorNotFoundException ex) {
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
