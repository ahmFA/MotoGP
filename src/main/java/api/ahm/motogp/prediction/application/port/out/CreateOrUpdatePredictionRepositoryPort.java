package api.ahm.motogp.prediction.application.port.out;

import api.ahm.motogp.prediction.domain.model.Prediction;

import java.util.Optional;

public interface CreateOrUpdatePredictionRepositoryPort {

    Prediction createPrediction(Prediction prediction);
    Prediction updatePrediction(Prediction prediction);
    boolean existsPrediction(Prediction prediction);
    Prediction getPrediction(Prediction prediction);
}
