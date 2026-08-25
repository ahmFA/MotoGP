package api.ahm.motogp.championship.application.port.in.command;

import java.util.List;

public record CreateEventCommand(
        int championshipId,
        List<EventCommand> events
) {
}
