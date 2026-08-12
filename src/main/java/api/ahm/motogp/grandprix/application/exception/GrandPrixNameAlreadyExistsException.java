package api.ahm.motogp.grandprix.application.exception;

public class GrandPrixNameAlreadyExistsException extends RuntimeException {
    public GrandPrixNameAlreadyExistsException(String name) {
        super("GrandPrix name already exists: " + name);
    }
}
