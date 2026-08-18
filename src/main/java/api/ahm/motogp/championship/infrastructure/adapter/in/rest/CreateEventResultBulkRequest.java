package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateEventResultBulkRequest(
        @NotEmpty
        @Size(max = 100)
        List<@Valid ResultEntry> results
) {
    public record ResultEntry(
            @NotNull
            @Positive
            Integer championshipRiderId,
            @NotNull
            @Positive
            Integer position,
            @NotNull
            @PositiveOrZero
            Float points
    ) {
    }
}
