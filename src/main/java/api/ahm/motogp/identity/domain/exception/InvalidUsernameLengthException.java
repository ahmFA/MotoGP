package api.ahm.motogp.identity.domain.exception;

public class InvalidUsernameLengthException extends RuntimeException {
    public InvalidUsernameLengthException() {
        super("Username length must be between 3 and 25 characters");
    }
}
