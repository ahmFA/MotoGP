package api.ahm.motogp.league.application.service;

import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.league.application.exception.LeagueNotFoundException;
import api.ahm.motogp.league.application.exception.UserLeagueAlreadyExistsException;
import api.ahm.motogp.identity.application.exception.UserNotFoundException;
import api.ahm.motogp.league.application.port.in.CreateUserLeagueUseCase;
import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.out.LeagueRepositoryPort;
import api.ahm.motogp.league.application.port.out.UserLeagueRepositoryPort;
import api.ahm.motogp.league.application.port.query.UserLeagueView;
import org.springframework.stereotype.Service;

@Service
public class CreateUserLeagueService implements CreateUserLeagueUseCase {

    private final LeagueRepositoryPort leagueRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final UserLeagueRepositoryPort userLeagueRepositoryPort;

    public CreateUserLeagueService(LeagueRepositoryPort leagueRepositoryPort,
                                   UserRepositoryPort userRepositoryPort,
                                   UserLeagueRepositoryPort userLeagueRepositoryPort) {
        this.leagueRepositoryPort = leagueRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.userLeagueRepositoryPort = userLeagueRepositoryPort;
    }

    @Override
    public UserLeagueView createUserLeague(CreateUserLeagueCommand createUserLeagueCommand) {
        long leagueId = createUserLeagueCommand.leagueId();
        long userId = createUserLeagueCommand.userId().id();

        if (!leagueRepositoryPort.existsLeague(leagueId)) {
            throw new LeagueNotFoundException(leagueId);
        }
        if (!userRepositoryPort.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        if (userLeagueRepositoryPort.existsUserLeagueByLeagueIdAndUserId(leagueId, userId)) {
            throw new UserLeagueAlreadyExistsException(leagueId, userId);
        }
        return userLeagueRepositoryPort.createUserLeague(createUserLeagueCommand);
    }
}
