package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipEventResult;
import api.ahm.motogp.repositories.ChampionshipEventResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events/{eventId}/results")
public class ChampionshipEventResultController {

    private final ChampionshipEventResultRepository championshipEventResultRepository;

    public ChampionshipEventResultController(ChampionshipEventResultRepository championshipEventResultRepository) {
        this.championshipEventResultRepository = championshipEventResultRepository;
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
}
