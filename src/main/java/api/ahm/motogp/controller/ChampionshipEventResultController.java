package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipEvent;
import api.ahm.motogp.entities.ChampionshipEventResult;
import api.ahm.motogp.entities.ChampionshipRider;
import api.ahm.motogp.repositories.ChampionshipEventRepository;
import api.ahm.motogp.repositories.ChampionshipEventResultRepository;
import api.ahm.motogp.repositories.ChampionshipRiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events/{eventId}/results")
public class ChampionshipEventResultController {

    private final ChampionshipEventResultRepository championshipEventResultRepository;
    private final ChampionshipEventRepository championshipEventRepository;
    private final ChampionshipRiderRepository championshipRiderRepository;

    public ChampionshipEventResultController(ChampionshipEventResultRepository championshipEventResultRepository,
                                             ChampionshipEventRepository championshipEventRepository,
                                             ChampionshipRiderRepository championshipRiderRepository) {
        this.championshipEventResultRepository = championshipEventResultRepository;
        this.championshipEventRepository = championshipEventRepository;
        this.championshipRiderRepository = championshipRiderRepository;
    }


    @GetMapping
    public ResponseEntity<List<ChampionshipEventResult>> getChampionshipEventResult(@PathVariable int eventId) {
        List<ChampionshipEventResult> results =  championshipEventResultRepository.getChampionshipEventResultByChampionshipEventId(eventId);
        if(results.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<ChampionshipEventResult> getChampionshipEventResultByRider(@PathVariable int eventId, @PathVariable int riderId) {
        return championshipEventResultRepository.getChampionshipEventResultByChampionshipEventIdAndChampionshipRiderId(eventId, riderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChampionshipEventResult> addChampionshipEventResult(@PathVariable int eventId,
                                                                             @RequestBody ChampionshipEventResult championshipEventResult,
                                                                             UriComponentsBuilder ucb) {
        ChampionshipEvent championshipEvent = getChampionshipEventReference(eventId);
        ChampionshipRider championshipRider = getChampionshipRiderReference(championshipEventResult.getChampionshipRider());
        if (championshipEvent == null || championshipRider == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipEventResult.setId(0);
        championshipEventResult.setChampionshipEvent(championshipEvent);
        championshipEventResult.setChampionshipRider(championshipRider);
        ChampionshipEventResult newChampionshipEventResult = championshipEventResultRepository.save(championshipEventResult);
        URI location = ucb.path("/events/{eventId}/results/{id}")
                .buildAndExpand(eventId, newChampionshipEventResult.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampionshipEventResult> putChampionshipEventResult(@PathVariable int eventId,
                                                                             @PathVariable int id,
                                                                             @RequestBody ChampionshipEventResult championshipEventResult) {
        if (championshipEventResultRepository.findByChampionshipEventIdAndId(eventId, id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChampionshipEvent championshipEvent = getChampionshipEventReference(eventId);
        ChampionshipRider championshipRider = getChampionshipRiderReference(championshipEventResult.getChampionshipRider());
        if (championshipEvent == null || championshipRider == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipEventResult.setId(id);
        championshipEventResult.setChampionshipEvent(championshipEvent);
        championshipEventResult.setChampionshipRider(championshipRider);
        return ResponseEntity.ok(championshipEventResultRepository.save(championshipEventResult));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChampionshipEventResult> deleteChampionshipEventResult(@PathVariable int eventId,
                                                                                @PathVariable int id) {
        Optional<ChampionshipEventResult> championshipEventResult =
                championshipEventResultRepository.findByChampionshipEventIdAndId(eventId, id);
        if (championshipEventResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        championshipEventResultRepository.delete(championshipEventResult.get());
        return ResponseEntity.noContent().build();
    }

    private ChampionshipEvent getChampionshipEventReference(int eventId) {
        if (!championshipEventRepository.existsById(eventId)) {
            return null;
        }
        return championshipEventRepository.getReferenceById(eventId);
    }

    private ChampionshipRider getChampionshipRiderReference(ChampionshipRider championshipRider) {
        if (championshipRider == null
                || championshipRider.getId() == 0
                || !championshipRiderRepository.existsById(championshipRider.getId())) {
            return null;
        }
        return championshipRiderRepository.getReferenceById(championshipRider.getId());
    }
}
