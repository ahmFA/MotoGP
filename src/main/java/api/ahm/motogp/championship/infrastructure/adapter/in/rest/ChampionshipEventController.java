package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.application.port.in.CreateChampionshipEventUseCase;
import api.ahm.motogp.championship.application.port.in.ListChampionshipEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("championships/{championshipId}/events")
public class ChampionshipEventController {

    private final CreateChampionshipEventUseCase createChampionshipGrandPrixEventUseCase;
    private final ListChampionshipEventUseCase listChampionshipEventUseCase;

    public ChampionshipEventController(CreateChampionshipEventUseCase createChampionshipGrandPrixEventUseCase,
                                       ListChampionshipEventUseCase listChampionshipEventUseCase) {
        this.createChampionshipGrandPrixEventUseCase = createChampionshipGrandPrixEventUseCase;
        this.listChampionshipEventUseCase = listChampionshipEventUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipEventResponse>> getEventsByChampionship(@PathVariable int championshipId) {
        List<ChampionshipEventResponse> eventResponse = listChampionshipEventUseCase.getEventsByChampionship(championshipId)
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
