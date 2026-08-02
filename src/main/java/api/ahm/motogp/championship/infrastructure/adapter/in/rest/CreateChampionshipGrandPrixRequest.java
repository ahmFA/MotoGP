package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record CreateChampionshipGrandPrixRequest(
        @NotNull
        @Positive
        Integer grandPrixId,
        @NotNull
        Date date,
        @NotNull
        @Positive
        Integer roundNumber
) {
}
