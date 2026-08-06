package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipEventAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.ChampionshipEventDuplicatedInRequestException;
import api.ahm.motogp.championship.application.exception.ChampionshipGrandPrixNotFoundException;
import api.ahm.motogp.championship.application.port.in.CreateChampionshipEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipEventCommand;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateChampionshipEventService implements CreateChampionshipEventUseCase {

    private final ChampionshipEventRepositoryPort championshipGrandPrixEventRepositoryPort;
    private final ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort;

    public CreateChampionshipEventService(ChampionshipEventRepositoryPort championshipGrandPrixEventRepositoryPort,
                                          ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort) {
        this.championshipGrandPrixEventRepositoryPort = championshipGrandPrixEventRepositoryPort;
        this.championshipGrandPrixRepositoryPort = championshipGrandPrixRepositoryPort;
    }

    @Override
    @Transactional
    public void createEvents(CreateChampionshipEventCommand eventsCommand) {
        validateNoDuplicatedEventsInRequest(eventsCommand);
        validateChampionshipGrandPrixesExist(eventsCommand);
        validateEventsDoNotExist(eventsCommand);

        championshipGrandPrixEventRepositoryPort.createChampionshipGrandPrixEvents(eventsCommand.events());
    }

    private void validateNoDuplicatedEventsInRequest(CreateChampionshipEventCommand eventsCommand) {
        Set<EventKey> eventKeys = new HashSet<>();
        for (EventCommand event : eventsCommand.events()) {
            EventKey eventKey = new EventKey(event.championshipGrandPrixId(), event.eventType());
            if (!eventKeys.add(eventKey)) {
                throw new ChampionshipEventDuplicatedInRequestException(
                        event.championshipGrandPrixId(),
                        event.eventType().name()
                );
            }
        }
    }

    private void validateChampionshipGrandPrixesExist(CreateChampionshipEventCommand eventsCommand) {
        for (EventCommand event : eventsCommand.events()) {
            if (!championshipGrandPrixRepositoryPort.existsChampionshipGrandPrixByChampionshipIdAndId(
                    eventsCommand.championshipId(),
                    event.championshipGrandPrixId())) {
                throw new ChampionshipGrandPrixNotFoundException(event.championshipGrandPrixId());
            }
        }
    }

    private void validateEventsDoNotExist(CreateChampionshipEventCommand eventsCommand) {
        for (EventCommand event : eventsCommand.events()) {
            if (championshipGrandPrixEventRepositoryPort.existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(
                    event.championshipGrandPrixId(),
                    event.eventType())) {
                throw new ChampionshipEventAlreadyExistsException(
                        event.championshipGrandPrixId(),
                        event.eventType().name()
                );
            }
        }
    }

    private record EventKey(int championshipGrandPrixId, EventCommand.EventType eventType) {
    }
}
