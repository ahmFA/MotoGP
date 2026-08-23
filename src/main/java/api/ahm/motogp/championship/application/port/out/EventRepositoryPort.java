package api.ahm.motogp.championship.application.port.out;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.EventView;
import api.ahm.motogp.championship.domain.model.Event;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.List;

public interface EventRepositoryPort {
    boolean existsChampionshipGrandPrixEventById(int championshipGrandPrixEventId);
    boolean existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(int championshipGrandPrixId,
                                                                                  EventType eventType);
    void createEvents(List<EventCommand> events);
    EventCommand getEventByEventId(int eventId);
    Event getEventById(int eventId);
    List<EventView> getEventsByChampionship(int championshipId);
    void updateEventStatus(EventCommand eventCommand);
}
