package api.ahm.motogp.championship.application.port.in.command;

import java.util.List;

public record CreateEventResultCommand(
        int championshipEventId,
        List<EventResultCommand> results
) {
}
