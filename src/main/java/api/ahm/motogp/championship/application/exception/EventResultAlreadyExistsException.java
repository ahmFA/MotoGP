package api.ahm.motogp.championship.application.exception;

public class EventResultAlreadyExistsException extends RuntimeException {
    public EventResultAlreadyExistsException(int championshipEventId, int championshipRiderId) {
        super("Result for Championship Rider " + championshipRiderId + " already exists in Championship Event " + championshipEventId);
    }
}
