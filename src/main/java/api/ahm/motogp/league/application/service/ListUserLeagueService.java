package api.ahm.motogp.league.application.service;

import api.ahm.motogp.league.application.exception.LeagueNotFoundException;
import api.ahm.motogp.league.application.exception.UserLeagueNotFoundException;
import api.ahm.motogp.league.application.port.in.ListUserLeagueUseCase;
import api.ahm.motogp.league.application.port.out.LeagueRepositoryPort;
import api.ahm.motogp.league.application.port.out.UserLeagueRepositoryPort;
import api.ahm.motogp.league.application.port.query.UserLeagueView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserLeagueService implements ListUserLeagueUseCase {

    private final LeagueRepositoryPort leagueRepositoryPort;
    private final UserLeagueRepositoryPort userLeagueRepositoryPort;

    public ListUserLeagueService(LeagueRepositoryPort leagueRepositoryPort,
                                 UserLeagueRepositoryPort userLeagueRepositoryPort) {
        this.leagueRepositoryPort = leagueRepositoryPort;
        this.userLeagueRepositoryPort = userLeagueRepositoryPort;
    }

    @Override
    public List<UserLeagueView> getUsersByLeague(long leagueId) {
        if (!leagueRepositoryPort.existsLeague(leagueId)) {
            throw new LeagueNotFoundException(leagueId);
        }
        return userLeagueRepositoryPort.getUsersByLeague(leagueId);
    }

    @Override
    public UserLeagueView getUserByLeague(long leagueId, long userId) {
        if (!leagueRepositoryPort.existsLeague(leagueId)) {
            throw new LeagueNotFoundException(leagueId);
        }
        if (!userLeagueRepositoryPort.existsUserLeagueByLeagueIdAndUserId(leagueId, userId)) {
            throw new UserLeagueNotFoundException(leagueId, userId);
        }
        return userLeagueRepositoryPort.getUserByLeague(leagueId, userId);
    }
}
