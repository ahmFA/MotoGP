package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;

public interface EventResultRepositoryPort {
    boolean existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(int championshipEventId,
                                                                                              int championshipRiderId);
    void createChampionshipGrandPrixEventResults(CreateEventResultCommand resultsCommand);
}
