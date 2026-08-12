package api.ahm.motogp.championship.application.service;

import api.ahm.motogp.championship.application.port.in.command.EventCommand;
import api.ahm.motogp.championship.domain.model.ChampionshipEvent;

public class EventMapper {

    public static ChampionshipEvent toDomain(EventCommand command){
        return new ChampionshipEvent(
                command.id(),
                command.championshipGrandPrixId(),
                command.eventType(),
                command.startDate(),
                command.eventStatus()
        );
    }

    public static EventCommand toCommand(ChampionshipEvent championshipEvent){
        return new EventCommand(
                championshipEvent.getId(),
                championshipEvent.getChampionshipGrandPrixId(),
                championshipEvent.getEventType(),
                championshipEvent.getStartDate(),
                championshipEvent.getEventStatus()
        );
    }
}
