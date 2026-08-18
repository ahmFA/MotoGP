package api.ahm.motogp.prediction.application.service;

import api.ahm.motogp.championship.application.exception.EventNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.exception.EventCannotBePredictedException;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import api.ahm.motogp.championship.domain.model.Event;
import api.ahm.motogp.championship.domain.model.valueobjects.EventId;
import api.ahm.motogp.grandprix.application.port.out.GrandPrixRepositoryPort;
import api.ahm.motogp.league.application.exception.LeagueNotFoundException;
import api.ahm.motogp.league.application.port.out.LeagueRepositoryPort;
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
    private final EventRepositoryPort eventRepositoryPort;
    private final ChampionshipRiderRepositoryPort riderRepositoryPort;
    private final LeagueRepositoryPort leagueRepositoryPort;

    public CreateOrUpdatePredictionService(CreateOrUpdatePredictionRepositoryPort createOrUpdatePredictionRepositoryPort,
                                           GrandPrixRepositoryPort grandPrixRepositoryPort,
                                           EventRepositoryPort eventRepositoryPort,
                                           ChampionshipRiderRepositoryPort riderRepositoryPort,
                                           LeagueRepositoryPort leagueRepositoryPort) {
        this.createOrUpdatePredictionRepositoryPort = createOrUpdatePredictionRepositoryPort;
        this.grandPrixRepositoryPort = grandPrixRepositoryPort;
        this.eventRepositoryPort = eventRepositoryPort;
        this.riderRepositoryPort = riderRepositoryPort;
        this.leagueRepositoryPort = leagueRepositoryPort;
    }

    @Override
    public UserEventPredictionResponse createOrUpdateUserEventPrediction(CreateOrUpdatePredictionQuery predictionQuery, long leagueId) {
        Prediction prediction = toDomain(predictionQuery);
        if(leagueRepositoryPort.existsLeague(leagueId)){
            throw new LeagueNotFoundException(leagueId);
        }
        if(!eventRepositoryPort.existsChampionshipGrandPrixEventById(Math.toIntExact(prediction.getEventId().id()))){
            throw new EventNotFoundException(Math.toIntExact(prediction.getEventId().id()));
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
        Event event = eventRepositoryPort.getEventById(Math.toIntExact(prediction.getEventId().id()));
        if(!event.canBePredicted()){
            throw new EventCannotBePredictedException(event.getId());
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
        EventCommand eventCommand = eventRepositoryPort.getEventByEventId(Math.toIntExact(prediction.getEventId().id()));
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
