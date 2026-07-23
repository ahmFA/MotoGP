package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Team;
import api.ahm.motogp.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team, UriComponentsBuilder ucb) {
        team.setId(0);
        Team newTeam = teamRepository.save(team);
        URI location = ucb.path("/teams/{id}").buildAndExpand(newTeam.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> putTeam(@PathVariable int id, @RequestBody Team team) {
        if (!teamRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        team.setId(id);
        return ResponseEntity.ok(teamRepository.save(team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable int id) {
        try {
            Team team = teamRepository.getReferenceById(id);
            team.setActive(false);
            teamRepository.save(team);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
