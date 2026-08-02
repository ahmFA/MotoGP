package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipTeamNotFoundException;
import api.ahm.motogp.championship.application.port.in.ListChampionshipTeamUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamQueryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;
import api.ahm.motogp.team.application.port.out.TeamRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListChampionshipTeamService implements ListChampionshipTeamUseCase {

    private final ChampionshipTeamQueryPort championshipTeamQueryPort;
    private final ChampionshipTeamRepositoryPort championshipTeamRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public ListChampionshipTeamService(ChampionshipTeamQueryPort championshipTeamQueryPort,
                                       ChampionshipTeamRepositoryPort championshipTeamRepositoryPort,
                                       TeamRepositoryPort teamRepositoryPort,
                                       ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipTeamQueryPort = championshipTeamQueryPort;
        this.championshipTeamRepositoryPort = championshipTeamRepositoryPort;
        this.teamRepositoryPort = teamRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public List<ChampionshipTeamView> getChampionshipTeams(int championshipId) {
        if(!championshipRepositoryPort.existsChampionshipById(championshipId)){
            throw new ChampionshipNotFoundException(championshipId);
        }
        return championshipTeamQueryPort.getChampionshipTeamsResponse(championshipId);
    }

    @Override
    public ChampionshipTeamView getChampionshipTeam(int championshipId, int championshipTeamId){
        if(!championshipRepositoryPort.existsChampionshipById(championshipId)){
            throw new ChampionshipNotFoundException(championshipId);
        }
        if(!championshipTeamRepositoryPort.existsChampionshipTeamById(championshipTeamId)){
            throw new ChampionshipTeamNotFoundException(championshipTeamId);
        }
        return championshipTeamQueryPort.getChampionshipTeamResponse(championshipId, championshipTeamId);
    }


}
