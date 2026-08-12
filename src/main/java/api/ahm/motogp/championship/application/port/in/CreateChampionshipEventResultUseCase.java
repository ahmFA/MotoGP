package api.ahm.motogp.championship.application.port.in;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventResultCommand;

public interface CreateChampionshipEventResultUseCase {
    void createResults(CreateChampionshipEventResultCommand resultsCommand);
}
