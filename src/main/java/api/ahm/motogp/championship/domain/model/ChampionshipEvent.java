package api.ahm.motogp.championship.domain.model;

import java.util.Date;

public record ChampionshipEvent(
        int id,
        int championshipGrandPrixId,
        EventType eventType,
        Date startDate,
        EventStatus eventStatus
) {
    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }

    public enum EventStatus {
        CLOSED,
        OPEN,
        FINISHED
    }
    public ChampionshipEvent {
        if(id <= 0){
            throw new IllegalArgumentException("ChampionshipEvent ID must be greater than 0");
        }
        if(championshipGrandPrixId <= 0){
            throw new IllegalArgumentException("Championship Grand Prix ID must be greater than 0");
        }
        if(eventType == null){
            throw new IllegalArgumentException("Championship Event Type is not valid");
        }
        if(startDate == null){
            throw new IllegalArgumentException("Championship Start Date is null");
        }
    }
}
