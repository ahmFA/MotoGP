package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipTeamNotFoundException;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipTeamUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteChampionshipTeamService implements DeleteChampionshipTeamUseCase {

    private final ChampionshipTeamRepositoryPort championshipTeamRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public DeleteChampionshipTeamService(ChampionshipTeamRepositoryPort championshipTeamRepositoryPort,  ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipTeamRepositoryPort = championshipTeamRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public void deleteChampionshipTeam(int championshipId, int championshipTeamId) {
        if(!championshipRepositoryPort.existsChampionshipById(championshipId)){
            throw new ChampionshipNotFoundException(championshipId);
        }
        if(!championshipTeamRepositoryPort.existsChampionshipTeamById(championshipTeamId)){
            throw new ChampionshipTeamNotFoundException(championshipTeamId);
        }
        championshipTeamRepositoryPort.deleteChampionshipTeamById(championshipTeamId);
    }
}
