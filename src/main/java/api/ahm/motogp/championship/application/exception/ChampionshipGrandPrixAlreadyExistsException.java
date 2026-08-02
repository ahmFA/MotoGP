package api.ahm.motogp.championship.application.exception;

public class ChampionshipGrandPrixAlreadyExistsException extends RuntimeException {
    public ChampionshipGrandPrixAlreadyExistsException(int championshipId, int grandPrixId) {
        super("GrandPrix (" + grandPrixId + ") already exists in Championship (" + championshipId + ").");
    }
}
