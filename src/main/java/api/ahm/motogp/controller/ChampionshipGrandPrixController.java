package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipGrandPrix;
import api.ahm.motogp.repositories.ChampionshipGrandPrixRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championship-grand-prix")
public class ChampionshipGrandPrixController {

    private final ChampionshipGrandPrixRepository championshipGrandPrixRepository;

    public ChampionshipGrandPrixController(ChampionshipGrandPrixRepository championshipGrandPrixRepository) {
        this.championshipGrandPrixRepository = championshipGrandPrixRepository;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipGrandPrix>> getChampionshipGrandPrix() {
        List<ChampionshipGrandPrix> championshipGrandPrix = championshipGrandPrixRepository.findAll();
        if(championshipGrandPrix.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(championshipGrandPrix);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipGrandPrix> getChampionshipGrandPrix(@PathVariable("id") int id) {
        return championshipGrandPrixRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
