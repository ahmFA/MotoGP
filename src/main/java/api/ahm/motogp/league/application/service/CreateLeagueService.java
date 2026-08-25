package api.ahm.motogp.league.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.league.application.port.in.CreateLeagueUseCase;
import api.ahm.motogp.league.application.port.in.command.CreateLeagueCommand;
import api.ahm.motogp.league.application.port.out.LeagueRepositoryPort;
import api.ahm.motogp.league.domain.model.League;
import org.springframework.stereotype.Service;

@Service
public class CreateLeagueService implements CreateLeagueUseCase {

    private final LeagueRepositoryPort leagueRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public CreateLeagueService(LeagueRepositoryPort leagueRepositoryPort,
                               ChampionshipRepositoryPort championshipRepositoryPort) {
        this.leagueRepositoryPort = leagueRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public League createLeague(CreateLeagueCommand createLeagueCommand) {
        if (!championshipRepositoryPort.existsChampionshipById(createLeagueCommand.championshipId())) {
            throw new ChampionshipNotFoundException(createLeagueCommand.championshipId());
        }
        return leagueRepositoryPort.createLeague(createLeagueCommand);
    }
}
