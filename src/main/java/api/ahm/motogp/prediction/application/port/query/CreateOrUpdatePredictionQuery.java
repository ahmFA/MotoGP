package api.ahm.motogp.prediction.application.port.query;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.league.domain.model.valueobjects.UserLeagueId;

public record CreateOrUpdatePredictionQuery(
        Long id,
        UserLeagueId userLeagueId,
        EventId eventId,
        String grandPrixName,
        String eventType,
        RiderId firstRider,
        RiderId secondRider,
        RiderId thirdRider
) {
}
