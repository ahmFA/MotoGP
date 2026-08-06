package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public record CreateChampionshipEventBulkRequest(
        @NotEmpty
        @Size(max = 100)
        List<@Valid EventEntry> events
) {
    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }

    public record EventEntry(
            @NotNull
            @Positive
            int championshipGrandPrixId,
            @NotNull
            EventType eventType,
            @NotNull
            Date startDate
    ){}
}
