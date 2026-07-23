package api.ahm.motogp.controller;

import api.ahm.motogp.dto.PatchChampionshipGrandPrixRequest;
import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.entities.ChampionshipGrandPrix;
import api.ahm.motogp.entities.GrandPrix;
import api.ahm.motogp.repositories.ChampionshipGrandPrixRepository;
import api.ahm.motogp.repositories.ChampionshipRepository;
import api.ahm.motogp.repositories.GrandPrixRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/championship-grand-prix")
public class ChampionshipGrandPrixController {

    private final ChampionshipGrandPrixRepository championshipGrandPrixRepository;
    private final ChampionshipRepository championshipRepository;
    private final GrandPrixRepository grandPrixRepository;

    public ChampionshipGrandPrixController(ChampionshipGrandPrixRepository championshipGrandPrixRepository,
                                           ChampionshipRepository championshipRepository,
                                           GrandPrixRepository grandPrixRepository) {
        this.championshipGrandPrixRepository = championshipGrandPrixRepository;
        this.championshipRepository = championshipRepository;
        this.grandPrixRepository = grandPrixRepository;
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

    @PostMapping
    public ResponseEntity<ChampionshipGrandPrix> addChampionshipGrandPrix(@RequestBody ChampionshipGrandPrix championshipGrandPrix,
                                                                         UriComponentsBuilder ucb) {
        Championship championship = getChampionshipReference(championshipGrandPrix.getChampionship());
        GrandPrix grandPrix = getGrandPrixReference(championshipGrandPrix.getRace());
        if (championship == null || grandPrix == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipGrandPrix.setId(0);
        championshipGrandPrix.setChampionship(championship);
        championshipGrandPrix.setRace(grandPrix);
        ChampionshipGrandPrix newChampionshipGrandPrix = championshipGrandPrixRepository.save(championshipGrandPrix);
        URI location = ucb.path("/championship-grand-prix/{id}").buildAndExpand(newChampionshipGrandPrix.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChampionshipGrandPrix> patchChampionshipGrandPrix(@PathVariable int id,
                                                                           @RequestBody PatchChampionshipGrandPrixRequest request) {
        Optional<ChampionshipGrandPrix> championshipGrandPrix = championshipGrandPrixRepository.findById(id);
        if (championshipGrandPrix.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChampionshipGrandPrix updatedChampionshipGrandPrix = championshipGrandPrix.get();
        if (request.date() != null) {
            updatedChampionshipGrandPrix.setDate(request.date());
        }
        if (request.roundNumber() != null) {
            updatedChampionshipGrandPrix.setRound_number(request.roundNumber());
        }

        return ResponseEntity.ok(championshipGrandPrixRepository.save(updatedChampionshipGrandPrix));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChampionshipGrandPrix> deleteChampionshipGrandPrix(@PathVariable int id) {
        if (!championshipGrandPrixRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        championshipGrandPrixRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Championship getChampionshipReference(Championship championship) {
        if (championship == null || championship.getId() == 0 || !championshipRepository.existsById(championship.getId())) {
            return null;
        }
        return championshipRepository.getReferenceById(championship.getId());
    }

    private GrandPrix getGrandPrixReference(GrandPrix grandPrix) {
        if (grandPrix == null || grandPrix.getId() == 0 || !grandPrixRepository.existsById(grandPrix.getId())) {
            return null;
        }
        return grandPrixRepository.getReferenceById(grandPrix.getId());
    }
}
