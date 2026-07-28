package api.ahm.motogp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record CreateRiderRequestDTO(
        @NotBlank
        String name,
        @Positive
        Integer number,
        Date birthday,
        @Positive
        Integer countryId
) {
}
