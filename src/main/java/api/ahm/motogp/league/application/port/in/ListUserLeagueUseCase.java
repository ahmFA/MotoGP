package api.ahm.motogp.league.application.port.in;

import api.ahm.motogp.league.application.port.query.UserLeagueView;

import java.util.List;

public interface ListUserLeagueUseCase {
    List<UserLeagueView> getUsersByLeague(long leagueId);
    UserLeagueView getUserByLeague(long leagueId, long userId);
}
