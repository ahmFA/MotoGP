package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Team;
import api.ahm.motogp.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;


    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeamss(){
        List<Team> team =  teamRepository.findAll();
        if(team.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(team);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable int id){
        return teamRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
