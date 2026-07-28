package api.ahm.motogp.team.infrastructure.adapter.in.rest;

import api.ahm.motogp.team.application.port.in.CreateTeamUseCase;
import api.ahm.motogp.team.application.port.in.DeleteTeamUseCase;
import api.ahm.motogp.team.application.port.in.FullUpdateTeamUseCase;
import api.ahm.motogp.team.application.port.in.ListTeamUseCase;
import api.ahm.motogp.team.domain.model.Team;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final ListTeamUseCase listTeamUseCase;
    private final CreateTeamUseCase createTeamUseCase;
    private final FullUpdateTeamUseCase fullUpdateTeamUseCase;
    private final DeleteTeamUseCase deleteTeamUseCase;

    public TeamController(ListTeamUseCase listTeamUseCase,
                          CreateTeamUseCase createTeamUseCase,
                          FullUpdateTeamUseCase fullUpdateTeamUseCase,
                          DeleteTeamUseCase deleteTeamUseCase) {
        this.listTeamUseCase = listTeamUseCase;
        this.createTeamUseCase = createTeamUseCase;
        this.fullUpdateTeamUseCase = fullUpdateTeamUseCase;
        this.deleteTeamUseCase = deleteTeamUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamResponse> getTeams() {
        List<Team> teams = listTeamUseCase.getTeams();
        return TeamMapper.toResponse(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable int id) {
        Optional<TeamResponse> teamResponse = listTeamUseCase.getTeam(id).map(TeamMapper::toResponse);
        return teamResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamResponse> addTeam(@Valid @RequestBody CreateTeamRequest teamRequest,
                                                UriComponentsBuilder ucb) {
        Team team = createTeamUseCase.createTeam(TeamMapper.toCommand(teamRequest));
        URI location = ucb.path("/teams/{id}").buildAndExpand(team.id()).toUri();
        return ResponseEntity.created(location).body(TeamMapper.toResponse(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> putTeam(@PathVariable int id,
                                                @Valid @RequestBody PutTeamRequest teamRequest) {
        Team team = fullUpdateTeamUseCase.fullUpdateTeam(TeamMapper.toCommand(id, teamRequest));
        return ResponseEntity.ok(TeamMapper.toResponse(team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamResponse> deleteTeam(@PathVariable int id) {
        deleteTeamUseCase.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
