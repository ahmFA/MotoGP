package api.ahm.motogp.championship.application.exception;

public class ChampionshipTeamAlreadyExistsException extends RuntimeException {
    public ChampionshipTeamAlreadyExistsException(int championshipId, int teamId) {
        super("Team ("+ teamId +") already exists in Championship ("+ championshipId +").");
    }
}
