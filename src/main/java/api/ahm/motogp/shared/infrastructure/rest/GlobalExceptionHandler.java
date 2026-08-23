package api.ahm.motogp.shared.infrastructure.rest;

import api.ahm.motogp.championship.application.exception.*;
import api.ahm.motogp.shared.infrastructure.adapter.in.GlobalErrorResponse;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNameAlreadyExistsException;
import api.ahm.motogp.grandprix.application.exception.GrandPrixNotFoundException;
import api.ahm.motogp.identity.application.exception.UserEmailAlreadyExistsException;
import api.ahm.motogp.identity.application.exception.UsernameAlreadyExistsException;
import api.ahm.motogp.identity.domain.exception.InvalidEmailException;
import api.ahm.motogp.identity.domain.exception.InvalidUsernameException;
import api.ahm.motogp.identity.domain.exception.InvalidUsernameLengthException;
import api.ahm.motogp.league.application.exception.LeagueNotFoundException;
import api.ahm.motogp.league.application.exception.UserLeagueAlreadyExistsException;
import api.ahm.motogp.league.application.exception.UserLeagueNotFoundException;
import api.ahm.motogp.identity.application.exception.UserNotFoundException;
import api.ahm.motogp.prediction.application.exception.PredictionNotFoundException;
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

        GlobalErrorResponse erDTO = new GlobalErrorResponse(
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

    // EVENTS
    @ExceptionHandler(EventDuplicatedInRequestException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventDuplicatedInRequestException(EventDuplicatedInRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventAlreadyExistsException(EventAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventNotFoundException(EventNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(EventCannotBePredictedException.class)
    public ResponseEntity<?> handleEventCannotBePredictedException(EventCannotBePredictedException ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(bodyResponse(ex, HttpStatus.PRECONDITION_FAILED));
    }

    // EVENT RESULTS
    @ExceptionHandler(EventResultDuplicatedInRequestException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventResultDuplicatedInRequestException(EventResultDuplicatedInRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(EventResultAlreadyExistsException.class)
    public ResponseEntity<?> handleChampionshipGrandPrixEventResultAlreadyExistsException(EventResultAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TeamIsNotActiveException.class)
    public ResponseEntity<?> handleTeamIsNotActiveException(TeamIsNotActiveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // LEAGUES
    @ExceptionHandler(LeagueNotFoundException.class)
    public ResponseEntity<?> handleLeagueChampionshipNotFoundException(LeagueNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UserLeagueNotFoundException.class)
    public ResponseEntity<?> handleUserLeagueNotFoundException(UserLeagueNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserLeagueUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UserLeagueAlreadyExistsException.class)
    public ResponseEntity<?> handleUserLeagueAlreadyExistsException(UserLeagueAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResponse(ex, HttpStatus.BAD_REQUEST));
    }

    // PREDICTION
    @ExceptionHandler(PredictionNotFoundException.class)
    public ResponseEntity<?> handlePredictionNotFoundException(PredictionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse(ex, HttpStatus.NOT_FOUND));
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

    private GlobalErrorResponse bodyResponse(Exception ex, HttpStatus status) {
        Map<String, String> errors = new HashMap<>();
        errors.put("description", ex.getMessage());

        return new GlobalErrorResponse(
                LocalDateTime.now(),
                status.value(),
                errors
        );
    }
}
