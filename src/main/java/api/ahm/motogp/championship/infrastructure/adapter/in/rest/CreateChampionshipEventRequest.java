package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record CreateChampionshipEventRequest(
      @NotNull
      @Positive
      int championshipGrandPrixId,
      @NotNull
      EventType eventType,
      @NotNull
      Date startDate
) {
    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }
}
