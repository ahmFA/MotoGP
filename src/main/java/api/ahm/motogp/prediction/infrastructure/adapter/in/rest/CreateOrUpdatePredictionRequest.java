package api.ahm.motogp.prediction.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import jakarta.validation.constraints.NotNull;

public record CreateOrUpdatePredictionRequest(
        @NotNull
        long leagueId,
        @NotNull
        long first,
        @NotNull
        long second,
        @NotNull
        long third
) {
    public CreateOrUpdatePredictionRequest {
        if(first==second || first==third || second==third){
            throw new IllegalArgumentException("Same rider cannot be in two or more different positions");
        }
    }
}
