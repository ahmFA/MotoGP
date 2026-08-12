package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.exception.ChampionshipEventNotFoundException;
import api.ahm.motogp.championship.application.port.in.UpdateEventUseCase;
import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipEventRepositoryPort;
import api.ahm.motogp.championship.domain.model.ChampionshipEvent;

public class UpdateEventService implements UpdateEventUseCase {

    private final ChampionshipEventRepositoryPort eventRepositoryPort;

    public UpdateEventService(ChampionshipEventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void updateEvent(EventCommand eventCommand){
        EventCommand currentEventCommand = eventRepositoryPort.getEventByEventId(eventCommand.id());
        if(currentEventCommand == null){
            throw new ChampionshipEventNotFoundException(eventCommand.id());
        }
        ChampionshipEvent currentEvent = EventMapper.toDomain(currentEventCommand);
        ChampionshipEvent updatedEvent = EventMapper.toDomain(eventCommand);
        if(currentEvent.getEventStatus() != updatedEvent.getEventStatus()){
            currentEvent.changeStatus(updatedEvent.getEventStatus());
        }
        if(currentEvent.getStartDate() != updatedEvent.getStartDate()){
            currentEvent.changeStartDate(updatedEvent.getStartDate());
        }
        eventRepositoryPort.updateEventStatus(EventMapper.toCommand(updatedEvent));
    }


}
