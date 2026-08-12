package api.ahm.motogp.championship.application.exception;

public class ChampionshipEventResultAlreadyExistsException extends RuntimeException {
    public ChampionshipEventResultAlreadyExistsException(int championshipEventId, int championshipRiderId) {
        super("Result for Championship Rider " + championshipRiderId + " already exists in Championship Event " + championshipEventId);
    }
}
