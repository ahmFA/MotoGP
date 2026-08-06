package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import java.util.Date;

public record ChampionshipEventResponse(
        int id,
        EventType eventType,
        Date startDate,
        String grandPrixName
) {
    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }
}
