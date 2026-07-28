package api.ahm.motogp.rider.application.exception;

public class RiderNumberAlreadyExistException extends RuntimeException {
    public RiderNumberAlreadyExistException(Integer number) {
        super("Rider with number " + number + " already exists.");
    }
}
