package api.ahm.motogp.championship.application.port.in.command;

import java.util.List;

public record CreateChampionshipEventResultCommand(
        int championshipEventId,
        List<EventResultCommand> results
) {
}
