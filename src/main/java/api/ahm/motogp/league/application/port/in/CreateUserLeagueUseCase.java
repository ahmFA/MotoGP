package api.ahm.motogp.league.application.port.in;

import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.query.UserLeagueView;

public interface CreateUserLeagueUseCase {
    UserLeagueView createUserLeague(CreateUserLeagueCommand createUserLeagueCommand);
}
