package api.ahm.motogp.prediction.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.championship.domain.model.valueobjects.RiderId;
import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.EventJPAEntity;
import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipRiderJPAEntity;
import api.ahm.motogp.league.domain.model.valueobjects.UserLeagueId;
import api.ahm.motogp.league.infrastructure.adapter.out.persistence.UserLeagueJPAEntity;
import api.ahm.motogp.prediction.application.exception.PredictionNotFoundException;
import api.ahm.motogp.prediction.application.port.out.CreateOrUpdatePredictionRepositoryPort;
import api.ahm.motogp.prediction.domain.model.Prediction;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class PredictionPersistenceAdapter implements CreateOrUpdatePredictionRepositoryPort {

    private final SpringDataPredictionRepository predictionRepository;
    private final EntityManager entityManager;

    public PredictionPersistenceAdapter(SpringDataPredictionRepository predictionRepository,
                                        EntityManager entityManager) {
        this.predictionRepository = predictionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Prediction createPrediction(Prediction prediction){
        PredictionJPAEntity predictionJPAEntity = toEntity(prediction);
        if(existsPrediction(prediction)){
            return updatePrediction(prediction);
        }
        else{
            PredictionJPAEntity newPredictionJPA = predictionRepository.save(predictionJPAEntity);
            return toDomain(newPredictionJPA);
        }
    }

    @Override
    public Prediction getPrediction(Prediction prediction){
        Optional<PredictionJPAEntity> entity = predictionRepository.findByUserIdAndEventId(prediction.getUserLeagueId().id(), prediction.getEventId().id());
        if(entity.isEmpty()){
            throw new IllegalArgumentException("Prediction not found");
        }
        PredictionJPAEntity predictionJPAEntity = entity.get();
        return toDomain(predictionJPAEntity);
    }

    @Override
    public boolean existsPrediction(Prediction prediction){
        return predictionRepository.existsByUserIdAndEventId(prediction.getUserLeagueId().id(), prediction.getEventId().id());
    }

    @Override
    public Prediction updatePrediction(Prediction prediction){
        Optional<PredictionJPAEntity> entity = predictionRepository.findByUserIdAndEventId(prediction.getUserLeagueId().id(), prediction.getEventId().id());
        if(entity.isEmpty()){
            throw new PredictionNotFoundException("Prediction not found");
        }
        PredictionJPAEntity updatedPrediction = entity.get();
        ChampionshipRiderJPAEntity firstRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getFirstRider().id());
        ChampionshipRiderJPAEntity secondRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getSecondRider().id());
        ChampionshipRiderJPAEntity thirdRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getThirdRider().id());
        updatedPrediction.setFirstRider(firstRider);
        updatedPrediction.setSecondRider(secondRider);
        updatedPrediction.setThirdRider(thirdRider);

        PredictionJPAEntity updatedPredictionJPAEntity = predictionRepository.save(entity.get());
        return toDomain(updatedPredictionJPAEntity);
    }

    private Prediction toDomain(PredictionJPAEntity entity){
        return new Prediction(
                entity.getId(),
                new UserLeagueId(entity.getUser().getId()),
                new EventId((long) entity.getEvent().getId()),
                new RiderId((long) entity.getFirstRider().getId()),
                new RiderId((long) entity.getSecondRider().getId()),
                new RiderId((long) entity.getThirdRider().getId())
        );
    }

    private PredictionJPAEntity toEntity(Prediction prediction){
        UserLeagueJPAEntity userJPAEntity = entityManager.find(UserLeagueJPAEntity.class, prediction.getUserLeagueId().id());
        EventJPAEntity eventJPAEntity = entityManager.find(EventJPAEntity.class, prediction.getEventId().id());
        ChampionshipRiderJPAEntity firstRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getFirstRider().id());
        ChampionshipRiderJPAEntity secondRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getSecondRider().id());
        ChampionshipRiderJPAEntity thirdRider = entityManager.find(ChampionshipRiderJPAEntity.class, prediction.getThirdRider().id());
        PredictionJPAEntity predictionJPAEntity = new PredictionJPAEntity();
        predictionJPAEntity.setUser(userJPAEntity);
        predictionJPAEntity.setEvent(eventJPAEntity);
        predictionJPAEntity.setFirstRider(firstRider);
        predictionJPAEntity.setSecondRider(secondRider);
        predictionJPAEntity.setThirdRider(thirdRider);
        return predictionJPAEntity;
    }
}
