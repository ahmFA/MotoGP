package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.domain.model.Event;

public class EventMapper {

    public static Event toDomain(EventCommand command){
        return new Event(
                command.id(),
                command.championshipGrandPrixId(),
                command.eventType(),
                command.startDate(),
                command.eventStatus()
        );
    }

    public static EventCommand toCommand(Event event){
        return new EventCommand(
                event.getId(),
                event.getChampionshipGrandPrixId(),
                event.getEventType(),
                event.getStartDate(),
                event.getEventStatus()
        );
    }
}
