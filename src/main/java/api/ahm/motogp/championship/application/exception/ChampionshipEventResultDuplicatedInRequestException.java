package api.ahm.motogp.championship.application.exception;

public class ChampionshipEventResultDuplicatedInRequestException extends RuntimeException {
    public ChampionshipEventResultDuplicatedInRequestException(int championshipEventId, int championshipRiderId) {
        super("Result for Championship Rider " + championshipRiderId + " is duplicated in request for Championship Event " + championshipEventId);
    }
}
