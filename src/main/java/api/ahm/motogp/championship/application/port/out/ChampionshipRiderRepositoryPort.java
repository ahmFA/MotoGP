package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipRiderCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;

public interface ChampionshipRiderRepositoryPort {
    boolean existsChampionshipRiderById(int championshipRiderId);
    boolean existsChampionshipRiderByChampionshipIdAndId(int championshipId, int championshipRiderId);
    boolean existsChampionshipRiderByChampionshipIdAndRiderId(int championshipId, int riderId);
    ChampionshipRiderView createChampionshipRider(CreateChampionshipRiderCommand command);
    void deleteChampionshipRiderById(int championshipRiderId);
}
