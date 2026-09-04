package api.ahm.motogp.prediction.infrastructure.adapter.in.rest;

import api.ahm.motogp.prediction.application.port.in.CreateOrUpdatePredictionUseCase;
import api.ahm.motogp.prediction.application.port.query.CreateOrUpdatePredictionQuery;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leagues/{leagueId}/events/{eventId}/predictions")
public class UserEventPredictionController {

    private final CreateOrUpdatePredictionUseCase createOrUpdatePredictionUseCase;

    public UserEventPredictionController(CreateOrUpdatePredictionUseCase userEventPredictionUseCase) {
        this.createOrUpdatePredictionUseCase = userEventPredictionUseCase;
    }

    @PutMapping
    public ResponseEntity<UserEventPredictionResponse> createOrUpdatePrediction(@PathVariable Long leagueId,@PathVariable Long eventId, @Valid @RequestBody CreateOrUpdatePredictionRequest userPrediction){
        // Falta obtener el usuario a través de la autenticacion
        CreateOrUpdatePredictionQuery command = PredictionMapper.toCommand(userPrediction, eventId, 11L);
        UserEventPredictionResponse response = createOrUpdatePredictionUseCase.createOrUpdateUserEventPrediction(command, leagueId);
        return ResponseEntity.ok(response);
    }
}
