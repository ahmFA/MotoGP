package api.ahm.motogp.team.application.exception;

public class TeamIsNotActiveException extends RuntimeException {
    public TeamIsNotActiveException(int teamId) {
        super("Team " + teamId + " is not active");
    }
}
