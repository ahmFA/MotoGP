package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.Date;

public record ChampionshipEventResponse(
        int id,
        EventType eventType,
        Date startDate,
        String grandPrixName
) {

}
