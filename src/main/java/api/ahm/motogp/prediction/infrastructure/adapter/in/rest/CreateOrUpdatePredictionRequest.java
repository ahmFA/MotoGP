package api.ahm.motogp.prediction.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateUserEventPredictionRequest(
        @NotNull
        RiderId first,
        @NotNull
        RiderId second,
        @NotNull
        RiderId third
) {
    public CreateOrUpdateUserEventPredictionRequest {
        if(first.equals(second) || first.equals(third) || second.equals(third)){
            throw new IllegalArgumentException("Same rider cannot be in two or more different positions");
        }
    }
}
