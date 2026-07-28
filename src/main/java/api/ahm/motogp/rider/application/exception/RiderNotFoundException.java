package api.ahm.motogp.rider.application.exception;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(Integer number) {
        super("Rider with id " + number + " was not found");
    }
}
