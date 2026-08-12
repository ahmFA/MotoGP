package api.ahm.motogp.prediction.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.identity.domain.model.valueobjects.UserId;

public record UserEventPredictionResponse(
        EventId eventId,
        String grandPrixName,
        String eventType,
        RiderId firstRider,
        RiderId secondRider,
        RiderId thirdRider
) {
}
