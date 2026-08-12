package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

import api.ahm.motogp.championship.domain.model.valueobjects.EventType;

import java.util.Date;

public record UpdateEventRequest(
        Date startDate,
        EventType eventType
) {
}
