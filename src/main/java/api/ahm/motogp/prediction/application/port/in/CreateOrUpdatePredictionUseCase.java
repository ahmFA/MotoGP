package api.ahm.motogp.prediction.application.port.in;

import api.ahm.motogp.prediction.application.port.query.CreateOrUpdatePredictionQuery;
import api.ahm.motogp.prediction.infrastructure.adapter.in.rest.UserEventPredictionResponse;

public interface CreateOrUpdatePredictionUseCase {

    UserEventPredictionResponse createOrUpdateUserEventPrediction(CreateOrUpdatePredictionQuery prediction);
}
