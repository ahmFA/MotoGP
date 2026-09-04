package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;
import api.ahm.motogp.championship.application.port.query.EventResultView;

import java.util.List;

public interface EventResultRepositoryPort {
    boolean existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(int championshipEventId,
                                                                                              int championshipRiderId);
    void createChampionshipGrandPrixEventResults(CreateEventResultCommand resultsCommand);
    List<EventResultView> getEventResults(int eventId);
}
