package api.ahm.motogp.championship.application.exception;

public class EventResultDuplicatedInRequestException extends RuntimeException {
    public EventResultDuplicatedInRequestException(int championshipEventId, int championshipRiderId) {
        super("Result for Championship Rider " + championshipRiderId + " is duplicated in request for Championship Event " + championshipEventId);
    }
}
