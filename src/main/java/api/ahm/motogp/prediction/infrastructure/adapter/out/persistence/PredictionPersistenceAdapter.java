package api.ahm.motogp.prediction.infrastructure.adapter.out.persistence;

import api.ahm.motogp.prediction.application.port.out.CreateOrUpdatePredictionRepositoryPort;
import api.ahm.motogp.prediction.domain.model.Prediction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CreatePredictionPersistenceAdapter implements CreateOrUpdatePredictionRepositoryPort {

    private final SpringDataPredictionRepository predictionRepository;

    public CreatePredictionPersistenceAdapter(SpringDataPredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public Prediction createPrediction(Prediction prediction){

    }

    @Override
    public Optional<Prediction> getPrediction(Prediction prediction){
        return predictionRepository.findByUserIdAndEventId(Long.valueOf(prediction.getUserId())., prediction.getEventId());
    }
}
