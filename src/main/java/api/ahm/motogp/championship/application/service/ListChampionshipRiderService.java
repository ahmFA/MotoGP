package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.port.in.ListChampionshipRiderUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderQueryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListChampionshipRiderService implements ListChampionshipRiderUseCase {

    private final ChampionshipRiderQueryPort championshipRiderQueryPort;
    private final ChampionshipRiderRepositoryPort championshipRiderRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public ListChampionshipRiderService(ChampionshipRiderQueryPort championshipRiderQueryPort,
                                        ChampionshipRiderRepositoryPort championshipRiderRepositoryPort,
                                        ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipRiderQueryPort = championshipRiderQueryPort;
        this.championshipRiderRepositoryPort = championshipRiderRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public List<ChampionshipRiderView> getChampionshipRiders(int championshipId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        return championshipRiderQueryPort.getChampionshipRidersResponse(championshipId);
    }

    @Override
    public ChampionshipRiderView getChampionshipRider(int championshipId, int championshipRiderId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        if (!championshipRiderRepositoryPort.existsChampionshipRiderByChampionshipIdAndId(championshipId, championshipRiderId)) {
            throw new ChampionshipRiderNotFoundException(championshipRiderId);
        }
        return championshipRiderQueryPort.getChampionshipRiderResponse(championshipId, championshipRiderId);
    }
}
