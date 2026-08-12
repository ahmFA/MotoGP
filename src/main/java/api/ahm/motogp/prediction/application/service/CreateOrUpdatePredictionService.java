package api.ahm.motogp.prediction.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipEventNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.prediction.application.port.in.CreateOrUpdatePredictionUseCase;
import api.ahm.motogp.prediction.application.port.out.CreateOrUpdatePredictionRepositoryPort;
import api.ahm.motogp.prediction.application.port.query.CreateOrUpdatePredictionQuery;
import api.ahm.motogp.prediction.domain.model.Prediction;
import api.ahm.motogp.prediction.infrastructure.adapter.in.rest.UserEventPredictionResponse;
import api.ahm.motogp.grandprix.domain.model.GrandPrix;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOrUpdatePredictionService implements CreateOrUpdatePredictionUseCase {

    private final CreateOrUpdatePredictionRepositoryPort createOrUpdatePredictionRepositoryPort;
    private final GrandPrixRepositoryPort grandPrixRepositoryPort;
    private final ChampionshipEventRepositoryPort championshipEventRepositoryPort;
    private final ChampionshipRiderRepositoryPort riderRepositoryPort;

    public CreateOrUpdatePredictionService(CreateOrUpdatePredictionRepositoryPort createOrUpdatePredictionRepositoryPort,
                                           GrandPrixRepositoryPort grandPrixRepositoryPort,
                                           ChampionshipEventRepositoryPort championshipEventRepositoryPort,
                                           ChampionshipRiderRepositoryPort riderRepositoryPort) {
        this.createOrUpdatePredictionRepositoryPort = createOrUpdatePredictionRepositoryPort;
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
        this.championshipEventRepositoryPort = championshipEventRepositoryPort;
        this.riderRepositoryPort = riderRepositoryPort;
    }

    @Override
    public UserEventPredictionResponse createOrUpdateUserEventPrediction(CreateOrUpdatePredictionQuery predictionQuery) {
        Prediction prediction = toDomain(predictionQuery);
        if(!championshipEventRepositoryPort.existsChampionshipGrandPrixEventById(Math.toIntExact(prediction.getEventId().id()))){
            throw new ChampionshipEventNotFoundException(Math.toIntExact(prediction.getEventId().id()));
        }
        if(!riderRepositoryPort.existsChampionshipRiderById(Math.toIntExact(prediction.getFirstRider().id()))){
            throw new ChampionshipRiderNotFoundException(Math.toIntExact(prediction.getFirstRider().id()));
        }
        if(!riderRepositoryPort.existsChampionshipRiderById(Math.toIntExact(prediction.getSecondRider().id()))){
            throw new ChampionshipRiderNotFoundException(Math.toIntExact(prediction.getSecondRider().id()));
        }
        if(!riderRepositoryPort.existsChampionshipRiderById(Math.toIntExact(prediction.getThirdRider().id()))){
            throw new ChampionshipRiderNotFoundException(Math.toIntExact(prediction.getThirdRider().id()));
        }
        Prediction newPrediction = createOrUpdatePredictionRepositoryPort.createPrediction(prediction);
        return toResponse(newPrediction);
    }


    private Prediction toDomain(CreateOrUpdatePredictionQuery predictionQuery) {
        return new Prediction(
                predictionQuery.id(),
                predictionQuery.userId(),
                predictionQuery.eventId(),
                predictionQuery.firstRider(),
                predictionQuery.secondRider(),
                predictionQuery.thirdRider()
        );
    }

    private UserEventPredictionResponse toResponse(Prediction prediction) {
        EventCommand eventCommand = championshipEventRepositoryPort.getEventByEventId(Math.toIntExact(prediction.getEventId().id()));
        Optional<GrandPrix> gp = grandPrixRepositoryPort.getGrandPrix(eventCommand.championshipGrandPrixId());
        GrandPrix grandPrix = gp.get();
        return new UserEventPredictionResponse(
                new EventId((long)eventCommand.id()),
                grandPrix.name(),
                eventCommand.eventType().toString(),
                prediction.getFirstRider(),
                prediction.getSecondRider(),
                prediction.getThirdRider()

        );
    }
}
