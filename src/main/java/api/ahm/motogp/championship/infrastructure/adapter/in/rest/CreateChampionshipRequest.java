package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateChampionshipRequest(
        @NotNull
        @Positive
        Integer categoryId,
        @NotNull
        @Positive
        Integer year
) {
}
