package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateEventCommand;

public interface CreateEventUseCase {
    void createEvents(CreateEventCommand eventsCommand);

}
