package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.domain.model.Championship;

import java.util.List;
import java.util.Optional;

public interface ListChampionshipUseCase {
    List<Championship> getChampionships();
    Optional<Championship> getChampionship(int id);
}
