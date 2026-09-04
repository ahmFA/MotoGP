package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateEventResultUseCase;
import api.ahm.motogp.championship.application.port.in.ListEventResultsUseCase;
import api.ahm.motogp.championship.application.port.query.EventResultView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events/{eventId}/results")
public class EventResultController {

    private final CreateEventResultUseCase createChampionshipGrandPrixEventResultUseCase;
    private final ListEventResultsUseCase listEventResultsUseCase;

    public EventResultController(CreateEventResultUseCase createChampionshipGrandPrixEventResultUseCase,
                                 ListEventResultsUseCase listEventResultsUseCase) {
        this.createChampionshipGrandPrixEventResultUseCase = createChampionshipGrandPrixEventResultUseCase;
        this.listEventResultsUseCase = listEventResultsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<EventResultView>> getEventResults(@PathVariable int eventId) {
        List<EventResultView> results = listEventResultsUseCase.getEventResults(eventId);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createResults(@PathVariable int eventId,
                                           @Valid @RequestBody CreateEventResultBulkRequest resultsBulk) {
        createChampionshipGrandPrixEventResultUseCase.createResults(
                ChampionshipEventResultMapper.toCommand(resultsBulk, eventId)
        );
        return ResponseEntity.noContent().build();
    }
}
