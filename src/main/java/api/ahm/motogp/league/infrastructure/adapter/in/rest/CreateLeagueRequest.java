package api.ahm.motogp.league.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLeagueRequest(
        @NotNull
        @Positive
        Integer championshipId,
        @NotBlank
        String name
) {
}
