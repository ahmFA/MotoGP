package api.ahm.motogp.league.application.port.out;

import api.ahm.motogp.league.application.port.in.command.CreateLeagueCommand;
import api.ahm.motogp.league.domain.model.League;

public interface LeagueRepositoryPort {
    League createLeague(CreateLeagueCommand createLeagueCommand);
    boolean existsLeague(long leagueId);
}
