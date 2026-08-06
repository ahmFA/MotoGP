package api.ahm.motogp.championship.application.exception;

public class ChampionshipEventDuplicatedInRequestException extends RuntimeException {
    public ChampionshipEventDuplicatedInRequestException(int championshipGrandPrixId, String eventType) {
        super("Event " + eventType + " is duplicated in request for Championship Grand Prix " + championshipGrandPrixId);
    }
}
