package api.ahm.motogp.championship.application.port.in.command;

import api.ahm.motogp.championship.domain.model.valueobjects.EventStatus;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record EventCommand(
        Integer id,
        int championshipGrandPrixId,
        @NotNull
        EventType eventType,
        Date startDate,
        EventStatus eventStatus
) {

}
