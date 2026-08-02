package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;

public interface ChampionshipGrandPrixRepositoryPort {
    boolean existsChampionshipGrandPrixById(int championshipGrandPrixId);
    boolean existsChampionshipGrandPrixByChampionshipIdAndId(int championshipId, int championshipGrandPrixId);
    boolean existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(int championshipId, int grandPrixId);
    ChampionshipGrandPrixView createChampionshipGrandPrix(CreateChampionshipGrandPrixCommand command);
    void deleteChampionshipGrandPrixById(int championshipGrandPrixId);
}
