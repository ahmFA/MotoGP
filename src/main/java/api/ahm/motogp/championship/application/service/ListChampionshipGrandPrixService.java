package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipGrandPrixNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.in.ListChampionshipGrandPrixUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixQueryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListChampionshipGrandPrixService implements ListChampionshipGrandPrixUseCase {

    private final ChampionshipGrandPrixQueryPort championshipGrandPrixQueryPort;
    private final ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public ListChampionshipGrandPrixService(ChampionshipGrandPrixQueryPort championshipGrandPrixQueryPort,
                                            ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort,
                                            ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipGrandPrixQueryPort = championshipGrandPrixQueryPort;
        this.championshipGrandPrixRepositoryPort = championshipGrandPrixRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public List<ChampionshipGrandPrixView> getChampionshipGrandPrixes(int championshipId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        return championshipGrandPrixQueryPort.getChampionshipGrandPrixesResponse(championshipId);
    }

    @Override
    public ChampionshipGrandPrixView getChampionshipGrandPrix(int championshipId, int championshipGrandPrixId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        if (!championshipGrandPrixRepositoryPort.existsChampionshipGrandPrixByChampionshipIdAndId(championshipId, championshipGrandPrixId)) {
            throw new ChampionshipGrandPrixNotFoundException(championshipGrandPrixId);
        }
        return championshipGrandPrixQueryPort.getChampionshipGrandPrixResponse(championshipId, championshipGrandPrixId);
    }
}
