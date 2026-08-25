package api.ahm.motogp.league.application.port.in;

import api.ahm.motogp.league.application.port.in.command.CreateLeagueCommand;
import api.ahm.motogp.league.domain.model.League;

public interface CreateLeagueUseCase {
    League createLeague(CreateLeagueCommand createLeagueCommand);
}
