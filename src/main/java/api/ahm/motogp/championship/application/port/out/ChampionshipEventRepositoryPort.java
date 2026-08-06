package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipGranPrixEventView;

import java.util.List;

public interface ChampionshipEventRepositoryPort {
    boolean existsChampionshipGrandPrixEventById(int championshipGrandPrixEventId);
    boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                  EventCommand.EventType eventType);
    void createChampionshipGrandPrixEvents(List<EventCommand> events);
    EventCommand getEventByEventId(int eventId);
    List<ChampionshipGranPrixEventView> getEventsByChampionship(int championshipId);
}
