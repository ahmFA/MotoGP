package api.ahm.motogp.grandprix.application.exception;

public class GrandPrixNotFoundException extends RuntimeException {
    public GrandPrixNotFoundException(Integer id) {
        super("GrandPrix with id " + id + " was not found");
    }
}
