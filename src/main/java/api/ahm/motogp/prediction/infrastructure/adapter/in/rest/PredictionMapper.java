package api.ahm.motogp.prediction.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.league.domain.model.valueobjects.UserLeagueId;
import api.ahm.motogp.prediction.application.port.query.CreateOrUpdatePredictionQuery;

public class PredictionMapper {

    public static CreateOrUpdatePredictionQuery toCommand(CreateOrUpdatePredictionRequest userPrediction, Long eventId, Long userLeagueId){
        return new CreateOrUpdatePredictionQuery(
                null,
                new UserLeagueId(userLeagueId),
                new EventId(eventId),
                null,
                null,
                new RiderId(userPrediction.first()),
                new RiderId(userPrediction.second()),
                new RiderId(userPrediction.third())
        );
    }

    public static UserEventPredictionResponse toUserEventResponse(CreateOrUpdatePredictionQuery userPrediction){
        return new UserEventPredictionResponse(
                userPrediction.eventId(),
                userPrediction.grandPrixName(),
                userPrediction.eventType(),
                userPrediction.firstRider(),
                userPrediction.secondRider(),
                userPrediction.thirdRider()
        );
    }
}
