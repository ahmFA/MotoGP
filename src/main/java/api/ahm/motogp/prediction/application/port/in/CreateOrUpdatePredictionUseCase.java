package api.ahm.motogp.prediction.application.port.in;

import api.ahm.motogp.prediction.domain.model.Prediction;

public interface CreateOrUpdateUserEventPredictionUseCase {

    Prediction createOrUpdateUserEventPrediction(Prediction prediction);
}
