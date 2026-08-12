package api.ahm.motogp.championship.application.exception;

public class ChampionshipRiderAlreadyExistsException extends RuntimeException {
    public ChampionshipRiderAlreadyExistsException(int championshipId, int riderId) {
        super("Rider (" + riderId + ") already exists in Championship (" + championshipId + ").");
    }
}
