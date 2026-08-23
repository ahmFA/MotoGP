package api.ahm.motogp.league.application.port.out;

import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.query.UserLeagueView;

import java.util.List;

public interface UserLeagueRepositoryPort {
    List<UserLeagueView> getUsersByLeague(long leagueId);
    UserLeagueView getUserByLeague(long leagueId, long userId);
    boolean existsUserLeagueByLeagueIdAndUserId(long leagueId, long userId);
    UserLeagueView createUserLeague(CreateUserLeagueCommand createUserLeagueCommand);
}
