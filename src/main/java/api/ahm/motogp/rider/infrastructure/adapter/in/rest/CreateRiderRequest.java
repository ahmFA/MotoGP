package api.ahm.motogp.rider.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record CreateRiderRequest(
        @NotBlank
        String name,
        @NotNull
        @Positive
        Integer number,
        @NotNull
        Date birthday,
        @NotNull
        @Positive
        Integer countryId,
        @NotNull
        Boolean active
) {
}
