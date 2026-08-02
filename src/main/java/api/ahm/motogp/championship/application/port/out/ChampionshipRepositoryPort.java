package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipCommand;
import api.ahm.motogp.championship.domain.model.Championship;

import java.util.List;
import java.util.Optional;

public interface ChampionshipRepositoryPort {
    List<Championship> getChampionships();
    Optional<Championship> getChampionship(int id);
    Boolean existsChampionshipById(int id);
    Boolean existsChampionshipByCategoryIdAndYear(Integer categoryId, Integer year);
    Championship createChampionship(CreateChampionshipCommand championshipCommand);
    void deleteChampionship(int id);
}
