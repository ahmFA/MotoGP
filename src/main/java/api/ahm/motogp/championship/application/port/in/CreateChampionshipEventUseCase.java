package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventCommand;

public interface CreateChampionshipEventUseCase {
    void createEvents(CreateChampionshipEventCommand eventsCommand);

}
