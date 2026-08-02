package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateChampionshipTeamRequest(
        @NotNull
        @Positive
        int teamId,
        @NotNull
        @Positive
        int constructorId,
        @NotNull
        @Positive
        int championshipId,
        @NotBlank
        String name
) {
}
