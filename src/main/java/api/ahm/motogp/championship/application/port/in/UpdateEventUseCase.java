package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.infrastructure.adapter.in.rest.UpdateEventRequest;

public interface UpdateEventUseCase {
    void updateEvent(EventCommand eventCommand);
}
