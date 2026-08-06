package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventResultCommand;

public interface ChampionshipEventResultRepositoryPort {
    boolean existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(int championshipEventId,
                                                                                              int championshipRiderId);
    void createChampionshipGrandPrixEventResults(CreateChampionshipEventResultCommand resultsCommand);
}
