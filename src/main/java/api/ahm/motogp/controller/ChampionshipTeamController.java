package api.ahm.motogp.controller;

import api.ahm.motogp.entities.ChampionshipTeam;
import api.ahm.motogp.repositories.ChampionshipTeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championships/{championshipId}/teams")
public class ChampionshipTeamController {

    private final ChampionshipTeamRepository championshipTeamRepository;

    public ChampionshipTeamController(ChampionshipTeamRepository championshipTeamRepository) {
        this.championshipTeamRepository = championshipTeamRepository;
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
}
