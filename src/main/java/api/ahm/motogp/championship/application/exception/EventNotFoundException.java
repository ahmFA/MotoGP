package api.ahm.motogp.championship.application.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(int id) {
        super("Championship grand prix event with ID: " + id + " not found");
    }
}
