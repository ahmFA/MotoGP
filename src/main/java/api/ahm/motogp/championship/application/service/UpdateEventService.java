package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.EventNotFoundException;
import api.ahm.motogp.championship.application.port.in.UpdateEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.EventRepositoryPort;
import api.ahm.motogp.championship.domain.model.Event;

public class UpdateEventService implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public UpdateEventService(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void updateEvent(EventCommand eventCommand){
        EventCommand currentEventCommand = eventRepositoryPort.getEventByEventId(eventCommand.id());
        if(currentEventCommand == null){
            throw new EventNotFoundException(eventCommand.id());
        }
        Event currentEvent = EventMapper.toDomain(currentEventCommand);
        Event updatedEvent = EventMapper.toDomain(eventCommand);
        if(currentEvent.getEventStatus() != updatedEvent.getEventStatus()){
            currentEvent.changeStatus(updatedEvent.getEventStatus());
        }
        if(currentEvent.getStartDate() != updatedEvent.getStartDate()){
            currentEvent.changeStartDate(updatedEvent.getStartDate());
        }
        eventRepositoryPort.updateEventStatus(EventMapper.toCommand(updatedEvent));
    }


}
