package api.ahm.motogp.championship.application.exception;

public class EventDuplicatedInRequestException extends RuntimeException {
    public EventDuplicatedInRequestException(int championshipGrandPrixId, String eventType) {
        super("Event " + eventType + " is duplicated in request for Championship Grand Prix " + championshipGrandPrixId);
    }
}
