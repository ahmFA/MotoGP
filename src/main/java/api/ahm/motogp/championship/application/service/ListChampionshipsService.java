package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipNotFoundException;
import api.ahm.motogp.championship.application.port.in.ListChampionshipUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.domain.model.Championship;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ListChampionshipsService implements ListChampionshipUseCase {

    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public ListChampionshipsService(ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public List<Championship> getChampionships() {
        return championshipRepositoryPort.getChampionships();
    }

    @Override
    public Optional<Championship> getChampionship(int id) {
        Optional<Championship> championship = championshipRepositoryPort.getChampionship(id);
        if (championship.isEmpty()) {
            throw new ChampionshipNotFoundException(id);
        }
        return championship;
    }
}
