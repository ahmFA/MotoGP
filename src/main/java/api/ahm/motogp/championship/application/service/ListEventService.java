package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.in.ListEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.application.port.query.EventView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEventService implements ListEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public ListEventService(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public EventCommand getEvent(int eventId){
        return eventRepositoryPort.getEventByEventId(eventId);
    }

    public List<EventView> getEventsByChampionship(int championshipId){
        return eventRepositoryPort.getEventsByChampionship(championshipId);
    }
}
