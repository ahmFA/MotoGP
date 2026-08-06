package api.ahm.motogp.championship.application.exception;

public class ChampionshipEventAlreadyExistsException extends RuntimeException {
    public ChampionshipEventAlreadyExistsException(int championshipGrandPrixId, String eventType) {
        super("Event " + eventType + " already exists in Championship Grand Prix " + championshipGrandPrixId);
    }
}
