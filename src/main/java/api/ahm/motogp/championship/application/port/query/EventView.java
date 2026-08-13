package api.ahm.motogp.championship.application.port.query;

import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.Date;

public record EventView(
        int id,
        EventType eventType,
        Date startDate,
        int championshipGrandPrixId,
        String grandPrixName
) {

}
