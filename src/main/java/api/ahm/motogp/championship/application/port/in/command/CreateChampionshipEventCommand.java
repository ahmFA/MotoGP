package api.ahm.motogp.championship.application.port.in.command;

import java.util.List;

public record CreateChampionshipEventCommand(
        int championshipId,
        List<EventCommand> events
) {
}
