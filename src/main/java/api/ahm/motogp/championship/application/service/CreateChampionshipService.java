package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipAlreadyExistsException;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipCommand;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipUseCase;
import api.ahm.motogp.championship.application.port.out.ChampionshipRepositoryPort;
import api.ahm.motogp.championship.domain.model.Championship;
import org.springframework.stereotype.Service;

@Service
public class CreateChampionshipService implements CreateChampionshipUseCase {

    private final ChampionshipRepositoryPort championshipRepositoryPort;

    public CreateChampionshipService(ChampionshipRepositoryPort championshipRepositoryPort) {
        this.championshipRepositoryPort = championshipRepositoryPort;
    }

    @Override
    public Championship createChampionship(CreateChampionshipCommand championshipCommand) {
        if (championshipRepositoryPort.existsChampionshipByCategoryIdAndYear(
                championshipCommand.categoryId(),
                championshipCommand.year())) {
            throw new ChampionshipAlreadyExistsException(championshipCommand.categoryId(), championshipCommand.year());
        }
        return championshipRepositoryPort.createChampionship(championshipCommand);
    }
}
