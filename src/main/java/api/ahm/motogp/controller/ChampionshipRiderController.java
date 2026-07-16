package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipRider;
import api.ahm.motogp.repositories.ChampionshipRiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championships/{championshipId}/riders")
public class ChampionshipRiderController {

    private final ChampionshipRiderRepository championshipRiderRepository;

    public ChampionshipRiderController(ChampionshipRiderRepository championshipRiderRepository) {
        this.championshipRiderRepository = championshipRiderRepository;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipRider>> getChampionshipRiders(@PathVariable int championshipId) {
        List<ChampionshipRider> riders = championshipRiderRepository.findByChampionshipId(championshipId);

        if (riders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(riders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipRider> getChampionshipRider(@PathVariable int championshipId, @PathVariable int id) {
        return championshipRiderRepository.findByChampionshipIdAndId(championshipId, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
