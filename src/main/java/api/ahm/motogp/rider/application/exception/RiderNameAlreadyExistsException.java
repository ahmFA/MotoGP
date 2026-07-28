package api.ahm.motogp.rider.application.exception;

public class RiderNameAlreadyExistsException extends RuntimeException {
    public RiderNameAlreadyExistsException(String name) {
        super("Rider name already exists: " + name);
    }
}
