package api.ahm.motogp.grandprix.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateGrandPrixRequest(
        @NotBlank
        String name,
        @NotBlank
        String circuitName,
        @NotNull
        @Positive
        Integer countryId
) {
}
