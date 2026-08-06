package api.ahm.motogp.championship.application.exception;

public class ChampionshipEventNotFoundException extends RuntimeException {
    public ChampionshipEventNotFoundException(int id) {
        super("Championship grand prix event with ID: " + id + " not found");
    }
}
