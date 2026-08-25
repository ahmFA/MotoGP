package api.ahm.motogp.championship.application.exception;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException(int championshipGrandPrixId, String eventType) {
        super("Event " + eventType + " already exists in Championship Grand Prix " + championshipGrandPrixId);
    }
}
