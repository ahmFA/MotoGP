package api.ahm.motogp.championship.application.exception;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(int id) {
        super("Constructor with id " + id + " not found");
    }
}
