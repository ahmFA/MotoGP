package api.ahm.motogp.team.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PutTeamRequest(
        @NotBlank
        String name,
        @NotNull
        Boolean active
) {
}
