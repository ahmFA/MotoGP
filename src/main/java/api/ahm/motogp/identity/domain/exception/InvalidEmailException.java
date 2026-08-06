package api.ahm.motogp.identity.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("Email address format is invalid");
    }
}
