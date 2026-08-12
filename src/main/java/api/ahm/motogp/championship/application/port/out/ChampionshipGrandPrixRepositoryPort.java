package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;
import api.ahm.motogp.championship.domain.model.ChampionshipGrandPrix;

public interface ChampionshipGrandPrixRepositoryPort {
    ChampionshipGrandPrix getChampionshipGrandPrixById(int championshipGrandPrixId);
    boolean existsChampionshipGrandPrixById(int championshipGrandPrixId);
    boolean existsChampionshipGrandPrixByChampionshipIdAndId(int championshipId, int championshipGrandPrixId);
    boolean existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(int championshipId, int grandPrixId);
    ChampionshipGrandPrixView createChampionshipGrandPrix(CreateChampionshipGrandPrixCommand command);
    void deleteChampionshipGrandPrixById(int championshipGrandPrixId);
}
