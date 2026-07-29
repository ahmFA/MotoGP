package api.ahm.motogp.controller;

import api.ahm.motogp.dto.CreateTeamRequest;
import api.ahm.motogp.entities.Team;
import api.ahm.motogp.repositories.TeamRepository;
import api.ahm.motogp.services.TeamService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teamsss")
public class TeamOldController {

    private final TeamService teamService;


    public TeamOldController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable int id){
        return teamService.getTeam(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@Valid @RequestBody CreateTeamRequest team, UriComponentsBuilder ucb) {
        Team newTeam = teamService.createTeam(team);
        URI location = ucb.path("/teams/{id}").buildAndExpand(newTeam.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> putTeam(@PathVariable int id, @RequestBody CreateTeamRequest team) {
        return teamService.updateTeam(id, team)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable int id) {
        return teamService.deleteTeam(id) ? ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
