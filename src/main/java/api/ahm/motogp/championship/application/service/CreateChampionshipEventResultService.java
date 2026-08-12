package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipEventNotFoundException;
import api.ahm.motogp.championship.application.exception.ChampionshipEventResultAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipEventResultDuplicatedInRequestException;
import api.ahm.motogp.championship.application.exception.ChampionshipRiderNotFoundException;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipEventResultUseCase;
import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventResultCommand;
import api.ahm.motogp.championship.application.port.in.command.EventResultCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventResultRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateChampionshipEventResultService implements CreateChampionshipEventResultUseCase {

    private final ChampionshipEventResultRepositoryPort championshipGrandPrixEventResultRepositoryPort;
    private final ChampionshipEventRepositoryPort championshipGrandPrixEventRepositoryPort;
    private final ChampionshipRiderRepositoryPort championshipRiderRepositoryPort;

    public CreateChampionshipEventResultService(ChampionshipEventResultRepositoryPort championshipGrandPrixEventResultRepositoryPort,
                                                ChampionshipEventRepositoryPort championshipGrandPrixEventRepositoryPort,
                                                ChampionshipRiderRepositoryPort championshipRiderRepositoryPort) {
        this.championshipGrandPrixEventResultRepositoryPort = championshipGrandPrixEventResultRepositoryPort;
        this.championshipGrandPrixEventRepositoryPort = championshipGrandPrixEventRepositoryPort;
        this.championshipRiderRepositoryPort = championshipRiderRepositoryPort;
    }

    @Override
    @Transactional
    public void createResults(CreateChampionshipEventResultCommand resultsCommand) {
        validateEventExists(resultsCommand);
        validateNoDuplicatedResultsInRequest(resultsCommand);
        validateChampionshipRidersExist(resultsCommand);
        validateResultsDoNotExist(resultsCommand);

        championshipGrandPrixEventResultRepositoryPort.createChampionshipGrandPrixEventResults(resultsCommand);
    }

    private void validateEventExists(CreateChampionshipEventResultCommand resultsCommand) {
        if (!championshipGrandPrixEventRepositoryPort.existsChampionshipGrandPrixEventById(resultsCommand.championshipEventId())) {
            throw new ChampionshipEventNotFoundException(resultsCommand.championshipEventId());
        }
    }

    private void validateNoDuplicatedResultsInRequest(CreateChampionshipEventResultCommand resultsCommand) {
        Set<Integer> championshipRiderIds = new HashSet<>();
        for (EventResultCommand result : resultsCommand.results()) {
            if (!championshipRiderIds.add(result.championshipRiderId())) {
                throw new ChampionshipEventResultDuplicatedInRequestException(
                        resultsCommand.championshipEventId(),
                        result.championshipRiderId()
                );
            }
        }
    }

    private void validateChampionshipRidersExist(CreateChampionshipEventResultCommand resultsCommand) {
        for (EventResultCommand result : resultsCommand.results()) {
            if (!championshipRiderRepositoryPort.existsChampionshipRiderById(result.championshipRiderId())) {
                throw new ChampionshipRiderNotFoundException(result.championshipRiderId());
            }
        }
    }

    private void validateResultsDoNotExist(CreateChampionshipEventResultCommand resultsCommand) {
        for (EventResultCommand result : resultsCommand.results()) {
            if (championshipGrandPrixEventResultRepositoryPort.existsChampionshipGrandPrixEventResultByChampionshipEventIdAndChampionshipRiderId(
                    resultsCommand.championshipEventId(),
                    result.championshipRiderId())) {
                throw new ChampionshipEventResultAlreadyExistsException(
                        resultsCommand.championshipEventId(),
                        result.championshipRiderId()
                );
            }
        }
    }
}
