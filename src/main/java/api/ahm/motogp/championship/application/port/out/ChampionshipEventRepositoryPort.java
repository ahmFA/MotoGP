package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.ChampionshipEventView;
import api.ahm.motogp.championship.domain.model.ChampionshipEvent;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.List;

public interface ChampionshipEventRepositoryPort {
    boolean existsChampionshipGrandPrixEventById(int championshipGrandPrixEventId);
    boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                  EventType eventType);
    void createChampionshipGrandPrixEvents(List<EventCommand> events);
    EventCommand getEventByEventId(int eventId);
    ChampionshipEvent getEventById(int eventId);
    List<ChampionshipEventView> getEventsByChampionship(int championshipId);
    void updateEventStatus(EventCommand eventCommand);
}
