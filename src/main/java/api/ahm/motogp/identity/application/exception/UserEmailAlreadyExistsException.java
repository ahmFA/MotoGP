package api.ahm.motogp.identity.application.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String email) {
        super("User email already exists: " + email);
    }
}
