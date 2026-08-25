package api.ahm.motogp.league.infrastructure.adapter.in.rest;

import api.ahm.motogp.league.application.port.in.CreateUserLeagueUseCase;
import api.ahm.motogp.league.application.port.in.ListUserLeagueUseCase;
import api.ahm.motogp.league.application.port.query.UserLeagueView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/leagues/{leagueId}/users")
public class UserLeagueController {

    private final ListUserLeagueUseCase listUserLeagueUseCase;
    private final CreateUserLeagueUseCase createUserLeagueUseCase;

    public UserLeagueController(ListUserLeagueUseCase listUserLeagueUseCase,
                                CreateUserLeagueUseCase createUserLeagueUseCase) {
        this.listUserLeagueUseCase = listUserLeagueUseCase;
        this.createUserLeagueUseCase = createUserLeagueUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UserLeagueResponse>> getUsers(@PathVariable long leagueId) {
        List<UserLeagueResponse> responses = UserLeagueMapper.toResponse(listUserLeagueUseCase.getUsersByLeague(leagueId));
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserLeagueResponse> getUser(@PathVariable long leagueId,
                                                      @PathVariable long userId) {
        UserLeagueView userLeagueView = listUserLeagueUseCase.getUserByLeague(leagueId, userId);
        return ResponseEntity.ok(UserLeagueMapper.toResponse(userLeagueView));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserLeagueResponse> addUserToLeague(@PathVariable long leagueId,
                                                              @PathVariable long userId,
                                                              UriComponentsBuilder ucb) {
        UserLeagueView userLeagueView = createUserLeagueUseCase.createUserLeague(
                UserLeagueMapper.toCommand(leagueId, userId)
        );
        URI location = ucb.path("/leagues/{leagueId}/users/{userId}")
                .buildAndExpand(userLeagueView.leagueId(), userLeagueView.userId())
                .toUri();
        return ResponseEntity.created(location).body(UserLeagueMapper.toResponse(userLeagueView));
    }
}
