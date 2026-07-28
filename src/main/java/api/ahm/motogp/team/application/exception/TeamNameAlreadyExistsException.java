package api.ahm.motogp.team.application.exception;

public class TeamNameAlreadyExistsException extends RuntimeException {
    public TeamNameAlreadyExistsException(String name) {
        super("Team name already exists: " + name);
    }
}
