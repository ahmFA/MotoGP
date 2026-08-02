package api.ahm.motogp.rider.application.exception;

public class RiderIsNotActiveException extends RuntimeException {
    public RiderIsNotActiveException(int riderId) {
        super("Rider " + riderId + " is not active");
    }
}
