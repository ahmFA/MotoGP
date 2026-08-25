package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.EventAlreadyExistsException;
import api.ahm.motogp.championship.application.exception.EventDuplicatedInRequestException;
import api.ahm.motogp.championship.application.exception.ChampionshipGrandPrixNotFoundException;
import api.ahm.motogp.championship.application.port.in.CreateEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.CreateEventCommand;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import api.ahm.motogp.championship.domain.model.valueobjects.EventType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateEventService implements CreateEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort;

    public CreateEventService(EventRepositoryPort eventRepositoryPort,
                              ChampionshipGrandPrixRepositoryPort championshipGrandPrixRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.championshipGrandPrixRepositoryPort = championshipGrandPrixRepositoryPort;
    }

    @Override
    @Transactional
    public void createEvents(CreateEventCommand eventsCommand) {
        validateNoDuplicatedEventsInRequest(eventsCommand);
        validateChampionshipGrandPrixesExist(eventsCommand);
        validateEventsDoNotExist(eventsCommand);

        eventRepositoryPort.createEvents(eventsCommand.events());

    }

    private void validateNoDuplicatedEventsInRequest(CreateEventCommand eventsCommand) {
        Set<EventKey> eventKeys = new HashSet<>();
        for (EventCommand event : eventsCommand.events()) {
            EventKey eventKey = new EventKey(event.championshipGrandPrixId(), event.eventType());
            if (!eventKeys.add(eventKey)) {
                throw new EventDuplicatedInRequestException(
                        event.championshipGrandPrixId(),
                        event.eventType().name()
                );
            }
        }
    }

    private void validateChampionshipGrandPrixesExist(CreateEventCommand eventsCommand) {
        for (EventCommand event : eventsCommand.events()) {
            if (!championshipGrandPrixRepositoryPort.existsChampionshipGrandPrixByChampionshipIdAndId(
                    eventsCommand.championshipId(),
                    event.championshipGrandPrixId())) {
                throw new ChampionshipGrandPrixNotFoundException(event.championshipGrandPrixId());
            }
        }
    }

    private void validateEventsDoNotExist(CreateEventCommand eventsCommand) {
        for (EventCommand event : eventsCommand.events()) {
            if (eventRepositoryPort.existsChampionshipGrandPrixEventByChampionshipGrandPrixIdAndEventType(
                    event.championshipGrandPrixId(),
                    event.eventType())) {
                throw new EventAlreadyExistsException(
                        event.championshipGrandPrixId(),
                        event.eventType().name()
                );
            }
        }
    }

    private record EventKey(int championshipGrandPrixId, EventType eventType) {
    }
}
