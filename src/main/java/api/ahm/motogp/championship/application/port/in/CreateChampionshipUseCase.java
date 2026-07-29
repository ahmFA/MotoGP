package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.domain.model.Championship;

public interface CreateChampionshipUseCase {
    Championship createChampionship(CreateChampionshipCommand championshipCommand);
}
