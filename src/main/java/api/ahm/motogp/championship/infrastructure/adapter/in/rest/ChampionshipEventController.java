package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateEventUseCase;
import api.ahm.motogp.championship.application.port.in.ListEventUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("championships/{championshipId}/events")
public class ChampionshipEventController {

    private final CreateEventUseCase createChampionshipGrandPrixEventUseCase;
    private final ListEventUseCase listEventUseCase;

    public ChampionshipEventController(CreateEventUseCase createChampionshipGrandPrixEventUseCase,
                                       ListEventUseCase listEventUseCase) {
        this.createChampionshipGrandPrixEventUseCase = createChampionshipGrandPrixEventUseCase;
        this.listEventUseCase = listEventUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipEventResponse>> getEventsByChampionship(@PathVariable int championshipId) {
        List<ChampionshipEventResponse> eventResponse = listEventUseCase.getEventsByChampionship(championshipId)
                                                            .stream().map(ChampionshipEventMapper::toResponse).toList();
        return ResponseEntity.ok(eventResponse);
    }

//    @GetMapping("/grand-prixes/{championshipGrandPrixId}")
//    public ResponseEntity<ChampionshipEventResponse> getEventsByChampionshipGrandPrix(@PathVariable int championshipId, @PathVariable int championshipGrandPrixId) {
//        return null;
//    }
//
//    @GetMapping("{eventId}")
//    public ResponseEntity<ChampionshipEventResponse> getEvent(@PathVariable int eventId) {
//        return null;
//    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createEvents(@PathVariable int championshipId,
                                          @Valid @RequestBody CreateChampionshipEventBulkRequest eventsBulk){
        createChampionshipGrandPrixEventUseCase.createEvents(ChampionshipEventMapper.toCommand(eventsBulk, championshipId));
        return ResponseEntity.noContent().build();
    }
}
