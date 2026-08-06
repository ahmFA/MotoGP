package api.ahm.motogp.championship.application.port.query;

import java.util.Date;

public record ChampionshipGranPrixEventView(
        int id,
        EventType eventType,
        Date startDate,
        int championshipGrandPrixId,
        String grandPrixName
) {
    public enum EventType {
        QUALIFYING,
        SPRINT,
        MAIN_RACE
    }
}
