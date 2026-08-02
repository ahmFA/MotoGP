package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixEventCommand;

public interface CreateChampionshipGrandPrixEventUseCase {
    void createEvents(CreateChampionshipGrandPrixEventCommand eventsCommand);

}
