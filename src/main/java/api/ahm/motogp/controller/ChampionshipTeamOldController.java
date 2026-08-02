package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Championship;
import api.ahm.motogp.entities.ChampionshipTeam;
import api.ahm.motogp.entities.Constructor;
import api.ahm.motogp.repositories.ChampionshipRepository;
import api.ahm.motogp.repositories.ChampionshipTeamRepository;
import api.ahm.motogp.repositories.ConstructorRepository;
import api.ahm.motogp.services.ChampionshipTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/championships/{championshipId}/teams")
public class ChampionshipTeamController {

    private final ChampionshipTeamRepository championshipTeamRepository;
    private final ChampionshipRepository championshipRepository;
    private final ConstructorRepository constructorRepository;
    private final ChampionshipTeamService championshipTeamService;

    public ChampionshipTeamController(ChampionshipTeamRepository championshipTeamRepository,
                                      ChampionshipRepository championshipRepository,
                                      ConstructorRepository constructorRepository,
                                      ChampionshipTeamService championshipTeamService) {
        this.championshipTeamRepository = championshipTeamRepository;
        this.championshipRepository = championshipRepository;
        this.constructorRepository = constructorRepository;
        this.championshipTeamService = championshipTeamService;
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipTeam>> getChampionshipTeams(@PathVariable int championshipId) {
        List<ChampionshipTeam> teams = championshipTeamRepository.findByChampionshipId(championshipId);
        if (teams.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipTeam> findByChampionshipIdAndId(@PathVariable int championshipId, @PathVariable int id) {
        return championshipTeamRepository.findByChampionshipIdAndId(championshipId, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChampionshipTeam> addChampionshipTeam(@PathVariable int championshipId,
                                                               @RequestBody ChampionshipTeam championshipTeam,
                                                               UriComponentsBuilder ucb) {
        Championship championship = getChampionshipReference(championshipId);
        Constructor constructor = getConstructorReference(championshipTeam.getConstructor());
        if (championship == null || constructor == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipTeam.setId(0);
        championshipTeam.setChampionship(championship);
        championshipTeam.setConstructor(constructor);
        ChampionshipTeam newChampionshipTeam = championshipTeamRepository.save(championshipTeam);
        URI location = ucb.path("/championships/{championshipId}/teams/{id}")
                .buildAndExpand(championshipId, newChampionshipTeam.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampionshipTeam> putChampionshipTeam(@PathVariable int championshipId,
                                                               @PathVariable int id,
                                                               @RequestBody ChampionshipTeam championshipTeam) {
        if (championshipTeamRepository.findByChampionshipIdAndId(championshipId, id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Championship championship = getChampionshipReference(championshipId);
        Constructor constructor = getConstructorReference(championshipTeam.getConstructor());
        if (championship == null || constructor == null) {
            return ResponseEntity.badRequest().build();
        }

        championshipTeam.setId(id);
        championshipTeam.setChampionship(championship);
        championshipTeam.setConstructor(constructor);
        return ResponseEntity.ok(championshipTeamRepository.save(championshipTeam));
    }

    private Championship getChampionshipReference(int championshipId) {
        if (!championshipRepository.existsById(championshipId)) {
            return null;
        }
        return championshipRepository.getReferenceById(championshipId);
    }

    private Constructor getConstructorReference(Constructor constructor) {
        if (constructor == null || constructor.getId() == 0 || !constructorRepository.existsById(constructor.getId())) {
            return null;
        }
        return constructorRepository.getReferenceById(constructor.getId());
    }
}
