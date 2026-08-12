package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.in.DeleteChampionshipUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteChampionshipService implements DeleteChampionshipUseCase {

    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public DeleteChampionshipService(ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public void deleteChampionship(int id) {
        if (!championshipRepositoryPort.existsChampionshipById(id)) {
            throw new ChampionshipNotFoundException(id);
        }
        championshipRepositoryPort.deleteChampionship(id);
    }
}
