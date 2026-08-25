package api.ahm.motogp.championship.application.exception;

import api.ahm.motogp.championship.domain.model.valueobjects.EventStatus;

public class EventCannotBePredictedException extends RuntimeException {
    public EventCannotBePredictedException(int eventId) {
        super("Event with id " + eventId + " is not available to receive predictions.");
    }
}
