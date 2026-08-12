package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateChampionshipRiderRequest(
        @NotNull
        @Positive
        Integer riderId,
        @NotNull
        @Positive
        Integer teamId,
        @NotNull
        @PositiveOrZero
        Integer number
) {
}
