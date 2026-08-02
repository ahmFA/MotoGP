package api.ahm.motogp.championship.application.port.in.command;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record EventCommand(
        int championshipGrandPrixId,
        @NotNull
        EventType eventType,
        Date startDate
) {

    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }
}
