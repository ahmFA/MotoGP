package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipGrandPrixNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipGrandPrixUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteChampionshipGrandPrixService implements DeleteChampionshipGrandPrixUseCase {

    private final ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort;
    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public DeleteChampionshipGrandPrixService(ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort,
                                              ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipGrandPrixRepositoryPort = championshipGrandPrixRepositoryPort;
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public void deleteChampionshipGrandPrix(int championshipId, int championshipGrandPrixId) {
        if (!championshipRepositoryPort.existsChampionshipById(championshipId)) {
            throw new ChampionshipNotFoundException(championshipId);
        }
        if (!championshipGrandPrixRepositoryPort.existsChampionshipGrandPrixByChampionshipIdAndId(championshipId, championshipGrandPrixId)) {
            throw new ChampionshipGrandPrixNotFoundException(championshipGrandPrixId);
        }
        championshipGrandPrixRepositoryPort.deleteChampionshipGrandPrixById(championshipGrandPrixId);
    }
}
