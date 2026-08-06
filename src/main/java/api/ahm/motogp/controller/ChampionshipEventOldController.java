package api.ahm.motogp.controller;

import api.ahm.motogp.dto.PatchChampionshipEventRequest;
import api.ahm.motogp.entities.ChampionshipEvent;
import api.ahm.motogp.entities.ChampionshipGrandPrix;
import api.ahm.motogp.repositories.ChampionshipEventRepository;
import api.ahm.motogp.repositories.ChampionshipGrandPrixRepository;
import api.ahm.motogp.services.ChampionshipEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championshipssss/{championshipId}/events")
public class ChampionshipEventController {

    private final ChampionshipEventRepository championshipEventRepository;
    private final ChampionshipGrandPrixRepository championshipGrandPrixRepository;
    private final ChampionshipEventService championshipEventService;

    public ChampionshipEventController(ChampionshipEventRepository championshipEventRepository,
                                       ChampionshipGrandPrixRepository championshipGrandPrixRepository,
                                       ChampionshipEventService championshipEventService) {
        this.championshipEventRepository = championshipEventRepository;
        this.championshipGrandPrixRepository = championshipGrandPrixRepository;
        this.championshipEventService = championshipEventService;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipEvent>> getChampionshipEvents() {
        List<ChampionshipEvent> championshipEvents = championshipEventRepository.findAll();
        if(championshipEvents.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(championshipEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipEvent> getChampionshipEvent(@PathVariable("id") int id) {
        return championshipEventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChampionshipEvent> addChampionshipEvent(@RequestBody ChampionshipEvent championshipEvent,
                                                                 UriComponentsBuilder ucb) {
        ChampionshipGrandPrix championshipGrandPrix = getChampionshipGrandPrixReference(championshipEvent.getChampionshipGrandPrix());
        if (championshipGrandPrix == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipEvent.setId(0);
        championshipEvent.setGrandPrix(championshipGrandPrix);
        ChampionshipEvent newChampionshipEvent = championshipEventRepository.save(championshipEvent);
        URI location = ucb.path("/championship-events/{id}").buildAndExpand(newChampionshipEvent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChampionshipEvent> patchChampionshipEvent(@PathVariable int id,
                                                                   @RequestBody PatchChampionshipEventRequest request) {
        Optional<ChampionshipEvent> championshipEvent = championshipEventRepository.findById(id);
        if (championshipEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChampionshipEvent updatedChampionshipEvent = championshipEvent.get();
        if (request.startDate() != null) {
            updatedChampionshipEvent.setStartDate(request.startDate());
        }

        return ResponseEntity.ok(championshipEventRepository.save(updatedChampionshipEvent));
    }

    private ChampionshipGrandPrix getChampionshipGrandPrixReference(ChampionshipGrandPrix championshipGrandPrix) {
        if (championshipGrandPrix == null
                || championshipGrandPrix.getId() == 0
                || !championshipGrandPrixRepository.existsById(championshipGrandPrix.getId())) {
            return null;
        }
        return championshipGrandPrixRepository.getReferenceById(championshipGrandPrix.getId());
    }
}
