package api.ahm.motogp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTeamRequest(
        @NotBlank(message="Empty name is not allowed")
        String name,
        @NotNull
        Boolean active
) {

}
