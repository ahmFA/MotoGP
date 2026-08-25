package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.EventNotFoundException;
import api.ahm.motogp.championship.application.exception.EventResultAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.EventResultDuplicatedInRequestException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.port.in.CreateEventResultUseCase;
import api.ahm.motogp.championship.application.port.in.command.CreateEventResultCommand;
import api.ahm.motogp.championship.application.port.in.command.EventResultCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.EventResultRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateEventResultService implements CreateEventResultUseCase {

    private final EventResultRepositoryPort championshipGrandPrixEventResultRepositoryPort;
    private final EventRepositoryPort championshipGrandPrixEventRepositoryPort;
    private final ChampionshipRiderRepositoryPort championshipRiderRepositoryPort;

    public CreateEventResultService(EventResultRepositoryPort championshipGrandPrixEventResultRepositoryPort,
                                    EventRepositoryPort championshipGrandPrixEventRepositoryPort,
                                    ChampionshipRiderRepositoryPort championshipRiderRepositoryPort) {
        this.championshipGrandPrixEventResultRepositoryPort = championshipGrandPrixEventResultRepositoryPort;
        this.championshipGrandPrixEventRepositoryPort = championshipGrandPrixEventRepositoryPort;
        this.championshipRiderRepositoryPort = championshipRiderRepositoryPort;
    }

    @Override
    @Transactional
    public void createResults(CreateEventResultCommand resultsCommand) {
        validateEventExists(resultsCommand);
        validateNoDuplicatedResultsInRequest(resultsCommand);
        validateChampionshipRidersExist(resultsCommand);
        validateResultsDoNotExist(resultsCommand);

        championshipGrandPrixEventResultRepositoryPort.createChampionshipGrandPrixEventResults(resultsCommand);
    }

    private void validateEventExists(CreateEventResultCommand resultsCommand) {
        if (!championshipGrandPrixEventRepositoryPort.existsChampionshipGrandPrixEventById(resultsCommand.championshipEventId())) {
            throw new EventNotFoundException(resultsCommand.championshipEventId());
        }
    }

    private void validateNoDuplicatedResultsInRequest(CreateEventResultCommand resultsCommand) {
        Set<Integer> championshipRiderIds = new HashSet<>();
        for (EventResultCommand result : resultsCommand.results()) {
            if (!championshipRiderIds.add(result.championshipRiderId())) {
                throw new EventResultDuplicatedInRequestException(
                        resultsCommand.championshipEventId(),
                        result.championshipRiderId()
                );
            }
        }
    }

    private void validateChampionshipRidersExist(CreateEventResultCommand resultsCommand) {
        for (EventResultCommand result : resultsCommand.results()) {
            if (!championshipRiderRepositoryPort.existsChampionshipRiderById(result.championshipRiderId())) {
                throw new ChampionshipRiderNotFoundException(result.championshipRiderId());
            }
        }
    }

    private void validateResultsDoNotExist(CreateEventResultCommand resultsCommand) {
        for (EventResultCommand result : resultsCommand.results()) {
            if (championshipGrandPrixEventResultRepositoryPort.existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(
                    resultsCommand.championshipEventId(),
                    result.championshipRiderId())) {
                throw new EventResultAlreadyExistsException(
                        resultsCommand.championshipEventId(),
                        result.championshipRiderId()
                );
            }
        }
    }
}
