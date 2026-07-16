package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipEvent;
import api.ahm.motogp.repositories.ChampionshipEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championship-events")
public class ChampionshipEventController {

    private final ChampionshipEventRepository championshipEventRepository;

    public ChampionshipEventController(ChampionshipEventRepository championshipEventRepository) {
        this.championshipEventRepository = championshipEventRepository;
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
}
