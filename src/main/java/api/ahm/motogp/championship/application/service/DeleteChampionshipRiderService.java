package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipRiderUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteChampionshipRiderService implements DeleteChampionshipRiderUseCase {

    private final ChampionshipRiderRepositoryPort championshipRiderRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public DeleteChampionshipRiderService(ChampionshipRiderRepositoryPort championshipRiderRepositoryPort,
                                          ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipRiderRepositoryPort = championshipRiderRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public void deleteChampionshipRider(int championshipId, int championshipRiderId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        if (!championshipRiderRepositoryPort.existsChampionshipRiderByChampionshipIdAndId(championshipId, championshipRiderId)) {
            throw new ChampionshipRiderNotFoundException(championshipRiderId);
        }
        championshipRiderRepositoryPort.deleteChampionshipRiderById(championshipRiderId);
    }
}
