package api.ahm.motogp.league.infrastructure.adapter.in.rest;

import api.ahm.motogp.league.application.port.in.CreateLeagueUseCase;
import api.ahm.motogp.league.domain.model.League;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    private final CreateLeagueUseCase createLeagueUseCase;

    public LeagueController(CreateLeagueUseCase createLeagueUseCase) {
        this.createLeagueUseCase = createLeagueUseCase;
    }

    @PostMapping
    public ResponseEntity<LeagueResponse> createLeague(@Valid @RequestBody CreateLeagueRequest createLeagueRequest,
                                                       UriComponentsBuilder ucb) {
        League league = createLeagueUseCase.createLeague(LeagueMapper.toCommand(createLeagueRequest));
        URI location = ucb.path("/leagues/{id}").buildAndExpand(league.id()).toUri();
        return ResponseEntity.created(location).body(LeagueMapper.toResponse(league));
    }
}
