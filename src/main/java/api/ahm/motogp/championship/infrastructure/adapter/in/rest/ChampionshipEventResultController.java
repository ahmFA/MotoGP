package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipEventResultUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/{eventId}/results")
public class ChampionshipEventResultController {

    private final CreateChampionshipEventResultUseCase createChampionshipGrandPrixEventResultUseCase;

    public ChampionshipEventResultController(CreateChampionshipEventResultUseCase createChampionshipGrandPrixEventResultUseCase) {
        this.createChampionshipGrandPrixEventResultUseCase = createChampionshipGrandPrixEventResultUseCase;
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createResults(@PathVariable int eventId,
                                           @Valid @RequestBody CreateChampionshipEventResultBulkRequest resultsBulk) {
        createChampionshipGrandPrixEventResultUseCase.createResults(
                ChampionshipEventResultMapper.toCommand(resultsBulk, eventId)
        );
        return ResponseEntity.noContent().build();
    }
}
