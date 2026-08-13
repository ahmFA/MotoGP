package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;

public interface CreateEventResultUseCase {
    void createResults(CreateEventResultCommand resultsCommand);
}
