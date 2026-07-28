package api.ahm.motogp.team.application.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Team with id " + id + " was not found");
    }
}
