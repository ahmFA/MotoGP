package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.query.EventView;

import java.util.List;

public interface ListEventUseCase {
    EventCommand getEvent(int eventId);
    List<EventView> getEventsByChampionship(int championshipId);
}
