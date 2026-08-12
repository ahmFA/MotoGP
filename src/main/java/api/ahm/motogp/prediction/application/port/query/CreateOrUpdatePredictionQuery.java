package api.ahm.motogp.prediction.application.port.query;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.identity.domain.model.valueobjects.UserId;

public record CreateOrUpdatePredictionQuery(
        Long id,
        UserId userId,
        EventId eventId,
        String grandPrixName,
        String eventType,
        RiderId firstRider,
        RiderId secondRider,
        RiderId thirdRider
) {
}
